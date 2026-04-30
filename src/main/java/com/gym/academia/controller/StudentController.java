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

import com.gym.academia.entity.Student;
import com.gym.academia.service.StudentService;


@RestController
@RequestMapping("/students")
public class StudentController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private StudentService service;
	
	@GetMapping
	public ResponseEntity<List<Student>> findAll() {
		List<Student> list = service.getStudents();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Student> findById(@PathVariable Long id) {
		Student obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<List<Student>> findByName(@RequestParam String name) {
		List<Student> list = service.findByName(name);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student obj) {
		obj = service.addStudent(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student obj){
		obj = service.updateStudent(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Student> deleteById(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
