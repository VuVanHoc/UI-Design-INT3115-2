package com.uet.int3315.nhom8.license_test_system.repository;

import com.uet.int3315.nhom8.license_test_system.model.entity.ExamSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamScheduleRepository extends JpaRepository<ExamSchedule, Integer> {
    ExamSchedule findFirstByCourseId(Integer courseId);
    ExamSchedule findAllByIdAndDeletedIsFalse(Integer id);
}
