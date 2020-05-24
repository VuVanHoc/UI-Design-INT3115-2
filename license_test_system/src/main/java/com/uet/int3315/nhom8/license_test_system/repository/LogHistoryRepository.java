package com.uet.int3315.nhom8.license_test_system.repository;

import com.uet.int3315.nhom8.license_test_system.model.entity.LogHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogHistoryRepository extends JpaRepository<LogHistory, Integer> {
}
