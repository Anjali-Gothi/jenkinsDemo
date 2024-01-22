package com.example.demoWebClient.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/anjali/test")
public class Webcontroller {
    @GetMapping("/getdata")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("Hello this is k8s testing", HttpStatus.OK);
    }
}
