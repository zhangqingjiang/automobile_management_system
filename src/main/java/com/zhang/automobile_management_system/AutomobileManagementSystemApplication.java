package com.zhang.automobile_management_system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
@Slf4j
@SpringBootApplication
public class AutomobileManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomobileManagementSystemApplication.class, args);
    }
}
