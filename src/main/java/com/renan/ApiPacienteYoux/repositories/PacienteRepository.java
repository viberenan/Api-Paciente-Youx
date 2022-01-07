package com.renan.ApiPacienteYoux.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.renan.ApiPacienteYoux.entities.Pacientes;

public interface PacienteRepository extends JpaRepository<Pacientes, Long> {
	
	@Transactional(readOnly = true)
	Pacientes findByCpf(String cpf);
	
	@Transactional(readOnly = true)
	Optional<Pacientes> findById(Long id);

}
