package com.houkai.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAdminServer
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

    /**
     * 用来进行拦截错误信息,以便发送消息
     * @param instanceRepository
     * @return
     */
    @Bean
    public DingDingNotifier dingDingNotifier(InstanceRepository instanceRepository){
        return new DingDingNotifier(instanceRepository);
    }

}
