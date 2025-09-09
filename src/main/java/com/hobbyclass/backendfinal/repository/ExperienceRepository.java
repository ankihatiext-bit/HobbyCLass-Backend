package com.hobbyclass.backendfinal.repository;

import com.hobbyclass.backendfinal.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hobbyclass.backendfinal.repository.ExperienceRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
