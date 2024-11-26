package com.example.demoGbHomework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonController {
    @GetMapping("/people")
    public String person(){
        return "person";
    }
}
