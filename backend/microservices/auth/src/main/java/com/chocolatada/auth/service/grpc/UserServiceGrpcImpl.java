package com.chocolatada.auth.service.grpc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chocolatada.auth.entity.User;
import com.chocolatada.auth.exception.InvalidCredentialsException;
import com.chocolatada.auth.exception.InvalidUserDataException;
import com.chocolatada.auth.exception.UserAlreadyExistsException;
import com.chocolatada.auth.exception.UserNotActiveException;
import com.chocolatada.auth.grpc.LoginUserRequest;
import com.chocolatada.auth.grpc.LoginUserResponse;
import com.chocolatada.auth.grpc.RegisterUserRequest;
import com.chocolatada.auth.grpc.RegisterUserResponse;
import com.chocolatada.auth.grpc.UserServiceGrpc;
import com.chocolatada.auth.security.JwtTokenProvider;
import com.chocolatada.auth.service.jpa.IUserService;
import com.chocolatada.auth.service.mail.IMailService;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementación del servicio gRPC para autenticación de usuarios.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceGrpcImpl extends UserServiceGrpc.UserServiceImplBase {

    private final IUserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final IMailService mailService;

    @Value("${app.verification.url:http://localhost:8080/api/auth/verify}")
    private String verificationBaseUrl;

    /**
     * Registra un nuevo usuario.
     *
     * @param request          Solicitud de registro con email y contraseña
     * @param responseObserver Observador para enviar la respuesta
     */
    @Override
    public void registerUser(RegisterUserRequest request, StreamObserver<RegisterUserResponse> responseObserver) {
        try {
            // Intentar registrar el usuario
            User newUser = userService.registerUser(request.getEmail(), request.getPassword());

            // Generar token de verificación
            String verificationToken = jwtTokenProvider.generateVerificationToken(
                newUser.getId(), 
                newUser.getEmail()
            );

            // Construir enlace de verificación
            String verificationLink = verificationBaseUrl + "?token=" + verificationToken;

            // Enviar correo de verificación
            try {
                mailService.sendVerificationEmail(newUser.getEmail(), verificationLink);
                log.info("Correo de verificación enviado a: {}", newUser.getEmail());
            } catch (Exception e) {
                log.error("Error al enviar correo de verificación: {}", e.getMessage());
                // No fallar el registro si el correo falla, pero loguear el error
            }

            // Construir respuesta de éxito
            RegisterUserResponse response = RegisterUserResponse.newBuilder()
                .setMessage("Usuario registrado exitosamente. Verifica tu correo electrónico")
                .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (InvalidUserDataException e) {
            // Email o contraseña con formato inválido
            responseObserver.onError(
                Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .asException()
            );
        } catch (UserAlreadyExistsException e) {
            // El email ya está registrado
            responseObserver.onError(
                Status.ALREADY_EXISTS
                    .withDescription(e.getMessage())
                    .asException()
            );
        } catch (Exception e) {
            // Error interno no esperado
            log.error("Error inesperado en registerUser: {}", e.getMessage(), e);
            responseObserver.onError(
                Status.INTERNAL
                    .withDescription("Error interno del servidor")
                    .asException()
            );
        }
    }

    /**
     * Autentica un usuario y retorna un token JWT.
     *
     * @param request          Solicitud de login con email y contraseña
     * @param responseObserver Observador para enviar la respuesta con el JWT
     */
    @Override
    public void loginUser(LoginUserRequest request, StreamObserver<LoginUserResponse> responseObserver) {
        try {
            // Intentar autenticar el usuario
            User user = userService.loginUser(request.getEmail(), request.getPassword());

            // Generar token JWT
            String jwt = jwtTokenProvider.generateToken(user.getId(), user.getEmail());

            // Construir respuesta de éxito
            LoginUserResponse response = LoginUserResponse.newBuilder()
                .setMessage("Login exitoso")
                .setJwt(jwt)
                .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (InvalidUserDataException e) {
            // Email o contraseña con formato inválido
            responseObserver.onError(
                Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .asException()
            );
        } catch (InvalidCredentialsException e) {
            // Credenciales inválidas
            responseObserver.onError(
                Status.UNAUTHENTICATED
                    .withDescription(e.getMessage())
                    .asException()
            );
        } catch (UserNotActiveException e) {
            // Usuario no está activo (pendiente de verificación o baneado)
            responseObserver.onError(
                Status.PERMISSION_DENIED
                    .withDescription(e.getMessage())
                    .asException()
            );
        } catch (Exception e) {
            // Error interno no esperado
            responseObserver.onError(
                Status.INTERNAL
                    .withDescription("Error interno del servidor")
                    .asException()
            );
        }
    }
}
