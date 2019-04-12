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

import org.weso.snoicd.crawler.types.Relation;

/**
 * Instance of SnomedRelation.java
 * 
 * @author
 * @version
 */
public class SnomedRelation implements Relation {

	private String relationType;
	private String destinationNodeId;
	
	public SnomedRelation(String relationType, String endOfTheRelationNodeId) {
		this.relationType = relationType;
		this.destinationNodeId = endOfTheRelationNodeId;
	}

	/*
	 * (non-Javadoc)
	 * @see org.weso.snoicd.crawler.types.Relation#getRelationType()
	 */
	@Override
	public String getRelationType() {
		return this.relationType;
	}

	/**
	 * Sets the type of the relation.
	 * 
	 * @param relationType to be set.
	 */
	public void setRelationType( String relationType ) {
		this.relationType = relationType;
	}

	/*
	 * (non-Javadoc)
	 * @see org.weso.snoicd.crawler.types.Relation#getNodeToRelation()
	 */
	@Override
	public String getNodeToRelation() {
		return this.destinationNodeId;
	}

	/**
	 * Set the node that will be the end of the relation.
	 * 
	 * @param destinationNodeId represents the node that will be the end of the
	 *            relation.
	 */
	public void setDestinationNodeId( String destinationNodeId ) {
		this.destinationNodeId = destinationNodeId;
	}
}
