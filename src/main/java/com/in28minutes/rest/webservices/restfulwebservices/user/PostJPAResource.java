package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PostJPAResource {
    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "/jpa/posts/{id}")
    public Optional<Post> getPostById (@PathVariable int id) {
        return postRepository.findById(id);
    }
}
