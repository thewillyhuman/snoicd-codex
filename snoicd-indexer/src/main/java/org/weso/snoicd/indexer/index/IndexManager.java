package org.weso.snoicd.indexer.index;

import org.weso.snoicd.indexer.io.ConceptsLoader;

public class IndexManager {

    private ConceptsLoader conceptsLoader;
    private Thread[] threads;

    public IndexManager(ConceptsLoader conceptsLoader) {
        this.conceptsLoader = conceptsLoader;
    }

    public void start() {
        int cores = Runtime.getRuntime().availableProcessors();
        int itemsPerChunk = conceptsLoader.getConcepts().size() / cores;
        int extraChunk = conceptsLoader.getConcepts().size() % 2;
        int startTh, endTh;
        threads = new Thread[cores];
        Thread itThread;

        for(int i = 0; i < cores; i++) {
            startTh = i * itemsPerChunk;
            endTh = (i+1) * itemsPerChunk + extraChunk;
            itThread = new IndexAgent(conceptsLoader.getConcepts(), startTh, endTh);
            threads[i] = itThread;
            itThread.start();
        }
    }

    public void waitTillFinishes() {
        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
