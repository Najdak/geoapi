package com.geoapi.web.services;

import com.geoapi.web.models.GeoDocument;
import com.geoapi.web.persistence.LuceneRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GeoServiceTest {

    @Mock
    private LuceneRepository luceneRepository;

    @InjectMocks
    private GeoService sut;


    @Test
    public void getResult() throws Exception {
        //prepare
        when(sut.getGeoIndexResult("Chisinau")).then((Answer<?>) new HashMap<String, String>());
        //testing
        Set<GeoDocument> chisinau = sut.getGeoIndexResult("Chisinau");
        Set<GeoDocument> result = sut.getGeoIndexResult("san diego");
        //validate
        verify(sut).getGeoIndexResult("Chisinau");

    }

}