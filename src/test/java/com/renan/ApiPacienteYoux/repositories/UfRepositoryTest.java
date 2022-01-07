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

import com.renan.ApiPacienteYoux.entities.Uf;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UfRepositoryTest {
	
	@Autowired
	private UfRepository ufRepository;
	
	private static final String nome = "GO";
	
	@Before
	public void setUp() throws Exception{
		Uf uf = new Uf();
		uf.setNome(nome);
		this.ufRepository.save(uf);
	}
	
	@After
	public final void tearDown() {
		this.ufRepository.deleteAll();
	}
	
	@Test
	public void testFindByNome() {
		Uf uf = this.ufRepository.findBynome(nome);
		assertEquals(nome, uf.getNome());
	}

}
