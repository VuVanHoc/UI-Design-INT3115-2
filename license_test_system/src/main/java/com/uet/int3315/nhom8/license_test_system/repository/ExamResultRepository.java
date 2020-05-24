package com.uet.int3315.nhom8.license_test_system.repository;

import com.uet.int3315.nhom8.license_test_system.model.entity.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamResultRepository extends JpaRepository<ExamResult, Integer> {
	List<ExamResult> findAllByDeletedIsFalse();
	
	List<ExamResult> findByUserId(Integer userId);
	
	List<ExamResult> findByCourseId(Integer courseId);
	
	List<ExamResult> findByUserIdAndCourseId(Integer userId, Integer courseId);
	
	Integer countAllByDeletedIsFalse();
	
	Integer countAllByStatusIsTrue();
	
	Integer countByScoreIs(Integer score);
}
