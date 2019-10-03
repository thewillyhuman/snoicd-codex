package org.weso.snoicd.search.search;

import org.junit.Test;
import org.weso.snoicd.core.ResponseToQuery;
import org.weso.snoicd.search.SnoicdSearch;
import org.weso.snoicd.search.SnoicdSearchDefaultImpl;
import org.weso.snoicd.search.core.AllFieldsSearchStrategy;
import org.weso.snoicd.search.core.ConceptIDSearchStrategy;
import org.weso.snoicd.search.core.DescriptionSearchStrategy;
import org.weso.snoicd.search.persistence.BigTablePersistenceImpl;

import java.util.Hashtable;
import java.util.Set;

public class ConceptIdSearchStrategyTest {

    private static SnoicdSearch smokeSearchApp = new SnoicdSearchDefaultImpl(
            "conceptIDIndex.json",
            "descriptionsIndex.json"
    );

    static {
        smokeSearchApp.loadConceptsInMemory();
    }

    private Hashtable<String, Long> stats = new Hashtable<>();
    private ResponseToQuery result;
    private int iterationsPerSearch = 100000;

    private static String SNOMED_FEVER_CODE = "315683006";
    private static String SNOMED_FEVER_DESCRIPTION = "fiebre";

    @Test
    public void allFieldsSearchTest() {
        stats.put("conceptIdInitTime", System.nanoTime());

        for(int i = 0; i < iterationsPerSearch; i ++) {
            result = smokeSearchApp.executeQuery(new AllFieldsSearchStrategy(BigTablePersistenceImpl.instance), "Barré");
        }

        stats.put("conceptIdEndTime", System.nanoTime());
        System.out.println("--- ALL FIELDS TEST ---");
        printResult(result);

        System.out.println("Time elapsed: " + ((stats.get("conceptIdEndTime").longValue() - stats.get("conceptIdInitTime").longValue()) /1000000));
    }

    @Test
    public void conceptIdSearchTest() {
        stats.put("conceptIdInitTime", System.nanoTime());

        for(int i = 0; i < iterationsPerSearch; i ++) {
            result = smokeSearchApp.executeQuery(new ConceptIDSearchStrategy(BigTablePersistenceImpl.instance), SNOMED_FEVER_CODE);
        }

        stats.put("conceptIdEndTime", System.nanoTime());
        System.out.println("--- CONCEPT TEST ---");
        printResult(result);
        System.out.println("Time elapsed: " + ((stats.get("conceptIdEndTime").longValue() - stats.get("conceptIdInitTime").longValue()) /1000000));
    }

    @Test
    public void descriptionsSearchTest() {
        stats.put("conceptIdInitTime", System.nanoTime());

        for(int i = 0; i < iterationsPerSearch; i ++) {
            result = smokeSearchApp.executeQuery(new DescriptionSearchStrategy(BigTablePersistenceImpl.instance), SNOMED_FEVER_DESCRIPTION);
        }

        stats.put("conceptIdEndTime", System.nanoTime());
        System.out.println("--- DESCRIPTIONS TEST ---");
        printResult(result);
        System.out.println("Time elapsed: " + ((long)(stats.get("conceptIdEndTime").longValue() - stats.get("conceptIdInitTime").longValue()) /1000000));
    }

    private void printResult(ResponseToQuery rtq) {
        System.out.println("Quey: " + rtq.getQuery());
        System.out.println("Results: " + rtq.getResult().size());
        //for(Concept c : rtq.getResult()) System.out.println("\t"+c);
    }
}
