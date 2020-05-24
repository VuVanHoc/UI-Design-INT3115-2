package com.uet.int3315.nhom8.license_test_system.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "course")
public class Course extends BaseEntity {
	@Column(name = "type_id")
	private int typeId;

	@Column(name = "course_name")
	private String courseName;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@JoinTable(name = "course_user",
			joinColumns = @JoinColumn(name = "course_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	@JsonIgnore
	private Set<User> users;
}
