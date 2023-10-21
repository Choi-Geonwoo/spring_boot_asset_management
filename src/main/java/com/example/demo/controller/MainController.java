package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
public class MainController {
    
    @GetMapping("main")
    public String main(){
        return "main/main";
    }

}
