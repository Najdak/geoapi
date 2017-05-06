package com.geoapi.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class GeoDocument {
    @JsonIgnore
    private String id;
    private String name;
    private float ratio;
    private String asciiName;
    private String alterNames;
    private String searchNames;
    private String latitude;
    private String longitude;
    private String feature_class;
    private String feature_code;
    private String country_code;
    private String cc2;
    private String admin1_code;
    private String admin2_code;
    private String admin3_code;
    private String admin4_code;
    private String population;
    private String elevation;
    private String dem;
    private String timezone;
    private String modification_date;

    public GeoDocument(String id, String name, float ratio, String asciiName, String alterNames, String searchNames, String latitude, String longitude, String feature_class, String feature_code, String country_code, String cc2, String admin1_code, String admin2_code, String admin3_code, String admin4_code, String population, String elevation, String dem, String timezone, String modification_date) {
        this.id = id;
        this.name = name;
        this.ratio = ratio;
        this.asciiName = asciiName;
        this.alterNames = alterNames;
        this.searchNames = searchNames;
        this.latitude = latitude;
        this.longitude = longitude;
        this.feature_class = feature_class;
        this.feature_code = feature_code;
        this.country_code = country_code;
        this.cc2 = cc2;
        this.admin1_code = admin1_code;
        this.admin2_code = admin2_code;
        this.admin3_code = admin3_code;
        this.admin4_code = admin4_code;
        this.population = population;
        this.elevation = elevation;
        this.dem = dem;
        this.timezone = timezone;
        this.modification_date = modification_date;
    }
}
