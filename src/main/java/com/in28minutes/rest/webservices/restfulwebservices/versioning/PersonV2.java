package com.in28minutes.rest.webservices.restfulwebservices.versioning;

public class PersonV2 {
    private Name name;

    public PersonV2(String firstName, String lastName) {
        this.name = new Name(firstName, lastName);
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
