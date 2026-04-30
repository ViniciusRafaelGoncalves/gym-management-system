package com.gym.academia.configuration;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gym.academia.entity.Student;
import com.gym.academia.repository.StudentRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Student s1 = new Student("Lindinha", "975149889", "31295678902", LocalDate.now(), true);
		Student s2 = new Student("Docinho", "975176889", "31291348902", LocalDate.now(), true);
		Student s3 = new Student("Florzinha", "971379889", "36495678902", LocalDate.of(2026, 1, 27), true);
		Student s4 = new Student("Linlin", "975147845", "31295145902", LocalDate.now(), true);
		
		studentRepository.saveAll(Arrays.asList(s1,s2,s3,s4));
		
	}
	
	
}
