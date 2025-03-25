package com.example.meu_primeiro_projeto.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/info")
public class TesteEndpointController {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.author}")
    private String appAuthor;

    private String localDateTime = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

    @GetMapping
    public Map<String, String> getAppInfo(){
        Map<String, String> response = new HashMap<>();
        response.put("name", appName);
        response.put("version", appVersion);
        response.put("author", appAuthor);
        response.put("data e hora", localDateTime);
        return response;
    }
}
