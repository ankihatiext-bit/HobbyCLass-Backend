package com.hobbyclass.backendfinal.repository;

import com.hobbyclass.backendfinal.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    Optional<Mentor> findByUsername(String username);
}
