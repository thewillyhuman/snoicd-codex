package org.weso.snoicd.server;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.weso.snoicd.search.SnoicdSearch;
import org.weso.snoicd.search.SnoicdSearchDefaultImpl;

@SpringBootApplication
public class SnoicdServer {

    public static SnoicdSearch QUERY_ENGINE;

    public static void main(String... args) {
        QUERY_ENGINE = new SnoicdSearchDefaultImpl(
                "C:\\git\\snoicd-codex\\snoicd-search\\conceptIDIndex.json",
                "C:\\git\\snoicd-codex\\snoicd-search\\descriptionsIndex.json"
        );

        QUERY_ENGINE.loadConceptsInMemory(); // Load the indexes in memory...

        // Start the application.
        SpringApplication.run(SnoicdServer.class, args);
    }
}
