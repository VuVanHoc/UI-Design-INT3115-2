package com.uet.int3315.nhom8.license_test_system.config;


import com.uet.int3315.nhom8.license_test_system.repository.RoleRepository;
import com.uet.int3315.nhom8.license_test_system.repository.UserRepository;
import com.uet.int3315.nhom8.license_test_system.service.impl.UserDetailServiceImpl;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final UserDetailServiceImpl userDetailService;
	
	private DozerBeanMapper mapper;
	final
	UserRepository userRepository;
	final
	RoleRepository roleRepository;

	final
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	public WebSecurityConfig(UserDetailServiceImpl userDetailService, DozerBeanMapper mapper, UserRepository userRepository, RoleRepository roleRepository, JwtTokenProvider jwtTokenProvider) {
		this.userDetailService = userDetailService;
		this.mapper = mapper;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers("/login*", "/authenticate").permitAll()
				.antMatchers("/v2/api-docs",
						"/swagger-resources",
						"/swagger-resources/**",
						"/configuration/ui",
						"/configuration/security",
						"/swagger-ui.html",
						"/webjars/**",
						"/**/*.html",
						"/**/*.css",
						"/**/*.js",
						"/**/*.png").permitAll()
//				.antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
//				.antMatchers("/student/**").hasAnyAuthority("ROLE_STUDENT")
				.anyRequest().permitAll()
				
				
				
				.and()
				.formLogin()
				.loginPage("/login")
				.failureUrl("/login?com.uet.int3315.nhom8.license_test_system.error=true")
				.and()
				.logout()
				.permitAll()
				.and()
				.addFilter(new JwtAuthenticationFilter(mapper, userRepository, roleRepository, authenticationManagerBean(), jwtTokenProvider))
				.addFilter(new JwtAuthorizationFilter(authenticationManager()))
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
		return new MyAuthenticationSuccessHandler();
	}
	
	//	cung cấp userDetailService và passwordEncoder cho Spring Security
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	}
}
