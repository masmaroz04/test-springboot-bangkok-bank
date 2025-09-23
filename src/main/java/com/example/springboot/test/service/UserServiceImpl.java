package com.example.springboot.test.service;

import com.example.springboot.test.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public List<User> getUsersFromUrl() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/users";
        User[] users = restTemplate.getForObject(url, User[].class);
        return Arrays.asList(users);
    }
}
