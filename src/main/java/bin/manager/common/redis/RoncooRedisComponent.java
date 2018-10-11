package bin.manager.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * redis测试
 */
@Component
public class RoncooRedisComponent {

    //springboot封装的redis的模块
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void set(String key, String value) {
        ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
        if (!this.stringRedisTemplate.hasKey(key)) {
            ops.set(key, value);
            System.out.println("redis的key设置成功");
            System.out.println("this key = " + ops.get(key));
        }
    }

    public String get(String key) {
        return this.stringRedisTemplate.opsForValue().get(key);
    }
    public Object hget(String key, Object o) {
        return this.stringRedisTemplate.opsForHash().get(key,o);
    }

    public void del(String key) {
        this.stringRedisTemplate.delete(key);
    }
}
