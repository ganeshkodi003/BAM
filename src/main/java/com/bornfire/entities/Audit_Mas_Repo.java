package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Audit_Mas_Repo extends JpaRepository<Audit_Master , BigDecimal>{


	@Query(value = "select * from AUDIT_MASTER ", nativeQuery = true)
	List<Audit_Master> getInquirelist();
	
}
