package cz.michalsimcik.basic_user_api.service;

import cz.michalsimcik.basic_user_api.exception.UserNotFoundException;
import cz.michalsimcik.basic_user_api.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final List<User> users = new ArrayList<>();

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User getUserById(Long id) {
        return users.stream()
                .filter(u -> Objects.equals(u.getId(), id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(id));

    }
}
