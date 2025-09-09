package com.hobbyclass.backendfinal.service;

import com.hobbyclass.backendfinal.entity.HobbyClass;
import com.hobbyclass.backendfinal.entity.Mentor;
import com.hobbyclass.backendfinal.repository.HobbyClassRepository;
import com.hobbyclass.backendfinal.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HobbyClassService {
    @Autowired
    private HobbyClassRepository hobbyClassRepository;

    @Autowired
    private MentorRepository mentorRepository;

    public List<HobbyClass> getAllClasses() {
        return hobbyClassRepository.findAll();
    }

    public Optional<HobbyClass> getClassById(Long id) {
        return hobbyClassRepository.findById(id);
    }

    public HobbyClass createClass(HobbyClass hobbyClass, Long mentorId) {
        Mentor mentor = mentorRepository.findById(mentorId).orElse(null);
        hobbyClass.setMentor(mentor);
        return (HobbyClass) hobbyClassRepository.save(hobbyClass);
    }

    public HobbyClass updateClass(Long id, HobbyClass classDetails, Long mentorId) {
        return hobbyClassRepository.findById(id)
                .map(hobbyClass -> {
                    hobbyClass.setTitle(classDetails.getTitle());
                    hobbyClass.setDescription(classDetails.getDescription());
                    hobbyClass.setCategory(classDetails.getCategory());
                    hobbyClass.setSchedule(classDetails.getSchedule());
                    hobbyClass.setPrice(classDetails.getPrice());
                    Mentor mentor = mentorRepository.findById(mentorId).orElse(null);
                    hobbyClass.setMentor(mentor);
                    return hobbyClassRepository.save(hobbyClass);
                })
                .orElse(null);
    }

    public void deleteClass(Long id) {
        hobbyClassRepository.deleteById(id);
    }

    public List<HobbyClass> getClassesByMentor(Long mentorId) {
        return hobbyClassRepository.findAll().stream()
                .filter(hc -> hc.getMentor() != null && hc.getMentor().getId().equals(mentorId))
                .toList();
    }
}
