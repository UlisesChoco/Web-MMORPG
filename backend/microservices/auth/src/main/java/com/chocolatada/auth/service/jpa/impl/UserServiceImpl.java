package com.chocolatada.auth.service.jpa.impl;

import org.springframework.stereotype.Service;

import com.chocolatada.auth.entity.User;
import com.chocolatada.auth.entity.UserStatus;
import com.chocolatada.auth.exception.InvalidCredentialsException;
import com.chocolatada.auth.exception.InvalidUserDataException;
import com.chocolatada.auth.exception.UserAlreadyExistsException;
import com.chocolatada.auth.exception.UserNotActiveException;
import com.chocolatada.auth.repository.UserRepository;
import com.chocolatada.auth.security.PasswordEncoderService;
import com.chocolatada.auth.service.jpa.IUserService;
import com.chocolatada.auth.validator.UserValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final PasswordEncoderService passwordEncoderService;

    @Override
    public User registerUser(String email, String password) 
        throws InvalidUserDataException, UserAlreadyExistsException {
        // Validar datos entrantes
        userValidator.validateEmail(email);
        userValidator.validatePassword(password);

        // Verificar si el usuario ya existe
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException(
                "Ya existe un usuario registrado con el email: " + email
            );
        }

        // Crear y guardar el nuevo usuario
        String encodedPassword = passwordEncoderService.encode(password);
        User newUser = User.builder()
            .email(email)
            .password(encodedPassword)
            .status(UserStatus.PENDING)  // Nuevo usuario inicia como PENDING
            .build();

        return userRepository.save(newUser);
    }

    @Override
    public User loginUser(String email, String password) 
        throws InvalidUserDataException, InvalidCredentialsException, UserNotActiveException {
        // Validar datos entrantes
        userValidator.validateEmail(email);
        userValidator.validatePassword(password);

        // Buscar el usuario por email
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new InvalidCredentialsException(
                "Las credenciales proporcionadas son inválidas"
            ));

        // Verificar que la contraseña coincida
        if (!passwordEncoderService.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException(
                "Las credenciales proporcionadas son inválidas"
            );
        }

        // Verificar el estado del usuario
        if (user.getStatus() == UserStatus.PENDING) {
            throw new UserNotActiveException(
                "El usuario aún no ha verificado su correo electrónico"
            );
        }

        if (user.getStatus() == UserStatus.BANNED) {
            throw new UserNotActiveException(
                "Este usuario ha sido baneado y no tiene acceso al sistema"
            );
        }

        return user;
    }

    @Override
    public User updateUserStatus(Long userId, UserStatus newStatus) {
        // Buscar el usuario por ID
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException(
                "Usuario no encontrado con ID: " + userId
            ));

        // Actualizar el estado
        user.setStatus(newStatus);

        // Guardar los cambios en la BD
        return userRepository.save(user);
    }
}