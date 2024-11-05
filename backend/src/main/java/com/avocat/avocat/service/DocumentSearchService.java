package com.avocat.avocat.service;

import com.avocat.avocat.model.Document;
import com.avocat.avocat.repository.DocumentElasticsearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentSearchService {

    @Autowired
    private DocumentElasticsearchRepository documentElasticsearchRepository;

    public List<Document> searchByClientFullName(String clientFullName) {
        return documentElasticsearchRepository.findByClientFullName(clientFullName);
    }

    // Additional search methods (e.g., by title or status) can be added here if needed
}
