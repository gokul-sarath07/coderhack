package com.crio.coderhack.service;

import com.crio.coderhack.constants.Badges;
import com.crio.coderhack.dto.CreateUserDTO;
import com.crio.coderhack.entity.User;
import com.crio.coderhack.exception.UserNotFoundException;
import com.crio.coderhack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.sort((userOne, userTwo) -> userTwo.getScore() - userOne.getScore());

        return users;
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with Id: " + userId + " not found"));
    }

    @Override
    public User registerUser(CreateUserDTO createUserDTO) {
        if (userRepository.existsById(createUserDTO.getUserId())) {
            throw new IllegalArgumentException("UserId already exists. Please use another userId");
        }
        User user = createUserObj(createUserDTO);
        return userRepository.save(user);
    }

    private User createUserObj(CreateUserDTO createUserDTO) {
        return new User(createUserDTO.getUserId(), createUserDTO.getUsername());
    }

    @Override
    public User updateUserScore(String userId, int score) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with Id: " + userId + " not found"));

        user.setScore(score);
        updateUserBadge(score, user);

        return userRepository.save(user);
    }

    private void updateUserBadge(int score, User user) {
        List<Badges> newBadges = new ArrayList<>();
        if (score >= 1) {
            newBadges.add(Badges.CODE_NINJA);
        }
        if (score >= 30) {
            newBadges.add(Badges.CODE_CHAMP);
        }
        if (score >= 60) {
            newBadges.add(Badges.CODE_MASTER);
        }
        user.setBadges(newBadges);
    }

    @Override
    public String deleteUser(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User with Id: " + userId + " not found");
        }
        userRepository.deleteById(userId);

        return "User with Id: " + userId + " has been deleted";
    }
}
