package com.avocat.avocat.service;

import com.avocat.avocat.model.Document;
import com.avocat.avocat.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    // Save document with uploaded file
    public Document saveDocument(String clientFullName, String caseName, String status, String title, MultipartFile file) throws IOException {
        Document document = new Document();
        document.setClientFullName(clientFullName);
        document.setCaseName(caseName);
        document.setStatus(status);
        document.setTitle(title);
        document.setFileContent(file.getBytes()); // Store file content in byte array

        return documentRepository.save(document);
    }

    // Retrieve documents by client full name
    public List<Document> getDocumentsByClientFullName(String clientFullName) {
        return documentRepository.findByClientFullName(clientFullName);
    }
}
