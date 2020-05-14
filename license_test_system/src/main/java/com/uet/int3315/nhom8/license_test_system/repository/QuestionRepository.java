package com.uet.int3315.nhom8.license_test_system.repository;



import com.uet.int3315.nhom8.license_test_system.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findAllByDeletedIsFalse();
    Question findOneByIdAndDeletedIsFalse(Integer id);
}
