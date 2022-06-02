package com.cellulam.spring.db.test;

import com.cellulam.spring.db.test.dal.CourseDao;
import com.cellulam.spring.db.test.dal.UserDao;
import com.cellulam.spring.db.test.po.CourseDo;
import com.cellulam.spring.db.test.po.UserDo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class ShardingJdbcTest extends BaseUnitTest{

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private UserDao userDao;
    
    @Test
    public void testSharding() {
        CourseDo course = new CourseDo();
        course.setCid(System.currentTimeMillis());
        course.setCname("Java");
        course.setUserId(100L);
        course.setStatus("Normal");
        course.setCreateTime(LocalDateTime.now());
        course.setUpdateTime(LocalDateTime.now());
        courseDao.add(course);

        System.out.println("course: " + courseDao.getById(course.getCid()));

        course = new CourseDo();
        course.setCid(System.currentTimeMillis());
        course.setCname("Python");
        course.setUserId(100L);
        course.setStatus("Simple");
        course.setCreateTime(LocalDateTime.now());
        course.setUpdateTime(LocalDateTime.now());
        courseDao.add(course);
        System.out.println("course: " + courseDao.getById(course.getCid()));

        List<CourseDo> courses = courseDao.getAll();

        System.out.println("count: " + courses.size());

        UserDo user = new UserDo();
        user.setUid(System.currentTimeMillis());
        user.setName("Jack");
        user.setStatus("Normal");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userDao.add(user);

        System.out.println("course: " + userDao.getById(user.getUid()));

        user = new UserDo();
        user.setUid(System.currentTimeMillis());
        user.setName("Tom");
        user.setStatus("Disabled");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userDao.add(user);
        System.out.println("user: " + userDao.getById(user.getUid()));

        List<UserDo> users = userDao.getAll();

        System.out.println("count: " + users.size());

    }
}
