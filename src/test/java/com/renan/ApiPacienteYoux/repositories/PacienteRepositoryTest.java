package com.renan.ApiPacienteYoux.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.renan.ApiPacienteYoux.entities.Pacientes;
import com.renan.ApiPacienteYoux.entities.Uf;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PacienteRepositoryTest {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private UfRepository ufRepository;

	private static final String cpf = "14839710740";
	
	@Before
	public void setUp() throws Exception {
		Uf uf = new Uf();
		uf.setNome("GO");
		this.ufRepository.save(uf);
		Pacientes paciente = new Pacientes();
		paciente.setNome("Teste");
		paciente.setCpf(cpf);
		paciente.setUf(this.ufRepository.findBynome(uf.getNome()));
		this.pacienteRepository.save(paciente);
	}

	@After
	public final void tearDown() {
		this.pacienteRepository.deleteAll();
	}

	@Test
	public void testFindByCPF() {
		Pacientes paciente = this.pacienteRepository.findByCpf(cpf);
		assertEquals(cpf, paciente.getCpf());
	}
		
}
