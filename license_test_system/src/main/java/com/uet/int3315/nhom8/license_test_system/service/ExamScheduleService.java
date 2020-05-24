package com.uet.int3315.nhom8.license_test_system.service;

import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.ExamScheduleDTO;

public interface ExamScheduleService {
    RestBody createExamSchedule(ExamScheduleDTO examScheduleDTO, Integer courseId);
    RestBody updateExamSchedule(ExamScheduleDTO examScheduleDTO, Integer courseId);
    RestBody getExamSchedule(Integer courseId);
    RestBody deleteExamSchedule(Integer courseId);

}
