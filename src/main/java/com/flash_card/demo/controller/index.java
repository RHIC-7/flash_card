package com.flash_card.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class index {

    @RequestMapping("/")
    public String requestMethodName() {
        return "redirect:/login";
    }
}
