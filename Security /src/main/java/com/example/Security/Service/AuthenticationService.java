package com.example.Security.Service;

import com.example.Security.Model.User;
import com.example.Security.dto.JwtAuthenticationResponse;
import com.example.Security.dto.SignInRequest;
import com.example.Security.dto.SignUpRequest;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signIn(SignInRequest signInRequest);
}
