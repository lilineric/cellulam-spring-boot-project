package com.cellulam.spring.db.test.dal;

import com.cellulam.spring.db.test.po.CourseDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseDAO {
    CourseDO getById(long id);
    int add(CourseDO course);
    List<CourseDO> getAll();
}
