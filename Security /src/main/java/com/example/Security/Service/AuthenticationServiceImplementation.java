package com.example.Security.Service;

import com.example.Security.Enum.Role;
import com.example.Security.Model.User;
import com.example.Security.Repo.UserRepo;
import com.example.Security.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImplementation implements AuthenticationService {

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    public User signUp(SignUpRequest signUpRequest){

        User user = new User();

        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getEmail()));
        user.setFirstName(signUpRequest.getFirstName());
        user.setRole(Role.User);
        user.setLastName(signUpRequest.getLastName());

        return userRepo.save(user);
    }
}
