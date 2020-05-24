package com.uet.int3315.nhom8.license_test_system.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetailDTO {
	
	private int index;
	private int id;
	private String fullName;
	private String birthday;
	private String email;
	private String phone;
	private String roleName;
	private int roleId;
}
