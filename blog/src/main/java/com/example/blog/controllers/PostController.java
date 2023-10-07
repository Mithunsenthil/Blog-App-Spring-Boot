package com.example.blog.controllers;

import com.example.blog.entities.Post;
import com.example.blog.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    private PostServices postServices;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping({"/allposts","/list"})
    public String allPost(Model model) {
        List<Post> list=postServices.getAllPost();
        model.addAttribute("posts",list);
        return "allposts";
    }

    @GetMapping("/publish")
    public String publish(Model model, Post post) {
        post.setDate_created(LocalDate.now());
        model.addAttribute("posts",post);
        return "publish";
    }
    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        Optional<Post> optionalPost= postServices.getbyId(id);
        if (optionalPost.isPresent()) {
            Post post=optionalPost.get();
            model.addAttribute("post",post);
            return "view";
        }
        return "404";
    }
    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id)
    {
        Optional<Post> optionalPost= postServices.getbyId(id);
        if (optionalPost.isPresent()) {
            Post post=optionalPost.get();
            postServices.deletebyId(id);
            return "404";
        }
        return "404";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id,Model model)
    {
        Optional<Post> optionalPost= postServices.getbyId(id);
        if (optionalPost.isPresent()) {
            Post oldpost=optionalPost.get();
            model.addAttribute("post",oldpost);
            postServices.deletebyId(oldpost.getId());
            return "edit";
        }
        return "404";
    }

    @PostMapping("/save")
    public String save(Model model,Post post) {
        Post result=postServices.addPost(post);
        return "publish";
    }
}
