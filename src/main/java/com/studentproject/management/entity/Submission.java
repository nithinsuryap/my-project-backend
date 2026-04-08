package com.studentproject.management.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contentUrl; // Could be a link to a file, GitHub repo, etc.

    @Column(nullable = false)
    private LocalDateTime submissionDate;

    @OneToOne
    @JoinColumn(name = "group_id", nullable = false, unique = true)
    private StudentGroup studentGroup;

    public Submission() {
    }

    public Submission(Long id, String contentUrl, LocalDateTime submissionDate, StudentGroup studentGroup) {
        this.id = id;
        this.contentUrl = contentUrl;
        this.submissionDate = submissionDate;
        this.studentGroup = studentGroup;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getContentUrl() { return contentUrl; }
    public void setContentUrl(String contentUrl) { this.contentUrl = contentUrl; }
    public LocalDateTime getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(LocalDateTime submissionDate) { this.submissionDate = submissionDate; }
    public StudentGroup getStudentGroup() { return studentGroup; }
    public void setStudentGroup(StudentGroup studentGroup) { this.studentGroup = studentGroup; }
}
