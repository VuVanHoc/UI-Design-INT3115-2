package com.uet.int3315.nhom8.license_test_system.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity(name = "role")
public class Role extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
}
