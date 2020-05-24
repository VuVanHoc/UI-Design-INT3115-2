package com.uet.int3315.nhom8.license_test_system.model.dtos;

import lombok.Data;

@Data
public class ExamResultFullDTO {
    private Integer index;
    private String fullName;
    private String userName;
    private String coursename;
    private Integer score;
    private String status;
}
