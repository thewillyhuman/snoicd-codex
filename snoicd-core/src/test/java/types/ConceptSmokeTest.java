package types;

import TestKit.SmokeTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.weso.snoicd.core.Concept;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

@Category(SmokeTest.class)
public class ConceptSmokeTest {

    @Test
    public void allPropertiesTest() {
        assertPojoMethodsFor(Concept.class).testing(
                Method.GETTER,
                Method.SETTER,
                Method.EQUALS,
                Method.HASH_CODE,
                Method.TO_STRING,
                Method.CONSTRUCTOR).areWellImplemented();

    }
}
