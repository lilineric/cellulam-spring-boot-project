package com.cellulam.spring.db.test.po;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDo {
    private Long uid;
    private String name;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
