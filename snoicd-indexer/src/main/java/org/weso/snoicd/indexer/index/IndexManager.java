package org.weso.snoicd.indexer.index;

import org.weso.snoicd.indexer.io.ConceptsLoader;

/**
 * Manager for the indexing agents, will create a thread per core and each thread will index a chunk of the concepts list produced by the crawler.
 */
public class IndexManager {

    private ConceptsLoader conceptsLoader;
    private Thread[] threads;

    /**
     * @param conceptsLoader is the loader employed to load the concepts produced by the crawler.
     */
    public IndexManager(ConceptsLoader conceptsLoader) {
        this.conceptsLoader = conceptsLoader;
    }

    /**
     * Starts the process of indexing the concepts.
     */
    public void start() {
        int cores = Runtime.getRuntime().availableProcessors();
        int itemsPerChunk = conceptsLoader.getConcepts().size() / cores;
        int extraChunk = conceptsLoader.getConcepts().size() % 2;
        int startTh, endTh;
        threads = new Thread[cores];
        Thread itThread;

        for (int i = 0; i < cores; i++) {
            startTh = i * itemsPerChunk;
            endTh = (i + 1) * itemsPerChunk + extraChunk;
            itThread = new IndexAgent(conceptsLoader.getConcepts(), startTh, endTh);
            threads[i] = itThread;
            itThread.start();
        }
    }

    /**
     * Will perform a join operation for each thread created.
     */
    public void waitTillFinishes() {
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
