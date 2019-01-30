package org.weso.snoicd.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.weso.snoicd.StartUp;
import org.weso.snoicd.repositories.ConceptsRepository;
import org.weso.snoicd.types.Concept;
import org.weso.snoicd.types.SimpleConcept;

import TestKit.IntegrationTest;

@SpringBootTest(classes = { StartUp.class })
@RunWith(SpringJUnit4ClassRunner.class)
@DataMongoTest
@ActiveProfiles("test")
@Category(IntegrationTest.class)
public class ConceptRepositoryIntegrationTest {
	
	@Autowired
	ConceptsRepository repo;
	
	@After
	public void tearDown() {
		this.repo.deleteAll();
	}
	
	@Test
	public void insertTest() {
		SimpleConcept sc = new SimpleConcept();
		sc.setCode("SC-1");
		sc.getDescriptions().add("Simple description 1");

		Concept c = new Concept();
		c.setCode("C-1");
		c.getDescriptions().add("Description 1");

		c.getRelatedCodes().add(sc);

		repo.save(c);
		
		assertEquals(c, repo.findByCode("C-1").get(0));
	}
	
	@Test
	public void updateTest() {
		SimpleConcept sc = new SimpleConcept();
		sc.setCode("SC-1");
		sc.getDescriptions().add("Simple description 1");

		Concept c = new Concept();
		c.setCode("C-1");
		c.getDescriptions().add("Description 1");

		repo.save(c);
		
		assertEquals(repo.findByCode("C-1").get(0).getRelatedCodes().size(), 0);
		
		Concept fromRepo = repo.findByCode("C-1").get(0);
		fromRepo.getRelatedCodes().add(sc);
		repo.save(fromRepo);
		
		assertEquals(repo.findByCode("C-1").get(0).getRelatedCodes().size(), 1);
	}
	
	@Test
	public void findByCodeTest() {
		SimpleConcept sc = new SimpleConcept();
		sc.setCode("SC-1");
		sc.getDescriptions().add("Simple description 1");

		Concept c = new Concept();
		c.setCode("C-1");
		c.getDescriptions().add("Description 1");

		c.getRelatedCodes().add(sc);

		repo.save(c);
		
		assertEquals(c, repo.findByCode("C-1").get(0));
	}
	
	@Test
	public void findByDescriptionTest() {
		SimpleConcept sc = new SimpleConcept();
		sc.setCode("SC-1");
		sc.getDescriptions().add("Simple description 1");

		Concept c = new Concept();
		c.setCode("C-1");
		c.getDescriptions().add("Description 1");

		c.getRelatedCodes().add(sc);

		repo.save(c);
		
		assertEquals(c, repo.findByDescription("Description").get(0));
	}

}
