package com.crio.coderhack.service;

import com.crio.coderhack.dto.CreateUserDTO;
import com.crio.coderhack.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUser(String userId);
    User registerUser(CreateUserDTO createUserDTO);
    User updateUserScore(String userId, int score);
    String deleteUser(String userId);
}
