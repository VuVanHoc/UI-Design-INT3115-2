package com.uet.int3315.nhom8.license_test_system.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@Entity(name = "question")
public class Question extends BaseEntity {
	
	@Column(name = "question_type")
	private int questionTypeId;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "image_url")
	private String imageUrl;

	@ManyToMany(mappedBy = "questions")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Set<ExamSchedule> exams;
}
