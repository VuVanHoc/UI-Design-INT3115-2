package com.uet.int3315.nhom8.license_test_system.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity(name = "course")
public class Course extends BaseEntity {
	
	@Column(name = "type_id")
	private int typeId;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "schedule_id")
	private int scheduleId;
	
}
