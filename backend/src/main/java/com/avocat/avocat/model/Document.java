package com.avocat.avocat.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientFullName;
    private String phoneNumber;
    private String caseName;
    private String caseStatus;
    private LocalDateTime uploadTime;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id")  // This will create a column in File table to reference the Document
    private List<File> files;  // List of files attached to the document

    // Constructors
    public Document() {}

    public Document(String clientFullName, String phoneNumber, String caseName, String caseStatus) {
        this.clientFullName = clientFullName;
        this.phoneNumber = phoneNumber;
        this.caseName = caseName;
        this.caseStatus = caseStatus;
        this.uploadTime = LocalDateTime.now();  // Automatically set upload time
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public void setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
