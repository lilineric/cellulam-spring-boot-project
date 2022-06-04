package com.cellulam.spring.db.test.dal;

import com.cellulam.spring.db.dal.BaseDao;
import com.cellulam.spring.db.test.po.UserDo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends BaseDao {
    UserDo getById(long id);

    int add(UserDo user);

    List<UserDo> getAll();
}
