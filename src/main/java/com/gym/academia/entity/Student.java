package com.gym.academia.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
@Entity
@Table(name = "students")
public class Student implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@NotBlank(message = "O campo nome não pode ser nulo ou vazio")
	private String name;
	@Pattern(regexp = "^\\(?\\d{2}\\)?\\s?(9?\\d{4})-?\\d{4}$", 
			message = "Número de telefone inválido")
	@Column(nullable = false)
	private String phone;
	@CPF(message = "CPF inválido")
	@Column(nullable = false, unique = true, length = 11)
	private String cpf;
	@PastOrPresent(message = "Não é possível iniciar no futuro")
	@Column(nullable = false)                                                                                    
	private LocalDate startDate;
	@Column(nullable = false)
	private Boolean active;
                           
	public Student() {
		super();
	}

	public Student(String name, String phone, String cpf, LocalDate startDate, Boolean active) {
		super();
		this.name = name;
		this.phone = phone;
		this.cpf = cpf;
		this.startDate = startDate;
		this.active = true;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	@PrePersist
	public void prePersist() {
		if (startDate == null) {
			startDate = LocalDate.now();
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(id, other.id);
	}

}
