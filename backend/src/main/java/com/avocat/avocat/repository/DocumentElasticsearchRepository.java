package com.avocat.avocat.repository;

import com.avocat.avocat.model.Document;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentElasticsearchRepository extends ElasticsearchRepository<Document, String> {
    List<Document> findByClientFullName(String clientFullName);
}
