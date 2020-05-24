package com.uet.int3315.nhom8.license_test_system.model.dtos;

import lombok.Data;

@Data
public class CourseDTO {
	
	private int id;
	private int index;
    private int typeId;
    private String courseName;
    private String typeName;
    private String description;
    private String createdTime;
    private int minScore;
    private int total;
    
}
