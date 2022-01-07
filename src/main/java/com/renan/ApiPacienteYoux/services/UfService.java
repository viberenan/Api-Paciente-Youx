package com.renan.ApiPacienteYoux.services;

import java.util.Optional;

import com.renan.ApiPacienteYoux.entities.Uf;

public interface UfService {

	/**
	 * Busca uma Uf pelo nome.
	 * 
	 * @param nome
	 * @return Optional<Uf>
	 */
	Optional<Uf> findByNome(String nome);

	/**
	 * Cadastra um novo Uf
	 * 
	 * @param uf
	 * @return UF
	 */
	Uf persistir(Uf uf);

}
