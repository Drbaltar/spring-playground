package com.drbaltar.springplayground;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String helloWorld(@RequestParam(value = "name", defaultValue = "") String name) {
        return "Hello " + (name.equals("") ? name : name + " ") + "from Spring!";
    }

    @GetMapping("/math/pi")
    public String getPi() {
        return String.valueOf(Math.PI);
    }
}
