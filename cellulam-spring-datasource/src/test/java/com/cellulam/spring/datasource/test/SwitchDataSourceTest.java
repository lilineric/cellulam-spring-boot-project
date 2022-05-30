package com.cellulam.spring.datasource.test;

import com.cellulam.spring.datasource.context.DataSourceContextHolder;
import com.cellulam.spring.datasource.test.dal.OrderDAO;
import com.cellulam.spring.datasource.test.dal.SysDAO;
import com.cellulam.spring.datasource.test.dal.UserDAO;
import com.cellulam.spring.datasource.test.po.OrderDO;
import com.cellulam.spring.datasource.test.po.UserDO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class SwitchDataSourceTest extends BaseUnitTest{

    @Autowired
    private UserDAO userDao;

    @Autowired
    private SysDAO sysDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Test
    public void testSwitchDataSource() {

        LocalDateTime currentTime = sysDAO.getSysDate();

        UserDO userDO = new UserDO();
        userDO.setName("Mike");
        userDO.setAge(28);
        userDO.setCreateTime(currentTime);
        userDO.setUpdateTime(currentTime);
        userDao.addUser(userDO);

        System.out.println(userDO);

        UserDO user = userDao.getById(userDO.getId());
        System.out.println(user);

        Assert.assertEquals(userDO.getId(), user.getId());
        Assert.assertEquals(userDO.getName(), user.getName());
        Assert.assertEquals(userDO.getAge(), user.getAge());

        DataSourceContextHolder.switchDataSource("test2");
        currentTime = sysDAO.getSysDate();
        OrderDO orderDO = new OrderDO();
        orderDO.setUid(user.getId());
        orderDO.setAmount(100);
        orderDO.setTitle("Test");
        orderDO.setCreateTime(currentTime);
        orderDO.setUpdateTime(currentTime);

        orderDAO.addOrder(orderDO);
        System.out.println(orderDAO.getById(orderDO.getId()));
    }
}
