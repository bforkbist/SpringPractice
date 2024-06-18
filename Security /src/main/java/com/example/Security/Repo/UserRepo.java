package com.example.Security.Repo;

import com.example.Security.Enum.Role;
import com.example.Security.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    User findByRole(Role role);
}
