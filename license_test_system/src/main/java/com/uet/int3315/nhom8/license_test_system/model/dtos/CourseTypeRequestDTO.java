package com.uet.int3315.nhom8.license_test_system.model.dtos;

import lombok.Data;

@Data
public class CourseTypeRequestDTO {
    private String name;
    private String description;
    private int minScore;
    private int numberQuestion;
}
