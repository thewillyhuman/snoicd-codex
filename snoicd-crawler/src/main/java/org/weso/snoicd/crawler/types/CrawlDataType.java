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
 */
public interface CrawlDataType {

    /**
     * Gets the unique code that identifies a concept on its terminology. It
     * serves as a unique key for the terminology.
     *
     * @return the code that identifies the concept in the terminology.
     */
    String getConceptID();

    /**
     * Gets the terminology name to which the concept belongs.
     *
     * @return the terminology name to which the concept belongs.
     */
    String getTerminologyName();

    /**
     * Gets the set of possible descriptions that can be applied to the concept.
     *
     * @return the set of possible descriptions that can be applied to the concept.
     */
    Set<String> getDescriptions();

    /**
     * Gets the set of possible translations ids for one node on to other terminologies.
     *
     * @return
     */
    Set<String> getTranslationNodesIds();
}
