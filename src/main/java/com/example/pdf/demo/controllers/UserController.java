package com.example.pdf.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pdf.demo.models.User;
import com.example.pdf.demo.repositories.UserRepository;

// This code defines a RESTful API controller for users. 
// The @RestController and @RequestMapping annotations are used to define the base URL endpoint for this controller, 
// which is "/users". The ArrayList<User> users variable is used to store a list of users.
@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    // This method handles GET requests to the "/users" endpoint 
    // and returns a ResponseEntity object containing the list of users 
    // and an HTTP status of 200 OK.
    @GetMapping("")
    public ResponseEntity getUsers(){
        List<User> users = this.userRepository.findAll();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    // This method handles GET requests to the "/users/{userId}" endpoint 
    // and takes a path variable userId to specify which user to return. 
    // It loops through the list of users and returns the user with the specified ID if it exists,
    // along with an HTTP status of 200 OK. 
    // If no user is found, it returns an HTTP status of 404 Not Found.
    @GetMapping("{userId}")
    public ResponseEntity getUser(@PathVariable("userId") String userId){
        User user = this.userRepository.findById(userId).get();
        return new ResponseEntity(user, HttpStatus.OK);
    }

    // This method handles POST requests to the "/users" endpoint 
    // and takes a request body containing a Map with a "name" field.
    // It creates a new User object with a generated ID and the name from the request body, 
    // adds it to the list of users, and returns a ResponseEntity object 
    // containing the new user and an HTTP status of 201 Created.
    @PostMapping("")
    public ResponseEntity createUser(@RequestBody Map<String,String> body){
        User user = new User();
        user.setUsername(body.get("username"));
        user.setPassword(body.get("password"));
        this.userRepository.save(user);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }


    // This method handles PUT requests to the "/users/{userId}" endpoint 
    // and takes a path variable userId to specify which user to update, 
    // and a request body containing a Map with a "name" field. 
    // It loops through the list of users and updates the user with the specified ID if it exists, 
    // setting its name to the value from the request body. 
    // It returns a ResponseEntity object containing the updated user and an HTTP status of 200 OK. 
    // If no user is found, it returns an HTTP status of 404 Not Found.
    @PutMapping("{userId}")
    public ResponseEntity updateUser(@PathVariable("userId") String userId, @RequestBody Map<String,String> body){
        return new ResponseEntity("ok", HttpStatus.OK);
    }

    // This method handles DELETE requests to the "/users/{userId}" endpoint 
    // and takes a path variable userId to specify which user to delete. 
    // It loops through the list of users and removes the user with the specified ID if it exists.
    // It returns a ResponseEntity object containing a message saying the user was deleted 
    // and an HTTP status of 200 OK. If no user is found, it returns an HTTP status of 404 Not Found.
    @DeleteMapping("{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") String userId){
        return new ResponseEntity("ok", HttpStatus.OK);
    }

}
