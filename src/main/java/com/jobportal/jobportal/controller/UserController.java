package com.jobportal.jobportal.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/")
    public String greetings(HttpServletRequest request){
        return "Welcome to Jobsy" +request.getSession().getId();
    }
}
