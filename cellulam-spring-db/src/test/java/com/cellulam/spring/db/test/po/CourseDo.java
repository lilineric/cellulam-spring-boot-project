package com.cellulam.spring.db.test.po;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseDo {
    private Long cid;
    private String cname;
    private Long userId;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
