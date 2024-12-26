package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Inventory_Masterrep extends CrudRepository<Inventory_Master, String> {
	public Optional<Inventory_Master> findById(String empId);

	@Query(value = "SELECT * FROM inventory_master", nativeQuery = true)
	List<Inventory_Master> getWorkAssignlist();
	
	@Query(value = "SELECT * FROM inventory_master where asset_serial_number =?1", nativeQuery = true)
	Inventory_Master getWorkAssign(String AssetNo);

}
