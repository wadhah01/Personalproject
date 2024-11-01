package com.avocat.avocat.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientFullName;
    private String phoneNumber;
    private String caseName;
    private String caseStatus;
    private String documentTitle;
    private LocalDateTime uploadTime;

    // Add any additional fields if needed

    // Constructors
    public Document() {}

    public Document(String clientFullName, String phoneNumber, String caseName, String caseStatus, String documentTitle) {
        this.clientFullName = clientFullName;
        this.phoneNumber = phoneNumber;
        this.caseName = caseName;
        this.caseStatus = caseStatus;
        this.documentTitle = documentTitle;
        this.uploadTime = LocalDateTime.now();  // Automatically set upload time
    }

    // Getters and Setters
    // (Right-click and use 'Generate' if you are in an IDE like IntelliJ or Eclipse)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public
