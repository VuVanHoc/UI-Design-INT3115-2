package com.uet.int3315.nhom8.license_test_system.error;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String msg){
        super(msg);
    }
}
