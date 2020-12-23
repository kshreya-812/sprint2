package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.RechargeService;
import com.example.demo.entity.Recharge;
import com.example.demo.payload.BaseResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/recharge")
public class RechargeController {
	@Autowired
	private RechargeService rechargeservice;
	
	
	@PostMapping("/")
	@ApiOperation(value = "Add a recharge")
	public ResponseEntity<?> saveRecharge(@RequestBody Recharge recharge){
		
		Recharge requestObj = rechargeservice.saveRecharge(recharge);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(requestObj);		
		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
	}
	
}
