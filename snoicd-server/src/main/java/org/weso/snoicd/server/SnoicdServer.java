package org.weso.snoicd.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.weso.snoicd.core.ResponseToQuery;
import org.weso.snoicd.search.SnoicdSearch;
import org.weso.snoicd.search.SnoicdSearchDefaultImpl;

import java.io.IOException;
import java.io.StringWriter;

import static spark.Spark.*;

public class SnoicdServer {

    public static SnoicdSearch QUERY_ENGINE;

    public static void main(String... args) {
        QUERY_ENGINE = new SnoicdSearchDefaultImpl(
                "C:\\git\\snoicd-codex\\snoicd-search\\conceptIDIndex.json",
                "C:\\git\\snoicd-codex\\snoicd-search\\descriptionsIndex.json"
        );

        QUERY_ENGINE.loadConceptsInMemory(); // Load the indexes in memory...

        get("/search", (req, res) -> {

            String query = req.queryParams("q");
            String filter = req.queryParams("filter");


            res.status(200);
            res.type("application/json");

            return dataToJson(new SearchExecutor(filter).execute(query));
        } );
    }

    public static String dataToJson(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);
            return sw.toString();
        } catch (IOException e){
            throw new RuntimeException("IOException from a StringWriter?");
        }
    }
}
