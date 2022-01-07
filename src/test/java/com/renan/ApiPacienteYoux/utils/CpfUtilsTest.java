package com.renan.ApiPacienteYoux.utils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CpfUtilsTest {

	private static final String cpf = "57484437085";
	private final BCryptPasswordEncoder bCryEncoder = new BCryptPasswordEncoder();

	@Test
	public void testCpfNulo() throws Exception {
		assertNull(CpfUtils.gerarBcrypt(null));
	}

	@Test
	public void testGerarHashCpf() throws Exception {
		String hash = CpfUtils.gerarBcrypt(cpf);
		assertTrue(bCryEncoder.matches(cpf, hash));
	}
}
