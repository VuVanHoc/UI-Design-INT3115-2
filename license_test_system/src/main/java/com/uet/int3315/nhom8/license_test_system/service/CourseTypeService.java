package com.uet.int3315.nhom8.license_test_system.service;

import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.CourseTypeRequestDTO;

public interface CourseTypeService {
    RestBody createCourseType(CourseTypeRequestDTO courseTypeRequestDTO);
    RestBody updateCourseType(CourseTypeRequestDTO courseTypeRequestDTO, Integer id);
    RestBody deleteCourseType(Integer id);
    RestBody getAllCourseTypes(Integer pageNo, Integer pageSize);
    RestBody getCourseType(Integer id);
}
