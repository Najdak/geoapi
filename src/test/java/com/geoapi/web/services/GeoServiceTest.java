package com.geoapi.web.services;

import com.geoapi.web.models.GeoDocument;
import com.geoapi.web.persistence.LuceneRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GeoServiceTest {


    @Mock
    private LuceneRepository luceneRepository;

    @InjectMocks
    private GeoService sut;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getResult() throws Exception {
        //prepare
//        when(sut.getGeoIndexResult("Chisinau")).then(anyCollectionOf());
        //testing
        List<GeoDocument> chisinau = sut.getGeoIndexResult("Chisinau");
        //validate
//        verify(sut, never()).getGeoIndexResult(any());

    }



    @Test
    public void getGeoIndexResult() throws Exception {

    }

}