package com.uet.int3315.nhom8.license_test_system.config;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Getter
	@Setter
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
	                                    HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}
	
	private void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		System.out.println(response.getHeader("Authorization"));
		request.setAttribute("Authorization", response.getHeader("Authorization"));
		String targetUrl = determineTargetUrl(authentication);
		LOGGER.info(targetUrl);
		if (response.isCommitted()) {
			LOGGER.debug("Response has been committed" + targetUrl);
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	private String determineTargetUrl(Authentication authentication) {
		LOGGER.info(authentication.getAuthorities().toString());
		boolean isAdmin = false;
		boolean isMentor = false;
		boolean isStudent = false;
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().contains("ROLE_ADMIN")) {
				isAdmin = true;
				break;
			}
			if (grantedAuthority.getAuthority().contains("ROLE_MENTOR")) {
				isMentor = true;
				break;
			}
			if (grantedAuthority.getAuthority().contains("ROLE_STUDENT")) {
				isStudent = true;
				break;
			}
		}
		
		if (isAdmin) {
			return "/admin/dashboard";
		} else if (isMentor) {
			return "/mentor/dashboard";
		} else if (isStudent) {
			return "/student/dashboard";
		} else {
			throw new IllegalArgumentException();
		}
		
	}
	
	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		
	}
}
