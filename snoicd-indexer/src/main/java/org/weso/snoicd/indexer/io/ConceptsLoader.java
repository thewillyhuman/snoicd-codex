package org.weso.snoicd.indexer.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.weso.snoicd.types.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConceptsLoader {

    private List<Concept> concepts;
    private String pathname;

    public ConceptsLoader(String pathname) {
        this.pathname = pathname;
        loadConceptsInMemory();
    }

    public List<Concept> getConcepts() {
        return this.concepts;
    }

    private void loadConceptsInMemory() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            concepts = mapper.readValue(
                    new File(pathname),
                    mapper.getTypeFactory().constructCollectionType(List.class, Concept.class)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
