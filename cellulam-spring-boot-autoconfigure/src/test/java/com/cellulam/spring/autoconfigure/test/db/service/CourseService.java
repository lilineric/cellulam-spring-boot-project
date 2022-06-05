package com.cellulam.spring.autoconfigure.test.db.service;

import com.cellulam.spring.autoconfigure.test.db.dal.test1.Course2Dao;
import com.cellulam.spring.autoconfigure.test.db.dal.test2.CourseDao;
import com.cellulam.spring.autoconfigure.test.db.po.test2.CourseDo;
import com.cellulam.spring.db.annotation.TargetDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {
    @Autowired
    private Course2Dao course2Dao;

    @Autowired
    private CourseDao courseDao;

    @TargetDataSource("test2")
    public CourseDo getById(long id) {
        CourseDo courseDo = course2Dao.getById(id);
        return courseDo;
    }

    @Transactional
    @TargetDataSource("test2")
    public void addCourses(CourseDo courseDo, CourseDo courseDo2) {
        courseDao.add(courseDo);
        throw new RuntimeException("test transaction");
    }
}
