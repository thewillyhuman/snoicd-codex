package org.weso.snoicd.indexer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.weso.snoicd.indexer.engine.CodeIndex;
import org.weso.snoicd.indexer.engine.DescriptionIndex;
import org.weso.snoicd.types.Concept;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.thewilly.bigtable.BigTable;
import io.thewilly.bigtable.BigTableProducer;
import lombok.extern.java.Log;

/**
 * Hello world!
 *
 */
@Log
public class StartUp {
	
	private static BigTable<String, Concept> _condeptIDIndex = new BigTableProducer<String,Concept>().withIndexEngine( new CodeIndex() ).build();
	private static BigTable<String, Concept> _conceptDescriptionIndex = new BigTableProducer<String,Concept>().withIndexEngine( new DescriptionIndex() ).build();
	
	private static List<Concept> concepts = new ArrayList<Concept>();
	
	public static void main( String[] args ) throws JsonParseException, JsonMappingException, IOException {
		log.info( "Memory warming started." );
		loadConceptsInMemory();
		log.info( "Concepts loaded in memory." );
		log.info( "Indexing concepts." );
		indexConcepts();
		log.info( "Concepts indexed." );
		log.info( "Saving concepts." );
		saveIndexes();
		log.info( "Concepts saved." );
	}
	
	public static void loadConceptsInMemory() throws JsonParseException, JsonMappingException, IOException {
		log.info( "Reading crawler file." );
		ObjectMapper mapper = new ObjectMapper();
		concepts = mapper.readValue( new File("concepts.json"), mapper.getTypeFactory().constructCollectionType(List.class, Concept.class) );
		log.info( "Crawler file readed." );
	}
	
	public static void indexConcepts() {
		
		for(Concept c : concepts) {
			if( c.getCode() != null && c.getDescriptions() != null) {
				
				_condeptIDIndex.insert( c.getCode(), c );
				
				for(String description : c.getDescriptions()) {
					_conceptDescriptionIndex.insert( description, c );
				}
			}
		}
		
		log.info( "Concepts in big table." );
		log.info( "Cleaning memory." );
		concepts = null;
		log.info( "Memory clean." );
	}
	
	public static void saveIndexes() throws IOException {
		log.info( "Saving id index." );
		FileOutputStream fos = new FileOutputStream( "../snoicd-search/conceptID.index" );
		ObjectOutputStream oos = new ObjectOutputStream( fos );
		oos.reset();
		oos.writeObject( _condeptIDIndex.getMemoryMap() );
		oos.close();
		fos.close();
		
		log.info( "Saving descriptions index." );
		fos = new FileOutputStream( "../snoicd-search/descriptions.index" );
		oos = new ObjectOutputStream( fos );
		oos.reset();
		oos.writeObject( _conceptDescriptionIndex.getMemoryMap() );
		oos.close();
		fos.close();
	}
}
