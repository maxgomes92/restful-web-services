package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User createdBy;

    public Post() {}

    public Post(int id, String message, User createdBy) {
        this.id = id;
        this.message = message;
        this.createdBy = createdBy;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", createdBy=" + createdBy +
                '}';
    }
}
