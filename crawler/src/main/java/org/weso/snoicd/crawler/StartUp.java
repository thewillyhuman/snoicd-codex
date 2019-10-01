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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.weso.snoicd.crawler.engines.impl.*;
import org.weso.snoicd.crawler.types.AbstractTerminologyNode;

import com.google.gson.Gson;

/**
 * Instance of StartUp.java
 * 
 * @author Guillermo Facundo Colunga
 * @version 0.1
 */
public class StartUp {
	
	public static Map<String, AbstractTerminologyNode> _nodes = new HashMap<>();


	public static void main( String[] args ) throws InterruptedException, IOException {
		String mongoIp = "34.243.166.227";

		AbstractCrawler snomed = new SnomedCrawler(mongoIp, 27017, "health-knowledge");
		AbstractCrawler icd9 = new Icd9Crawler(mongoIp, 27017, "health-knowledge");
		AbstractCrawler icd10 = new Icd10Crawler(mongoIp, 27017, "health-knowledge");

		AbstractCrawler snomedIcd9Linker = new SnomedIcd9Linker(mongoIp,27017,"health-knowledge");
		AbstractCrawler snomedIcd10Linker = new SnomedIcd9Linker(mongoIp,27017,"health-knowledge");

		System.out.println("Start crawling data");

		icd9.start();
		icd10.start();
		snomed.start();
		
		icd9.join();
		icd10.join();
		snomed.join();

		System.out.println("End crawling data");
		System.out.println("End linking data");

		snomedIcd9Linker.start();
		snomedIcd10Linker.start();

		snomedIcd9Linker.join();
		snomedIcd10Linker.join();

		System.out.println("Writing crawled data");

		BufferedWriter writer = new BufferedWriter(new FileWriter("snoicd-crawler/concepts-test.json"));
		writer.append("[");
		_nodes.forEach( ( k, v ) -> {
			try {
				writer.append( new Gson().toJson( v ) );
				writer.append( ",\n" );
			} catch (IOException e) {
				e.printStackTrace();
			}
		} );
		writer.append("]");
		writer.close();

		System.out.println("End writing data");
	}
}
