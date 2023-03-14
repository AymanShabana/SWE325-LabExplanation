package com.example.pdf.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.pdf.demo.models.Post;
import com.example.pdf.demo.repositories.PostRepository;

@Controller
public class StaticController {
    @GetMapping("hello")
    public String getIndex(){
        return "index.html";
    }
    @GetMapping("goodbye")
    public String getGoodbye(){
        return "subfolder/goodbye.html";
    }

}
