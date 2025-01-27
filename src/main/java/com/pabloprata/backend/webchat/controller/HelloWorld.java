package com.pabloprata.backend.webchat.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("hello")
public class HelloWorld {
    
    @GetMapping
    public String helloWorld() {
        return "Hello World!";
    
    }
}