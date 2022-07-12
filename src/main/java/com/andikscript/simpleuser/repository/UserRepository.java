package com.andikscript.simpleuser.repository;

import com.andikscript.simpleuser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
