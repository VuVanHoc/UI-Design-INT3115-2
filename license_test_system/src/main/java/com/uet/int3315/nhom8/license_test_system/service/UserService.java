package com.uet.int3315.nhom8.license_test_system.service;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.UserDetailDTO;
import com.uet.k62.web.system.examination.model.dtos.UserFormRegistrationDTO;

public interface UserService {
	RestBody registerAccount(UserFormRegistrationDTO userFormRegistrationDTO);
	
	RestBody getAllUsers();

	RestBody getUser(Integer id);

	RestBody deleteUser(Integer id);

	RestBody updateInfoUser(UserDetailDTO userDetailDTO, Integer id);
}
