package cz.michalsimcik.basic_user_api.service;

import cz.michalsimcik.basic_user_api.exception.UserNotFoundException;
import cz.michalsimcik.basic_user_api.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTests {

    private UserServiceImpl userService;
    private final User mockUser = new User(1L, "Jan", "Novák", "jan.novak@example.com", 42);

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
    }

    @Test
    @DisplayName("getAllUsers() should not return null")
    void getAllUsers_shouldNotReturnNull() {
        List<User> users = userService.getAllUsers();
        assertNotNull(users);
    }

    @Test
    @DisplayName("getUserById() should return the correct user")
    void getUserById_shouldReturnCorrectUserById() {
        userService.getAllUsers().add(mockUser);
        User user = userService.getUserById(1L);

        assertEquals(mockUser, user);
    }

    @Test
    @DisplayName("getUserById() should throw an exception for a non-existent user")
    void getUserById_shouldThrowExceptionForNonExistentUser() {
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(999L));
    }
}
