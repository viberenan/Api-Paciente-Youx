package com.renan.ApiPacienteYoux.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CpfUtils {

	public CpfUtils() {

	}

	public static String gerarBcrypt(String cpf) {
		if (cpf == null) {
			return cpf;
		}

		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.encode(cpf);
	}

}
