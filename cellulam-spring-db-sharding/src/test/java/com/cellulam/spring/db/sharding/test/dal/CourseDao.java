package com.cellulam.spring.db.sharding.test.dal;

import com.cellulam.spring.db.sharding.test.po.CourseDo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao {
    CourseDo getById(long id);

    int add(CourseDo course);

    List<CourseDo> getAll();

    CourseDo getByUserId(long userId);

}
