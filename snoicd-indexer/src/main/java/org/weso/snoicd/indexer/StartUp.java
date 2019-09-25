package org.weso.snoicd.indexer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import io.thewilly.bigtable.BigTable;
import io.thewilly.bigtable.BigTableProducer;
import lombok.extern.java.Log;
import org.weso.snoicd.indexer.index.IndexManager;
import org.weso.snoicd.indexer.index.engine.CodeIndex;
import org.weso.snoicd.indexer.index.engine.DescriptionIndex;
import org.weso.snoicd.indexer.io.ConceptsLoader;
import org.weso.snoicd.indexer.io.SaveFileManager;
import org.weso.snoicd.types.*;

import java.io.IOException;

/**
 * Hello world!
 *
 */
@Log
public class StartUp {
	
	public static BigTable<String, Concept> CONCEPT_ID_INDEX = new BigTableProducer<String,Concept>().asParallel().withIndexEngine(new CodeIndex()).build();
	public static BigTable<String, Concept> CONCEPT_DESCRIPTIONS_INDEX = new BigTableProducer<String,Concept>().asParallel().withIndexEngine(new DescriptionIndex()).build();

	
	public static void main( String[] args ) throws JsonParseException, JsonMappingException, IOException {
		log.info( "Indexing concepts." );

		IndexManager indexManager = new IndexManager(new ConceptsLoader("concepts.json"));
		indexManager.start();
		indexManager.waitTillFinishes();
		
		log.info( "Concepts indexed." );
		log.info( "Saving concepts." );

		SaveFileManager saveFileManager = new SaveFileManager();
		saveFileManager.start();
		saveFileManager.waitTillFinishes();

		log.info( "Concepts saved." );
	}
}
