package com.uet.int3315.nhom8.license_test_system.restcontroller;


import com.uet.int3315.nhom8.license_test_system.config.JwtTokenProvider;
import com.uet.int3315.nhom8.license_test_system.config.MyAuthenticationSuccessHandler;
import com.uet.int3315.nhom8.license_test_system.model.dtos.JwtAuthenticationResponse;
import com.uet.int3315.nhom8.license_test_system.model.dtos.LoginRequest;
import com.uet.int3315.nhom8.license_test_system.model.entity.CustomUserDetail;
import com.uet.int3315.nhom8.license_test_system.repository.UserRepository;
import com.uet.int3315.nhom8.license_test_system.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	
	public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
	                      UserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userRepository = userRepository;
	}
	
	@PostMapping(value = "/authenticate", produces = {"application/json"}, consumes = {"application/x-www-form-urlencoded"})
	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest, HttpServletRequest request,
	                                          HttpServletResponse response) throws IOException, ServletException {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(),
						loginRequest.getPassword())
				);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		String jwt = jwtTokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
		System.out.println("Auth"+authentication);
		
		response.addHeader(Constant.HEADER_TOKEN, Constant.BEARER_TOKEN + jwt);
		response.addHeader("Content-type", "application/json");
		AuthenticationSuccessHandler authenticationSuccessHandler = new MyAuthenticationSuccessHandler();
		authenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}
}
