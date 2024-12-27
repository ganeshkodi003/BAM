package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BAMInventryMastRep extends JpaRepository<BAMInventorymaster, String> {

	 @Query(value = "SELECT DISTINCT asst_srl_no FROM BAM_INVENTORY_MASTER", nativeQuery = true)
	    List<String>  getdatas();
	 
	 @Query(value = "SELECT * from BAM_INVENTORY_MASTER where asst_srl_no = ?1 ",nativeQuery = true) 
	 BAMInventorymaster  getview(String asst_srl_no);
	

	 @Query(value = "SELECT * from BAM_INVENTORY_MASTER where del_flg = 'N' and sale_flg is null ",nativeQuery = true) 
	 List<BAMInventorymaster>   getall();


	 @Query(value = "SELECT * FROM BAM_INVENTORY_MASTER ORDER BY entry_time DESC FETCH FIRST ROW ONLY", nativeQuery = true)
	 BAMInventorymaster findLatestRecord();
	 	 
	 @Query(value = "SELECT asst_srl_no FROM BAM_INVENTORY_MASTER ORDER BY entry_time DESC FETCH FIRST ROW ONLY", nativeQuery = true)
	 String findLatestAssetSerialNumber();
 
	 @Query(value = "SELECT count(*) from BAM_INVENTORY_MASTER where TO_CHAR(date_of_purchase,'MM-YYYY')=?1",nativeQuery = true) 
	  int getdata(String monthyear);
	 
	 @Query(value = "SELECT count(*) from BAM_INVENTORY_MASTER where date_of_purchase>=TO_DATE(?1,'dd-MM-YYYY') and date_of_purchase<=TO_DATE(?2,'dd-MM-YYYY')",nativeQuery = true) 
	  int gettotal(String stryear,String endyear);
	 
	 //----------------------------------categories graph-------------------------------------------------------------------------------------------------
	 
	 @Query(value = "SELECT SUM(CUR_BOOK_VALUE) from BAM_INVENTORY_MASTER where date_of_purchase>=TO_DATE(?1,'dd-MM-YYYY') and date_of_purchase<=TO_DATE(?2,'dd-MM-YYYY')",nativeQuery = true) 
	  int gettotalvalue(String stryear,String endyear);
	 
	 
	 @Query(value = "SELECT SUM(CUR_BOOK_VALUE) from BAM_INVENTORY_MASTER where asset_category like 'A%' and date_of_purchase>=TO_DATE(?1,'dd-MM-YYYY') and date_of_purchase<=TO_DATE(?2,'dd-MM-YYYY')",nativeQuery = true) 
	  int getFurnitureFittings(String stryear,String endyear );
	 
	 @Query(value = "SELECT SUM(CUR_BOOK_VALUE) from BAM_INVENTORY_MASTER where asset_category like 'B%' and date_of_purchase>=TO_DATE(?1,'dd-MM-YYYY') and date_of_purchase<=TO_DATE(?2,'dd-MM-YYYY')",nativeQuery = true) 
	  int getMachineryPlant(String stryear,String endyear );
	 
	 @Query(value = "SELECT SUM(CUR_BOOK_VALUE) from BAM_INVENTORY_MASTER where asset_category like 'C%' OR asset_category like 'D%' and date_of_purchase>=TO_DATE(?1,'dd-MM-YYYY') and date_of_purchase<=TO_DATE(?2,'dd-MM-YYYY')",nativeQuery = true) 
	  int getPremises(String stryear,String endyear );
	 
	 //**************FOR LOCATION TYPE GRAPH*****************************************************************************************************************************
	 @Query(value = "SELECT count(*) from BAM_INVENTORY_MASTER where loc_type='OFF' and date_of_purchase>=TO_DATE(?1,'dd-MM-YYYY') and date_of_purchase<=TO_DATE(?2,'dd-MM-YYYY')",nativeQuery = true) 
	  int getoffice(String stryear,String endyear );
	 
	 @Query(value = "SELECT count(*) from BAM_INVENTORY_MASTER where loc_type='BRN' and date_of_purchase>=TO_DATE(?1,'dd-MM-YYYY') and date_of_purchase<=TO_DATE(?2,'dd-MM-YYYY')",nativeQuery = true) 
	  int getbranch(String stryear,String endyear );
	 
	 @Query(value = "SELECT count(*) from BAM_INVENTORY_MASTER where loc_type='QUA' and date_of_purchase>=TO_DATE(?1,'dd-MM-YYYY') and date_of_purchase<=TO_DATE(?2,'dd-MM-YYYY')",nativeQuery = true) 
	  int getquarters(String stryear,String endyear );
	 
	 @Query(value = "SELECT count(*) from BAM_INVENTORY_MASTER where loc_type='GEN' and date_of_purchase>=TO_DATE(?1,'dd-MM-YYYY') and date_of_purchase<=TO_DATE(?2,'dd-MM-YYYY')",nativeQuery = true) 
	  int getgeneral(String stryear,String endyear );
	 
	//***************FOR INVENTORIES GRAPH*********************************************************************************************************************************
	 
	 @Query(value = "SELECT count(*) FROM BAM_INVENTORY_MASTER WHERE (TO_CHAR(date_of_purchase, 'MM') = '04') AND (TO_CHAR(date_of_purchase, 'YYYY') BETWEEN ?1 AND ?2)", nativeQuery = true)
	 int getAPR(String startYear, String endYear);

	 @Query(value = "SELECT count(*) FROM BAM_INVENTORY_MASTER WHERE (TO_CHAR(date_of_purchase, 'MM') = '05') AND (TO_CHAR(date_of_purchase, 'YYYY') BETWEEN ?1 AND ?2)", nativeQuery = true)
	 int getMAY(String startYear, String endYear);

	 @Query(value = "SELECT count(*) FROM BAM_INVENTORY_MASTER WHERE (TO_CHAR(date_of_purchase, 'MM') = '06') AND (TO_CHAR(date_of_purchase, 'YYYY') BETWEEN ?1 AND ?2)", nativeQuery = true)
	 int getJUN(String startYear, String endYear);

	 @Query(value = "SELECT count(*) FROM BAM_INVENTORY_MASTER WHERE (TO_CHAR(date_of_purchase, 'MM') = '07') AND (TO_CHAR(date_of_purchase, 'YYYY') BETWEEN ?1 AND ?2)", nativeQuery = true)
	 int getJULY(String startYear, String endYear);

	 @Query(value = "SELECT count(*) FROM BAM_INVENTORY_MASTER WHERE (TO_CHAR(date_of_purchase, 'MM') = '08') AND (TO_CHAR(date_of_purchase, 'YYYY') BETWEEN ?1 AND ?2)", nativeQuery = true)
	 int getAUG(String startYear, String endYear);

	 @Query(value = "SELECT count(*) FROM BAM_INVENTORY_MASTER WHERE (TO_CHAR(date_of_purchase, 'MM') = '09') AND (TO_CHAR(date_of_purchase, 'YYYY') BETWEEN ?1 AND ?2)", nativeQuery = true)
	 int getSEP(String startYear, String endYear);
	 
	 @Query(value = "SELECT count(*) FROM BAM_INVENTORY_MASTER WHERE (TO_CHAR(date_of_purchase, 'MM') = '10') AND (TO_CHAR(date_of_purchase, 'YYYY') BETWEEN ?1 AND ?2)", nativeQuery = true)
	 int getOCT(String startYear, String endYear);

	 @Query(value = "SELECT count(*) FROM BAM_INVENTORY_MASTER WHERE (TO_CHAR(date_of_purchase, 'MM') = '11') AND (TO_CHAR(date_of_purchase, 'YYYY') BETWEEN ?1 AND ?2)", nativeQuery = true)
	 int getNOV(String startYear, String endYear);

	 @Query(value = "SELECT count(*) FROM BAM_INVENTORY_MASTER WHERE (TO_CHAR(date_of_purchase, 'MM') = '12') AND (TO_CHAR(date_of_purchase, 'YYYY') BETWEEN ?1 AND ?2)", nativeQuery = true)
	 int getDEC(String startYear, String endYear);

	 @Query(value = "SELECT count(*) FROM BAM_INVENTORY_MASTER WHERE (TO_CHAR(date_of_purchase, 'MM') = '01') AND (TO_CHAR(date_of_purchase, 'YYYY') BETWEEN ?1 AND ?2)", nativeQuery = true)
	 int getJAN(String startYear, String endYear);

	 @Query(value = "SELECT count(*) FROM BAM_INVENTORY_MASTER WHERE (TO_CHAR(date_of_purchase, 'MM') = '02') AND (TO_CHAR(date_of_purchase, 'YYYY') BETWEEN ?1 AND ?2)", nativeQuery = true)
	 int getFEB(String startYear, String endYear);

	 @Query(value = "SELECT count(*) FROM BAM_INVENTORY_MASTER WHERE (TO_CHAR(date_of_purchase, 'MM') = '03') AND (TO_CHAR(date_of_purchase, 'YYYY') BETWEEN ?1 AND ?2)", nativeQuery = true)
	 int getMAR(String startYear, String endYear);
	 
	 @Query(value = "SELECT * from BAM_INVENTORY_MASTER where verify_flg = 'N'AND DEL_FLG ='N'",nativeQuery = true) 
	 List<BAMInventorymaster> getunverified();

	 
	 


}
