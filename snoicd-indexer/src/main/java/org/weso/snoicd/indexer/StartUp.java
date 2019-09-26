package org.weso.snoicd.indexer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import io.thewilly.bigtable.BigTable;
import io.thewilly.bigtable.BigTableProducer;
import lombok.extern.java.Log;
import org.weso.snoicd.indexer.index.IndexManager;
import org.weso.snoicd.indexer.index.engine.CodeIndex;
import org.weso.snoicd.indexer.index.engine.DescriptionIndex;
import org.weso.snoicd.indexer.io.ConceptsLoader;
import org.weso.snoicd.indexer.io.SaveFileManager;
import org.weso.snoicd.types.Concept;

import java.io.IOException;

/**
 * Start up class. Main execution class for the application.
 * Once you run this class the process of indexation begins.
 */
@Log
public class StartUp {

    // Index for the id of the concepts.
    public static BigTable<String, Concept> CONCEPT_ID_INDEX =
            new BigTableProducer<String, Concept>()
                    .asParallel()
                    .withIndexEngine(new CodeIndex())
                    .build();

    // Index for the descriptions of the concepts.
    public static BigTable<String, Concept> CONCEPT_DESCRIPTIONS_INDEX =
            new BigTableProducer<String, Concept>()
                    .asParallel()
                    .withIndexEngine(new DescriptionIndex())
                    .build();

    /**
     * Main execution entry point of the application. No arguments required.
     *
     * @param args none of them are expected.
     * @throws JsonParseException   if and error occurs while loading the
     *                              concepts from the json file produced by the crawler.
     * @throws JsonMappingException if an error occurs while trying to match
     *                              the information contained in the json file produced by the crawler
     *                              with the expected information.
     * @throws IOException          occurs if any error happen during the process of
     *                              saving the indexes on to memory.
     */
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        log.info("Indexing concepts.");

        IndexManager indexManager = new IndexManager(new ConceptsLoader("concepts.json"));
        indexManager.start();
        indexManager.waitTillFinishes();

        log.info("Concepts indexed.");
        log.info("Saving concepts.");

        SaveFileManager saveFileManager = new SaveFileManager();
        saveFileManager.start();
        saveFileManager.waitTillFinishes();

        log.info("Concepts saved.");
    }
}
