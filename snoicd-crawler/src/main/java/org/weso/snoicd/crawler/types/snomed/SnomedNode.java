/*
 * This source file is part of the snoicd-crawler open source project.
 *
 * Copyright (c) 2019 willy and the snoicd-crawler project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.crawler.types.snomed;

import java.util.HashSet;
import java.util.Set;

import org.weso.snoicd.crawler.types.AbstractTerminologyNode;
import org.weso.snoicd.crawler.types.Relation;

/**
 * Instance of SnomedNode.java
 * 
 * @author
 * @version
 */
public class SnomedNode extends AbstractTerminologyNode {

	private Set<Relation> relations;

	/**
	 * Gets the set of relations for which this node is the origin.
	 * 
	 * @return the relations for which this node is the origin.
	 */
	public Set<Relation> getRelations() {
		if (this.relations == null) {
			return this.relations = new HashSet<Relation>();
		} else {
			return this.relations;
		}
	}
}
