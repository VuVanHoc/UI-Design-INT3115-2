package com.uet.int3315.nhom8.license_test_system.service;


import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.UserDetailDTO;
import com.uet.int3315.nhom8.license_test_system.model.dtos.UserFormRegistrationDTO;

public interface UserService {
	RestBody registerAccount(UserFormRegistrationDTO userFormRegistrationDTO);
	
	RestBody getAllUsers();

	RestBody getUser(Integer id);

	RestBody deleteUser(Integer id);

	RestBody updateInfoUser(UserDetailDTO userDetailDTO, Integer id);
}
