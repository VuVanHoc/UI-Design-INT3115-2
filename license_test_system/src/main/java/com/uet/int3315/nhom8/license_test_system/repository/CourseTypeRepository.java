package com.uet.int3315.nhom8.license_test_system.repository;

import com.uet.int3315.nhom8.license_test_system.model.entity.CourseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseTypeRepository extends JpaRepository<CourseType, Integer> {
	Page<CourseType> findAllByDeletedIsFalse(Pageable paging);
	
	CourseType findByIdAndDeletedIsFalse(Integer id);
}
