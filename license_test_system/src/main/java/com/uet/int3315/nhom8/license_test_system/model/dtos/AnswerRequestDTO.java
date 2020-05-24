package com.uet.int3315.nhom8.license_test_system.model.dtos;

import lombok.Data;

import java.util.List;

@Data
public class AnswerRequestDTO {
    private Integer questionId;
    private List<String> answers;
    private int[] correctIndex;
}
