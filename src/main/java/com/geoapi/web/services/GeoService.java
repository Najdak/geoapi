package com.geoapi.web.services;

import com.geoapi.helpers.FuzzyMatch;
import com.geoapi.web.models.GeoDocument;
import com.geoapi.web.persistence.LuceneRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GeoService {

    @Autowired
    private LuceneRepository luceneRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public Set<GeoDocument> getGeoIndexResult(String query){

        Map<String, Object> geoIndexResultMap = getGeoIndexResultMap(query);

        if (!geoIndexResultMap.isEmpty()){
            if (isGeoQueryCheck(geoIndexResultMap)){
                return (Set<GeoDocument>) geoIndexResultMap.get("documents");
            }
        }

        return new HashSet<>();
    }

    private Map<String, Object> getGeoIndexResultMap(String query) {
        Map<String, Object> geoIndexResultMap = luceneRepository.getGeoIndexByQuery(StringUtils.stripAccents(query.trim()));
        if (!geoIndexResultMap.isEmpty()) {
            geoIndexResultMap.put("query", query);
            Set<GeoDocument> geoRes = (Set<GeoDocument>) geoIndexResultMap.get("documents");
            String qq = query.replaceAll("[0-9]|[A-Z]{2}", "").trim().toLowerCase();

            List<String> querySet = Arrays.asList(qq.split("\\s"));

            Map<Float, String> geoIndexHits = new TreeMap<>();

            for (GeoDocument geoRe : geoRes) {
                Set<String> hits = new LinkedHashSet<>();
                Set<String> geoNames = new TreeSet<>();
                StringTokenizer stringTokenizer1 = new StringTokenizer(geoRe.getName().toString());
                while (stringTokenizer1.hasMoreTokens()) {
                    geoNames.add(stringTokenizer1.nextToken());
                }

                for (String geoName : geoNames) {
                    for (String queryToken : querySet) {
                        int redDist = FuzzyMatch.calculateLevenshteinDistance(queryToken.toLowerCase().trim(), geoName.toLowerCase().trim());
                        if (redDist >= 85) {
                            hits.add(queryToken.toLowerCase().trim());
                            break;
                        }
                    }
                }

                float line = Math.max((float) querySet.size(), (float) geoNames.size());
                float geoToken = 100 * (float) hits.size() / line;
                geoIndexHits.put(geoToken, geoNames.toString());
            }

            float editDistance = 0;
            editDistance = Collections.max(geoIndexHits.keySet());
            geoIndexResultMap.put("editDistance", editDistance);
            return geoIndexResultMap;
        } else LOGGER.info("[MAP] Qery: [" + query + "] not in the geo-index");
        return new HashMap<>();
    }

    private boolean isGeoQueryCheck(Map<String, Object> geoIndexResultMap) {
        String query = (String) geoIndexResultMap.get("query");
        Float editDistance = (Float) geoIndexResultMap.get("editDistance");
        Integer totalHits = (Integer) geoIndexResultMap.get("totalHits");
        Float top1Score = (Float) geoIndexResultMap.get("top1Score");
        Float top10Score = (Float) geoIndexResultMap.get("top10Score");
        Float top3Score = (Float) geoIndexResultMap.get("top3Score");
        if (
                (totalHits > 1000 && Float.compare(top10Score, (float) 2) == 1) ||
                        Float.compare(top1Score, (float) 10) == 1 ||
                        Float.compare(top10Score, (float) 7) == 1 ||
                        (Float.compare(top3Score, (float) 5) == 1 && Float.compare(editDistance, (float) 70) == 1 && totalHits > 50) ||
                        (Float.compare(editDistance, (float) 90) == 1) ||
                        (totalHits > 30000 && Float.compare(editDistance, 50f) > -1)
                ) {
            LOGGER.info("[MAP] Qery: [" + query + "] IS geo query");
            return true;
        }
        LOGGER.info("[MAP] Qery: [" + query + "] is NOT geo query");
        return false;
    }

}
