package com.example.demo.Service;
import static com.example.demo.util.AppConstants.OPERATION_FAILED_CONST;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.OperationFailedException;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.entity.Account;
import com.example.demo.entity.Recharge;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Transactional
	public Account saveAccount(Account account) {
		Account accountObj = null;
		try {
			accountObj = accountRepository.save(account);
			
		}catch(Exception e) {
			throw new OperationFailedException(OPERATION_FAILED_CONST+e.getMessage());
		}		
		return accountObj;
	}

	}
