package com.renan.ApiPacienteYoux.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.renan.ApiPacienteYoux.entities.Uf;

public interface UfRepository extends JpaRepository<Uf, Long> {
	
	@Transactional(readOnly = true)
	Uf findBynome(String nome);
}
