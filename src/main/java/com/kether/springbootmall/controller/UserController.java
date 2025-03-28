package com.kether.springbootmall.controller;

import com.kether.springbootmall.dto.UserRequest;
import com.kether.springbootmall.model.User;
import com.kether.springbootmall.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRequest userRequest) {
        Integer id = userService.register(userRequest);
        User user = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
