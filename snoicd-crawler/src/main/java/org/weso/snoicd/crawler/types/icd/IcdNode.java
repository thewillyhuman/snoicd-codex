/*
 * This source file is part of the snoicd-crawler open source project.
 *
 * Copyright (c) 2019 willy and the snoicd-crawler project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.crawler.types.icd;

import org.weso.snoicd.crawler.types.AbstractTerminologyNode;

/**
 * Instance of IcdNode.java
 * 
 * @author 
 * @version 
 */
public class IcdNode extends AbstractTerminologyNode {

	private ICDVersion version;
	
	/**
	 * Gets the version of the icd node.
	 * 
	 * @return the version of the icd node.
	 */
	public ICDVersion getVersion() { return this.version; }
	
	/**
	 * Sets the version of the icd node.
	 * 
	 * @param version to be set to the icd node.
	 */
	public void setVersion( ICDVersion version ) { this.version = version; }
}
