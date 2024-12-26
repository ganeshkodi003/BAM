package com.bornfire.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BTMAdminAssociateProfileRep extends CrudRepository<BTMAdminAssociateProfile, String> {

	public Optional<BTMAdminAssociateProfile> findById(String resId);


	@Query(value = "select * from RESOURCE_MASTER where del_flg='N' order by RESOURCE_ID ", nativeQuery = true)
	List<BTMAdminAssociateProfile> getAssociateDeletelist();
	
	@Query(value = "select * from RESOURCE_MASTER where del_flg='N' order by RESOURCE_ID ", nativeQuery = true)
	List<BTMAdminAssociateProfile> getAssociatelist();

	@Query(value = "select * from RESOURCE_MASTER where resource_id =?1 and del_flg='N' ", nativeQuery = true)
	BTMAdminAssociateProfile getEmployeedetail(String userid);
	
	@Query(value = "select * from RESOURCE_MASTER where resource_id =?1 and del_flg='N' ", nativeQuery = true)
	BTMAdminAssociateProfile getEmployeedetail1(String resId);
	
	@Query(value = "select * from RESOURCE_MASTER where del_flg='N' ORDER BY resource_id ASC ", nativeQuery = true)
	List<BTMAdminAssociateProfile> getEmployeedetail2();

	@Query(value = "select * from RESOURCE_MASTER where resource_id =?1", nativeQuery = true)
	BTMAdminAssociateProfile getEmployeedetailList(String resId);
	
	
	@Query(value = "SELECT * FROM RESOURCE_MASTER where resource_id=?1 ", nativeQuery = true)

	BTMAdminAssociateProfile delete2(String resId);
	

}
