package com.avocat.avocat.service;

import com.avocat.avocat.model.Document;
import com.avocat.avocat.repository.DocumentElasticsearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentElasticsearchRepository documentElasticsearchRepository;

    // Method to search documents by client full name containing the given search term
    public List<Document> searchDocumentsByClientFullName(String clientFullName) {
        return documentElasticsearchRepository.findByClientFullNameContaining(clientFullName);
    }
}
