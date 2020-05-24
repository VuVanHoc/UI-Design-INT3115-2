package com.uet.int3315.nhom8.license_test_system.repository;

import com.uet.int3315.nhom8.license_test_system.model.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findByIdAndDeletedIsFalse(Integer courseId);
    Page<Course> findAllByDeletedIsFalse(Pageable paging);
    Integer countAllByDeletedIsFalse();

}
