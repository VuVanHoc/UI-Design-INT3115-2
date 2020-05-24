package com.uet.int3315.nhom8.license_test_system.service.impl;

import com.uet.int3315.nhom8.license_test_system.error.CourseTypeNotFoundException;
import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.CourseTypeRequestDTO;
import com.uet.int3315.nhom8.license_test_system.model.entity.CourseType;
import com.uet.int3315.nhom8.license_test_system.repository.CourseTypeRepository;
import com.uet.int3315.nhom8.license_test_system.service.CourseTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseTypeServiceImpl implements CourseTypeService {
    private CourseTypeRepository courseTypeRepository;

    public CourseTypeServiceImpl(CourseTypeRepository courseTypeRepository) {
        this.courseTypeRepository = courseTypeRepository;
    }

    @Override
    public RestBody createCourseType(CourseTypeRequestDTO courseTypeRequestDTO) {
        CourseType courseType = new CourseType();
        BeanUtils.copyProperties(courseTypeRequestDTO, courseType);

        CourseType courseTypeSaved = courseTypeRepository.save(courseType);
        return RestBody.success(courseTypeSaved);
    }

    @Override
    public RestBody updateCourseType(CourseTypeRequestDTO courseTypeRequestDTO, Integer id) {
        CourseType courseType = courseTypeRepository.findByIdAndDeletedIsFalse(id);
        BeanUtils.copyProperties(courseTypeRequestDTO, courseType);
        courseTypeRepository.save(courseType);
        return RestBody.success(courseType);
    }

    @Override
    public RestBody deleteCourseType(Integer id) {
        CourseType courseType = courseTypeRepository.findByIdAndDeletedIsFalse(id);
        if (courseType == null) {
            throw new CourseTypeNotFoundException("Course Type Not Found!");
        }
        courseType.setDeleted(true);
        courseTypeRepository.save(courseType);
        return RestBody.success("Deleted!");
    }

    @Override
    public RestBody getAllCourseTypes(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<CourseType> courseTypes = courseTypeRepository.findAllByDeletedIsFalse(paging);
        if (courseTypes.hasContent()) {
            return RestBody.success(courseTypes.getContent());
        } else {
            return RestBody.success("Không có loại nào");
        }
    }

    @Override
    public RestBody getCourseType(Integer id) {
        CourseType courseType = courseTypeRepository.findByIdAndDeletedIsFalse(id);
        return RestBody.success(courseType);
    }
}
