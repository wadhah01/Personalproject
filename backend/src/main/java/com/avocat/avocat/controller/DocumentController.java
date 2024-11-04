package com.avocat.avocat.controller;

import com.avocat.avocat.model.Document;
import com.avocat.avocat.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    // Endpoint to upload a document
    @PostMapping("/upload")
    public ResponseEntity<Document> uploadDocument(
            @RequestParam String clientFullName,
            @RequestParam String caseName,
            @RequestParam String status,
            @RequestParam String title,
            @RequestParam("file") MultipartFile file) throws IOException {

        Document document = documentService.saveDocument(clientFullName, caseName, status, title, file);
        return ResponseEntity.ok(document);
    }

    // Endpoint to retrieve documents by client's full name
    @GetMapping("/search")
    public ResponseEntity<List<Document>> getDocumentsByClientFullName(@RequestParam String clientFullName) {
        List<Document> documents = documentService.getDocumentsByClientFullName(clientFullName);
        return ResponseEntity.ok(documents);
    }
}
