package org.weso.snoicd.knowledge.graph;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.weso.snoicd.types.ChildNode;

import TestKit.UnitTest;
import pl.pojo.tester.api.assertion.Method;

@Category(UnitTest.class)
public class ChildNodeTest {
	
	@Test
	public void automaticTest() {
		assertPojoMethodsFor(ChildNode.class).testing(
				Method.GETTER, 
				Method.SETTER, 
				Method.EQUALS, 
				Method.HASH_CODE,
				Method.TO_STRING, 
				Method.CONSTRUCTOR )
		.areWellImplemented();
	}
}
