package com.houkai.eurekaserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost/getHi
 */
@RestController
public class MainComtroller {

    @Value("${server.port}")
    String port;
    @GetMapping("/getHi")
    public String getHi(){
        return "Hi,im: "+port;
    }
}
