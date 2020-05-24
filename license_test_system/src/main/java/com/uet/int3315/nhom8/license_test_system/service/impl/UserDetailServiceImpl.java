package com.uet.int3315.nhom8.license_test_system.service.impl;

import com.uet.int3315.nhom8.license_test_system.model.entity.CustomUserDetail;
import com.uet.int3315.nhom8.license_test_system.model.entity.User;
import com.uet.int3315.nhom8.license_test_system.repository.RoleRepository;
import com.uet.int3315.nhom8.license_test_system.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	public UserDetailServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		User currentUser = userRepository.findByUsername(username);
		if (currentUser == null) {
			throw new UsernameNotFoundException(username);
		}
		String roleUser = roleRepository.findById(currentUser.getRoleId()).isPresent() ?
				roleRepository.findById(currentUser.getRoleId()).get().getName() : "";
		roleUser = "ROLE_".concat(roleUser);
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roleUser);
		
		CustomUserDetail customUserDetail = new CustomUserDetail(currentUser, authorities);
//		System.out.println(customUserDetail);
		return customUserDetail;
	}
}

