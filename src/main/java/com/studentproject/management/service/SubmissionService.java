package com.studentproject.management.service;

import com.studentproject.management.entity.StudentGroup;
import com.studentproject.management.entity.Submission;
import com.studentproject.management.repository.StudentGroupRepository;
import com.studentproject.management.repository.SubmissionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final StudentGroupRepository groupRepository;

    public SubmissionService(SubmissionRepository submissionRepository, StudentGroupRepository groupRepository) {
        this.submissionRepository = submissionRepository;
        this.groupRepository = groupRepository;
    }

    public Submission submitProject(Submission submission, Long groupId) {
        StudentGroup group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        submission.setStudentGroup(group);
        submission.setSubmissionDate(LocalDateTime.now());
        return submissionRepository.save(submission);
    }

    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public Optional<Submission> getSubmissionById(Long id) {
        return submissionRepository.findById(id);
    }

    public void deleteSubmission(Long id) {
        submissionRepository.deleteById(id);
    }
}
