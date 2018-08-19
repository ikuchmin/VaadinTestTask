package com.haulmont.testtask.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("rest")
public class Greeting {

    @RequestMapping("hello")
    String home() {
        return "Hello World!";
    }
}