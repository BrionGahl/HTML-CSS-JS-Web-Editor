package org.editor.user;

import org.editor.model.ErrorResponse;
import org.editor.model.JwtRequest;
import org.editor.model.JwtResponse;
import org.editor.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "api/v1/auth/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest) throws Exception {
        logger.info(jwtRequest.toString());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (Exception e) {
            logger.error("Bad Credentials");
            return new ResponseEntity<>(new ErrorResponse("Bad Credentials."), HttpStatus.UNAUTHORIZED);
        }

        UserDetails user = userService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtil.generateToken(user);
        logger.info("Token: " + token);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "api/v1/auth/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            userService.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.ok(userService.registerUser(user));
        }
        return new ResponseEntity<>(new ErrorResponse("Username already exists."), HttpStatus.UNAUTHORIZED);
    }
}
