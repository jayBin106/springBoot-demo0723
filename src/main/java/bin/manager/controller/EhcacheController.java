package bin.manager.controller;

import bin.manager.common.ehcache.TbUserCache;
import bin.manager.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lenovo on 2018/7/25.
 */
@RestController
@RequestMapping("/ehcache")
public class EhcacheController {

    @Autowired
    TbUserCache tbUserCache;

    @RequestMapping("/select/{id}")
    public void select(@PathVariable(value = "id") Integer id) {
        TbUser tbUser = tbUserCache.selectById(id);
        System.out.println("姓名:" + tbUser.getUsername());
    }

    @RequestMapping("/update/{id}")
    public void update(@PathVariable(value = "id") Integer id) {
        TbUser tbUser = tbUserCache.selectById(id);
        tbUser.setUsername("李二狗");
        TbUser tbUser1 = tbUserCache.updateById(tbUser);
        System.out.println("姓名:" + tbUser1.getUsername());
    }
}
