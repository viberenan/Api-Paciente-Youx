package com.renan.ApiPacienteYoux.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renan.ApiPacienteYoux.entities.Pacientes;
import com.renan.ApiPacienteYoux.repositories.PacienteRepository;
import com.renan.ApiPacienteYoux.services.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Override
	public Pacientes persistir(Pacientes paciente) {
		return this.pacienteRepository.save(paciente);
	}

	@Override
	public Optional<Pacientes> findbyCpf(String cpf) {
		return Optional.ofNullable(this.pacienteRepository.findByCpf(cpf));
	}

	@Override
	public Optional<Pacientes> findById(Long id) {
		return Optional.ofNullable(this.pacienteRepository.getById(id));
	}

	@Override
	public void remover(Long id) {
		this.pacienteRepository.deleteById(id);

	}

	@Override
	public List<Pacientes> findlAll() {
		return this.pacienteRepository.findAll();
	}

}
