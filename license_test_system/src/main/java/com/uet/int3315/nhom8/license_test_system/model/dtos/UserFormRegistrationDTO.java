package com.uet.int3315.nhom8.license_test_system.model.dtos;

import lombok.Data;

import java.math.BigInteger;

@Data
public class UserFormRegistrationDTO {
	private BigInteger roleId ;
	private String username;
	private String password;
	private String confirmPassword;
	private String fullName;
	private String birthday;
	private String email;
	private String phone;

}
