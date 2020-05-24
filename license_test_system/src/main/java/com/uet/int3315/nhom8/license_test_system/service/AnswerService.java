package com.uet.int3315.nhom8.license_test_system.service;

import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.AnswerRequestDTO;

public interface AnswerService {
    RestBody createAnswer(AnswerRequestDTO answerRequestDTO);
    RestBody updateAnswer(AnswerRequestDTO answerRequestDTO);
    RestBody deleteAnswer(Integer questionId);
}
