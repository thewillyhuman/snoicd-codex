/*
 * This source file is part of the snoicd-crawler open source project.
 *
 * Copyright (c) 2019 willy and the snoicd-crawler project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.crawler.types;

import java.util.Set;

/**
 * Instance of CrawlDataType.java
 * 
 * @author Guillermo Facundo Colunga.
 * @version
 */
public interface CrawlDataType {

	/**
	 * Gets the unique code that identifies a concept on its terminology. It
	 * serves as a unique key for the terminology.
	 * 
	 * @return the code that identifies the concept in the terminology.
	 */
	public String getConceptID();

	/**
	 * Gets the terminology name to which the concept belongs.
	 * 
	 * @return the terminology name to which the concept belongs.
	 */
	public String getTerminologyName();

	/**
	 * Gets the set of possible descriptions that can be applied to the concept.
	 * 
	 * @return the set of possible descriptions that can be applied to the concept.
	 */
	public Set<String> getDescriptions();
}
