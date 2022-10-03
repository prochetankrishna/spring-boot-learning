package com.hertzbit.springbootlearning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MySpringController {

    @GetMapping("/greetUser")
    public String greetUser() {
        return "<html>" +
                "<head>" +
                "</head>" +
                "<body>" +
                "<h1>Hello from Spring Boot!</h1>" +
                "</body>" +
                "</html>";
    }
}
