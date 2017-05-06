package com.geoapi.web.controllers;

import com.geoapi.web.models.GeoDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.geoapi.web.services.GeoService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private GeoService geoService;

    @RequestMapping
    public Set<GeoDocument> api(@RequestParam(name = "q", required = false) String q){

        return geoService.getGeoIndexResult(q);
    }

}
