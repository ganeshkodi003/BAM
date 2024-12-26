package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BamAcquisitionrep extends JpaRepository<BamAcquisition,String> {
	public Optional<BamAcquisition> findById(String assetName);
	
	@Query(value = "SELECT * from BAM_ACQUISITION", nativeQuery = true)
	List<BamAcquisition> getBamAcquisitionlist();
	
	@Query(value = "SELECT * FROM BAM_ACQUISITION where ASSET_NAME =?1", nativeQuery = true)
	BamAcquisition getall(String headcode);
	
	
	
}
