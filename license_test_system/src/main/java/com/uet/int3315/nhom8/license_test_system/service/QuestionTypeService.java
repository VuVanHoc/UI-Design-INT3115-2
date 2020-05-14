package com.uet.int3315.nhom8.license_test_system.service;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.QuestionTypeDTO;

public interface QuestionTypeService {
    RestBody createQuestionType(QuestionTypeDTO dto);
    RestBody updateQuestionType(QuestionTypeDTO dto, Integer id);
    RestBody deleteQuestionType(Integer id);
    RestBody getQuestionType(Integer id);
    RestBody getAllQuestionTypes();
}
