package com.cellulam.spring.autoconfigure.test.db.po.test1;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class UserDo {
    private Long uid;
    private String name;
    private String status;
}
