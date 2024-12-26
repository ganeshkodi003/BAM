package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Baminventorytranrep extends JpaRepository<Baminventorytransfer, String> {

	
	@Query(value = "SELECT * FROM BAM_INVENTORY_TRANSAFER where asst_srl_npo =?1", nativeQuery = true)
	Baminventorytransfer getall(String asst_srl_npo);
	
	@Query(value = "SELECT * from BAM_INVENTORY_TRANSAFER where del_flg = 'N' and sale_flg is null ",nativeQuery = true) 
	 List<Baminventorytransfer>   getall();
	
	 @Query(value = "SELECT * from BAM_INVENTORY_TRANSAFER where asst_srl_npo = ?1 ",nativeQuery = true) 
	 Baminventorytransfer  getview(String asst_srl_npo);
	
	 @Query(value = "SELECT asst_srl_npo FROM BAM_INVENTORY_TRANSAFER ORDER BY entry_time DESC FETCH FIRST ROW ONLY", nativeQuery = true)
	 String findLatestAssetSerialNumber();	
	
   @Query(value = "SELECT * from BAM_INVENTORY_TRANSAFER where del_flg = 'Y'" ,nativeQuery = true) 
  List<Baminventorytransfer> getlist();
   
   @Query(value = "SELECT count(*) from BAM_INVENTORY_TRANSAFER where TO_CHAR(date_of_tfr,'MM-YYYY')=?1",nativeQuery = true) 
	  int gettrndata(String monthyear);
  
   @Query(value = "SELECT * FROM BAM_INVENTORY_TRANSFER WHERE ASST_XFR_REF_NO = ?1", nativeQuery = true)
   Baminventorytransfer getdate(BigDecimal asst_xfr_ref_no);
   

	@Query(value = "SELECT * FROM BAM_INVENTORY_TRANSAFER where srl_no =?1", nativeQuery = true)
	Baminventorytransfer getal(BigDecimal srl_no);
	

	 @Query(value = "SELECT * FROM BAM_INVENTORY_TRANSAFER ORDER BY entry_time DESC FETCH FIRST ROW ONLY", nativeQuery = true)
	 Baminventorytransfer getLatestTransferDate();
		 
	 @Query(value = "SELECT MAX(t.date_of_tfr) FROM BAM_INVENTORY_TRANSAFER t", nativeQuery = true)
	 Date getdatas();



}
