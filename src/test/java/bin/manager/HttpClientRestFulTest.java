package bin.manager;

import bin.manager.pojo.TbUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2018/7/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpClientRestFulTest {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    /**
     * get请求
     */
    @Test
    public void getForObject() {
        TbUser bean = restTemplateBuilder.build().getForObject("http://localhost:8080/user/select/{id}", TbUser.class, 7);
        System.out.println(bean);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 7);
        bean = restTemplateBuilder.build().postForObject("http://localhost:8080/user/update", map, TbUser.class);
        System.out.println(bean);
    }

}
