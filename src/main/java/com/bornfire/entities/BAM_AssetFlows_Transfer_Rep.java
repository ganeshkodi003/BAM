package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BAM_AssetFlows_Transfer_Rep extends JpaRepository<BAM_AssetFlows_Transfer_Entity, BigDecimal> {
    
    @Query(value = "SELECT * from FLOW_ASSETS_TRANSFER where asset_serial_no = ?1 ",nativeQuery = true) 
	  Optional<BAM_AssetFlows_Transfer_Entity>  findById(String asset_serial_no);
	  
}
