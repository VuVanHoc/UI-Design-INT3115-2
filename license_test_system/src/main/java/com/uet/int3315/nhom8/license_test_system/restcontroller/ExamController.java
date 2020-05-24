package com.uet.int3315.nhom8.license_test_system.restcontroller;

import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.QuestionIdsListDTO;
import com.uet.int3315.nhom8.license_test_system.service.ExamService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/exams/{examId}/questions")
public class ExamController {
    private ExamService examService;

    public ExamController(ExamService examService){
        this.examService  = examService;
    }

    @ApiOperation(value = "Get a list question of an exam", response = RestBody.class)
    @GetMapping
    public ResponseEntity createExam(@PathVariable Integer examId){
        RestBody restBody = examService.getExam(examId);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Create an exam with a question list", response = RestBody.class)
    @PostMapping
    public ResponseEntity createExam(@PathVariable Integer examId, @RequestBody QuestionIdsListDTO questionIdsListDTO){
        RestBody restBody = examService.createExam(examId, questionIdsListDTO);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Update an exam with an other question list", response = RestBody.class)
    @PutMapping
    public ResponseEntity updateExam(@PathVariable Integer examId, @RequestBody QuestionIdsListDTO questionIdsListDTO){
        RestBody restBody = examService.updateExam(examId, questionIdsListDTO);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Delete all questions of an exam", response = RestBody.class)
    @DeleteMapping
    public ResponseEntity deleteExam(@PathVariable Integer examId){
        RestBody restBody = examService.deleteExam(examId);
        return ResponseEntity.ok(restBody);
    }

}
