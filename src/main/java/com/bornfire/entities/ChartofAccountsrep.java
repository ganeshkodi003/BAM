package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChartofAccountsrep extends JpaRepository<ChartOfAccounts, String> {
	
	public Optional<ChartOfAccounts> findById(String asset_serial_no);

	@Query(value = "SELECT * FROM CHART_OF_ACCOUNTS", nativeQuery = true)
	List<ChartOfAccounts> getWorkAssignlist();
	
	@Query(value = "SELECT * FROM CHART_OF_ACCOUNTS where asset_serial_no =?1", nativeQuery = true)
	ChartOfAccounts getWorkAssign(String asset_serial_no);
	
	@Query(value = "select * from CHART_OF_ACCOUNTS where asset_serial_no =?1", nativeQuery = true)
	List<ChartOfAccounts> getEmployeedetail2(String asset_serial_no);


}
