package com.uet.int3315.nhom8.license_test_system.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity(name = "question_type")
public class QuestionType extends BaseEntity {

	@Column(name = "code")
	private String code;
	
	@Column(name = "description")
	private String description;
	
}
