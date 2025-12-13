package com.chocolatada.auth.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Servicio encargado de la encriptación y validación de contraseñas.
 * Utiliza BCrypt como algoritmo de hashing.
 */
@Component
public class PasswordEncoderService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PasswordEncoderService() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Encripta una contraseña en texto plano.
     *
     * @param rawPassword Contraseña en texto plano
     * @return Contraseña encriptada (hash)
     */
    public String encode(String rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    /**
     * Compara una contraseña en texto plano con su hash encriptado.
     *
     * @param rawPassword     Contraseña en texto plano
     * @param encodedPassword Contraseña encriptada (hash)
     * @return true si coinciden, false en caso contrario
     */
    public boolean matches(String rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
