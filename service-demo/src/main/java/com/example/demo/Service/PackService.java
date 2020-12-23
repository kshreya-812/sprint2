package com.example.demo.Service;

import static com.example.demo.util.AppConstants.OPERATION_FAILED_CONST;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.OperationFailedException;
import com.example.demo.Repository.PackRepository;
import com.example.demo.entity.Pack;

@Service
public class PackService {
	@Autowired
	private PackRepository packRepository;
	
	@Transactional
	public Pack savePack(Pack pack) {
		Pack packObj = null;
		try {
			packObj = packRepository.save(pack);
			
		}catch(Exception e) {
			throw new OperationFailedException(OPERATION_FAILED_CONST+e.getMessage());
		}		
		return packObj;
	}

}
