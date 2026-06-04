package com.gym.academia.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

public record StudentDTO(
		@NotBlank(message = "Id obrigatório")
		Long id,
		@NotBlank(message = "Nome obrigatório")
		String name,
		@NotBlank
		@Pattern(regexp = "^\\(?\\d{2}\\)?\\s?(9?\\d{4})-?\\d{4}$", 
		message = "Número de telefone inválido")
		String phone, 
		@NotBlank
		@CPF
		String cpf, 
		@NotNull
		@PastOrPresent(message = "Não é possível iniciar no futuro")
		LocalDate startDate,
		@NotNull(message = "Status obrigatório")
	    Boolean active){

    
}
