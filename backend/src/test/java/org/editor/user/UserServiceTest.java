package org.editor.user;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Test registerUser")
    public void testRegisterUser(){
        //instantiate user
        User user = new User("123", "test_pilot", "12345", "test");
        doReturn(user).when(userRepository).save(user);//mock object

        User registeredUser = userService.registerUser(user);

        Assertions.assertEquals(registeredUser, user);
    }
}
