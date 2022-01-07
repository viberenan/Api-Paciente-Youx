package com.renan.ApiPacienteYoux.services;

import java.util.List;
import java.util.Optional;

import com.renan.ApiPacienteYoux.entities.Pacientes;

public interface PacienteService {

	/**
	 * Cadastra um novo Paciente
	 * 
	 * @param paciente
	 * @return Paciente
	 */
	Pacientes persistir(Pacientes paciente);

	/**
	 * Busca um paciente pelo CPF
	 * 
	 * @param cpf
	 * @return Optional<Pacientes>
	 */
	Optional<Pacientes> findbyCpf(String cpf);

	/**
	 * Busca um paciente pelo id
	 * 
	 * @param id
	 * @return Optional<Pacientes>
	 */
	Optional<Pacientes> findById(Long id);

	/**
	 * Busca todos os pacientes
	 * 
	 * @return List<Pacientes>
	 */
	List<Pacientes> findlAll();

	/**
	 * Remove um paciente
	 * 
	 * @param id
	 */
	void remover(Long id);
}
