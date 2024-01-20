package com.example.onetemplateproject.security.repository;


import com.example.onetemplateproject.security.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
