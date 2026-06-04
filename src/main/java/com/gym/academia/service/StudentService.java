package com.gym.academia.service;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.academia.dto.StudentDTO;
import com.gym.academia.entity.Student;
import com.gym.academia.repository.StudentRepository;

@Service
public class StudentService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private StudentRepository repository;

	public List<StudentDTO> getStudents() {
		return repository.findAll()
				.stream()
				.map(this::toDTO)
				.toList();
	}

	public Student findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
	}

	public List<StudentDTO> findByName(String name) {
		return repository.findByNameContainingIgnoreCase(name)
				.stream()
				.map(this::toDTO)
				.toList();
	}

	public StudentDTO addStudent(StudentDTO obj) {
		Student student = fromDTO(obj);
		student.setPhone(normalize(student.getPhone()));
		student.setCpf(normalize(student.getCpf()));
		Student saved = repository.save(student);
		return toDTO(saved);
	}

	public StudentDTO updateStudent(Long id, StudentDTO obj) {
		Student entity = findById(id);
		setIfNotNull(obj.name(), entity::setName);
		setIfNotNull(normalize(obj.phone()), entity::setPhone);
		setIfNotNull(normalize(obj.cpf()), entity::setCpf);
		setIfNotNull(obj.startDate(), entity::setStartDate);
		setIfNotNull(obj.active(), entity::setActive);

		Student updated = repository.save(entity);
		return toDTO(updated);
	}
	
	public StudentDTO getStudentById(Long id) {
	    return toDTO(findById(id));
	}

	private String normalize(String value) {
		if (value == null)
			return null;
		return value.replaceAll("\\D", "");
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public <T> void setIfNotNull(T value, Consumer<T> setter) {
		if (value != null) {
			setter.accept(value);
		}
	}

	public Student fromDTO(StudentDTO dto) {
		Student student = new Student();
		student.setName(dto.name());
		student.setPhone(dto.phone());
		student.setCpf(dto.cpf());
		student.setStartDate(dto.startDate());
		student.setActive(dto.active());

		return student;
	}

	public StudentDTO toDTO(Student obj) {
		return new StudentDTO(obj.getId(), obj.getName(), obj.getPhone(), obj.getCpf(), obj.getStartDate(), obj.getActive());
	}
}
