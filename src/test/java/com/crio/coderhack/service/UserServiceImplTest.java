package com.crio.coderhack.service;

import com.crio.coderhack.dto.CreateUserDTO;
import com.crio.coderhack.entity.User;
import com.crio.coderhack.exception.UserNotFoundException;
import com.crio.coderhack.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User("1", "UserOne");
        user1.setScore(50);
        User user2 = new User("2", "UserTwo");
        user2.setScore(80);
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals(user2.getUsername(), result.getFirst().getUsername());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUser_UserExists() {
        String userId = "1";
        User user = new User(userId, "UserOne");
        user.setScore(50);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.getUser(userId);

        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUser_UserNotFound() {
        String userId = "1";
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUser(userId));
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testRegisterUser_NewUser() {
        CreateUserDTO createUserDTO = new CreateUserDTO("1", "NewUser");
        User newUser = new User("1", "NewUser");
        when(userRepository.existsById("1")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User result = userService.registerUser(createUserDTO);

        assertNotNull(result);
        assertEquals(createUserDTO.getUserId(), result.getUserId());
        verify(userRepository, times(1)).existsById("1");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUser_UserAlreadyExists() {
        CreateUserDTO createUserDTO = new CreateUserDTO("1", "NewUser");
        when(userRepository.existsById("1")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(createUserDTO));
        verify(userRepository, times(1)).existsById("1");
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    void testUpdateUserScore_UserExists() {
        String userId = "1";
        int newScore = 70;
        User user = new User(userId, "UserOne");
        user.setScore(50);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.updateUserScore(userId, newScore);

        assertNotNull(result);
        assertEquals(newScore, result.getScore());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUserScore_UserNotFound() {
        String userId = "1";
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUserScore(userId, 50));
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testDeleteUser_UserExists() {
        String userId = "1";
        when(userRepository.existsById(userId)).thenReturn(true);
        doNothing().when(userRepository).deleteById(userId);

        String result = userService.deleteUser(userId);

        assertEquals("User with Id: 1 has been deleted", result);
        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void testDeleteUser_UserNotFound() {
        String userId = "1";
        when(userRepository.existsById(userId)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(userId));
        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, times(0)).deleteById(userId);
    }
}