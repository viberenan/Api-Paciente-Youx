package com.renan.ApiPacienteYoux.services.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renan.ApiPacienteYoux.entities.Uf;
import com.renan.ApiPacienteYoux.repositories.UfRepository;
import com.renan.ApiPacienteYoux.services.UfService;

@Service
public class UfServiceImpl implements UfService {

	@Autowired
	private UfRepository ufRepository;

	@Override
	public Optional<Uf> findByNome(String nome) {
		return Optional.ofNullable(ufRepository.findBynome(nome));
	}

	@Override
	public Uf persistir(Uf uf) {
		return this.ufRepository.save(uf);
	}

}
