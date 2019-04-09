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

import java.util.HashSet;
import java.util.Set;

import org.weso.snoicd.crawler.engines.impl.Icd9Crawler;
import org.weso.snoicd.crawler.types.AbstractTerminologyNode;

/**
 * Instance of StartUp.java
 * 
 * @author 
 * @version 
 */
public class StartUp {
	
	public static Set<AbstractTerminologyNode> _nodes = new HashSet<AbstractTerminologyNode>();

	public static void main( String[] args ) {
		// new SnomedCrawler("34.245.137.253", 27017, "health-knowledge").start();
		new Icd9Crawler("34.245.137.253", 27017, "health-knowledge").start();
		// new Icd10Crawler("34.245.137.253", 27017, "health-knowledge").start();
	}
}
