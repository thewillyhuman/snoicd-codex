package org.weso.snoicd.crawler.two;

import main.java.org.weso.snoicd.glue.SnoicdGlue;
import main.java.org.weso.snoicd.glue.SnoicdGlueImpl;

public class SnoicdCrawlStartUp {

    private static SnoicdGlue glue = new SnoicdGlueImpl();

    public static void main(String... args) {
        glue.submitJob(
                new Snoicd9CrawlJob(
                        "icd9Job",
                        "C:\\git\\snoicd-codex\\data\\icd9-en.json")
        );

        glue.submitJob(
                new Snoicd10CrawlJob(
                        "icd10Job",
                        "C:\\git\\snoicd-codex\\data\\icd-10-en.json")
        );

        glue.submitJob(
                new SnoicdSnomedCrawlJob(
                        "snomedJob",
                        "C:\\git\\snoicd-codex\\data\\concepts.json")
        );

        glue.executeAllJobs();
        glue.waitUntillAllJobsFinished();
    }
}
