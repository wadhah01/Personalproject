package com.avocat.avocat.repository;

import com.avocat.avocat.model.Document;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentElasticsearchRepository extends ElasticsearchRepository<Document, String> {

    // Search for a document where clientFullName contains the search term anywhere (partial match)
    List<Document> findByClientFullNameContaining(String clientFullName);
}
