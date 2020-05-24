package com.uet.int3315.nhom8.license_test_system.error;

public class CourseTypeNotFoundException extends RuntimeException {
    public CourseTypeNotFoundException(String message){
        super(message);
    }
}
