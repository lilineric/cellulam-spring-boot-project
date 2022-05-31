package com.cellulam.spring.db.test;

import com.cellulam.spring.db.test.dal.CourseDAO;
import com.cellulam.spring.db.test.po.CourseDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class ShardingJdbcTest extends BaseUnitTest{

    @Autowired
    private CourseDAO courseDAO;

    @Test
    public void addCourse() {
        CourseDO course = new CourseDO();
        course.setCid(System.currentTimeMillis());
        course.setCname("Java");
        course.setUserId(100L);
        course.setStatus("Normal");
        course.setCreateTime(LocalDateTime.now());
        course.setUpdateTime(LocalDateTime.now());
        courseDAO.add(course);

        System.out.println("course: " + courseDAO.getById(course.getCid()));

        course = new CourseDO();
        course.setCid(System.currentTimeMillis());
        course.setCname("Python");
        course.setUserId(100L);
        course.setStatus("Simple");
        course.setCreateTime(LocalDateTime.now());
        course.setUpdateTime(LocalDateTime.now());
        courseDAO.add(course);
        System.out.println("course: " + courseDAO.getById(course.getCid()));

        List<CourseDO> courses = courseDAO.getAll();

        System.out.println("count: " + courses.size());

    }
}
