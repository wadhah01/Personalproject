package com.avocat.avocat.service;

import com.avocat.avocat.model.Document;
import com.avocat.avocat.model.File;
import com.avocat.avocat.repository.DocumentRepository;
import com.avocat.avocat.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private DocumentRepository documentRepository;  // Elasticsearch repo

    @Autowired
    private FileRepository fileRepository;  // Traditional SQL repo (for file metadata)

    // Method to handle file upload
    public Document uploadFiles(Long documentId, List<MultipartFile> files) throws IOException {
        // Find the document by ID from Elasticsearch
        Document document = documentRepository.findById(String.valueOf(documentId)).orElseThrow(() -> new RuntimeException("Document not found"));

        List<File> fileList = new ArrayList<>();
        
        for (MultipartFile file : files) {
            // Convert MultipartFile to File entity
            File fileEntity = new File();
            fileEntity.setFileName(file.getOriginalFilename());
            fileEntity.setFileType(file.getContentType());
            fileEntity.setData(file.getBytes());

            // Save file to the traditional database (SQL)
            fileRepository.save(fileEntity);

            fileList.add(fileEntity);
        }

        // Set the list of files to the document and save the document to Elasticsearch
        document.setFiles(fileList);
        documentRepository.save(document);  // Save to Elasticsearch
        
        return document;
    }
}
