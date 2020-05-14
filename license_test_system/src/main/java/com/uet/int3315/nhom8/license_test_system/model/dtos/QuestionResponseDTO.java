package com.uet.int3315.nhom8.license_test_system.model.dtos;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class QuestionResponseDTO {
    private BigInteger id;
//    private int questionTypeId;
    private String questionTypeCode;
    private String content;
    private String imageUrl;
    private Date createdDate;
    private Date updatedDate;
}
