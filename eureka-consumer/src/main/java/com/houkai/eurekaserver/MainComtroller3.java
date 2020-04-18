package com.houkai.eurekaserver;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class MainComtroller3 {
    @Autowired
    EurekaClient  client2;
    @Autowired
    DiscoveryClient client;

    @Autowired
    LoadBalancerClient lb;
    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/client10")
    public String client10(){
        // ribbon 完成客户端的负载均衡，过滤掉down了的节点
        ServiceInstance provider1 = lb.choose("provider");
        URI uri = provider1.getUri();
        String url=uri+"/getHi";
        String respStr = restTemplate.getForObject(url, String.class);
        System.out.println(respStr);
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        System.out.println("--"+forEntity.toString());
        return respStr;
    }
    @GetMapping("/client11")
    public Object client11(){
        // ribbon 完成客户端的负载均衡，过滤掉down了的节点
        ServiceInstance provider1 = lb.choose("provider");
        URI uri = provider1.getUri();
        String url=uri+"/getMap";
        Map forObject = restTemplate.getForObject(url, Map.class);
        System.out.println("--forObject"+forObject.toString());
        return forObject;
    }
    @GetMapping("/client12")
    public Object client12(){
        // ribbon 完成客户端的负载均衡，过滤掉down了的节点
        ServiceInstance provider1 = lb.choose("provider");
        URI uri = provider1.getUri();
        String url=uri+"/getMap2";
        Map forObject = restTemplate.getForObject(url, Map.class);
        System.out.println("--forObject"+forObject.toString());
        return forObject;
    }
    @GetMapping("/client13")
    public Object client13(){
        // ribbon 完成客户端的负载均衡，过滤掉down了的节点
        ServiceInstance provider1 = lb.choose("provider");
        URI uri = provider1.getUri();
        String url=uri+"/postParam";
        Map<String, String> map = Collections.singletonMap("name", " memeda");
        Person forObject = restTemplate.postForObject(url,map, Person.class);
        System.out.println("--forObject"+forObject.toString());
        return forObject;
    }
    @GetMapping("/client14")
    public Object client14(){
        // ribbon 完成客户端的负载均衡，过滤掉down了的节点
        ServiceInstance provider1 = lb.choose("provider");
        URI uri = provider1.getUri();
        String url=uri+"/getObj";
        Person forObject = restTemplate.getForObject(url, Person.class);
        System.out.println("--forObject"+forObject.toString());
        return forObject;
    }
    @GetMapping("/client15")
    public Object client15(){
        // ribbon 完成客户端的负载均衡，过滤掉down了的节点
        ServiceInstance provider1 = lb.choose("provider");
        URI uri = provider1.getUri();
        String url=uri+"/getObj2?name={1}";
        Person forObject = restTemplate.getForObject(url, Person.class,"dabao");
        System.out.println("--forObject"+forObject.toString());
        return forObject;
    }
    @GetMapping("/client16")
    public Object client16(){
        // ribbon 完成客户端的负载均衡，过滤掉down了的节点
        ServiceInstance provider1 = lb.choose("provider");
        URI uri = provider1.getUri();
        String url=uri+"/postLocation";
        Map<String, String> stringStringMap = Collections.singletonMap("name", "houkaikai");
        URI forObject = restTemplate.postForLocation(url,stringStringMap,Person.class);
        System.out.println("--forObject"+forObject.toString());
        return forObject;
    }
    /*@GetMapping("/client17")
    public Object client17(){
        // ribbon 完成客户端的负载均衡，过滤掉down了的节点
        ServiceInstance provider1 = lb.choose("provider");
        URI uri = provider1.getUri();
        String url=uri+"/health";
        Map<String, Object> stringStringMap = Collections.singletonMap("name", false);
        String forObject = restTemplate.getForObject(url,Map.class,stringStringMap);
        System.out.println("--forObject"+forObject);
        return forObject;
    }*/
}
