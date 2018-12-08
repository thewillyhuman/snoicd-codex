package org.weso.snoicd;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.weso.snoicd.knowledge.graph.ChildNode;
import org.weso.snoicd.knowledge.graph.TermNode;
import org.weso.snoicd.repository.TermNodeRepository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import static org.junit.Assert.*;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import java.util.List;

import pl.pojo.tester.api.assertion.Method;

/**
 * Unit test for simple App.
 */
@SpringBootTest(classes = { StartUp.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class TermNodeTest {
	
	@Autowired
	TermNodeRepository repo;

	@Test
	public void autoTest() {
		assertPojoMethodsFor( TermNode.class )
				.testing(
						Method.GETTER,
						Method.SETTER,
						Method.EQUALS,
						Method.HASH_CODE,
						Method.TO_STRING,
						Method.CONSTRUCTOR )
				.areWellImplemented();
	}
	
	@Test @Ignore
	public void insertOne() {
		TermNode node = new TermNode();
		node.setDescription( "Dolor de cabeza agudo" );
		node.setIcd9Code( "001" );
		node.setIcd10Code( "A00" );
		node.setSnomedCode( "S001.A001" );
		
		ChildNode child = new ChildNode();
		child.setDescription( "Jaqueca" );
		child.setIcd9Code( "002" );
		child.setIcd10Code( "A002" );
		child.setSnomedCode( "S001.A001.1" );
		
		ChildNode child2 = new ChildNode();
		child2.setDescription( "Cefalea" );
		child2.setIcd9Code( "003" );
		child2.setIcd10Code( "A003" );
		child2.setSnomedCode( "S001.A001.2" );
		
		ChildNode[] children2 = { child2 };
		//child.setChildren( children2 );
		
		ChildNode[] children = { child };
		node.setChildren( children );
		node.setChildren( children2 );
		
		repo.save( node );
		//repo.save( child );
		//repo.save( child2 );
	}
	
	@Test @Ignore
	public void findOne() {
		TermNode node = repo.findByIcd9Code( "001" );
		System.out.println( node );
	}
	
	@Test @Ignore
	public void populateTable() {
		MongoClient client = new MongoClient();
		DB database = client.getDB( "snomed" );
		DBCollection collection = database.getCollection( "concepts" );
		
		System.out.println( "Conection to mongo opened ..." );
		
		DBCursor cursor = collection.find();
		
		BasicDBObject queryChild;		
		DBCursor children;
		List<ChildNode> childnodes;
		
		System.out.println( "Populating table ..." );
		
		while(cursor.hasNext()) {
			System.out.println( "New result ..." );
			DBObject result = cursor.next();
			TermNode node = new TermNode();
			node.setSnomedCode( result.get( "conceptId" ).toString() );
			node.setDescription( result.get( "defaultTerm" ).toString() );
			
			/*queryChild = new BasicDBObject();
			queryChild.put( "relationships.target.conceptId", result.get( "conceptId" ).toString() );
			children = collection.find(queryChild);
			childnodes = new ArrayList<ChildNode>();
			
			while(children.hasNext()) {
				System.out.println( "New child ..." );
				DBObject child = children.next();
				ChildNode childnode = new ChildNode();
				childnode.setSnomedCode( child.get( "conceptId" ).toString() );
				childnode.setDescription( child.get( "defaultTerm" ).toString() );
				
				childnodes.add( childnode );
				
				System.out.println( "New child added..." );
			}
			
			ChildNode[] toSet = childnodes.toArray( new ChildNode[childnodes.size()] );
			
			node.setChildren( toSet );
			*/
			repo.save( node );
			System.out.println( "New result added ..." );
		}
		
		client.close();
		System.out.println( "Connection closed ..." );
	}
	
	@Test @Ignore
	public void testAllTermsLoaded() {
		List<TermNode> terms = repo.findAll();
		
		// That means all the codes have been correctly loaded in mongo.
		assertEquals(432471, terms.size());
	}
}
