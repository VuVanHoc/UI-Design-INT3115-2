package com.uet.int3315.nhom8.license_test_system.service;

import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.UserDetailDTO;
import com.uet.int3315.nhom8.license_test_system.model.dtos.UserFormRegistrationDTO;

public interface UserService {
	RestBody registerAccount(UserFormRegistrationDTO userFormRegistrationDTO);
	
	RestBody getAllUsers(Integer pageNo, Integer pageSize);

	RestBody getUser(Integer id);

	RestBody getUser(String username);

	RestBody deleteUser(Integer id);

	RestBody updateInfoUser(UserDetailDTO userDetailDTO, Integer id);

	RestBody getCourses(Integer id);
	
	RestBody calculateTotal();
}
