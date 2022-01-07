package com.renan.ApiPacienteYoux.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renan.ApiPacienteYoux.dtos.PacienteDto;
import com.renan.ApiPacienteYoux.entities.Pacientes;
import com.renan.ApiPacienteYoux.entities.Uf;
import com.renan.ApiPacienteYoux.response.Response;
import com.renan.ApiPacienteYoux.services.PacienteService;
import com.renan.ApiPacienteYoux.services.UfService;

@RestController
@RequestMapping("api/paciente")
@CrossOrigin(origins = "*")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private UfService ufService;

	public PacienteController() {

	}

	/**
	 * CADASTRA UM NOVO PACIENTE
	 * 
	 * @param pacienteDto
	 * @param result
	 * @return 200(OK)
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<String>> cadastrar(@Valid @RequestBody PacienteDto pacienteDto, BindingResult result)
			throws NoSuchAlgorithmException {
		Response<String> response = new Response<String>();
		validarCpfExistente(pacienteDto, result);
		Pacientes paciente = this.converterDtoParaPaciente(pacienteDto, result);
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		this.pacienteService.persistir(paciente);
		response.setData("Cadastrado com Sucesso");
		return ResponseEntity.ok(response);
	}

	/**
	 * DELETA UM PACIENTE
	 * 
	 * @param id
	 * @return 200(OK)
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deletar(@PathVariable("id") Long id) throws EntityNotFoundException {
		Response<String> response = new Response<String>();
		Optional<Pacientes> paciente = this.pacienteService.findById(id);
		if (!paciente.isPresent()) {
			response.getErrors().add("Erro ao remover paciente. Id do paciente não encontrado " + id);
			return ResponseEntity.badRequest().body(response);
		}
		this.pacienteService.remover(id);
		response.setData("Removido com sucesso");
		return ResponseEntity.ok(response);
	}

	/**
	 * ATUALIZA OS DADOS DO PACIENTE
	 * 
	 * @param id
	 * @param pacienteDto
	 * @param result
	 * @return 200(OK)
	 * @throws Exception
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<String>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody PacienteDto pacienteDto, BindingResult result) throws Exception {
		Response<String> response = new Response<String>();
		Optional<Pacientes> paciente = this.pacienteService.findById(id);
		if (!paciente.isPresent()) {
			result.addError(new ObjectError("Paciente", "Paciente não encontrado."));
		}
		this.atualizarDadosPaciente(paciente.get(), pacienteDto, result);
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		this.pacienteService.persistir(paciente.get());
		response.setData("Atualizado com Sucesso");
		return ResponseEntity.ok(response);
	}

	/**
	 * BUSCA TODOS OS PACIENTES
	 * 
	 * @return List<Pacientes>
	 */
	@GetMapping
	public List<Pacientes> listaPacientes() {
		return this.pacienteService.findlAll();
	}

	/**
	 * ATUALIZA PACIENTE DE ACORDO COM PACIENTE DTO
	 * 
	 * @param paciente
	 * @param pacienteDto
	 * @param result
	 * @throws NoSuchAlgorithmException
	 */
	private void atualizarDadosPaciente(Pacientes paciente, PacienteDto pacienteDto, BindingResult result)
			throws NoSuchAlgorithmException {
		paciente.setNome(pacienteDto.getNome());
		paciente.setAltura(pacienteDto.getAltura());
		paciente.setPeso(pacienteDto.getPeso());
		paciente.setDataNascimento(pacienteDto.getDataNascimento());
		if (this.ufService.findByNome(pacienteDto.getUf()).isPresent()) {
			paciente.setUf(this.ufService.findByNome(pacienteDto.getUf()).get());
		} else {
			Uf uf = new Uf();
			uf.setNome(pacienteDto.getUf());
			this.ufService.persistir(uf);
			paciente.setUf(uf);
		}
	}

	/**
	 * VALIDAR SE CPF ESTÁ CADASTRADO
	 * 
	 * @param pacienteDto
	 * @param result
	 */
	private void validarCpfExistente(PacienteDto pacienteDto, BindingResult result) {
		this.pacienteService.findbyCpf(pacienteDto.getCPF())
				.ifPresent(pac -> result.addError(new ObjectError("paciente", "CPF já existente")));
	}

	/**
	 * CONVERTE PACIENTE DTO PARA PACIENTE
	 * 
	 * @param pacienteDto
	 * @param result
	 * @return Paciente
	 * @throws NoSuchAlgorithmException
	 */
	private Pacientes converterDtoParaPaciente(PacienteDto pacienteDto, BindingResult result)
			throws NoSuchAlgorithmException {
		Pacientes paciente = new Pacientes();
		paciente.setNome(pacienteDto.getNome());
		paciente.setCpf(pacienteDto.getCPF());
		paciente.setAltura(pacienteDto.getAltura());
		paciente.setDataNascimento(pacienteDto.getDataNascimento());
		if (this.ufService.findByNome(pacienteDto.getUf()).isPresent()) {
			paciente.setUf(this.ufService.findByNome(pacienteDto.getUf()).get());
		} else {
			Uf uf = new Uf();
			uf.setNome(pacienteDto.getUf());
			this.ufService.persistir(uf);
			paciente.setUf(uf);
		}
		return paciente;
	}
}
