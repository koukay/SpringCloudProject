package com.houkai.eurekaserver;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class MainComtroller2 {
    @Autowired
    EurekaClient  client2;
    @Autowired
    DiscoveryClient client;

    @Autowired
    LoadBalancerClient lb;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/client6")
    public String client6(){
        // ribbon 完成客户端的负载均衡，过滤掉down了的节点
        String url="http://provider/getHi";
        String respStr = restTemplate.getForObject(url, String.class);
        System.out.println(respStr);
        return respStr;
    }
    @GetMapping("/client7")
    public String client7(){
        List<ServiceInstance> provider = client.getInstances("provider");
        //自定义轮询算法

        //随机
        int i = new Random().nextInt(provider.size());
        AtomicInteger atomicInteger = new AtomicInteger();
        //轮询
        int i1 = atomicInteger.getAndIncrement();
        provider.get(i1 %  provider.size());

        //  权重
        for (ServiceInstance serviceInstance : provider) {
            Map<String, String> metadata = serviceInstance.getMetadata();
        }

        ServiceInstance serviceInstance = provider.get(i);
        String url ="http://" + serviceInstance.getHost() +":"+ serviceInstance.getPort() + "/getHi";
//        String url ="http://ek2.com:80/getHi";
        System.out.println("=====================url: "+url);
        System.out.println("---------");
        String respStr = restTemplate.getForObject(url, String.class);
        return respStr;

    }

    @GetMapping("/client8")
    public String client8(){
        // ribbon 完成客户端的负载均衡，过滤掉down了的节点
        ServiceInstance instance = lb.choose("provider");
//        String url="http://ek2.com:90/getHi";
        String url="http://"+instance.getHost()+":"+instance.getPort()+"/getHi";
        String respStr = restTemplate.getForObject(url, String.class);
        System.out.println(respStr);
        return respStr;
    }
    @GetMapping("/client9")
    public String client9(){
        ServiceInstance provider1 = lb.choose("provider");
        URI uri = provider1.getUri();
        String url=uri+"/getHi";
        String respStr = restTemplate.getForObject(url, String.class);
        System.out.println(respStr);
        return respStr;
    }
}
