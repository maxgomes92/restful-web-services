package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MyController {

    @GetMapping(path = "/")
    public ArrayList<String> welcome () {
        var list = new ArrayList<String>();
        list.add("Salve!");

        return list;
    }
}
