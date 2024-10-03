package com.crio.coderhack.controller;

import com.crio.coderhack.dto.CreateUserDTO;
import com.crio.coderhack.dto.UpdateUserDTO;
import com.crio.coderhack.entity.User;
import com.crio.coderhack.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crio.coderhack.config.PathConstants.*;

@RestController
@RequestMapping(API_BASE_PATH)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(GET_ALL_USER)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping(GET_USER)
    public ResponseEntity<?> getUser(@PathVariable("userId") String userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping(CREATE_USER)
    public ResponseEntity<User> registerUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        User user = userService.registerUser(createUserDTO);
        return ResponseEntity.ok(user);
    }

    @PutMapping(UPDATE_USER)
    public ResponseEntity<User> updateUserScore(@PathVariable("userId") String userId, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        User user = userService.updateUserScore(userId, updateUserDTO.getScore());
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(DELETE_USER)
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) {
        String response = userService.deleteUser(userId);
        return ResponseEntity.ok(response);
    }
}
