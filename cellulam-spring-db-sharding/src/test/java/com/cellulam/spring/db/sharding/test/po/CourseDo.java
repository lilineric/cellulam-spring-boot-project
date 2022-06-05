package com.cellulam.spring.db.sharding.test.po;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class CourseDo {
    private Long cid;
    private String cname;
    private Long userId;
    private String status;
    private Long createTime;
    private Long updateTime;
}
