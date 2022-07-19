package com.andikscript.simpleuser.service;

import com.andikscript.simpleuser.model.User;

import java.util.Optional;

public interface UserService {
    void addUser(User user);
    Optional<User> findByUsername(String username);
}
