package com.example.meu_primeiro_projeto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testeurl")
public class HelloController {

    @GetMapping
    public String hello(){
        return "Hello, Spring Boot!";
    }
}
