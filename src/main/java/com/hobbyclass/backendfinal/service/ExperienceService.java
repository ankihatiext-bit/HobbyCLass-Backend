package com.hobbyclass.backendfinal.service;

import com.hobbyclass.backendfinal.entity.Experience;
import com.hobbyclass.backendfinal.entity.Mentor;
import com.hobbyclass.backendfinal.repository.ExperienceRepository;
import com.hobbyclass.backendfinal.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceService {
    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private MentorRepository mentorRepository;

    public List<Experience> getAllExperiences() {
        return experienceRepository.findAll();
    }

    public Optional<Experience> getExperienceById(Long id) {
        return experienceRepository.findById(id);
    }

    public Experience createExperience(Experience experience, Long mentorId) {
        Mentor mentor = mentorRepository.findById(mentorId).orElse(null);
        experience.setMentor(mentor);
        return experienceRepository.save(experience);
    }

    public Experience updateExperience(Long id, Experience experienceDetails, Long mentorId) {
        return experienceRepository.findById(id)
                .map(experience -> {
                    experience.setName(experienceDetails.getName());
                    experience.setDetails(experienceDetails.getDetails());
                    experience.setDate(experienceDetails.getDate());
                    experience.setDuration(experienceDetails.getDuration());
                    Mentor mentor = mentorRepository.findById(mentorId).orElse(null);
                    experience.setMentor(mentor);
                    return experienceRepository.save(experience);
                })
                .orElse(null);
    }

    public void deleteExperience(Long id) {
        experienceRepository.deleteById(id);
    }

    public List<Experience> getExperiencesByMentor(Long mentorId) {
        return experienceRepository.findAll().stream()
                .filter(exp -> exp.getMentor() != null && exp.getMentor().getId().equals(mentorId))
                .toList();
    }
}
