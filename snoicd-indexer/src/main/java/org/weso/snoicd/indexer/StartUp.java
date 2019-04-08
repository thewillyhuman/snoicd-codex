package org.weso.snoicd.indexer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.weso.snoicd.indexer.engine.CodeIndex;
import org.weso.snoicd.indexer.engine.DescriptionIndex;
import org.weso.snoicd.indexer.types.Concept;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.thewilly.bigtable.BigTable;
import io.thewilly.bigtable.BigTableProducer;
//import lombok.extern.slf4j.Slf4j;

/**
 * Hello world!
 *
 */
//@Slf4j
public class StartUp {
	
	private static BigTable<String, Concept> _condeptIDIndex = new BigTableProducer<String,Concept>().withIndexEngine( new CodeIndex() ).build();
	private static BigTable<String, Concept> _conceptDescriptionIndex = new BigTableProducer<String,Concept>().withIndexEngine( new DescriptionIndex() ).build();
	
	private static List<Concept> concepts = new ArrayList<Concept>();
	
	public static void main( String[] args ) throws JsonParseException, JsonMappingException, IOException {
		loadConceptsInMemory();
		indexConcepts();
		saveIndexes();
	}
	
	public static void loadConceptsInMemory() throws JsonParseException, JsonMappingException, IOException {
		System.out.println( "Memory warming started." );
		ObjectMapper mapper = new ObjectMapper();
		concepts = mapper.readValue( new File("concepts.json"), mapper.getTypeFactory().constructCollectionType(List.class, Concept.class) );
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
		
		System.out.println( "Concepts in big table." );
		System.out.println( "Cleaning memory." );
		concepts = null;
		System.out.println( "Memory clean." );
	}
	
	public static void saveIndexes() {
		Gson gson = new Gson();
		//String json = gson.toJson( _condeptIDIndex.getMemoryMap() );
		//System.out.println( json );
		String json = gson.toJson( _conceptDescriptionIndex.getMemoryMap() );
		System.out.println( json );
	}
}
