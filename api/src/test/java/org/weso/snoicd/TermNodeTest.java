package org.weso.snoicd;

import org.bson.BSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.weso.snoicd.knowledge.graph.ChildNode;
import org.weso.snoicd.knowledge.graph.Description;
import org.weso.snoicd.knowledge.graph.TermNode;
import org.weso.snoicd.repository.TermNodeRepository;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import static org.junit.Assert.*;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.pojo.tester.api.assertion.Method;

/**
 * Unit test for simple App.
 */
//@SpringBootTest(classes = { StartUp.class })
//@RunWith(SpringJUnit4ClassRunner.class)
public class TermNodeTest {

	//@Autowired
	TermNodeRepository repo;

	@Test
	public void autoTest() {
		assertPojoMethodsFor(TermNode.class).testing(Method.GETTER, Method.SETTER, Method.EQUALS, Method.HASH_CODE,
				Method.TO_STRING, Method.CONSTRUCTOR).areWellImplemented();
	}

	/*
	 * @Test @Ignore public void insertOne() { TermNode node = new TermNode();
	 * node.setDescription( "Dolor de cabeza agudo" ); node.setIcd9Code( "001" );
	 * node.setIcd10Code( "A00" ); node.setSnomedCode( "S001.A001" );
	 * 
	 * ChildNode child = new ChildNode(); child.setDescription( "Jaqueca" );
	 * child.setIcd9Code( "002" ); child.setIcd10Code( "A002" );
	 * child.setSnomedCode( "S001.A001.1" );
	 * 
	 * ChildNode child2 = new ChildNode(); child2.setDescription( "Cefalea" );
	 * child2.setIcd9Code( "003" ); child2.setIcd10Code( "A003" );
	 * child2.setSnomedCode( "S001.A001.2" );
	 * 
	 * ChildNode[] children2 = { child2 }; //child.setChildren( children2 );
	 * 
	 * ChildNode[] children = { child }; node.setChildren( children );
	 * node.setChildren( children2 );
	 * 
	 * repo.save( node ); //repo.save( child ); //repo.save( child2 ); }
	 */

	@Test @Ignore
	public void populateTable() {
		MongoClient client = new MongoClient();
		DB database = client.getDB("snoicd-codex");
		DBCollection collection = database.getCollection("concepts-snomed");

		System.out.println("Conection to mongo opened ...");

		DBCursor cursor = collection.find();

		BasicDBObject queryChild;
		DBCursor children;
		List<ChildNode> childnodes;

		System.out.println("Populating table ...");

		while (cursor.hasNext()) {
			System.out.println("New result ...");
			DBObject result = cursor.next();
			TermNode node = new TermNode();
			node.setSnomedCode(result.get("conceptId").toString());
			Description desc = new Description("en_US", result.get("defaultTerm").toString());
			Description[] descs = { desc };
			node.setDescriptions(descs);

			if (result.containsField("relationships")) {

				System.out.println(result.get("relationships").toString());
				System.out.println(result.get("relationships").getClass());

				BasicDBList parentsList = (BasicDBList) result.get("relationships");
				List<String> parents = new ArrayList<String>();

				for (int i = 0; i < parentsList.size(); i++) {
					System.out.println(parentsList.get(i).getClass());
					BSONObject json = (BSONObject) parentsList.get(i);
					System.out.println(((BSONObject) json.get("target")).get("conceptId"));

					parents.add(((BSONObject) json.get("target")).get("conceptId").toString());
				}

				String[] parentsToSet = parents.toArray(new String[parents.size()]);

				node.setParents(parentsToSet);
			}

			// cursor.close();

			/*
			 * queryChild = new BasicDBObject(); queryChild.put(
			 * "relationships.target.conceptId", result.get( "conceptId" ).toString() );
			 * children = collection.find(queryChild); childnodes = new
			 * ArrayList<ChildNode>();
			 * 
			 * while(children.hasNext()) { System.out.println( "New child ..." ); DBObject
			 * child = children.next(); ChildNode childnode = new ChildNode();
			 * childnode.setSnomedCode( child.get( "conceptId" ).toString() );
			 * childnode.setDescription( child.get( "defaultTerm" ).toString() );
			 * 
			 * childnodes.add( childnode );
			 * 
			 * System.out.println( "New child added..." ); }
			 * 
			 * ChildNode[] toSet = childnodes.toArray( new ChildNode[childnodes.size()] );
			 * 
			 * node.setChildren( toSet );
			 */
			repo.save(node);
			System.out.println("New result added ...");
		}

		client.close();
		System.out.println("Connection closed ...");
	}

	@Test @Ignore
	public void testAllTermsLoaded() {
		List<TermNode> terms = repo.findAll();

		// That means all the codes have been correctly loaded in mongo.
		assertEquals(432471, terms.size());
	}

	@Test @Ignore
	public void invertModel() {
		List<TermNode> nodes = repo.findAll();

		int total = nodes.size();
		int current = 1;

		for (TermNode node : nodes) {

			if (node.getParents() != null) {
				
				System.out.println("Updating childrens for parents on one node");

				for (String parentId : node.getParents()) {
					TermNode parentNode = repo.findBySnomedCode(parentId);

					ChildNode[] parentChildNodes = parentNode.getChildren();

					if (parentChildNodes != null) {
						List<ChildNode> listToAppend = new ArrayList<ChildNode>(Arrays.asList(parentChildNodes));
						listToAppend.add(new ChildNode(node.getSnomedCode(), node.getIcd9Code(), node.getIcd10Code(),
								node.getDescriptions()));

						parentChildNodes = listToAppend.toArray(new ChildNode[listToAppend.size()]);

						parentNode.setChildren(parentChildNodes);

						repo.save(parentNode);
					} else {
						ChildNode[] parentChildNodesAux = { new ChildNode(node.getSnomedCode(), node.getIcd9Code(), node.getIcd10Code(),
								node.getDescriptions()) };
						
						parentNode.setChildren(parentChildNodesAux);

						repo.save(parentNode);
					}
				}
			}
			
			System.out.println("Status...  CURRENT: " + current + " TOTAL: " + total);
			current++;
		}
	}
	
	@Test @Ignore
	public void addIcd10Mapping() {
		MongoClient client = new MongoClient();
		DB database = client.getDB("snoicd-codex");
		DBCollection collection = database.getCollection("snomed-icd-map");
		
		List<TermNode> nodes = repo.findAll();
		BasicDBObject query;
		BasicDBObject ne;
		
		int total = nodes.size();
		int current = 1;
		
		for(TermNode node : nodes) {
			ne = new BasicDBObject("$ne","");

			query =  new BasicDBObject();
			
			query.put("mapTarget", ne);
			query.put("conceptId", node.getSnomedCode());
			
			DBCursor cursor = collection.find(query);
			
			if(cursor != null) {
				DBObject result = cursor.one();
				
				if(result != null) {
					String code = result.get("mapTarget").toString();
					System.out.println(code);
					node.setIcd10Code(code);
					repo.save(node);
				}
			}
			
			System.out.println("Status...  CURRENT: " + current + " TOTAL: " + total);
			current++;
						
		}
		
		
	}
	
	@Test @Ignore
	public void addIcd9Mapping() {
		MongoClient client = new MongoClient();
		DB database = client.getDB("snoicd-codex");
		DBCollection collection = database.getCollection("snomed-icd9-map");
		
		DBCursor cursor = collection.find();
		
		while(cursor.hasNext()) {
			DBObject result = cursor.next();
			System.out.println(result);
			
			if(result.get("SNOMED_CID")!= null) {
				TermNode node = repo.findBySnomedCode(result.get("SNOMED_CID").toString());
				
				if(node != null) {
					System.out.println(result.get("ICD_CODE").toString());
					node.setIcd9Code(result.get("ICD_CODE").toString());
					repo.save(node);
				}
			}			
		}		
	}
}
