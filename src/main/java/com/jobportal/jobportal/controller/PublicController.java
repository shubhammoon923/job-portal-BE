package com.jobportal.jobportal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping("/public/hello")
    public String publicHello() {
        return "This is a public endpoint.";
    }

    @GetMapping("/secure/hello")
    public String secureHello() {
        return "This is a secured endpoint.";
    }
}
