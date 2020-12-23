package com.example.demo.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.demo.Exception.OpenServiceFoundException;
import com.example.demo.Exception.OperationFailedException;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.RechargeRepository;
import com.example.demo.Repository.ServiceRequestRepository;
import com.example.demo.dto.ServiceRequestDto;
import com.example.demo.entity.Account;
import com.example.demo.entity.Recharge;
import com.example.demo.entity.ServiceRequest;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import static com.example.demo.util.AppConstants.REQUEST_NOT_FOUND_CONST;
import static com.example.demo.util.AppConstants.OPERATION_FAILED_CONST;
import static com.example.demo.util.AppConstants.OPEN_SERVICE_FOUND_CONST;
import static com.example.demo.util.AppConstants.RECHARGE_NOT_FOUND;




@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {
	
	@Autowired
	private ServiceRequestRepository requestRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
    private RechargeRepository rechargeRepository;


		
	@Transactional
	@Override
	public ServiceRequest add(ServiceRequest request) {
		ServiceRequest requestObj = null;
		try {
			
			requestObj = requestRepository.save(request);
			
		}catch(Exception e) {
			throw new OperationFailedException(OPERATION_FAILED_CONST+e.getMessage());
		}		
		return requestObj;
	}
		
	
	@Override
	public ServiceRequest findServiceRequestById(Long id) {
		//ServiceRequest request=requestRepository.findById(id)
		//.orElseThrow(() -> new ResourceNotFoundException("Request not found for this id :: " + id));
		Optional<ServiceRequest> request = requestRepository.findById(id);

		 if (!request.isPresent())
		      throw new ResourceNotFoundException(REQUEST_NOT_FOUND_CONST + id);	
		      
			
			return request.get();
		}

/*	@Transactional
	@Override
	public ServiceRequest Create(ServiceRequest request) {
		ServiceRequest reqObj = new ServiceRequest();
		ServiceRequest requestObj=null;
		try {
			requestObj.setId(request.getId());
			requestObj.setMessage("Opened Service");
			requestObj.setRequestdate(LocalDate.now());
			requestObj.setStatusOpened(true);
			requestObj.setAccount(request.getAccount());
			
		reqObj= requestRepository.save(requestObj);
			
		}catch(Exception e) {
			throw new OperationFailedException(OPERATION_FAILED_CONST+e.getMessage());
		}		
		return reqObj;
	}*/

	@Override
	public List<ServiceRequest> getAllOpenServiceRequest() {
		List<ServiceRequest> req=requestRepository.findAll();
		List<ServiceRequest> req1=req.stream().filter(x->x.isStatusOpened()==true).collect(Collectors.toList());

		return req1;
	}


	@Transactional
	@Override
	public ServiceRequest update1(Long id) {
		ServiceRequest updateService=null;
		
		Optional<ServiceRequest> request=requestRepository.findById(id);
		Account account=request.get().getAccount();
		List<ServiceRequest> req=account.getRequest();
		if (!request.isPresent()) {
			throw new ResourceNotFoundException(REQUEST_NOT_FOUND_CONST + id);
		} else {
			int days=account.getCurrentPack().getDaysValidity();
			LocalDate date=rechargeRepository.PurchesedDate(account.getId()).plusDays(days);
			LocalDate demo=LocalDate.now();
			if(request.get().getRequestdate().isBefore(LocalDate.of(2021, 01, 01)))
			{
				request.get().setMessage("close");
				request.get().setStatusOpened(false);
			}else {
				throw new ResourceNotFoundException(RECHARGE_NOT_FOUND + id);
			}
			try {
				updateService=  requestRepository.saveAndFlush(request.get());
				req.add(updateService);
			}catch(Exception e) {
				throw new OperationFailedException(OPERATION_FAILED_CONST+e.getMessage());
			}			
		}
		return updateService;
		
		
	}

	@Transactional
	@Override
	public ServiceRequest update(Long id) {
		ServiceRequest req=null;
		Optional<ServiceRequest> request=requestRepository.findById(id);
		Account account=request.get().getAccount();
		if (!request.isPresent()) {
			throw new ResourceNotFoundException(REQUEST_NOT_FOUND_CONST + id);
		} else {
			int days=account.getCurrentPack().getDaysValidity();
			LocalDate date=rechargeRepository.PurchesedDate(account.getId()).plusDays(days);
			//LocalDate date=LocalDate.of(2021, 02, 01);
			if(request.get().getRequestdate().isAfter(date))
			{
				throw new ResourceNotFoundException(RECHARGE_NOT_FOUND + id);
			}
			else
			try {
				  requestRepository.closeRequest(date);
			}
			catch(Exception e) {
				throw new OperationFailedException(OPERATION_FAILED_CONST+e.getMessage());
			}			
		}
		return request.get();
		
		
	}

	@Override
	public List<ServiceRequest> getservice() {
		return requestRepository.getJoin();
	}
	
	@Override
	public ServiceRequest deleteRequest(Long id) {
		Optional<ServiceRequest> request=requestRepository.findById(id);
		if (!request.isPresent()) {
			throw new ResourceNotFoundException(REQUEST_NOT_FOUND_CONST + id);
		} else {
			try {
				requestRepository.delete(request.get());
			
			}catch(Exception e) {
				throw new OperationFailedException(OPERATION_FAILED_CONST+e.getMessage());
			}			
		}
		
		return request.get();
	}

   @Transactional
	@Override
	public ServiceRequest create(Long reqid,Long id) {
		ServiceRequest req=null;
		LocalDate purchaseDate=rechargeRepository.PurchesedDate(id);
		boolean flag=false;
		Optional<Account> account=accountRepository.findById(id);
		int days=account.get().getCurrentPack().getDaysValidity();
		List<ServiceRequest> list=account.get().getRequest();
		for(ServiceRequest a:list) {
		if(a.isStatusOpened()==true)
			flag=true;
		}if (flag == true) {
			throw new ResourceNotFoundException(OPEN_SERVICE_FOUND_CONST + id);
		}
		else  {
	    req=new ServiceRequest();
        
		req.setId(reqid);
		req.setMessage("Service is opened");
		req.setRequestdate(purchaseDate.plusDays(days));
		req.setStatusOpened(true);
		req.setAccount(account.get());
		try {		 
			//req=requestRepository.save(req);
			list.add(req);
			account.get().setRequest(list);
		}catch(Exception e) {
			throw new OperationFailedException(OPERATION_FAILED_CONST+e.getMessage());
		}			
		}
		
		return req;
		
   }
   }
