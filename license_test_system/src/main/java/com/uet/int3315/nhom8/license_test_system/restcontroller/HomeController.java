package com.uet.int3315.nhom8.license_test_system.restcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/home")
    public String home() {
        return "Signin";
    }
}
