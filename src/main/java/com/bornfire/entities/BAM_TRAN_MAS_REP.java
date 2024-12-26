
	package com.bornfire.entities;

	import java.math.BigDecimal;
	import java.util.List;

	import org.springframework.data.jpa.repository.Query;
	import org.springframework.data.repository.CrudRepository;
public interface BAM_TRAN_MAS_REP extends CrudRepository<BAM_TRAN_MAS_ENTITY, BigDecimal>{

	@Query(value = "SELECT * FROM BAM_TRAN_MAS b WHERE b.rowid IN (SELECT MIN(rowid) FROM BAM_TRAN_MAS GROUP BY asset_name)", nativeQuery = true)
	List<BAM_TRAN_MAS_ENTITY> getdata();
		
		  
		  @Query(value = "SELECT * from BAM_TRAN_MAS where asset_name = ?1 ",nativeQuery = true) 
		  List<BAM_TRAN_MAS_ENTITY>  getview(String asset_name);
		  
		  
		 
	

}
