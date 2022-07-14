package com.andikscript.simpleuser.service;

import com.andikscript.simpleuser.model.User;
import com.andikscript.simpleuser.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }
}
