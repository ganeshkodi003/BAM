package com.bornfire.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
public class ReportServices {

	@Autowired
	Environment env;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;

	public File getFileAttendance(String emp_id, String cal_month, String cal_year, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Attendance_Report" + emp_id + "_" + cal_month + "_" + cal_year;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/AttendanceRegister_Report.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/AttendanceRegister_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			// logger.info("Assigning Parameters for Jasper");

			map.put("emp_id", emp_id);
			map.put("cal_month", cal_month);
			map.put("cal_year", cal_year);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileDailyAttendance(String cal_year, String cal_month, String cal_date, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Attendance_Report" + "_" + cal_month + "_" + cal_year + "_" + cal_date;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/AttendanceRegister_Daily_Report.jrxml");
			} else {

				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/AttendanceRegister_Daily_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("CAL_YEAR", cal_year);

			map.put("CAL_MONTH", cal_month);

			map.put("CAL_DATE", cal_date);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileMonthyAttendance(String cal_month, String cal_year, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Attendance_Report" + "_" + cal_month + "_" + cal_year;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/AttendanceRegister_Month_Report.jrxml");
			} else {

				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/AttendanceRegister_Month_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("CAL_YEAR", cal_year);

			map.put("CAL_MONTH", cal_month);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileLeaveRegister(String employee_id, String year, String leave_category, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Leave_Register" + "_" + employee_id + "_" + year;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/LeaveRegister_Report.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/LeaveRegister_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("EMPLOYEE_ID", employee_id);			
			map.put("LEAVE_CATEGORY", leave_category);
			map.put("CAL_YEAR", year);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}


	public File getFileTimeSheet(String emp_id, String year, String month, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Time_Sheet" + "_" + emp_id + "_" + year;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/TimesheerReport.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/TimesheerReport.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("emp_id", emp_id);
			map.put("year", year);
			map.put("month", month);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}
	public File getemployeeRegister(String monthend, String detials, String reportto, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "employeeRegister" + "_" + reportto + "_" + monthend;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("PDF")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/EmployeeRegisterReport.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/EmployeeRegisterReport.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("EMPLOYEE_ID", reportto);

			if (report_type.equals("PDF")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {
				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();
				

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}
	public File getFileWorkAssign(String emp_id, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Work_Assign" + "_" + emp_id;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/WorkAssign_Report.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/WorkAssign_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("emp_id", emp_id);
			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileProfileMaster(String emp_id, String ProfileType, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Profile_Master" + "_" + emp_id;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/ProfileMaster_Report.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/ProfileMaster_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("EMP_ID", emp_id);
			// map.put("profileType", ProfileType);
			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}


	public File getFileProject(String proj_id, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Project_Report" + "_" + proj_id;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/ProjectMaster_Report.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/ProjectMaster_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("PROJ_ID", proj_id);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileHolidayList(String cal_year, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Attendance_Report" + "_" + cal_year;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/Holiday_List_Report.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/Holiday_List_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("CAL_YEAR", cal_year);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileHolidayDetailsList(String cal_year, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Attendance_Report" + "_" + cal_year;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/HolidayList_Details.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/HolidayList_Details.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("CAL_YEAR", cal_year);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}
	
	//----------------1----------------//
	public File getFileInventoryRegister(String sol_id,Date report_date,String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Inventory_" + "_" + sol_id;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/InventoryReport.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/InventoryReport.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("DATE_OF_PURCHASE",report_date );
			map.put("SOL ID", sol_id);
			//map.put("FROM_SOL_ID", sol_id);

			if (report_type.equals("PDF")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}
	//inventory report only with sol id
	public File getFileInventoryRegister1(String sol_id,String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Inventory_" + "_" + sol_id;
		System.out.println("came to services");

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/InventoryReport.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/InventoryReport.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("SOL ID", sol_id);
			//map.put("FROM_SOL_ID", sol_id);

			if (report_type.equals("PDF")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	// for date of inventory report
	public File getFileInventoryRegister2(Date report_date,String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Inventory_" + "_" ;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/InventoryReport.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/InventoryReport.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("DATE_OF_PURCHASE",report_date );
			//map.put("FROM_SOL_ID", sol_id);

			if (report_type.equals("PDF")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

		//----------------2----------------//
		public File getFileTransferRegister(String from_sol_id, Date report_date, String report_type) throws FileNotFoundException, JRException, SQLException {
		    String path = "Downloads"; 

		    String fileName = "Transfer_Report" + from_sol_id + "_" + new SimpleDateFormat("yyyy-MM-dd").format(report_date);
		    File outputFile = null;
		    System.out.println("came to services");
		    try {
		        InputStream jasperFile = this.getClass().getResourceAsStream("/static/jasper/InventoryTransfer.jrxml");
		        JasperReport jr = JasperCompileManager.compileReport(jasperFile);

		        HashMap<String, Object> map = new HashMap<>();
		        map.put("FROM_SOL_ID", from_sol_id);
		        

		        if (report_type.equalsIgnoreCase("PDF")) {
		            fileName += ".pdf";
		            outputFile = new File(path + fileName);
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JasperExportManager.exportReportToPdfFile(jp, outputFile.getAbsolutePath());
		        } else if (report_type.equalsIgnoreCase("Excel")) {
		            fileName += ".xlsx";
		            outputFile = new File(path + fileName);
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JRXlsxExporter exporter = new JRXlsxExporter();
		            exporter.setExporterInput(new SimpleExporterInput(jp));
		            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile.getAbsolutePath()));
		            exporter.exportReport();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return outputFile;
		}
		// for only date
		public File getFileTransferRegister1(Date report_date, String report_type) throws FileNotFoundException, JRException, SQLException {
		    String path = "Downloads"; 

		    String fileName = "Transfer_Report" +"_" + new SimpleDateFormat("yyyy-MM-dd").format(report_date);
		    File outputFile = null;
		    System.out.println("came to services ony date");
		    try {
		        InputStream jasperFile = this.getClass().getResourceAsStream("/static/jasper/InventoryTransfer.jrxml");
		        JasperReport jr = JasperCompileManager.compileReport(jasperFile);

		        HashMap<String, Object> map = new HashMap<>();
		        //map.put("FROM_SOL_ID", from_sol_id);
		        map.put("DATE_OF_PURCHASE",report_date );

		        if (report_type.equalsIgnoreCase("PDF")) {
		            fileName += ".pdf";
		            outputFile = new File(path + fileName);
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JasperExportManager.exportReportToPdfFile(jp, outputFile.getAbsolutePath());
		        } else if (report_type.equalsIgnoreCase("Excel")) {
		            fileName += ".xlsx";
		            outputFile = new File(path + fileName);
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JRXlsxExporter exporter = new JRXlsxExporter();
		            exporter.setExporterInput(new SimpleExporterInput(jp));
		            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile.getAbsolutePath()));
		            exporter.exportReport();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return outputFile;
		}
		// only for from sol id
		public File getFileTransferRegister2(String from_sol_id, String report_type) throws FileNotFoundException, JRException, SQLException {
		    String path = "Downloads"; 

		    String fileName = "Transfer_Report" + from_sol_id;
		    File outputFile = null;
		    System.out.println("came to services only sol");
		    try {
		        InputStream jasperFile = this.getClass().getResourceAsStream("/static/jasper/InventoryTransfer.jrxml");
		        JasperReport jr = JasperCompileManager.compileReport(jasperFile);

		        HashMap<String, Object> map = new HashMap<>();
		        map.put("FROM_SOL_ID", from_sol_id);
		        

		        if (report_type.equalsIgnoreCase("PDF")) {
		            fileName += ".pdf";
		            outputFile = new File(path + fileName);
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JasperExportManager.exportReportToPdfFile(jp, outputFile.getAbsolutePath());
		        } else if (report_type.equalsIgnoreCase("Excel")) {
		            fileName += ".xlsx";
		            outputFile = new File(path + fileName);
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JRXlsxExporter exporter = new JRXlsxExporter();
		            exporter.setExporterInput(new SimpleExporterInput(jp));
		            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile.getAbsolutePath()));
		            exporter.exportReport();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return outputFile;
		}

	             //--------------3--------------//
		public File getFileSaleRegister(String asset_serial_no, Date report_date, String report_type) throws FileNotFoundException, JRException, SQLException {
		    String path = "Downloads";

		    String fileName = "Sale_Report" + asset_serial_no + "_" + new SimpleDateFormat("yyyy-MM-dd").format(report_date);
		    String zipFileName = fileName + ".zip";
		    File outputFile;

		    try {
		        InputStream jasperFile = this.getClass().getResourceAsStream("/static/jasper/SaleReport.jrxml");
		        JasperReport jr = JasperCompileManager.compileReport(jasperFile);

		        HashMap<String, Object> map = new HashMap<>();
		        map.put("ASST_SRL_NO", asset_serial_no);
		       
		        if (report_type.equalsIgnoreCase("PDF")) {
		            fileName += ".pdf";
		            path += fileName;
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JasperExportManager.exportReportToPdfFile(jp, path);
		        } else if (report_type.equalsIgnoreCase("Excel")) {
		            fileName += ".xlsx";
		            path += fileName;
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JRXlsxExporter exporter = new JRXlsxExporter();
		            exporter.setExporterInput(new SimpleExporterInput(jp));
		            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
		            exporter.exportReport();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    outputFile = new File(path);
		    return outputFile;
		}
		//for asset srl no
		public File getFileSaleRegister1(String asset_serial_no,String report_type) throws FileNotFoundException, JRException, SQLException {
		    String path = "Downloads";

		    String fileName = "Sale_Report" + asset_serial_no;
		    String zipFileName = fileName + ".zip";
		    File outputFile;

		    try {
		        InputStream jasperFile = this.getClass().getResourceAsStream("/static/jasper/SaleReport.jrxml");
		        JasperReport jr = JasperCompileManager.compileReport(jasperFile);

		        HashMap<String, Object> map = new HashMap<>();
		        map.put("ASST_SRL_NO", asset_serial_no);
		       
		        if (report_type.equalsIgnoreCase("PDF")) {
		            fileName += ".pdf";
		            path += fileName;
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JasperExportManager.exportReportToPdfFile(jp, path);
		        } else if (report_type.equalsIgnoreCase("Excel")) {
		            fileName += ".xlsx";
		            path += fileName;
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JRXlsxExporter exporter = new JRXlsxExporter();
		            exporter.setExporterInput(new SimpleExporterInput(jp));
		            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
		            exporter.exportReport();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    outputFile = new File(path);
		    return outputFile;
		}

		//for date
		public File getFileSaleRegister2(Date report_date, String report_type) throws FileNotFoundException, JRException, SQLException {
		    String path = "Downloads";

		    String fileName = "Sale_Report date";
		    String zipFileName = fileName + ".zip";
		    File outputFile;

		    try {
		        InputStream jasperFile = this.getClass().getResourceAsStream("/static/jasper/SaleReport.jrxml");
		        JasperReport jr = JasperCompileManager.compileReport(jasperFile);

		        HashMap<String, Object> map = new HashMap<>();
		       // map.put("ASST_SRL_NO", asset_serial_no);
		        map.put("DATE_OF_PURCHASE",report_date );
		        
		        if (report_type.equalsIgnoreCase("PDF")) {
		            fileName += ".pdf";
		            path += fileName;
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JasperExportManager.exportReportToPdfFile(jp, path);
		        } else if (report_type.equalsIgnoreCase("Excel")) {
		            fileName += ".xlsx";
		            path += fileName;
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JRXlsxExporter exporter = new JRXlsxExporter();
		            exporter.setExporterInput(new SimpleExporterInput(jp));
		            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
		            exporter.exportReport();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    outputFile = new File(path);
		    return outputFile;
		}
		//-----------------4-------------------//
		public File getFileWriteoffRegister(String asset_serial_no, Date report_date, String report_type) throws FileNotFoundException, JRException, SQLException {
		    String path = "Downloads";

		    String fileName = "Writeoff_Report" + asset_serial_no + "_" + new SimpleDateFormat("yyyy-MM-dd").format(report_date);
		    String zipFileName = fileName + ".zip";
		    File outputFile;

		    try {
		        InputStream jasperFile = this.getClass().getResourceAsStream("/static/jasper/WriteoffReport.jrxml");
		        JasperReport jr = JasperCompileManager.compileReport(jasperFile);

		        HashMap<String, Object> map = new HashMap<>();
		        map.put("ASST_SRL_NO", asset_serial_no);

		        if (report_type.equalsIgnoreCase("PDF")) {
		            fileName += ".pdf";
		            path += fileName;
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JasperExportManager.exportReportToPdfFile(jp, path);
		        } else if (report_type.equalsIgnoreCase("Excel")) {
		            fileName += ".xlsx";
		            path += fileName;
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JRXlsxExporter exporter = new JRXlsxExporter();
		            exporter.setExporterInput(new SimpleExporterInput(jp));
		            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
		            exporter.exportReport();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    outputFile = new File(path);
		    return outputFile;
		}
		
		//for asset srl no
		
		public File getFileWriteoffRegister1(String asset_serial_no, String report_type) throws FileNotFoundException, JRException, SQLException {
		    String path = "Downloads";

		    String fileName = "Writeoff_Report" + asset_serial_no;
		    String zipFileName = fileName + ".zip";
		    File outputFile;

		    try {
		        InputStream jasperFile = this.getClass().getResourceAsStream("/static/jasper/WriteoffReport.jrxml");
		        JasperReport jr = JasperCompileManager.compileReport(jasperFile);

		        HashMap<String, Object> map = new HashMap<>();
		        map.put("ASST_SRL_NO", asset_serial_no);

		        if (report_type.equalsIgnoreCase("PDF")) {
		            fileName += ".pdf";
		            path += fileName;
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JasperExportManager.exportReportToPdfFile(jp, path);
		        } else if (report_type.equalsIgnoreCase("Excel")) {
		            fileName += ".xlsx";
		            path += fileName;
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JRXlsxExporter exporter = new JRXlsxExporter();
		            exporter.setExporterInput(new SimpleExporterInput(jp));
		            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
		            exporter.exportReport();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    outputFile = new File(path);
		    return outputFile;
		}
		
		//for date (write off)
		
		public File getFileWriteoffRegister2(Date report_date, String report_type) throws FileNotFoundException, JRException, SQLException {
		    String path = "Downloads";

		    String fileName = "Writeoff_Report" +"_" + new SimpleDateFormat("yyyy-MM-dd").format(report_date);
		    String zipFileName = fileName + ".zip";
		    File outputFile;

		    try {
		        InputStream jasperFile = this.getClass().getResourceAsStream("/static/jasper/WriteoffReport.jrxml");
		        JasperReport jr = JasperCompileManager.compileReport(jasperFile);

		        HashMap<String, Object> map = new HashMap<>();
		        //map.put("ASST_SRL_NO", asset_serial_no);
		        map.put("DATE_OF_PURCHASE",report_date );
		        
		        if (report_type.equalsIgnoreCase("PDF")) {
		            fileName += ".pdf";
		            path += fileName;
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JasperExportManager.exportReportToPdfFile(jp, path);
		        } else if (report_type.equalsIgnoreCase("Excel")) {
		            fileName += ".xlsx";
		            path += fileName;
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JRXlsxExporter exporter = new JRXlsxExporter();
		            exporter.setExporterInput(new SimpleExporterInput(jp));
		            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
		            exporter.exportReport();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    outputFile = new File(path);
		    return outputFile;
		}

		//------------------DEPRECIATION---------------------------------
		public File getFileDepreciationReport(String asset_serial_no, Date report_date, String report_type) throws FileNotFoundException, JRException, SQLException {
		    String path = "Downloads";

		    String fileName = "Depreciaton" + asset_serial_no + "_" + new SimpleDateFormat("yyyy-MM-dd").format(report_date);
		    String zipFileName = fileName + ".zip";
		    File outputFile;

		    try {
		        InputStream jasperFile = this.getClass().getResourceAsStream("/static/jasper/Depreciation.jrxml");
		        JasperReport jr = JasperCompileManager.compileReport(jasperFile);

		        HashMap<String, Object> map = new HashMap<>();
		        map.put("ASST_SRL_NO", asset_serial_no);

		        if (report_type.equalsIgnoreCase("PDF")) {
		            fileName += ".pdf";
		            path += fileName;
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JasperExportManager.exportReportToPdfFile(jp, path);
		        } else if (report_type.equalsIgnoreCase("Excel")) {
		            fileName += ".xlsx";
		            path += fileName;
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JRXlsxExporter exporter = new JRXlsxExporter();
		            exporter.setExporterInput(new SimpleExporterInput(jp));
		            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
		            exporter.exportReport();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    outputFile = new File(path);
		    return outputFile;
		}
		
		// for asset srl no(depreciation)
		public File getFileDepreciationReport1(String asset_serial_no, String report_type) throws FileNotFoundException, JRException, SQLException {
		    String path = "Downloads";

		    String fileName = "Depreciaton" + asset_serial_no;
		    String zipFileName = fileName + ".zip";
		    File outputFile;

		    try {
		        InputStream jasperFile = this.getClass().getResourceAsStream("/static/jasper/Depreciation.jrxml");
		        JasperReport jr = JasperCompileManager.compileReport(jasperFile);

		        HashMap<String, Object> map = new HashMap<>();
		        map.put("ASST_SRL_NO", asset_serial_no);

		        if (report_type.equalsIgnoreCase("PDF")) {
		            fileName += ".pdf";
		            path += fileName;
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JasperExportManager.exportReportToPdfFile(jp, path);
		        } else if (report_type.equalsIgnoreCase("Excel")) {
		            fileName += ".xlsx";
		            path += fileName;
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JRXlsxExporter exporter = new JRXlsxExporter();
		            exporter.setExporterInput(new SimpleExporterInput(jp));
		            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
		            exporter.exportReport();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    outputFile = new File(path);
		    return outputFile;
		}
		
		//for only date(depreciation)
		public File getFileDepreciationReport2(Date report_date, String report_type) throws FileNotFoundException, JRException, SQLException {
		    String path = "Downloads";

		    String fileName = "Depreciaton"+ "_" + new SimpleDateFormat("yyyy-MM-dd").format(report_date);
		    String zipFileName = fileName + ".zip";
		    File outputFile;

		    try {
		        InputStream jasperFile = this.getClass().getResourceAsStream("/static/jasper/Depreciation.jrxml");
		        JasperReport jr = JasperCompileManager.compileReport(jasperFile);

		        HashMap<String, Object> map = new HashMap<>();
		       // map.put("ASST_SRL_NO", asset_serial_no);
		        map.put("DATE_OF_PURCHASE",report_date );
		        
		        if (report_type.equalsIgnoreCase("PDF")) {
		            fileName += ".pdf";
		            path += fileName;
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JasperExportManager.exportReportToPdfFile(jp, path);
		        } else if (report_type.equalsIgnoreCase("Excel")) {
		            fileName += ".xlsx";
		            path += fileName;
		            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		            JRXlsxExporter exporter = new JRXlsxExporter();
		            exporter.setExporterInput(new SimpleExporterInput(jp));
		            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
		            exporter.exportReport();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    outputFile = new File(path);
		    return outputFile;
		}


}
