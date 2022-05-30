package com.cellulam.spring.datasource.test.dal;

import com.cellulam.spring.datasource.test.po.OrderDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDAO {
    int addOrder(OrderDO orderDO);
    OrderDO getById(long id);
}
