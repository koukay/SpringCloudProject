/*
package com.houkai.eurekaserver;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

*/
/**
 * 自定义ribbon配置类
 *//*

@ExcludeFromComponetScan
@Configuration
public class RibbonConfig {
    @Autowired
    IClientConfig config;

    @Bean
    @ConditionalOnMissingBean
    public IRule ribbonRule(IClientConfig config){
        System.out.println("-------ribbon自定义随机------");
        return new RandomRule();
    }
}
*/
