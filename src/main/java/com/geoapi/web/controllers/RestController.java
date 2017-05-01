package com.geoapi.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.geoapi.web.services.GeoService;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private GeoService geoService;

    @RequestMapping
    public String api(@RequestParam(name = "q", required = false) String q){

        return geoService.getResult(q);
    }

}
