package com.uet.int3315.nhom8.license_test_system.service.impl;

import com.uet.int3315.nhom8.license_test_system.error.QuestionNotFoundException;
import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.CorrectAnswerResponseDTO;
import com.uet.int3315.nhom8.license_test_system.model.dtos.QuestionAnswerResponseDTO;
import com.uet.int3315.nhom8.license_test_system.model.dtos.QuestionRequestDTO;
import com.uet.int3315.nhom8.license_test_system.model.dtos.QuestionResponseDTO;
import com.uet.int3315.nhom8.license_test_system.model.entity.Answer;
import com.uet.int3315.nhom8.license_test_system.model.entity.Question;
import com.uet.int3315.nhom8.license_test_system.repository.AnswerRepository;
import com.uet.int3315.nhom8.license_test_system.repository.QuestionRepository;
import com.uet.int3315.nhom8.license_test_system.repository.QuestionTypeRepository;
import com.uet.int3315.nhom8.license_test_system.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    public static final Logger LOGGER = LoggerFactory.getLogger(QuestionServiceImpl.class);

    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private QuestionTypeRepository questionTypeRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository, QuestionTypeRepository questionTypeRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.questionTypeRepository = questionTypeRepository;
    }

    @Override
    public RestBody createQuestion(QuestionRequestDTO questionRequestDTO) {
        Question question = new Question();
        BeanUtils.copyProperties(questionRequestDTO, question);

        questionRepository.save(question);

        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO();
        //Convert to dto
        BeanUtils.copyProperties(question, questionResponseDTO);
        return RestBody.success(questionResponseDTO);
    }

    @Override
    public RestBody updateQuestion(QuestionRequestDTO dto, Integer id) {
        Question question = questionRepository.findOneByIdAndDeletedIsFalse(id);
        if (question == null) {
            throw new QuestionNotFoundException("Question is not found: " + id);
        }
        BeanUtils.copyProperties(dto, question);
        question.setUpdatedDate(new Date());
        questionRepository.save(question);

        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO();
        BeanUtils.copyProperties(question, questionResponseDTO);
        questionResponseDTO.setQuestionTypeCode(this.getQuestionTypeCode(question.getQuestionTypeId()));
        return RestBody.success(questionResponseDTO);
    }

    @Override
    public RestBody deleteQuestion(Integer id) {
        Question question = questionRepository.findOneByIdAndDeletedIsFalse(id);
        if (question == null) {
            throw new QuestionNotFoundException("Question is not found: " + id);
        }
        question.setDeleted(true);

        questionRepository.save(question);
        return RestBody.success(null);
    }

    @Override
    public RestBody getAllQuestions() {
        List<QuestionResponseDTO> questionResponseListDTO = new ArrayList<>();
        List<Question> questions = questionRepository.findAllByDeletedIsFalse();
        questions.forEach(question -> {
            QuestionResponseDTO single = new QuestionResponseDTO();
            BeanUtils.copyProperties(question, single);
            single.setQuestionTypeCode(this.getQuestionTypeCode(question.getQuestionTypeId()));
            questionResponseListDTO.add(single);
        });

        return RestBody.success(questionResponseListDTO);
    }

    @Override
    public RestBody getQuestion(Integer id) {
        Question question = questionRepository.findOneByIdAndDeletedIsFalse(id);
        if (question == null) {
            throw new QuestionNotFoundException("Question is not found: " + id);
        }

        List<Answer> answerEntities = answerRepository.findAllByQuestionId(question.getId());
        List<String> answers = new ArrayList<>();
        answerEntities.forEach(item->answers.add(item.getContent()));

        QuestionAnswerResponseDTO responseDTO = new QuestionAnswerResponseDTO();
        responseDTO.setQuestionTypeCode(this.getQuestionTypeCode(question.getQuestionTypeId()));
        responseDTO.setAnswers(answers);
        BeanUtils.copyProperties(question, responseDTO);

        return RestBody.success(responseDTO);
    }

    @Override
    public RestBody getCorrectAnswer(Integer id) {
        Question question = questionRepository.findOneByIdAndDeletedIsFalse(id);
        List<Answer> answerEntities = answerRepository.findAllByQuestionId(question.getId());
        List<String> answers = new ArrayList<>();
        answerEntities.forEach(item->answers.add(item.getContent()));

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for(int i = 0; i < answerEntities.size(); i++){
            if(answerEntities.get(i).isStatus()){ // answer.getStatus = true
                arrayList.add(i);
            }
        }
        int correctIndex[] = new int[arrayList.size()];
        for(int i = 0; i < arrayList.size(); i++){
            correctIndex[i] = arrayList.get(i);
        }

        CorrectAnswerResponseDTO responseDTO = new CorrectAnswerResponseDTO();
        responseDTO.setQuestion_id(id);
        responseDTO.setAnswers(answers);
        responseDTO.setCorrectIndex(correctIndex);
        return RestBody.success(responseDTO);
    }

    private String getQuestionTypeCode(Integer questionTypeId){
        return questionTypeRepository.findOneByIdAndDeletedIsFalse(questionTypeId).getCode();
    }
}
