package com.example.pdf.demo.controllers;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pdf.demo.dtos.PostDto;
import com.example.pdf.demo.models.Post;
import com.example.pdf.demo.repositories.PostRepository;
import com.example.pdf.demo.services.LoggingService;


// This code defines a RESTful API controller for posts. 
// The @RestController and @RequestMapping annotations are used to define the base URL endpoint for this controller, 
// which is "/posts". The ArrayList<Post> posts variable is used to store a list of posts.
@RestController
@RequestMapping("/posts")
public class PostController {


    @Autowired
    private LoggingService loggingService;

    @Autowired
    private PostRepository postRepository;


    // This method handles GET requests to the "/posts" endpoint 
    // and returns a ResponseEntity object containing the list of posts 
    // and an HTTP status of 200 OK.
    @GetMapping("")
    public ResponseEntity getPosts(@RequestParam(required = false, name = "search") String search,
    @PageableDefault(size = 20) Pageable pageable){
        loggingService.log("User fetched all posts");
        if(search == null)
            return new ResponseEntity(postRepository.findAll(pageable), HttpStatus.OK);
        return new ResponseEntity(postRepository.getPostsThatHasThisAsASubstringNative(search), HttpStatus.OK);
    }

    // This method handles GET requests to the "/posts/{postId}" endpoint 
    // and takes a path variable postId to specify which post to return. 
    // It loops through the list of posts and returns the post with the specified ID if it exists,
    // along with an HTTP status of 200 OK. 
    // If no post is found, it returns an HTTP status of 404 Not Found.
    @GetMapping("{postId}")
    public ResponseEntity getPost(@PathVariable("postId") Integer postId){
        Post res = postRepository.findById(postId).get();
        if(res != null)
            return new ResponseEntity(res, HttpStatus.OK);
        return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
    }

    // This method handles POST requests to the "/posts" endpoint 
    // and takes a request body containing a Map with a "caption" field.
    // It creates a new Post object with a generated ID and the caption from the request body, 
    // adds it to the list of posts, and returns a ResponseEntity object 
    // containing the new post and an HTTP status of 201 Created.
    @PostMapping("")
    public ResponseEntity createPost(@RequestBody PostDto body
    ){
        Post res = new Post(body.getCaption(), body.getType());
        postRepository.save(res);
    
        return new ResponseEntity(res, HttpStatus.CREATED);
    }

    // This method handles PUT requests to the "/posts/{postId}" endpoint 
    // and takes a path variable postId to specify which post to update, 
    // and a request body containing a Map with a "caption" field. 
    // It loops through the list of posts and updates the post with the specified ID if it exists, 
    // setting its caption to the value from the request body. 
    // It returns a ResponseEntity object containing the updated post and an HTTP status of 200 OK. 
    // If no post is found, it returns an HTTP status of 404 Not Found.
    @PutMapping("{postId}")
    public ResponseEntity updatePost(@PathVariable("postId") Integer postId, @RequestBody Map<String,String> body){
        Post res = postRepository.findById(postId).get();
        if(res == null)
            return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
        res.setCaption(body.get("caption"));
        postRepository.save(res);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    // This method handles DELETE requests to the "/posts/{postId}" endpoint 
    // and takes a path variable postId to specify which post to delete. 
    // It loops through the list of posts and removes the post with the specified ID if it exists.
    // It returns a ResponseEntity object containing a message saying the post was deleted 
    // and an HTTP status of 200 OK. If no post is found, it returns an HTTP status of 404 Not Found.
    @DeleteMapping("{postId}")
    public ResponseEntity deletePost(@PathVariable("postId") Integer postId){
        Post toBeDeleted = postRepository.findById(postId).get();
        if(toBeDeleted == null){
            return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
        }
        postRepository.delete(toBeDeleted);
        return new ResponseEntity("Deleted", HttpStatus.OK);
    }

}
