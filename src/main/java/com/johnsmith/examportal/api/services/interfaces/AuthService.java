package com.johnsmith.examportal.api.configurations.services.interfaces;

import com.johnsmith.examportal.api.entities.User;
import com.johnsmith.examportal.api.payloads.requests.LoginRequest;
import com.johnsmith.examportal.api.payloads.requests.RegisterRequest;
import com.johnsmith.examportal.api.payloads.responses.LoginResponse;
import com.johnsmith.examportal.api.payloads.responses.UserPrincipalResponse;
import com.johnsmith.examportal.api.security.UserPrincipal;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    User register(RegisterRequest request);
    UserPrincipalResponse getUserPrincipal(UserPrincipal userPrincipal);
}
