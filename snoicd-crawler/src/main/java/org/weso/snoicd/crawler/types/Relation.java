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

/**
 * Instance of Relation.java
 * 
 * @author 
 * @version 
 */
public interface Relation {

	/**
	 * 
	 * @return
	 */
	public String getRelationType();
	
	/**
	 * 
	 * @return
	 */
	public AbstractTerminologyNode getNodeToRelation();
}
