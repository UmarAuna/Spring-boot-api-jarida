package com.jarida.server.jaridaserver.spring_security_2.service.impl;

import com.jarida.server.jaridaserver.spring_security_2.entity.UserTwos;
import com.jarida.server.jaridaserver.spring_security_2.exception.BadCredentialsExceptionTwos;
import com.jarida.server.jaridaserver.spring_security_2.exception.ResourceNotFoundExceptionTwos;
import com.jarida.server.jaridaserver.spring_security_2.payload.UpdatePasswordRequestTwos;
import com.jarida.server.jaridaserver.spring_security_2.payload.UpdatePhoneNumberRequestTwos;
import com.jarida.server.jaridaserver.spring_security_2.repository.UserRepositoryTwos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplTwos {

    UserRepositoryTwos userRepositoryTwos;

    private final PasswordEncoder passwordEncoderTwo;

    @Autowired
    public  AuthServiceImplTwos(UserRepositoryTwos userRepositoryTwos, PasswordEncoder passwordEncoderTwo) {
        this.userRepositoryTwos = userRepositoryTwos;
        this.passwordEncoderTwo = passwordEncoderTwo;
    }

    private Boolean currentPasswordMatches(UserTwos currentUser, String password) {
        return passwordEncoderTwo.matches(password, currentUser.getPassword());
    }

    public UserTwos findByUserId(long  userId) {
        return userRepositoryTwos.findByUserId(userId);
    }

    public void updatePassword(long userId, UpdatePasswordRequestTwos updatePasswordRequestTwos) {
            UserTwos user = userRepositoryTwos.findById(userId).orElseThrow(
                    () -> new ResourceNotFoundExceptionTwos("User", "id", userId));

            if (!currentPasswordMatches(user, updatePasswordRequestTwos.getOldPassword())) {
                throw new BadCredentialsExceptionTwos(HttpStatus.BAD_REQUEST, "Invalid current password");
            }
            String newPassword = passwordEncoderTwo.encode(updatePasswordRequestTwos.getNewPassword());
            user.setPassword(newPassword);
            userRepositoryTwos.save(user);

        //return Optional.of(user);
    }

    public void updatePhoneNumber(long userId, UpdatePhoneNumberRequestTwos updatePhoneNumberRequestTwos) {
        UserTwos user = userRepositoryTwos.findById(userId).orElseThrow(
                () -> new ResourceNotFoundExceptionTwos("User", "id", userId));

        if (updatePhoneNumberRequestTwos.getPhoneNumber().length() < 11) {
            throw new BadCredentialsExceptionTwos(HttpStatus.BAD_REQUEST, "Invalid Phone number");
        }
        String newPhoneNumber = updatePhoneNumberRequestTwos.getPhoneNumber();
        user.setPassword(newPhoneNumber);
        userRepositoryTwos.save(user);

        //return Optional.of(user);
    }
}
