package com.houkai.userapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户操作相关接口
 * @author houkai
 *
 */
@RequestMapping("/User")
public interface UserApi {

    @GetMapping("/isAlive")
    public String isAlive();
    @GetMapping("/getById")
    public String getById(Integer id);
    @PostMapping("/postPserson")
    public Person postPserson(@RequestBody Person person);
}