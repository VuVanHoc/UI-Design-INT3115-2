package com.uet.int3315.nhom8.license_test_system.service;

import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.QuestionIdsListDTO;

public interface ExamService {
    RestBody getExam(Integer examId);
    RestBody createExam(Integer examId, QuestionIdsListDTO questionIdsListDTO);
    RestBody updateExam(Integer examId, QuestionIdsListDTO questionIdsListDTO);
    RestBody deleteExam(Integer examId);
}
