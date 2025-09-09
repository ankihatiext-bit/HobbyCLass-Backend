package com.hobbyclass.backendfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.hobbyclass.backendfinal.entity.Mentor;
import com.hobbyclass.backendfinal.entity.HobbyClass;
import com.hobbyclass.backendfinal.entity.Experience;
import com.hobbyclass.backendfinal.repository.MentorRepository;
import com.hobbyclass.backendfinal.repository.HobbyClassRepository;
import com.hobbyclass.backendfinal.repository.ExperienceRepository;
import java.time.LocalDate;

@SpringBootApplication
public class BackendfinalApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendfinalApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(MentorRepository mentorRepo, HobbyClassRepository classRepo, ExperienceRepository expRepo) {
        return args -> {
            Mentor mentor1 = new Mentor();
            mentor1.setId(1L);
            mentor1.setName("Alice");
            mentor1.setExpertise("Pottery");
            mentor1.setBio("Expert in pottery with 10 years experience.");
            mentor1.setEmail("alice@example.com");
            mentor1.setPhone("1234567890");
            mentor1.setUsername("alice_pottery");
            mentor1.setPassword("password123");
            mentorRepo.save(mentor1);

            Mentor mentor2 = new Mentor();
            mentor2.setId(2L);
            mentor2.setName("Bob");
            mentor2.setExpertise("Painting");
            mentor2.setBio("Watercolor artist and teacher.");
            mentor2.setEmail("bob@example.com");
            mentor2.setPhone("0987654321");
            mentor2.setUsername("bob_painting");
            mentor2.setPassword("password123");
            mentorRepo.save(mentor2);

            HobbyClass class1 = new HobbyClass();
            class1.setId(1L);
            class1.setTitle("Beginner Pottery");
            class1.setDescription("Learn the basics of pottery.");
            class1.setCategory("Art");
            class1.setSchedule("Sat 10am");
            class1.setPrice(500.0);
            class1.setMentor(mentor1);
            classRepo.save(class1);

            HobbyClass class2 = new HobbyClass();
            class2.setId(2L);
            class2.setTitle("Watercolor Painting");
            class2.setDescription("Explore watercolor techniques.");
            class2.setCategory("Art");
            class2.setSchedule("Sun 2pm");
            class2.setPrice(600.0);
            class2.setMentor(mentor2);
            classRepo.save(class2);

            Experience exp1 = new Experience();
            exp1.setId(1L);
            exp1.setName("Live Pottery Demo");
            exp1.setDetails("Watch Alice create a vase live.");
            exp1.setDate(LocalDate.now().plusDays(3));
            exp1.setDuration(90);
            exp1.setMentor(mentor1);
            expRepo.save(exp1);

            Experience exp2 = new Experience();
            exp2.setId(2L);
            exp2.setName("Painting Masterclass");
            exp2.setDetails("Bob teaches advanced watercolor.");
            exp2.setDate(LocalDate.now().plusDays(7));
            exp2.setDuration(120);
            exp2.setMentor(mentor2);
            expRepo.save(exp2);
        };
    }
}
