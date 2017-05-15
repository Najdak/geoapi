package com.geoapi.helpers;

import org.junit.Test;

import static org.junit.Assert.*;

public class FuzzyMatchTest {
    @Test
    public void calc() throws Exception {
        assertEquals(4, FuzzyMatch.calc(2));

    }

    @Test
    public void calculateWordsSimilarity() throws Exception {
        assertEquals(50, FuzzyMatch.calculateWordsSimilarity("papa","mama"));
        assertEquals(0, FuzzyMatch.calculateWordsSimilarity("mom","dad"));
        assertEquals(75, FuzzyMatch.calculateWordsSimilarity("size","siza"));
        assertEquals(67, FuzzyMatch.calculateWordsSimilarity("aaaapp","aaap"));
        assertEquals(34, FuzzyMatch.calculateWordsSimilarity("zia","aiz"));
    }



}