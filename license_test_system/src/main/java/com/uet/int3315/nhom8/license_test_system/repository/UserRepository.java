package com.uet.int3315.nhom8.license_test_system.repository;

import com.uet.k62.web.system.examination.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findAllByDeletedIsFalse();
	User findByUsername(String username);
	User findUserById(Integer id);
	User findByIdAndDeletedIsFalse(Integer id);
}
