package com.cellulam.spring.autoconfigure.test.db.dal.test1;

import com.cellulam.spring.autoconfigure.test.db.po.test2.CourseDo;
import org.springframework.stereotype.Repository;

@Repository
public interface Course2Dao {
    CourseDo getById(long id);
}
