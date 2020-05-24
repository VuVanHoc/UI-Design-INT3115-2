package com.uet.int3315.nhom8.license_test_system.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity(name = "exam_schedule")
public class ExamSchedule extends BaseEntity {

	@Column(name = "course_id")
	private int courseId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm", timezone = "GMT+07")
	@Column(name = "start_time")
	private Date startTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm", timezone = "GMT+07")
	@Column(name = "end_time")
	private Date endTime;
	
	@Column(name = "note")
	private String note;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@JoinTable(name = "exam_question",
			joinColumns = @JoinColumn(name = "exam_id"),
			inverseJoinColumns = @JoinColumn(name = "question_id"))
	@JsonIgnore
	private Set<Question> questions;
}
