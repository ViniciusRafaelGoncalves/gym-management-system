package com.gym.academia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.academia.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	List<Student> findByNameContainingIgnoreCase(String name);
}
