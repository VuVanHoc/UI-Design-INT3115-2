package com.uet.int3315.nhom8.license_test_system.repository;



import com.uet.int3315.nhom8.license_test_system.model.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findAllByQuestionId(Integer questionId);

    @Override
    void deleteById(Integer integer);
}