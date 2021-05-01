package com.example.songr.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

@Controller
public class GeneralController {

    //splash screen
    @GetMapping("/")
    public String getSplash(){
        return "splash.html";
    }
    @GetMapping("/hello")
    public String getHello(){
        return "hello.html";
    }

    @GetMapping("/capitalize/{text}")
    @ResponseBody
    public String getCapitalText(@PathVariable(value = "text") String text){
        return text.toUpperCase();
    };

    @GetMapping("/error")
    public String getErrorPage(){
        return "error.html";
    }
}
