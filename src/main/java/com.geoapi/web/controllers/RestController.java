package com.geoapi.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.geoapi.web.services.GeoService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private GeoService geoService;

    @RequestMapping(value = "/{q}")
    public String api(@PathVariable (name = "q", required = false) String q){

        return geoService.met(q);
    }

}
