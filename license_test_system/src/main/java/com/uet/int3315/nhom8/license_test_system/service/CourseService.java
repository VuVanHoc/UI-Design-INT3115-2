package com.uet.int3315.nhom8.license_test_system.service;

import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.CourseDTO;
import com.uet.int3315.nhom8.license_test_system.model.dtos.UserIdListDTO;

public interface CourseService {
    RestBody createCourse(CourseDTO courseDTO);
    RestBody updateCourse(CourseDTO courseDTO, Integer courseId);
    RestBody deleteCourse(Integer courseId);
    RestBody getAllCourses(Integer pageNo, Integer pageSize);
    RestBody getCourse(Integer id);
    RestBody registerCourse(Integer courseId, UserIdListDTO dto);
    RestBody leaveCourse(Integer courseId, UserIdListDTO dto);
    RestBody getTotal();
    
	RestBody getAllUserInCourse(Integer courseId);
}
