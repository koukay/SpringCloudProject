package com.houkai;

import com.houkai.userapi.Person;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
//http://localhost:92/actuator/refresh手动刷新
@RestController
@RefreshScope
public class MainController {
    @Autowired
    ConsumerApi api;
    @Autowired
    RestService restService;
    @Value("${myconfig}")
    String myconfig;
    @Value("${server.port}")
    String port;

    /**
     * http://localhost:91/
     * http://localhost:92/
     * @return
     */
    @GetMapping
    public String getConfig(){
        return port+" : "+myconfig;
    }
    /**
     * http://localhost:91/alive
     * 服务请求过来会调用 ConsumerApi的isAlive方法
     * @FeignClient(name = "user-provider",fallbackFactory = UserProviderBackFactory.class) FeignClient进行远程服务调用,
     * 如果发生异常则在fallbackFactory中进行处理,UserProviderBackFactory 实现FallbackFactory,重写create方法,拿到异常信息和响应信息
     * @return
     */
    @GetMapping("/alive")
    public String alive() {
        //远程服务调用,hystrix配置有超时机制和重试次数
//        #业务逻辑超时时间(ms)
//        ribbon.ReadTimeout=2000
//        #同一台实例最大重试次数,不包括首次调用
//        ribbon.MaxAutoRetries=3
        return "User Consumer:" + port + "->>>>"+api.isAlive();
    }

    @GetMapping("/alive2")
    @HystrixCommand(defaultFallback = "back")
    public String alive2() {
        /**
         * URL 不能变
         *
         * jar
         * 文档
         */

        return "Consumer:" + port + "->>>>" + restService.alive();
    }

    public String back() {

        return "呵呵,熔断了";
    }
    /**
     *
     * 降级
     *
     *
     * 隔离
     *
     * 熔断
     *
     * 自己写
     *
     *
     * try{
     *
     *     1.   发起向服务方的请求;
     *     		1.1 判断连接超时
     *     			-> 这次请求 记录到服务里
     *     		http请求  线程消耗
     *
     *
     *     		map(URI,线程数)
     *     		线程池（线程数）
     *        阈值 阀值
     *
     *        计数 连续失败次数 达到阈值
     *        count ++；
     *     if(count == 10){
     *
     *     new romdom  == 1  按时间
     *       发请求
     *
     *
     *     	throw exception;
     *     }
     *
     *
     *         请求/不请求/半请求
     *         开      关         半开
     *
     *     if （当前线程满了）{
     *     	throw exception
     *     }
     *
     *
     *     		1.2 尝试向其他服务器发起请求
     *
     *
     *     注解
     *
     *
     *     2. 还是没成功
     *
     *     }catch(Exception e){
     *
     *     	1.	避免返回不友好的错误信息
     *     			-> 好看点儿的页面  重试按钮 联系邮箱
     *
     *
     *     	2.	return 另外一个东西 写到MQ里 admin 发个邮件
     *
     *     		return "客观稍后再来"；
     *
     *     }
     *
     *
     *     Hystrix 干的就是这件事儿
     */




    //	@GetMapping("/vip")
//	public String vip() {
//
//		return mapi.getVip();
//	}
//
    @GetMapping("/map")
    public Map<Integer, String> map(Integer id) {
        System.out.println(id);
        return api.getMap(id);
    }

    @GetMapping("/map2")
    public Map<Integer, String> map2(Integer id,String name) {
        System.out.println(id);
        return api.getMap2(id,name);
    }


    @GetMapping("/map3")
    public Map<Integer, String> map3(@RequestParam Map<String, Object> map) {
        HashMap<String, Object> map1 = new HashMap<>(2);

        map1.put("id", 2000);
        map1.put("name", "凯");
        return api.getMap3(map1);
    }


    @GetMapping("/map4")
    public Map<Integer, String> map4(@RequestParam Map<String, Object> map) {
//		System.out.println(id);
//		HashMap<String, Object> map = new HashMap<>(2);
//
//		map.put("id", id);
//		map.put("name", name);
//		syso
        System.out.println(map);
        return api.postMap(map);
    }




    @GetMapping("/postPerson")
    public Person postPerson(@RequestParam Map<String, Object> map) {

        System.out.println(map);

        Person person = new Person();
        person.setId(Integer.parseInt(map.get("id").toString()));
        person.setName("xxoo");
        return api.postPserson(person);
    };


}
