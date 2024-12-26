package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BamGeneralLedgerRep extends CrudRepository<BamGeneralLedger,String> {
	
	public Optional<BamGeneralLedger> findById(String refId);
	
	@Query(value = "SELECT * from GENERAL_LED WHERE del_flg='N' ", nativeQuery = true)
	List<BamGeneralLedger> getRefCodelist();
	
	@Query(value = "SELECT * from GENERAL_LED WHERE GL_CODE=?1 ", nativeQuery = true)
	BamGeneralLedger getRefMaster(String GL_CODE);

}
