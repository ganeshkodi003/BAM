package com.bornfire.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bornfire.entities.BAMInventorymaster;
import com.bornfire.entities.BAMInventryMastRep;
import com.bornfire.entities.BTMDocumentMaster;
import com.bornfire.entities.GeneralLedgerWork_Entity;

@Service
@Transactional
@ConfigurationProperties("output")
public class DocFileUploadServices {
	
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;
	
	@Autowired
	BAMInventryMastRep BAMInventryMastRep;
	
	@Autowired
	ExcelUploadService excelUploadService;
	
	private static String UPLOAD_FOLDER = "C://test//";
	
	public String  MultipartToFile(BTMDocumentMaster btmDocumentMaster, MultipartFile file, String fileName,String formmode)
			throws IllegalStateException, IOException {
		Session hs = sessionFactory.getCurrentSession();
		String msg= "";
		if (formmode.equals("add")) {
		Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
		System.out.println(path);
		
		try (InputStream is = file.getInputStream(); OutputStream os = Files.newOutputStream(path)) {
			byte[] buffer = new byte[4096];
			BTMDocumentMaster up = btmDocumentMaster;
			hs.saveOrUpdate(up);
			
			is.read(buffer);
			
			
			int read = 0;
			while ((read = is.read(buffer)) > 0) {
				os.write(buffer, 0, read);
			}
			
			
				
			}catch (Exception e) {
				return msg="Internal_server_error";
			}
		
		msg = "File Uploaded Successfully";
		}
		
		return msg;
}
	
	public String UploadserviceCOLLECTION(String screenId, MultipartFile file, String userid,
			BAMInventorymaster BAMInventorymaster)
			throws SQLException, FileNotFoundException, IOException, NullPointerException {
		System.out.println("Entering third Service Succesfully of GST EXCEL UPLOAD");

		String fileName = file.getOriginalFilename();

		String fileExt = "";
		String msg = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			fileExt = fileName.substring(i + 1);
		}

		if (fileExt.equals("xlsx") || fileExt.equals("xls")) {

			try {	
				Workbook workbook = WorkbookFactory.create(file.getInputStream());
				List<HashMap<Integer, String>> mapList = new ArrayList<HashMap<Integer, String>>();
				for (Sheet s : workbook) {
					for (Row r : s) {

						if (!isRowEmpty(r)) {
							if (r.getRowNum() == 0) {
								continue;
							}
							HashMap<Integer, String> map = new HashMap<>();

							for (int j = 0; j < 200; j++) {

								Cell cell = r.getCell(j);
								DataFormatter formatter = new DataFormatter();
								String text = formatter.formatCellValue(cell);
								map.put(j, text);
							}
							mapList.add(map);
						}
					}
				}

				for (HashMap<Integer, String> item : mapList) {

					BAMInventorymaster PO = new BAMInventorymaster(); 

					String asst_srl_no  = item.get(0);
					System.out.println("asst_srl_no: " + asst_srl_no);  

					String description = item.get(1);
					System.out.println("description: " + description);

					String asst_sub_category = item.get(2);
					System.out.println("asst_sub_category: " + asst_sub_category); 
					
					String asst_category = item.get(3);
					System.out.println("asst_category: " + asst_category);    

					String asst_head = item.get(4);
					System.out.println("asst_head: " + asst_head);

					String loc_type = item.get(5);
					System.out.println("loc_type: " + loc_type); 
					
					String sol_id = item.get(6);
					System.out.println("sol_id: " + sol_id); 
					
					String datePattern = "yyyy-MM-dd"; // Correct pattern for dates like "2009-10-09"
					SimpleDateFormat inputDateFormat = new SimpleDateFormat(datePattern);

					String outputDatePattern = "yyyy/MM/dd"; // Desired format
					SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputDatePattern);

					String Purchase_date = item.get(7); // Assuming this is the date string in the format "DATE(2009,10,9)"
					System.out.println("Purchase_date: " + Purchase_date);  
					Date date_value1 = null;

					if (Purchase_date != null && !Purchase_date.isEmpty()) {
					    try {
					        // Remove the "DATE(" and ")" from the string
					        Purchase_date = Purchase_date.replace("DATE(", "").replace(")", "").replace(",", "-");
					        
					        // Now the date should be in the format "2009-10-09"
					        date_value1 = inputDateFormat.parse(Purchase_date);
					        
					        // Format the date to the desired "yyyy/MM/dd" format
					        String formattedDate = outputDateFormat.format(date_value1);
					        
					        System.out.println("Formatted date: " + formattedDate); // Output will be in "yyyy/MM/dd"
					    } catch (ParseException e) {
					        e.printStackTrace();
					    }
					} else {
					    System.out.println("Record Date is null");
					}
					
					String Purchase_year = item.get(8); // Assuming this is the date string in the format "DATE(2009,10,9)"
					System.out.println("Purchase_year: " + Purchase_year);  
					Date date_value2 = null;

					if (Purchase_year != null && !Purchase_year.isEmpty()) {
					    try {
					        // Remove the "DATE(" and ")" from the string
					    	Purchase_year = Purchase_year.replace("DATE(", "").replace(")", "").replace(",", "-");
					        
					        // Now the date should be in the format "2009-10-09"
					        date_value2 = inputDateFormat.parse(Purchase_year);
					        
					        // Format the date to the desired "yyyy/MM/dd" format
					        String formattedDate = outputDateFormat.format(date_value2);
					        
					        System.out.println("Formatted date: " + formattedDate); // Output will be in "yyyy/MM/dd"
					    } catch (ParseException e) {
					        e.printStackTrace();
					    }
					} else {
					    System.out.println("Record Date is null");
					}
					
					BigDecimal org_cost = new BigDecimal(item.get(9));
					System.out.println("org_cost: " + org_cost); 
					
					String depr_method = item.get(10);
					System.out.println("depr_method: " + depr_method); 
					
					BigDecimal depr_per  = new BigDecimal(item.get(11));
					System.out.println("depr_per: " + depr_per);  
 
					BigDecimal depr_amt = new BigDecimal(item.get(12));
					System.out.println("depr_amt: " + depr_amt); 

					Date last_depr = new Date(item.get(13));
					System.out.println("last_depr: " + last_depr);  
					
					BigDecimal book_value = new BigDecimal(item.get(14));
					System.out.println("book_value: " + book_value); 
					
					PO.setAsst_srl_no(asst_srl_no);
					PO.setCategory_desc(asst_category); 
					PO.setAsset_sub_category(asst_sub_category);
					PO.setAsset_category(asst_category);
					PO.setAsset_head(asst_head);
					PO.setLoc_type(loc_type);
					PO.setSol_id(sol_id);;
					PO.setDate_of_purchase(date_value1);
					PO.setYear_of_purchase(date_value2);
					PO.setOrg_cost(org_cost);
					PO.setDepr_method(depr_method);
					PO.setDepr_percent(depr_per);
					PO.setNom_depr_amt(depr_amt);
					PO.setDate_of_last_depr(last_depr);
					PO.setCur_book_value(book_value);
					
					PO.setDel_flg("N");
					PO.setEntity_flg("N");
					PO.setModify_flg("N");
					PO.setVerify_flg("N");
					PO.setEntry_time(new Date());
					PO.setModify_time(new Date());
					PO.setEntry_user(userid);
					PO.setModify_user(userid);
							
					BAMInventryMastRep.save(PO);

					msg = "Excel Data Uploaded Successfully";
				}
			} catch (Exception e) {
				e.printStackTrace();
				msg = "File has not been successfully uploaded";
			}
		}
		return msg;

	}

	private boolean isRowEmpty(Row row) {
		boolean isEmpty = true;
		DataFormatter dataFormatter = new DataFormatter();

		if (row != null) {
			for (Cell cell : row) {
				if (dataFormatter.formatCellValue(cell).trim().length() > 0) {
					isEmpty = false;
					break;
				}
			}
		}
		return isEmpty;
	}
	
	public String Uploadgstserviceone(String screenId, MultipartFile file, String userid, GeneralLedgerWork_Entity GeneralLedgerWork_Entity)
	        throws FileNotFoundException, SQLException, IOException, NullPointerException {
	    System.out.println("first tservice testing GST EXCEL UPLOAD");
	    
	    // Create an instance of FileUploadServices
	 //   FileUploadServices fileUploadServices = new FileUploadServices();
	    
	    // Call the non-static UploadPO method on the instance
	    String msg = excelUploadService.Uploadgstservicetwo(screenId, file, userid, GeneralLedgerWork_Entity);
	    
	    return msg;
	}
	
	
	
}
