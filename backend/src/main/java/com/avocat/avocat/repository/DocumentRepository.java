package com.avocat.avocat.repository;

import com.avocat.avocat.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    // Custom query to find documents by client's full name
    List<Document> findByClientFullNameContaining(String clientFullName);
}
