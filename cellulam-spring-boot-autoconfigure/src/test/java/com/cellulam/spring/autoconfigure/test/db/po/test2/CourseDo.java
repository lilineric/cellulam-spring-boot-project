package com.cellulam.spring.autoconfigure.test.db.po.test2;

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
}
