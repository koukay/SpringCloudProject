package com.houkai;

import com.houkai.userapi.Person;
import feign.FeignException;
import feign.hystrix.FallbackFactory;

import java.util.Map;

public class UserProviderBackFactory implements FallbackFactory<ConsumerApi> {
    @Override
    public ConsumerApi create(Throwable cause) {
        return new ConsumerApi() {
            @Override
            public Map<Integer, String> getMap(Integer id) {
                return null;
            }

            @Override
            public Map<Integer, String> getMap2(Integer id, String name) {
                return null;
            }

            @Override
            public Map<Integer, String> getMap3(Map<String, Object> map) {
                return null;
            }

            @Override
            public Map<Integer, String> postMap(Map<String, Object> map) {
                return null;
            }

            @Override
            public String isAlive() {
                System.out.println(cause);
                if(cause instanceof FeignException.InternalServerError) {

                    return "远程服务器 500" + cause.getLocalizedMessage();
                }else {

                    return "呵呵";
                }
            }

            @Override
            public String getById(Integer id) {
                return null;
            }

            @Override
            public Person postPserson(Person person) {
                return null;
            }
        };
    }
}
