package com.cellulam.spring.db.sharding.test.po;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class UserDo {
    private Long uid;
    private String name;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
