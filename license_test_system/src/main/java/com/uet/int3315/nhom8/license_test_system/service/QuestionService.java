package com.uet.int3315.nhom8.license_test_system.service;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.QuestionRequestDTO;

public interface QuestionService {
    RestBody createQuestion(QuestionRequestDTO dto);
    RestBody updateQuestion(QuestionRequestDTO dto, Integer id);
    RestBody deleteQuestion(Integer id);
    RestBody getAllQuestions();
    RestBody getQuestion(Integer id);
    RestBody getCorrectAnswer(Integer id);
}
