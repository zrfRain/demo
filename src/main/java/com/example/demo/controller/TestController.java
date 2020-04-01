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


    @GetMapping("/test2")
    public User test2(){
        log.info("test2");
        return new User("user","pwd");
    }

    @GetMapping("/test3")
    public User test3(){
        log.info("test3");
        return new User("user","pwd");
    }



}
