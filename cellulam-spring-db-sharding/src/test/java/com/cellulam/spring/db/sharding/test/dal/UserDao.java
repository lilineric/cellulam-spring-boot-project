package com.cellulam.spring.db.sharding.test.dal;

import com.cellulam.spring.db.sharding.test.po.UserDo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    UserDo getById(long id);

    int add(UserDo user);

    List<UserDo> getAll();
}
