package com.geoapi.web.persistence;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import org.apache.lucene.search.Query;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class LuceneRepository {

    @Value("${geo_index_dir}")
    private String geo_index_dir;
    
    public Map<String, Object> getGeoIndexByQuery(String geoName) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> resultDocs = new LinkedList<>();

        try (Directory directory = new SimpleFSDirectory(new File(geo_index_dir));
             DirectoryReader ireader = DirectoryReader.open(directory)) {
            IndexSearcher indexSearcher = new IndexSearcher(ireader);

            Analyzer analyzer = new StandardAnalyzer();
            QueryParser parser = new QueryParser("name", analyzer);
            Query query = parser.parse(geoName);
            TopDocs topDocs = indexSearcher.search(query, 10);
            if (topDocs.totalHits > 0) {
//                LOGGER.info("[MAP] Retrieving results from GEO-INDEX in {} ms", System.currentTimeMillis() - lukeQueryToGeoIndex);
                Integer totalHits = topDocs.totalHits;
                ScoreDoc[] scoreDocs = topDocs.scoreDocs;
                result.put("totalHits", totalHits);
                result.put("top1Score", scoreDocs[0].score);
                float top10Score;
                float top3Score = 0;
                float sum = 0;
                for (int i = 0; i < scoreDocs.length; i++) {
                    if (i == 3) {
                        top3Score = sum / 3;
                    }
                    sum += scoreDocs[i].score;
                }
                top10Score = sum / 10F;
                result.put("top10Score", top10Score);
                result.put("top3Score", top3Score);

                for (ScoreDoc csoreDoc : scoreDocs) {
                    Document doc = indexSearcher.doc(csoreDoc.doc);
                    float score = csoreDoc.score;
                    Map<String, Object> element = new HashMap<>();
                    element.put("geoName", doc.get("name"));
                    element.put("score", score);
                    resultDocs.add(element);
                }

                result.put("documents", resultDocs);
            }
        } catch (ParseException | IOException e) {
//            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

}
