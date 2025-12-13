package com.chocolatada.auth.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.chocolatada.auth.exception.InvalidUserDataException;

@Component
public class UserValidator {

    // Patrón regex para validar email
    private static final String EMAIL_REGEX = 
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * Valida que el email tenga un formato correcto.
     *
     * @param email Email a validar
     * @throws InvalidUserDataException si el email no es válido
     */
    public void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new InvalidUserDataException("El email no puede estar vacío");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidUserDataException("El email tiene un formato inválido");
        }
    }

    /**
     * Saneamiento y validación de contraseña.
     * Solo permite letras y números.
     *
     * @param password Contraseña a validar y sanear
     * @throws InvalidUserDataException si la contraseña contiene caracteres inválidos o está vacía
     */
    public void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new InvalidUserDataException("La contraseña no puede estar vacía");
        }

        // Verifica que contenga solo letras y números
        if (!password.matches("^[a-zA-Z0-9]+$")) {
            throw new InvalidUserDataException(
                "La contraseña solo puede contener letras y números"
            );
        }

        if (password.length() < 6) {
            throw new InvalidUserDataException(
                "La contraseña debe tener al menos 6 caracteres"
            );
        }
    }
}
