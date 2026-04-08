package com.studentproject.management.controller;

import com.studentproject.management.entity.StudentGroup;
import com.studentproject.management.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<StudentGroup> createGroup(@RequestBody StudentGroup group) {
        return ResponseEntity.ok(groupService.createGroup(group));
    }

    @GetMapping
    public ResponseEntity<List<StudentGroup>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentGroup> getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{groupId}/project/{projectId}")
    public ResponseEntity<StudentGroup> assignProjectToGroup(@PathVariable Long groupId, @PathVariable Long projectId) {
        return ResponseEntity.ok(groupService.assignProjectToGroup(groupId, projectId));
    }

    @PostMapping("/{groupId}/member/{userId}")
    public ResponseEntity<StudentGroup> addMemberToGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        return ResponseEntity.ok(groupService.addMemberToGroup(groupId, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.ok().build();
    }
}
