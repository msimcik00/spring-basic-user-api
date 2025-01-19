package cz.michalsimcik.basic_user_api.controller;

import cz.michalsimcik.basic_user_api.exception.UserNotFoundException;
import cz.michalsimcik.basic_user_api.model.User;
import cz.michalsimcik.basic_user_api.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = UserController.class)
class UserControllerTests {

    private static final String BASE_URL = "/api/users";
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    private final User testUser1 = new User(1L, "Jan", "Novák", "jan.novak@example.com", 42);
    private final User testUser2 = new User(2L, "Jana", "Nováková", "jana.novakova@example.com", 24);
    private final List<User> allUsers = List.of(testUser1, testUser2);

    @Test
    @DisplayName("Should return list of all users when authenticated")
    void shouldReturnUsersListWhenGetAllUsersIsCalled() throws Exception {
        when(userService.getAllUsers()).thenReturn(allUsers);

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .with(httpBasic(ADMIN_USERNAME, ADMIN_PASSWORD)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("Should return user details when fetching existing user by ID")
    void shouldReturnUserDetailsWhenFetchingExistingUser() throws Exception {
        when(userService.getUserById(2L)).thenReturn(testUser2);

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/2")
                        .with(httpBasic(ADMIN_USERNAME, ADMIN_PASSWORD)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.firstName").value("Jana"))
                .andExpect(jsonPath("$.lastName").value("Nováková"));
    }

    @Test
    @DisplayName("Should return 404 Not Found when user ID doesn't exist")
    void shouldReturnNotFoundWhenUserDoesNotExist() throws Exception {
        when(userService.getUserById(999L))
                .thenThrow(new UserNotFoundException(999L));

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/999")
                        .with(httpBasic(ADMIN_USERNAME, ADMIN_PASSWORD)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should return 401 Unauthorized when accessing user details without authentication")
    void shouldReturnUnauthorizedWhenAccessingUserDetailsWithoutAuth() throws Exception {
        when(userService.getUserById(2L)).thenReturn(testUser1);

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Should return 401 Unauthorized when accessing users list without authentication")
    void shouldReturnUnauthorizedWhenAccessingUsersListWithoutAuth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
                .andExpect(status().isUnauthorized());
    }
}
