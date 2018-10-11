package bin.manager;

import bin.manager.common.mongoDB.RoncooMongodbComponent;
import bin.manager.common.mongoDB.RoncooUserLogMongoDao;
import bin.manager.pojo.TbUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by lenovo on 2018/7/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDBTest {
    @Autowired
    private RoncooMongodbComponent roncooMongodbComponent;
    @Autowired
    private RoncooUserLogMongoDao roncooUserLogMongoDao;

    @Test
    public void set() {
        TbUser roncooUser = new TbUser();
        roncooUser.setId(1l);
        roncooUser.setUsername("无境1");
        roncooUser.setCreated(new Date());
        roncooMongodbComponent.insert(roncooUser);
    }

    @Test
    public void select() {
        System.out.println(roncooMongodbComponent.selectById(1).getUsername());
    }

    @Test
    public void update() {
        TbUser roncooUser = new TbUser();
        roncooUser.setId(1l);
        roncooUser.setUsername("测试修改");
        roncooUser.setCreated(new Date());
        roncooMongodbComponent.updateById(roncooUser);
        System.out.println(roncooMongodbComponent.selectById(1).getUsername());
    }

    @Test
    public void delete() {
        roncooMongodbComponent.deleteById(1);
    }


    @Test
    public void set1() {
        TbUser roncooUser = new TbUser();
        roncooUser.setId(4l);

        roncooUser.setUsername("无境4");
        roncooUser.setCreated(new Date());
        roncooUserLogMongoDao.insert(roncooUser);
    }

    @Test
    public void select1() {
        //分页
        Pageable pageable = new PageRequest(0, 20, new Sort(new Sort.Order(Sort.Direction.DESC, "created")));
        for (TbUser o : roncooUserLogMongoDao.findAll(pageable)) {
            System.out.println(" id：" + o.getId() + ",名字:" + o.getUsername() + ",时间：" + o.getCreated());
        }
    }
}
