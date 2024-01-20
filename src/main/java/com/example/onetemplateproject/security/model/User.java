package com.example.onetemplateproject.security.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@RedisHash("User")
@Data
public class User implements Serializable {

    @Id
    private Long id;

    @Indexed
    private String login;

    private String password;

    private String fio;

    private String role;

    private String status;
}