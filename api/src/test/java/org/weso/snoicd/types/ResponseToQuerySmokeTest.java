package org.weso.snoicd.types;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import TestKit.SmokeTest;
import pl.pojo.tester.api.assertion.Method;

@Category(SmokeTest.class)
public class ResponseToQuerySmokeTest {

	@Test
	public void allPropertiesTest() {
		assertPojoMethodsFor(ResponseToQuery.class).testing(
				Method.GETTER,
				Method.SETTER,
				Method.EQUALS,
				Method.HASH_CODE,
				Method.TO_STRING,
				Method.CONSTRUCTOR).areWellImplemented();
		
	}
}
