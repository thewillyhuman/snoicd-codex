package org.weso.snoicd.crawler.two;

import main.java.org.weso.snoicd.glue.jobs.SnoicdGlueAbstractJob;
import main.java.org.weso.snoicd.glue.jobs.SnoicdGlueJobScheduller;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.weso.snoicd.crawler.StartUp;
import org.weso.snoicd.crawler.types.AbstractTerminologyNode;
import org.weso.snoicd.crawler.types.icd.ICDVersion;
import org.weso.snoicd.crawler.types.icd.IcdNode;
import org.weso.snoicd.crawler.types.snomed.SnomedNode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SnoicdSnomedCrawlJob extends SnoicdGlueAbstractJob {

    private final String pathToFileToCrawl;
    private JSONArray arrayOfICD9NodesInFile;

    public SnoicdSnomedCrawlJob(String jobIdentifier, String pathToFileToCrawl) {
        super(jobIdentifier);
        this.pathToFileToCrawl = pathToFileToCrawl;
    }

    @Override
    public SnoicdGlueJobScheduller getScheduller() {
        throw new NotImplementedException();
    }

    @Override
    public void run() {
        try {
            loadFileToCrawlInJSONArray();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void loadFileToCrawlInJSONArray() throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader(this.pathToFileToCrawl));

        arrayOfICD9NodesInFile = (JSONArray) obj;

        Iterator nodesIterator = arrayOfICD9NodesInFile.iterator();
        AbstractTerminologyNode abstractNode;

        while(nodesIterator.hasNext()) {
            JSONObject node = (JSONObject) nodesIterator.next();

            System.out.println("PROCESSING --> " + node.toJSONString());

            abstractNode = new SnomedNode();

            abstractNode.setTerminologyName("SNOMED");
            abstractNode.setConceptID(node.get("code").toString());

            // Get the descriptions
            List<String> descriptions = (ArrayList)node.get("descriptions");

            if(descriptions != null) {
                AbstractTerminologyNode finalAbstractNode = abstractNode;
                descriptions.forEach(description -> finalAbstractNode.getDescriptions().add(description));
            }

            StartUp._nodes.put(abstractNode.getConceptID(), abstractNode);
        }
    }
}
