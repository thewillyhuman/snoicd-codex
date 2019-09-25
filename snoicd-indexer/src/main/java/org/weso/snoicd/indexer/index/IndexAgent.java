package org.weso.snoicd.indexer.index;

import org.weso.snoicd.indexer.StartUp;
import org.weso.snoicd.types.Concept;

import java.util.List;

public class IndexAgent extends Thread {

    private List<Concept> concepts;
    private int startPosition, endPosition;

    public IndexAgent(List<Concept> concepts, int startPosition, int endPosition) {
        this.concepts = concepts;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    @Override
    public void run() {
        for(int i = startPosition; i < endPosition; i++) {
            Concept c = concepts.get(i);
            if( c.getCode() != null && c.getDescriptions() != null) {

                StartUp.CONCEPT_ID_INDEX.insert( c.getCode(), c );

                for(String description : c.getDescriptions()) {
                    StartUp.CONCEPT_DESCRIPTIONS_INDEX.insert( description, c );
                }
            }
        }
    }
}
