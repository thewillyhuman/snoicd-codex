/*
 * This source file is part of the snoicd-crawler open source project.
 *
 * Copyright (c) 2019 willy and the snoicd-crawler project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.crawler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.weso.snoicd.crawler.engines.impl.AbstractCrawler;
import org.weso.snoicd.crawler.engines.impl.Icd10Crawler;
import org.weso.snoicd.crawler.engines.impl.Icd9Crawler;
import org.weso.snoicd.crawler.engines.impl.SnomedCrawler;
import org.weso.snoicd.crawler.types.AbstractTerminologyNode;

import com.google.gson.Gson;

/**
 * Instance of StartUp.java
 * 
 * @author 
 * @version 
 */
public class StartUp {
	
	public static Set<AbstractTerminologyNode> _nodes = new HashSet<AbstractTerminologyNode>();

	public static void main( String[] args ) throws InterruptedException, IOException {
		AbstractCrawler snomed = new SnomedCrawler("34.245.137.253", 27017, "health-knowledge");
		AbstractCrawler icd9 = new Icd9Crawler("34.245.137.253", 27017, "health-knowledge");
		AbstractCrawler icd10 = new Icd10Crawler("34.245.137.253", 27017, "health-knowledge");
		
		icd9.start();
		icd10.start();
		snomed.start();
		
		icd9.join();
		icd10.join();
		snomed.join();
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("concepts-test.json"));
		writer.append("[");
		_nodes.stream().forEach( ( n ) -> {
			try {
				writer.append( new Gson().toJson( n ) );
				writer.append( ",\n" );
			} catch (IOException e) {
				e.printStackTrace();
			}
		} );
		writer.append("]");
		writer.close();
	}
}
