package com.uet.int3315.nhom8.license_test_system.config;


import com.uet.int3315.nhom8.license_test_system.service.impl.UserDetailServiceImpl;
import com.uet.int3315.nhom8.license_test_system.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserDetailServiceImpl userDetailService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			String token = getJwtFromRequest(request);
			
			if (StringUtils.hasText(token)) {
//			lấy username từ JWT
				String usernameFromToken = jwtTokenProvider.getUsernameFromToken(token);

//			lấy thông tin user từ username
				UserDetails userDetails = userDetailService.loadUserByUsername(usernameFromToken);
				if (userDetails != null) {
//				Nếu user hợp lệ thì truyền vào cho SecurityContext
					UsernamePasswordAuthenticationToken authenticationToken =
							new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					SecurityContext securityContext = SecurityContextHolder.getContext();
					securityContext.setAuthentication(authenticationToken);
					LOGGER.info("Authentication successfully");
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error to set authentication", e);
		}
		chain.doFilter(request, response);
	}
	
	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(Constant.HEADER_TOKEN);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constant.BEARER_TOKEN)) {
			return bearerToken.substring(7);
		}
		return null;
	}
}
