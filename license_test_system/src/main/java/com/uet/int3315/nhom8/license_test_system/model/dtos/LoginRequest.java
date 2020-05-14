package com.uet.int3315.nhom8.license_test_system.model.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
	
	@NotBlank
	private String username;
	@NotBlank
	private String password;
}
