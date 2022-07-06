package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController{

    public TestController() {
    }

    @GetMapping("test")
    public ResponseEntity test(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
