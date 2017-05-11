package com.geoapi.web.controllers;

import com.geoapi.web.models.GeoDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.geoapi.web.services.GeoService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private GeoService geoService;

    @RequestMapping
    public Object api(@RequestParam(name = "q", defaultValue = "") String q){

        if (!q.trim().isEmpty()){
        List<Object> result = new ArrayList<>();
        List<GeoDocument> geoServiceResult = geoService.getResult(q);
        if (geoServiceResult.isEmpty()){
            Map<String, Boolean> geo = new HashMap<>();
            geo.put("isGeo", false);
            return geo;
        }else {
            Map<String, Boolean> geo = new HashMap<>();
            geo.put("isGeo", true);
            result.add(geo);
            result.add(geoServiceResult);
        }
        return result;
        }
        return "{\"status\": \"error\"}";
    }

}
