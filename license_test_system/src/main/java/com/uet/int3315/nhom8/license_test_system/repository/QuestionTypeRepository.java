package com.uet.int3315.nhom8.license_test_system.repository;

import com.uet.k62.web.system.examination.model.entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionTypeRepository extends JpaRepository<QuestionType, Integer> {
    List<QuestionType> findAllByDeletedIsFalse();
    QuestionType findByIdAndDeletedIsFalse(int id);
    QuestionType findOneByCode(String code);
    QuestionType findOneByIdAndDeletedIsFalse(Integer id);
}
