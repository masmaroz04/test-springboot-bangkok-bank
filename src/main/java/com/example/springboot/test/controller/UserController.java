package com.example.springboot.test.controller;

import com.example.springboot.test.model.User;
import com.example.springboot.test.service.UserService;
import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUser() {
        logger.info("process getUser");
        return  userService.getUsersFromUrl();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<User>> getUserSpecific(@PathVariable Long userId) {
        logger.info("start getUserSpecific");
        List<User> userList = userService.getUsersFromUrl();

        logger.info("userId : {}", userId);
        userList = userList.stream()
                .filter(data -> data.getId() == userId)
                .toList();

        if (userList.size() == 0 || userList == null) {
            logger.error("userList not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("end getUserSpecific");
        return ResponseEntity.ok(userList);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("process createUser");
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<List<User>> updateUser(@PathVariable Long userId, @RequestBody User user) {
        logger.info("start updateUser");
        List<User> userList = userService.getUsersFromUrl();

        User found = null;
        for (User data : userList) {
            if (data.getId() == userId) {
                data.setName(user.getName());
                data.setUsername(user.getUsername());
                data.setEmail(user.getEmail());
                data.setPhone(user.getPhone());
                data.setWebsite(user.getWebsite());
                found = data;
                break;
            }
        }

        if (found == null) {
            logger.error("userList not found");
            return ResponseEntity.notFound().build();
        }

        logger.info("end updateUser");
        return ResponseEntity.ok(userList);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable Long userId) {
        logger.info("start deleteUser");
        List<User> userList = userService.getUsersFromUrl();

        userList = userList.stream().filter(
                data -> data.getId() != userId
        ).toList();

        if (userList.size() == 0 || userList == null) {
            logger.error("userList not found");
            return ResponseEntity.notFound().build();
        }

        logger.info("end deleteUser");
        return ResponseEntity.ok(userList);
    }
}
