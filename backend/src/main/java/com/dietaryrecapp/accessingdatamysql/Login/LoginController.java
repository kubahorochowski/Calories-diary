package com.dietaryrecapp.accessingdatamysql.Login;

import com.dietaryrecapp.accessingdatamysql.users.User;
import com.dietaryrecapp.accessingdatamysql.users.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private UserService userService;
    private final long expirationTime;
    private final String secret;

    public LoginController(UserService userService, @Value("${jwt.expirationTime}") long expirationTime, @Value("${jwt.secret}") String secret) {
        this.userService = userService;
        this.expirationTime = expirationTime;
        this.secret = secret;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.addUser(user);
    }

}
