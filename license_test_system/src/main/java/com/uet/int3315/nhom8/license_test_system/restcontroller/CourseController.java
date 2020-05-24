package com.uet.int3315.nhom8.license_test_system.restcontroller;

import com.uet.int3315.nhom8.license_test_system.model.RestBody;
import com.uet.int3315.nhom8.license_test_system.model.dtos.CourseDTO;
import com.uet.int3315.nhom8.license_test_system.model.dtos.UserIdListDTO;
import com.uet.int3315.nhom8.license_test_system.paging.PageConstant;
import com.uet.int3315.nhom8.license_test_system.service.CourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/courses")
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @ApiOperation(value = "Get a page of course", response = RestBody.class)
    @GetMapping
    public ResponseEntity getListCourses(@RequestParam(defaultValue = PageConstant.PAGE_NO) Integer pageNo,
                                         @RequestParam(defaultValue = PageConstant.PAGE_SIZE) Integer pageSize){
        RestBody restBody = courseService.getAllCourses(pageNo, pageSize);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Get total course", response = RestBody.class)
    @GetMapping(value = "total")
    public ResponseEntity getTotalCourses(){
        RestBody restBody = courseService.getTotal();
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Get a course", response = RestBody.class)
    @GetMapping(value = "{id}")
    public ResponseEntity getCourse(@PathVariable Integer id){
        RestBody restBody = courseService.getCourse(id);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Create a new course", response = RestBody.class)
    @PostMapping
    public ResponseEntity createCourse(@RequestBody CourseDTO courseDTO){
        RestBody restBody = courseService.createCourse(courseDTO);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Update course", response = RestBody.class)
    @PutMapping(value = "{id}")
    public ResponseEntity updateCourse(@PathVariable Integer id, @RequestBody CourseDTO courseDTO){
        RestBody restBody = courseService.updateCourse(courseDTO, id);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Delete Course", response = RestBody.class)
    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteCourse(@PathVariable Integer id){
        RestBody restBody = courseService.deleteCourse(id);
        return ResponseEntity.ok(restBody);
    }


    @ApiOperation(value = "Register a course", response = RestBody.class)
    @PostMapping(value = "{courseId}/users")
    public ResponseEntity registerCourse(@PathVariable Integer courseId, @RequestBody UserIdListDTO dto){
        RestBody restBody = courseService.registerCourse(courseId, dto);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Register a course", response = RestBody.class)
    @DeleteMapping(value = "{courseId}/users")
    public ResponseEntity leaveCourse(@PathVariable Integer courseId, @RequestBody UserIdListDTO dto){
        RestBody restBody = courseService.leaveCourse(courseId, dto);
        return ResponseEntity.ok(restBody);
    }
	
	@ApiOperation(value = "Register a course", response = RestBody.class)
	@GetMapping(value = "{courseId}/users")
	public ResponseEntity getListUserInCourse(@PathVariable Integer courseId){
		
    	RestBody restBody = courseService.getAllUserInCourse(courseId);
		return ResponseEntity.ok(restBody);
	}
}
