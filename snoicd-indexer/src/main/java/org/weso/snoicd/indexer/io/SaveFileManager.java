package org.weso.snoicd.indexer.io;

import org.weso.snoicd.indexer.StartUp;

public class SaveFileManager {

    private SaveFileAgent conceptIdIndexAgent, descriptionIndexAgent;

    public void start() {
        conceptIdIndexAgent = new SaveFileAgent("../snoicd-search/conceptID.index", StartUp.CONCEPT_ID_INDEX);
        conceptIdIndexAgent.start();

        descriptionIndexAgent = new SaveFileAgent("../snoicd-search/descriptions.index", StartUp.CONCEPT_DESCRIPTIONS_INDEX);
        descriptionIndexAgent.start();
    }

    public void waitTillFinishes() {
        try {
            conceptIdIndexAgent.join();
            descriptionIndexAgent.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
