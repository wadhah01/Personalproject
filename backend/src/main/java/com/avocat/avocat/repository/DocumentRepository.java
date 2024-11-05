package com.avocat.avocat.repository;

import com.avocat.avocat.model.Document;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

@Repository
public interface DocumentRepository extends ElasticsearchRepository<Document, String> {
    
    // Match query for partial matching on clientFullName
    @Query("{\"match\": {\"clientFullName\": \"?0\"}}")
    List<Document> findByClientFullName(String clientFullName);
}