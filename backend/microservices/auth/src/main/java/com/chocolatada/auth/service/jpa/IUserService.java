package com.chocolatada.auth.service.jpa;

import com.chocolatada.auth.entity.User;
import com.chocolatada.auth.entity.UserStatus;
import com.chocolatada.auth.exception.InvalidCredentialsException;
import com.chocolatada.auth.exception.InvalidUserDataException;
import com.chocolatada.auth.exception.UserAlreadyExistsException;
import com.chocolatada.auth.exception.UserNotActiveException;

public interface IUserService {

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param email    Correo electrónico del usuario
     * @param password Contraseña del usuario
     * @return Usuario registrado
     * @throws InvalidUserDataException    si el email o contraseña tienen formato inválido
     * @throws UserAlreadyExistsException  si ya existe un usuario con el mismo email
     */
    User registerUser(String email, String password) 
        throws InvalidUserDataException, UserAlreadyExistsException;

    /**
     * Autentica un usuario en el sistema.
     *
     * @param email    Correo electrónico del usuario
     * @param password Contraseña del usuario
     * @return Usuario autenticado
     * @throws InvalidUserDataException    si el email o contraseña tienen formato inválido
     * @throws InvalidCredentialsException si las credenciales no son válidas
     * @throws UserNotActiveException      si el usuario aún no ha verificado su email o ha sido baneado
     */
    User loginUser(String email, String password) 
        throws InvalidUserDataException, InvalidCredentialsException, UserNotActiveException;

    /**
     * Actualiza el estado de un usuario.
     *
     * @param userId    ID del usuario a actualizar
     * @param newStatus Nuevo estado del usuario
     * @return Usuario actualizado
     */
    User updateUserStatus(Long userId, UserStatus newStatus);
}
