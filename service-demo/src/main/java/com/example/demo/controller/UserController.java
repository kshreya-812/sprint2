package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.UserService;
import com.example.demo.entity.User;
import com.example.demo.payload.BaseResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/recharge")
public class UserController {
	@Autowired
	private UserService userservice;
}