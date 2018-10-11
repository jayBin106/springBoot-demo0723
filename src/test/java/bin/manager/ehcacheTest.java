package bin.manager;

import bin.manager.common.ehcache.TbUserCache;
import bin.manager.pojo.TbUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ehcacheTest {
    @Autowired
    TbUserCache tbUserCache;

    @Test
    public void selectById() {
        TbUser tbUser = tbUserCache.selectById(1);
        System.out.println(tbUser.getUsername());
    }
}
