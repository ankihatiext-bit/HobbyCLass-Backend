package com.hobbyclass.backendfinal.controller;

import com.hobbyclass.backendfinal.entity.Experience;
import com.hobbyclass.backendfinal.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiences")
@Validated
public class ExperienceLiveClassesController {
    @Autowired
    private ExperienceService experienceService;

    @GetMapping
    public List<Experience> getAllExperiences() {
        return experienceService.getAllExperiences();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experience> getExperienceById(@PathVariable Long id) {
        return experienceService.getExperienceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Experience createExperience(@RequestBody Experience experience) {
        if (experience.getMentor() != null) {
            return experienceService.createExperience(experience, experience.getMentor().getId());
        }
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Experience> updateExperience(@PathVariable Long id, @RequestBody Experience experienceDetails, @RequestParam Long mentorId) {
        Experience updatedExperience = experienceService.updateExperience(id, experienceDetails, mentorId);
        if (updatedExperience == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedExperience);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/mentor/{mentorId}")
    public List<Experience> getExperiencesByMentor(@PathVariable Long mentorId) {
        return experienceService.getExperiencesByMentor(mentorId);
    }
}
