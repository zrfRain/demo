package com.example.demo.controller;


import com.example.demo.VO.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @GetMapping("/test")
    public User test(){
        log.info("test");
        return new User("user","pwd");
    }





}
