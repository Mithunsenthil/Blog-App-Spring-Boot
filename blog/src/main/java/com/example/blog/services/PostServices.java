package com.example.blog.services;

import com.example.blog.entities.Post;
import com.example.blog.repositories.PostRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServices {
    @Autowired
    PostRepositories postRepositories;
    public List<Post> getAllPost()
    {
        return postRepositories.findAll();
    }
    public Post addPost(Post post)
    {
        return postRepositories.save(post);
    }
    public Optional<Post> getbyId(Long idl){
        return postRepositories.findById(idl);
    }
    public void deletebyId(Long id){
        postRepositories.deleteById(id);
    }
}
