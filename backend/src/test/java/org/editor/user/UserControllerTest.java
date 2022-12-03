package org.editor.user;


import io.jsonwebtoken.Jwt;
import org.editor.model.ErrorResponse;
import org.editor.model.JwtRequest;
import org.editor.model.JwtResponse;
import org.editor.utils.JwtUtil;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.security.core.userdetails.User.UserBuilder;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    UserController userController;

    @MockBean
    UserService userService;

    @MockBean
    JwtUtil jwtUtil;

    @MockBean
    AuthenticationManager authenticationManager;

    @Test
    @DisplayName("Test loginSuccess")
    public void testLoginSuccess() throws Exception {
        User user = new User("1", "mock", "", "");
        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername("mock");
        userBuilder.password(user.getPassword());
        userBuilder.roles(user.getRole());

        doReturn(userBuilder.build()).when(userService).loadUserByUsername("mock");
        doReturn("1").when(jwtUtil).generateToken(userBuilder.build());
        doReturn(null).when(authenticationManager).authenticate(new UsernamePasswordAuthenticationToken("mock", ""));

        Assertions.assertEquals(ResponseEntity.ok(new JwtResponse("1")), userController.login(new JwtRequest("mock", "")));
    }

    @Test
    @DisplayName("Test loginFail")
    public void testLoginFail() throws Exception {
        doThrow(new BadCredentialsException("Mock")).when(authenticationManager).authenticate(new UsernamePasswordAuthenticationToken("mock", ""));

        Assertions.assertEquals(new ResponseEntity<>(new ErrorResponse("Bad Credentials."), HttpStatus.UNAUTHORIZED), userController.login(new JwtRequest("mock", "")));
    }

    @Test
    @DisplayName("Test registerSuccess")
    public void testRegisterSuccess() {
        User user = new User("1", "mock", "", "");
        doThrow(new UsernameNotFoundException(user.getUsername())).when(userService).loadUserByUsername("mock");
        Assertions.assertEquals(ResponseEntity.ok(userService.registerUser(user)), userController.register(user));
    }

    @Test
    @DisplayName("Test registerFail")
    public void testRegisterFail() {
        User user = new User("1", "mock", "", "");
        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername("mock");
        userBuilder.password(user.getPassword());
        userBuilder.roles(user.getRole());
        doReturn(userBuilder.build()).when(userService).loadUserByUsername("mock");

        Assertions.assertEquals(new ResponseEntity<>(new ErrorResponse("Username already exists."), HttpStatus.UNAUTHORIZED), userController.register(user));
    }

}
