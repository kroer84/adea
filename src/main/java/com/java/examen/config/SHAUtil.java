package com.java.examen.config;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SHAUtil {

	public static String encriptar(String datos) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(datos.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error al encriptar con SHA-256", e);
		}
	}

	public static boolean verificar(String datos, String hashEncriptado) {
		String hashCalculado = encriptar(datos);
		return hashCalculado.equals(hashEncriptado);
	}
}