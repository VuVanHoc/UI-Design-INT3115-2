package com.uet.int3315.nhom8.license_test_system.model.dtos;

import lombok.Data;

@Data
public class UserFormRegistrationDTO {
	
	private Integer roleId ;
	private String fullName;
	private String username;
	private String password;
	private String confirmPassword;
	private String phone;

}
