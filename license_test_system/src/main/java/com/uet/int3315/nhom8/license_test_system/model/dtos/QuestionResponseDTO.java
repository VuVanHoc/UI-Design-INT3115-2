package com.uet.int3315.nhom8.license_test_system.model.dtos;

import lombok.Data;

@Data
public class QuestionResponseDTO {
	private int index;
    private Integer id;
//    private int questionTypeId;
    private String questionTypeCode;
    private String content;
    private String imageUrl;
    private String createdDate;
    private String updatedDate;
}
