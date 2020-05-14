package com.uet.int3315.nhom8.license_test_system.service;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.AnswerRequestDTO;

public interface AnswerService {
    RestBody createAnswer(AnswerRequestDTO answerRequestDTO);
    RestBody updateAnswer(AnswerRequestDTO answerRequestDTO);
    RestBody deleteAnswer(Integer questionId);
}
