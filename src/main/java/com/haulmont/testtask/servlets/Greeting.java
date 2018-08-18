package com.haulmont.testtask.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Greeting {

    @RequestMapping("greeting")
    String home() {
        return "Hello World!";
    }
}