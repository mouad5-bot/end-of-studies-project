package com.youcode.come2play.service.impl;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/open")
public class OpenApi {

    @GetMapping
    public String athnna() {return "its an open api";}
}
