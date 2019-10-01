package org.weso.snoicd.crawler.engines.impl;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.weso.snoicd.crawler.StartUp;
import org.weso.snoicd.crawler.types.AbstractTerminologyNode;

public class SnomedIcd10Linker extends AbstractCrawler {

    public SnomedIcd10Linker(String uri, int port, String dbName ) {
        super( uri, port, dbName );
    }

    @Override
    void specificCrawl() {
        // Getting the snomed collection.
        MongoCollection<Document> coll = super.hDB.getCollection( "snomed-icd10" );

        for(Document d : coll.find()) {

            Object snomedCodeO = d.get("conceptId");
            Object icd10CodeO = d.get("mapTarget");

            if(snomedCodeO == null || icd10CodeO == null)
                continue;

            String snomedCode = snomedCodeO.toString();
            String icd10Code = icd10CodeO.toString();


            AbstractTerminologyNode node = StartUp._nodes.get(snomedCode);

            if(node != null)
                node.getTranslationNodesIds().add(icd10Code);

            node = StartUp._nodes.get(icd10Code);

            if(node != null)
                node.getTranslationNodesIds().add(snomedCode);
        }
    }
}
