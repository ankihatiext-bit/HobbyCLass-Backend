package com.hobbyclass.backendfinal.controller;

import com.hobbyclass.backendfinal.entity.HobbyClass;
import com.hobbyclass.backendfinal.service.HobbyClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@Validated
public class ExploreController {
    @Autowired
    private HobbyClassService hobbyClassService;

    @GetMapping
    public List<HobbyClass> getAllClasses() {
        return hobbyClassService.getAllClasses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HobbyClass> getClassById(@PathVariable Long id) {
        return hobbyClassService.getClassById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HobbyClass> createClass(@RequestBody HobbyClass hobbyClass) {
        if (hobbyClass.getMentor() != null) {
            HobbyClass createdClass = hobbyClassService.createClass(hobbyClass, hobbyClass.getMentor().getId());
            if (createdClass != null) {
                return ResponseEntity.ok(createdClass);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HobbyClass> updateClass(@PathVariable Long id, @RequestBody HobbyClass classDetails) {
        if (classDetails.getMentor() != null) {
            HobbyClass updatedClass = hobbyClassService.updateClass(id, classDetails, classDetails.getMentor().getId());
            if (updatedClass == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedClass);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        hobbyClassService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/mentor/{mentorId}")
    public List<HobbyClass> getClassesByMentor(@PathVariable Long mentorId) {
        return hobbyClassService.getClassesByMentor(mentorId);
    }
}
