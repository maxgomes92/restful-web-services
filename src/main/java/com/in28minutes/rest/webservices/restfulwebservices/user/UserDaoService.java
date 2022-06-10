package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {
    private static final List<User> users = new ArrayList<User>();

    static {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Eve", new Date()));
        users.add(new User(3, "Jack", new Date()));
    }

    public List<User> findAll () {
        return users;
    }

    public User save(User user) {
        user.setId(users.size()+1);
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        return users
                .stream()
                .filter((User user) -> user.getId() == id)
                .findAny()
                .orElse(null);
    }
}
