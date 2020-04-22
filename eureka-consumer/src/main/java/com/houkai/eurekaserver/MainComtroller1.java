package com.houkai.eurekaserver;

import com.houkai.ConsumerApi;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
public class MainComtroller1 {
    @Autowired
    EurekaClient  client2;
    @Autowired
    DiscoveryClient client;

    @Autowired
    LoadBalancerClient lb;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${server.port}")
    String port;

    @GetMapping("/alive")
    public String alive() {

        return "Eureka Consumer:" + port + "->>>>";
    }
    @GetMapping("/getHi")
    public String getHi(){
        return "Hi,im 90";
    }

    @GetMapping("/client")
    public String client(){
        List<String> services = client.getServices();
        for (String service : services) {
            System.out.println(service);
        }
        return "Hi i am client 90";
    }

    @GetMapping("/client2")
    public Object client2(){
        return client.getInstances("provider");
    }
    @GetMapping("/client3")
    public String client3(){
        List<ServiceInstance> services = client.getInstances("provider");
        for (ServiceInstance service : services) {
            System.out.println(ToStringBuilder.reflectionToString(service));
        }
        return "client3";
    }
    @GetMapping("/client4")
    public String client4(){
        // 具体服务
//        List<InstanceInfo> provider = client2.getInstancesById("ek2.com:provider:80");
        // 使用服务名 ，找列表
        List<InstanceInfo> provider = client2.getInstancesByVipAddress("provider", false);
        for (InstanceInfo instanceInfo : provider) {
            System.out.println(ToStringBuilder.reflectionToString(instanceInfo));
        }
        String respStr="";
        if (provider.size()>0){
            //服务
            InstanceInfo instanceInfo = provider.get(0);
            //判断服务状态
            if (instanceInfo.getStatus()== InstanceInfo.InstanceStatus.UP){
                System.out.println("instanceInfo.getHomePageUrl(): "+instanceInfo.getHomePageUrl());
                String url =instanceInfo.getHomePageUrl() +"/getHi";
                RestTemplate restTemplate1= new RestTemplate();
                respStr = restTemplate1.getForObject(url, String.class);
            }
        }
        return respStr;
    }
    @GetMapping("/client5")
    public String client5(){
        // ribbon 完成客户端的负载均衡，过滤掉down了的节点
        ServiceInstance provider1 = lb.choose("provider");
        URI uri = provider1.getUri();
        String url=uri+"/getHi";
        RestTemplate restTemplate1= new RestTemplate();
        String respStr = restTemplate1.getForObject(url, String.class);
        System.out.println(respStr);
        return respStr;
    }
}
