package com.uet.int3315.nhom8.license_test_system.restcontroller;

import com.uet.k62.web.system.examination.config.JwtTokenProvider;
import com.uet.k62.web.system.examination.model.dtos.JwtAuthenticationResponse;
import com.uet.k62.web.system.examination.model.dtos.LoginRequest;
import com.uet.k62.web.system.examination.model.entity.CustomUserDetail;
import com.uet.k62.web.system.examination.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	
	public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userRepository = userRepository;
	}
	
	@PostMapping(value = "/authenticate", produces = {"application/json"}, consumes = {"application/x-www-form-urlencoded"})
	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
		System.out.println("Auth" + loginRequest.toString());
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(),
						loginRequest.getPassword())
				);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		String jwt = jwtTokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
		System.out.println("Auth"+authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}
}
