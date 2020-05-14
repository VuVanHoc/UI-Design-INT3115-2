package com.uet.int3315.nhom8.license_test_system.model.dtos;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class AnswerResponseDTO {
    private BigInteger question_id;
    private List<String> answers;
}
