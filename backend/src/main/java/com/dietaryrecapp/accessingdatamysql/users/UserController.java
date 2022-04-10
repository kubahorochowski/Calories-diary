package com.dietaryrecapp.accessingdatamysql.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(path="/users")
public class UserController {

    private final UserService userService;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping(path="/user/{user_id}")
    public @ResponseBody
    Optional<User> getAllUser(@PathVariable Integer user_id) {
        return userService.getUserById(user_id);
    }

    @PutMapping(path="/update/{user_id}")
    public ResponseEntity<Object> editUser(@RequestBody User user, @PathVariable Integer user_id) {
        User editedUser = userService.editUser(user ,user_id);
        return new ResponseEntity<>(editedUser, HttpStatus.OK);
    }
}

