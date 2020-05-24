package com.uet.int3315.nhom8.license_test_system.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity(name = "exam_result_update")
public class ExamResult extends BaseEntity {

	@Column(name = "user_id")
	private int userId;

	@Column(name = "course_id")
	private int courseId;
	
//	@Column(name = "end_time")
//	private Date endTime;
	
	@Column(name = "score")
	private int score;
	
	@Column(name = "status")
	private boolean status;
	
}
