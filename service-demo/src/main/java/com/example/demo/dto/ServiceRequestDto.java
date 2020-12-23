package com.example.demo.dto;


public class ServiceRequestDto {

	private Long accid;
	private String message;
	public ServiceRequestDto(Long accid, String message) {
		super();
		this.accid = accid;
		this.message = message;
	}
	@Override
	public String toString() {
		return "ServiceRequestDto [accid=" + accid + ", message=" + message + "]";
	}
	
	
	
	
	
	
}
