package com.uet.int3315.nhom8.license_test_system.restcontroller;

import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.ExamScheduleDTO;
import com.uet.int3315.nhom8.license_test_system.service.ExamScheduleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/courses")
public class ExamScheduleController {
    private ExamScheduleService examScheduleService;

    public ExamScheduleController(ExamScheduleService examScheduleService){
        this.examScheduleService = examScheduleService;
    }

    @ApiOperation(value = "get exam schedule", response = RestBody.class)
    @GetMapping(value = "{courseId}/exam-schedule")
    public ResponseEntity getExamSchedule(@PathVariable Integer courseId){
        RestBody restBody = examScheduleService.getExamSchedule(courseId);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "create exam schedule", response = RestBody.class)
    @PostMapping(value = "{courseId}/exam-schedule")
    public ResponseEntity createExamSchedule(@PathVariable Integer courseId, @RequestBody ExamScheduleDTO dto){
        RestBody restBody = examScheduleService.createExamSchedule(dto, courseId);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "update exam schedule", response = RestBody.class)
    @PutMapping(value = "{courseId}/exam-schedule")
    public ResponseEntity updateExamSchedule(@PathVariable Integer courseId, @RequestBody ExamScheduleDTO dto){
        RestBody restBody = examScheduleService.updateExamSchedule(dto, courseId);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "delete exam schedule", response = RestBody.class)
    @DeleteMapping(value = "{courseId}/exam-schedule")
    public ResponseEntity deleteExamSchedule(@PathVariable Integer courseId){
        RestBody restBody = examScheduleService.deleteExamSchedule(courseId);
        return ResponseEntity.ok(restBody);
    }
}
