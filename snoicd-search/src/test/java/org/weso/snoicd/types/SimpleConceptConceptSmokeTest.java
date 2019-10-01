package org.weso.snoicd.types;

import TestKit.SmokeTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

@Category(SmokeTest.class)
public class SimpleConceptConceptSmokeTest {

    @Test
    public void allPropertiesTest() {
        assertPojoMethodsFor(SimpleConcept.class).testing(
                Method.GETTER,
                Method.SETTER,
                Method.EQUALS,
                Method.HASH_CODE,
                Method.TO_STRING,
                Method.CONSTRUCTOR).areWellImplemented();

    }
}
