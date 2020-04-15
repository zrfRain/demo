package com.example.demo.controller;


import com.example.demo.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(value="/test",tags="User的相关信息接口")
@RequestMapping("/api")

public class TestController {

    @GetMapping("/test")

    @ApiOperation(value="获取单个user",notes="获取单个user，需参数", httpMethod = "GET")
    public User test(){
        log.info("test");
        return new User("user","pwd",1);
    }


    @GetMapping("/test2")
    @ApiOperation(value="获取单个user",notes="获取单个user，需参数", httpMethod = "POST")
    public User test2(){
        log.info("test2");
        return new User("user","pwd",1);
    }

    @GetMapping("/test3")
    public User test3(){
        log.info("test3");
        return new User("user","pwd",1);
    }

    @GetMapping("/addUser")
    public User addUser(@Validated User user){
        return user;
    }


    public static void main(String[] args) {
        String test = "https://dollarcdn.cdollar.cn/zofund/图啊图123@#￥@#%.png";
        test = test.replace("%","%25")
                .replace("#","%23");
        System.out.println(test);

    }



}
