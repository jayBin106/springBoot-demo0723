package bin.manager.controller;

import bin.manager.pojo.TbUser;
import bin.manager.service.TbUserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by lenovo on 2018/7/12.
 */
@Controller
@RequestMapping("/user")
public class TbUserController {
    @Autowired
    private TbUserService tbUserService;

    @RequestMapping("/insert")
    public int insert(TbUser tbUser) {
        int insert = tbUserService.insert(tbUser);
        return insert;
    }

    @RequestMapping("/select")
    public String findAllUser(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize, ModelMap modelMap) {
        PageInfo<TbUser> pageInfo = tbUserService.selectByExample(pageNum, pageSize);
        modelMap.put("tbUser", pageInfo.getList());
        modelMap.put("title", "测试frameMakeer");
        return "index";
    }

    @ResponseBody
    @RequestMapping("/select/{id}")
    public TbUser findAllUserById(@PathVariable(value = "id") Integer id) {
        TbUser tbUser = tbUserService.selectByPrimaryKey(Long.valueOf(id + ""));
        return tbUser;
    }

    @ResponseBody
    @PostMapping("/update")
    public TbUser update(@RequestBody JsonNode jsonNode) {
        System.out.println(jsonNode);
        TbUser tbUser = tbUserService.selectByPrimaryKey(Long.valueOf(jsonNode.get("id").asLong()));
        tbUser.setUsername("李铁柱");
        tbUser.setCreated(new Date());
        int i = tbUserService.updateByPrimaryKeySelective(tbUser);
        return tbUser;
    }

    @PostMapping(value = "/error")
    public String error(ModelMap map) {
        throw new RuntimeException("测试异常");
    }

    @PostMapping(value = "/cPost")
    public void cPost(@RequestParam(value = "id", defaultValue = "2") Integer id) {
        System.out.println(id);
    }
}
