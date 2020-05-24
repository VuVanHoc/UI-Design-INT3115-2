package com.uet.int3315.nhom8.license_test_system.error;

public class ExamScheduleNotFoundException extends RuntimeException{
    public ExamScheduleNotFoundException(String msg){
        super(msg);
    }
}
