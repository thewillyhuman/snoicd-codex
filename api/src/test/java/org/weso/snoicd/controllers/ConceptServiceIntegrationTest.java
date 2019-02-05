/*
 * This source file is part of the snoicd open source project.
 *
 * Copyright (c) 2019 willy and the snoicd project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.weso.snoicd.StartUp;
import org.weso.snoicd.repositories.ConceptsRepository;
import org.weso.snoicd.services.ConceptsService;
import org.weso.snoicd.types.Concept;
import org.weso.snoicd.types.SimpleConcept;

import TestKit.IntegrationTest;

@SpringBootTest(classes = { StartUp.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@Category(IntegrationTest.class)
@DirtiesContext
public class ConceptServiceIntegrationTest {
	
	@Autowired
	private ConceptsRepository repo;
	
	@Autowired
	private ConceptsService service;
	
	@Before
	public void setUp() {

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

	@After
	public void tearDown() {
		repo.deleteAll();
	}
	
	@Test
	public void getConceptByCodeTest() {
		Concept c = repo.findByCode( "C-1" ).get( 0 );
		
		assertEquals( c, service.getConceptByCode( "C-1" ).get( 0 ) );
		
	}
	
	@Test
	public void getConceptByDescriptionTest() {
		Concept c = repo.findByCode( "C-1" ).get( 0 );
		
		assertEquals( c, service.getConceptsByDescription( "Description 1" ).get( 0 ) );
		
	}

}
