package com.cellulam.spring.db.sharding.test.dal;

import com.cellulam.spring.db.sharding.test.po.AddressDo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDao {
    AddressDo getById(long id);

    int add(AddressDo user);

    List<AddressDo> getAll();

    List<AddressDo> getByUserId(long uid);
    List<AddressDo> get(long id);
}
