package com.liftoff.gifter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("gifter")
public class HomeController {

    @GetMapping
    public String index() {
        return "index";
    }
}
