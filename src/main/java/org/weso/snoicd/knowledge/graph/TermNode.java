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

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Instance of TermNode.java
 * 
 * @author 
 * @version 
 */
@Data
@Document(collection = "third-grammar")
public class TermNode {
	
	@Id
	@JsonIgnore
	private ObjectId _id;
	
	// SNOMED CT Code.
	@Indexed
	private String snomedCode;
	
	// ICD 9 Code.
	@Indexed
	private String icd9Code;
	
	// ICD 10 Code.
	@Indexed
	private String icd10Code;
	
	// The description of the term
	private Description[] descriptions;
	
	// List of the children.
	//@DBRef for keeping just the reference. But then we will have to resolve the terms.
	private ChildNode[] children;
	
	private String[] parents;
}
