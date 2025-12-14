package com.chocolatada.auth.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.chocolatada.auth.api.response.VerificationResponse;
import com.chocolatada.auth.entity.UserStatus;
import com.chocolatada.auth.security.JwtTokenProvider;
import com.chocolatada.auth.security.VerificationTokenData;
import com.chocolatada.auth.service.jpa.IUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class EmailVerificationController {

    private final IUserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/verify")
    public ResponseEntity<VerificationResponse> verifyEmail(@RequestParam String token) {
        try {
            log.info("Intento de verificación de email con token: {}", token.substring(0, Math.min(20, token.length())) + "...");
            
            VerificationTokenData tokenData = jwtTokenProvider.validateVerificationToken(token);
            Long userId = tokenData.getUserId();
            String email = tokenData.getEmail();

            log.info("Token válido para usuario ID: {}, email: {}", userId, email);

            userService.updateUserStatus(userId, UserStatus.ACTIVE);

            log.info("Usuario ID: {} marcado como ACTIVE", userId);

            VerificationResponse response = new VerificationResponse(
                true,
                "Correo electrónico verificado exitosamente. Tu cuenta está activada."
            );

            return ResponseEntity.ok(response);

        } catch (JWTVerificationException e) {
            log.warn("Error de verificación de token: {}", e.getMessage());
            VerificationResponse response = new VerificationResponse(
                false,
                "Token de verificación inválido o expirado"
            );
            return ResponseEntity.badRequest().body(response);

        } catch (IllegalArgumentException e) {
            log.error("Usuario no encontrado durante verificación: {}", e.getMessage());
            VerificationResponse response = new VerificationResponse(
                false,
                "Usuario no encontrado"
            );
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            log.error("Error inesperado durante verificación de email: {}", e.getMessage(), e);
            VerificationResponse response = new VerificationResponse(
                false,
                "Error interno del servidor"
            );
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
