/*
 * This source file is part of the snoicd-indexer open source project.
 *
 * Copyright (c) 2019 willy and the snoicd-indexer project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 *
 */
package org.weso.snoicd.indexer.utils;

import java.text.Normalizer;

/**
 * Instance of StringNormalizator.java
 */
public class StringNormalizator {

    /**
     * For a given String will remove unsafe characters and transform it to lowercase.
     *
     * @param stringToNormalize is the string to normalize.
     * @return the string normalized.
     */
    public static String normalize(String stringToNormalize) {

        // Removing '-' and replacing it by a white space.
        String ret = stringToNormalize.replaceAll("-", " ");
        ret = ret.replaceAll("/", " ");
        ret = ret.replaceAll("[.,]", "");
        ret = ret.replaceAll("[()]", "");
        ret = ret.replaceAll("[\\[\\]]", "");

        //Normalizing special characters to simple ascii ones.
        ret = Normalizer.normalize(ret, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        // Always to lower case as a convention.
        ret = ret.toLowerCase();

        return ret;
    }

}
