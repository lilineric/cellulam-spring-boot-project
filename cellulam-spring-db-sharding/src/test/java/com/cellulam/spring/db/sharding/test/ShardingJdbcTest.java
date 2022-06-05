package com.cellulam.spring.db.sharding.test;

import com.cellulam.spring.db.sharding.test.dal.AddressDao;
import com.cellulam.spring.db.sharding.test.dal.CourseDao;
import com.cellulam.spring.db.sharding.test.dal.UserDao;
import com.cellulam.spring.db.sharding.test.po.AddressDo;
import com.cellulam.spring.db.sharding.test.po.CourseDo;
import com.cellulam.spring.db.sharding.test.po.UserDo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class ShardingJdbcTest extends BaseUnitTest {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AddressDao addressDao;

    @Test
    public void testSharding() {
        UserDo user = new UserDo();
        user.setUid(System.currentTimeMillis());
        user.setName("Jack");
        user.setStatus("Normal");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userDao.add(user);

        System.out.println("course: " + userDao.getById(user.getUid()));
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

    @Test
    public void testBinding() {
        UserDo user = new UserDo();
        user.setUid(System.currentTimeMillis());
        user.setName("Jack");
        user.setStatus("Normal");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userDao.add(user);
        System.out.println("user: " + userDao.getById(user.getUid()));

        AddressDo address = new AddressDo();
        address.setId(System.currentTimeMillis() * 1000 + user.getUid() % 1000);
        address.setAddress("Java");
        address.setUid(user.getUid());
        address.setStatus("Enable");
        addressDao.add(address);
        System.out.println("address: " + addressDao.getById(address.getId()));
        System.out.println("address: " + addressDao.getByUserId(address.getUid()));
        System.out.println("address: " + addressDao.get(address.getId()));
    }
}


