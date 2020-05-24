package com.uet.int3315.nhom8.license_test_system.model.dtos;

import lombok.Data;

@Data
public class ExamResultDTO {
    private Integer userId;
    private Integer courseId;
//    private Integer endTime;
    private Integer score;
    private boolean status;
}
