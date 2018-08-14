package com.bengmall.consumer1.web;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//http://benjious-pc:8503//actuator/health
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello consul";
    }

    /***
     *   这是对外提供的服务
     * @param name
     * @return
     */
    @RequestMapping("/service/{name}")
    public String serviceForOut(@PathVariable("name") String name){
        return "This is server,your name is "+name;
    }



}
