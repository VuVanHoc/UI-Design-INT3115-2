package com.uet.int3315.nhom8.license_test_system.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity(name = "course_type")
public class CourseType extends BaseEntity {
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "min_score")
	private int minScore;
	
	@Column(name = "number_question")
	private int numberQuestion;
}
