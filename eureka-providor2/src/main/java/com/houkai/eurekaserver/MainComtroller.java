package com.houkai.eurekaserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * http://localhost/getHi
 */
@RestController
public class MainComtroller {
    @Value("${server.port}")
    String port;
    
    @GetMapping("/getHi")
    public String getHi(){
        return "Hi,im:  "+port;
    }
    @GetMapping("/getMap")
    public Map<String, String> getMap() {

        return Collections.singletonMap("id", "100");
    }
    @GetMapping("/getMap2")
    public Map<String, String> getMap2() {

        HashMap<String, String> map = new HashMap<>();
        map.put("name", "500");
        return map;
    }
    @PostMapping("/postParam")
    public Person postParam(@RequestBody String name) {

        System.out.println("name:" + name);

        Person person = new Person();
        person.setId(500);
        person.setName("houkai" + name);
        return person;
    }
    @GetMapping("/getObj")
    public Person getObj() {
        Person person = new Person(100,"houkai");
        return person;
    }

    @GetMapping("/getObj2")
    public Person getObj2(String name) {
        Person person = new Person(40000,name);
        return person;
    }
    @PostMapping("/postLocation")
    public URI postParam(@RequestBody Person person, HttpServletResponse response) throws Exception {

        URI uri = new URI("https://www.baidu.com/s?wd="+person.getName().trim());

        //	response.addHeader("Location", uri.toString());

        return uri;

    }






    @Autowired
    HealthStatusService hsrv;

    @GetMapping("/health")
    public String health(@RequestParam("status") Boolean status) {

        hsrv.setStatus(status);
        return hsrv.getStatus();
    }
}
