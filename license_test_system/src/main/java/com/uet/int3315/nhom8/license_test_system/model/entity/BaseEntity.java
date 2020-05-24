package com.uet.int3315.nhom8.license_test_system.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	@Getter
	@Setter
	@Column(name = "created_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+07")
	protected Date createdDate;

	@Setter
	@Getter
	@Column(name = "updated_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+07")
	protected Date updatedDate;

	@Setter
	@Getter
	@Column(name = "deleted")
	protected boolean deleted;

	public Integer getId() {
		return this.id;
	}

	BaseEntity() {
		this.createdDate = new Date();
		this.updatedDate = new Date();
	}
}
