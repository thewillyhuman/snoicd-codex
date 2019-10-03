package org.weso.snoicd.indexer.io;

import org.weso.snoicd.indexer.StartUp;

/**
 * Is the manager for the agents that will save the different indexes in the .index files. Those files will be the ones used by the search module to perform the queries.
 */
public class SaveFileManager {

    private SaveFileAgent conceptIdIndexAgent, descriptionIndexAgent;
    private volatile boolean threadsStarted = false;

    /**
     * Starts two threads, one for each index to be written at the same time but in different locations.
     */
    public void start() {

        descriptionIndexAgent = new SaveFileAgent("../snoicd-search/descriptionsIndex.json", StartUp.CONCEPT_DESCRIPTIONS_INDEX);
        descriptionIndexAgent.start();

        conceptIdIndexAgent = new SaveFileAgent("../snoicd-search/conceptIDIndex.json", StartUp.CONCEPT_ID_INDEX);
        conceptIdIndexAgent.start();

        threadsStarted = true;
    }

    /**
     * Performs a join operation over the threads that are writing the index files, notice that this method must be called only after the start, else will produce an exception.
     */
    public void waitTillFinishes() {

        if (!threadsStarted)
            throw new IllegalStateException("Start method not called, please start() the process of saving the indexes first");

        try {
            conceptIdIndexAgent.join();
            descriptionIndexAgent.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
