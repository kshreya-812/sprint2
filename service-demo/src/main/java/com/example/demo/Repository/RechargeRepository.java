package com.example.demo.Repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Recharge;

public interface RechargeRepository extends JpaRepository<Recharge,Long> {

@Query("Select r.purchasedDate from Recharge r Join r.account a where a.id = :id")
public LocalDate PurchesedDate(@Param("id") Long id);

}
