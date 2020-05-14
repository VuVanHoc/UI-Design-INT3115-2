package com.uet.int3315.nhom8.license_test_system.model.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CorrectAnswerResponseDTO {
    private Integer question_id;
    private List<String> answers;
    private int[] correctIndex;
}
