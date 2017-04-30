package com.geoapi.web.services;

import com.geoapi.helpers.FuzzyMatch;
import com.geoapi.web.persistence.LuceneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GeoService {

    @Autowired
    private LuceneRepository luceneRepository;

    public String met(String s){

        Map<String, Object> stringObjectMap = luceneRepository.getGeoIndexByQuery(s);

        if (!stringObjectMap.isEmpty()) {

            Integer totalHits = (Integer) stringObjectMap.get("totalHits");
            Float top1Score = (Float) stringObjectMap.get("top1Score");
            Float top10Score = (Float) stringObjectMap.get("top10Score");
            Float top3Score = (Float) stringObjectMap.get("top3Score");
            List<Map<String, Object>> geoRes = (List<Map<String, Object>>) stringObjectMap.get("documents");
            String qq = s.replaceAll("[0-9]|[A-Z]{2}", "").trim().toLowerCase();

            Set<String> querySet = new TreeSet<>();
            StringTokenizer stringTokenizer = new StringTokenizer(qq.toLowerCase());
            while (stringTokenizer.hasMoreTokens()) {
                querySet.add(stringTokenizer.nextToken());
            }
            Map<Float, String> geoIndexHits = new TreeMap<>();
            for (Map<String, Object> geoRe : geoRes) {
                Set<String> hits = new LinkedHashSet<>();
                Set<String> geoNames = new TreeSet<>();
                StringTokenizer stringTokenizer1 = new StringTokenizer(geoRe.get("geoName").toString());
                while (stringTokenizer1.hasMoreTokens()) {
                    geoNames.add(stringTokenizer1.nextToken());
                }
                for (String geoName : geoNames) {
                    for (String queryToken : querySet) {
                        int redDist = FuzzyMatch.calculateLevensteinDistance(queryToken.toLowerCase().trim(), geoName.toLowerCase().trim());
                        if (redDist <= 1) {
                            hits.add(queryToken.toLowerCase().trim());
                            break;
                        }
                    }
                }
                float line = Math.max((float) querySet.size(), (float) geoNames.size());
                float geoToken = 100 * (float) hits.size() / line;
                if (hits.size() > 0) {
                    geoIndexHits.put(geoToken, geoNames.toString());
                }
            }
            float editDistance = Collections.max(geoIndexHits.keySet());
            String livenstainName = "";
            if (!geoIndexHits.isEmpty()) {
                livenstainName = geoIndexHits.get(editDistance);
                livenstainName = livenstainName.replaceAll("[\\[|\\]]", "");
            }
            if (
                    (totalHits > 1000 && Float.compare(top10Score, (float) 2) == 1) ||
                            Float.compare(top1Score, (float) 10) == 1 ||
                            Float.compare(top10Score, (float) 7) == 1 ||
                            (Float.compare(top3Score, (float) 5) == 1 && Float.compare(editDistance, (float) 70) == 1 && totalHits > 50) ||
                            Float.compare(editDistance, (float) 90) == 1
                    ) {
                System.out.println("its geo");
            }
        }
//        } else LOGGER.info("[MAP] Qery: [" + newQuery + "] not in the geo-index");









        return luceneRepository.met(s);
    }

}
