package com.uet.int3315.nhom8.license_test_system.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uet.int3315.nhom8.license_test_system.utils.Constant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "user")
public class User extends BaseEntity {
	
	@Column(name = "role_id", nullable = false)
	private Integer roleId;
	
	@Column(name = "username", nullable = false, length = 50)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;

	@Column(name = "birthday")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_FORMAT_PATTERN, timezone = "GMT+07:00")
	private Date birthday;
	
	@Column(name = "picture")
	private String picture;
	
}
