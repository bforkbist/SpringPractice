package com.example.Security.Service;

import com.example.Security.Model.User;
import com.example.Security.dto.SignUpRequest;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
}
