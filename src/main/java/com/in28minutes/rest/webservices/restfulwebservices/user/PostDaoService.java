package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PostDaoService {
    public static final List<Post> posts = new ArrayList<>();

    public List<Post> getPosts (int userId) {
        System.out.println(posts.size());
        return posts.stream().filter(post -> post.createdBy.getId() == userId).toList();
    }

    public Post createPostByUser (User user, String message) {
        var post = new Post(posts.size(), message, user);

        posts.add(post);

        return post;
    }

    public Post getPostById (int userId, int postId) {
        return posts
                .stream()
                .filter(post -> post.createdBy.getId() == userId)
                .filter(post -> post.getId() == postId).findAny().orElse(null);
    }
}
