package com.example.pdf.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.pdf.demo.models.Post;
import com.example.pdf.demo.repositories.PostRepository;

@Controller
@RequestMapping("/thymeleaf")
public class DynamicController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("posts")
    public ModelAndView getPosts(){
        ModelAndView mav = new ModelAndView("list-posts");
        List<Post> posts = postRepository.findAll();
        mav.addObject("posts", posts);
        return mav;
    }

    @GetMapping("add-post")
    public ModelAndView getAddPostForm(){
        ModelAndView mav = new ModelAndView("add-post-form");
        Post newPost = new Post();
        mav.addObject("post", newPost);
        return mav;
    }

    @PostMapping("save-post")
    public String savePost(@ModelAttribute Post body){
        postRepository.save(body);
        return "redirect:/thymeleaf/posts";
    }

    @GetMapping("update-post")
    public ModelAndView getUpdatePostForm(@RequestParam int postId){
        ModelAndView mav = new ModelAndView("add-post-form");
        Post oldPost = postRepository.findById(postId).orElse(null);
        mav.addObject("post", oldPost);
        return mav;
    }

    @GetMapping("delete-post")
    public String deletePost(@RequestParam int postId){
        postRepository.deleteById(postId);
        return "redirect:/thymeleaf/posts";
    }

    
}
