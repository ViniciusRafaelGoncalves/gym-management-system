package com.gym.academia.controller;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gym.academia.dto.StudentDTO;
import com.gym.academia.service.StudentService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/students")
public class StudentController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private StudentService service;
	
	@GetMapping
	public ResponseEntity<List<StudentDTO>> findAll() {
		List<StudentDTO> list = service.getStudents();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<StudentDTO> findById(@PathVariable Long id) {
		StudentDTO obj = service.getStudentById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/search")
	public ResponseEntity<List<StudentDTO>> findByName(@RequestParam String name) {
		List<StudentDTO> list = service.findByName(name);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<StudentDTO> createStudent(@RequestBody @Valid StudentDTO obj) {
		obj = service.addStudent(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.id()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody @Valid StudentDTO obj){
		obj = service.updateStudent(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
