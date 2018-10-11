package bin.manager.common.mongoDB;


import bin.manager.pojo.TbUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoncooUserLogMongoDao extends MongoRepository<TbUser, Integer> {

//    TbUser findByUserName(String string);
//
//    TbUser findByUserNameAndUserIp(String string, String ip);
//
//    Page<TbUser> findByUserName(String string, Pageable pageable);
    @Override
    List<TbUser> findAll();

    @Override
    Page<TbUser> findAll(Pageable pageable);



}
 