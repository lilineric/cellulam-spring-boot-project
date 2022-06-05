package com.cellulam.spring.autoconfigure.test.db.dal.test2;

import com.cellulam.spring.autoconfigure.test.db.po.test2.CourseDo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao {
    CourseDo getById(long id);

    int add(CourseDo course);

    List<CourseDo> getAll();

    CourseDo getByUserId(long userId);

}
