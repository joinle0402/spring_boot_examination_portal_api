package com.johnsmith.examportal.api.services.interfaces;

import com.johnsmith.examportal.api.entities.User;
import com.johnsmith.examportal.api.payloads.requests.LoginRequest;
import com.johnsmith.examportal.api.payloads.requests.RegisterRequest;
import com.johnsmith.examportal.api.payloads.responses.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    User register(RegisterRequest request);
}
