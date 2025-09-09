package com.hobbyclass.backendfinal.service;

import com.hobbyclass.backendfinal.entity.Mentor;
import com.hobbyclass.backendfinal.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MentorService {
    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
    }

    public Optional<Mentor> getMentorById(Long id) {
        return mentorRepository.findById(id);
    }

    public Mentor createMentor(Mentor mentor) {
        mentor.setPassword(passwordEncoder.encode(mentor.getPassword()));
        return mentorRepository.save(mentor);
    }

    public Mentor updateMentor(Long id, Mentor mentorDetails) {
        return mentorRepository.findById(id)
                .map(mentor -> {
                    mentor.setName(mentorDetails.getName());
                    mentor.setExpertise(mentorDetails.getExpertise());
                    mentor.setBio(mentorDetails.getBio());
                    mentor.setEmail(mentorDetails.getEmail());
                    mentor.setPhone(mentorDetails.getPhone());
                    if (mentorDetails.getPassword() != null) {
                        mentor.setPassword(passwordEncoder.encode(mentorDetails.getPassword()));
                    }
                    return mentorRepository.save(mentor);
                })
                .orElse(null);
    }

    public void deleteMentor(Long id) {
        mentorRepository.deleteById(id);
    }
}
