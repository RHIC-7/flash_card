package com.flush_card.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloWorld {
    @GetMapping("/test")
    public String getMethodName() {
        return "/hello";
    }
}
