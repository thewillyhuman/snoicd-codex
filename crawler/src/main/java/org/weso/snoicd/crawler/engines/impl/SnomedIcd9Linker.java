package org.weso.snoicd.crawler.engines.impl;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.weso.snoicd.crawler.StartUp;
import org.weso.snoicd.crawler.engines.Crawler;
import org.weso.snoicd.crawler.types.AbstractTerminologyNode;

public class SnomedIcd9Linker extends AbstractCrawler {

    public SnomedIcd9Linker(String uri, int port, String dbName ) {
        super( uri, port, dbName );
    }

    @Override
    void specificCrawl() {
        // Getting the snomed collection.
        MongoCollection<Document> coll = super.hDB.getCollection( "snomed-icd9" );

        for(Document d : coll.find()) {

            Object snomedCodeO = d.get("SNOMED_CID");
            Object icd9CodeO = d.get("ICD_CODE");

            if(snomedCodeO == null || icd9CodeO == null)
                continue;

            String snomedCode = snomedCodeO.toString();
            String icd9Code = icd9CodeO.toString();


            AbstractTerminologyNode node = StartUp._nodes.get(snomedCode);

            if(node != null)
                node.getTranslationNodesIds().add(icd9Code);

            node = StartUp._nodes.get(icd9Code);

            if(node != null)
                node.getTranslationNodesIds().add(snomedCode);
        }
    }
}
