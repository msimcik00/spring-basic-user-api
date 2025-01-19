package cz.michalsimcik.basic_user_api.exception;

public class UserNotFoundException extends RuntimeException{

    /**
     * Exception thrown when attempting to access a user that doesn't exist.
     */
    public UserNotFoundException(Long userId) {
        super("User with ID " + userId + " was not found.");
    }
}

