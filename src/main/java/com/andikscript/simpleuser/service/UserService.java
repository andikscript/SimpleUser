package com.andikscript.simpleuser.service;

import com.andikscript.simpleuser.model.User;

import java.util.Optional;

public interface UserService {
    public void addUser(User user);
    public Optional<User> findByUsername(String username);
}
