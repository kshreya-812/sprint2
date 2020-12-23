package com.example.demo.Service;

import com.example.demo.entity.ServiceRequest;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.ServiceRequestDto;
import com.example.demo.entity.Account;

public interface ServiceRequestService {
	public ServiceRequest add(ServiceRequest request);
	public ServiceRequest create(Long reqid,Long id);
	public ServiceRequest update(Long id);
	public ServiceRequest update1(Long id);

    public List<ServiceRequest> getAllOpenServiceRequest();
	public List<ServiceRequest> getservice();
	public ServiceRequest findServiceRequestById(Long id);
	public ServiceRequest deleteRequest(Long id);

}
