package org.weso.snoicd.knowledge.graph;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.weso.snoicd.types.TermNode;

import TestKit.UnitTest;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;
import pl.pojo.tester.api.assertion.Method;

@Category(UnitTest.class)
public class TermNodeTest {

	@Test
	public void automaticTest() {
		assertPojoMethodsFor(TermNode.class).testing(
				Method.GETTER, 
				Method.SETTER, 
				Method.EQUALS, 
				Method.HASH_CODE,
				Method.TO_STRING, 
				Method.CONSTRUCTOR )
		.areWellImplemented();
	}
	
}
