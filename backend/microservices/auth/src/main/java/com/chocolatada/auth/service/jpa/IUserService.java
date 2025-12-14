package com.chocolatada.auth.service.jpa;

import com.chocolatada.auth.entity.User;
import com.chocolatada.auth.entity.UserStatus;
import com.chocolatada.auth.exception.InvalidCredentialsException;
import com.chocolatada.auth.exception.InvalidUserDataException;
import com.chocolatada.auth.exception.UserAlreadyExistsException;
import com.chocolatada.auth.exception.UserNotActiveException;

public interface IUserService {

    User registerUser(String email, String password) 
        throws InvalidUserDataException, UserAlreadyExistsException;

    User loginUser(String email, String password) 
        throws InvalidUserDataException, InvalidCredentialsException, UserNotActiveException;

    User updateUserStatus(Long userId, UserStatus newStatus);
}
