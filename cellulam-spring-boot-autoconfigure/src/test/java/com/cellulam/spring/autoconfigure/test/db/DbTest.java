package com.cellulam.spring.autoconfigure.test.db;

import com.cellulam.core.context.RuntimeContext;
import com.cellulam.core.utils.AssertUtils;
import com.cellulam.spring.autoconfigure.test.conf.BaseUnitTest;
import com.cellulam.spring.autoconfigure.test.db.dal.test1.UserDao;
import com.cellulam.spring.autoconfigure.test.db.dal.test2.CourseDao;
import com.cellulam.spring.autoconfigure.test.db.po.test1.UserDo;
import com.cellulam.spring.autoconfigure.test.db.po.test2.CourseDo;
import com.cellulam.spring.autoconfigure.test.db.service.CourseService;
import com.cellulam.spring.test.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {TestConf.class})
public class DbTest extends BaseUnitTest {
    @Autowired
    private UserDao userDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CourseService courseService;

    @Test
    public void testAutoSwitchDataSource() throws Exception {
        UserDo userDo = TestUtils.randomBean(UserDo.class);
        userDao.add(userDo);

        CourseDo courseDo = TestUtils.randomBean(CourseDo.class);
        courseDao.add(courseDo);

        UserDo userDo1 = userDao.getById(userDo.getUid());
        CourseDo courseDo1 = courseDao.getById(courseDo.getCid());

        TestUtils.assertSameObject(userDo, userDo1);
        TestUtils.assertSameObject(courseDo, courseDo1);

        System.out.println(userDo);
        System.out.println(userDo1);
        System.out.println(courseDo);
        System.out.println(courseDo1);
    }

    @Test
    public void testTargetDataSource() throws Exception {
        CourseDo courseDo = TestUtils.randomBean(CourseDo.class);
        courseDao.add(courseDo);
        System.out.println(RuntimeContext.getDigest());

        CourseDo courseDo1 = courseDao.getById(courseDo.getCid());
        System.out.println(RuntimeContext.getDigest());

        CourseDo courseDo2 = courseService.getById(courseDo.getCid());
        System.out.println(RuntimeContext.getDigest());

        CourseDo courseDo3 = courseDao.getById(courseDo.getCid());
        System.out.println(RuntimeContext.getDigest());


        TestUtils.assertSameObject(courseDo, courseDo1);
        TestUtils.assertSameObject(courseDo, courseDo2);
        TestUtils.assertSameObject(courseDo, courseDo3);

        System.out.println(courseDo);
        System.out.println(courseDo1);
        System.out.println(courseDo2);
        System.out.println(courseDo3);
    }

    @Test
    public void testTransaction() throws Exception {
        CourseDo courseDo = TestUtils.randomBean(CourseDo.class);
        CourseDo courseDo2 = TestUtils.randomBean(CourseDo.class);
        try {
            courseService.addCourses(courseDo, courseDo2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("test transaction", e.getMessage());
            AssertUtils.isNull(courseDao.getById(courseDo.getCid()));
            AssertUtils.isNull(courseDao.getById(courseDo2.getCid()));
        }
    }
}
