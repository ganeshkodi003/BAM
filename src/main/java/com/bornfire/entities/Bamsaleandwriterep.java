package com.bornfire.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Bamsaleandwriterep extends JpaRepository<Bamsaleandwrite, String> {
	
	@Query(value = "SELECT count(*) from BAM_SALE_AND_WRITE_OFF where TO_CHAR(date_of_sale,'MM-YYYY')=?1",nativeQuery = true) 
	  int getsaledata(String monthyear);

}
