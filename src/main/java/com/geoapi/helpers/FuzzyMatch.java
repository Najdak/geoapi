package com.geoapi.helpers;


import org.apache.commons.lang3.StringUtils;


public class FuzzyMatch {

    public static int calculateLevensteinDistance(String s1, String s2) {
        int distance = StringUtils.getLevenshteinDistance(s1, s2);
        double ratio = ((double) distance) / (Math.max(s1.length(), s2.length()));
        return 100 - new Double(ratio * 100).intValue();
    }

}
