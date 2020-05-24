package com.uet.int3315.nhom8.license_test_system.service.impl;

import com.uet.int3315.nhom8.license_test_system.error.ExamNotFoundException;
import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.QuestionIdsListDTO;
import com.uet.int3315.nhom8.license_test_system.model.entity.ExamSchedule;
import com.uet.int3315.nhom8.license_test_system.model.entity.Question;
import com.uet.int3315.nhom8.license_test_system.repository.ExamScheduleRepository;
import com.uet.int3315.nhom8.license_test_system.repository.QuestionRepository;
import com.uet.int3315.nhom8.license_test_system.service.ExamService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExamServiceImpl implements ExamService {
    private ExamScheduleRepository examScheduleRepository;
    private QuestionRepository questionRepository;

    public ExamServiceImpl(ExamScheduleRepository examScheduleRepository,
                           QuestionRepository questionRepository) {
        this.examScheduleRepository = examScheduleRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public RestBody getExam(Integer examId) {
        ExamSchedule examSchedule = examScheduleRepository.findAllByIdAndDeletedIsFalse(examId);
        if (examSchedule == null) {
            throw new ExamNotFoundException("Exam Not Found");
        }
        List<Integer> questionIdsList = new ArrayList<>();
        examSchedule.getQuestions().forEach(question -> questionIdsList.add(question.getId()));
        return RestBody.success(questionIdsList);
    }

    @Override
    public RestBody createExam(Integer examId, QuestionIdsListDTO questionIdsListDTO) {
        Set<Integer> questionsFail = new HashSet<>();
        ExamSchedule examSchedule = examScheduleRepository.findAllByIdAndDeletedIsFalse(examId);
        if (examSchedule == null) {
            throw new ExamNotFoundException("Exam Not Found");
        }
        questionIdsListDTO.getQuestionIds().forEach(questionId -> {
            Question question = questionRepository.findOneByIdAndDeletedIsFalse(questionId);
            if (question == null) {
                questionsFail.add(questionId);
            } else {
                examSchedule.getQuestions().add(question);
            }
        });
        examScheduleRepository.save(examSchedule);
        return questionsFail.isEmpty() ? RestBody.success("Success! Created exam") : RestBody.success("Some questions can not add to exam");
    }

    @Override
    public RestBody updateExam(Integer examId, QuestionIdsListDTO questionIdsListDTO) {
        Set<Integer> questionsFail = new HashSet<>();
        ExamSchedule examSchedule = examScheduleRepository.findAllByIdAndDeletedIsFalse(examId);
        if (examSchedule == null) {
            throw new ExamNotFoundException("Exam Not Found");
        }

        examSchedule.getQuestions().removeAll(examSchedule.getQuestions());
        questionIdsListDTO.getQuestionIds().forEach(questionId -> {
            Question question = questionRepository.findOneByIdAndDeletedIsFalse(questionId);
            if (question == null) {
                questionsFail.add(questionId);
            } else {
                examSchedule.getQuestions().add(question);
            }
        });
        examScheduleRepository.save(examSchedule);
        return questionsFail.isEmpty() ? RestBody.success("Success! Updated exam") : RestBody.success("Some questions can not add to exam");
//        return RestBody.success("Updated exam");
    }

    @Override
    public RestBody deleteExam(Integer examId) {
        ExamSchedule examSchedule = examScheduleRepository.findAllByIdAndDeletedIsFalse(examId);
        examSchedule.getQuestions().removeAll(examSchedule.getQuestions());
        examScheduleRepository.save(examSchedule);
        return RestBody.success("Deleted");
    }
}
