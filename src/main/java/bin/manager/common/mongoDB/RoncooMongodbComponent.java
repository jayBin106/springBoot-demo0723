package bin.manager.common.mongoDB;

import bin.manager.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

/**
 * mongoDB工具类
 */
@Component
public class RoncooMongodbComponent {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void insert(TbUser tbUser) {
        mongoTemplate.insert(tbUser);
    }

    public void deleteById(int id) {
        Criteria criteria = Criteria.where("id").in(id);
        Query query = new Query(criteria);
        mongoTemplate.remove(query, TbUser.class);
    }

    public void updateById(TbUser tbUser) {
        Criteria criteria = Criteria.where("id").in(tbUser.getId());
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("username", tbUser.getUsername());
        update.set("created", tbUser.getCreated());
        mongoTemplate.updateMulti(query, update, TbUser.class);
    }

    public TbUser selectById(int id) {
        Criteria criteria = Criteria.where("id").in(id);
        Query query = new Query(criteria);
        return mongoTemplate.findOne(query, TbUser.class);
    }
} 