package com.CourseTodoCode.educationalplatform.controller;

import com.CourseTodoCode.educationalplatform.dto.AuthLoginRequestDTO;
import com.CourseTodoCode.educationalplatform.dto.AuthResponseDTO;
import com.CourseTodoCode.educationalplatform.service.UserDetailsServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthLoginRequestDTO userRequest){
        return new ResponseEntity<>(userDetailsServiceImp.loginUser(userRequest), HttpStatus.OK);
    }

}
