package com.chocolatada.auth.security;

/**
 * Clase para encapsular la información del token de verificación.
*/
public class VerificationTokenData {
    public final Long userId;
    public final String email;

    public VerificationTokenData(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }
}