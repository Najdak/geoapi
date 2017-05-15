package com.geoapi.web.controllers;

import com.geoapi.web.persistence.LuceneRepository;
import com.geoapi.web.services.GeoService;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RestControllerTest {
    private MockMvc restAdverseGroupMockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;
    @Before
    public void setUp() throws Exception {
        this.restAdverseGroupMockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void balti() throws Exception {
        restAdverseGroupMockMvc.perform(get("/api").param("q", "balti"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isGeo", Is.is(true)));
    }
        @Test
    public void marseilleFrance() throws Exception {
        restAdverseGroupMockMvc.perform(get("/api").param("q", "marseille France"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isGeo", Is.is(true)));
    }
        @Test
    public void universitateaDe() throws Exception {
        restAdverseGroupMockMvc.perform(get("/api").param("q", "univerisitatea de"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isGeo", Is.is(false)));
    }
        @Test
    public void boston() throws Exception {
        restAdverseGroupMockMvc.perform(get("/api").param("q", "Boston"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isGeo", Is.is(true)));
    }
        @Test
    public void newYork() throws Exception {
        restAdverseGroupMockMvc.perform(get("/api").param("q", "new york"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isGeo", Is.is(true)));
    }
        @Test
    public void london() throws Exception {
        restAdverseGroupMockMvc.perform(get("/api").param("q", "London"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isGeo", Is.is(true)));
    }
        @Test
    public void chisinau() throws Exception {
        restAdverseGroupMockMvc.perform(get("/api").param("q", "Chisinau"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isGeo", Is.is(true)));
    }
        @Test
    public void moldova() throws Exception {
        restAdverseGroupMockMvc.perform(get("/api").param("q", "MOLDOVA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isGeo", Is.is(true)));
    }
        @Test
    public void sanDiego() throws Exception {
        restAdverseGroupMockMvc.perform(get("/api").param("q", "san diego"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isGeo", Is.is(true)));
    }
        @Test
    public void santoDoMingo() throws Exception {
        restAdverseGroupMockMvc.perform(get("/api").param("q", "santo do mingo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isGeo", Is.is(true)));
    }
        @Test
    public void iablona() throws Exception {
        restAdverseGroupMockMvc.perform(get("/api").param("q", "iablona"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isGeo", Is.is(true)));
    }

}