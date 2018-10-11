package bin.manager.service.impl;

import bin.manager.dao.TbUserMapper;
import bin.manager.pojo.TbUser;
import bin.manager.pojo.TbUserExample;
import bin.manager.service.TbUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lenovo on 2018/7/12.
 */
@Transactional
@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    TbUserMapper tbUserMapper;

    @Override
    public int insert(TbUser record) {
        return tbUserMapper.insert(record);
    }

    @Override
    public TbUser selectByPrimaryKey(Long id) {
        return tbUserMapper.selectByPrimaryKey(id);
    }

    /*
  * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
  * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
  * pageNum 开始页数
  * pageSize 每页显示的数据条数
  * */
    @Override
    public PageInfo<TbUser> selectByExample(int pageNum, int pageSize) {
        TbUserExample example = new TbUserExample();
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(tbUsers);
        return pageInfo;
    }

    @Override
    public int updateByPrimaryKeySelective(TbUser record) {
        return tbUserMapper.updateByPrimaryKeySelective(record);
    }
}
