package com.example.demo.Service;

import static com.example.demo.util.AppConstants.OPERATION_FAILED_CONST;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Exception.OperationFailedException;
import com.example.demo.Repository.RechargeRepository;
import com.example.demo.entity.Recharge;

@Service
public class RechargeService {
	@Autowired
	private RechargeRepository rechargeRepository;
	
	
	@Transactional
	public Recharge saveRecharge(Recharge recharge) {
		Recharge rechargeObj = null;
		List<Recharge> list=rechargeObj.getAccount().getRecharge();
		try {
			rechargeObj = rechargeRepository.save(recharge);
			list.add(rechargeObj);
			
		}catch(Exception e) {
			throw new OperationFailedException(OPERATION_FAILED_CONST+e.getMessage());
		}		
		return rechargeObj;
	}
	
	public Recharge updatepurchaesedDate(Long id) {
		Optional<Recharge> recharge=null;
		Recharge Update=null;
		recharge=rechargeRepository.findById(id);
		List<Recharge> list=recharge.get().getAccount().getRecharge();
		if(!recharge.isPresent()) {
			System.out.println("No Recharge");
		}
		else {
			recharge.get().setPurchasedDate(LocalDate.of(2020, 12, 21));
			//Update=rechargeRepository.saveAndFlush(entity);
		}
		
		return null;
		
	}

   // Recharge createRecharge(Pack pack, Account account);

    //Recharge update(Recharge recharge);

   // List<Recharge> findRechargesForUserInDescendingOrderByPurchasedDate(Account account);

    //int rechargesForUserCount(Account account);

    //List<Recharge> findAllRechargesInPeriod(LocalDate startDate, LocalDate endDate);

    //int countRechargesInPeriod(LocalDate startDate, LocalDate endDate);

    /**
     * calculates revenue by add of all recharges
     */
   // double totalRevenueInPeriod(LocalDate startDate, LocalDate endDate);

    /**
     *
     * recharges done on a pack
     */
   // int rechargesCount(Pack pack);

    /**
     * expire recharge if validity is over, mark active flag as false, also remove current plan from account
     */
   // Recharge expireIfValidityFinished(Account account ,Recharge recharge
}
