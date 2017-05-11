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
//    @Autowired
//    private GeoService geoService;
//
//    @Autowired
//    private LuceneRepository luceneRepository;
    @Before
    public void setUp() throws Exception {
        RestController restController = new RestController();
//        ReflectionTestUtils.setField(geoService, "luceneRepository", luceneRepository);
//        ReflectionTestUtils.setField(restController, "geoService", geoService);
        this.restAdverseGroupMockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void api() throws Exception {
        restAdverseGroupMockMvc.perform(get("/api").param("q", "balti"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isGeo", Is.is(true)));
    }

}