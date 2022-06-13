package com.in28minutes.rest.webservices.restfulwebservices.user;

public class Post {
    int id;
    String message;
    User createdBy;


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
