package com.dakotawild.streetsmart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository repository;
    
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            users = userRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @PostMapping("/user")
    public User postUser(@RequestBody User user) {
 
      User _user = repository.save(new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword()));
      return _user;
    }

}
