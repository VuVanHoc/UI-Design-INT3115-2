package com.uet.int3315.nhom8.license_test_system.error;

public class ExamScheduleExistException extends RuntimeException {
    public ExamScheduleExistException(String msg){
        super(msg);
    }
}
