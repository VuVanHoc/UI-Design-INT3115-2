package com.uet.int3315.nhom8.license_test_system.restcontroller;

import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.UserDetailDTO;
import com.uet.int3315.nhom8.license_test_system.model.dtos.UserFormRegistrationDTO;
import com.uet.int3315.nhom8.license_test_system.paging.PageConstant;
import com.uet.int3315.nhom8.license_test_system.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/users")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	/* Resource: /users */
	
	// Method GET - Returns a list of all active users
	@ApiOperation(value = "Get all users", response = RestBody.class)
	@GetMapping
	public ResponseEntity getAllUsers(@RequestParam(defaultValue = PageConstant.PAGE_NO) Integer pageNo,
	                                  @RequestParam(defaultValue = PageConstant.PAGE_SIZE)Integer pageSize) {
		System.out.println("Hello world");
		RestBody restBody = userService.getAllUsers(pageNo, pageSize);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		LOGGER.info(securityContext.getAuthentication().getPrincipal().toString());
		return ResponseEntity.ok(restBody);
	}
	
	// Method POST - Create a new user
	@ApiOperation(value = "Registers a new account", response = RestBody.class)
	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity registerAccount(@RequestBody UserFormRegistrationDTO userFormRegistrationDTO) {
//		LOGGER.info(userFormRegistrationDTO.toString());
		RestBody restBody = userService.registerAccount(userFormRegistrationDTO);
		return ResponseEntity.ok(restBody);
	}
	
	// Method PUT
//	@ApiOperation(value = "Update all users", response = RestBody.class)
//	@PutMapping
//	public ResponseEntity updateAllUsers(){
//		return null;
//	}
	
	// Method DELETE
//	@ApiOperation(value = "Delete all users", response = RestBody.class)
//	@DeleteMapping
//	public ResponseEntity deleteAllUsers(){
//		return null;
//	}
	
	/* Resource: /users/id */
	
	// Method GET - Returns a specific user
	@ApiOperation(value = "Get a user", response = RestBody.class)
	@GetMapping(value = "{id}")
	public ResponseEntity getUser(@PathVariable Integer id) {
		RestBody restBody = userService.getUser(id);
		return ResponseEntity.ok(restBody);
	}
	
	// Method PUT - Updates a specific user
	@ApiOperation(value = "Updates information user", response = RestBody.class)
	@PutMapping(value = "{id}")
	public ResponseEntity updateAccount(@RequestBody UserDetailDTO userDetailDTO, @PathVariable("id") Integer id) {
		RestBody restBody = userService.updateInfoUser(userDetailDTO, id);
		return ResponseEntity.ok(restBody);
	}
	
	// Method DELETE - Deletes a specific user
	@ApiOperation(value = "Deletes a user", response = RestBody.class)
	@DeleteMapping(value = "{id}")
	public ResponseEntity deleteAccount(@PathVariable Integer id) {
		RestBody restBody = userService.deleteUser(id);
		return ResponseEntity.ok(restBody);
	}

	@ApiOperation(value = "Find user by username", response = RestBody.class)
	@GetMapping(value = "username/{username}")
	public ResponseEntity getByUsername(@PathVariable String username){
		RestBody restBody = userService.getUser(username);
		return ResponseEntity.ok(restBody);
	}

	@ApiOperation(value = "User's Courses", response = RestBody.class)
	@GetMapping(value = "{id}/courses")
	public ResponseEntity getCourses(@PathVariable Integer id){
		RestBody restBody = userService.getCourses(id);
		return ResponseEntity.ok(restBody);
	}
	
	@GetMapping(value = "/total")
	public ResponseEntity<?> calculateTotalUser(){
		return ResponseEntity.ok(userService.calculateTotal());
	}
}
