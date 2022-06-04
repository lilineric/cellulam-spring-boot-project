package com.cellulam.spring.db.test.po;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddressDo {
    private Long id;
    private Long uid;
    private String userName;
    private String address;
    private String status;
}
