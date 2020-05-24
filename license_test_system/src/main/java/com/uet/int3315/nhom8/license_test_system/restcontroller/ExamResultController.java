package com.uet.int3315.nhom8.license_test_system.restcontroller;

import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.ExamResultDTO;
import com.uet.int3315.nhom8.license_test_system.service.ExamResultService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/exam-result")
public class ExamResultController {
    private ExamResultService examResultService;

    public ExamResultController(ExamResultService examResultService){
        this.examResultService = examResultService;
    }

    @ApiOperation(value = "Lưu kết quả thi", response = RestBody.class)
    @PostMapping
    public ResponseEntity saveResult(@RequestBody ExamResultDTO examResultDTO){
        RestBody restBody = examResultService.saveExamResult(examResultDTO);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Lấy kết quả thi", response = RestBody.class)
    @GetMapping
    public ResponseEntity getExamResult(@RequestParam(required = false) Integer userId, @RequestParam(required = false) Integer courseId){
        RestBody restBody = examResultService.getExamResult(userId, courseId);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Tổng số người đã thi", response = RestBody.class)
    @GetMapping(value = "/totalRecords")
    public ResponseEntity getTotal(){
        RestBody restBody = examResultService.getTotalRecord();
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Tổng số người đỗ", response = RestBody.class)
    @GetMapping(value = "/totalPass")
    public ResponseEntity getPass(){
        RestBody restBody = examResultService.getPass();
        return ResponseEntity.ok(restBody);
    }
    
    
    @ApiOperation(value = "Thống kê số lượng điểm ", response = RestBody.class)
	@GetMapping(value = "/count-point")
	public ResponseEntity countPoint(){
        return ResponseEntity.ok(examResultService.countPoint());
    }
}
