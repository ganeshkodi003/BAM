package com.bornfire.entities;

 

import java.util.List;
import java.util.Optional;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

 

@Repository
public interface spf_repo extends JpaRepository <spf_entity, String>{
	@Query(value = "SELECT * FROM BSPF_BTM WHERE salary_month = ?1 order by emp_no", nativeQuery = true)
	List<spf_entity> getall(String Month);
	
	@Query(value = "SELECT * FROM BSPF_BTM WHERE salary_month = ?1 order by emp_no", nativeQuery = true)
	List<spf_entity> getData(String Month);
	
	@Query(value = "SELECT * FROM BSPF_BTM where CTC_AMT<'300000' and ESI!='0' and salary_month=?1 ", nativeQuery = true)
	List<spf_entity> getESI(String Month);
	
	@Query(value = "SELECT * FROM BSPF_BTM where unique_id=?1 ", nativeQuery = true)
	spf_entity findit(String a);
}