package com.studentproject.management.service;

import com.studentproject.management.entity.Project;
import com.studentproject.management.entity.StudentGroup;
import com.studentproject.management.entity.User;
import com.studentproject.management.repository.ProjectRepository;
import com.studentproject.management.repository.StudentGroupRepository;
import com.studentproject.management.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final StudentGroupRepository groupRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public GroupService(StudentGroupRepository groupRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public StudentGroup createGroup(StudentGroup group) {
        return groupRepository.save(group);
    }

    public List<StudentGroup> getAllGroups() {
        return groupRepository.findAll();
    }

    public Optional<StudentGroup> getGroupById(Long id) {
        return groupRepository.findById(id);
    }

    public StudentGroup assignProjectToGroup(Long groupId, Long projectId) {
        StudentGroup group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
        group.setProject(project);
        return groupRepository.save(group);
    }

    public StudentGroup addMemberToGroup(Long groupId, Long userId) {
        StudentGroup group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        group.getMembers().add(user);
        return groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}
