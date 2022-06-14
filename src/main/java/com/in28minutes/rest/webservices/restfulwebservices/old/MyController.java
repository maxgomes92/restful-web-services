package com.in28minutes.rest.webservices.restfulwebservices.old;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
public class MyController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/")
    public ArrayList<String> home () {
        var list = new ArrayList<String>();
        list.add("Salve!");

        return list;
    }

    @GetMapping(path = "/i18n")
    public String i18n () {
        return messageSource.getMessage("greeting.message", null, LocaleContextHolder.getLocale());
    }

    @GetMapping(path = "/bean")
    public MyBean bean () {
        return  new MyBean("Bean");
    }

    @GetMapping(path = "welcome/{name}")
    public String welcome (@PathVariable String name) {
        var letters = name.split("");
        var sorted = Arrays.stream(letters).sorted().collect(Collectors.joining("-"));

        return "Welcome " + sorted;
    }
}
