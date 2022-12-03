package org.editor.user;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.security.core.userdetails.User.UserBuilder;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Test loadUserByUsernameSuccess")
    public void testLoadUserByUsernameSuccess() {
        User user = new User("1", "mock", "", "");
        doReturn(user).when(userRepository).findByUsername("mock");

        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
        userBuilder.password(user.getPassword());
        userBuilder.roles(user.getRole());

        Assertions.assertEquals(userBuilder.build(), userService.loadUserByUsername("mock"));
    }

    @Test
    @DisplayName("Test loadUserByUsernameFail")
    public void testLoadUserByUsernameFail() {
        doReturn(null).when(userRepository).findByUsername("mock");
        Assertions.assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("mock"));
    }

    @Test
    @DisplayName("Test registerUser")
    public void testRegisterUser() {
        User user = new User("1", "mock", "test", "");
        doReturn(user).when(userRepository).save(user);

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        Assertions.assertEquals(user, userService.registerUser(user));
    }



}
