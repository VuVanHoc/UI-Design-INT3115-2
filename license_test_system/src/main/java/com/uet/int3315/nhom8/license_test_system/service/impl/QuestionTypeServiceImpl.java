package com.uet.int3315.nhom8.license_test_system.service.impl;


import com.uet.int3315.nhom8.license_test_system.error.QuestionTypeNotFoundException;
import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.QuestionTypeDTO;
import com.uet.int3315.nhom8.license_test_system.model.entity.QuestionType;
import com.uet.int3315.nhom8.license_test_system.repository.QuestionTypeRepository;
import com.uet.int3315.nhom8.license_test_system.service.QuestionTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {
    private QuestionTypeRepository questionTypeRepository;

    public QuestionTypeServiceImpl(QuestionTypeRepository questionTypeRepository){
        this.questionTypeRepository = questionTypeRepository;
    }

    @Override
    public RestBody createQuestionType(QuestionTypeDTO dto) {
        QuestionType questionType = questionTypeRepository.findOneByCode(dto.getCode());
        if(questionType != null){
            throw new QuestionTypeNotFoundException("This question type exists");
        }
        QuestionType newQuestionType = new QuestionType();
        BeanUtils.copyProperties(dto, newQuestionType);
        questionTypeRepository.save(newQuestionType);
        return RestBody.success(newQuestionType);
    }

    @Override
    public RestBody updateQuestionType(QuestionTypeDTO dto, Integer id) {
        QuestionType questionType = questionTypeRepository.findOneByIdAndDeletedIsFalse(id);
        if(questionType == null){
            throw new QuestionTypeNotFoundException("Not found question type: " + id);
        }
        BeanUtils.copyProperties(dto, questionType);
        questionType.setUpdatedDate(new Date());
        questionTypeRepository.save(questionType);
        return RestBody.success(questionType);
    }

    @Override
    public RestBody deleteQuestionType(Integer id) {
        QuestionType questionType = questionTypeRepository.findOneByIdAndDeletedIsFalse(id);
        if(questionType == null){
            throw new QuestionTypeNotFoundException("Not found question type: " + id);
        }
        questionType.setDeleted(true);
        questionTypeRepository.save(questionType);
        return RestBody.success(null);
    }

    @Override
    public RestBody getQuestionType(Integer id) {
        QuestionType questionType = questionTypeRepository.findOneByIdAndDeletedIsFalse(id);
        if(questionType == null){
            throw new QuestionTypeNotFoundException("Not found question type: " + id);
        }
        return RestBody.success(questionType);
    }

    @Override
    public RestBody getAllQuestionTypes() {
        List<QuestionType> questionTypes = questionTypeRepository.findAllByDeletedIsFalse();
        return RestBody.success(questionTypes);
    }
}
