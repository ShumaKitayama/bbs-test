package com.example.bbs.application.usecase;

import com.example.bbs.application.auth.UserAuthRepository;
import com.example.bbs.application.form.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class UserAuthUsecase {
    private final UserAuthRepository authRepository;

    public void userCreate(UserForm form, HttpServletRequest request) throws ServletException {
        authRepository.createUser(
                form.getUsername(),
                form.getPassword()
        );

        request.login(form.getUsername(), form.getPassword());
    }
}
