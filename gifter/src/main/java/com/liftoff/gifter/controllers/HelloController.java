package com.liftoff.gifter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//TODO: turn this into home controller. Landing page, unless user is logged in, then redirect to dashboard.

@Controller
public class HelloController {
    @GetMapping
    @ResponseBody
    public String hello() {
        return "Hello, world!";
    }
}
