package org.weso.snoicd.types;

import java.util.ArrayList;
import java.util.List;

import org.bson.BSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.weso.snoicd.StartUp;
import org.weso.snoicd.repository.ConceptsRepository;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@SpringBootTest(classes = { StartUp.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class ConceptTest {

	@Autowired
	ConceptsRepository repo;

	@Test @Ignore
	public void insertOne() {
		SimpleConcept sc = new SimpleConcept();
		sc.setCode("SC-1");
		sc.getDescriptions().add("Simple description 1");

		Concept c = new Concept();
		c.setCode("C-1");
		c.getDescriptions().add("Description 1");

		c.getRelatedCodes().add(sc);

		repo.save(c);
	}

	@Test @Ignore
	public void addRelatedCode() {
		SimpleConcept sc = new SimpleConcept();
		sc.setCode("SC-2");
		sc.getDescriptions().add("Simple description 2");

		Concept c = repo.findByCode("C-1").get(0);

		c.getRelatedCodes().add(sc);

		repo.save(c);

	}

	@Test @Ignore
	public void addSnomed() {
		MongoClient client = new MongoClient();

		@SuppressWarnings("deprecation")
		DB database = client.getDB("snomed");

		DBCollection collection = database.getCollection("concepts");

		System.out.println("Conection to mongo opened ...");

		DBCursor cursor = collection.find();

		System.out.println("Populating table ...");
		
		int j = 0;
		int t = cursor.size();

		while (cursor.hasNext()) {
			DBObject result = cursor.next();
			
			Concept concept = new Concept();
			
			concept.setCode(result.get("conceptId").toString());
			concept.setTerminologyName("snomed");
			
			BasicDBList descriptions = (BasicDBList) result.get("descriptions");
			
			for (int i = 0; i < descriptions.size(); i++) {
				BSONObject json = (BSONObject) descriptions.get(i);
				if((json.get("languageCode").toString().equals("es"))) {
					concept.getDescriptions().add(json.get("term").toString());
				}
				
			}
			

			repo.save(concept);
			
			System.out.println(j++ + "/" + t);
		}

		client.close();
		System.out.println("Connection closed ...");
	}
	
	@Test @Ignore
	public void addIcd9() {
		MongoClient client = new MongoClient();

		@SuppressWarnings("deprecation")
		DB database = client.getDB("snomed");

		DBCollection collection = database.getCollection("icd9");

		System.out.println("Conection to mongo opened ...");


		BasicDBObject query =  new BasicDBObject();;
		BasicDBObject ne;
		
		ne = new BasicDBObject("$ne","");
		query.put("Id9", ne);
		
		DBCursor cursor = collection.find(query);

		System.out.println("Populating table ...");
		
		int j = 0;
		int t = cursor.size();

		while (cursor.hasNext()) {
			DBObject result = cursor.next();
			
			Concept concept = new Concept();
			
			concept.setCode(result.get("Id9").toString());
			concept.getDescriptions().add(result.get("Desc9").toString());
			concept.setTerminologyName("icd_9");
					
			repo.save(concept);
			
			System.out.println(j++ + "/" + t);
		}

		client.close();
		System.out.println("Connection closed ...");
	}
	
	@Test @Ignore
	public void addIcd10() {
		MongoClient client = new MongoClient();

		@SuppressWarnings("deprecation")
		DB database = client.getDB("snomed");

		DBCollection collection = database.getCollection("icd10");

		System.out.println("Conection to mongo opened ...");


		BasicDBObject query =  new BasicDBObject();;
		BasicDBObject ne;
		
		ne = new BasicDBObject("$ne","");
		query.put("id10", ne);
		
		DBCursor cursor = collection.find(query);

		System.out.println("Populating table ...");
		
		int j = 0;
		int t = cursor.size();

		while (cursor.hasNext()) {
			DBObject result = cursor.next();
			
			Concept concept = new Concept();
			
			concept.setCode(result.get("id10").toString());
			concept.getDescriptions().add(result.get("dec10").toString());
			concept.setTerminologyName("icd_10");
					
			repo.save(concept);
			
			System.out.println(j++ + "/" + t);
		}

		client.close();
		System.out.println("Connection closed ...");
	}
	
	@Test @Ignore
	public void addIcd9SnomedMapping() {
		MongoClient client = new MongoClient();
		DB database = client.getDB("snoicd-codex");
		DBCollection collection = database.getCollection("snomed-icd9-map");
		
		DBCursor cursor = collection.find();
		
		while(cursor.hasNext()) {
			DBObject result = cursor.next();
			System.out.println(result);
			
			Object icd9Code = result.get("ICD_CODE");
			Object snomedCode = result.get("SNOMED_CID");
			
			if(icd9Code != null && snomedCode != null) {
				List<Concept> snomedCodesToAddMapping = repo.findByCode(snomedCode.toString());
				List<Concept> icd9CodesToAddMapping = repo.findByCode(icd9Code.toString());
				
				List<SimpleConcept> snomedSimpleCodes = new ArrayList<SimpleConcept>();
				List<SimpleConcept> icd9SimpleCodes = new ArrayList<SimpleConcept>();
				
				SimpleConcept sc;
				
				for(Concept c : snomedCodesToAddMapping) {
					sc = new SimpleConcept();
					sc.setCode(c.getCode());
					sc.setDescriptions(c.getDescriptions());
					sc.setTerminologyName("snomed");
					snomedSimpleCodes.add(sc);
				}
				
				for(Concept c : icd9CodesToAddMapping) {
					sc = new SimpleConcept();
					sc.setCode(c.getCode());
					sc.setDescriptions(c.getDescriptions());
					sc.setTerminologyName("icd_9");
					icd9SimpleCodes.add(sc);
				}
				
				for(Concept c : snomedCodesToAddMapping) {
					c.getRelatedCodes().addAll(icd9SimpleCodes);
					repo.save(c);
				}
				
				for(Concept c : icd9CodesToAddMapping) {
					c.getRelatedCodes().addAll(snomedSimpleCodes);
					repo.save(c);
				}
			}
			
		}
	}
	
	@Test @Ignore
	public void addIcd10SnomedMapping() {
		MongoClient client = new MongoClient();
		DB database = client.getDB("snoicd-codex");
		DBCollection collection = database.getCollection("snomed-icd-map");
		
		DBCursor cursor = collection.find();
		
		while(cursor.hasNext()) {
			DBObject result = cursor.next();
			System.out.println(result);
			
			Object icd9Code = result.get("mapTarget");
			Object snomedCode = result.get("conceptId");
			
			if(icd9Code != null && snomedCode != null) {
				List<Concept> snomedCodesToAddMapping = repo.findByCode(snomedCode.toString());
				List<Concept> icd9CodesToAddMapping = repo.findByCode(icd9Code.toString());
				
				List<SimpleConcept> snomedSimpleCodes = new ArrayList<SimpleConcept>();
				List<SimpleConcept> icd9SimpleCodes = new ArrayList<SimpleConcept>();
				
				SimpleConcept sc;
				
				for(Concept c : snomedCodesToAddMapping) {
					sc = new SimpleConcept();
					sc.setCode(c.getCode());
					sc.setDescriptions(c.getDescriptions());
					sc.setTerminologyName("snomed");
					snomedSimpleCodes.add(sc);
				}
				
				for(Concept c : icd9CodesToAddMapping) {
					sc = new SimpleConcept();
					sc.setCode(c.getCode());
					sc.setDescriptions(c.getDescriptions());
					sc.setTerminologyName("icd_10");
					icd9SimpleCodes.add(sc);
				}
				
				for(Concept c : snomedCodesToAddMapping) {
					c.getRelatedCodes().addAll(icd9SimpleCodes);
					repo.save(c);
				}
				
				for(Concept c : icd9CodesToAddMapping) {
					c.getRelatedCodes().addAll(snomedSimpleCodes);
					repo.save(c);
				}
			}
			
		}
	}

}
