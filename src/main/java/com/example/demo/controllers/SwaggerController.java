package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwaggerController {
    @RequestMapping(method = RequestMethod.GET, value = "/api/j")
    public String swagger() {
        return "Swagger";
    }
}
