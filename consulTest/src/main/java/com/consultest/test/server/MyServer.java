package com.consultest.test.server;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MyServer {
    @Autowired
    @Qualifier(value = "remoteRestTemplate")
    private RestTemplate mRestTemplate;

    public String nameServiceForOut(String name) {
        try {

            String response = mRestTemplate.exchange("http://service-producer/service/{name}",
                    HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                    }, name).getBody();
            System.out.println("xyz  响应为： " + response);
            return response;
        } catch (Exception e) {
            System.out.println("这是 consulTest 的错误！");
            throw e;
        }
    }


    @Bean(name = "remoteRestTemplate")
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
