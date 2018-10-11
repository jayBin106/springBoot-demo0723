package bin.manager.common.ehcache;

import bin.manager.pojo.TbUser;

/**
 * @author wujing
 */
public interface TbUserCache {

    /**
     * 查询
     */
    TbUser selectById(Integer id);

    /**
     * 更新
     */
    TbUser updateById(TbUser roncooUserLog);

    /**
     * 删除
     */
    String deleteById(Integer id);
}
 
 