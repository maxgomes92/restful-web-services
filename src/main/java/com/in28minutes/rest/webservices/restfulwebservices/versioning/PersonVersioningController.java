package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
    // URI versioning
    @GetMapping("/v1/param")
    public PersonV1 personV1 () {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/v2/param")
    public PersonV2 personV2 () {
        return new PersonV2("Bob", "Charlie");
    }

    // Request parameter versioning
    @GetMapping(value="/person/param", params="version=1")
    public PersonV1 personV1Param () {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value="/person/param", params="version=2")
    public PersonV2 personV2Param () {
        return new PersonV2("Bob", "Charlie");
    }

    // Header versioning
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 personV1header () {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 personV2header () {
        return new PersonV2("Bob", "Charlie");
    }

    // Accept header or mime type versioning
    @GetMapping(value = "/person/produces", produces = "application/my.app-v1+json")
    public PersonV1 personV1produces () {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person/produces", produces = "application/my.app-v2+json")
    public PersonV2 personV2produces () {
        return new PersonV2("Bob", "Charlie");
    }
}
