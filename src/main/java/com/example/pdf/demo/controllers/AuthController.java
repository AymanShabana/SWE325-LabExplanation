package com.example.pdf.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.pdf.demo.models.User;
import com.example.pdf.demo.repositories.UserRepository;
import com.example.pdf.demo.utils.JwtUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("login")
    public ResponseEntity generateToken(@RequestBody Map<String, String> body){
        User user = this.userRepository.findByUsername(body.get("username")).get();
        if(user == null){
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
        }
        boolean matches = passwordEncoder.matches(body.get("password"), user.getPassword());
        if(!matches){
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
        }
        String token = this.jwtUtils.generateToken(user.getId());
        Map<String,String> res = new HashMap<>();
        res.put("token", token);
        return new ResponseEntity(res ,HttpStatus.OK);
    }   

    @PostMapping("register")
    public ResponseEntity register(@RequestBody Map<String, String> body){
        User user =  new User();
        user.setUsername(body.get("username"));
        String encodedPassword = passwordEncoder.encode(body.get("password"));
        user.setPassword(encodedPassword);
        this.userRepository.save(user);
        String token = this.jwtUtils.generateToken(user.getId());
        Map<String,String> res = new HashMap<>();
        res.put("token", token);
        return new ResponseEntity(res ,HttpStatus.OK);
    }   
}
