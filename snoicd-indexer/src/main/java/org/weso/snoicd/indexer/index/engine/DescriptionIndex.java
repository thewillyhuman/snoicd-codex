/*
 * This source file is part of the snoicd-indexer open source project.
 *
 * Copyright (c) 2019 willy and the snoicd-indexer project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 *
 */
package org.weso.snoicd.indexer.index.engine;

import io.thewilly.bigtable.BigTable;
import io.thewilly.bigtable.index.IndexEngine;
import org.weso.snoicd.indexer.utils.StringNormalizator;

import java.util.HashSet;

/**
 * Instance of DescriptionIndex.java
 *
 * @author
 */
public class DescriptionIndex implements IndexEngine {

    private static int MINIMUM_INDEX_WORD_LENGHT = 3;

    /* (non-Javadoc)
     * @see io.thewilly.bigtable.index.IndexEngine#index(io.thewilly.bigtable.BigTable, java.lang.Object, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    //@Override
    public <K, V> boolean index(BigTable<K, V> table, K key, V value) {
        String normalizedKey = StringNormalizator.normalize(key.toString());
        String[] keys = normalizedKey.split(" ");

        for (String ikey : keys) {
            if (ikey.length() >= MINIMUM_INDEX_WORD_LENGHT) {
                if (table.getMemoryMap().containsKey(ikey)) {
                    table.getMemoryMap().get(ikey).add(value);
                } else {
                    table.getMemoryMap().put((K) ikey, new HashSet<V>());
                    table.getMemoryMap().get(ikey).add(value);
                }
            }
        }
        return true;
    }

}
