package com.renan.ApiPacienteYoux.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "uf")
public class Uf {
	private Long id;
	private String nome;
	private List<Pacientes> pacientes;

	public Uf() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@OneToMany
	public List<Pacientes> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Pacientes> pacientes) {
		this.pacientes = pacientes;
	}

}
