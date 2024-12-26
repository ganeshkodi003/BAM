package com.bornfire.entities;

import java.math.BigDecimal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Audit_Repo extends JpaRepository<AUDIT_ENTITY , BigDecimal>{


	@Query(value = "select * from AUD_TABLE ", nativeQuery = true)
	List<AUDIT_ENTITY> getInquirelist();
	
	/*
	 * @Query(value = "select * from AUD_SERVICES where sl_no =?1", nativeQuery =
	 * true) List<AUDIT_ENTITY> findbyId(BigDecimal sl_no);
	 */
	//@Query(value = "select count(*) from AUD_SERVICES where create_cust_id=?1 ", nativeQuery = true)
	//String getusercount(String create_cust_id);
	
	// @Query(value = "select AUD_SERVICES_SL_NO.nextval from dual", nativeQuery = true)
	 //String SrlNo();	 
		
	
}