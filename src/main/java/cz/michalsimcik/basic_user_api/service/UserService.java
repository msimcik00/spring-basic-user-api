package cz.michalsimcik.basic_user_api.service;

import cz.michalsimcik.basic_user_api.model.User;

import java.util.List;

/**
 * Service interface for user management operations.
 */
public interface UserService {

    /**
     * Retrieves all stored users.
     * @return List of all users
     */
    public List<User> getAllUsers();

    /**
     * Retrieves a specific user by their ID.
     * @param id the ID of the user to retrieve
     * @return the user with the specified ID
     * @throws com.o2_dev.demo.exceptions.UserNotFoundException if user is not found
     */
    public User getUserById(Long id);
}
