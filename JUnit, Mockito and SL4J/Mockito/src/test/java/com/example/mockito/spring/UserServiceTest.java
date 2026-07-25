package com.example.mockito.spring;

import com.example.mockito.model.User;
import com.example.mockito.repository.UserRepository;
import com.example.mockito.service.UserService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Test
    public void testGetUserById() {
        UserRepository mockRepository = mock(UserRepository.class);
        User mockUser = new User(1L, "John Doe");
        when(mockRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        UserService userService = new UserService(mockRepository);
        User result = userService.getUserById(1L);

        assertEquals("John Doe", result.getName());
    }
}
