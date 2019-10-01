package org.weso.snoicd.search.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.Normalizer;

@Slf4j
public class StringNormalizator {

    public static String normalize(String s) {

        // Removing '-' and replacing it by a white space.
        String ret = s.replaceAll("-", " ");

        // Normalizing special characters to simple ascii ones.
        ret = Normalizer.normalize(ret, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        // Always to lower case as a convention.
        ret = ret.toLowerCase();

        log.info("Normalizing from: " + s + ". To: " + ret);

        return ret;
    }
}
