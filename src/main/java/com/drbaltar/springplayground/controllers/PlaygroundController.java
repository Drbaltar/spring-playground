package com.drbaltar.springplayground.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaygroundController {

    @PostMapping("/some/path")
    public Person getJSONString(@RequestBody Person person) {
        return person;
    }

    record Person(String name, Pet pet) {
    }

    record Pet(String name, String color) {}

}
