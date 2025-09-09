package com.hobbyclass.backendfinal.service;

import com.hobbyclass.backendfinal.entity.AppUser;
import com.hobbyclass.backendfinal.entity.Mentor;
import com.hobbyclass.backendfinal.repository.UserRepository;
import com.hobbyclass.backendfinal.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Optional<AppUser> authenticateUser(String username, String password) {
        Optional<AppUser> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            return userOpt;
        }
        return Optional.empty();
    }

    public Optional<Mentor> authenticateMentor(String username, String password) {
        Optional<Mentor> mentorOpt = mentorRepository.findByUsername(username);
        if (mentorOpt.isPresent() && passwordEncoder.matches(password, mentorOpt.get().getPassword())) {
            return mentorOpt;
        }
        return Optional.empty();
    }
}
