/*
 * This source file is part of the snoicd open source project.
 *
 * Copyright (c) 2018 willy and the snoicd project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.knowledge.graph;

import lombok.Data;

/**
 * Instance of TermNode.java
 * 
 * @author 
 * @version 
 */
@Data
public class TermNode {
	
	// SNOMED CT Code.
	private String snomedCode;
	
	// ICD 9 Code.
	private String icd9Code;
	
	// ICD 10 Code.
	private String icd10Code;
	
	// The description of the term
	private String description;
	
	// List of the children.
	private TermNode[] children;
}
