package com.cellulam.spring.autoconfigure.test.db.dal.test1;

import com.cellulam.spring.autoconfigure.test.db.po.test1.UserDo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    UserDo getById(long id);

    int add(UserDo user);

    List<UserDo> getAll();
}
