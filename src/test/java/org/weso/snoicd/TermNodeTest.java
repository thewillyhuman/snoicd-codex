package org.weso.snoicd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.weso.snoicd.knowledge.graph.TermNode;
import org.weso.snoicd.repository.TermNodeRepository;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

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
	
	@Test
	public void insertOne() {
		TermNode node = new TermNode();
		node.setDescription( "Dolor de cabeza agudo" );
		node.setIcd9Code( "001" );
		node.setIcd10Code( "A00" );
		node.setSnomedCode( "S001.A001" );
		
		TermNode child = new TermNode();
		child.setDescription( "Jaqueca" );
		child.setIcd9Code( "002" );
		child.setIcd10Code( "A002" );
		node.setSnomedCode( "S001.A001.1" );
		
		TermNode[] children = {child};
		
		node.setChildren( children );
		
		/*repo.save( node );
		repo.save( child );*/
	}
}
