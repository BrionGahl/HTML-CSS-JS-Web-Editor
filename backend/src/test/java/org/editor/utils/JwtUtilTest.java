package org.editor.utils;

import org.editor.user.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtUtilTest {

    @Autowired
    JwtUtil jwtUtil;

    @Test
    @DisplayName("Test generateToken")
    public void testGenerateToken() {
        User user = new User("1", "mock", "", "");
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername("mock");
        userBuilder.password(user.getPassword());
        userBuilder.roles(user.getRole());

        Assertions.assertEquals(jwtUtil.generateToken(userBuilder.build()), jwtUtil.generateToken(userBuilder.build()));

    }

}
