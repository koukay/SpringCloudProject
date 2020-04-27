package com.houkai;

import com.houkai.userapi.Person;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class UserProviderBack implements ConsumerApi {
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
        return "降级了";
    }

    @Override
    public String getById(Integer id) {
        return null;
    }

    @Override
    public Person postPserson(Person person) {
        return null;
    }
}
