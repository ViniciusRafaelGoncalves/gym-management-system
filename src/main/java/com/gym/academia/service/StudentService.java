package com.gym.academia.service;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.academia.entity.Student;
import com.gym.academia.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;
	
	public List<Student> getStudents(){
		return repository.findAll();
	}
	
	public Student findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
	}
	
	public List<Student> findByName(String name){
		return repository.findByNameContainingIgnoreCase(name);
	}
	
	public Student addStudent(Student obj) {
		return repository.save(obj);
	}
	
	public Student updateStudent(Long id, Student obj) {
		Student entity = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Student not found"));
		setIfNotNull(obj.getName(), entity::setName);
		setIfNotNull(obj.getPhone(), entity::setPhone);
		setIfNotNull(obj.getCpf(), entity::setCpf);
		setIfNotNull(obj.getStartDate(), entity::setStartDate);
		setIfNotNull(obj.getActive(), entity::setActive);
		
		return repository.save(entity);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	public <T> void setIfNotNull(T value, Consumer<T> setter) {
		if(value != null) {
			setter.accept(value);
		}
	}

}
