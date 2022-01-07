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

import com.renan.ApiPacienteYoux.entities.Uf;
import com.renan.ApiPacienteYoux.repositories.UfRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UfServiceTest {

	@MockBean
	private UfRepository ufRepository;

	@Autowired
	private UfService ufService;

	private static final String nome = "GO";

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.ufRepository.findBynome(Mockito.anyString())).willReturn(new Uf());
		BDDMockito.given(this.ufRepository.save(Mockito.any(Uf.class))).willReturn(new Uf());
	}

	@Test
	public void testFindByNome() {
		Optional<Uf> uf = this.ufService.findByNome(nome);
		assertTrue(uf.isPresent());
	}

	@Test
	public void testPersistirUf() {
		Uf uf = this.ufService.persistir(new Uf());
		assertNotNull(uf);
	}
}
