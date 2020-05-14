package com.uet.int3315.nhom8.license_test_system.model.dtos;

import lombok.Data;

@Data
public class QuestionRequestDTO {
    private int questionTypeId;
    private String content;
    private String imageUrl;
}
