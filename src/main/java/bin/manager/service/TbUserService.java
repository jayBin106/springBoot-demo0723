package bin.manager.service;


import bin.manager.pojo.TbUser;
import com.github.pagehelper.PageInfo;

/**
 * Created by lenovo on 2018/7/12.
 */
public interface TbUserService {

    int insert(TbUser record);

    TbUser selectByPrimaryKey(Long id);

    public PageInfo<TbUser> selectByExample(int pageNum, int pageSize);

    int updateByPrimaryKeySelective(TbUser record);
}
