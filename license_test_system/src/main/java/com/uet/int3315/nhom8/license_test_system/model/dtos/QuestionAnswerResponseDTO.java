package com.uet.int3315.nhom8.license_test_system.model.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QuestionAnswerResponseDTO {
    private Integer id;
//    private int questionTypeId;
    private String questionTypeCode;
    private String content;
    private String imageUrl;
    private Date createdDate;
    private Date updatedDate;
    private List<String> answers;
}
