package com.smlteam.textsimilarity.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String home(){
        return "index";
    }

    @RequestMapping(value = "/about")
    public String about(){
        return "about";
    }

    @RequestMapping(value = "/help")
    public String help(){
        return "help";
    }

    @RequestMapping(value = "/progress")
    public String progress(){
        return "progress";
    }

    @RequestMapping(value = "/result")
    public String result(){
        return "result";
    }
}
