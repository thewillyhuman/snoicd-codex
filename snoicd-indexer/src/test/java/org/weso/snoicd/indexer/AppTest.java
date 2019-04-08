package org.weso.snoicd.indexer;

import org.junit.Test;
import org.weso.snoicd.indexer.utils.StringNormalizator;

/**
 * Unit test for simple App.
 */
public class AppTest {
    
	@Test
	public void stringUtilTest() {
		String text = "Fiebre-viral transmitida por artropodos, no (especificada) ([corchetes]).";
		System.out.println( StringNormalizator.normalize( text ) );
		String[] splitted = StringNormalizator.normalize( text ).split( " " );
		
		for(String s : splitted) {
			System.out.println( s );
		}
	}
	
}
