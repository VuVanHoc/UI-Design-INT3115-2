package com.uet.int3315.nhom8.license_test_system.config;


import com.uet.int3315.nhom8.license_test_system.model.entity.CustomUserDetail;
import com.uet.int3315.nhom8.license_test_system.model.entity.Role;
import com.uet.int3315.nhom8.license_test_system.model.entity.User;
import com.uet.int3315.nhom8.license_test_system.repository.RoleRepository;
import com.uet.int3315.nhom8.license_test_system.repository.UserRepository;
import com.uet.int3315.nhom8.license_test_system.utils.Constant;
import org.dozer.DozerBeanMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private  DozerBeanMapper mapper;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;
	
	public JwtAuthenticationFilter(DozerBeanMapper mapper, UserRepository userRepository,
	                               RoleRepository roleRepository,
	                               AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
		this.mapper = mapper;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		try {
			User user = mapper.map(request.getInputStream(), User.class);
			User exist = userRepository.findByUsername(user.getUsername());
			
			Role role = roleRepository.findById(exist.getRoleId()).orElseGet(null);
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role.getName());
			
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							user.getUsername(),
							user.getPassword(),
							grantedAuthorities)
			);
			
		} catch (Exception e){
			throw new RuntimeException();
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		String jwt = jwtTokenProvider.generateToken((CustomUserDetail) authResult.getPrincipal());
		System.out.println("Success " + jwt);
		response.addHeader(Constant.HEADER_TOKEN, Constant.BEARER_TOKEN+jwt);
		response.addHeader("Content-Type", "application/json");
		
	}
}
