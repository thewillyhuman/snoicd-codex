package org.weso.snoicd.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.annotation.WebInitParam;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.weso.snoicd.StartUp;
import org.weso.snoicd.repositories.ConceptsRepository;
import org.weso.snoicd.types.Concept;
import org.weso.snoicd.types.SimpleConcept;

import TestKit.IntegrationTest;

@SpringBootTest(classes = { StartUp.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@Category(IntegrationTest.class)
@DirtiesContext
public class ConceptControllerIntegrationTest {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	private MockHttpSession session;

	@Autowired
	ConceptsRepository repo;

	@MockBean
	private RestTemplate template;

	@Before
	public void setUp() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		session = new MockHttpSession();

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
	public void statusTest() throws Exception {
		MockHttpServletRequestBuilder request = get("/api/search?q=C-1&filter=code").session(session)
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk());
	}

	@Test
	public void findConceptsByDescriptionTest() throws Exception {
		MockHttpServletRequestBuilder request = get("/api/search?q=des&filter=description").session(session)
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk());
	}
	
	@Test
	public void findConceptsByAllFieldsTest() throws Exception {
		MockHttpServletRequestBuilder request = get("/api/search?q=C-1").session(session)
				.contentType(MediaType.APPLICATION_JSON);
		// mockMvc.perform(request).andExpect(status().isOk());
	}

}
