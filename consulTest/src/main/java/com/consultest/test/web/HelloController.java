package com.consultest.test.web;

import com.consultest.test.server.MyServer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private MyServer mMyServer;

    @RequestMapping("/")
    public String hello(){
        return "hello consul two ";
    }

    //http://localhost:8502/hello/getFromOtherServer/Mike
    //http://localhost:8502/actuator/hystrix.stream
    //http://localhost:9001/hystrix.stream

    @RequestMapping("/getFromOtherServer/{name}")
    @HystrixCommand(fallbackMethod = "dealFailRequest")
    public String getResponse(@PathVariable("name")String name){
        return mMyServer.nameServiceForOut(name);
    }

    public String dealFailRequest(String name){
        return "the request is failed and this is your default name : "+ name;
    }



}