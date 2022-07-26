package com.example.springweb.controller;

import com.example.backendservicecore.model.dto.LoginDTO;
import com.example.backendservicecore.model.dto.UserDataDTO;
import com.example.backendservicecore.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UserDataController {
    @Autowired
    private UserDataService userDataService;

    @PostMapping("")
    public ResponseEntity<UserDataDTO> login(@RequestBody(required = false) LoginDTO loginDTO) throws Exception {
        System.out.println("Failed to login");
        return new ResponseEntity<>(userDataService.logIn(loginDTO), HttpStatus.CREATED);
    }
}
