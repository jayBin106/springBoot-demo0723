package bin.manager.controller;

import bin.manager.common.redis.RoncooRedisComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lenovo on 2018/7/12.
 */
@RestController
@RequestMapping("/user2")
public class RedisController {
    @Autowired
    private RoncooRedisComponent redisComponent;

    @PostMapping(value = "/setKey")
    public void setKey(String key, String value) {
        redisComponent.set(key, value);
    }

    @PostMapping(value = "/getKey")
    public String getKey(String key) {
        String s = redisComponent.get(key);
        System.out.println(s);
        return s;
    }

    @PostMapping(value = "/delKey")
    public void delKey(String key) {
        redisComponent.del(key);
    }
}
