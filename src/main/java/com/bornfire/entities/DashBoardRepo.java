package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashBoardRepo extends CrudRepository<DashBoardEntity, String> {
	
	@Query(value = "SELECT * FROM DASHBOARD_MANAGER", nativeQuery = true)
	List<DashBoardEntity> getWorkAssignlist();
	
	@Query(value = "SELECT DISTINCT screen_id FROM DASHBOARD_MANAGER", nativeQuery = true)
	List<String>  getdatas();
	
	@Query(value = "SELECT * FROM DASHBOARD_MANAGER where screen_id =?1", nativeQuery = true)
	DashBoardEntity getWorkAssign(String screen_id);
	
	@Query(value = "select * from DASHBOARD_MANAGER where screen_id =?1", nativeQuery = true)
	List<DashBoardEntity> getEmployeedetail2(String screen_id);


	
}
