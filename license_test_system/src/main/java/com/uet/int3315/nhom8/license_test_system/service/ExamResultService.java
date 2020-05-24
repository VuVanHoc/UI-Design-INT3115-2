package com.uet.int3315.nhom8.license_test_system.service;

import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.ExamResultDTO;

public interface ExamResultService {
    RestBody saveExamResult(ExamResultDTO examResultDTO);
    RestBody getExamResult(Integer userId, Integer courseId);
    RestBody getAllResults();
    RestBody getFullResults();
    RestBody getTotalRecord();
    RestBody getPass();
    RestBody countPoint();
}
