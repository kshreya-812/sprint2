package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Account;
import com.example.demo.entity.ServiceRequest;
import com.example.demo.payload.BaseResponse;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Service.ServiceRequestService;
import com.example.demo.dto.ServiceRequestDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("/servicerequest")
public class ServiceRequestController {
	
	@Autowired
	private ServiceRequestService requestService;
	
	@PostMapping("/")
	@ApiOperation(value = "Add a request")
	public ResponseEntity<?> saveRequest(@RequestBody ServiceRequest request){
		
		ServiceRequest requestObj = requestService.add(request);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(requestObj);		
		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value="find Service by Id")
    public ResponseEntity<ServiceRequest> fetchProductDetails(@PathVariable("id") Long id) {
		
		ServiceRequest request = null;
		try {
			request = requestService.findServiceRequestById(id);			
		}
		catch(ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());			
		}
		
		return new ResponseEntity<>(request, HttpStatus.OK);		
		
	}
	
	@GetMapping("/{reqid}/{id}")
	@ApiOperation(value = "Add a Request")
	public ResponseEntity<?> createRequest(@PathVariable("reqid") Long reqid,@PathVariable("id") Long id){
		
		ServiceRequest requestObj = requestService.create(reqid,id);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(requestObj);		
		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	@ApiOperation(value = "Show All Open Request")
	public ResponseEntity<?> fetchAllRequest() {
		
		List<ServiceRequest> request = requestService.getAllOpenServiceRequest();
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(request);	
		
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);		
		
	}
	
	@GetMapping("/allservice")
	@ApiOperation(value = "Show All open service request")
	public ResponseEntity<?> fetchAllOpenServiceRequest() {
		
		List<ServiceRequest> request = requestService.getservice();
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(request);	
		
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);		
		
	}
	@PutMapping("/close Request{id}")
	@ApiOperation(value = "close the request")
	public ResponseEntity<?> closeRequest(@PathVariable("id") Long id) {
		
		ServiceRequest request =requestService.update(id);
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(request);	
		
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);			
	}
	
	@DeleteMapping("/delete Request{id}")
	@ApiOperation(value = "delete  request")
	public ResponseEntity<?> deleteRequest(@PathVariable("id") Long id) {
		
		ServiceRequest request =requestService.deleteRequest(id);
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(request);	
		
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);			
	}
	@PutMapping("/ Request{id}")
	@ApiOperation(value = "close the request")
	public ResponseEntity<?> closeRequesttry(@PathVariable("id") Long id) {
		
		ServiceRequest request =requestService.update1(id);
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(request);	
		
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);			
	}
	
}
