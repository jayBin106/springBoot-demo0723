package bin.manager.controller;

import bin.manager.common.redis.RoncooRedisComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * Created by lenovo on 2018/7/25.
 */
@Controller
public class indexController {
    @Value("${spring.session.redis.namespace}")
    String redisNameSpace;
    @Autowired
    RoncooRedisComponent roncooRedisComponent;

    @RequestMapping(value = "/redisSession")
    public String index(ModelMap map, HttpSession httpSession) throws UnsupportedEncodingException {
        httpSession.setAttribute("jay", "123456");
        map.put("title", "第一个应用：sessionID=" + httpSession.getId());
        System.out.println("sessionID=" + httpSession.getId());
        String key = redisNameSpace + ":sessions:" + httpSession.getId();
        Object s = roncooRedisComponent.hget(key, "sessionAttr:jay");
        byte[] bytes = s.toString().getBytes("gbk");
        String s1 = new String(bytes, "gbk");
        System.out.println(s1);
        return "redisSession";
    }

}
