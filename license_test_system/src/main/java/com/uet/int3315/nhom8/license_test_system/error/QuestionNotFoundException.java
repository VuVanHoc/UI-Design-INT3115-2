package com.uet.int3315.nhom8.license_test_system.error;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(String message){
        super(message);
    }
}
