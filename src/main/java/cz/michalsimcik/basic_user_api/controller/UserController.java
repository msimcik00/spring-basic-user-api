package cz.michalsimcik.basic_user_api.controller;

import cz.michalsimcik.basic_user_api.model.User;
import cz.michalsimcik.basic_user_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller handling user-related operations.
 * All endpoints require basic authentication.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves all stored users.
     * @return List of all users
     */
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Retrieves a specific user by their ID.
     * @param userId the ID of the user to retrieve
     * @return the user with the specified ID
     * @throws cz.michalsimcik.basic_user_api.exception.UserNotFoundException if user is not found
     */
    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }
}
