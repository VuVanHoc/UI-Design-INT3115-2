package com.uet.int3315.nhom8.license_test_system.model.dtos;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
	private String accessToken;
	private String tokenType = "Bearer";
	
	public JwtAuthenticationResponse(String accessToken){
		this.accessToken = accessToken;
	}
}
