package com.uet.int3315.nhom8.license_test_system.service;

import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.CreateQuestionAndAnswerRequest;
import com.uet.int3315.nhom8.license_test_system.model.dtos.QuestionRequestDTO;

public interface QuestionService {
    RestBody createQuestion(QuestionRequestDTO dto);
    RestBody updateQuestion(QuestionRequestDTO dto, Integer id);
    RestBody deleteQuestion(Integer id);
    RestBody getAllQuestions(Integer pageNo, Integer pageSize);
    RestBody getQuestion(Integer id);
    RestBody getCorrectAnswer(Integer id);
    RestBody createQuestionAndAnswer(CreateQuestionAndAnswerRequest createQuestionAndAnswerRequest);
}
