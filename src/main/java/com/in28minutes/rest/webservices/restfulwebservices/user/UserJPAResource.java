package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAResource {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostDaoService postDaoService;

    @GetMapping(path = "/jpa/users")
    public List<User> retrieveAllUsers () {
        return userRepository.findAll();
    }

    @GetMapping(path = "/jpa/users-without-ids")
    public MappingJacksonValue retrieveAllUsersWithoutIds () {
        var users = userRepository.findAll();

        var filter = SimpleBeanPropertyFilter.filterOutAllExcept("name");
        var filters = new SimpleFilterProvider().addFilter("UsersWithoutIds", filter);
        var mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<Optional<User>> retrieveUser(@PathVariable int id) {
        var user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User id " + id + " was not found.");
        }

        var model = EntityModel.of(user);

        var linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkToUsers.withRel("all-users"));

        return model;
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        var saved = userRepository.save(user);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> getPosts(@PathVariable int id) {
        return postDaoService.getPosts(id);
    }

    @PostMapping(path = "/jpa/users/{id}/posts")
    public Post createPost(@PathVariable int id, @RequestBody String message) {
        var user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found " + id);
        }

        return postDaoService.createPostByUser(user, message);
    }

    @GetMapping(path = "/jpa/users/{userId}/posts/{postId}")
    public Post getPostFromUser (@PathVariable int userId, @PathVariable int postId) {
        return postDaoService.getPostById(userId, postId);
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUserById (@PathVariable int id) {
        userRepository.deleteById(id);
    }
}
