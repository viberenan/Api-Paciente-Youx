package com.renan.ApiPacienteYoux.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.renan.ApiPacienteYoux.entities.Pacientes;
import com.renan.ApiPacienteYoux.repositories.PacienteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PacienteServiceTest {
	
	@MockBean
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private PacienteService pacienteService;
	
	private static final String cpf = "14839710740";
	
	private static final Long id = 1L;
	
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.pacienteRepository.save(Mockito.any(Pacientes.class))).willReturn(new Pacientes());
		BDDMockito.given(this.pacienteRepository.findByCpf(Mockito.anyString())).willReturn(new Pacientes());
		BDDMockito.given(this.pacienteRepository.getById(Mockito.anyLong())).willReturn(new Pacientes());
	}
	
	@Test
	public void testPersistirPaciente() {
		Pacientes paciente = this.pacienteService.persistir(new Pacientes());
		assertNotNull(paciente);
	}
	
	@Test
	public void testFindByCpf() {
		Optional<Pacientes> paciente = this.pacienteService.findbyCpf(cpf);
		assertTrue(paciente.isPresent());
	}
	
	@Test
	public void testFindById() {
		Optional<Pacientes> paciente = this.pacienteService.findById(id);
		assertTrue(paciente.isPresent());
	}
}
