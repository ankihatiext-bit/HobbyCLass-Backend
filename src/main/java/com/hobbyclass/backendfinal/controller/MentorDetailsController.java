package com.hobbyclass.backendfinal.controller;

import com.hobbyclass.backendfinal.entity.Mentor;
import com.hobbyclass.backendfinal.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mentors")
public class MentorDetailsController {
    @Autowired
    private MentorService mentorService;

    @GetMapping
    public List<Mentor> getAllMentors() {
        return mentorService.getAllMentors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mentor> getMentorById(@PathVariable Long id) {
        return mentorService.getMentorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mentor createMentor(@RequestBody Mentor mentor) {
        return mentorService.createMentor(mentor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mentor> updateMentor(@PathVariable Long id, @RequestBody Mentor mentorDetails) {
        Mentor updatedMentor = mentorService.updateMentor(id, mentorDetails);
        if (updatedMentor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMentor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMentor(@PathVariable Long id) {
        mentorService.deleteMentor(id);
        return ResponseEntity.noContent().build();
    }
}
