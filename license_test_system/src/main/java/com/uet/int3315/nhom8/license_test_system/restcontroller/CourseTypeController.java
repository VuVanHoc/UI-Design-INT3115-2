package com.uet.int3315.nhom8.license_test_system.restcontroller;

import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.CourseTypeRequestDTO;
import com.uet.int3315.nhom8.license_test_system.paging.PageConstant;
import com.uet.int3315.nhom8.license_test_system.service.CourseTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/course-types")
public class CourseTypeController {
    private CourseTypeService courseTypeService;
    public CourseTypeController(CourseTypeService courseTypeService){
        this.courseTypeService = courseTypeService;
    }

    @ApiOperation(value = "Get all course-types", response = RestBody.class)
    @GetMapping
    public ResponseEntity getAllCourseTypes(@RequestParam(defaultValue = PageConstant.PAGE_NO) Integer pageNo,
                                            @RequestParam(defaultValue = PageConstant.PAGE_SIZE) Integer pageSize){
        RestBody restBody = courseTypeService.getAllCourseTypes(pageNo, pageSize);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Create new course type", response = RestBody.class)
    @PostMapping
    public ResponseEntity createCourseType(@RequestBody CourseTypeRequestDTO courseTypeRequestDTO){
        RestBody restBody = courseTypeService.createCourseType(courseTypeRequestDTO);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Get one course-type", response = RestBody.class)
    @GetMapping(value = "{id}")
    public ResponseEntity getCourseType(@PathVariable Integer id){
        RestBody restBody = courseTypeService.getCourseType(id);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Update one course-type", response = RestBody.class)
    @PutMapping(value = "{id}")
    public ResponseEntity getCourseType(@PathVariable Integer id, @RequestBody CourseTypeRequestDTO dto){
        RestBody restBody = courseTypeService.updateCourseType(dto, id);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Delete a course type", response = RestBody.class)
    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteCourseType(@PathVariable Integer id){
        courseTypeService.deleteCourseType(id);
        return ResponseEntity.noContent().build();
    }
}
