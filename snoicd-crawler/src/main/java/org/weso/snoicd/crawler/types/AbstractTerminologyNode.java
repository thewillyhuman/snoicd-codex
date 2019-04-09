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

import java.util.HashSet;
import java.util.Set;

/**
 * Instance of AbstractTerminologyNode.java
 * 
 * @author 
 * @version 
 */
public class AbstractTerminologyNode implements CrawlDataType {
	
	private String conceptId;
	private String terminologyName;
	private Set<String> descriptions;

	/* (non-Javadoc)
	 * @see org.weso.snoicd.crawler.types.CrawlDataType#getConceptID()
	 */
	@Override
	public String getConceptID() {
		return this.conceptId;
	}
	
	public void setConceptID(String conceptID) { this.conceptId = conceptID; }

	/* (non-Javadoc)
	 * @see org.weso.snoicd.crawler.types.CrawlDataType#getTerminologyName()
	 */
	@Override
	public String getTerminologyName() {
		return this.terminologyName;
	}
	
	public void setTerminologyName(String terminologyName) { this.terminologyName = terminologyName; }

	/* (non-Javadoc)
	 * @see org.weso.snoicd.crawler.types.CrawlDataType#getDescriptions()
	 */
	@Override
	public Set<String> getDescriptions() {
		if (this.descriptions == null) {
			return this.descriptions = new HashSet<String>();
		} else {
			return this.descriptions;
		}
	}
}
