package com.bornfire.controller;

import java.io.BufferedReader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.bornfire.entities.*;
import com.bornfire.services.AdminOperServices;
import com.bornfire.services.AttendanceRegisterService;
import com.bornfire.services.BankDetailService;
import com.bornfire.services.ExcelUploadService;
import com.bornfire.services.FileUploadServices;
import com.bornfire.services.InquiriesServices;
import com.bornfire.services.LoginServices;
import com.bornfire.services.Mail;
import com.bornfire.services.MaintenanceOperServices;
import com.bornfire.services.OnDutyServices;
import com.bornfire.services.PlacementServices;
import com.bornfire.services.ProjectMasterServices;
import com.bornfire.services.ReportServices;
import com.bornfire.services.TimeSheetPdf;
import com.bornfire.services.WorkAssignmentReportExcel;
import com.ibm.icu.text.SimpleDateFormat;

import net.sf.jasperreports.engine.JRException;
@Controller
@ConfigurationProperties("default")
public class BTMNavigationController {

	private static final Logger logger = LoggerFactory.getLogger(BTMNavigationController.class);
	
	@Autowired
	BAM_TRAN_MAS_REP BAM_TRAN_MAS_rep;
	
	@Autowired
	BamGeneralLedgerRep BamGeneralLedgerRep;
	
	@Autowired
	BAM_AssetFlows_Rep  BAM_AssetFlows_rep;


	@Autowired
	perfomance_evaluation_REP perfomance_evaluation_rep;
	
	@Autowired
	LeaveTableRep LeaveTablerep;
	
	@Autowired
	Mail maile;

	@Autowired
	Taxation_parameter_Rep Taxation_parameter_rep;

	@Autowired
	GstoverseasRepo gstoverseasRepo;
	@Autowired
	PlacementMaintenanceRep placementMaintenanceRep;
	
	@Autowired
	BsalRep bsalRep;
	
	@Autowired
	GstBtmRep gstBtmRep;
	
	@Autowired
	GstRep gstRep;
	
	@Autowired
	spfRepo SpfRepo;

	@Autowired
	spf_repo Spf_repo;
	
	@Autowired
	LoginServices loginServices;
	
	@Autowired
	IssueTrackerRep issueTrackerRep;
	
	@Autowired
	ReportServices reportServices;
	
	@Autowired
	ClientMasterRep clientMasterRep;
	
	@Autowired
	DocumentMainRep documentMainRep;
	
	@Autowired
	BankMasterRep bankMasterRep;
	
	@Autowired
	BTMAdminProfileMangerRep btmAdminProfileMangerRep;

	@Autowired
	BankDetailService bankDetailService;
	
	@Autowired
	PlacementServices placementServices;

	@Autowired
	OnDutyServices onDutyServices;
	
	@Autowired
	DataSource srcdataSource;

	@Autowired
	InquiriesServices inquiriesServices;

	@Autowired
	AdminOperServices adminOperServices;
	
	@Autowired
	ExcelUploadService excelUploadService;

	@Autowired
	BLRSBatchJobAlertRep blrsBatchJobAlertRep;

	@Autowired
	PlacementMasterRep placementMasterRep;

	@Autowired
	TimesheetManagementRep timesheetManagementRep;

	@Autowired
	InvoiceMasterRep invoiceMasterRep;

	@Autowired
	ProfileManagerRep profileManagerRep;

	@Autowired
	BTMProjectMasterRep btmProjectMasterRep;

	@Autowired
	BTMProjectTeamDetailsRep btmProjectTeamDetailsRep;

	@Autowired
	BTMProjectDetailsRep btmProjectDetailsRep;

	@Autowired
	AttendanceRegisterRep attendanceRegisterRep;

	@Autowired
	OnDutyRep onDutyRep;

	@Autowired
	BTMAdminOndutyCountRep bTMAdminOndutyCountRep;

	@Autowired
	LeaveMasterRep leaveMasterRep;

	@Autowired
	BTMAdminAssociateProfileRep btmAdminAssociateProfileRep;

	@Autowired
	LeaveMasterCounterRep leaveMasterCounterRep;

	@Autowired
	ExtenseMasterRep extenseMasterRep;

	@Autowired
	BTMWorkAssignmentRep btmWorkAssignmentRep;

	@Autowired
	BTMEmpTimeSheetRep bTMEmpTimeSheetRep;
	
	@Autowired
	BTMTravelMasterRep btmTravelMasterRep;

	@Autowired
	BTMRefCodeMasterRep btmRefCodeMasterRep;

	@Autowired
	BTMAdminOrganizationMasterRep btmAdminOrganizationMasterRep;

	@Autowired
	BTMEmpTimeSheetRep btmEmpTimeSheetRep;
	
	@Autowired
	BTMMTimeSheetRep btmmTimeSheetRep;

	@Autowired
	TimeSheetBeanRep timeSheetBeanRep;

	@Autowired
	BTMAdminExpenseReportRep btmAdminExpenseReportRep;

	@Autowired
	BTMDocumentMasterRep btmDocumentMasterRep;

	@Autowired
	BTMEventMasterRep btmEventMasterRep;

	@Autowired
	MaintenanceOperServices maintenanceOperServices;
	
	@Autowired
	AttendanceRegisterService attendanceRegisterService;
	
	@Autowired
	PlacementResourcesMasterRepo placementResourcesMasterRepo;

	
	@Autowired
	ResourceMasterRepo resourceMasterRepo;
	
	@Autowired
	TimeSheetPdf timeSheetPdf;
	
	@Autowired
	WorkAssignmentReportExcel workAssignmentReportExcel;
	
	@Autowired
	BTMAdminHolidayMasterRep btmAdminHolidayMasterRep;
	
	@Autowired
	PlacementMaintenanceRep placementmaintenancerep;
	
	@Autowired
	BTMAdminAssociateModRep btmAdminAssociateModRep;
	
	@Autowired
	 AccessRolesRep accessRolesRep;
	@Autowired
	bexpiRepo bexpiRepoa;
	
	@Autowired
	tdsRepo tdsRepos;
	@Autowired
	btdsviewRepo btdsviewRepos;
	@Autowired
	com.bornfire.entities.AttendanceRegisterGetRep AttendanceRegisterGetRep;
	
	

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	salary_parameter_rep salary_parameter_Rep;
	
	@Autowired
	ProjectMasterServices projectMasterServices;

	@Autowired
	Salary_Pay_Rep salary_Pay_Rep;

	@Autowired
	CandEvalFormRep candEvalFormRep;

	@Autowired
	com.bornfire.services.sendingmail_appointment sendingmail_appointment;
	@Autowired
	com.bornfire.services.sendigmail_offerletter sendigmail_offerletter;
	
	@Autowired
	com.bornfire.services.Sendingmail_coveringletter sendingmail_coveringletter;
	
	
	@Autowired
	com.bornfire.services.sendingmail_batchjob sendingmail_batchjob;

	@Autowired
	paystructurerep Paystructurerep;

	@Autowired
	ProfileManagerRep1 profileManagerRep1;


	@Autowired
	PerdiemMasterRep perdiemMasterRep;

	@Autowired
	Assosiate_Profile_Repo assosiate_Profile_Repo;
	@Autowired
	com.bornfire.entities.Baj_Work_Repo Baj_Work_Repo;


	@Autowired
	FileUploadServices fileUploadServices;

	@Autowired
	com.bornfire.entities.Document_Master_Repo Document_Master_Repo;
	
	@Autowired
	Inventory_Masterrep inventory_masterRep;

	@Autowired
	Bamcatcodemaintainrep Bamcatcodemain;
	
	@Autowired
	BamDocumentMasRep BAMDocmastrep;
	
	@Autowired
	BAMInventryMastRep BAMInvmastrep;
	
	@Autowired
	Baminventorytranrep BamInvtrnrep;
	
	@Autowired
	Bamsaleandwriterep bamsalerep;
	
	@Autowired
	Bamtranmasrep Bamtranrep;
	
	@Autowired
	Bambatchjobrep Bambatjobrep;
	
	@Autowired
	BamDepriciationRep Bamdeprep;
	
	@Autowired
	ChartofAccountsrep charofaccountrep;
	
	@Autowired
	DashBoardRepo  dashboardRepository;
	
	@Autowired
	BamAcquisitionrep bamAcquisitionrep;
	
	@Autowired
	Audit_Mas_Repo auditRepository;
	@Autowired
	HolidayMaster_Rep holidayMaster_Rep;
	@Autowired
	Organization_Branch_Rep organization_Branch_Rep;
	@Autowired
	Organization_Repo organization_Repo;
	String pagesize;

	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

	@RequestMapping(value = "Dashboard", method = { RequestMethod.GET, RequestMethod.POST })
	public String getdashboard(Model md, HttpServletRequest req) {

		String userid = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userid));
		md.addAttribute("menu", "BTMHeaderMenu");
		// md.addAttribute("menu", "Dashboard");
		// md.addAttribute("checkpassExpiry", loginServices.checkpassexpirty(userid));
		md.addAttribute("checkAcctExpiry", loginServices.checkAcctexpirty(userid));
		//md.addAttribute("changepassword", loginServices.checkPasswordChangeReq(userid));

		int completed = 0;
		int uncompleted = 0;
		md.addAttribute("reportList", "");
		md.addAttribute("completed", completed);
		md.addAttribute("uncompleted", uncompleted);
		md.addAttribute("menu", "Dashboard");
		return "BTMDashboard";

	}

	@RequestMapping(value = "verifyUser", method = RequestMethod.POST)
	@ResponseBody
	public String verifyUser(@ModelAttribute UserProfile userprofile, Model md, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");
		String msg = loginServices.verifyUser(userprofile, userid);

		return msg;

	}
	

	@RequestMapping(value = "deleteuser")
	@ResponseBody
	public String deleteuser(@RequestParam("userid") String userid ,Model md, HttpServletRequest rq) {
		System.out.print(userid);
		String msg = loginServices.deleteuser( userid);
		return msg;
	}

	
	

	@RequestMapping(value = "passwordReset", method = RequestMethod.POST)
	@ResponseBody
	public String passwordReset(@ModelAttribute UserProfile userprofile, Model md, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");
		String msg = loginServices.passwordReset(userprofile, userid);

		return msg;
	}

	@RequestMapping(value = "login?logout", method = RequestMethod.POST)
	@ResponseBody
	public String logoutUpdate(HttpServletRequest req) {

		String msg;

		String userid = (String) req.getSession().getAttribute("USERID");

		try {
			logger.info("Updating Logout");
			loginServices.SessionLogging("LOGOUT", "M0", req.getSession().getId(), userid, req.getRemoteAddr(),
					"IN-ACTIVE");
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "failed";
		}
		return msg;
	}

//	======================================  Admin Module ====================================================

	@RequestMapping(value = "organizationMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String organizationMaster(@RequestParam(required = false) String formmode, Model md, HttpServletRequest req)
			throws ParseException {

		String EmpId = "U72900TN2017PTC115892";
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("view")) {
			
			List<LeaveTable> leaves = LeaveTablerep.getAll(); // Fetch your data
	        md.addAttribute("leaves", leaves);
	        long count= LeaveTablerep.count();
	        md.addAttribute("hasRecords", count > 0);
			md.addAttribute("formmode", "view");
			md.addAttribute("adminOrganization", adminOperServices.getUser(EmpId));

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("adminOrganization", adminOperServices.getUser(EmpId));

		} else {

			md.addAttribute("formmode", formmode);
		}

		return "BTMAdminOrganizationMaster";
	}

	@RequestMapping(value = "organizationMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String organizationMasterAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute BTMAdminOrganizationMaster btmAdminOrganizationMaster, Model md, HttpServletRequest rq) {

		String msg = adminOperServices.addOrganizationModyfiy(btmAdminOrganizationMaster, formmode);
		return msg;
	}

	@RequestMapping(value = "adminAssociateProfile", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminAssociateProfile(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminAssociateProfileList", btmAdminAssociateProfileRep.getAssociatelist());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("refernce_code",btmRefCodeMasterRep.getBankList());
		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("adminAssociateProfile", adminOperServices.getAssociteUser(resId));

		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", "verify");
			md.addAttribute("adminAssociateProfile", adminOperServices.getAssociteVerifyUser(resId));

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("adminAssociateProfile", adminOperServices.getAssociteUser(resId));

		} else if (formmode.equals("cancel")) {

			md.addAttribute("formmode", "cancel");
			md.addAttribute("adminAssociateProfile", adminOperServices.getAssociteCancelUser(resId));

		} else if (formmode.equals("listview")) {

			md.addAttribute("formmode", "listview");
			md.addAttribute("adminAssociateProfile", adminOperServices.getAssociteListUser(resId));

		}

		return "BTMAdminAssociateProfile";
	}

	@RequestMapping(value = "adminAssociateProfileAdd", method = RequestMethod.POST)
	@ResponseBody
	public String adminAssociateAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute BTMAdminAssociateProfile bTMAdminAssociateProfile, Model md, HttpServletRequest rq) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		String msg = adminOperServices.addAssociateUser(bTMAdminAssociateProfile, formmode,userId);
		return msg;
	}
	
	/*@RequestMapping(value = "UserProfile", method = { RequestMethod.GET, RequestMethod.POST })
	public String userprofile(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// Logging Navigation
		loginServices.SessionLogging("USERPROFILE", "M2", req.getSession().getId(), loginuserid, req.getRemoteAddr(),
				"ACTIVE");
		Session hs1 = sessionFactory.getCurrentSession();
		md.addAttribute("menu", "UserProfile"); // To highlight the menu

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("userProfiles", loginServices.getUsersList());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			//md.addAttribute("domains", reportServices.getDomainList());
			//md.addAttribute("userProfile", loginServices.getUser(userid));

		} else {

			md.addAttribute("formmode", formmode);
			//md.addAttribute("domains", reportServices.getDomainList());
			//md.addAttribute("FinUserProfiles", loginServices.getFinUsersList());
			//md.addAttribute("userProfile", loginServices.getUser(""));

		}

		return "BTMUserprofile";
	}*/

	
	
	

	@RequestMapping(value = "UserProfile", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminUserProfile(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, Model md, HttpServletRequest req)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		loginServices.SessionLogging("USERPROFILE", "M2", req.getSession().getId(), loginuserid, req.getRemoteAddr(),
				"ACTIVE");

		md.addAttribute("menu", "UserProfile");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("userProfiles", loginServices.getUsersList());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("userProfile", loginServices.getUser(userid));

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("userProfile", loginServices.getUser(""));

		} else if (formmode.equals("view")){
			
			md.addAttribute("formmode",
					formmode);
			//md.addAttribute("userProfile", loginServices.getUser(userid));
			/*
			 * md.addAttribute("FinUserProfiles", loginServices.getFinUsersList());
			 * md.addAttribute("userProfile", loginServices.getUser(userid));
			 */
			 
		}

		return "BTMUserprofile";
	}

	@RequestMapping(value = "createUser", method = RequestMethod.POST)
	@ResponseBody
	public String createUser(@RequestParam("formmode") String formmode, @ModelAttribute UserProfile userprofile,
			Model md, HttpServletRequest rq) throws NoSuchAlgorithmException, InvalidKeySpecException {

		String userid = (String) rq.getSession().getAttribute("USERID");
		String msg = loginServices.addUser(userprofile, formmode, userid);

		return msg;
	}

	@RequestMapping(value = "adminProfileMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminProfileMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminProfileManagerList", adminOperServices.getProfileManagerlist());

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("adminProfileManagerList", adminOperServices.getProfileManagerlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminProfileManager", adminOperServices.getProfileManager(resId));

		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("adminProfileManager", adminOperServices.getProfileManager(resId));

		}else if (formmode.equals("verifyList")) {

			md.addAttribute("formmode", "verifyList");
			md.addAttribute("adminProfileManagerList", adminOperServices.getProfileManagerlist());
			
		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", "verify");
			md.addAttribute("adminProfileManager", adminOperServices.getProfileManager(resId));

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("adminProfileManager", adminOperServices.getProfileManager(resId));

		} else if (formmode.equals("deleteList")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("adminProfileManagerList", adminOperServices.getProfileManagerlist());

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", "delete");
			md.addAttribute("adminProfileManager", adminOperServices.getProfileManager(resId));

		}

		return "BTMAdminProfileMaster";
	}

	@RequestMapping(value = "adminProfileMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String adminProfileMasterAdd(@RequestParam("formmode") String formmode, Model md,
			@ModelAttribute BTMAdminProfileManager btmAdminProfileManager, HttpServletRequest rq) {

		String msg = adminOperServices.addProfileDetails(btmAdminProfileManager, formmode);
		return msg;
	}

	/*@RequestMapping(value = "adminLeaveMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminLeaveMaster(@RequestParam(required = false) String formmode,@RequestParam(required = false) BigDecimal year,
			@RequestParam(required = false) BigDecimal resId, @RequestParam(required = false) String RefId, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		BigDecimal year1 = new BigDecimal(Calendar.getInstance().get(Calendar.YEAR));
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("AdminLeaveList", leaveMasterRep.getAdminLeaveList1(year1));

		}else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("AdminLeaveList", leaveMasterRep.getAdminLeaveList1(year));
			
		}else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("leaveMaster", onDutyServices.getLeaveDetail(resId));
			md.addAttribute("AdminLeaveList", leaveMasterCounterRep.getLeaveCounterlist(RefId));

		}

		return "BTMAdminLeaveMaster";
	}*/
	
	@RequestMapping(value = "adminLeaveMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminLeaveMaster(@RequestParam(required = false) String formmode,@RequestParam(required = false) BigDecimal year,
			@RequestParam(required = false) BigDecimal resId, @RequestParam(required = false) String RefId, @RequestParam(required = false) String datelist ,
			@RequestParam(required = false) String datelist1,@RequestParam(required = false) String datelist2,@RequestParam(required = false) String datelist3,
			@RequestParam(required = false) String datelist4,
			@RequestParam(required = false) String datelist5,Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		BigDecimal year1 = new BigDecimal(Calendar.getInstance().get(Calendar.YEAR));
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			System.out.println("================"+datelist);
			System.out.println("================2222222222222"+datelist1);
			
			md.addAttribute("AdminLeaveList", leaveMasterRep.getAdminLeaveList11(year1));
			if (datelist!=null){ // Optional: Print the value to the console if
				  System.out.println("================+++++++++++++"+datelist);
				  
				  List<LeaveMaster> adminvalues = leaveMasterRep.getAdminLeaveList111(datelist); 
				/*List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist);*/
				  md.addAttribute("AdminLeaveList", adminvalues);
				  }
				   if (datelist1!=null){
					    // Optional: Print the value to the console if needed
					    System.out.println("================+++++++++++++"+datelist1);

					   List<LeaveMaster> adminvalues1 = leaveMasterRep.getAdmindetailsit1(datelist1);
					    //List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist);
					    		 md.addAttribute("AdminLeaveList", adminvalues1);;
					    
					    // Continue with the logic using adminvalues as needed
					}
					
				  
				 // Continue with the logic using adminvalues as needed }
				 
			
			 if (datelist2!=null){
			    // Optional: Print the value to the console if needed
			    System.out.println("================+++++++++++++"+datelist1);

			   List<LeaveMaster> adminvalues1 = leaveMasterRep.getAdmindetailsit2(datelist2);
			    //List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist);
			    		 md.addAttribute("AdminLeaveList", adminvalues1);;
			    
			    // Continue with the logic using adminvalues as needed
			}
			 if (datelist3!=null){
				    // Optional: Print the value to the console if needed
				    System.out.println("================+++++++++++++"+datelist1);

				   List<LeaveMaster> adminvalues1 = leaveMasterRep.getAdmindetailsit3(datelist3);
				    //List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist);
				    		 md.addAttribute("AdminLeaveList", adminvalues1);;
				    
				    // Continue with the logic using adminvalues as needed
				}
				
			 if (datelist4!=null){
				    // Optional: Print the value to the console if needed
				    System.out.println("================+++++++++++++"+datelist4);

				   List<LeaveMaster> adminvalues1 = leaveMasterRep.getAdmindetailsit4(datelist4);
				    //List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist);
				    		 md.addAttribute("AdminLeaveList", adminvalues1);;
				    
				    // Continue with the logic using adminvalues as needed
				}
				
			 if (datelist5!=null){
				    // Optional: Print the value to the console if needed
				    System.out.println("================+++++++++++++"+datelist5);

				   List<LeaveMaster> adminvalues1 = leaveMasterRep.getAdmindetailsit5(datelist5);
				    //List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist);
				    		 md.addAttribute("AdminLeaveList", adminvalues1);;
				    
				    // Continue with the logic using adminvalues as needed
				}
				
			


		//	md.addAttribute("AdminLeaveList", leaveMasterRep.getAdminLeaveList11(year1));

		}else if (formmode.equals("list1")) {

			//md.addAttribute("formmode", "list1");
			//md.addAttribute("AdminLeaveList", leaveMasterRep.getAdminLeaveList1(year));
			//md.addAttribute("AdminLeaveList", leaveMasterRep.getAdminLeaveList2(year1));
			
		}else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("leaveMaster", onDutyServices.getLeaveDetail(resId));
			md.addAttribute("AdminLeaveList", leaveMasterCounterRep.getLeaveCounterlist(RefId));

		}
		else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			//md.addAttribute("leaveMaster", onDutyServices.getLeaveDetail(resId));
			//md.addAttribute("AdminLeaveList", leaveMasterCounterRep.getLeaveCounterlist(RefId));

		}

		return "BTMAdminLeaveMaster";
	}

	@RequestMapping(value = "adminODMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminODMaster(@RequestParam(required = false) String formmode,@RequestParam(required = false) BigDecimal year,
			@RequestParam(required = false) String resId, @RequestParam(required = false) String RefId, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		BigDecimal year2 = new BigDecimal(Calendar.getInstance().get(Calendar.YEAR));
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("AdminODList", onDutyRep.getOdMasterList1(year2));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("leaveMaster", onDutyServices.getODDetail(resId));
			md.addAttribute("AdminODList", bTMAdminOndutyCountRep.getOndutyCounterlist(RefId));

		}

		return "BTMAdminODMaster";
	}
	

	@RequestMapping(value = "adminHolidayMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminHolidayMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("adminHolidayProfile", new BTMAdminHolidayMaster());

		} else if (formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminHolidayList", adminOperServices.getHolidaylist());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("adminHolidayProfile", adminOperServices.getHolidayDetail(resId));

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("adminHolidayList", adminOperServices.getHolidaylist());

		}else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("adminHolidayProfile", adminOperServices.getHolidayDetail(resId));

		} 
		return "BTMAdminHolidayMaster";
	}

	@RequestMapping(value = "adminHolidayMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String adminHolidayMasterAdd(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) BigDecimal recordNo,
			@ModelAttribute BTMAdminHolidayMaster btmAdminHolidayMaster, Model md, HttpServletRequest rq) {

		String msg = adminOperServices.addHolidayDetails(btmAdminHolidayMaster, formmode, recordNo);
		return msg;
	}

	@RequestMapping(value = "adminDocumentMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminDocumentMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("profileManagers", btmAdminAssociateProfileRep.getEmployeedetail2());
		} else if (formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminDocumentList", btmDocumentMasterRep.getDocumentlist());

		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("adminDocMaster", btmDocumentMasterRep.getDocument(resId));

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("adminDocumentList", btmDocumentMasterRep.getDocumentlist());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("adminDocMaster", btmDocumentMasterRep.getDocument(resId));

		}

		return "BTMAdminDocMaster";
	}

	@RequestMapping(value = "adminDocumentMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String adminDocumentMasterAdd(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) BigDecimal recordNo, @ModelAttribute BTMDocumentMaster btmDocumentMaster,
			Model md, HttpServletRequest rq) {

		String msg = adminOperServices.addDocumentUser(btmDocumentMaster, formmode);
		return msg;
	}

	@RequestMapping(value = "adminReferenceMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminReferenceMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminRefCodeMaster", btmRefCodeMasterRep.getRefCodelist());


		} else if (formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminRefCodeMaster", btmRefCodeMasterRep.getRefCodelist());

		} else if (formmode.equals("view1")) {
			System.out.println(resId);
			md.addAttribute("formmode", "view1");
			md.addAttribute("RefCodeMaster", adminOperServices.getRefMaster(resId));

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("adminRefCodeMaster", btmRefCodeMasterRep.getRefCodelist());

		} else if (formmode.equals("edit")) {

			System.out.println(resId);
			if(resId.equals("country code")) {
				md.addAttribute("formmode", "view1");
				md.addAttribute("RefCodeMaster", adminOperServices.getRefMaster(resId));
			}
			else if(resId.equals("Currency code")){
				md.addAttribute("formmode", "view2");
				md.addAttribute("RefCodeMaster", adminOperServices.getRefMaster(resId));
			}
			else if(resId.equals("Category code")){
				md.addAttribute("formmode", "view3");
				md.addAttribute("RefCodeMaster", adminOperServices.getRefMaster(resId));
			}
			else {
				md.addAttribute("formmode", "edit");
				md.addAttribute("RefCodeMaster", adminOperServices.getRefMaster(resId));
			}

		} else if (formmode.equals("add")) {

			
			md.addAttribute("formmode", formmode);

			List<Bamcategorycodemain> assetsrlno=Bamcatcodemain.getall();
			md.addAttribute("assetsrlno", assetsrlno);

		}  else if (formmode.equals("delete")) {

			md.addAttribute("formmode", "delete");
			md.addAttribute("RefCodeMaster", adminOperServices.getRefMaster(resId));
		}    

		return "BTMAdminRefCodeMaster";
	}

	@RequestMapping(value = "adminRefCodeMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String adminRefCodeAdd(@RequestParam("formmode") String formmode,@RequestParam("ref_id") String ref_id,
			@ModelAttribute BTMRefCodeMaster btmRefCodeMaster, Model md, HttpServletRequest rq) {
		String msg = adminOperServices.addRefCodeMaster(btmRefCodeMaster, formmode, ref_id);
		return msg;
	}

	@RequestMapping(value = "adminProjectMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminProjectMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) String resName,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("adminprojectMaster", new BTMProjectMaster());

		} else if (formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminProjectMaster", btmProjectMasterRep.getProjectlist());

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("adminProjectMaster", btmProjectMasterRep.getProjectlist());

		} else if (formmode.equals("deleteList")) {

			md.addAttribute("formmode", "deleteList");
			md.addAttribute("adminProjectMaster", btmProjectMasterRep.getProjectlist());

		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("adminProjectMaster", btmProjectMasterRep.getProjectlist());
			md.addAttribute("adminprojectMaster", btmProjectMasterRep.getProjectShow(resId, resName));
			md.addAttribute("projectDetails", btmProjectDetailsRep.getProjectDetails(resId));
			md.addAttribute("teamDetails", btmProjectTeamDetailsRep.getProjectTeamDetails(resId));

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("adminProjectMaster", btmProjectMasterRep.getProjectlist());
			md.addAttribute("adminprojectMaster", btmProjectMasterRep.getProjectShow(resId, resName));
			md.addAttribute("projectDetails", btmProjectDetailsRep.getProjectDetails(resId));
			md.addAttribute("teamDetails", btmProjectTeamDetailsRep.getProjectTeamDetails(resId));

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", "delete");
			md.addAttribute("adminprojectMaster", btmProjectMasterRep.getProjectShow(resId, resName));
			md.addAttribute("projectDetails", btmProjectDetailsRep.getProjectDetails(resId));
			md.addAttribute("teamDetails", btmProjectTeamDetailsRep.getProjectTeamDetails(resId));

		}

		return "BTMAdminProjectMaster";
	}

	@RequestMapping(value = "adminProjectMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String adminProjectMasterAdd(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) String userid, @ModelAttribute BTMProjectMaster btmProjectMaster,
			@ModelAttribute ProjectDetails projectDetails, @ModelAttribute ProjectTeamDetails projectTeamDetails,
			Model md, HttpServletRequest rq) {

		// String userid2 = (String) rq.getSession().getAttribute("USERID");
		String msg = adminOperServices.addProjectMaster(btmProjectMaster, projectDetails, projectTeamDetails, formmode,
				userid);
		return msg;
	}

	@RequestMapping(value = "adminTravelMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminTravelMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("TravelList", adminOperServices.getTravelMasterList());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminTravelMaster", adminOperServices.getTravelMaster(resId));
		}

		return "BTMAdminTravelMaster";
	}

	@RequestMapping(value = "adminExpenseReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminExpenseReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) String userid, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("expenseReport", adminOperServices.getExpenseReportlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminExpenseReport", adminOperServices.getReportManager(resId));

		}
		return "BTMAdminExpenseReport";
	}

	@RequestMapping(value = "adminWorkAssignment", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminWorkAssignment(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		}

		return "BTMAdminWorkAssignment";
	}
	
/*	@RequestMapping(value = "InventoryMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String InventoryMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String AssetNo, Model md, HttpServletRequest req) throws ParseException {
		logger.info(AssetNo);
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {
			List<BAMInventorymaster> BAMInventorymaster = BAMInvmastrep.findAll(); // Fetch your data
	        md.addAttribute("InventoryMasterlist", BAMInventorymaster);

			md.addAttribute("formmode", "list");
			//md.addAttribute("WorkAssignmentList",btmWorkAssignmentRep.getWorkAssignlist());
			//md.addAttribute("InventoryMasterlist",inventory_masterRep.getWorkAssignlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			// md.addAttribute("ass", inventory_masterRep.getWorkAssign(AssetNo));
			md.addAttribute("ass", BAMInvmastrep.findById(AssetNo).get());

		}

		return "BAMInventoryMaster";
	}
	
	
	@RequestMapping(value = "Transfers", method = { RequestMethod.GET, RequestMethod.POST })
	public String Transfers(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String AssetNo, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {
			
			List<Baminventorytransfer> BAMInventorymaster = BamInvtrnrep.findAll(); // Fetch your data
	        md.addAttribute("InventoryMasterlist", BAMInventorymaster);
			md.addAttribute("formmode", "list");
			//md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());
			//md.addAttribute("InventoryMasterlist",inventory_masterRep.getWorkAssignlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("TRAN", BamInvtrnrep.findById(AssetNo).get());

		}

		return "BAMTransfers";
	}*/
	
	@RequestMapping(value = "Sales", method = { RequestMethod.GET, RequestMethod.POST })
	public String Sales(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String AssetNo, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {
			List<Bamsaleandwrite> Bamsaleandwrite = bamsalerep.findAll(); // Fetch your data
	        md.addAttribute("InventoryMasterlist", Bamsaleandwrite);
			md.addAttribute("formmode", "list");
			//md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());
			//md.addAttribute("InventoryMasterlist",inventory_masterRep.getWorkAssignlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("S", bamsalerep.findById(AssetNo).get());

		}

		return "BAMSALES";
	}
	
	
	@RequestMapping(value = "Writeoff", method = { RequestMethod.GET, RequestMethod.POST })
	public String Writeoff(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String AssetNo, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {
			List<Bamsaleandwrite> Bamsaleandwrite = bamsalerep.findAll(); // Fetch your data
	        md.addAttribute("InventoryMasterlist", Bamsaleandwrite);
		

			md.addAttribute("formmode", "list");
			//md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());
			//md.addAttribute("InventoryMasterlist",inventory_masterRep.getWorkAssignlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("W", bamsalerep.findById(AssetNo).get());
		}

		return "BAMWRITEOFF";
	}

	

	@RequestMapping(value = "adminTimesheetMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminTimesheetMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		
       String empid = (String) req.getSession().getAttribute("USERID");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

			md.addAttribute("Profile", btmAdminAssociateProfileRep.getAssociatelist());
		
		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("TimesheetList", btmEmpTimeSheetRep.getTimeSheetdata(resId));

		}
		
		
		return "BTMAdminTimesheetMaster";
	}

	@RequestMapping(value = "adminCalendarMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminCalendarMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String year, @RequestParam(required = false) String month,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminCalendarMaster", adminOperServices.getCalendarlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminHolidayDetails", adminOperServices.getMonthlyHolidaylist(year, month));

		}

		return "BTMAdminCalendarMaster";
	}


	@RequestMapping(value = "adminDailyActivity", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminDailyActivity(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
		//	md.addAttribute("adminCalendarMaster", adminOperServices.getCalendarlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
		//	md.addAttribute("adminHolidayDetails", adminOperServices.getMonthlyHolidaylist(year, month));

		}
		return "BTMAdminDailyActivity";
	}
	
	

	@RequestMapping(value = "adminDocumentMaintenance", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminDocumentMaintenance(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String emp_id, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		
		    md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
			md.addAttribute("Document", documentMainRep.Documents());
			String userId = (String) req.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");
		
		return "BTMAdminDocMaintenance";
	}

	@RequestMapping(value = "uploadDocss", method = RequestMethod.POST)
	@ResponseBody
	public String uploadDoc(@RequestParam("document") MultipartFile file,Model md, HttpServletRequest rq,
	@RequestParam(required = false) String doc_id,@RequestParam(required = false) String doc_name,
	@RequestParam(required = false) String doc_desc,@RequestParam(required = false) String doc_type,
	@RequestParam(required = false) String doc_group,@RequestParam(required = false) String file_name) 
            throws ParseException, IOException {
		String userId = (String) rq.getSession().getAttribute("USERID");
		String username = (String) rq.getSession().getAttribute("USERNAME");
		try {
			logger.info("Received file: {}", file.getOriginalFilename());

			byte[] bytes = file.getBytes();

			String msg = onDutyServices.uploadDocss( bytes,doc_id,doc_name,doc_desc,doc_type,doc_group,file_name,userId,username,file.getOriginalFilename());

			logger.info("File uploaded successfully: {}", file.getOriginalFilename());
			return msg;
		} catch (IOException e) {
			logger.error("File upload failed: {}", e.getMessage());
			return "File upload failed: " + e.getMessage();
		}
}
   
	
	@GetMapping("/{docRefNo}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String docRefNo) {
        BTMDocumentMaster document = btmDocumentMasterRep.getDocument(docRefNo);
        if (document != null) {
            byte[] documentContent = document.getDocument();
            String fileName = document.getDoc_location();
            String nameoffile = document.getFile_name();
            String fileType = document.getFile_type();

            if (fileName.endsWith(".png")) {
                fileType = "image/png";
            } else if (fileName.endsWith(".pdf")) {
                fileType = "application/pdf";
            } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                fileType = "image/jpeg";
            } else if (fileName.endsWith(".mp4")) {
                fileType = "video/mp4";
            } else if (fileType == null || fileType.isEmpty()) {
                // Set a default file type if file_type is null or unrecognized
                fileType = "application/octet-stream";
            }
            ByteArrayResource resource = new ByteArrayResource(documentContent);
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + fileName)
                .contentType(MediaType.parseMediaType(fileType))
                .contentLength(documentContent.length)
                .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	@RequestMapping(value = "dateandDay", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<LeaveResponseModal> dateandDay(
			@RequestParam(required = false) String firstval,@RequestParam(required = false) String lastval,
			@ModelAttribute DocumentMaintenance DocMain,
			Model md, HttpServletRequest rq,RedirectAttributes ra) throws ParseException, IOException {

		
		ArrayList<LeaveResponseModal> arl=onDutyServices.dateSelector(firstval,lastval);
		
		
		return arl;
	}

// ============================================  Admin Module End =====================================

//=============================================  Operation Module =======================================

	@RequestMapping(value = "changePassword", method = { RequestMethod.GET, RequestMethod.POST })
	public String changePassword(@RequestParam(required = false) String formmode, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid = (String) req.getSession().getAttribute("USERID");

		md.addAttribute("profileManager", btmAdminAssociateProfileRep.getEmployeedetail(userid));
		return "BTMChangePassword";
	}
	
	@RequestMapping(value = "changePasswordLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public String changePasswordLogin(@RequestParam(required = false) String formmode, Model md, HttpServletRequest req)
			throws ParseException {
	return "BTMChangePasswordLogin";
	}

	@RequestMapping(value = "changePasswordReq", method = RequestMethod.POST)
	@ResponseBody
	public String changePasswordReq(@RequestParam("oldpass") String oldpass, @RequestParam("newpass") String newpass,@RequestParam("userid") String userid,
			Model md, HttpServletRequest rq) {
		String msg = loginServices.changePassword(oldpass, newpass, userid);
		 md.addAttribute("message", "succes");
		 md.addAttribute("profileManager", btmAdminAssociateProfileRep.getEmployeedetail(userid)); 
		 return msg;  
	}
	@RequestMapping(value = "changePasswordRequest", method = RequestMethod.POST)
	@ResponseBody
	public String changePasswordReq(@RequestParam("oldpass") String oldpass, @RequestParam("newpass") String newpass,
	        Model md, HttpServletRequest rq) {
	    String userid = (String) rq.getSession().getAttribute("USERID");
	    String msg = loginServices.changePassword(oldpass, newpass, userid);
	    
	    // Invalidate (clear) the session
	    rq.getSession().invalidate();

	    return msg;
	}


	@RequestMapping(value = "applyLeave", method = { RequestMethod.GET, RequestMethod.POST })
	public String applyLeave(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal resId, String empid, Model md, HttpServletRequest req) throws ParseException {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("profileManager", btmAdminAssociateProfileRep.getEmployeedetail(userId));

		md.addAttribute("Approvalmail", placementServices.getno_of_stages()); 
		List<String> days = placementServices.getno_of_days(); 
		
		List<ResourceMaster> emails=resourceMasterRepo.getmail();
		md.addAttribute("emails",emails );
		
	    //md.addAttribute("getno_of_days", days);
		// md.addAttribute("LeaveList", leaveMasterRep.getLeaveListbyRecord(resId));
		// md.addAttribute("srl_no", onDutyServices.getSrlNoValue());

		return "BTMLeaveMaster";
	}
	@RequestMapping(value = "mailsubmit", method = RequestMethod.POST)
	@ResponseBody
	public String mailsubmit(@RequestParam(required = false) String email1,@RequestParam(required = false) String email2,
			@RequestParam(required = false) String email3,@RequestParam(required = false) String email4,
			@RequestParam(required = false) String email5,HttpServletRequest req) {

		String userId = (String) req.getSession().getAttribute("USERID");
		String username = (String) req.getSession().getAttribute("USERNAME");
		//String b = a;
		//String to = b;
		String from = "prasanth.m@bornfire.in";
		String usernamelogin = "prasanth.m@bornfire.in"; // change accordingly
		String password = "MiddleEast#123"; // change accordingly
		String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
		//String ref_no = d;
		String mail=maile.sendmailss(userId,username,email1,email2,email3,email4,email5,usernamelogin,password,from,host);
		
		return mail;
	}
	

	@RequestMapping(value = "leaveMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String leaveMasterAdd(@ModelAttribute LeaveMaster leaveMaster,
			@ModelAttribute SampleLeaveMaster sampleLeaveMaster, @RequestParam(required = false) String formmode,@RequestParam(required = false) String employee_id,
			@RequestParam(required = false) String year,@RequestParam(required = false) String from_date,	Model md, HttpServletRequest rq) throws ParseException, SQLException {
		String msg = onDutyServices.addLeave(leaveMaster, sampleLeaveMaster, formmode);
		return msg;
	}

	@RequestMapping(value = "markOnDuty", method = { RequestMethod.GET, RequestMethod.POST })
	public String markOnDuty(@RequestParam(required = false) String formmode, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid1 = (String) req.getSession().getAttribute("USERID");

		md.addAttribute("profileManager", btmAdminAssociateProfileRep.getEmployeedetail(userid1));
		md.addAttribute("projectManager", btmProjectMasterRep.getProjectlist());
		BTMAdminAssociateProfile test = btmAdminAssociateProfileRep.getEmployeedetail(userid1);
		// md.addAttribute("srl_no", onDutyServices.getSrlNo());

		return "BTMMarkOnDuty";
	}

	@RequestMapping(value = "onDutyAdd", method = RequestMethod.POST)
	@ResponseBody
	public String onDutyAdd(@ModelAttribute OnDuty onDuty, @ModelAttribute BTMAdminSampleOD btmAdminOndutyCount,
			@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq) throws ParseException, SQLException {

		String msg = onDutyServices.addOnDuty(onDuty, btmAdminOndutyCount, formmode);
		return msg;
	}

	@RequestMapping(value = "travelMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String travelMaster(@RequestParam(required = false) String formmode, Model md, HttpServletRequest req)
			throws ParseException {

		String userid1 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String Travel = "TRA";
		md.addAttribute("travel_Ref", Travel.concat(onDutyServices.getTravelRef()));

		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("profileManager", btmAdminAssociateProfileRep.getEmployeedetail(userid1));

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("travelMaster", new BTMTravelMaster());
			md.addAttribute("profileManager", btmAdminAssociateProfileRep.getEmployeedetail(userid1));

		}

		return "BTMTravelMaster";
	}

	@RequestMapping(value = "travelMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String travelMasterAdd(@ModelAttribute BTMTravelMaster BTMtravelMaster, Model md, HttpServletRequest rq) {

		String msg = onDutyServices.addTravelList(BTMtravelMaster);
		return msg;
	}
	
	@RequestMapping(value = "submittravel", method = RequestMethod.POST)
	@ResponseBody
	public String submittravel(@RequestParam(required = false) String tra_ref, @ModelAttribute BTMTravelMaster btmTravelMaster, Model md, HttpServletRequest rq) {

		
		
	    String msg = onDutyServices.addTravelList(btmTravelMaster);
	    
	    System.out.println(btmTravelMaster.getAss_id());
	    System.out.println(btmTravelMaster.getClient_id());
	    System.out.println(btmTravelMaster.getAss_name());
	    System.out.println(btmTravelMaster.getPrj_id());
	    return msg;
	}


	@RequestMapping(value = "claimExpenses", method = { RequestMethod.GET, RequestMethod.POST })
	public String claimExpenses(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, Model md, HttpServletRequest req) throws ParseException {

		String userid2 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String Expense = "EXP";
		md.addAttribute("expense_Ref", Expense.concat(onDutyServices.getExpRef()));

		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminExpenseReport", btmAdminAssociateProfileRep.getEmployeedetail(userid2));

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("adminExpenseReport", btmAdminAssociateProfileRep.getEmployeedetail(userid2));
		}
		return "BTMClaimExpenses";
	}

	@RequestMapping(value = "claimExpensesAdd", method = RequestMethod.POST)
	@ResponseBody
	public String claimExpensesAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute ExpenseMaster expenseMaster, Model md, HttpServletRequest rq) {

		String msg = onDutyServices.addExpenseReport(expenseMaster, formmode);
		return msg;
	}

	@RequestMapping(value = "workAssignment", method = { RequestMethod.GET, RequestMethod.POST })
	public String workAssignment(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {

		String userid1 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignListById(userid1));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		}

		return "BTMWorkAssign";
	}
		
	

	@RequestMapping(value = "timeSheet", method = { RequestMethod.GET, RequestMethod.POST })
	public String timeSheet(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String emp_id, String month, BigDecimal year, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid2 = (String) req.getSession().getAttribute("USERID");
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");

			md.addAttribute("TimesheetList", btmEmpTimeSheetRep.getTimeSheetLast(userid2));

		}
		else if (formmode.equals("modify")) {

			md.addAttribute("formmode", "modify");
			md.addAttribute("TimesheetList", btmEmpTimeSheetRep.getTimeSheetModify(emp_id, year, month));

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("TimesheetList", onDutyServices.getTimeSheetselect(userid2));
		} else if (formmode.equals("Yes")) {
			md.addAttribute("formmode", "Yes");
			md.addAttribute("TimesheetList", onDutyServices.getTimeSheetselect(userid2));
		}else if (formmode.equals("addnew")) {
			md.addAttribute("formmode", "addnew");
		}


		return "BTMTimesheetOperation";
	}

	@RequestMapping(value = "timeSheetedit", method = { RequestMethod.GET, RequestMethod.POST })
	public String timeSheetedit(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String month, @RequestParam(required = false) String year,
			@RequestParam(required = false) String empid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		String userid2 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("TimesheetList", timeSheetBeanRep.getTimesheetList(userid2));
			// md.addAttribute("WorkList", btmWorkAssignmentRep.getWorkMaster(id2));
			// md.addAttribute("TimesheetList",
			// timesheetManagementRep.getTimesheetList(id2));
			// md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));
		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("TimesheetList", timeSheetBeanRep.getTimesheetList(userid2));
			// md.addAttribute("WorkList", btmWorkAssignmentRep.getWorkMaster(id2));

			md.addAttribute("timeSheetList", timeSheetBeanRep.getTimeSheetdata(empid, year, month));
			// md.addAttribute("TimesheetList",
			// timesheetManagementRep.getTimesheetList(id2));
			// md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(id2));

		}

		return "BTMTimeSheet";
	}

	@RequestMapping(value = "timeSheetAdd", method = RequestMethod.POST)
	@ResponseBody
	public String timeSheetAdd(@ModelAttribute BTMEmpTimeSheet btmEmpTimeSheet, Model md,
			HttpServletRequest rq) {
		String msg = onDutyServices.addTimeSheet(btmEmpTimeSheet);
		return msg;
	}
	
	
	@RequestMapping(value = "timeSheetEdit", method = RequestMethod.POST)
	@ResponseBody
	public String timeSheetEdit(@RequestParam(required = false) String empid,@RequestParam(required = false) BigDecimal year,@RequestParam(required = false) String month,@ModelAttribute BTMEmpTimeSheet btmEmpTimeSheet, Model md, HttpServletRequest rq) {

		String msg = onDutyServices.EditTimeSheet(btmEmpTimeSheet,empid,year,month);
		return msg;
	}

	@RequestMapping(value = "issueTracker", method = { RequestMethod.GET, RequestMethod.POST })
	public String issueTracker(@RequestParam(required = false) String formmode,String srl_no,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if(formmode == null || formmode.equals("view")) {
			  md.addAttribute("formmode", "view");
		  }
		else if ( formmode.equals("list")) {
			md.addAttribute("formmode", "list");
		  
		  md.addAttribute("IssueTracker", issueTrackerRep.findAll());
		  
		  }  else if (formmode.equals("elist")) {
			  
			  md.addAttribute("formmode", "elist");
			  md.addAttribute("issueTracker", issueTrackerRep.getIssueList());
		
			  } 
		else if ( formmode.equals("view1")) { md.addAttribute("formmode", "view1");
		md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
		  md.addAttribute("issueview", issueTrackerRep.getIssue(srl_no));
		  
		  } 
		  
		 else if (formmode.equals("modify")) {
			  
			  md.addAttribute("formmode", "modify");
			  md.addAttribute("issueview", issueTrackerRep.getIssue(srl_no));
			  md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
			  md.addAttribute("issuemodify", issueTrackerRep.getIssue(srl_no));
		
		} else if (formmode.equals("add")) { 
		  md.addAttribute("formmode", "add");
		  md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
		  } else if (formmode.equals("upload")) { 
			  md.addAttribute("formmode", "upload");
			  }
		return "BTMIssueTracker";
	}
	@RequestMapping(value = "issueAdd", method = RequestMethod.POST)
	@ResponseBody
	public String issueAdd(@RequestParam(required = false) String formmode,
			@ModelAttribute IssueTracker issuetracker, Model md, HttpServletRequest rq) {
		String msg = onDutyServices.addissue(issuetracker,formmode);
		return msg;
	}

	@RequestMapping(value = "issueEdit", method = RequestMethod.POST)
	@ResponseBody
	public String issueEdit(@RequestParam(required = false) String formmode, @ModelAttribute IssueTracker issuetracker,
			Model md, HttpServletRequest rq) {
		String msg = onDutyServices.editissue(issuetracker, formmode);
		return msg;
	}

	@RequestMapping(value = "downFormat", method = RequestMethod.GET)
	public ResponseEntity<Resource> generateExcelReport() throws IOException {
		List<IssueTracker> Issues = issueTrackerRep.getIssueFormat();

		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();

		int rowCount = 0;
		Row row = sheet.createRow(rowCount++);

		Font font = wb.createFont();
		font.setBold(true);

		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setBorderTop(BorderStyle.THICK);
		cellStyle.setBorderBottom(BorderStyle.THICK);
		cellStyle.setBorderLeft(BorderStyle.THICK);
		cellStyle.setBorderRight(BorderStyle.THICK);
		cellStyle.setFont(font);

		Cell cell = row.createCell(0);
		cell.setCellValue("srl_no");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(1);
		cell.setCellValue("Category");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(2);
		cell.setCellValue("Groups");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(3);
		cell.setCellValue("Product");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(4);
		cell.setCellValue("Module");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(5);
		cell.setCellValue("Screen");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(6);
		cell.setCellValue("Operation");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(7);
		cell.setCellValue("Description");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(8);
		cell.setCellValue("Issue Ref No");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(9);
		cell.setCellValue("Date of Issue");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(10);
		cell.setCellValue("Reported By");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(11);
		cell.setCellValue("Approved By");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(12);
		cell.setCellValue("Nature of Issue");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(13);
		cell.setCellValue("Issue Details");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(14);
		cell.setCellValue("Severity");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(15);
		cell.setCellValue("Remarks");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(16);
		cell.setCellValue("Assigned To");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(17);
		cell.setCellValue("Date of Assigned");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(18);
		cell.setCellValue("Fix Period");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(19);
		cell.setCellValue("Delivery Date");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(20);
		cell.setCellValue("Fix Details");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(21);
		cell.setCellValue("Date of Fix");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(22);
		cell.setCellValue("Tested By");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(23);
		cell.setCellValue("Tested On");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(24);
		cell.setCellValue("Test Result");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(25);
		cell.setCellValue("Issue Status");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(26);
		cell.setCellValue("Turn Around Time");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(27);
		cell.setCellValue("Final Closure");
		cell.setCellStyle(cellStyle);

		cellStyle = wb.createCellStyle();
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);

		for (IssueTracker issue : Issues) {
			row = sheet.createRow(rowCount++);

			int columnCount = 0;

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getSrl_no());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getCategory());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getGroups());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getProduct());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getModule());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getScreen());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getOperation());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getOper_desc());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getIssue_ref_no());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getDate_of_issue());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getRpt_by());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getApr_by());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getNat_of_issue());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getIssue_details());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getIssue_severity());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getIssue_rmks());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getAssign_to());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getAssign_date());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getFix_period().toString());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getDel_date());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getFix_details());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getDate_of_fix());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getTest_by());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getTest_date());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getTest_results());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getIssue_status());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getTat_per().doubleValue());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getFinal_cls());
			cell.setCellStyle(cellStyle);

		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		wb.write(os);
		wb.close();

		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(
				MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=IssueTracker.xlsx");

		ResponseEntity<Resource> response = new ResponseEntity<Resource>(new InputStreamResource(is), headers,
				HttpStatus.OK);

		return response;
	}
//================================== Opertion Module End ======================================================

//==================================== Inquiries Module =========================================================

	@RequestMapping(value = "organizationPolicy", method = { RequestMethod.GET, RequestMethod.POST })
	public String organizationPolicy(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		return "BTMOrganizationPolicy";
	}

	@RequestMapping(value = "holidayMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String holidayMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("holidayList", adminOperServices.getHolidaylist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("BTMholidayList", adminOperServices.getHolidayDetail(resId));

		}

		return "BTMHolidayMaster";
	}

	@RequestMapping(value = "associateMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String associateMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid1 = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("associateMaster", inquiriesServices.getAssociateData(userid1));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("associateMaster", inquiriesServices.getAssociateData(userid1));

		}

		return "BTMAssociateProfile";
	}

	@RequestMapping(value = "profileMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String profileMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String id2 = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("profileMasterList", adminOperServices.getProfileManager(id2));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("profileMasterList", adminOperServices.getProfileManager(id2));

		}

		return "BTMProfileMaster";
	}

	@RequestMapping(value = "projectMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String projectMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resName, @RequestParam(required = false) String resId, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("projectMasterList", adminOperServices.getProjectMasterlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("projectList", btmProjectMasterRep.getProjectShow(resId, resName));
			md.addAttribute("projectDetails", btmProjectDetailsRep.getProjectDetails(resId));
			md.addAttribute("projectTeamDetails", btmProjectTeamDetailsRep.getProjectTeamDetails(resId));

		}

		return "BTMInqueriesProjectMaster";
	}

	@RequestMapping(value = "leaveMaster", method =  RequestMethod.GET)
	public String leaveMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal resId, @RequestParam(required = false) String RefId, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("LeaveList", leaveMasterRep.getLeaveListbyid(userid));
		}

		else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("LeaveList", leaveMasterRep.getLeaveListbyRecord(resId));
			md.addAttribute("LeaveListref", leaveMasterCounterRep.getleavelistbyrec(RefId));

		}

		return "BTMILeaveMaster";
	}

	@RequestMapping(value = "onDuty", method = { RequestMethod.GET, RequestMethod.POST })
	public String onDuty(@RequestParam(required = false) String formmode, @RequestParam(required = false) String resId,
			@RequestParam(required = false) String RefId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("onDutyList", onDutyRep.getOdListbyid(userid));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("onDutyList", onDutyRep.getOdListbyrecord(resId));
			md.addAttribute("onDutyListCount", bTMAdminOndutyCountRep.getondutybyref(RefId));

		}

		return "BTMOdApply";
	}

	@RequestMapping(value = "listOfTravel", method = { RequestMethod.GET, RequestMethod.POST })
	public String listOfTravel(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("TravelList", btmTravelMasterRep.getTravelListbyid(userid));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminTravelMaster", adminOperServices.getTravelMaster(resId));
		}

		return "BTMTravelList";
	}

	@RequestMapping(value = "expensesReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String expensesReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid2 = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("ExpenseList", extenseMasterRep.getListByassId(userid2));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("expenseReport", adminOperServices.getReportManager(resId));

		}
		return "BTMExpensesReport";

	}

	@RequestMapping(value = "workAssignList", method = { RequestMethod.GET, RequestMethod.POST })
	public String workAssignList(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid1 = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());
		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		}

		return "BTMInquiriesWorkAssignment";
	}

	@RequestMapping(value = "DepreciationTransaction", method = { RequestMethod.GET, RequestMethod.POST })
	public String solutionDocument(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid,@RequestParam(required = false) String tranid,
			 Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		
		md.addAttribute("menu", "BTMHeaderMenu");
		
		BAM_AssetFlows_Entity newInventory = new BAM_AssetFlows_Entity();
        newInventory.setEntry_user(userId);  // Set ENTRY_USER as the logged-in user
        newInventory.setModify_user(userId); // Set MODIFY_USER as the logged-in user
        newInventory.setVerify_user(userId); // Set VERIFY_USER as the logged-in user (optional for add)

		
		
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("gettran", BAM_AssetFlows_rep.gettrandata());
			} 
		else if (formmode.equals("view")) {
			

			md.addAttribute("formmode", "view");
			md.addAttribute("gettranview", BAM_AssetFlows_rep.gettranview(tranid));
			md.addAttribute("gettranview1", newInventory);
		}
		return "BAMDepriciationTransaction";
	}

	
	@RequestMapping(value = "attendanceRegister", method = { RequestMethod.GET, RequestMethod.POST })
	public String attendanceRegister(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal serialNo,
			@RequestParam(required = false) String yearone,
			@RequestParam(required = false) String monthone ,
			@RequestParam(required = false) String dayone,Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatdate = new SimpleDateFormat("dd/MM/yyyy");
		String str = formatdate.format(cal.getTime());
		Date dat1 = null;
		try {
			dat1 = formatdate.parse(str);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		// resourceMasterRepo.gettotaluser();
		int totalemployees = resourceMasterRepo.gettotalnum();
		int present = attendanceRegisterRep.getALLpresent(dat1);
		int absent = totalemployees-present;
		int onduty = 0;
		md.addAttribute("attendanceList", attendanceRegisterRep.getALL(dat1));
		md.addAttribute("numofEmployees",totalemployees);
		md.addAttribute("numofPresent",present);
		md.addAttribute("numofabsent",absent);
		md.addAttribute("numofabsent1",absent);
		md.addAttribute("numofonduty",onduty);
		md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
		SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
		String month = formatMonth.format(cal.getTime());
		
		md.addAttribute("CurrentMonth",month);
		SimpleDateFormat formatyear = new SimpleDateFormat("yyyy");
		String year = formatyear.format(cal.getTime());
		md.addAttribute("Currentyear",year);
		md.addAttribute("sms",AttendanceRegisterGetRep.getsms(yearone,monthone,dayone));
		//List<AttendanceRegisterGet> smsd=AttendanceRegisterGetRep.getsms(yearone,monthone,dayone);
		//System.out.println("hhhhhhhhhhhhhhhhhhhhhhh"+smsd.get(0));
		
		System.out.println("sms seendingoooooooooooooooooooooooooooooo"+AttendanceRegisterGetRep.getsms(yearone,monthone,dayone));
		
		
		
		return "BTMAttendanceRegister";
	}
	@RequestMapping(value = "attendanceAbsentRegister", method = { RequestMethod.GET, RequestMethod.POST })
	public String attendanceAbsentRegister(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal serialNo, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		return "BTMAttendanceAbsentList";
	}

	
	  @RequestMapping(value = "timesheetMaster", method = { RequestMethod.GET,RequestMethod.POST }) 
	  public String timesheetMaster(@RequestParam(required =false) String formmode,@RequestParam(required = false) String resId, 
	  @RequestParam(required =false) Optional<Integer> page,@RequestParam(required = false) String month, @RequestParam(required = false) BigDecimal year, 
	  @RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) throws ParseException{
			
			  String empId = (String) req.getSession().getAttribute("USERID");
			  // AttendanceID
		//	  userid3 = (AttendanceID) req.getSession().getAttribute("USERID");
			  String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
			  
			  if (formmode == null || formmode.equals("list")) {
			  
			  md.addAttribute("formmode", "list");
			  
			  md.addAttribute("TimesheetList", btmEmpTimeSheetRep.getTimeSheetLast(empId));
			  
			  } else if (formmode.equals("view")) {
			  
			  md.addAttribute("formmode", "view"); md.addAttribute("TimesheetList",
			  btmEmpTimeSheetRep.getTimeSheetLast(resId)); }
			  else if (formmode.equals("view1")) {
				  
				  md.addAttribute("formmode", "view1"); md.addAttribute("TimesheetList",
				  btmEmpTimeSheetRep.getTimeSheetdataView(resId,month,year)); }
			  
			 
			 
			 
			  

		return "BTMTimesheetMaster";
	}
	  
	  @RequestMapping(value = "timesheetMain", method = { RequestMethod.GET, RequestMethod.POST })
		public String timesheetMain(@RequestParam(required = false) String formmode,
				@RequestParam(required = false) String emp_id, @RequestParam(required = false) BigDecimal year,
				@RequestParam(required = false) String month, Model md, HttpServletRequest req) throws ParseException {

		  String userId = (String) req.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");

			if (formmode == null || formmode.equals("list")) {

				md.addAttribute("formmode", "list");

				md.addAttribute("TimesheetList", btmEmpTimeSheetRep.getTimeSheetlist());

			} else if (formmode.equals("approve")) {

				md.addAttribute("formmode", "approve");
				md.addAttribute("timeSheetVerify", btmEmpTimeSheetRep.getTimeSheetModify(emp_id, year, month));
			} else if (formmode.equals("view")) {

				md.addAttribute("formmode", "view");
				md.addAttribute("TimesheetView", btmmTimeSheetRep.getTimeSheetList(emp_id));
			} else if (formmode.equals("view1")) {

				md.addAttribute("formmode", "view1");
				md.addAttribute("timeSheetVerify", btmmTimeSheetRep.getTimeSheetVerify(emp_id, year, month));
			}

			return "BTMTimeSheetMaintenance";
		}

	@RequestMapping(value = "listOfIssue", method = { RequestMethod.GET, RequestMethod.POST })
	public String listOfIssue(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if ( formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
		  
		  md.addAttribute("IssueTracker", issueTrackerRep.findAll());
		  
		  } 
		else if ( formmode.equals("view1")) { md.addAttribute("formmode", "view1");
		md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
		  md.addAttribute("issueview", issueTrackerRep.getIssue(userid));
		  
		  } 
		  
		return "BTMIssueTracker";

	}

//   =================================== Reports Module =============================================

	@RequestMapping(value = "attendanceReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String attendanceReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
		md.addAttribute("role",  resourceMasterRepo.getrole(userId));
		return "BTMAttendanceReport";
	}

	@RequestMapping(value = "employeeRegister", method = { RequestMethod.GET, RequestMethod.POST })
	public String employeeRegister(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
		return "BTMEmployeeRegister";
	}


	@RequestMapping(value = "projectMasterReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String projectMasterReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("projectMasterList", adminOperServices.getProjectMasterlist());
		md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
		return "BTMProjectMasterReports";
	}

	@RequestMapping(value = "holidayList", method = { RequestMethod.GET, RequestMethod.POST })
	public String holidayList(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
		md.addAttribute("menu", "BTMHeaderMenu");
		return "BTMHolidayList";
	}

	@RequestMapping(value = "profileMasterReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String profileMasterReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
		
		return "BTMProfileMasterReport";
	}

	@RequestMapping(value = "leaveRegister", method = { RequestMethod.GET, RequestMethod.POST })
	public String leaveRegister(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
		return "BTMLeaveRegister";
	}

	@RequestMapping(value = "timesheetReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String timsheetReport(@RequestParam(required = false) String EmpId,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("profileManagers", btmAdminAssociateProfileRep.getEmployeedetail2());
		md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
	
		
		return "BTMTimeSheetJasperReport";
	}


	@RequestMapping(value = "workAssignReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String workAssignReport(@RequestParam(required = false) String empId,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("profileManagers", btmWorkAssignmentRep.getWorkAssigndetail());
		return "BTMWorkAssignJasperReport";
	}
	

	// ============================================ Placement Menu =====================================

	//==================================BankMaster=========================================
	
	@RequestMapping(value = "bankMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String bankMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String bank_srl_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("banklist", placementServices.getBanklist());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);
			

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			
			md.addAttribute("banklist", bankMasterRep.getBanklist(bank_srl_no));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("banklist", bankMasterRep.getBanklist(bank_srl_no));
			
		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("banklist", bankMasterRep.getBanklist(bank_srl_no));
			
		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("banklist", bankMasterRep.getBanklist(bank_srl_no));
			
		} else {
			
			md.addAttribute("formmode", formmode);
			md.addAttribute("banklist", bankMasterRep.getBanklist(""));
		}
		return "BTMBankMaster";
	}
	
	@RequestMapping(value = "bankMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String bankMasterAdd(@RequestParam(required = false) String formmode,
			@ModelAttribute BankMaster bankMaster, Model md, HttpServletRequest rq) {
		String userid1 = (String) rq.getSession().getAttribute("USERID");
		String msg = placementServices.addBankuser(bankMaster, formmode,userid1);
		return msg;
	}
	
	@RequestMapping(value = "bankMasterModify", method = RequestMethod.POST)
	  @ResponseBody public String bankMasterModify(@RequestParam(required = false) String bank_srl_no,
			@ModelAttribute BankMaster bankMaster, Model md, HttpServletRequest rq )
	  throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException,
	  IOException {
		String userid1 = (String) rq.getSession().getAttribute("USERID");
	  
	  String msg = placementServices.bankMasterModify(bankMaster, bank_srl_no,userid1);
	  return msg;
	  }
	
	 @RequestMapping(value = "bankMasterVerify", method = RequestMethod.POST)
	  @ResponseBody public String bankMasterVerify(@RequestParam(required = false) String bank_srl_no,
			@ModelAttribute BankMaster bankMaster, Model md, HttpServletRequest rq )
	  throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException,
	  IOException {
		  
		  String userid1 = (String) rq.getSession().getAttribute("USERID");
	  String msg = placementServices.bankMasterVerify(bankMaster, bank_srl_no,userid1);
	  return msg;
	  }
	 
	 @RequestMapping(value = "bankMasterDelete", method = RequestMethod.POST)
	   @ResponseBody public String bankMasterDelete(@RequestParam(required = false) String bank_srl_no,
			@ModelAttribute BankMaster bankMaster, Model md, HttpServletRequest rq )
	  throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException,
	  IOException {
		 String userid1 = (String) rq.getSession().getAttribute("USERID");
	  String msg = placementServices.bankMasterDelete(bankMaster, bank_srl_no,userid1);
	  return msg;
	  }
	
	// =================================clientMaster======================================
	
	@RequestMapping(value = "clientMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String clientMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String client_srl_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("clientlist", placementServices.getClientlist());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);
			

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("clientlist", clientMasterRep.getClientlist(client_srl_no));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("clientlist", clientMasterRep.getClientlist(client_srl_no));
			
		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("clientlist", clientMasterRep.getClientlist(client_srl_no));
			
		} else if (formmode.equals("delete")) {
		
			md.addAttribute("formmode", formmode);
			md.addAttribute("clientlist", clientMasterRep.getClientlist(client_srl_no));
			
		} else {
			
			md.addAttribute("formmode", formmode);
			md.addAttribute("clientlist", clientMasterRep.getClientlist(" "));
		}
		return "BTMClientMaster";
	}
	
	@RequestMapping(value = "clientMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String clientMasterAdd(@RequestParam(required = false) String formmode,
			@ModelAttribute ClientMaster clientMaster, Model md, HttpServletRequest rq) {
		String userid1 = (String) rq.getSession().getAttribute("USERID");
		String msg = placementServices.addClientUser(clientMaster, formmode , userid1);
		return msg;
	}
	
	
	  @RequestMapping(value = "clientMasterModify", method = RequestMethod.POST)
	  @ResponseBody public String clientMasterModify(@RequestParam(required = false) String client_srl_no,
			@ModelAttribute ClientMaster clientMaster, Model md, HttpServletRequest rq )
	  throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException,
	  IOException {
		  
		  String userid1 = (String) rq.getSession().getAttribute("USERID");
	  
	  String msg = placementServices.clientMasterModify(clientMaster, client_srl_no,userid1);
	  return msg;
	  }
	 
      @RequestMapping(value = "clientMasterVerify", method = RequestMethod.POST)
	  @ResponseBody public String clientMasterVerify(@RequestParam(required = false) String client_srl_no,
			@ModelAttribute ClientMaster clientMaster, Model md, HttpServletRequest rq )
	  throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException,
	  IOException {
    	  String userid1 = (String) rq.getSession().getAttribute("USERID");
	  String msg = placementServices.clientMasterVerify(clientMaster, client_srl_no,userid1);
	  return msg;
	  }
      
      @RequestMapping(value = "clientMasterDelete", method = RequestMethod.POST)
	   @ResponseBody public String clientMasterDelete(@RequestParam(required = false) String client_srl_no,
			@ModelAttribute ClientMaster clientMaster, Model md, HttpServletRequest rq )
	  throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException,
	  IOException {
		  
	  String msg = placementServices.clientMasterDelete(clientMaster, client_srl_no);
	  return msg;
	  }
      
    //invoice

  	@RequestMapping(value = "invoiceMaster", method = { RequestMethod.GET, RequestMethod.POST })
  	public String invoiceMaster(@RequestParam(required = false) String formmode,@RequestParam(required = false) String invoice_no,String inv_no,String po_id,
  			@RequestParam(required = false) String EmpId,@RequestParam(required = false) String bank_name, Model md, HttpServletRequest req) throws ParseException {
  		String userId = (String) req.getSession().getAttribute("USERID");
  		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
  		md.addAttribute("menu", "BTMHeaderMenu");
  		if (formmode == null || formmode.equals("list")) {
  			md.addAttribute("formmode", "list");
  			

			 Date currentDate = new Date();

		        // Create a date format
		        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

		        // Format the current date
		        String formattedDate = dateFormat.format(currentDate);

		        // Print the formatted date
		        System.out.println("Current Date: " + formattedDate);
		        md.addAttribute("formattedDate", formattedDate);
  			//md.addAttribute("invoiceMasterList", placementServices.getplacementlist2());
  			md.addAttribute("invoiceMasterList",invoiceMasterRep.getplacementlist());
  			//md.addAttribute("invoicefromplacement",placementMaintenanceRep.getinvlist());
  		} else if (formmode.equals("add")) {

  			md.addAttribute("formmode", formmode);
  			md.addAttribute("banklist", bankMasterRep.getBanklist2());
  			md.addAttribute("clients", clientMasterRep.getClientlist2());
  			

  		} else if (formmode.equals("edit")) {

  			md.addAttribute("formmode", formmode);
  			md.addAttribute("invoiceMasterList", invoiceMasterRep.getplacementlist2(po_id));
  			//md.addAttribute("invoiceMasterList", placementMaintenanceRep.getinvoicelist(inv_no));
  			//md.addAttribute("clients", clientMasterRep.getClientlist2());

  		} else if (formmode.equals("view")) {

  			md.addAttribute("formmode", formmode);
  			md.addAttribute("invoiceMasterList", invoiceMasterRep.getplacementlist2(po_id));
  			
  			
  		} else {
  			md.addAttribute("formmode", formmode);
  		}

  		return "BTMInvoiceMaster";
  	}

  	@RequestMapping(value = "invoiceMasterAdd", method = RequestMethod.POST)
  	@ResponseBody
  	public String invoiceMasterAdd(@RequestParam(required = false) String formmode,
  			@ModelAttribute InvoiceMaster invoiceMaster, Model md, HttpServletRequest rq) {
  		String msg = placementServices.addInvoiceUser(invoiceMaster, formmode);
  		return msg;
  	}

  	 @RequestMapping(value = "invoiceMasterModify", method = RequestMethod.POST)
  	  @ResponseBody public String invoiceMasterModify(@RequestParam(required = false) String po_id,
  			@ModelAttribute InvoiceMaster invoiceMaster, Model md, HttpServletRequest rq )
  	  throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException,
  	  IOException {
  		  System.out.println(po_id);
  	  String msg = placementServices.invoiceMasterModify(invoiceMaster, po_id);
  	  return msg;
  	  }
  	 
		
		  @RequestMapping(value = "/invoicedelete", method = RequestMethod.POST)
		  
		  @ResponseBody public String invoicedelete(@RequestParam(required = false)
		  String po_id,PlacementMaintenance placementMaintenance, InvoiceMaster invoiceMaster,  Model md, HttpServletRequest rq) {
		  
			  PlacementMaintenance up = placementMaintenanceRep.getPoM(po_id);
			    if (up == null) {
			        return "Error: PlacementMaintenance not found for po_id " + po_id;
			    }
			    placementMaintenanceRep.delete(up);

			    InvoiceMaster up1 = invoiceMasterRep.getplacementlist2(po_id);
			    if (up1 == null) {
			        return "Error: InvoiceMaster not found for po_id " + po_id;
			    }
			    invoiceMasterRep.delete(up1);
		  
		  return "Deleted successfully";
		  
		  }
		 
	
	
	//==================================================================================================

	@RequestMapping(value = "resourceMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String resourceMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String EmpId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("placementProfileList", placementServices.getUsersList());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getUser(EmpId));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getUser(EmpId));
			
		} else {
			
			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getUser(""));
		}
		return "BTMResourceMaster";
	}

	@RequestMapping(value = "resourceMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String resourceMasterAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute PlacementResourceMaster placementResourceMaster, Model md, HttpServletRequest rq) {
		String msg = placementServices.addUser(placementResourceMaster, formmode);
		return msg;
	}

	@RequestMapping(value = "placementMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String placementMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String EmpId, @RequestParam(required = false) String Placement_id,
			@RequestParam(required = false) String EmpName, @RequestParam(required = false) String mobile_no,
			@RequestParam(required = false) String email, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

		} else if (formmode.equals("list1")) {
			
			md.addAttribute("formmode", formmode);
			md.addAttribute("Placement_id", Placement_id);
			md.addAttribute("EmpName", EmpName);
			md.addAttribute("mobile_no", mobile_no);
			md.addAttribute("email", email);
			md.addAttribute("placementList", placementServices.getPlacementMasterlist(Placement_id, EmpName));

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getPlacementUser(EmpId));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getPlacementUser(EmpId));
		} else {
			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getPlacementUser(""));
		}

		return "BTMPlacementMaster";
	}

	@RequestMapping(value = "placementMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String placementMasterAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute PlacementMaster placementMaster, Model md, HttpServletRequest rq) {

		String msg = placementServices.addPlacementUser(placementMaster, formmode);
		return msg;
	}

	@RequestMapping(value = "timesheetManagement", method = { RequestMethod.GET, RequestMethod.POST })
	public String timesheetManagement(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String EmpId, @RequestParam(required = false) String Placement_id,
			@RequestParam(required = false) String EmpName, @RequestParam(required = false) String mobile_no,
			@RequestParam(required = false) String email, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

		} else if (formmode.equals("list1")) {
			
			md.addAttribute("formmode", formmode);
			md.addAttribute("Placement_id", Placement_id);
			md.addAttribute("EmpName", EmpName);
			md.addAttribute("mobile_no", mobile_no);
			md.addAttribute("email", email);
			md.addAttribute("timeSheetList", placementServices.getTimesheetManagementList(Placement_id, EmpName));

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getTimesheetUser(EmpId));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getTimesheetUser(EmpId));
			
		} else {
			
			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getTimesheetUser(""));
		}

		return "BTMTimesheetManagement";
	}

	@RequestMapping(value = "timesheetManagementAdd", method = RequestMethod.POST)
	@ResponseBody
	public String timesheetManagementAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute TimesheetManagement timesheetManagement, Model md, HttpServletRequest rq) {

		String msg = placementServices.addTimesheetUser(timesheetManagement, formmode);
		return msg;
	}

	@RequestMapping(value = "professionalCharge", method = { RequestMethod.GET, RequestMethod.POST })
	public String professionalCharge(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal SerialNo, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {
			
			md.addAttribute("formmode", "list");
			md.addAttribute("professionalChargeList", placementServices.getUsersList1());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("professionalCharge", placementServices.getUser1(SerialNo));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("professionalCharge", placementServices.getUser1(SerialNo));
			
		} else {
			
			md.addAttribute("formmode", formmode);
		}

		return "BTMProfessionalCharge";
	}

	@RequestMapping(value = "professionalChargeAdd", method = RequestMethod.POST)

	@ResponseBody
	public String professionalChargeAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute ProfessionalCharge professionalCharge, Model md, HttpServletRequest rq) {

		String msg = placementServices.addUser1(professionalCharge, formmode);
		return msg;
	}
	
	

	// ========================== Jasper download ========================/

	@RequestMapping(value = "invoiceReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource invoiceReportDownload(HttpServletResponse response,

			@RequestParam(value = "inv_no", required = false) String inv_no,

			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + inv_no + ", FileType :" + filetype + "");

			File repfile = placementServices.getFile(inv_no, filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}
	@RequestMapping(value = "AttendanceReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource AttendanceReportDownload(HttpServletResponse response,

			@RequestParam(value = "emp_id", required = false) String emp_id,
			@RequestParam(value = "cal_month", required = false) String cal_month,
			@RequestParam(value = "cal_year", required = false) String cal_year,

			@RequestParam(value = "report_type", required = false) String report_type) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + emp_id + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileAttendance(emp_id,cal_month,cal_year,report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}
	@RequestMapping(value = "AttendanceRegisterDailyReport", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource AttendanceRegisterDailyReport(HttpServletResponse response,

			@RequestParam(value = "login_time", required = false) Date login_time,
			@RequestParam(value = "report_type", required = false) String report_type) throws IOException, SQLException {

		response.setContentType("application/octet-stream");
		SimpleDateFormat formatday = new SimpleDateFormat("dd");
		SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
		SimpleDateFormat formatyear = new SimpleDateFormat("yyyy");
		String cal_month = formatMonth.format(login_time);
		String cal_year =formatyear.format(login_time);
		String cal_date=formatday.format(login_time);
		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + cal_month+"_"+cal_year+"_"+cal_date + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileDailyAttendance(cal_year,cal_month,cal_date,report_type);

		    response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			
		    resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}
	@RequestMapping(value = "AttendanceRegisterMonthReport", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource AttendanceRegisterMonthReport(HttpServletResponse response,
			@RequestParam(value = "cal_month", required = false) String cal_month,
			@RequestParam(value = "cal_year", required = false) String cal_year,

			@RequestParam(value = "report_type", required = false) String report_type) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + cal_month + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileMonthyAttendance(cal_month,cal_year,report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}
	@RequestMapping(value = "leaveRegisterReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource leaveRegisterReportDownload(HttpServletResponse response,
			@RequestParam(value = "employee_id", required = false) String employee_id,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "leave_category", required = false) String leave_category,

			@RequestParam(value = "report_type", required = false) String report_type) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + employee_id + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileLeaveRegister(employee_id, year, leave_category , report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}
	@RequestMapping(value = "projectMasterReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource projectMasterReportDownload(HttpServletResponse response,
			@RequestParam(value = "proj_id", required = false) String proj_id,

			@RequestParam(value = "report_type", required = false) String report_type) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + proj_id + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileProject(proj_id,report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}
	@RequestMapping(value = "holidayListReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource holidayListReportDownload(HttpServletResponse response,
			@RequestParam(value = "cal_year", required = true) String cal_year,
			@RequestParam(value = "detailsRequired", required = false) String detailsRequired,

			@RequestParam(value = "report_type", required = false) String report_type) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + cal_year + ", FileType :" + report_type + "");
			if(detailsRequired.equals("No")) {
			File repfile = reportServices.getFileHolidayList(cal_year,report_type);
			

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
			}else if(detailsRequired.equals("Yes")){
				File repfile = reportServices.getFileHolidayDetailsList(cal_year,report_type);
				
				response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
				resource = new InputStreamResource(new FileInputStream(repfile));
			}

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}
	@RequestMapping(value = "timesheetReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource timesheetReportDownload(HttpServletResponse response,
			@RequestParam(value = "emp_id", required = true) String emp_id,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "month", required = false) String month,

			@RequestParam(value = "report_type", required = false) String report_type) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + emp_id + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileTimeSheet(emp_id, year, month , report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}
	
	@RequestMapping(value = "employeeRegisterReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource employeeRegisterReportDownload(HttpServletResponse response,
			@RequestParam(value = "monthend", required = false) String monthend,
			@RequestParam(value = "detials", required = false) String detials,
			@RequestParam(value = "reportto", required = true) String reportto,

			@RequestParam(value = "report_type", required = false) String report_type) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + monthend + ", FileType :" + report_type + ",ReporttO :" + reportto);

			File repfile = reportServices.getemployeeRegister(monthend, detials, reportto , report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}
	
	@RequestMapping(value = "workAssignReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource workAssignReportDownload(HttpServletResponse response,
			@RequestParam(value = "emp_id", required = true) String emp_id,
			@RequestParam(value = "report_type", required = false) String report_type) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + emp_id + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileWorkAssign(emp_id, report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}
	@RequestMapping(value = "profileMasterReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource profileMasterReportDownload(HttpServletResponse response,
			@RequestParam(value = "emp_id", required = true) String emp_id,
			@RequestParam(value = "profileType", required = false) String profileType,
			@RequestParam(value = "reportType", required = false) String report_type) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + emp_id + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileProfileMaster(emp_id,profileType, report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}
	

	
	// ========================================== Maintenance Module ===========================================
	
	@RequestMapping(value = "MtAssociateProfile", method = { RequestMethod.GET, RequestMethod.POST })
	
	public String MtAssociateProfile(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		 if (formmode==null ||formmode.equals("list") ) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminAssociateProfileList", btmAdminAssociateProfileRep.getAssociatelist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminAssociateProfile", adminOperServices.getAssociteUser(resId));

		}
		return "BTMMAssociateProfile";
	}

	@RequestMapping(value = "MtAssociateProfileAdd", method = RequestMethod.POST)
	@ResponseBody
	public String MtAssociateProfileAdd(@RequestParam("formmode") String formmode,
			@RequestParam("userid") String userid, @ModelAttribute BTMAdminAssociateProfile btmAdminAssociateProfile,
			Model md, HttpServletRequest rq) {

		String msg = adminOperServices.modifyAssociate(btmAdminAssociateProfile, formmode, userid);
		return msg;
	}
	
	@RequestMapping(value = "MtProjectMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtProjectMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) String resName, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {
			
			md.addAttribute("formmode", "list");
			md.addAttribute("projectMasterList", adminOperServices.getProjectMasterlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("MtprojectList", btmProjectMasterRep.getProjectShow(resId, resName));
			md.addAttribute("projectDetails", btmProjectDetailsRep.getProjectDetails(resId));
			md.addAttribute("projectTeamDetails", btmProjectTeamDetailsRep.getProjectTeamDetails(resId));

		}

		return "BTMMtProjectMaster";
	}
	
	@RequestMapping(value = "MtLeaveMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtLeaveMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal resId, @RequestParam(required = false) String RefId,
			 Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("AdminLeaveList", leaveMasterRep.getLeavelist1());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("MtLeaveMaster", onDutyServices.getLeaveDetail(resId));
			md.addAttribute("LeaveListCounter", leaveMasterCounterRep.getLeaveCounterlist(RefId));
			md.addAttribute("approvalstatus", onDutyServices.getstatus(RefId,userId));
			md.addAttribute("pendingstatus", onDutyServices.getstatuspending(RefId,userId));

		}

		return "BTMMLeaveMaster";
	}

	@RequestMapping(value = "MtleaveMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String MtleaveMasterAdd(@RequestParam("formmode") String formmode, @RequestParam("userid") String userid,
			@ModelAttribute LeaveMaster leaveMaster, Model md, HttpServletRequest rq) {
		String Id = (String) rq.getSession().getAttribute("USERID");
		String msg = adminOperServices.modifyLeave(leaveMaster, formmode,userid,Id);

		return msg;
	}

	@RequestMapping(value = "MtOnDuty", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtOnDuty(@RequestParam(required = false) String formmode,

			@RequestParam(required = false) String resId, @RequestParam(required = false) String RefId, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

			md.addAttribute("AdminODList", onDutyRep.getODlist1());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");

			md.addAttribute("MtOnDuty", onDutyServices.getODDetail(resId));

			md.addAttribute("onDutyListCount", bTMAdminOndutyCountRep.getOndutyCounterlist(RefId));

		}

		return "BTMMtOnDuty";
	}

	@RequestMapping(value = "MtOdMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String MtOdMasterAdd(@RequestParam("formmode") String formmode, @RequestParam("userid") String userid,
			@ModelAttribute OnDuty onDuty, Model md, HttpServletRequest rq) throws ParseException {
		String Id = (String) rq.getSession().getAttribute("USERID");
		String msg = adminOperServices.modifyOd(onDuty, formmode, userid,Id);

		return msg;
	}
	
	@RequestMapping(value = "MtTimesheetMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtTimesheetMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		
       String empid = (String) req.getSession().getAttribute("USERID");
		
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());
		
		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		}
		
		return "BTMAdminTimesheetMaster";
	}
	
	@RequestMapping(value = "MtTravelMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtTravelMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("AdminTravelList", btmTravelMasterRep.getTravellist1());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("MtTravelMaster", adminOperServices.getTravelMaster(resId));

		}

		return "BTMMtTravelMaster";
	}

	@RequestMapping(value = "MtTravelMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String MtTravelMasterAdd(@RequestParam("formmode") String formmode, @RequestParam("userid") String userid,
			@ModelAttribute BTMTravelMaster travelMaster, Model md, HttpServletRequest rq) throws ParseException {

		String msg = adminOperServices.modifyTravelMaster(travelMaster, formmode, userid);
		return msg;
	}
	
	@RequestMapping(value = "MtExpenseReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtExpenseReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) String userid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("report", btmAdminExpenseReportRep.getReportList1());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("MtExpenseReport", adminOperServices.getReportManager(resId));

		}
		return "BTMMtExpenseReport";

	}

	@RequestMapping(value = "MtExpenseReportAdd", method = RequestMethod.POST)
	@ResponseBody
	public String MtExpenseReportAdd(@RequestParam("formmode") String formmode, @RequestParam("userid") String userid,
			@ModelAttribute ExpenseMaster expenses, Model md, HttpServletRequest rq) throws ParseException {

		String msg = adminOperServices.modifyExpense(expenses, formmode, userid);
		return msg;
	}
	
	@RequestMapping(value = "MtEventMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtEventMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("eventMasterList", btmEventMasterRep.getScreenlist());

		}

		return "BTMMtEventMaster";
	}

	@RequestMapping(value = "MtWorkAssignment", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtWorkAssignment(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("profileManagers", btmAdminAssociateProfileRep.getEmployeedetail2());
			
		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		}else if (formmode.equals("approveList")) {

			md.addAttribute("formmode", "approveList");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		}else if (formmode.equals("view2")) {

			md.addAttribute("formmode", "view2");
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		}

		return "BTMMtWorkAssignment";
	}

	@RequestMapping(value = "MtWorkAssignmentAdd", method = RequestMethod.POST)
	@ResponseBody
	public String MtWorkAssignmentAdd(@RequestParam("formmode") String formmode, @RequestParam("userid") String userid, @RequestParam("emp_name") String emp_name,
			@ModelAttribute BTMWorkAssignment btmWorkAssignment, Model md, HttpServletRequest rq)
			throws ParseException {

		String msg = maintenanceOperServices.addWorkAssign(btmWorkAssignment, formmode, userid , emp_name);
		return msg;
	}
	
	
	@RequestMapping(value = "Pomaintenance", method = { RequestMethod.GET, RequestMethod.POST })
	public String Pomaintenance(@RequestParam(required = false) String formmode,String po_delivery_date,String po_month,
			@RequestParam(required = false) String po_no,@RequestParam(required = false) String po_id, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		
		
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		
		
		System.out.println(formmode);
		if (formmode == null || formmode.equals("list")) {

			
			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getMaintenance()); 
			 
		}
		else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		}else if (formmode.equals("sview")) {

			md.addAttribute("formmode", formmode);

		}else if (formmode.equals("fview")) {
			md.addAttribute("poidlist", placementmaintenancerep.getPoId(po_id));
			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("success")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("slist", placementmaintenancerep.getSdetail());

		} else if (formmode.equals("failure")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("flist", placementmaintenancerep.getFdetail());
		} 
		
		else if (formmode.equals("upload")) {

			md.addAttribute("formmode", formmode);

		}
		
		else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			System.out.println(po_id);
			PlacementMaintenance oo=placementmaintenancerep.getMaintenancelist(po_id);
			System.out.println(":::::::::"+oo.getPo_no());
			
			md.addAttribute("polist", placementmaintenancerep.getPoMaintenance(po_id)); 
			md.addAttribute("pmlist", oo); 
			md.addAttribute("ilist", placementmaintenancerep.getPolist(oo.getPo_no())); 

		}  
		
		else if(formmode.equals("view")){
			md.addAttribute("formmode", formmode);
			md.addAttribute("polist", placementmaintenancerep.getPoMaintenance(po_no)); 
		}else if(formmode.equals("Modifyg")){
			md.addAttribute("formmode", formmode);
			md.addAttribute("po",placementmaintenancerep.getPoM(po_id));
			//md.addAttribute("polist", placementmaintenancerep.getPoMaintenance(po_id));
			//md.addAttribute("po",placementmaintenancerep.getPoM(po_no));
		}else if(formmode.equals("ModifyI")){
			md.addAttribute("formmode", formmode);
			md.addAttribute("po",placementmaintenancerep.getPoM(po_id));
			//md.addAttribute("polist", placementmaintenancerep.getPoMaintenance(po_id));
			//md.addAttribute("po",placementmaintenancerep.getPoM(po_no));
		}else if(formmode.equals("modify")){
			md.addAttribute("formmode", formmode);
			md.addAttribute("po",placementmaintenancerep.getPoM(po_id));
			md.addAttribute("polist", placementmaintenancerep.getPoMaintenance(po_no)); 
		}else if(formmode.equals("ModifyR")){
			System.out.println(formmode);
			md.addAttribute("formmode", formmode);
			md.addAttribute("po",placementmaintenancerep.getPoM(po_id));
			//md.addAttribute("polist", placementmaintenancerep.getPoMaintenance(po_no)); 
		}

		return "Pomaintenance";
	}
	
	
	
	
	@RequestMapping(value = "PurchaseOrders", method = { RequestMethod.GET, RequestMethod.POST })
	public String PurchaseOrders(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			
			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails()); 
			
			 
		}
		else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
		

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "PO";
	}
	@RequestMapping(value = "Remittances", method = { RequestMethod.GET, RequestMethod.POST })
	public String Remittence(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			
			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails()); 
			 
		}
		else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
		

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "Remittance";
	}
	
	@RequestMapping(value = "SPRates", method = { RequestMethod.GET, RequestMethod.POST })
	public String SPRates(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			
			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails()); 
			 
		}
		else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
		

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "SPRates";
	}
	@RequestMapping(value = "GRNDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public String GRNDetails(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			
			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails()); 
			 
		}
		else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
		

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "Grn";
	}
	
	@RequestMapping(value = "InvoiceDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public String InvoiceDetails(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			
			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails()); 
		}
		else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
		

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "InvDetails";
	}
	
	@RequestMapping(value = "Acknowledgement", method = { RequestMethod.GET, RequestMethod.POST })
	public String Acknowledgement(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			
			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails()); 
		}
		else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
		

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "InvStatus";
	}
	
	@RequestMapping(value = "SPInvoices", method = { RequestMethod.GET, RequestMethod.POST })
	public String SPInvoices(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			
			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails()); 
		}
		else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
		

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "Ivc";
	}
	
	
	@RequestMapping(value = "SPPayments", method = { RequestMethod.GET, RequestMethod.POST })
	public String SPPayments(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			
			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails()); 
		}
		else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
		

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "SPpay";
	}
	

	
	@RequestMapping(value = "PurchaseOrderDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public String PurchaseOrderDetails(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			
			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails());  
		}
		else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
		

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "PurchaseOrderDetails";
	}



	@RequestMapping(value = "CreatePMAdd", method = RequestMethod.POST)
	@ResponseBody
	public String CreatePMAdd(@RequestParam(required = false) String formmode, @ModelAttribute PlacementMaintenance placementmaintenance, Model md,
			HttpServletRequest rq) {
		
		System.out.println(formmode+placementmaintenance.getPo_no()+placementmaintenance.getPo_month()+placementmaintenance.getPo_id());
		
		String msg = placementServices.CreatePMAdd(placementmaintenance, formmode);
		System.out.println(msg);
		return msg;
	}
	
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam(required = false) String formmode, @ModelAttribute PlacementMaintenance placementmaintenance, Model md,
			HttpServletRequest rq) {
		String msg = placementServices.upload(placementmaintenance, formmode);
		return msg;
	}
	
	
	
	
	@RequestMapping(value = "logoutsub",  method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String logoutsub(
			  Model md, HttpServletRequest rq)
			throws ParseException {
        
         String userid1 = (String) rq.getSession().getAttribute("USERID");
		String msg = attendanceRegisterService.logoutsubmit(userid1);
		return "BTMStart.html";
	}
	
	
	@RequestMapping(value = "TimesheetVerify", method = RequestMethod.POST)
	@ResponseBody
	public String TimesheetVerify(@RequestParam(required = false) String emp_id,@RequestParam(required = false) BigDecimal year,@RequestParam(required = false) String month,@ModelAttribute BTMEmpTimeSheet time,  Model md,
			HttpServletRequest rq) throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException,
	  IOException{
		String userid = (String)rq.getSession().getAttribute("USERID");
		
		
		String msg = timeSheetPdf.timeSheetVerify(time, emp_id,year,month,userid);
		return msg;
	}
	
	//INQURIES ATTENDANCE REGISTER
	
	
	@RequestMapping(value = "attendanceRegisterInquries", method = { RequestMethod.GET, RequestMethod.POST })
	public String attendanceRegisterInquries(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal serialNo, Model md, HttpServletRequest req)
			throws ParseException {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatdate = new SimpleDateFormat("dd/MM/yyyy");
		String str = formatdate.format(cal.getTime());
		Date dat1 = null;
		try {
			dat1 = formatdate.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// resourceMasterRepo.gettotaluser();
		//int totalemployees = resourceMasterRepo.gettotalnum();
		//int present = attendanceRegisterRep.getALLpresent(dat1);
		//int absent = totalemployees-present;
	//	int onduty = 0;
		md.addAttribute("attendanceList", attendanceRegisterRep.getALL(dat1));
		//md.addAttribute("numofEmployees",totalemployees);
	//	md.addAttribute("numofPresent",present);
	//	md.addAttribute("numofAbsent",absent);
	//	md.addAttribute("numofOnduty",onduty);
		md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
		SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
		String month = formatMonth.format(cal.getTime());
		
		md.addAttribute("CurrentMonth",month);
		SimpleDateFormat formatyear = new SimpleDateFormat("yyyy");
		String year = formatyear.format(cal.getTime());
		md.addAttribute("Currentyear",year);
		
		
		
		return "BTMAttendanceRegisterInquires";
	}
	
@RequestMapping(value = "BTMAdminAccessandRole", method = { RequestMethod.GET, RequestMethod.POST })
	
	public String BTMAdminAccessandRole(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String role_id, Model md, HttpServletRequest req)
					throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException   {
		
	String userId = (String) req.getSession().getAttribute("USERID");
	md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
	md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("accessRolesList",accessRolesRep.getRolelist());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("BTMAccessRole",new AccessRoles());

		} else {

			md.addAttribute("formmode", formmode);

		}
		
		return "AccessRole";
	}
	
	@RequestMapping(value = "addAccessRole", method = RequestMethod.POST) 
	@ResponseBody 

	public String addAccessRole (@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq,
			@RequestParam(value = "adminValue", required = false) String adminValue,
			@RequestParam(value = "auditValue", required = false) String auditValue,
			@RequestParam(value = "IPSOperationsValue", required = false) String IPSOperationsValue,
			@RequestParam(value = "monitoringValue", required = false) String monitoringValue,
			@RequestParam(value = "my_tregistrationValue", required = false) String my_tregistrationValue,
			@RequestParam(value = "finalString", required = false) String finalString,
			@ModelAttribute AccessRoles ar) { 
	AccessRoles up = new AccessRoles();
	up.setRole_id(ar.getRole_id());
	up.setRole_desc(ar.getRole_desc());
	up.setPermissions(ar.getPermissions());
	up.setRemarks(ar.getRemarks());
	up.setWork_class(ar.getWork_class());
	up.setMenulist(finalString);
	up.setAdmin(adminValue);
	up.setAudit_operations(auditValue);
	up.setIps_operations(IPSOperationsValue);
	up.setMonitoring(monitoringValue);
	up.setMyt_registration(my_tregistrationValue);;
	accessRolesRep.save(up);
	String msg="Role Added Successfully";
	return msg; 

	} 
	
	@RequestMapping(value = "fsubmit", method = RequestMethod.POST) 
	@ResponseBody
	public String fsubmit (@RequestParam(required = false) String po_id, Model md, HttpServletRequest rq,
	@ModelAttribute PlacementMaintenance placementMaintenance) { 
		System.out.println("Hi"+placementMaintenance.getPo_id());
		System.out.println(placementMaintenance.getPo_no());
		
		PlacementMaintenance up =placementMaintenanceRep.getPoId(po_id);
		up.setPo_no(placementMaintenance.getPo_no());
		up.setPo_delivery_date(placementMaintenance.getPo_delivery_date());
		up.setEmp_id(placementMaintenance.getEmp_id());
		
		up.setMessage("SUCCESS");
		up.setFlag('Y');
		//System.out.println(up.getPo_no());
		placementMaintenanceRep.save(up);
	return "success"; 

	}
	
	

	@RequestMapping(value = "PO_Status", method = { RequestMethod.GET, RequestMethod.POST })
	public String PO_Status(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String salary_code, Model md, HttpServletRequest req)
					throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException   {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		/* md.addAttribute("menu", "paystructure"); */
		System.out.println("hi");
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("postatus_notnull", placementMaintenanceRep.getponulldetails());
		}
			
			return "PO_Status";
			
		}	
	
	
	@RequestMapping(value = "GRNStatus", method = { RequestMethod.GET, RequestMethod.POST })
	public String GRNStatus(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String name, Model md, HttpServletRequest req,@ModelAttribute PlacementMaintenance placementMaintenance)
					throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException   {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		/* md.addAttribute("menu", "paystructure"); */

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("grnstsnotnull",placementMaintenanceRep.grnstsnotnull());
			

		}
		
		return "GRNStatus";
	}
	
	@RequestMapping(value = "remainder", method = { RequestMethod.GET, RequestMethod.POST })
	public String remainder(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {

		String userid1 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("postatus_notnull", placementMaintenanceRep.getponulldetails());
		}

		return "Remainder";
	}
	
	@RequestMapping(value = "spinvoices", method = { RequestMethod.GET, RequestMethod.POST })
	public String spinvoices(@RequestParam(required = false) String formmode,@RequestParam(required = false) String sp,@RequestParam(required = false) String inv_due_date,@RequestParam(required = false) String inv_date, Model md, HttpServletRequest req) throws ParseException {
		String userid1 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		
		
		if (formmode == null || formmode.equals("list")) {
			
			md.addAttribute("formmode", "list");
			md.addAttribute("postatus_notnull", placementMaintenanceRep.getponulldetails());
		}
		else if (formmode.equals("table")) {
			System.out.println(inv_due_date);
			System.out.println(inv_date);
			
			List<String> myList = new ArrayList<>();

	        // Add elements to the list
	       
			md.addAttribute("formmode", "table");
			md.addAttribute("fuc", sp);
			md.addAttribute("fuc1", inv_due_date);
			md.addAttribute("fuc2", inv_date);
			md.addAttribute("splist", placementMaintenanceRep.getsplist(sp,inv_due_date,inv_date));
		}
		

		return "SPINVOICES";
	}
	
	@RequestMapping(value = "spf", method = { RequestMethod.GET, RequestMethod.POST })
	public String spf(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resName,@RequestParam(required = false) String a, @RequestParam(required = false) String Month, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		 if(formmode == null ||formmode=="table") {
				md.addAttribute("formmode", "table");
			}
		 else if (formmode.equals("edit")) {

				md.addAttribute("formmode", "edit");
				md.addAttribute("editing", Spf_repo.findit(a));
		 }
		 else if ( formmode.equals("list")) {
			if(Month!=null || Month=="") {
			List<spf_entity> spfValues = Spf_repo.getall(Month);

			
			md.addAttribute("ghj",spfValues);
			md.addAttribute("formmode", "list");
			md.addAttribute("month",Month);
			}else {
				 YearMonth currentYearMonth = YearMonth.now();

			        // Format the current month and year as a string
			        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
			        String formattedMonthYear = currentYearMonth.format(formatter);
			        //String[] parts = formattedMonthYear.split(" ");
			        // Print the current month and year
			        System.out.println("Current Month and Year: " + formattedMonthYear);
			        
				List<spf_entity> spfValues = Spf_repo.getall(formattedMonthYear);

				
				
				md.addAttribute("ghj",spfValues);
				md.addAttribute("formmode", "list");
				
			}
			
			
			
		}

 

		return "spf";
	}
	
	@RequestMapping(value = "SPFDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource SPFDownload(HttpServletResponse response

		) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {
			
			String filetype="Excel";
			//logger.info("Getting download File :" +  + ", FileType :Excel" +  + "");

			File repfile = placementServices.getFile1(filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}
	
	
	@RequestMapping(value = "ESIDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource ESIDownload(HttpServletResponse response

		) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {
			
			String filetype="Excel";
			//logger.info("Getting download File :" +  + ", FileType :Excel" +  + "");

			File repfile = placementServices.getFileesi(filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}
	
	
	
	@RequestMapping(value = "SPFDownload1", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource SPFDownload1(HttpServletResponse response,@RequestParam(required = false) String MONTH

		) throws IOException, SQLException {

		response.setContentType("application/octet-stream");
System.out.println("==============="+MONTH);
		InputStreamResource resource = null;
		try {
			
			String filetype="Excel";
			String Mon=MONTH;
			//logger.info("Getting download File :" +  + ", FileType :Excel" +  + "");

			File repfile = placementServices.getFile2(filetype,Mon);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}
	
	@RequestMapping(value = "SalaryDownload1", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource SalaryDownload1(HttpServletResponse response,@RequestParam(required = false) String MONTH

		) throws IOException, SQLException {

		response.setContentType("application/octet-stream");
System.out.println("==============="+MONTH);
		InputStreamResource resource = null;
		try {
			
			String filetype="Excel";
			String Mon=MONTH;
			//logger.info("Getting download File :" +  + ", FileType :Excel" +  + "");

			File repfile = placementServices.getSalaryFile(filetype,Mon);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}
	
	
	@RequestMapping(value = "ESIDownload1", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource ESIDownload1(HttpServletResponse response,@RequestParam(required = false) String MONTH

		) throws IOException, SQLException {

		response.setContentType("application/octet-stream");
System.out.println("==============="+MONTH);
		InputStreamResource resource = null;
		try {
			
			String filetype="Excel";
			String Mon=MONTH;
			//logger.info("Getting download File :" +  + ", FileType :Excel" +  + "");

			File repfile = placementServices.getFileESI2(filetype,Mon);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}
	
	@RequestMapping(value = "viewtospf", method = RequestMethod.POST)
	@ResponseBody
	public String viewtospf(
	        @RequestParam(required = false) String formmode,
	        Model md,HttpServletRequest rq,@RequestParam(required = false) String b) {
		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
	    System.out.println(b);
	    int uniqueIdCounter = Integer.parseInt(b);
	    try {
	        // AccessRoles up = new AccessRoles();
	        // List<BSPF_ENTITY> up1 = SpfRepo.getData(b);
	        List<BSPF_ENTITY> up2 = SpfRepo.getData(b);
	        List<spf_entity> up3 = new ArrayList<>();
	        
	        for (BSPF_ENTITY bspfEntity : up2) {
	        	
	        	if (bspfEntity.getSpf() == null || bspfEntity.getSpf().compareTo(BigDecimal.ZERO) == 0) {
	        		
	        		

		            spf_entity spfEntity = new spf_entity();
		            spfEntity.setEmp_no(bspfEntity.getEmp_no());
		            spfEntity.setEmp_name(bspfEntity.getEmp_name());
		            spfEntity.setEmp_desig(bspfEntity.getEmp_desig());
		            spfEntity.setDate_of_birth(bspfEntity.getDate_of_birth());
		            spfEntity.setDate_of_join(bspfEntity.getDate_of_join());
		            spfEntity.setSpf_acct_no(bspfEntity.getSpf_acct_no());
		            spfEntity.setUrn_no(bspfEntity.getUrn_no());
		            spfEntity.setNo_of_days(bspfEntity.getNo_of_days());
		            spfEntity.setDays_paid(bspfEntity.getDays_paid());
		            spfEntity.setBank_name(bspfEntity.getBank_name());
		            spfEntity.setBank_acct_no(bspfEntity.getBank_acct_no());
		            spfEntity.setSalary_month(bspfEntity.getSalary_month());
		            spfEntity.setEmp_cont_12("0.00");
		            spfEntity.setEmp_cont_367("0.00");
		            spfEntity.setEmp_cont_833("0.00");
		            spfEntity.setTot_emp_cont("0.00");
		            
		           
		            spfEntity.setHra(bspfEntity.getHra());
		            spfEntity.setSpl_allow(bspfEntity.getSpl_allow());
		            spfEntity.setMedi_reimb(bspfEntity.getMedi_reimb());
		            spfEntity.setConv_allow(bspfEntity.getConv_allow());
		            spfEntity.setLunch_allow(bspfEntity.getLunch_allow());
		            spfEntity.setEdu_allow(bspfEntity.getEdu_allow());
		            spfEntity.setBuss_attire(bspfEntity.getBuss_attire());
		            spfEntity.setCar_maint(bspfEntity.getCar_maint());
		            spfEntity.setLeave_travel_allow(bspfEntity.getLeave_travel_allow());
		            spfEntity.setOutstn_allow(bspfEntity.getOutstn_allow());
		            spfEntity.setAnnual_loyal_bonus(bspfEntity.getAnnual_loyal_bonus());
		            spfEntity.setOtr_allow(bspfEntity.getOtr_allow());
		            
		            String grossSalary = bspfEntity.getGross_salary().toString();
		            String formattedValue = formatLakh(Double.valueOf(grossSalary));
		            spfEntity.setGross_salary(formattedValue);
		            
		            String BasicPay=bspfEntity.getBasic_pay().toString();
		            String formattedValue1 = formatLakh(Double.valueOf(BasicPay));
		            spfEntity.setBasic_pay(formattedValue1);
		            //BigDecimal bigDecimalValue = new BigDecimal(a);
		           // spfEntity.setGross_salary(bigDecimalValue);
		            spfEntity.setSpf(bspfEntity.getSpf());
		            spfEntity.setTds(bspfEntity.getTds());
		            spfEntity.setProf_tax(bspfEntity.getProf_tax());
		            spfEntity.setEsi(bspfEntity.getEsi());
		            spfEntity.setRecovery(bspfEntity.getRecovery());
		            spfEntity.setOtr_ded(bspfEntity.getOtr_ded());
		            spfEntity.setTotal_deductions(bspfEntity.getTotal_deductions());
		            spfEntity.setNet_salary(bspfEntity.getNet_salary());
		            spfEntity.setDate_of_pay(bspfEntity.getDate_of_pay());
		            spfEntity.setCum_tds_fy(bspfEntity.getCum_tds_fy());
		            spfEntity.setCtc_amt(bspfEntity.getCtc_amt());
		            spfEntity.setMobile_no(bspfEntity.getMobile_no());
		            spfEntity.setEmail_id(bspfEntity.getEmail_id());
		            spfEntity.setIfsc_code(bspfEntity.getIfsc_code());
		            spfEntity.setRemarks(bspfEntity.getRemarks());
		            spfEntity.setAdhar_no(bspfEntity.getAdhar_no());

		            spfEntity.setUnique_id(bspfEntity.getSalary_month()+bspfEntity.getEmp_no());
		            up3.add(spfEntity);
	            }
	        	
	        	else {
	        		
	        		
	        		
	        		 spf_entity spfEntity = new spf_entity();
	 	            spfEntity.setEmp_no(bspfEntity.getEmp_no());
	 	            spfEntity.setEmp_name(bspfEntity.getEmp_name());
	 	            spfEntity.setEmp_desig(bspfEntity.getEmp_desig());
	 	            spfEntity.setDate_of_birth(bspfEntity.getDate_of_birth());
	 	            spfEntity.setDate_of_join(bspfEntity.getDate_of_join());
	 	            spfEntity.setSpf_acct_no(bspfEntity.getSpf_acct_no());
	 	            spfEntity.setUrn_no(bspfEntity.getUrn_no());
	 	            spfEntity.setNo_of_days(bspfEntity.getNo_of_days());
	 	            spfEntity.setDays_paid(bspfEntity.getDays_paid());
	 	            spfEntity.setBank_name(bspfEntity.getBank_name());
	 	            spfEntity.setBank_acct_no(bspfEntity.getBank_acct_no());
	 	            spfEntity.setSalary_month(bspfEntity.getSalary_month());
	 	            
	 	            
	 	            BigDecimal expectedValue = new BigDecimal("300000");
	 	            if (bspfEntity.getCtc_amt().compareTo(expectedValue) >= 0) {
	 	                BigDecimal basicPay = new BigDecimal("15000");
	 	                spfEntity.setBasic_pay("15,000.00");
	 	                
	 	                //BigDecimal basicPay = bspfEntity.getBasic_pay();
	 	                
	 	                
	 		            int intValue = basicPay.intValue();
	 		            int emailId = Math.round(15000 * 8.33f / 100); 
	 		            String stringValue = Integer.toString(emailId);
	 		            spfEntity.setEmp_cont_833(formatLakh(Double.valueOf(stringValue)));
	 		            
			          
			            
	 				    int ifsc = Math.round(15000 * 3.67f / 100); 
	 		            String stringValue1 = Integer.toString(ifsc);
	 		            spfEntity.setEmp_cont_367(formatLakh(Double.valueOf(stringValue1)));
	 				    
	 				    int remarks = Math.round(15000 * 12.00f / 100); 
	 		            String stringValue2 = Integer.toString(remarks);
	 		            spfEntity.setTot_emp_cont(formatLakh(Double.valueOf(stringValue2)));
	 		           
	 		            
	 	            } else if (bspfEntity.getCtc_amt().compareTo(expectedValue) < 0) {
	 	                BigDecimal basicPay = new BigDecimal("13985");
	 	                spfEntity.setBasic_pay("13,985.00");
	 	                
	 	                int intValue = basicPay.intValue();
	 	                
	 	                int remarks = 1500; 
	 		            String stringValue2 = Integer.toString(remarks);
	 		            spfEntity.setTot_emp_cont(formatLakh(Double.valueOf(stringValue2)));
	 		            
	 		            int emailId = Math.round(remarks * 8.33f / 12); 
	 		            String stringValue = Integer.toString(emailId);
	 		            spfEntity.setEmp_cont_833(formatLakh(Double.valueOf(stringValue)));
	 				    
	 				    int ifsc = Math.round(remarks * 3.67f / 12); 
	 		            String stringValue1 = Integer.toString(ifsc);
	 		            spfEntity.setEmp_cont_367(formatLakh(Double.valueOf(stringValue1)));
	 				    
	 				   
	 		            
	 	            } else {
	 	                // Handle the case where bspfEntity.getCtc_amt() is equal to expectedValue
	 	                // You can add code here if needed.
	 	            }

	 	           String grossSalary = bspfEntity.getGross_salary().toString();
		            String formattedValue = formatLakh(Double.valueOf(grossSalary));
		            spfEntity.setGross_salary(formattedValue);
		            
		          
	 	            spfEntity.setHra(bspfEntity.getHra());
	 	            spfEntity.setSpl_allow(bspfEntity.getSpl_allow());
	 	            spfEntity.setMedi_reimb(bspfEntity.getMedi_reimb());
	 	            spfEntity.setConv_allow(bspfEntity.getConv_allow());
	 	            spfEntity.setLunch_allow(bspfEntity.getLunch_allow());
	 	            spfEntity.setEdu_allow(bspfEntity.getEdu_allow());
	 	            spfEntity.setBuss_attire(bspfEntity.getBuss_attire());
	 	            spfEntity.setCar_maint(bspfEntity.getCar_maint());
	 	            spfEntity.setLeave_travel_allow(bspfEntity.getLeave_travel_allow());
	 	            spfEntity.setOutstn_allow(bspfEntity.getOutstn_allow());
	 	            spfEntity.setAnnual_loyal_bonus(bspfEntity.getAnnual_loyal_bonus());
	 	            spfEntity.setOtr_allow(bspfEntity.getOtr_allow());
	 	           // spfEntity.setGross_salary(bspfEntity.getGross_salary());
	 	            spfEntity.setSpf(bspfEntity.getSpf());
	 	            spfEntity.setTds(bspfEntity.getTds());
	 	            spfEntity.setProf_tax(bspfEntity.getProf_tax());
	 	            spfEntity.setEsi(bspfEntity.getEsi());
	 	            spfEntity.setRecovery(bspfEntity.getRecovery());
	 	            spfEntity.setOtr_ded(bspfEntity.getOtr_ded());
	 	            spfEntity.setTotal_deductions(bspfEntity.getTotal_deductions());
	 	            spfEntity.setNet_salary(bspfEntity.getNet_salary());
	 	            spfEntity.setDate_of_pay(bspfEntity.getDate_of_pay());
	 	            spfEntity.setCum_tds_fy(bspfEntity.getCum_tds_fy());
	 	            spfEntity.setCtc_amt(bspfEntity.getCtc_amt());
	 	            spfEntity.setMobile_no(bspfEntity.getMobile_no());
	 	            spfEntity.setEmail_id(bspfEntity.getEmail_id());
	 	            spfEntity.setIfsc_code(bspfEntity.getIfsc_code());
	 	            spfEntity.setRemarks(bspfEntity.getRemarks());
	 	            spfEntity.setAdhar_no(bspfEntity.getAdhar_no());

	 	            spfEntity.setUnique_id(bspfEntity.getSalary_month()+bspfEntity.getEmp_no());
	 	            up3.add(spfEntity); }
	        	
	        	
	        }
	        System.out.println(up3);
	        	Spf_repo.saveAll(up3); 
	        String msg = "Data Saved Successfully"; // Changed the message
	        return msg;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error: " + e.getMessage();
	    }
	}
	
	
	@RequestMapping(value = "viewtospf1", method = RequestMethod.POST)
	@ResponseBody
	public String viewtospf1(
	        @RequestParam(required = false) String formmode,
	        Model md,HttpServletRequest rq,@RequestParam(required = false) String b) {
		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
	    System.out.println(b);
	    int uniqueIdCounter = Integer.parseInt(b);
	    try {
	        // AccessRoles up = new AccessRoles();
	        // List<BSPF_ENTITY> up1 = SpfRepo.getData(b);
	        List<BSPF_ENTITY> up2 = SpfRepo.getData(b);
	        List<spf_entity> up3 = new ArrayList<>();
	        
	        for (BSPF_ENTITY bspfEntity : up2) {
	        	
	        	if (bspfEntity.getSpf() == null || bspfEntity.getSpf().compareTo(BigDecimal.ZERO) == 0) {
	        		
	        		

		            spf_entity spfEntity = new spf_entity();
		            spfEntity.setEmp_no(bspfEntity.getEmp_no());
		            spfEntity.setEmp_name(bspfEntity.getEmp_name());
		            spfEntity.setEmp_desig(bspfEntity.getEmp_desig());
		            spfEntity.setDate_of_birth(bspfEntity.getDate_of_birth());
		            spfEntity.setDate_of_join(bspfEntity.getDate_of_join());
		            spfEntity.setSpf_acct_no(bspfEntity.getSpf_acct_no());
		            spfEntity.setUrn_no(bspfEntity.getUrn_no());
		            spfEntity.setNo_of_days(bspfEntity.getNo_of_days());
		            spfEntity.setDays_paid(bspfEntity.getDays_paid());
		            spfEntity.setBank_name(bspfEntity.getBank_name());
		            spfEntity.setBank_acct_no(bspfEntity.getBank_acct_no());
		            spfEntity.setSalary_month(bspfEntity.getSalary_month());
		            spfEntity.setEmp_cont_12("0");
		            spfEntity.setEmp_cont_367("0");
		            spfEntity.setEmp_cont_833("0");
		            spfEntity.setTot_emp_cont("0");
		            
		           
		            spfEntity.setHra(bspfEntity.getHra());
		            spfEntity.setSpl_allow(bspfEntity.getSpl_allow());
		            spfEntity.setMedi_reimb(bspfEntity.getMedi_reimb());
		            spfEntity.setConv_allow(bspfEntity.getConv_allow());
		            spfEntity.setLunch_allow(bspfEntity.getLunch_allow());
		            spfEntity.setEdu_allow(bspfEntity.getEdu_allow());
		            spfEntity.setBuss_attire(bspfEntity.getBuss_attire());
		            spfEntity.setCar_maint(bspfEntity.getCar_maint());
		            spfEntity.setLeave_travel_allow(bspfEntity.getLeave_travel_allow());
		            spfEntity.setOutstn_allow(bspfEntity.getOutstn_allow());
		            spfEntity.setAnnual_loyal_bonus(bspfEntity.getAnnual_loyal_bonus());
		            spfEntity.setOtr_allow(bspfEntity.getOtr_allow());
		            
		            String grossSalary = bspfEntity.getGross_salary().toString();
		            //String formattedValue = formatLakh(Double.valueOf(grossSalary));
		            spfEntity.setGross_salary(grossSalary);
		            
		            String BasicPay=bspfEntity.getBasic_pay().toString();
		            //String formattedValue1 = formatLakh(Double.valueOf(BasicPay));
		            spfEntity.setBasic_pay(BasicPay);
		            //BigDecimal bigDecimalValue = new BigDecimal(a);
		           // spfEntity.setGross_salary(bigDecimalValue);
		            spfEntity.setSpf(bspfEntity.getSpf());
		            spfEntity.setTds(bspfEntity.getTds());
		            spfEntity.setProf_tax(bspfEntity.getProf_tax());
		            spfEntity.setEsi(bspfEntity.getEsi());
		            spfEntity.setRecovery(bspfEntity.getRecovery());
		            spfEntity.setOtr_ded(bspfEntity.getOtr_ded());
		            spfEntity.setTotal_deductions(bspfEntity.getTotal_deductions());
		            spfEntity.setNet_salary(bspfEntity.getNet_salary());
		            spfEntity.setDate_of_pay(bspfEntity.getDate_of_pay());
		            spfEntity.setCum_tds_fy(bspfEntity.getCum_tds_fy());
		            spfEntity.setCtc_amt(bspfEntity.getCtc_amt());
		            spfEntity.setMobile_no(bspfEntity.getMobile_no());
		            spfEntity.setEmail_id(bspfEntity.getEmail_id());
		            spfEntity.setIfsc_code(bspfEntity.getIfsc_code());
		            spfEntity.setRemarks(bspfEntity.getRemarks());
		            spfEntity.setAdhar_no(bspfEntity.getAdhar_no());
		            if(bspfEntity.getBank_name().equals("ICICI")) {
		            	spfEntity.setTran_type("I");
		            }else {
		            	spfEntity.setTran_type("N");
		            }

		            spfEntity.setUnique_id(bspfEntity.getSalary_month()+bspfEntity.getEmp_no());
		            up3.add(spfEntity);
	            }
	        	else if (bspfEntity.getSpf().equals(new BigDecimal("2400"))) {
	        		
	        		

		            spf_entity spfEntity = new spf_entity();
		            spfEntity.setEmp_no(bspfEntity.getEmp_no());
		            spfEntity.setEmp_name(bspfEntity.getEmp_name());
		            spfEntity.setEmp_desig(bspfEntity.getEmp_desig());
		            spfEntity.setDate_of_birth(bspfEntity.getDate_of_birth());
		            spfEntity.setDate_of_join(bspfEntity.getDate_of_join());
		            spfEntity.setSpf_acct_no(bspfEntity.getSpf_acct_no());
		            spfEntity.setUrn_no(bspfEntity.getUrn_no());
		            spfEntity.setNo_of_days(bspfEntity.getNo_of_days());
		            spfEntity.setDays_paid(bspfEntity.getDays_paid());
		            spfEntity.setBank_name(bspfEntity.getBank_name());
		            spfEntity.setBank_acct_no(bspfEntity.getBank_acct_no());
		            spfEntity.setSalary_month(bspfEntity.getSalary_month());
		            spfEntity.setEmp_cont_12("2400");
		            spfEntity.setEmp_cont_367("734");
		            spfEntity.setEmp_cont_833("1666");
		            spfEntity.setTot_emp_cont("2400");
		            
		           
		            spfEntity.setHra(bspfEntity.getHra());
		            spfEntity.setSpl_allow(bspfEntity.getSpl_allow());
		            spfEntity.setMedi_reimb(bspfEntity.getMedi_reimb());
		            spfEntity.setConv_allow(bspfEntity.getConv_allow());
		            spfEntity.setLunch_allow(bspfEntity.getLunch_allow());
		            spfEntity.setEdu_allow(bspfEntity.getEdu_allow());
		            spfEntity.setBuss_attire(bspfEntity.getBuss_attire());
		            spfEntity.setCar_maint(bspfEntity.getCar_maint());
		            spfEntity.setLeave_travel_allow(bspfEntity.getLeave_travel_allow());
		            spfEntity.setOutstn_allow(bspfEntity.getOutstn_allow());
		            spfEntity.setAnnual_loyal_bonus(bspfEntity.getAnnual_loyal_bonus());
		            spfEntity.setOtr_allow(bspfEntity.getOtr_allow());
		            
		            String grossSalary = bspfEntity.getGross_salary().toString();
		            //String formattedValue = formatLakh(Double.valueOf(grossSalary));
		            spfEntity.setGross_salary(grossSalary);
		            BigDecimal expectedValue = new BigDecimal("300000");
	 	            if (bspfEntity.getCtc_amt().compareTo(expectedValue) >= 0) {
	 	                BigDecimal basicPay = new BigDecimal("15000");
	 	                spfEntity.setBasic_pay("15000");
	 	            }
		          //  String BasicPay=bspfEntity.getBasic_pay().toString();
		            //String formattedValue1 = formatLakh(Double.valueOf(BasicPay));
		           // spfEntity.setBasic_pay(BasicPay);
		            //BigDecimal bigDecimalValue = new BigDecimal(a);
		           // spfEntity.setGross_salary(bigDecimalValue);
		            spfEntity.setSpf(bspfEntity.getSpf());
		            spfEntity.setTds(bspfEntity.getTds());
		            spfEntity.setProf_tax(bspfEntity.getProf_tax());
		            spfEntity.setEsi(bspfEntity.getEsi());
		            spfEntity.setRecovery(bspfEntity.getRecovery());
		            spfEntity.setOtr_ded(bspfEntity.getOtr_ded());
		            spfEntity.setTotal_deductions(bspfEntity.getTotal_deductions());
		            spfEntity.setNet_salary(bspfEntity.getNet_salary());
		            spfEntity.setDate_of_pay(bspfEntity.getDate_of_pay());
		            spfEntity.setCum_tds_fy(bspfEntity.getCum_tds_fy());
		            spfEntity.setCtc_amt(bspfEntity.getCtc_amt());
		            spfEntity.setMobile_no(bspfEntity.getMobile_no());
		            spfEntity.setEmail_id(bspfEntity.getEmail_id());
		            spfEntity.setIfsc_code(bspfEntity.getIfsc_code());
		            spfEntity.setRemarks(bspfEntity.getRemarks());
		            spfEntity.setAdhar_no(bspfEntity.getAdhar_no());
		            if(bspfEntity.getBank_name().equals("ICICI")) {
		            	spfEntity.setTran_type("I");
		            }else {
		            	spfEntity.setTran_type("N");
		            }

		            spfEntity.setUnique_id(bspfEntity.getSalary_month()+bspfEntity.getEmp_no());
		            up3.add(spfEntity);
	            }
	        	else {
	        		
	        		
	        		
	        		 spf_entity spfEntity = new spf_entity();
	 	            spfEntity.setEmp_no(bspfEntity.getEmp_no());
	 	            spfEntity.setEmp_name(bspfEntity.getEmp_name());
	 	            spfEntity.setEmp_desig(bspfEntity.getEmp_desig());
	 	            spfEntity.setDate_of_birth(bspfEntity.getDate_of_birth());
	 	            spfEntity.setDate_of_join(bspfEntity.getDate_of_join());
	 	            spfEntity.setSpf_acct_no(bspfEntity.getSpf_acct_no());
	 	            spfEntity.setUrn_no(bspfEntity.getUrn_no());
	 	            spfEntity.setNo_of_days(bspfEntity.getNo_of_days());
	 	            spfEntity.setDays_paid(bspfEntity.getDays_paid());
	 	            spfEntity.setBank_name(bspfEntity.getBank_name());
	 	            spfEntity.setBank_acct_no(bspfEntity.getBank_acct_no());
	 	            spfEntity.setSalary_month(bspfEntity.getSalary_month());
	 	           /*if(bspfEntity.getBank_name().equals("ICICI")) {
		            	spfEntity.setTran_type("I");
		            }else {
		            	spfEntity.setTran_type("N");
		            }*/
	 	            
	 	           if (bspfEntity != null && bspfEntity.getBank_name() != null && bspfEntity.getBank_name().equals("ICICI")) {
	 	        	    spfEntity.setTran_type("I");
	 	        	} else {
	 	        	    spfEntity.setTran_type("N");
	 	        	}

	 	            
	 	            BigDecimal expectedValue = new BigDecimal("300000");
	 	            if (bspfEntity.getCtc_amt().compareTo(expectedValue) >= 0) {
	 	                BigDecimal basicPay = new BigDecimal("15000");
	 	                spfEntity.setBasic_pay("15000");
	 	                
	 	                //BigDecimal basicPay = bspfEntity.getBasic_pay();
	 	                
	 	                
	 		            int intValue = basicPay.intValue();
	 		            int emailId = Math.round(15000 * 8.33f / 100); 
	 		            String stringValue = Integer.toString(emailId);
	 		            spfEntity.setEmp_cont_833(stringValue);
	 		            //spfEntity.setEmp_cont_833(formatLakh(Double.valueOf(stringValue)));
	 		            
			          
			            
	 				    int ifsc = Math.round(15000 * 3.67f / 100); 
	 		            String stringValue1 = Integer.toString(ifsc);
	 		           spfEntity.setEmp_cont_367(stringValue1);
	 		           // spfEntity.setEmp_cont_367(formatLakh(Double.valueOf(stringValue1)));
	 				    
	 				    int remarks = Math.round(15000 * 12.00f / 100); 
	 		            String stringValue2 = Integer.toString(remarks);
	 		           spfEntity.setTot_emp_cont(stringValue2);
	 		            //spfEntity.setTot_emp_cont(formatLakh(Double.valueOf(stringValue2)));
	 		           
	 		            
	 	            } else if (bspfEntity.getCtc_amt().compareTo(expectedValue) < 0) {
	 	                BigDecimal basicPay = new BigDecimal("13985");
	 	                spfEntity.setBasic_pay("13985");
	 	                
	 	                int intValue = basicPay.intValue();
	 	                
	 	                int remarks = 1500; 
	 		            String stringValue2 = Integer.toString(remarks);
	 		           spfEntity.setTot_emp_cont(stringValue2);
	 		           // spfEntity.setTot_emp_cont(formatLakh(Double.valueOf(stringValue2)));
	 		            
	 		            int emailId = Math.round(remarks * 8.33f / 12); 
	 		            String stringValue = Integer.toString(emailId);
	 		            spfEntity.setEmp_cont_833(stringValue);
	 		           // spfEntity.setEmp_cont_833(formatLakh(Double.valueOf(stringValue)));
	 				    
	 				    int ifsc = Math.round(remarks * 3.67f / 12); 
	 		            String stringValue1 = Integer.toString(ifsc);
	 		           spfEntity.setEmp_cont_367(stringValue1);
	 		            //spfEntity.setEmp_cont_367(formatLakh(Double.valueOf(stringValue1)));
	 				    
	 				   
	 		            
	 	            } else {
	 	                // Handle the case where bspfEntity.getCtc_amt() is equal to expectedValue
	 	                // You can add code here if needed.
	 	            }

	 	           String grossSalary = bspfEntity.getGross_salary().toString();
		           // String formattedValue = formatLakh(Double.valueOf(grossSalary));
		            spfEntity.setGross_salary(grossSalary);
		            
		          
	 	            spfEntity.setHra(bspfEntity.getHra());
	 	            spfEntity.setSpl_allow(bspfEntity.getSpl_allow());
	 	            spfEntity.setMedi_reimb(bspfEntity.getMedi_reimb());
	 	            spfEntity.setConv_allow(bspfEntity.getConv_allow());
	 	            spfEntity.setLunch_allow(bspfEntity.getLunch_allow());
	 	            spfEntity.setEdu_allow(bspfEntity.getEdu_allow());
	 	            spfEntity.setBuss_attire(bspfEntity.getBuss_attire());
	 	            spfEntity.setCar_maint(bspfEntity.getCar_maint());
	 	            spfEntity.setLeave_travel_allow(bspfEntity.getLeave_travel_allow());
	 	            spfEntity.setOutstn_allow(bspfEntity.getOutstn_allow());
	 	            spfEntity.setAnnual_loyal_bonus(bspfEntity.getAnnual_loyal_bonus());
	 	            spfEntity.setOtr_allow(bspfEntity.getOtr_allow());
	 	           // spfEntity.setGross_salary(bspfEntity.getGross_salary());
	 	            spfEntity.setSpf(bspfEntity.getSpf());
	 	            spfEntity.setTds(bspfEntity.getTds());
	 	            spfEntity.setProf_tax(bspfEntity.getProf_tax());
	 	            spfEntity.setEsi(bspfEntity.getEsi());
	 	            spfEntity.setRecovery(bspfEntity.getRecovery());
	 	            spfEntity.setOtr_ded(bspfEntity.getOtr_ded());
	 	            spfEntity.setTotal_deductions(bspfEntity.getTotal_deductions());
	 	            spfEntity.setNet_salary(bspfEntity.getNet_salary());
	 	            spfEntity.setDate_of_pay(bspfEntity.getDate_of_pay());
	 	            spfEntity.setCum_tds_fy(bspfEntity.getCum_tds_fy());
	 	            spfEntity.setCtc_amt(bspfEntity.getCtc_amt());
	 	            spfEntity.setMobile_no(bspfEntity.getMobile_no());
	 	            spfEntity.setEmail_id(bspfEntity.getEmail_id());
	 	            spfEntity.setIfsc_code(bspfEntity.getIfsc_code());
	 	            spfEntity.setRemarks(bspfEntity.getRemarks());
	 	            spfEntity.setAdhar_no(bspfEntity.getAdhar_no());

	 	            spfEntity.setUnique_id(bspfEntity.getSalary_month()+bspfEntity.getEmp_no());
	 	            up3.add(spfEntity); }
	        	
	        	
	        }
	        System.out.println(up3);
	        	Spf_repo.saveAll(up3); 
	        String msg = "Data Saved Successfully"; // Changed the message
	        return msg;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error: " + e.getMessage();
	    }
	}

	private static String formatLakh(double d) {
	    String s = String.format(Locale.UK, "%1.2f", Math.abs(d));
	    s = s.replaceAll("(.+)(...\\...)", "$1,$2");
	    while (s.matches("\\d{3,},.+")) {
	        s = s.replaceAll("(\\d+)(\\d{2},.+)", "$1,$2");
	    }
	    return d < 0 ? ("-" + s) : s;
	}
	
	
	
	
	
	
	
	


	/*
	 * private static String formatLakh(double d) { String s =
	 * String.format(Locale.UK, "%1.2f", Math.abs(d)); s =
	 * s.replaceAll("(.+)(...\\...)", "$1,$2"); while (s.matches("\\d{3,},.+")) { s
	 * = s.replaceAll("(\\d+)(\\d{2},.+)", "$1,$2"); } return d < 0 ? ("-" + s) : s;
	 * }
	 */
	
	
	/*
	 * @RequestMapping(value = "viewtogst12", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public String viewtogst12(
	 * 
	 * @RequestParam(required = false) String formmode, Model md, HttpServletRequest
	 * rq,
	 * 
	 * @RequestParam(required = false) String b, @RequestParam(required = false)
	 * String a) {
	 * 
	 * String userId = (String) rq.getSession().getAttribute("USERID");
	 * md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
	 * md.addAttribute("menu", "BTMHeaderMenu");
	 * 
	 * System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}"+a +b); //int uniqueIdCounter =
	 * Integer.parseInt(b); //int uniqueIdCounter1 = Integer.parseInt(a);
	 * 
	 * try { List<BEXPI_entity> up2 = bexpiRepoa.getDataOverseas(b,a);
	 * List<Gstoverseas> up3 = new ArrayList<>();
	 * 
	 * for (BEXPI_entity bEXPI_entity : up2) { Gstoverseas gstoverseas = new
	 * Gstoverseas(); gstoverseas.setBank_account(bEXPI_entity.getBank_account());
	 * gstoverseas.setBank_charges(bEXPI_entity.getBank_charges());
	 * gstoverseas.setClient(bEXPI_entity.getClient());
	 * gstoverseas.setDel_flg(bEXPI_entity.getDel_flg());
	 * gstoverseas.setDescription(bEXPI_entity.getDescription());
	 * gstoverseas.setEmployee(bEXPI_entity.getEmployee());
	 * gstoverseas.setEnd_date(bEXPI_entity.getEnd_date());
	 * gstoverseas.setEntity_flg(bEXPI_entity.getEntity_flg());
	 * gstoverseas.setEntry_time(bEXPI_entity.getEntry_time());
	 * gstoverseas.setEntry_user(bEXPI_entity.getEntry_user());
	 * gstoverseas.setEx_fluc(bEXPI_entity.getEx_fluc());
	 * gstoverseas.setFbank_chg_fcy(bEXPI_entity.getFbank_chg_fcy());
	 * gstoverseas.setInv_amt_fcy(bEXPI_entity.getInv_amt_fcy());
	 * gstoverseas.setInv_amt_inr(bEXPI_entity.getInv_amt_inr());
	 * gstoverseas.setInv_date(bEXPI_entity.getInv_date());
	 * gstoverseas.setInv_no(bEXPI_entity.getInv_no());
	 * gstoverseas.setModify_flg(bEXPI_entity.getModify_flg());
	 * gstoverseas.setModify_time(bEXPI_entity.getModify_time());
	 * gstoverseas.setModify_user(bEXPI_entity.getModify_user());
	 * gstoverseas.setPart_tran_id(bEXPI_entity.getPart_tran_id());
	 * gstoverseas.setPart_tran_type(bEXPI_entity.getPart_tran_type());
	 * gstoverseas.setPayment_date(bEXPI_entity.getPayment_date());
	 * gstoverseas.setRate(bEXPI_entity.getRate());
	 * gstoverseas.setRemit_amt_fcy(bEXPI_entity.getRemit_amt_fcy());
	 * gstoverseas.setRemit_amt_inr(bEXPI_entity.getRemit_amt_inr());
	 * gstoverseas.setRemit_rate(bEXPI_entity.getRemit_rate());
	 * gstoverseas.setStart_date(bEXPI_entity.getStart_date());
	 * gstoverseas.setTran_crncy(bEXPI_entity.getTran_crncy());
	 * gstoverseas.setTran_date(bEXPI_entity.getTran_date());
	 * gstoverseas.setTran_id(bEXPI_entity.getTran_id());
	 * gstoverseas.setVerify_time(bEXPI_entity.getVerify_time());
	 * gstoverseas.setVerify_user(bEXPI_entity.getVerify_user());
	 * 
	 * up3.add(gstoverseas); }
	 * 
	 * System.out.println(up3); gstoverseasRepo.saveAll(up3);
	 * 
	 * // gstBtmRep.getInsert(b,a);
	 * 
	 * 
	 * System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}"+a +b); //int uniqueIdCounter =
	 * Integer.parseInt(b); //int uniqueIdCounter1 = Integer.parseInt(a);
	 * 
	 * try { List<gst> up4= gstRep.getDatagst(b,a); List<GstBtmEntity> up5 = new
	 * ArrayList<>();
	 * 
	 * for (gst gsts : up4) { GstBtmEntity gstBtmEntity = new GstBtmEntity();
	 * gstBtmEntity.setClient(gsts.getClient());
	 * gstBtmEntity.setClient_remark(gsts.getClient_remark());
	 * gstBtmEntity.setClient_type(gsts.getClient_type());
	 * gstBtmEntity.setEligible_amt(gsts.getEligible_amt());
	 * gstBtmEntity.setFin_year(gsts.getFin_year());
	 * gstBtmEntity.setGst_type(gsts.getGst_type());
	 * gstBtmEntity.setGstin(gsts.getGstin());
	 * gstBtmEntity.setInv_amt(gsts.getInv_amt());
	 * gstBtmEntity.setInv_cgst(gsts.getInv_cgst());
	 * gstBtmEntity.setInv_desc(gsts.getInv_desc());
	 * gstBtmEntity.setInv_igst(gsts.getInv_igst());
	 * gstBtmEntity.setInv_sgst(gsts.getInv_sgst());
	 * gstBtmEntity.setInv_tot_amt(gsts.getInv_tot_amt());
	 * gstBtmEntity.setInvoice_date(gsts.getInvoice_date());
	 * gstBtmEntity.setInvoice_no(gsts.getInvoice_no());
	 * gstBtmEntity.setPart_tran_id(gsts.getPart_tran_id());
	 * gstBtmEntity.setPart_tran_type(gsts.getPart_tran_type());
	 * gstBtmEntity.setPay_part_tran_id(gsts.getPay_part_tran_id());
	 * gstBtmEntity.setPay_part_tran_type(gsts.getPay_part_tran_type());
	 * gstBtmEntity.setPay_tran_date(gsts.getPay_tran_date());
	 * gstBtmEntity.setPayment_date(gsts.getPayment_date());
	 * gstBtmEntity.setRpay_tran_id(gsts.getRpay_tran_id());
	 * gstBtmEntity.setTotal_gst_amt(gsts.getTotal_gst_amt());
	 * gstBtmEntity.setTran_date(gsts.getTran_date());
	 * gstBtmEntity.setTran_id(gsts.getTran_id());
	 * 
	 * 
	 * up5.add(gstBtmEntity); }
	 * 
	 * System.out.println(up5); gstBtmRep.saveAll(up5);
	 * 
	 * //System.out.println(gstRep.getInsert(a,b)); String msg =
	 * "Data Saved Successfully"; return msg;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); return "Error: " +
	 * e.getMessage(); }
	 * 
	 */
	@RequestMapping(value = "viewtogst1", method = RequestMethod.POST)
	@ResponseBody
	public String viewtogst1(
	        @RequestParam(required = false) String formmode,
	        Model md, HttpServletRequest rq,
	        @RequestParam(required = false) String b, @RequestParam(required = false) String a) {

	    String userId = (String) rq.getSession().getAttribute("USERID");
	    md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
	    md.addAttribute("menu", "BTMHeaderMenu");

	    System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a + b);

	    try {
	        List<BEXPI_entity> up2 = bexpiRepoa.getDataOverseas(b, a);
	        List<Gstoverseas> up3 = new ArrayList<>();

	        for (BEXPI_entity bEXPI_entity : up2) {
	            Gstoverseas gstoverseas = new Gstoverseas();
	            gstoverseas.setBank_account(bEXPI_entity.getBank_account());
	            gstoverseas.setBank_charges(bEXPI_entity.getBank_charges());
	            gstoverseas.setClient(bEXPI_entity.getClient());
	            gstoverseas.setDel_flg(bEXPI_entity.getDel_flg());
	            gstoverseas.setDescription(bEXPI_entity.getDescription());
	            gstoverseas.setEmployee(bEXPI_entity.getEmployee());
	            gstoverseas.setEnd_date(bEXPI_entity.getEnd_date());
	            gstoverseas.setEntity_flg(bEXPI_entity.getEntity_flg());
	            gstoverseas.setEntry_time(bEXPI_entity.getEntry_time());
	            gstoverseas.setEntry_user(bEXPI_entity.getEntry_user());
	            gstoverseas.setEx_fluc(bEXPI_entity.getEx_fluc());
	            gstoverseas.setFbank_chg_fcy(bEXPI_entity.getFbank_chg_fcy());
	            gstoverseas.setInv_amt_fcy(bEXPI_entity.getInv_amt_fcy());
	            gstoverseas.setInv_amt_inr(bEXPI_entity.getInv_amt_inr());
	            gstoverseas.setInv_date(bEXPI_entity.getInv_date());
	            gstoverseas.setInv_no(bEXPI_entity.getInv_no());
	            gstoverseas.setModify_flg(bEXPI_entity.getModify_flg());
	            gstoverseas.setModify_time(bEXPI_entity.getModify_time());
	            gstoverseas.setModify_user(bEXPI_entity.getModify_user());
	            gstoverseas.setPart_tran_id(bEXPI_entity.getPart_tran_id());
	            gstoverseas.setPart_tran_type(bEXPI_entity.getPart_tran_type());
	            gstoverseas.setPayment_date(bEXPI_entity.getPayment_date());
	            gstoverseas.setRate(bEXPI_entity.getRate());
	            gstoverseas.setRemit_amt_fcy(bEXPI_entity.getRemit_amt_fcy());
	            gstoverseas.setRemit_amt_inr(bEXPI_entity.getRemit_amt_inr());
	            gstoverseas.setRemit_rate(bEXPI_entity.getRemit_rate());
	            gstoverseas.setStart_date(bEXPI_entity.getStart_date());
	            gstoverseas.setTran_crncy(bEXPI_entity.getTran_crncy());
	            gstoverseas.setTran_date(bEXPI_entity.getTran_date());
	            gstoverseas.setTran_id(bEXPI_entity.getTran_id());
	            gstoverseas.setVerify_time(bEXPI_entity.getVerify_time());
	            gstoverseas.setVerify_user(bEXPI_entity.getVerify_user());
	            gstoverseas.setUniqueid(bEXPI_entity.getTran_id()+bEXPI_entity.getPart_tran_id());

	            up3.add(gstoverseas);
	        }

	        System.out.println(up3);
	       //
	        gstoverseasRepo.saveAll(up3);

	        // gstBtmRep.getInsert(b,a);

	        System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a + b);
	        // int uniqueIdCounter = Integer.parseInt(b);
	        // int uniqueIdCounter1 = Integer.parseInt(a);

	        try {
	            List<gst> up4 = gstRep.getDatagst(b, a);
	            List<GstBtmEntity> up5 = new ArrayList<>();

	            for (gst gsts : up4) {
	                GstBtmEntity gstBtmEntity = new GstBtmEntity();
	                gstBtmEntity.setClient(gsts.getClient());
	                gstBtmEntity.setClient_remark(gsts.getClient_remark());
	                gstBtmEntity.setClient_type(gsts.getClient_type());
	                gstBtmEntity.setEligible_amt(gsts.getEligible_amt());
	                gstBtmEntity.setFin_year(gsts.getFin_year());
	                gstBtmEntity.setGst_type(gsts.getGst_type());
	                gstBtmEntity.setGstin(gsts.getGstin());
	                gstBtmEntity.setInv_amt(gsts.getInv_amt());
	                gstBtmEntity.setInv_cgst(gsts.getInv_cgst());
	                gstBtmEntity.setInv_desc(gsts.getInv_desc());
	                gstBtmEntity.setInv_igst(gsts.getInv_igst());
	                gstBtmEntity.setInv_sgst(gsts.getInv_sgst());
	                gstBtmEntity.setInv_tot_amt(gsts.getInv_tot_amt());
	                gstBtmEntity.setInvoice_date(gsts.getInvoice_date());
	                gstBtmEntity.setInvoice_no(gsts.getInvoice_no());
	                gstBtmEntity.setPart_tran_id(gsts.getPart_tran_id());
	                gstBtmEntity.setPart_tran_type(gsts.getPart_tran_type());
	                gstBtmEntity.setPay_part_tran_id(gsts.getPay_part_tran_id());
	                gstBtmEntity.setPay_part_tran_type(gsts.getPay_part_tran_type());
	                gstBtmEntity.setPay_tran_date(gsts.getPay_tran_date());
	                gstBtmEntity.setPayment_date(gsts.getPayment_date());
	                gstBtmEntity.setRpay_tran_id(gsts.getRpay_tran_id());
	                gstBtmEntity.setTotal_gst_amt(gsts.getTotal_gst_amt());
	                gstBtmEntity.setTran_date(gsts.getTran_date());
	                gstBtmEntity.setTran_id(gsts.getTran_id());
	                gstBtmEntity.setUniqueid(gsts.getTran_id()+gsts.getPart_tran_id());

	                up5.add(gstBtmEntity);
	            }

	            System.out.println(up5);
	            gstBtmRep.saveAll(up5);

	            // System.out.println(gstRep.getInsert(a,b));
	            String msg = "Data Saved Successfully";
	            return msg;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Error: " + e.getMessage();
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error: " + e.getMessage();
	    }
	}

	
	

	@RequestMapping(value = "esi", method = { RequestMethod.GET, RequestMethod.POST })
	public String esi(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resName,@RequestParam(required = false) String a, @RequestParam(required = false) String Month, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		
		 if ( formmode == null ||formmode.equals("list1")) {
			if(Month!=null || Month=="") {
			List<spf_entity> spfValues = Spf_repo.getESI(Month);

			for (int i = 0; i < spfValues.size(); i++) {
			    spf_entity entity = spfValues.get(i);
			    // Do something with the entity, e.g., print its properties
			    System.out.println("Bank Acct No: " + entity.getBank_acct_no());
			    System.out.println("Bank Name: " + entity.getBank_name());
			    System.out.println("Salary Month: " + entity.getSalary_month());
			    
			    String stringValue = entity.getGross_salary(); // Replace this with your desired string
		        BigDecimal grosspay = new BigDecimal(stringValue);
		        
	            int intValue = grosspay.intValue();
	            int emailId = Math.round(intValue * 3.25f / 100); 
	            String stringValue1 = Integer.toString(emailId);
			    entity.setEmail_id(stringValue1);
			    
			    int ifsc = Math.round(intValue * 0.75f / 100); 
	            String stringValue2 = Integer.toString(ifsc);
			    entity.setIfsc_code(stringValue2);
			    
			    int remarks = Math.round(intValue * 4.00f / 100); 
	            String stringValue3 = Integer.toString(remarks);
			    entity.setRemarks(stringValue3);
			    //spfValues.set(0, entity);
			}
			
			md.addAttribute("ghj",spfValues);
			md.addAttribute("formmode", "list1");
			md.addAttribute("month",Month);
			}else {
				 YearMonth currentYearMonth = YearMonth.now();

			        // Format the current month and year as a string
			        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
			        String formattedMonthYear = currentYearMonth.format(formatter);
			        //String[] parts = formattedMonthYear.split(" ");
			        // Print the current month and year
			        System.out.println("Current Month and Year: " + formattedMonthYear);
			        
				List<spf_entity> spfValues = Spf_repo.getall(formattedMonthYear);

				for (int i = 0; i < spfValues.size(); i++) {
				    spf_entity entity = spfValues.get(i);
				    // Do something with the entity, e.g., print its properties
				    System.out.println("Bank Acct No: " + entity.getBank_acct_no());
				    System.out.println("Bank Name: " + entity.getBank_name());
				    System.out.println("Salary Month: " + entity.getSalary_month());
				   
				    String stringValue = entity.getBasic_pay(); // Replace this with your desired string
			        BigDecimal basicPay = new BigDecimal(stringValue);
			        
		            int intValue = basicPay.intValue();
		            int emailId = Math.round(intValue * 8.33f / 100); 
		            String stringValue1 = Integer.toString(emailId);
				    entity.setEmail_id(stringValue1);
				    
				    int ifsc = Math.round(intValue * 3.67f / 100); 
		            String stringValue11 = Integer.toString(ifsc);
				    entity.setIfsc_code(stringValue11);
				    
				    int remarks = Math.round(intValue * 12.00f / 100); 
		            String stringValue2 = Integer.toString(remarks);
				    entity.setRemarks(stringValue2);
				    spfValues.set(0, entity);
				    
				    
				    
				}
				
				md.addAttribute("ghj",spfValues);
				md.addAttribute("formmode", "list1");
				
			}
			
			
			
		}else if ( formmode == null ||formmode.equals("list")) {
			if(Month!=null || Month=="") {
			List<spf_entity> spfValues = Spf_repo.getESI(Month);

			for (int i = 0; i < spfValues.size(); i++) {
			    spf_entity entity = spfValues.get(i);
			    // Do something with the entity, e.g., print its properties
			    System.out.println("Bank Acct No: " + entity.getBank_acct_no());
			    System.out.println("Bank Name: " + entity.getBank_name());
			    System.out.println("Salary Month: " + entity.getSalary_month());
			    
			    String stringValue = entity.getGross_salary(); // Replace this with your desired string
		        BigDecimal grosspay = new BigDecimal(stringValue);
		        
	            int intValue = grosspay.intValue();
	            int emailId = Math.round(intValue * 3.25f / 100); 
	            String stringValue1 = Integer.toString(emailId);
			    entity.setEmail_id(stringValue1);
			    
			    int ifsc = Math.round(intValue * 0.75f / 100); 
	            String stringValue2 = Integer.toString(ifsc);
			    entity.setIfsc_code(stringValue2);
			    
			    int remarks = Math.round(intValue * 4.00f / 100); 
	            String stringValue3 = Integer.toString(remarks);
			    entity.setRemarks(stringValue3);
			    //spfValues.set(0, entity);
			}
			
			md.addAttribute("ghj",spfValues);
			md.addAttribute("formmode", "list");
			md.addAttribute("month",Month);
			}else {
				 YearMonth currentYearMonth = YearMonth.now();

			        // Format the current month and year as a string
			        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
			        String formattedMonthYear = currentYearMonth.format(formatter);
			        //String[] parts = formattedMonthYear.split(" ");
			        // Print the current month and year
			        System.out.println("Current Month and Year: " + formattedMonthYear);
			        
				List<spf_entity> spfValues = Spf_repo.getall(formattedMonthYear);

				for (int i = 0; i < spfValues.size(); i++) {
				    spf_entity entity = spfValues.get(i);
				    // Do something with the entity, e.g., print its properties
				    System.out.println("Bank Acct No: " + entity.getBank_acct_no());
				    System.out.println("Bank Name: " + entity.getBank_name());
				    System.out.println("Salary Month: " + entity.getSalary_month());
				   
				    String stringValue = entity.getBasic_pay(); // Replace this with your desired string
			        BigDecimal basicPay = new BigDecimal(stringValue);
			        
		            int intValue = basicPay.intValue();
		            int emailId = Math.round(intValue * 8.33f / 100); 
		            String stringValue1 = Integer.toString(emailId);
				    entity.setEmail_id(stringValue1);
				    
				    int ifsc = Math.round(intValue * 3.67f / 100); 
		            String stringValue11 = Integer.toString(ifsc);
				    entity.setIfsc_code(stringValue11);
				    
				    int remarks = Math.round(intValue * 12.00f / 100); 
		            String stringValue2 = Integer.toString(remarks);
				    entity.setRemarks(stringValue2);
				    spfValues.set(0, entity);
				    
				    
				    
				}
				
				md.addAttribute("ghj",spfValues);
				md.addAttribute("formmode", "list");
				
			}
			
			
			
		}


 

		return "ESI";
	}

	@RequestMapping(value = "ackt", method = { RequestMethod.GET, RequestMethod.POST })
	public String ackt(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resName,@RequestParam(required = false) String a, @RequestParam(required = false) String Month, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		 if(formmode == null ||formmode=="table") {
				md.addAttribute("formmode", "table");
			}
		 else if (formmode.equals("edit")) {

				md.addAttribute("formmode", "edit");
				md.addAttribute("editing", Spf_repo.findit(a));
		 }
		 else if ( formmode.equals("list")) {
			if(Month!=null || Month=="") {
			List<spf_entity> spfValues = Spf_repo.getall(Month);

			
			md.addAttribute("ghj",spfValues);
			md.addAttribute("formmode", "list");
			md.addAttribute("month",Month);
			}else {
				 YearMonth currentYearMonth = YearMonth.now();

			        // Format the current month and year as a string
			        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
			        String formattedMonthYear = currentYearMonth.format(formatter);
			        //String[] parts = formattedMonthYear.split(" ");
			        // Print the current month and year
			        System.out.println("Current Month and Year: " + formattedMonthYear);
			        
				List<spf_entity> spfValues = Spf_repo.getall(formattedMonthYear);

				
				
				md.addAttribute("ghj",spfValues);
				md.addAttribute("formmode", "list");
				
			}
			
			
			
		}

 

		return "AckTrigger";
	}
	
	
	
	
	@RequestMapping(value = "gst", method = { RequestMethod.GET, RequestMethod.POST })
	public String gst(@RequestParam(required = false) String formmode,@RequestParam(required = false) String raised,
			@RequestParam(required = false) String resName,@RequestParam(required = false) String tran_id, @RequestParam(required = false) String Month,@RequestParam(required = false) String Year, Model md,
			@RequestParam(required = false) String uniqueid,HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		//System.out.println(raised);
		
		if(formmode == null ||formmode=="table") {
			md.addAttribute("formmode", "table");
			
		}
		else if(formmode.equals("table2")){
				md.addAttribute("formmode", "table2");
			}
		 else if(formmode.equals("vtb")) {
				md.addAttribute("formmode", "vtb");
			}
		 else if(formmode.equals("add")) {
				md.addAttribute("formmode", "add");
			}
		
		 else if(formmode.equals("add1")) {
				md.addAttribute("formmode", "add1");
			}
		 else if (formmode.equals("edit")) {

				md.addAttribute("formmode", "edit");
				md.addAttribute("editing", gstBtmRep.findByTran(uniqueid));
		 }
		 else if (formmode.equals("edit1")) {

				md.addAttribute("formmode", "edit1");
				md.addAttribute("editingoverseas", gstoverseasRepo.findByTranoverseas(uniqueid));
		 }
		 else if ( formmode.equals("list")) {
			 YearMonth currentYearMonth = YearMonth.now();
		     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
		     DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy");
		     String formattedMonth = currentYearMonth.format(formatter);
		     String formattedYear = currentYearMonth.format(formatter1);
		     System.out.println("Current Month and Year: " + formattedMonth+formattedYear);
		     System.out.println(Month + Year);
			if(raised.equals("By Us - India")) {
				if(Month == null || Year == null) {
					 List<GstBtmEntity> gstvalues = gstBtmRep.getByIndia(formattedMonth,formattedYear);
					 md.addAttribute("ghj",gstvalues);
					 
					
					 
					 md.addAttribute("raised",raised);
					 md.addAttribute("monthy",formattedMonth);
					 md.addAttribute("year",formattedYear);
					 md.addAttribute("formmode", "list");
					 
				}else if(Month != null || Year != null){
					 List<GstBtmEntity> gstvalues = gstBtmRep.getByIndia(Month,Year);
					 md.addAttribute("ghj",gstvalues);
					 md.addAttribute("raised",raised);
					 md.addAttribute("monthy",Month);
					 md.addAttribute("year",Year);
					 md.addAttribute("formmode", "list");
				}
			 }
			else if(raised.equals("On Us - India")) {
				if(Month == null || Year == null) {
					List<GstBtmEntity> gstvalues = gstBtmRep.getOnIndia(formattedMonth,formattedYear);
					 md.addAttribute("ghj",gstvalues);
					 md.addAttribute("raised",raised);
					 md.addAttribute("monthy",formattedMonth);
					 md.addAttribute("year",formattedYear);
					 md.addAttribute("formmode", "list");
				}else {
					List<GstBtmEntity> gstvalues = gstBtmRep.getOnIndia(Month,Year);
					 md.addAttribute("ghj",gstvalues);
					 md.addAttribute("raised",raised);
					 md.addAttribute("monthy",Month);
					 md.addAttribute("year",Year);
					 md.addAttribute("formmode", "list");
				}
				
			}
		 }
			 else if ( formmode.equals("list1")) {
				 YearMonth currentYearMonth1 = YearMonth.now();
			     DateTimeFormatter formatter11 = DateTimeFormatter.ofPattern("MM");
			     DateTimeFormatter formatter111 = DateTimeFormatter.ofPattern("yyyy");
			     String formattedMonth1 = currentYearMonth1.format(formatter111);
			     String formattedYear1 = currentYearMonth1.format(formatter111);
			     System.out.println("Current Month and Year: " + formattedMonth1+formattedYear1);
			     System.out.println(Month + Year);
			     if(raised.equals("By Us - Overseas")) {
				if(Month == null || Year == null) {
					//<GstBtmEntity> gstvalues = gstBtmRep.getOnIndia(formattedMonth1,formattedYear1);
					 
					
					 
					 List<Gstoverseas> gstvaluess =gstoverseasRepo .getBygstoversea(formattedMonth1,formattedYear1);
					 md.addAttribute("ghj",gstvaluess);
					 System.out.println("+++++"+gstvaluess);
					 md.addAttribute("raised",raised);
					 md.addAttribute("monthy",formattedMonth1);
					 md.addAttribute("year",formattedYear1);
					 md.addAttribute("formmode", "list1");
				}else {
					 List<Gstoverseas> gstvaluess =gstoverseasRepo .getBygstoversea(Month,Year);
					 System.out.println(gstvaluess);
					 md.addAttribute("ghj",gstvaluess);
					 md.addAttribute("raised",raised);
					 md.addAttribute("monthy",Month);
					 md.addAttribute("year",Year);
					 md.addAttribute("formmode", "list1");
				}
				
			}
			 }
			
		return "GST";
	}
	
	@RequestMapping(value = "bank_acct", method = { RequestMethod.GET, RequestMethod.POST })
	public String bank_acct(@RequestParam(required = false) String formmode,@RequestParam(required = false) String raised,
			@RequestParam(required = false) String resName,@RequestParam(required = false) String a,@RequestParam(required = false) String b, @RequestParam(required = false) String Month,@RequestParam(required = false) String Year, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if ( formmode == null ||formmode.equals("list")) {
			if(Month==null || Month=="") {
				 YearMonth currentYearMonth = YearMonth.now();

			        // Format the current month and year as a string
			        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
			        String formattedMonthYear = currentYearMonth.format(formatter);
			        //String[] parts = formattedMonthYear.split(" ");
			        // Print the current month and year
			        System.out.println("Current Month and Year: " + formattedMonthYear);
			        
				List<spf_entity> spfValues = Spf_repo.getall(formattedMonthYear);

				
				
				md.addAttribute("ghj",spfValues);
				md.addAttribute("formmode", "list");
				
				}else {
					List<spf_entity> spfValues = Spf_repo.getall(Month);
					md.addAttribute("ghj",spfValues);
					md.addAttribute("formmode", "list");
					md.addAttribute("month",Month);
					
				}
		//	md.addAttribute("ghj", Spf_repo.getData(b));
		//			 md.addAttribute("formmode", "list");
			}
		
		else if(formmode.equals("table")) {
				md.addAttribute("formmode", "table");
			}
		 else if (formmode.equals("edit")) {
				md.addAttribute("formmode", "edit");
				md.addAttribute("editing", Spf_repo.findit(a));
		 }
		 else {
			 
		 }
		return "BankAcct";
	}
	
	@RequestMapping(value = "viewtobtm", method = RequestMethod.POST) 
	@ResponseBody
	public String viewtobtm (@RequestParam(required = false) String b, Model md, HttpServletRequest rq) { 
	//System.out.println(b);	
	bankDetailService.getviewtobtm(b);
	return "success"; 

	}
	
	
	@RequestMapping(value = "GstDownload1", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource GstDownload1(HttpServletResponse response,@RequestParam(required = false) String raised,@RequestParam(required = false) String month,@RequestParam(required = false) String year

		) throws IOException, SQLException {

		response.setContentType("application/octet-stream");
System.out.println("==============="+month+year);
		InputStreamResource resource = null;
		try {
			
			String filetype="Excel";
			String Month=month;
			String Year=year;
			String Raised=raised;
			//logger.info("Getting download File :" +  + ", FileType :Excel" +  + "");

			File repfile = placementServices.getGstFile(filetype,Month,Year,Raised);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}
	
	@RequestMapping(value = "GSTPROFILE", method = RequestMethod.POST) 
	@ResponseBody
	public String barathvarson ( Model md, HttpServletRequest rq,
	@ModelAttribute GstBtmEntity gstBtmEntity, String tran_id,String Gst_type) { 
		
		
		System.out.println("The solid Id >>>>>>>>>>>>>>>>>>>>>>>>> "+gstBtmEntity.getTran_id());
		GstBtmEntity up= gstBtmRep.findByTran(gstBtmEntity.getTran_id());
		up.setInv_amt(gstBtmEntity.getInv_amt());
		up.setClient(gstBtmEntity.getClient());
		up.setGstin(gstBtmEntity.getGstin());
		up.setInvoice_no(gstBtmEntity.getInvoice_no());
		up.setInvoice_date(gstBtmEntity.getInvoice_date());
		up.setInv_desc(gstBtmEntity.getInv_desc());
		up.setInv_sgst(gstBtmEntity.getInv_sgst());
		up.setInv_cgst(gstBtmEntity.getInv_cgst());
		up.setInv_igst(gstBtmEntity.getInv_igst());
		up.setTotal_gst_amt(gstBtmEntity.getTotal_gst_amt());
		up.setInv_tot_amt(gstBtmEntity.getInv_tot_amt());
		up.setClient_remark(gstBtmEntity.getClient_remark());
		up.setGst_type(gstBtmEntity.getGst_type());
		
		
		System.out.println(gstBtmEntity.getGst_type());
		System.out.println(tran_id);
		gstBtmRep.save(up);
	//System.out.println("SalaryParameter");
	
	return "success"; 

	}

	
	@PostMapping("viewtogst")
	@ResponseBody
	public String viewtogst(
	        @RequestParam(required = false) String formmode,
	        Model md,HttpServletRequest rq,@RequestParam(required = false) String b) {
		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
	    System.out.println(b);
	    System.out.println(b.substring(0,4));
	    System.out.println(b.substring(4,6));
	    int uniqueIdCounter = Integer.parseInt(b);
	    try {
	        // AccessRoles up = new AccessRoles();
	        // List<BSPF_ENTITY> up1 = SpfRepo.getData(b);
	        List<gst> up2 = gstRep.getData(b.substring(4,6),b.substring(0,4));
	        List<spf_entity> up3 = new ArrayList<>();
	       
	    
	        System.out.println(up2);
	        	Spf_repo.saveAll(up3); 
	        String msg = "Data Saved Successfully"; // Changed the message
	        return msg;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error: " + e.getMessage();
	    }
	}
	
	@RequestMapping(value = "TDS", method = { RequestMethod.GET, RequestMethod.POST })
	public String TDS(@RequestParam(required = false) String formmode,@RequestParam(required = false) String moths,@RequestParam(required = false) String d,@RequestParam(required = false) String twoDigitYear,
			@RequestParam(required = false) String years,@RequestParam(required = false) String date_of_pay,@RequestParam(required = false) String uniqueids, @RequestParam(required = false) String Month,@RequestParam(required = false) String Year, Model md,
			tdsentity tdsentity	,HttpServletRequest req ) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		System.out.println(moths);
		System.out.println(d);
		System.out.println(years);            
		 if(formmode == null ||formmode=="table") {
				md.addAttribute("formmode", "table");
			}
		 else if(formmode.equals("view")) {
				md.addAttribute("formmode", "view");
			}
		 
		 else if(formmode.equals("list1")) {
			 
				/*
				 * if(moths == null || years == null) { List<tdsentity> tdsvalues =
				 * tdsRepos.gettdswithdec(moths,years); md.addAttribute("tds",tdsvalues);
				 * 
				 * }else if(moths != null || years!= null){
				 */
			 
			 YearMonth currentYearMonth = YearMonth.now();
		     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
		     DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy");
		     String formattedMonth = currentYearMonth.format(formatter);
		     String formattedYear = currentYearMonth.format(formatter1);
		     System.out.println("Current Month and Year: " + formattedMonth+formattedYear);
		     System.out.println(moths + Year);
			 if(moths == null || years == null) {
				 List<tdsentity> tdsvalues = tdsRepos.gettdswithfirst(formattedMonth,formattedYear);
				 md.addAttribute("tds",tdsvalues);
				 md.addAttribute("formmode", "list1");
			 }else if (moths != null || years != null) {
			 	System.out.println("=========================="+moths+"----------------"+years);
				 List<tdsentity> tdsvalues = tdsRepos.gettdswithdecs(moths,years);
				    md.addAttribute("tds", tdsvalues);
				    System.out.println(tdsvalues);
				    md.addAttribute("formmode", "list1");//hhhhhhhhhhhhhhhhhh
				   
			 }
				
			 }
				
				
			
			 
		 
				
				else if(formmode.equals("table1")) {
					md.addAttribute("formmode", "table1");
			}
				else if(formmode.equals("add")) {
					md.addAttribute("formmode", "add");
			}
				else if(formmode.equals("add1")) {
					md.addAttribute("formmode", "add1");
			}
				else if(formmode.equals("edit")) {
					
					md.addAttribute("edittab1", tdsRepos.getlisttab1(uniqueids));
					md.addAttribute("formmode", "edit");
			}
				else if(formmode.equals("edit1")) {
					
					md.addAttribute("edittab2",tdsRepos.getlisttab2(uniqueids));	
					md.addAttribute("formmode", "edit1");
			}
				else if(formmode.equals("delete")) {
					md.addAttribute("formmode", "delete");
			}
		 			
		 			
		return "TDS";
	}	
	
	
	
	
	@RequestMapping(value = "editgstonus", method = RequestMethod.POST)
	@ResponseBody
	public String editgstonus(@ModelAttribute GstBtmEntity GstBtmEntity, String tran_id,String Gst_type ,@RequestParam(required = false) String uniqueid,@RequestParam(required = false) String Ddt,@RequestParam(required = false) String dsr,
			@RequestParam(required = false) String rds,@RequestParam(required = false) String temp4,	
			@RequestParam(required = false) String f) throws ParseException {
		String u=uniqueid;
		
		
System.out.println(u);

		GstBtmEntity up = gstBtmRep.findByTran(u);
		System.out.println("hi this is uniqueid for editonusindia"+gstBtmRep.findByTran(u));
		System.out.println("hi this is btm");
		
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
			up.setTran_id(GstBtmEntity.getTran_id());
			up.setPart_tran_type(GstBtmEntity.getPart_tran_type());
			up.setClient(GstBtmEntity.getClient());
			up.setGstin(GstBtmEntity.getGstin());
			up.setGst_type(GstBtmEntity.getGst_type());
			up.setInvoice_no(GstBtmEntity.getInvoice_no());
			up.setInv_desc(GstBtmEntity.getInv_desc());
			up.setInvoice_date(GstBtmEntity.getInvoice_date());
			up.setInv_amt(GstBtmEntity.getInv_amt());
			up.setInv_sgst(GstBtmEntity.getInv_sgst());
			up.setInv_cgst(GstBtmEntity.getInv_cgst());
			up.setInv_igst(GstBtmEntity.getInv_igst());
			up.setTotal_gst_amt(GstBtmEntity.getTotal_gst_amt());
			up.setInv_tot_amt(GstBtmEntity.getInv_tot_amt());
			up.setClient_remark(GstBtmEntity.getClient_remark());
			//up.setTran_date(dateFormat.parse(f));
			//up.setInvoice_date(dateFormat.parse(rds));
			up.setTran_date(GstBtmEntity.getTran_date());
			
			up.setUniqueid(GstBtmEntity.getUniqueid());
			
			
			gstBtmRep.save(up);
			System.out.println("hi this is gst edit for india"+GstBtmEntity.getGst_type());
			System.out.println("hi this is btm"+GstBtmEntity.getGstin());
			System.out.println("hi this is btm"+GstBtmEntity.getTran_date());

			
			// Save the 'up' object with the updated entry_time

		} catch (Exception e) {
		    e.printStackTrace(); // Handle potential errors here, such as ParseException
		}

		return "edited Successfully";

	}

	

	@RequestMapping(value = "editoverseas", method = RequestMethod.POST)
	@ResponseBody
	public String editoverseas(@ModelAttribute Gstoverseas Gstoverseas, String tran_id,@RequestParam(required = false) String uniqueid) throws ParseException {
		String u1=uniqueid;

		Gstoverseas up = gstoverseasRepo.findByTranoverseas(u1);
		System.out.println("hi this is btm");
		
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);

		try {
			up.setTran_id(Gstoverseas.getTran_id());
			up.setPart_tran_type(Gstoverseas.getPart_tran_type());
			up.setClient(Gstoverseas.getClient());
			
			up.setInvoice_no(Gstoverseas.getInvoice_no());
			up.setInv_no(Gstoverseas.getInv_no());
			up.setInv_date(Gstoverseas.getInv_date());
			up.setInv_amt_fcy(Gstoverseas.getInv_amt_fcy());
			up.setInv_amt_inr(Gstoverseas.getInv_amt_inr());
			up.setBank_account(Gstoverseas.getBank_account());
			up.setBank_account(Gstoverseas.getBank_account());
			up.setTran_date(Gstoverseas.getTran_date());
			//up.setUniqueid(Gstoverseas.getUniqueid());
			
			
		    
			gstoverseasRepo.save(up);
			System.out.println("hi this is gst from overseas"+Gstoverseas.getTran_id());
			System.out.println("hi this is btm"+Gstoverseas.getInv_no());
			
			// Save the 'up' object with the updated entry_time

		} catch (Exception e) {
		    e.printStackTrace(); // Handle potential errors here, such as ParseException
		}

		return "edited Successfully OVERSEAS";

	}
	
	
	
	/*
	 * @RequestMapping(value = "downloadpage", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public InputStreamResource INRReportDownload
	 * (HttpServletResponse response,
	 * 
	 * @RequestParam(value = "filetype", required = false) String filetype) throws
	 * IOException, SQLException, JRException {
	 * 
	 * response.setContentType("application/octet-stream");
	 * 
	 * InputStreamResource resource = null; try { File repfile =
	 * placementServices.getECLFile(filetype);
	 * 
	 * response.setHeader("Content-Disposition", "attachment; filename=" +
	 * repfile.getName()); response.setContentType(
	 * "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // Set
	 * the content type to Excel
	 * 
	 * try (InputStream inputStream = new FileInputStream(repfile); OutputStream
	 * outputStream = response.getOutputStream()) {
	 * 
	 * byte[] buffer = new byte[1024]; int bytesRead;
	 * 
	 * while ((bytesRead = inputStream.read(buffer)) != -1) {
	 * outputStream.write(buffer, 0, bytesRead); }
	 * 
	 * outputStream.flush(); } } catch (FileNotFoundException e) { // Handle file
	 * not found exception e.printStackTrace(); // Consider logging or handling the
	 * exception appropriately } catch (IOException e) { // Handle IO exception
	 * e.printStackTrace(); // Consider logging or handling the exception
	 * appropriately } catch (Exception e) { // Handle other exceptions
	 * e.printStackTrace(); // Consider logging or handling the exception
	 * appropriately }
	 * 
	 * return resource; }
	 */

	/*
	 * @RequestMapping(value = "INRReportDownload", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public InputStreamResource INRReportDownload
	 * (HttpServletResponse response,
	 * 
	 * @RequestParam(value = "filetype", required = false) String
	 * filetype,@RequestParam(required = false) String month,@RequestParam(required
	 * = false) String year) throws IOException, SQLException, JRException {
	 * 
	 * response.setContentType("application/octet-stream");
	 * 
	 * InputStreamResource resource = null; try { File repfile =
	 * placementServices.getECLFile(filetype,month,year);
	 * 
	 * response.setHeader("Content-Disposition", "attachment; filename=" +
	 * repfile.getName()); response.setContentType(
	 * "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // Set
	 * the content type to Excel
	 * 
	 * try (InputStream inputStream = new FileInputStream(repfile); OutputStream
	 * outputStream = response.getOutputStream()) {
	 * 
	 * byte[] buffer = new byte[1024]; int bytesRead;
	 * 
	 * while ((bytesRead = inputStream.read(buffer)) != -1) {
	 * outputStream.write(buffer, 0, bytesRead); }
	 * 
	 * outputStream.flush(); } } catch (FileNotFoundException e) { // Handle file
	 * not found exception e.printStackTrace(); // Consider logging or handling the
	 * exception appropriately } catch (IOException e) { // Handle IO exception
	 * e.printStackTrace(); // Consider logging or handling the exception
	 * appropriately } catch (Exception e) { // Handle other exceptions
	 * e.printStackTrace(); // Consider logging or handling the exception
	 * appropriately }
	 * 
	 * return resource; }
	 */
	
	@RequestMapping(value = "/INRReportDownload", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<InputStreamResource> INRReportDownload(
	        HttpServletResponse response,
	        @RequestParam(required = false) String filetype,
	        @RequestParam(required = false) String month,
	        @RequestParam(required = false) String year) throws IOException, SQLException, JRException {

	    // Set the response content type
	    response.setContentType("application/octet-stream");

	    String YearL=null;
		if(month.equals("01")) {
			YearL="Jan";
		}else if(month.equals("02")) {
			YearL="Feb";
		}else if(month.equals("02")) {
			YearL="Feb";
		}else if(month.equals("03")) {
			YearL="Mar";
		}else if(month.equals("04")) {
			YearL="Apr";
		}else if(month.equals("05")) {
			YearL="May";
		}else if(month.equals("06")) {
			YearL="Jun";
		}else if(month.equals("07")) {
			YearL="Jul";
		}else if(month.equals("08")) {
			YearL="Aug";
		}else if(month.equals("09")) {
			YearL="Sep";
		}else if(month.equals("10")) {
			YearL="Oct";
		}else if(month.equals("11")) {
			YearL="Nov";
		}else if(month.equals("12")) {
			YearL="Dec";
		}
	    // Construct the file name
	    String fileName = "GST_" + YearL + "-" + year + ".xlsx";

	    // Get the file from the service
	    File eclFile = placementServices.getECLFile(fileName,filetype, month, year);

	    // Prepare the InputStreamResource
	    InputStreamResource resource = new InputStreamResource(new FileInputStream(eclFile));

	    // Prepare the response headers
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

	    // Return the ResponseEntity with InputStreamResource and headers
	    return ResponseEntity.ok()
	            .headers(headers)
	            .contentLength(eclFile.length())
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(resource);
	}
	
	
	@RequestMapping(value = "adddatasindia", method = RequestMethod.POST)
	@ResponseBody
	public String adddatasindia(Model md, HttpServletRequest rq, @ModelAttribute GstBtmEntity GstBtmEntity,
			String tran_id,@RequestParam(required = false) String tran_date,@RequestParam(required = false) String invoice_date ) {

		
		System.out.println("the rating AGENCY>>>> ");
		GstBtmEntity up = GstBtmEntity;
		up.setTran_date(GstBtmEntity.getTran_date());
		up.setInvoice_date(GstBtmEntity.getInvoice_date());
		
		up.setUniqueid(GstBtmEntity.getTran_id()+GstBtmEntity.getPart_tran_id());
		

		System.out.println("hi it is gst here your adding the record for india");
		System.out.println("hi it is gst here your adding the record "+GstBtmEntity.getTran_date());

		gstBtmRep.save(up);

		return "add successfullu";
	
   
}
	
	
	
	@RequestMapping(value = "addoverseas", method = RequestMethod.POST)
	@ResponseBody
	public String addoverseas(Model md, HttpServletRequest rq, @ModelAttribute Gstoverseas Gstoverseas,
			String tran_id) {

		
		System.out.println("the rating AGENCY>>>> ");
		Gstoverseas up = Gstoverseas;
		up.setUniqueid(Gstoverseas.getTran_id()+Gstoverseas.getPart_tran_id());
		
		System.out.println("hi it is gst here your adding the record for overseas");
		
		gstoverseasRepo.save(up);

		return "";
	
   
}
	
	
	@RequestMapping(value = "deleteoverseas", method = RequestMethod.POST)
	@ResponseBody
	public String deleteoverseas(Model md, HttpServletRequest rq, @ModelAttribute Gstoverseas Gstoverseas,
			String tran_id) {

		
		System.out.println("the rating AGENCY>>>> ");
		Gstoverseas up = Gstoverseas;
		System.out.println("hi it is gst here your adding the record "+Gstoverseas.getTran_date());
		
		System.out.println("hi it is gst here your adding the record for overseas");
		
		gstoverseasRepo.save(up);

		return "deleted successfully";
	
   
}
	
	@RequestMapping(value = "deleteindia", method = RequestMethod.POST)
	@ResponseBody
	public String deleteindia(Model md, HttpServletRequest rq, @ModelAttribute GstBtmEntity GstBtmEntity,
			String tran_id,@RequestParam(required = false) String tran_date,@RequestParam(required = false) String invoice_date ) {

		
		System.out.println("the rating AGENCY>>>> ");
		GstBtmEntity up = GstBtmEntity;
		
		
		
		

		System.out.println("hi it is gst here your adding the record for india");
		System.out.println("hi it is gst here your adding the record "+GstBtmEntity.getTran_date());

		gstBtmRep.save(up);

		return "deleted successfully";
	
   
}
	
	

	@RequestMapping(value = "viewtotds", method = RequestMethod.POST)
	@ResponseBody
	public String viewtotds(
	        @RequestParam(required = false) String formmode,
	        Model md, HttpServletRequest rq,
	        @RequestParam(required = false) String b, @RequestParam(required = false) String a) {

	    String userId = (String) rq.getSession().getAttribute("USERID");
	    md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
	    md.addAttribute("menu", "BTMHeaderMenu");

	    System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a + b);

	    try {
	        List<btdsview> up2 = btdsviewRepos.getdatetdslist(b, a);
	        List<tdsentity> up3 = new ArrayList<>();
	        
	        
	        for (btdsview btdsviews : up2) {
	        	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        	tdsentity tdsentitys = new tdsentity();
	        	tdsentitys.setEmp_no(btdsviews.getEmp_no());  
	        	tdsentitys.setEmp_name(btdsviews.getEmp_name());  
	        	tdsentitys.setEmp_desig(btdsviews.getEmp_desig());  
	        	tdsentitys.setEmp_group(btdsviews.getEmp_group());  
	        	tdsentitys.setPan_no(btdsviews.getPan_no());  
	        	tdsentitys.setDate_of_birth(btdsviews.getDate_of_birth()); 
	        	tdsentitys.setDate_of_joining(btdsviews.getDate_of_joining());  
	        	tdsentitys.setRecord_date(btdsviews.getRecord_date());  
	        	tdsentitys.setSalary_month(btdsviews.getSalary_month());  
	        	tdsentitys.setBasic_pay(btdsviews.getBasic_pay());  
	        	tdsentitys.setHra(btdsviews.getHra());  
	        	tdsentitys.setSpl_allow(btdsviews.getSpl_allow());  
	        	tdsentitys.setMedi_reimb(btdsviews.getMedi_reimb());  
	        	tdsentitys.setConv_allow(btdsviews.getConv_allow());  
	        	tdsentitys.setLunch_allow(btdsviews.getLunch_allow());  
	        	tdsentitys.setEdu_allow(btdsviews.getEdu_allow());  
	        	tdsentitys.setBuss_attire(btdsviews.getBuss_attire());  
	        	tdsentitys.setCar_maint(btdsviews.getCar_maint());  
	        	tdsentitys.setLeave_travel_allow(btdsviews.getLeave_travel_allow()); 
	        	tdsentitys.setOutstn_allow(btdsviews.getOutstn_allow()); 
	        	tdsentitys.setAnnual_loyal_bonus(btdsviews.getAnnual_loyal_bonus());  
	        	tdsentitys.setOtr_allow(btdsviews.getOtr_allow());  
	        	tdsentitys.setGross_salary(btdsviews.getGross_salary());  
	        	tdsentitys.setSpf(btdsviews.getSpf());  
	        	tdsentitys.setTds(btdsviews.getTds());  
	        	tdsentitys.setProf_tax(btdsviews.getProf_tax());  
	        	tdsentitys.setEsi(btdsviews.getEsi());  
	        	tdsentitys.setRecovery(btdsviews.getRecovery());  
	        	tdsentitys.setOtr_ded(btdsviews.getOtr_ded());  
	        	tdsentitys.setTotal_deductions(btdsviews.getTotal_deductions());  
	        	tdsentitys.setNet_salary(btdsviews.getNet_salary()); 
	        	tdsentitys.setDate_of_pay(btdsviews.getDate_of_pay());  
	        	tdsentitys.setCum_tds_fy(btdsviews.getCum_tds_fy());  
	        	tdsentitys.setProv_it(btdsviews.getProv_it());  
	        	tdsentitys.setTax_due(btdsviews.getTax_due());  
	        	tdsentitys.setTax_per_month(btdsviews.getTax_per_month());  
	        	tdsentitys.setBank_name(btdsviews.getBank_name());  
	        	tdsentitys.setBank_bsr_code(btdsviews.getBank_bsr_code()); 
	        	tdsentitys.setBank_chalan_no(btdsviews.getBank_chalan_no()); 
	        	tdsentitys.setChalan_amt(btdsviews.getChalan_amt());  
	        	tdsentitys.setTds_remit_date(btdsviews.getTds_remit_date());  
	        	tdsentitys.setTds_tran_ref(btdsviews.getTds_tran_ref());  
	        	tdsentitys.setMobile_no(btdsviews.getMobile_no());  
	        	tdsentitys.setEmail_id(btdsviews.getEmail_id());  
	        	tdsentitys.setEntity_flg(btdsviews.getEntity_flg());  
	        	tdsentitys.setDel_flg(btdsviews.getDel_flg());  
	        	tdsentitys.setEntry_time(btdsviews.getEntry_time());  
	        	tdsentitys.setEntry_user(btdsviews.getEntry_user());  
	        	tdsentitys.setModify_flg(btdsviews.getModify_flg());  
	        	tdsentitys.setModify_time(btdsviews.getModify_time());  
	        	tdsentitys.setModify_user(btdsviews.getModify_user());  
	        	tdsentitys.setVerify_time(btdsviews.getVerify_time());  
	        	tdsentitys.setVerify_user(btdsviews.getVerify_user());  
	        	tdsentitys.setRemarks(btdsviews.getRemarks());  
	        	tdsentitys.setAadhar_no(btdsviews.getAadhar_no());  
	        	tdsentitys.setRate_of_tds(btdsviews.getRate_of_tds());
	        	tdsentitys.setUniqueids(tdsentitys.getEmp_no() + dateFormat.format(tdsentitys.getDate_of_pay()));

	        	
	        	

	       
	        	
	           up3.add(tdsentitys);

	           // up3.add(gstoverseas);
	        }

	        System.out.println(up3);
	       //
	        tdsRepos.saveAll(up3);

	        // gstBtmRep.getInsert(b,a);

	        System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a + b);
	        // int uniqueIdCounter = Integer.parseInt(b);
	        // int uniqueIdCounter1 = Integer.parseInt(a);

	       

	            // System.out.println(gstRep.getInsert(a,b));
	            String msg = "Data Saved Successfully";
	            return msg;

	      

	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error: " + e.getMessage();
	    }
	}
	
	
	@RequestMapping(value = "submitaddtds", method = RequestMethod.POST)
	@ResponseBody
	public String submitaddtds(Model md, HttpServletRequest rq, @ModelAttribute tdsentity tdsentity,
			String emp_name ) {

		
		System.out.println("the rating AGENCY>>>> ");
		tdsentity up = tdsentity;
		up.setUniqueids(tdsentity.getEmp_no()+tdsentity.getDate_of_pay());
		System.out.println(tdsentity.getEmp_no()+tdsentity.getDate_of_pay());
		
		System.out.println("hi it is gst here your adding the record for TDS");
		
		tdsRepos.save(up);

		return "added successfully Tds";
	
   
}

	
	@RequestMapping(value = "submitaddtds2", method = RequestMethod.POST)
	@ResponseBody
	public String submitaddtds2(Model md, HttpServletRequest rq, @ModelAttribute tdsentity tdsentity,
			String tran_id) {

		
		System.out.println("the rating AGENCY>>>> ");
		tdsentity up = tdsentity;
		up.setUniqueids(tdsentity.getEmp_no()+tdsentity.getDate_of_pay());
		System.out.println(tdsentity.getEmp_no()+tdsentity.getDate_of_pay());
		
		System.out.println("hi it is gst here your adding the record for TDS");
		
		tdsRepos.save(up);

		return "";
	
   
}

	
	@RequestMapping(value = "tdstab1edit", method = RequestMethod.POST)
	@ResponseBody
	public String tdstab1edit(@ModelAttribute tdsentity tdsentity, String tran_id,@RequestParam(required = false) String uniqueids) throws ParseException {
		String u1=uniqueids;

		tdsentity up = tdsRepos.getlisttab1(u1);
		System.out.println("hi this is btm");
		
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);

		try {
			
			up.setEmp_name(tdsentity.getEmp_name());
			up.setPan_no(tdsentity.getPan_no());
			up.setDate_of_pay(tdsentity.getDate_of_pay());
			up.setNet_salary(tdsentity.getNet_salary());
			up.setRate_of_tds(tdsentity.getTds_tran_ref());
			up.setBank_bsr_code(tdsentity.getBank_bsr_code());
			up.setTds(tdsentity.getTds());
			up.setBank_chalan_no(tdsentity.getBank_chalan_no());
			up.setBank_name(tdsentity.getBank_name());
			up.setChalan_amt(tdsentity.getChalan_amt());
			up.setTds_remit_date(tdsentity.getTds_remit_date());
			up.setTds_tran_ref(tdsentity.getTds_tran_ref());
			
			//up.setUniqueid(Gstoverseas.getUniqueid());
			
			
		    
			tdsRepos.save(up);
			System.out.println("hi this is gst from tds"+tdsentity.getEmp_name());
			System.out.println("hi this is btm"+tdsentity.getDate_of_pay());
			
			// Save the 'up' object with the updated entry_time

		} catch (Exception e) {
		    e.printStackTrace(); // Handle potential errors here, such as ParseException
		}

		return "edited Successfully tdstable1";

	}
	
	
	@RequestMapping(value = "tdstab2edit", method = RequestMethod.POST)
	@ResponseBody
	public String tdstab2edit(@ModelAttribute tdsentity tdsentity, String tran_id,@RequestParam(required = false) String uniqueids) throws ParseException {
		String u1=uniqueids;

		tdsentity up = tdsRepos.getlisttab1(u1);
		System.out.println("hi this is btm");
		
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);

		try {
			
			up.setEmp_name(tdsentity.getEmp_name());
			up.setPan_no(tdsentity.getPan_no());
			up.setDate_of_pay(tdsentity.getDate_of_pay());
			up.setNet_salary(tdsentity.getNet_salary());
			up.setRate_of_tds(tdsentity.getTds_tran_ref());
			up.setBank_bsr_code(tdsentity.getBank_bsr_code());
			up.setTds(tdsentity.getTds());
			up.setBank_chalan_no(tdsentity.getBank_chalan_no());
			up.setBank_name(tdsentity.getBank_name());
			up.setChalan_amt(tdsentity.getChalan_amt());
			up.setTds_remit_date(tdsentity.getTds_remit_date());
			up.setTds_tran_ref(tdsentity.getTds_tran_ref());
			
			//up.setUniqueid(Gstoverseas.getUniqueid());
			
			
		    
			tdsRepos.save(up);
			System.out.println("hi this is gst from tds"+tdsentity.getEmp_name());
			System.out.println("hi this is btm"+tdsentity.getDate_of_pay());
			
			// Save the 'up' object with the updated entry_time

		} catch (Exception e) {
		    e.printStackTrace(); // Handle potential errors here, such as ParseException
		}

		return "edited Successfully tdstable2";

	}
	
	
	@RequestMapping(value = "deletetds", method = RequestMethod.POST)
	@ResponseBody
	public String deletetds(Model md, HttpServletRequest rq, @ModelAttribute tdsentity tdsentity,
			String tran_id,@RequestParam(required = false) String uniqueid) {

		
		System.out.println(uniqueid);
		tdsentity up = tdsRepos.delete1(uniqueid);
		
		
		
		

		System.out.println("hi it is gst here your adding the record for india");
		System.out.println("hi it is gst here your adding the record "+tdsentity.getUniqueids());

		tdsRepos.delete(up);

		return "deleted successfully";
	
   
}
	
	
	
	
/*	@RequestMapping(value = "/tdsexceldownload", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<InputStreamResource> tdsexceldownload(
	        HttpServletResponse response,
	        @RequestParam(required = false) String filetype,
	        @RequestParam(required = false) String moths,
	        @RequestParam(required = false) String years) throws IOException, SQLException, JRException {

	    // Set the response content type
	    response.setContentType("application/octet-stream");

	    String YearL=null;
		if(moths.equals("01")) {
			YearL="Jan";
		}else if(moths.equals("02")) {
			YearL="Feb";
		}else if(moths.equals("02")) {
			YearL="Feb";
		}else if(moths.equals("03")) {
			YearL="Mar";
		}else if(moths.equals("04")) {
			YearL="Apr";
		}else if(moths.equals("05")) {
			YearL="May";
		}else if(moths.equals("06")) {
			YearL="Jun";
		}else if(moths.equals("07")) {
			YearL="Jul";
		}else if(moths.equals("08")) {
			YearL="Aug";
		}else if(moths.equals("09")) {
			YearL="Sep";
		}else if(moths.equals("10")) {
			YearL="Oct";
		}else if(moths.equals("11")) {
			YearL="Nov";
		}else if(moths.equals("12")) {
			YearL="Dec";
		}
	    // Construct the file name
	    String fileName = "GST_" + YearL + "-" + years + ".xlsx";

	    // Get the file from the service
	    File eclFile = placementServices.gettdsexcel(fileName,filetype, moths, years);

	    // Prepare the InputStreamResource
	    InputStreamResource resource = new InputStreamResource(new FileInputStream(eclFile));

	    // Prepare the response headers
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

	    // Return the ResponseEntity with InputStreamResource and headers
	    return ResponseEntity.ok()
	            .headers(headers)
	            .contentLength(eclFile.length())
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(resource);
	}*/
	  @RequestMapping(
		      value = {"/tdsexceldownload"},
		      method = {RequestMethod.GET},
		      produces = {"application/octet-stream"}
		   )
		   @ResponseBody
		   public ResponseEntity<InputStreamResource> tdsexceldownload(HttpServletResponse response, @RequestParam(required = false) String filetype, @RequestParam(required = false) String moths, @RequestParam(required = false) String years) throws IOException, SQLException, JRException {
		      response.setContentType("application/octet-stream");
		      String YearL = null;
		      if (moths.equals("01")) {
		         YearL = "Jan";
		      } else if (moths.equals("02")) {
		         YearL = "Feb";
		      } else if (moths.equals("02")) {
		         YearL = "Feb";
		      } else if (moths.equals("03")) {
		         YearL = "Mar";
		      } else if (moths.equals("04")) {
		         YearL = "Apr";
		      } else if (moths.equals("05")) {
		         YearL = "May";
		      } else if (moths.equals("06")) {
		         YearL = "Jun";
		      } else if (moths.equals("07")) {
		         YearL = "Jul";
		      } else if (moths.equals("08")) {
		         YearL = "Aug";
		      } else if (moths.equals("09")) {
		         YearL = "Sep";
		      } else if (moths.equals("10")) {
		         YearL = "Oct";
		      } else if (moths.equals("11")) {
		         YearL = "Nov";
		      } else if (moths.equals("12")) {
		         YearL = "Dec";
		      }

		      String fileName = "TDS" + YearL + "-" + years + ".xlsx";
		      File eclFile = this.placementServices.gettdsexcel(fileName, filetype, moths, years);
		      InputStreamResource resource = new InputStreamResource(new FileInputStream(eclFile));
		      HttpHeaders headers = new HttpHeaders();
		      headers.add("Content-Disposition", "attachment; filename=" + fileName);
		      return ((BodyBuilder)ResponseEntity.ok().headers(headers)).contentLength(eclFile.length()).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
		   }
	  
	  
	  @RequestMapping(value = "submitdelete", method = RequestMethod.POST)
		@ResponseBody
		public String submitaddtds(Model md, HttpServletRequest rq, @ModelAttribute BTMAdminAssociateProfile BTMAdminAssociateProfile,
				@RequestParam(required = false) String  resId) {

			
			System.out.println("fghjkl"+BTMAdminAssociateProfile.getDel_flg());
			BTMAdminAssociateProfile up = btmAdminAssociateProfileRep.delete2(resId);
			System.out.println(resId);
			System.out.println( up);
			up.setDel_flg("Y");
			
			btmAdminAssociateProfileRep.save(up);

			return "deleted successfully";
		
	   
	}
	  
	
	 /* @RequestMapping(value = "sendSmsss", method = RequestMethod.POST)
	    @ResponseBody
	    public String sendSms(Model md, HttpServletRequest rq) {
	        System.out.println("hihihihihihihihihihihkihihhhhhhhhhhhhhhhhhhhhh");

	        String url = "https://api.smslane.com/api/v2/SendSMS";
	        String senderId = "BOFIRE"; // Verify sender ID with the service provider
	        String message = "Hi Employee, Please Mark Attendance through BTM Application for the day. It is MANDATORY. \n Thanks and Regards, Bornfire Innovation Private Limited.";
	        String mobileNumber = "9384374949";
	        String templateId = "1707170806443753132"; // Verify template ID with the service provider
	        String apiKey = "Bornfire2017"; // Verify API key with the service provider
	        String clientId = "siddhaiyan@bornfire.in";

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        // Construct the request body
	        String requestBody = String.format("{\"SenderId\":\"%s\",\"Message\":\"%s\",\"MobileNumbers\":\"%s\",\"TemplateId\":\"%s\",\"ApiKey\":\"%s\",\"ClientId\":\"%s\"}",
	            senderId, message, mobileNumber, templateId, apiKey, clientId);

	        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

	        RestTemplate restTemplate = new RestTemplate();
	        String response = restTemplate.postForObject(url,"nnjnnmn"+request, String.class);

	        return response;
	    }*/
	  
	  @RequestMapping(value = "/sendsms", method = RequestMethod.POST)
	    @ResponseBody
	    public String sendSms(
	            @RequestParam(required = false) String SenderId,
	            @RequestParam(required = false) String Message,
	            @RequestParam(required = false) String MobileNumbers,
	            @RequestParam(required = false) String TemplateId,
	            @RequestParam(required = false) String ApiKey,
	            @RequestParam(required = false) String ClientId,
	            @RequestParam(required = false) String oneto
	    ) {
	        // Build the URL with proper encoding
		  UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.smslane.com/api/v2/SendSMS")
		            .queryParam("SenderId", SenderId)
		            .queryParam("Message", Message)
		            .queryParam("MobileNumbers", MobileNumbers)
		            .queryParam("TemplateId", TemplateId)
		            .queryParam("ApiKey", ApiKey)
		            .queryParam("ClientId", ClientId);

		    // Create a RestTemplate instance to make HTTP requests
		    RestTemplate restTemplate = new RestTemplate();

		    // Send the request and get the response
		    String response = restTemplate.getForObject(builder.toUriString()+"hhhhhhhhhh", String.class);
System.out.println(response);
		    return response;
		}
	  @RequestMapping(value = "valuesending", method = RequestMethod.POST)
		@ResponseBody
		public String valuesending(Model md, HttpServletRequest rq, @ModelAttribute BTMAdminAssociateProfile BTMAdminAssociateProfile ,
				
				 @RequestParam(required = false) List<String> encodedNumbers,
				 @RequestParam(required = false) List<String> demonumber,
	  @RequestParam(required = false) String l,
      @RequestParam(required = false) String v,
      @RequestParam(required = false) String s,
      @RequestParam(required = false) String k,
      @RequestParam(required = false) String i,
      @RequestParam(required = false) String t) {

  try {
	  
	  List<String> b=encodedNumbers;
	
	  encodedNumbers.add("8610708934");
	  encodedNumbers.add("9486540575");
	
      String encodedSender = URLEncoder.encode(l, "UTF-8");
      String encodedMessage=v;
      String encodedMobileNumbers = URLEncoder.encode(s, "UTF-8");
      String encodedTemplateId = URLEncoder.encode(k, "UTF-8");
      String encodedApiKey = URLEncoder.encode(i, "UTF-8");
      String encodedClientId = URLEncoder.encode(t, "UTF-8");
      
      for(String m:b) {
		  try {
  	        String apiUrl = "https://api.smslane.com/api/v2/SendSMS";
  	        String constructedUrl = apiUrl + "?SenderId=" + encodedSender + "&Message=" + v +
  	       		 "&MobileNumbers=" +"91"+m + "&TemplateId=" + encodedTemplateId
  	    		  + "&ApiKey=" + encodedApiKey + "&ClientId=" + encodedClientId;

  	        URL url = new URL(constructedUrl);
  	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
  	        connection.setRequestMethod("GET");
  	        
  	        // Reading the response
  	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
  	        String inputLine;
  	        StringBuilder response = new StringBuilder();

  	        while ((inputLine = in.readLine()) != null) {
  	            response.append(inputLine);
  	        }
  	        in.close();
  	        System.out.println(response.toString());
  	        connection.disconnect();
  	    } catch (IOException e) {
  	        e.printStackTrace(); 
  	    }
	  }
      return "Sent Successfully";
  } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      return "Error constructing URL";
  }
}
	  
	  @RequestMapping(value = "send", method = RequestMethod.POST)
	    @ResponseBody
	    public String sendSMS(@RequestParam String url) {
		  System.out.println("hhhhvvvvvvvvvvvvvvvv"+url);
		  
	        // Assuming you're passing the constructed URL containing SMS details as a query parameter 'url'
	        // Here you can perform additional validation on the request or handle the SMS sending logic
	        
	        // For simplicity, I'll just make a GET request to the provided URL
		  String dates=url;
	       // RestTemplate restTemplate = new RestTemplate();
	      //  String response = restTemplate.getForObject(apiUrl, String.class);
	        
	        // You can return the response received from the SMS sending service or any other relevant message
	        return dates ;
	    
		}

		@RequestMapping(value = "onetwo", method = RequestMethod.POST)
		@ResponseBody
		public List<String> onetwo(Model md, HttpServletRequest rq,
				@ModelAttribute BTMAdminAssociateProfile BTMAdminAssociateProfile,
				@RequestParam(required = false) String p, @RequestParam(required = false) String q,
				@RequestParam(required = false) String r, @RequestParam(required = false) String url) {
			System.out.println("KKKKK" + p);
		//	md.addAttribute("sms", AttendanceRegisterGetRep.getsms(p, q, r));
			//List<String> smsList = AttendanceRegisterGetRep.getsms(p, q, r); // Assuming getsms() returns a
			System.out.println(resourceMasterRepo.smssenmding(p, q, r));
			List<String> smsList = resourceMasterRepo.smssenmding(p, q, r);
																				// List<String>
			md.addAttribute("smss", smsList);
			//System.out.println(AttendanceRegisterGetRep.getsms(p, q, r));
			
			System.out.println(resourceMasterRepo.smssenmding(p, q, r));
			System.out.println(smsList);

		
			return resourceMasterRepo.smssenmding(p, q, r);

		}
		
		@RequestMapping(value = "salarystructures", method = { RequestMethod.GET, RequestMethod.POST })
		public String salarystructures(@RequestParam(required = false) String formmode,
				@RequestParam(required = false) String emp_no, @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date ctc_eff_date,
				@RequestParam(required = false) String emp_no1,String keyword, Model md, HttpServletRequest req)

		{
			String userId = (String) req.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");
			
			if (formmode == null || formmode.equals("list")) {
				md.addAttribute("formmode", "list");
				md.addAttribute("salarypay", salary_Pay_Rep.getList());
			} else if (formmode.equals("add")) {
				System.out.println(emp_no1);
				md.addAttribute("salarypay", salary_Pay_Rep.getsalfromcvf(emp_no));
			
				md.addAttribute("formmode", "add");
				
				md.addAttribute("empty", ""); 
				md.addAttribute("resmasterlist",resourceMasterRepo.getalist());
				
				 //md.addAttribute("empty", ""); 
				//md.addAttribute("resmasterlist",salary_Pay_Rep.getalist());
				 

			} else if (formmode.equals("edit")) {
				md.addAttribute("formmode", "edit");
				md.addAttribute("salarypay", salary_Pay_Rep.getaedit1(emp_no,ctc_eff_date));
			} else if (formmode.equals("view")) {
				md.addAttribute("formmode", "view");
				md.addAttribute("salarypay", salary_Pay_Rep.getaedit1(emp_no,ctc_eff_date));
			} else if (formmode.equals("enquiry")) {
				md.addAttribute("formmode", "enquiry");
				md.addAttribute("salarypay", salary_Pay_Rep.getaedit1(emp_no,ctc_eff_date));
			} else if (formmode.equals("revision")) {
				md.addAttribute("formmode", "revision");
				md.addAttribute("salarypay", salary_Pay_Rep.getaedit1(emp_no,ctc_eff_date));
			}
			return "salarystructures";
		}

		@RequestMapping(value = "AddScreen", method = RequestMethod.POST)

		@ResponseBody
		public String AddScreen(Model md, HttpServletRequest rq,

				@ModelAttribute Salary_Pay_Entity salary_Pay_Entity) {
			System.out.println(salary_Pay_Entity+"gggggggggggggggggggggggggggg");
			System.out.println(salary_Pay_Entity.getCtc_amt()+"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");

			Salary_Pay_Entity up = salary_Pay_Entity;
			up.setDel_flg("N");
			up.setEntity_flg("Y");
			up.setModify_flg("N");
			salary_Pay_Rep.save(up);
			System.out.println(up+"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
			return "Saved Successfully";
		}

		@RequestMapping(value = "modifyscreen", method = RequestMethod.POST)

		@ResponseBody
		public String modifyscreens(@RequestParam(required = false) String emp_no, @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date ctc_eff_date, Model md, HttpServletRequest rq,
				@ModelAttribute Salary_Pay_Entity salary_Pay_Entity) {
			String msg = "";
			Salary_Pay_Entity up = salary_Pay_Entity;
			if (Objects.nonNull(up)) {
				up = salary_Pay_Entity;
				up.setDel_flg("N");
				up.setEntity_flg("Y");
				up.setModify_flg("N");
				salary_Pay_Rep.save(up);
				msg = "Modify Successfully";
			} else {
				msg = "Data Not Found";
			}
			return msg;
		}
		
		@RequestMapping(value = "revisionscreen", method = RequestMethod.POST)

		@ResponseBody
		public String revisionscreen(@RequestParam(required = false) String emp_no, Model md, HttpServletRequest rq, 
				@ModelAttribute Salary_Pay_Entity salary_Pay_Entity) {
			String msg = "";
			Salary_Pay_Entity up = salary_Pay_Entity;
			System.out.println("load...");
			if (Objects.nonNull(up)) {
				up.setDel_flg("N");
				up.setEntity_flg("Y");
				up.setModify_flg("N");
				salary_Pay_Rep.save(up);
				msg = "Revised Successfully";
			} else {
				msg = "Data Not Found";
			}
			return msg;
		}

		@RequestMapping(value = "deletescreen", method = RequestMethod.POST)
		@ResponseBody
		public String deletescreen(@RequestParam(required = false) String emp_no, @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date ctc_eff_date) {
			String msg = null;
			try {
				Salary_Pay_Entity vv = salary_Pay_Rep.getaedit1(emp_no,ctc_eff_date );
				vv.setDel_flg("Y");
				salary_Pay_Rep.delete(vv);
				msg = "Deleted Successfully";
			} catch (Exception e) {
				msg = "Delete Unsuccessfull";
			}
			return msg;
		}

		@GetMapping("paystructures")
		public String paystructures(@RequestParam(required = false) String formmode,
				@RequestParam(required = false) String emp_no,
				@RequestParam(required = false) String salaryMonth,
				@RequestParam(required = false) String empname,String keyword, Model md, HttpServletRequest req)

		{
			String userId = (String) req.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");
			
			if (formmode == null || formmode.equals("list")) {
				md.addAttribute("formmode", "list");
				md.addAttribute("salarypay", Paystructurerep.getpay());
			} else if (formmode.equals("add")) {
				md.addAttribute("formmode", "add");
			} else if (formmode.equals("edit")) {
				md.addAttribute("formmode", "edit");
				//md.addAttribute("salarypay", Paystructurerep.getaedit(emp_no));
				md.addAttribute("salarypay", Paystructurerep.getpaystructureedit(emp_no,salaryMonth,empname));
			} else if (formmode.equals("view")) {
				md.addAttribute("formmode", "view");
				md.addAttribute("salarypay", Paystructurerep.getaedit(emp_no));
			} else if (formmode.equals("verify")) {
				md.addAttribute("formmode", "verify");
				md.addAttribute("salarypay", Paystructurerep.getaedit(emp_no));
			}else if (formmode.equals("enquiry")) {
				md.addAttribute("formmode", "enquiry");
				md.addAttribute("salarypay", Paystructurerep.getpaystructureedit(emp_no,salaryMonth,empname));
			}
			return "paystructure";
		}

		@RequestMapping(value = "nationalvalues", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public List<String> nationalvalues(@RequestParam(required = false) String nationalvalue) {
		
			List<String> group = salary_parameter_Rep.getDistinctCountries(nationalvalue);
			System.out.println("size :"+group.size());

			
		return group;
		}
		@RequestMapping(value = "nationalvaluesone", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public int  nationalvaluesone(@RequestParam(required = false) String nationalvalue,Model md) {
			System.out.println("value :"+nationalvalue);
			List<String> group = salary_parameter_Rep.getDistinctCountries(nationalvalue);
			System.out.println("size :"+group.size());
			md.addAttribute("groups", group);

			return group.size();		
		}
		@RequestMapping(value = "getctcdatas", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public salary_parameter getctcdatas(@RequestParam(required = false) String selectedgroup) {
		
			salary_parameter group = salary_parameter_Rep.getgroup(selectedgroup);
			System.out.println("size :"+group.getCtc_cost_to_company());

			
		return group;
		}
	
		@RequestMapping(value = "AddScreen1", method = RequestMethod.POST)
		@ResponseBody
		public String AddScreen1(Model md, HttpServletRequest rq,

				@ModelAttribute paystructureentity Paystructureentity) {

			paystructureentity up = Paystructureentity;
			up.setDel_flg("N");
			up.setEntity_flg("N");
			up.setModify_flg("N");
			Paystructurerep.save(up);
			return "Saved Successfully";
		}

	

		@RequestMapping(value = "modifyscreen1", method = RequestMethod.POST)
		@ResponseBody
		public String modifyscreens1(@RequestParam(required = false) String formMode, 
				@RequestParam(required = false) String sal,
				@RequestParam(required = false) String name,Model md, HttpServletRequest rq,
				@ModelAttribute paystructureentity paystructureentity) {
			String msg = "";


		    try {
		        Optional<paystructureentity> up1 = Paystructurerep.getoptional(formMode);
		        if (up1.isPresent()) {
		            paystructureentity up = up1.get();
		            up = paystructureentity;
		            up.setDel_flg("N");
		            up.setEntity_flg("N");
		            up.setModify_flg("N");
		            Paystructurerep.save(up);
		            msg = "Modify Successfully";
		        } else {
		            msg = "Data Not Found";
		        }
		    } catch (Exception e) {
		        msg = "Error occurred while modifying the data";
		    }
			return msg;
		}

		@RequestMapping(value = "deletescreen1", method = RequestMethod.GET)
		@ResponseBody
		public String deletescreen1(@RequestParam(required = false) String emp_no) {
			String msg = "";
			try {
				paystructureentity vv = Paystructurerep.getid(emp_no);
				vv.setDel_flg("Y");
				Paystructurerep.save(vv);
				System.out.println("enter 1 ");
				msg = "Deleted Successfully";
			} catch (Exception e) {
				msg = "Delete Unsuccessfull"+e;
			}
			return msg;
		}

		

		@RequestMapping(value = "/cvfsubmit", method = RequestMethod.POST)
		@ResponseBody
		public String cvfsubmit(@RequestParam(required = false) String ref_no, Model md, HttpServletRequest rq,
				@ModelAttribute CandEvalFormEntity candEvalFormEntity) {

			//System.out.println("hi" + ref_no);
			//System.out.println("hi" + candEvalFormEntity.getCandi_name());

			CandEvalFormEntity up = candEvalFormEntity;

			up.setVerify_flg("N");

			candEvalFormRep.save(up);

			return "success";

		}

		@RequestMapping(value = "/cvfdelete", method = RequestMethod.POST)
		@ResponseBody
		public String cvfdelete(@RequestParam(required = false) String ref_no, Model md, HttpServletRequest rq) {

			CandEvalFormEntity up = candEvalFormRep.getCVFform(ref_no);
			candEvalFormRep.delete(up);

			return "Deleted successfully";

		}
		
		byte[] setvalue1;
		byte[] setImgValue1;
		
		@RequestMapping(value = "cvffileupload", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public String cvffileupload(@RequestParam("file") MultipartFile file,
				CandEvalFormEntity candEvalFormEntity) throws IOException {
			// Call service layer to handle file uploads and form data
			// Print the value of 'fileData' to the console

			// System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());

			
			byte[] byteArray = file.getBytes();
			this.setvalue1 = byteArray;

			return "success"; // Redirect to upload page after upload
		}
		
		@RequestMapping(value = "cvffileupload1", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public String cvffileupload1(CandEvalFormEntity candEvalFormEntity) {
			// Call service layer to handle file uploads and form data
			// Print the value of 'fileData' to the console

			//System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());
			candEvalFormEntity.setAnnexure_resume(setvalue1);
			//candEvalFormEntity.setDoc_image(setImgValue);
			candEvalFormEntity.setVerify_flg("N");
			candEvalFormRep.save(candEvalFormEntity);
			return "success"; // Redirect to upload page after upload
		}
		

		@RequestMapping(value = "/cvfverifysubmit/{b}", method = RequestMethod.POST)
		@ResponseBody
		public String cvfverifysubmit(@RequestParam(required = false) String ref_no, Model md, HttpServletRequest rq,
				@PathVariable String b, @ModelAttribute CandEvalFormEntity candEvalFormEntity) {

			
			CandEvalFormEntity up = candEvalFormRep.getCVFform(b);

			System.out.println("verify" + up.getRef_no());
			System.out.println("verify" + up.getAnnexure_resume());
			
			
			up.setVerify_flg("Y");	

			candEvalFormRep.save(up);

			return "success";

		}	
		
		@RequestMapping(value = "/cvfmodifysubmit1/{b}", method = RequestMethod.POST)
		@ResponseBody
		public String cvfmodifysubmit1(@RequestParam(required = false) String ref_no, Model md, HttpServletRequest rq,
				@PathVariable String b, @ModelAttribute CandEvalFormEntity candEvalFormEntity) {

			candEvalFormEntity.setRef_no(b);
			
			CandEvalFormEntity up1 = candEvalFormRep.getCVFform(b);
			candEvalFormEntity.setAnnexure_resume(up1.getAnnexure_resume());
			
			CandEvalFormEntity up = candEvalFormEntity;
			up.getCandi_name();
			System.out.println(up.getCandi_name());
			System.out.println(up.getAnnexure_resume());

			up.setVerify_flg("N");	
			up.setAnnexure_resume(setvalue1);
			candEvalFormRep.save(up);

			return "success";

		}

			
	
		@RequestMapping(value = "/cvfmodifysubmit2/{ref_no}", method = RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<ByteArrayResource> cvfmodifysubmit2(@PathVariable String ref_no) {
		    CandEvalFormEntity entity = candEvalFormRep.getCVFform(ref_no);
		    
		    if (entity != null && entity.getAnnexure_resume() != null) {
		        byte[] blobData = entity.getAnnexure_resume();
		        System.out.println(blobData);
		        
		        // Set response headers
		        HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.APPLICATION_PDF);
		        headers.setContentDispositionFormData("attachment", "annexure_resume.pdf");
		        
		        // Create a ByteArrayResource from the blob data
		        ByteArrayResource resource = new ByteArrayResource(blobData);
		        
		        // Return ResponseEntity with the PDF blob data
		        return ResponseEntity.ok()
		                .headers(headers)
		                .contentLength(blobData.length)
		                .body(resource);
		    } else {
		        // Return appropriate response if the entity or blob data is not found
		        return ResponseEntity.notFound().build();
		    }
		}

		
		


		
	

		@RequestMapping(value = "cvf", method = { RequestMethod.GET, RequestMethod.POST })
		public String cvf(@RequestParam(required = false) String formmode, @RequestParam(required = false) String ref_no,
				Model md, HttpServletRequest rq) {

			String userId = (String) rq.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");
			
			if (formmode == null || formmode.equals("list")) {

				md.addAttribute("del", candEvalFormRep.getCVFList());
				md.addAttribute("formmode", "list");

			} else if (formmode.equals("add")) {
				md.addAttribute("formmode", "add");

			} else if (formmode.equals("verify")) {

				md.addAttribute("cvfview", candEvalFormRep.getCVFform(ref_no));
				md.addAttribute("formmode", "verify");

			} else if (formmode.equals("view")) {

				md.addAttribute("cvfview", candEvalFormRep.getCVFform(ref_no));
				md.addAttribute("formmode", "view");

			} else if (formmode.equals("modify")) {

				md.addAttribute("cvfview", candEvalFormRep.getCVFform(ref_no));
				md.addAttribute("formmode", "modify");
			}
			return "CandidateEvaluationForm";
		}


		@RequestMapping(value = "offerLetter", method = { RequestMethod.GET, RequestMethod.POST })
		public String offerLetter(@RequestParam(required = false) String formmode,
				@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
				@ModelAttribute CandEvalFormEntity candEvalFormEntity, @RequestParam(required = false) String a,
				@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
			// md.addAttribute("IssueMaster", issueMasterRep.findAllCustom());

			String userId = (String) req.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");
			
			md.addAttribute("empty", "");
			md.addAttribute("kkkk", a);
			md.addAttribute("cvfverify", candEvalFormRep.getCVFListoffer());

			System.out.println(a);
			md.addAttribute("cvfverifys", candEvalFormRep.getCVFforms(a));

			return "OfferLetter";
		}
		
		@RequestMapping(value = "SalaryRevision", method = { RequestMethod.GET, RequestMethod.POST })
		public String SalaryRevision( @RequestParam(required = false) String userid, @RequestParam(required = false) String formmode,
				Model md, HttpServletRequest req, @RequestParam(required = false) String emp_no,  @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date ctc_eff_date) {
			// md.addAttribute("IssueMaster", issueMasterRep.findAllCustom());

			String userId = (String) req.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");
			
			if (formmode == null || formmode.equals("list")) {
				md.addAttribute("formmode", "list");
				md.addAttribute("getct", salary_Pay_Rep.getList());
				

			}else if (formmode.equals("ctc")) {
				md.addAttribute("formmode", "ctc");
				md.addAttribute("getctc1", salary_Pay_Rep.getaedit1(emp_no, ctc_eff_date));
				

			}
			       	
			return "SalaryRevision";
		}
		
		@RequestMapping(value = "SalaryRevisiondownload", method = RequestMethod.GET)
		@ResponseBody
		public InputStreamResource SalaryRevisiondownload(HttpServletResponse response,
				@RequestParam(value = "emp_no", required = false) String emp_no, @RequestParam(value = "ctc_eff_date", required = false) String ctc_eff_date,
				@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

			response.setContentType("application/octet-stream");

			InputStreamResource resource = null;
			System.out.println(emp_no);
			System.out.println(ctc_eff_date);
			try {
				logger.info("Getting download File :" + emp_no + ", FileType :" + filetype + "");

				File repfile = projectMasterServices.getctcpdf(emp_no,ctc_eff_date, filetype);

				response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
				resource = new InputStreamResource(new FileInputStream(repfile));
			} catch (JRException e) {
				// Log the error using the logger
				logger.error("Error generating TDS file", e);
				// Optionally, rethrow the exception or handle it as needed
				// throw new YourCustomException("Error generating TDS file", e);
			}

			return resource;
		}
		
		byte[] setctc;
		byte[] setctc1;
		@RequestMapping(value = "fileuploadrevision", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public String fileuploadrevision(@RequestParam("file") MultipartFile file,@RequestParam("file1") MultipartFile file1,@RequestParam(required = false) String emp,
				Salary_Pay_Entity salary_Pay_Entity) throws IOException {
			
			byte[] byteArray = file.getBytes();
			this.setctc = byteArray;
			byte[] byteArrays = file1.getBytes();
			this.setctc1 = byteArrays;

			return "success"; 
		}
		
		@RequestMapping(value = "fileuploadrevisionsave", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public String fileuploadrevisionsave(@RequestParam(required = false) String emp_no,@RequestParam(required = false) String ctc,
				
				Salary_Pay_Entity salary_Pay_Entity) throws ParseException {
			
			
			Salary_Pay_Entity up=salary_Pay_Rep.savectc(emp_no , ctc);
			
			up.setStr(setctc);
			up.setRevision(setctc1);
			
			
			salary_Pay_Rep.save(up);
			return "success"; 
		}
		
		
		
		@RequestMapping(value = "sendingmail_coveringletter", method = { RequestMethod.GET, RequestMethod.POST })
		public ResponseEntity<String> sendingmail_coveringletter(@RequestParam(required = false) String a, @RequestParam(required = false) String cc,
				@RequestParam(required = false) String d,@RequestParam(required = false) String ctc, Model md)
				throws SQLException, ParseException, IOException {
			System.out.println("Hi");
			String b = a;
			String to = b;
			
			String from = "HR@bornfire.in";
			String username = "HR@bornfire.in"; // change accordingly
			String password = "VNivas@636003"; // change accordingly
			String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
			String emp_no = d;
		
			System.out.println("sdfghjkl;");

			
			// Call sendMail method with correct parameters
			sendingmail_coveringletter.sendingctcmail(from, host, to, cc, username, password, emp_no, ctc); // pass from, host,
																									// password, and to

			// Return success response
			return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
		}
		

		@RequestMapping(value = "Appointment_Letter", method = { RequestMethod.GET, RequestMethod.POST })
		public String Appointment_Letter(@RequestParam(required = false) String formmode,
				@RequestParam(required = false) String userid, @RequestParam(required = false) String c,
				@RequestParam(required = false) String a, @RequestParam(required = false) String ref_no,
				CandEvalFormEntity candEvalFormEntity,
				@RequestParam(value = "page", required = false) Optional<Integer> page,
				@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

			String userId = (String) req.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");
			
			md.addAttribute("menu", "projectmaster"); // To highlight the menu

			if (formmode == null || formmode.equals("list")) {

				
				System.out.println(a);

				md.addAttribute("cvfverifys", candEvalFormRep.getCVFforms(a));
				System.out.println(candEvalFormRep.getCVFforms(a));
				md.addAttribute("kkkk", a);
				md.addAttribute("cvfverify", candEvalFormRep.getCVFListapp());
				md.addAttribute("empty", "");
				md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

			} else if (formmode.equals("edit")) {

				md.addAttribute("formmode", "edit");
				// md.addAttribute("formmode", "add"); // to set which form - valid values are
				// "edit" , "add" & "list"

				// md.addAttribute("domains", userProfileDao.getDomainList());
				// md.addAttribute("projectmaster", userProfileDao.getUser(userid));

			} else if (formmode.equals("view")) {

				md.addAttribute("formmode", "view");

			} else if (formmode.equals("add")) {

				md.addAttribute("formmode", "add");

				// md.addAttribute("domains", userProfileDao.getDomainList());
				// md.addAttribute("projectmaster", userProfileDao.getUser(userid));

			} else {

				md.addAttribute("formmode", formmode);
				// md.addAttribute("domains", reportServices.getDomainList());
				// md.addAttribute("FinUserProfiles", userProfileDao.getFinUsersList());
				// md.addAttribute("projectmaster", userProfileDao.getUser(""));

			}

			return "Appointment_Letter";
		}
		

		@RequestMapping(value = "sendingmail_appointment", method = { RequestMethod.GET, RequestMethod.POST })
		public ResponseEntity<String> sendMails(@RequestParam(required = false) String a, @RequestParam(required = false) String cc,
				@RequestParam(required = false) String d, @RequestParam(required = false) List<String> t, Model md)
				throws SQLException, ParseException {
			System.out.println("Hi");
			String b = a;
			String to = b;
			String from = "valarmathi.s@bornfire.in";
			String username = "valarmathi.s@bornfire.in"; // change accordingly
			String password = "Bornfire@123"; // change accordingly
			String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
			String ref_no = d;
			/*List<String> y = t;
			for (String bb : y) {
				System.out.println(bb);

			}*/

			System.out.println("sdfghjkl;");

			// Call sendMail method with correct parameters
			sendingmail_appointment.sendingmail(from, host, to, cc, username, password, ref_no); // pass from, host,
																								// password, and to

			// Return success response
			return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
		}


		@RequestMapping(value = "Appointmentdownload", method = RequestMethod.GET)
		@ResponseBody
		public InputStreamResource Appointmentdownload(HttpServletResponse response,
				@RequestParam(value = "a", required = false) String a,
				@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

			response.setContentType("application/octet-stream");

			InputStreamResource resource = null;
			System.out.println(a);
			try {
				logger.info("Getting download File :" + a + ", FileType :" + filetype + "");

				File repfile = projectMasterServices.getTdsExcel(a, filetype);

				response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
				resource = new InputStreamResource(new FileInputStream(repfile));
			} catch (JRException e) {
				// Log the error using the logger
				logger.error("Error generating TDS file", e);
				// Optionally, rethrow the exception or handle it as needed
				// throw new YourCustomException("Error generating TDS file", e);
			}

			return resource;
		}

		
		//private static final Logger logger = LoggerFactory.getLogger(NavigationController.class);

		/*@RequestMapping(value = "/AppointmentdownloadSSSSS", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
		@ResponseBody
		public InputStreamResource invoiceReportDownloadsww(HttpServletResponse response,
		        @RequestParam(value = "a", required = false) String a,
		        @RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

		    response.setContentType("application/octet-stream");

		    InputStreamResource resource = null;
		    try {
		        logger.info("Getting download File :" + a + ", FileType :" + filetype + "");

		      //  File repfile = projectMasterServices.getappointment(a, filetype);

		        response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
		        resource = new InputStreamResource(new FileInputStream(repfile));
		    } catch (FileNotFoundException e) {
		        logger.error("File not found", e);
		        // Handle the exception appropriately, such as returning an error response
		        response.setStatus(HttpServletResponse.SC_NOT_FOUND); // Set HTTP 404 Not Found status
		    } catch (JRException e) {
		        logger.error("Error generating appointment file", e);
		        // Handle the exception appropriately, such as returning an error response
		        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Set HTTP 500 Internal Server Error status
		    }

		    return resource;
		}*/

		@RequestMapping(value = "offerLetterss", method = RequestMethod.GET)
		@ResponseBody
		public InputStreamResource invoiceReportDownloads(HttpServletResponse response,
				@RequestParam(value = "a", required = false) String a,
				@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

			response.setContentType("application/octet-stream");

			InputStreamResource resource = null;
			System.out.println("GGGGGGG" + a);
			try {
				logger.info("Getting download File :" + a + ", FileType :" + filetype + "");

				File repfile = projectMasterServices.getofferExcel(a, filetype);

				response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
				resource = new InputStreamResource(new FileInputStream(repfile));
			} catch (JRException e) {
				// Log the error using the logger
				logger.error("Error generating TDS file", e);
				// Optionally, rethrow the exception or handle it as needed
				// throw new YourCustomException("Error generating TDS file", e);
			}

			return resource;
		}
		
	
		
		

		@RequestMapping(value = "salarystructuredownload", method = RequestMethod.GET)
		@ResponseBody
		public InputStreamResource invoiceReportDownload1(HttpServletResponse response,
				@RequestParam(value = "a", required = false) String a, @RequestParam(value = "ctc_eff_date", required = false) String ctc_eff_date,
				@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

			response.setContentType("application/octet-stream");

			InputStreamResource resource = null;
			System.out.println(a);
			System.out.println(ctc_eff_date);
			try {
				logger.info("Getting download File :" + a + ", FileType :" + filetype + "");

				File repfile = projectMasterServices.getsalExcel(a,ctc_eff_date, filetype);

				response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
				resource = new InputStreamResource(new FileInputStream(repfile));
			} catch (JRException e) {
				// Log the error using the logger
				logger.error("Error generating TDS file", e);
				// Optionally, rethrow the exception or handle it as needed
				// throw new YourCustomException("Error generating TDS file", e);
			}

			return resource;
		}

		@RequestMapping(value = "sendingmail_offerletter", method = { RequestMethod.GET, RequestMethod.POST })
		public ResponseEntity<String> sendMailss(@RequestParam(required = false) String a, @RequestParam(required = false) String cc,
				@RequestParam(required = false) String d, @RequestParam(required = false) List<String> t, Model md)
				throws SQLException, ParseException, IOException {
			System.out.println("Hi");
			String b = a;
			String to = b;
			
			String from = "valarmathi.s@bornfire.in";
			String username = "valarmathi.s@bornfire.in"; // change accordingly
			String password = "Bornfire@123"; // change accordingly
			String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
			String ref_no = d;
		/*	List<String> y = t;
			for (String bb : y) {
				System.out.println(bb);

			}*/

			System.out.println("sdfghjkl;");

			// Call sendMail method with correct parameters
			sendigmail_offerletter.sendingmailones(from, host, to, cc, username, password, ref_no); // pass from, host,
																									// password, and to

			// Return success response
			return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
		}

		@RequestMapping(value = "/pmaddsubmit", method = { RequestMethod.GET, RequestMethod.POST })

		@ResponseBody
		public String pmaddsubmit(Model md, HttpServletRequest rq, @RequestParam(required = false) String emp_id,
				@RequestParam(required = false) String emp_name, @RequestParam(required = false) String prev_orgn_1,
				@ModelAttribute ProfileManagerEntity1 profileManagerEntity) {

			System.out.println(emp_id);
			System.out.println(emp_name);
			System.out.println(prev_orgn_1);
			System.out.println("before" + profileManagerEntity.getPrev_orgn_1());

			ProfileManagerEntity1 up = profileManagerEntity;

			up.setVerify_flg("N");

			System.out.println("after" + up.getPrev_orgn_1());

			for (int i = 1; i <= 10; i++) {
				String fieldName = "prev_orgn_" + i;
				String fieldValue = null;
				switch (i) {
				case 1:
					fieldValue = profileManagerEntity.getPrev_orgn_1();
					if (fieldValue != null) {
						profileManagerEntity.setPrev_orgn_1(String.join("||", fieldValue.split(",")));
					}
					break;
				case 2:
					fieldValue = profileManagerEntity.getPrev_orgn_2();
					if (fieldValue != null) {
						profileManagerEntity.setPrev_orgn_2(String.join("||", fieldValue.split(",")));
					}
					break;
				case 3:
					fieldValue = profileManagerEntity.getPrev_orgn_3();
					if (fieldValue != null) {
						profileManagerEntity.setPrev_orgn_3(String.join("||", fieldValue.split(",")));
					}
					break;
				case 4:
					fieldValue = profileManagerEntity.getPrev_orgn_4();
					if (fieldValue != null) {
						profileManagerEntity.setPrev_orgn_4(String.join("||", fieldValue.split(",")));
					}
					break;
				case 5:
					fieldValue = profileManagerEntity.getPrev_orgn_5();
					if (fieldValue != null) {
						profileManagerEntity.setPrev_orgn_5(String.join("||", fieldValue.split(",")));
					}
					break;
				case 6:
					fieldValue = profileManagerEntity.getPrev_orgn_6();
					if (fieldValue != null) {
						profileManagerEntity.setPrev_orgn_6(String.join("||", fieldValue.split(",")));
					}
					break;
				case 7:
					fieldValue = profileManagerEntity.getPrev_orgn_7();
					if (fieldValue != null) {
						profileManagerEntity.setPrev_orgn_7(String.join("||", fieldValue.split(",")));
					}
					break;
				case 8:
					fieldValue = profileManagerEntity.getPrev_orgn_8();
					if (fieldValue != null) {
						profileManagerEntity.setPrev_orgn_8(String.join("||", fieldValue.split(",")));
					}
					break;
				case 9:
					fieldValue = profileManagerEntity.getPrev_orgn_9();
					if (fieldValue != null) {
						profileManagerEntity.setPrev_orgn_9(String.join("||", fieldValue.split(",")));
					}
					break;
				case 10:
					fieldValue = profileManagerEntity.getPrev_orgn_2();
					if (fieldValue != null) {
						profileManagerEntity.setPrev_orgn_2(String.join("||", fieldValue.split(",")));
					}
					break;
				}
			}

			for (int i = 1; i <= 20; i++) {
				String fieldName = "Proj_det_" + i;
				String fieldValue = null;
				switch (i) {
				case 1:
					fieldValue = profileManagerEntity.getProj_det_1();
					if (fieldValue != null) {
						profileManagerEntity.setProj_det_1(String.join("||", fieldValue.split(",")));
					}
					break;
				case 2:
					fieldValue = profileManagerEntity.getProj_det_2();
					if (fieldValue != null) {
						profileManagerEntity.setProj_det_2(String.join("||", fieldValue.split(",")));
					}
					break;
				case 3:
					fieldValue = profileManagerEntity.getProj_det_3();
					if (fieldValue != null) {
						profileManagerEntity.setProj_det_3(String.join("||", fieldValue.split(",")));
					}
					break;
				case 4:
					fieldValue = profileManagerEntity.getProj_det_4();
					if (fieldValue != null) {
						profileManagerEntity.setProj_det_4(String.join("||", fieldValue.split(",")));
					}
					break;
				case 5:
					fieldValue = profileManagerEntity.getProj_det_5();
					if (fieldValue != null) {
						profileManagerEntity.setProj_det_5(String.join("||", fieldValue.split(",")));
					}
					break;
				case 6:
					fieldValue = profileManagerEntity.getProj_det_6();
					if (fieldValue != null) {
						profileManagerEntity.setProj_det_6(String.join("||", fieldValue.split(",")));
					}
					break;
				case 7:
					fieldValue = profileManagerEntity.getProj_det_7();
					if (fieldValue != null) {
						profileManagerEntity.setProj_det_7(String.join("||", fieldValue.split(",")));
					}
					break;
				case 8:
					fieldValue = profileManagerEntity.getProj_det_8();
					if (fieldValue != null) {
						profileManagerEntity.setProj_det_8(String.join("||", fieldValue.split(",")));
					}
					break;
				case 9:
					fieldValue = profileManagerEntity.getProj_det_9();
					if (fieldValue != null) {
						profileManagerEntity.setProj_det_9(String.join("||", fieldValue.split(",")));
					}
					break;
				case 10:
					fieldValue = profileManagerEntity.getProj_det_2();

					if (fieldValue != null) {
						profileManagerEntity.setProj_det_2(String.join("||", fieldValue.split(",")));
					}
					break;
				case 11:
					fieldValue = profileManagerEntity.getProj_det_11();
					if (fieldValue != null) {
						profileManagerEntity.setProj_det_11(String.join("||", fieldValue.split(",")));
					}
					break;
				case 12:
					fieldValue = profileManagerEntity.getProj_det_12();
					if (fieldValue != null) {
						profileManagerEntity.setProj_det_12(String.join("||", fieldValue.split(",")));
					}
					break;
				case 13:
					fieldValue = profileManagerEntity.getProj_det_13();
					if (fieldValue != null) {
						profileManagerEntity.setProj_det_13(String.join("||", fieldValue.split(",")));
					}
					break;
				case 14:
					fieldValue = profileManagerEntity.getProj_det_14();
					if (fieldValue != null) {
						profileManagerEntity.setProj_det_14(String.join("||", fieldValue.split(",")));
					}
					break;
				case 15:
					fieldValue = profileManagerEntity.getProj_det_15();
					if (fieldValue != null) {
						profileManagerEntity.setProj_det_15(String.join("||", fieldValue.split(",")));
					}
					break;
				case 16:
					fieldValue = profileManagerEntity.getProj_det_16();
					if (fieldValue != null) {
						profileManagerEntity.setProj_det_16(String.join("||", fieldValue.split(",")));
					}
					break;
				case 17:
					fieldValue = profileManagerEntity.getProj_det_17();
					if (fieldValue != null) {
						profileManagerEntity.setProj_det_17(String.join("||", fieldValue.split(",")));
					}
					break;
				case 18:
					fieldValue = profileManagerEntity.getProj_det_18();
					if (fieldValue != null) {
						profileManagerEntity.setProj_det_18(String.join("||", fieldValue.split(",")));
					}
					break;
				case 19:
					fieldValue = profileManagerEntity.getProj_det_19();
					if (fieldValue != null) {
						profileManagerEntity.setProj_det_19(String.join("||", fieldValue.split(",")));
					}
					break;
				case 20:
					fieldValue = profileManagerEntity.getProj_det_20();
					if (fieldValue != null) {
						profileManagerEntity.setProj_det_20(String.join("||", fieldValue.split(",")));
					}
					break;
				}
			}

			profileManagerRep1.save(up);

			return "success";
		}

		@RequestMapping(value = "/pmversubmit/{emp}", method = { RequestMethod.POST })
		@ResponseBody
		public String pmversubmit(Model md, HttpServletRequest rq, @PathVariable String emp) {

			System.out.println("verify" + emp);
			ProfileManagerEntity1 up = profileManagerRep1.getPMform(emp);

			if (Objects.nonNull(up)) {
				up.setVerify_flg("Y");
				profileManagerRep1.save(up);
				return "success"; // Update successful
			} else {
				return "failure"; // Update failed
			}
		}

		@RequestMapping(value = "/pmdelete", method = RequestMethod.POST)
		@ResponseBody
		public String pmdelete(@RequestParam String emp_id, Model md, HttpServletRequest rq) {

			ProfileManagerEntity1 up = profileManagerRep1.getPMform(emp_id);

			profileManagerRep1.delete(up);

			return "Deleted successfully";

		}

		@RequestMapping(value = "/promanager", method = { RequestMethod.GET, RequestMethod.POST })
		public String promanager(@RequestParam(required = false) String formmode,
				@RequestParam(required = false) String emp_id, Model model, HttpServletRequest req) {

			String userId = (String) req.getSession().getAttribute("USERID");
			model.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			model.addAttribute("menu", "BTMHeaderMenu");
			
			if (formmode == null || formmode.equals("list")) {
				model.addAttribute("list", profileManagerRep1.getPMList());
				model.addAttribute("formmode", "list");
			} else if (formmode.equals("add")) {
				model.addAttribute("formmode", "add");
			} else if (formmode.equals("view")) {
				model.addAttribute("pmview", profileManagerRep1.getPMform(emp_id));

				// Process data for the first table with 6 columns
				List<String> pmForms = profileManagerRep1.getPMforms(emp_id);
				ArrayList<String[]> parsedForms = new ArrayList<>();
				for (String a : pmForms) {
					String[] values = a.split("\\|\\|");
					// Iterate over values and split into rows if more than 6 values are found
					for (int i = 0; i < values.length; i += 6) {
						String[] newRow = new String[6]; // Create a new row with 6 columns

						for (int j = 0; j < 6; j++) {
							// Check if index is within bounds
							if (i + j < values.length) {
								newRow[j] = values[i + j] != null ? values[i + j] : ""; // Replace null with empty string

							} else {
								newRow[j] = ""; // Fill with empty string if fewer than 6 values remain
							}
						}
						// Add the new row to parsedForms only if it has at least one non-zero value

						parsedForms.add(newRow);

					}
				}

				// Print parsed data for the first table
				for (String[] row : parsedForms) {
					System.out.println(Arrays.toString(row));
				}

				// Add parsedForms to the model for the first table
				model.addAttribute("pmlist", parsedForms);

				// Process data for the second table with 7 columns
				List<String> pmForms2 = profileManagerRep1.getPMforms2(emp_id);
				ArrayList<String[]> parsedForms2 = new ArrayList<>();
				for (String a : pmForms2) {
					String[] values = a.split("\\|\\|");
					// Iterate over values and split into rows if more than 7 values are found
					for (int i = 0; i < values.length; i += 7) {
						String[] newRow2 = new String[7]; // Create a new row with 7 columns
						for (int j = 0; j < 7; j++) {
							// Check if index is within bounds
							if (i + j < values.length) {
								newRow2[j] = values[i + j]; // Replace null with empty string
							} else {
								newRow2[j] = ""; // Fill with empty string if fewer than 7 values remain
							}
							// Count empty string values as well
							if (newRow2[j].isEmpty()) {
								// You can increment a counter here if you want to count empty values
							}
						}
						// Add the new row to parsedForms2
						parsedForms2.add(newRow2);
					}
				}

				// Print parsed data for the second table
				for (String[] row : parsedForms2) {
					System.out.println(Arrays.toString(row));
				}

				// Add parsedForms2 to the model for the second table
				model.addAttribute("pmlist2", parsedForms2);

				model.addAttribute("formmode", "view");

			} else if (formmode.equals("verify")) {
				model.addAttribute("pmview", profileManagerRep1.getPMform(emp_id));

				// Process data for the first table with 6 columns
				List<String> pmForms = profileManagerRep1.getPMforms(emp_id);
				ArrayList<String[]> parsedForms = new ArrayList<>();
				for (String a : pmForms) {
					String[] values = a.split("\\|\\|");
					// Iterate over values and split into rows if more than 6 values are found
					for (int i = 0; i < values.length; i += 6) {
						String[] newRow = new String[6]; // Create a new row with 6 columns

						for (int j = 0; j < 6; j++) {
							// Check if index is within bounds
							if (i + j < values.length) {
								newRow[j] = values[i + j] != null ? values[i + j] : ""; // Replace null with empty string

							} else {
								newRow[j] = ""; // Fill with empty string if fewer than 6 values remain
							}
						}
						// Add the new row to parsedForms only if it has at least one non-zero value

						parsedForms.add(newRow);

					}
				}

				// Print parsed data for the first table
				for (String[] row : parsedForms) {
					System.out.println(Arrays.toString(row));
				}

				// Add parsedForms to the model for the first table
				model.addAttribute("pmlist", parsedForms);

				// Process data for the second table with 7 columns
				List<String> pmForms2 = profileManagerRep1.getPMforms2(emp_id);
				ArrayList<String[]> parsedForms2 = new ArrayList<>();
				for (String a : pmForms2) {
					String[] values = a.split("\\|\\|");
					// Iterate over values and split into rows if more than 7 values are found
					for (int i = 0; i < values.length; i += 7) {
						String[] newRow2 = new String[7]; // Create a new row with 7 columns
						for (int j = 0; j < 7; j++) {
							// Check if index is within bounds
							if (i + j < values.length) {
								newRow2[j] = values[i + j]; // Replace null with empty string
							} else {
								newRow2[j] = ""; // Fill with empty string if fewer than 7 values remain
							}
							// Count empty string values as well
							if (newRow2[j].isEmpty()) {
								// You can increment a counter here if you want to count empty values
							}
						}
						// Add the new row to parsedForms2
						parsedForms2.add(newRow2);
					}
				}

				// Print parsed data for the second table
				for (String[] row : parsedForms2) {
					System.out.println(Arrays.toString(row));
				}

				// Add parsedForms2 to the model for the second table
				model.addAttribute("pmlist2", parsedForms2);

				model.addAttribute("formmode", "verify");
			}

			else if (formmode.equals("modify")) {
				model.addAttribute("pmview", profileManagerRep1.getPMform(emp_id));

				// Process data for the first table with 6 columns
				List<String> pmForms = profileManagerRep1.getPMforms(emp_id);
				ArrayList<String[]> parsedForms = new ArrayList<>();
				for (String a : pmForms) {
					String[] values = a.split("\\|\\|");
					// Iterate over values and split into rows if more than 6 values are found
					for (int i = 0; i < values.length; i += 6) {
						String[] newRow = new String[6]; // Create a new row with 6 columns

						for (int j = 0; j < 6; j++) {
							// Check if index is within bounds
							if (i + j < values.length) {
								newRow[j] = values[i + j] != null ? values[i + j] : ""; // Replace null with empty string

							} else {
								newRow[j] = ""; // Fill with empty string if fewer than 6 values remain
							}
						}
						// Add the new row to parsedForms only if it has at least one non-zero value

						parsedForms.add(newRow);

					}
				}

				// Print parsed data for the first table
				for (String[] row : parsedForms) {
					System.out.println(Arrays.toString(row));
				}

				// Add parsedForms to the model for the first table
				model.addAttribute("pmlist", parsedForms);

				// Process data for the second table with 7 columns
				List<String> pmForms2 = profileManagerRep1.getPMforms2(emp_id);
				ArrayList<String[]> parsedForms2 = new ArrayList<>();
				for (String a : pmForms2) {
					String[] values = a.split("\\|\\|");
					// Iterate over values and split into rows if more than 7 values are found
					for (int i = 0; i < values.length; i += 7) {
						String[] newRow2 = new String[7]; // Create a new row with 7 columns
						for (int j = 0; j < 7; j++) {
							// Check if index is within bounds
							if (i + j < values.length) {
								newRow2[j] = values[i + j]; // Replace null with empty string
							} else {
								newRow2[j] = ""; // Fill with empty string if fewer than 7 values remain
							}
							// Count empty string values as well
							if (newRow2[j].isEmpty()) {
								// You can increment a counter here if you want to count empty values
							}
						}
						// Add the new row to parsedForms2
						parsedForms2.add(newRow2);
					}
				}

				// Print parsed data for the second table
				for (String[] row : parsedForms2) {
					System.out.println(Arrays.toString(row));
				}

				// Add parsedForms2 to the model for the second table
				model.addAttribute("pmlist2", parsedForms2);

				model.addAttribute("formmode", "modify");
			}

			return "ProfileManager";
		}

		@RequestMapping(value = "assosiateProfile", method = { RequestMethod.GET, RequestMethod.POST })
		public String assosiateProfile(@RequestParam(required = false) String formmode,
				@RequestParam(required = false) String resource_id, Model md, HttpServletRequest rq,
				@ModelAttribute Assosiate_Profile_Entity assosiate_Profile_Entity) {

			String userId = (String) rq.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");
			
			if (formmode == null || formmode.equals("list")) {

				md.addAttribute("formmode", "list");
				md.addAttribute("AssosiateList", assosiate_Profile_Repo.getAssosiateList());

			} else if (formmode.equals("add")) {
				md.addAttribute("formmode", "add");
			} else if (formmode.equals("verify")) {

				md.addAttribute("formmode", "verify");
				md.addAttribute("AssosiateVerify", assosiate_Profile_Repo.getSingleIdData(resource_id));

			} else if (formmode.equals("view")) {

				md.addAttribute("formmode", "view");
				md.addAttribute("AssosiateVerify", assosiate_Profile_Repo.getSingleIdData(resource_id));

			} else if (formmode.equals("modify")) {
				md.addAttribute("formmode", "modify");
				md.addAttribute("AssosiateVerify", assosiate_Profile_Repo.getSingleIdData(resource_id));
			}

			return "AssosiateProfile";
		}

		@RequestMapping(value = "assosiateAdd", method = RequestMethod.POST)
		@ResponseBody
		public String assosiateAdd(String ref_no, Model md, HttpServletRequest rq,
				@ModelAttribute Assosiate_Profile_Entity assosiate_Profile_Entity) {
			assosiate_Profile_Entity.setEntity_flg("N");
			assosiate_Profile_Repo.save(assosiate_Profile_Entity);
			return "success";

		}

		@RequestMapping(value = "assosiateDelete", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public String assosiateDelete(@RequestParam(required = false) String resource_id,
				@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq,
				@ModelAttribute Assosiate_Profile_Entity assosiate_Profile_Entity) {

			assosiate_Profile_Repo.deleteById(resource_id);

			return "deleted";

		}

		@RequestMapping(value = "assosiateVerify", method = RequestMethod.POST)
		@ResponseBody
		public String assosiateVerify(Model md, HttpServletRequest rq,
				@ModelAttribute Assosiate_Profile_Entity assosiate_Profile_Entity) {

			assosiate_Profile_Entity.setEntity_flg("Y");
			assosiate_Profile_Repo.save(assosiate_Profile_Entity);
			return "success";

		}

		@RequestMapping(value = "assosiateModify", method = RequestMethod.POST)
		@ResponseBody
		public String assosiateModify(String ref_no, Model md, HttpServletRequest rq,
				@ModelAttribute Assosiate_Profile_Entity assosiate_Profile_Entity) {
			assosiate_Profile_Entity.setEntity_flg("N");
			assosiate_Profile_Repo.save(assosiate_Profile_Entity);
			return "success";

		}
		@RequestMapping(value = "pay_master", method = { RequestMethod.GET, RequestMethod.POST })
		public String pay_master(@RequestParam(required = false) String formmode,
				@RequestParam(required = false) String userid, @RequestParam(required = false) String record,@RequestParam(required = false) String salaryMonth,
				@RequestParam(required = false) String empname,
				@RequestParam(required = false) String a, @RequestParam(required = false) String salary_month,@RequestParam(required = false) String empno,
				paystructureentity Paystructureentity,

				@RequestParam(value = "page", required = false) Optional<Integer> page,
				@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

			// String loginuserid = (String) req.getSession().getAttribute("USERID");
			// Logging Navigation
			// loginServices.SessionLogging("USERPROFILE", "M2", req.getSession().getId(),
			// loginuserid, req.getRemoteAddr(),
			// "ACTIVE");

			md.addAttribute("menu", "projectmaster"); // To highlight the menu
			System.out.println("modiy pay master"+empno+salaryMonth);

			String userId = (String) req.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");

			//System.out.println(salary_month);

			if (formmode == null || formmode.equals("list")) {
				System.out.println(Paystructurerep.getpays(salary_month));
				//md.addAttribute("salarypay", Paystructurerep.getpays(record));
				 YearMonth currentYearMonth = YearMonth.now();
				    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM"); // Corrected pattern for month
				    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy");
				    String formattedMonth = currentYearMonth.format(formatter);
				    String formattedYear = currentYearMonth.format(formatter1);
				    System.out.println("Current Month and Year: " + formattedYear + formattedMonth);

		//	md.addAttribute("salarypay", Paystructurerep.getpay());
				    md.addAttribute("salarypay", Paystructurerep.getpayssdemo(formattedYear,formattedMonth));

				md.addAttribute("formmode", "list");
				
			} else if (formmode.equals("add")) {
				md.addAttribute("formmode", "add");
			} else if (formmode.equals("edit")) {
				md.addAttribute("salarypay", Paystructurerep.getaedits(empno,salaryMonth,empname));
				md.addAttribute("formmode", "edit");

			}

			return "pay_master";
		}
		@RequestMapping(value = "permas", method = { RequestMethod.GET, RequestMethod.POST })
		public String permas(@RequestParam(required = false) String formmode, @RequestParam(required = false) String emp_no,
				Model md, HttpServletRequest rq) {

			String userId = (String) rq.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");
			
			if (formmode == null || formmode.equals("list")) {

				md.addAttribute("list1", perdiemMasterRep.getPerMasList());
				md.addAttribute("formmode", "list");

			} else if (formmode.equals("add")) {
				md.addAttribute("formmode", "add");

			} else if (formmode.equals("view")) {

				System.out.println(emp_no);
				md.addAttribute("permasview", perdiemMasterRep.getPerMasform(emp_no));

				md.addAttribute("list5", perdiemMasterRep.getPerMasList3(emp_no));

				md.addAttribute("formmode", "view");

			}

			return "PerdiemMaster";
		}

		@RequestMapping(value = "/permassubmit", method = RequestMethod.POST)
		@ResponseBody
		public String permassubmit(@RequestParam(required = false) String emp_no, Model md, HttpServletRequest rq,
				@ModelAttribute PerdiemMasterEntity perdiemMasterEntity) {

			System.out.println("hi" + emp_no);

			PerdiemMasterEntity up = perdiemMasterEntity;

			up.setEntity_flg("N");

			perdiemMasterRep.save(up);

			return "success";

		}

		@RequestMapping(value = "/permasdelete", method = RequestMethod.POST)
		@ResponseBody
		public String permasdelete(@RequestParam(required = false) String emp_no, Model md, HttpServletRequest rq) {

			PerdiemMasterEntity up = perdiemMasterRep.getPerMasform(emp_no);

			perdiemMasterRep.delete(up);

			return "Deleted successfully";

		}

		@RequestMapping(value = "batchJob", method = { RequestMethod.GET, RequestMethod.POST })
		public String batchJob(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq) {

			String userId = (String) rq.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");
			
			return "BatchJob";
		}

		@RequestMapping(value = "monthlySalaryWork", method = { RequestMethod.GET, RequestMethod.POST })
		public String monthlySalaryWork(@RequestParam(required = false) String formmode,
				@RequestParam(required = false) String userid, @RequestParam(required = false) String record,

				@RequestParam(required = false) String a,@RequestParam(required = false) String uniqueid, @RequestParam(required = false) String ref_no,@RequestParam(required = false) String emp_no,

				CandEvalFormEntity candEvalFormEntity,
				@RequestParam(value = "page", required = false) Optional<Integer> page,
				@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

			String userId = (String) req.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");

			md.addAttribute("menu", "projectmaster"); // To highlight the menu

			System.out.println(record);
			if (formmode == null || formmode.equals("add")) {
				// System.out.println(Paystructurerep.getpays(record));
				// md.addAttribute("salarypay",
				// Paystructurerep.getpays(record));assosiate_Profile_Repo
				// md.addAttribute("salarypay", Baj_Work_Repo.getpays(record));
				// md.addAttribute("salarypay", Paystructurerep.getpay());

				md.addAttribute("formmode", "add");
				;
			} else if (formmode.equals("list1")) {
				// System.out.println(Paystructurerep.getpays(record));
				md.addAttribute("salarypay", Baj_Work_Repo.getpays(record));
				System.out.println(Baj_Work_Repo.getpays(record));
				// md.addAttribute("salarypay", Paystructurerep.getpay());

				md.addAttribute("formmode", "list1");
			} else if (formmode.equals("edit")) {
				md.addAttribute("salarypay", Baj_Work_Repo.getlisttab1(uniqueid));
				md.addAttribute("formmode", "edit");

			}  else if (formmode.equals("add1")) {
				md.addAttribute("formmode", "add1");
				
			}
		 else if (formmode.equals("add2")) {
				md.addAttribute("formmode", "add2");
				
			}
		 else if (formmode.equals("view")) {
			 md.addAttribute("salarypay", Baj_Work_Repo.getlisttab1(uniqueid));
				md.addAttribute("formmode", "view");
				
			}
		 else if (formmode.equals("verify")) {
			 md.addAttribute("salarypay", Baj_Work_Repo.getlisttab1(uniqueid));
				md.addAttribute("formmode", "verify");
				
			}
		
		return "monthlySalaryWork";
	}
		
		@RequestMapping(value = "/monthlyversubmit/{uni}", method = { RequestMethod.POST })
		@ResponseBody
		public String monthlyversubmit(Model md, HttpServletRequest rq, @PathVariable String uni) {
			
			System.out.println("verify" + uni);
			Baj_Sal_Work_Entity up = Baj_Work_Repo.getlisttab1(uni);

			if (Objects.nonNull(up)) {
				up.setEntity_flg("Y");
				Baj_Work_Repo.save(up);
				return "success"; // Update successful
			} else {
				return "failure"; // Update failed
			}
		}
		/*
		 * @RequestMapping(value = "monthlySalaryWork", method = { RequestMethod.GET,
		 * RequestMethod.POST }) public String monthlySalaryWork(@RequestParam(required
		 * = false) String formmode, Model md, HttpServletRequest rq) {
		 * 
		 * return "MonthlySalaryWork"; }
		 */

		@RequestMapping(value = "monthlySalaryGenerator", method = { RequestMethod.GET, RequestMethod.POST })
		public String monthlySalaryGenerator(@RequestParam(required = false) String formmode, Model md,
				HttpServletRequest rq) {

			String userId = (String) rq.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");
			
			return "MonthlySalaryGenerator";
		}

		@SuppressWarnings("null")
		@RequestMapping(value = "salaryTransactionCreation", method = { RequestMethod.GET, RequestMethod.POST })
		public String salaryTransactionCreation(@RequestParam(required = false) String formmode, Model md,
				HttpServletRequest rq, paystructureentity Paystructureentity,
				@RequestParam(required = false) String sal) {

			String userId = (String) rq.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");
			
			 if (formmode == null || formmode.equals("add")) {
		            // Fetch data from the database
		            List<paystructureentity> basicPayEntities = Paystructurerep.getstc(sal);
		            
		        
		            BigDecimal Basic = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getBasic_pay();
		            	 if (basicPay != null ) {
		                    Basic = Basic.add(basicPay);
		                 
		            	 }else {
		                    
		                    Basic = Basic.add(BigDecimal.ZERO);
		               
		            }
		            }
		            
		            BigDecimal hra = BigDecimal.ZERO;

		            for (paystructureentity entity : basicPayEntities) {
		               BigDecimal basicPay = entity.getHra();
		                    if (basicPay != null ) {
		                        hra = hra.add(basicPay);
		                    } else {
		                    	
		                    	hra = hra.add(BigDecimal.ZERO);
		                    }
		              }
		            

		            
		            BigDecimal spl = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	
		                	BigDecimal basicPay = entity.getSpl_allow();
		                	if (basicPay != null ) {
		                    spl = spl.add(basicPay);
		                	}
		            	 else {
		                    
		                    spl = spl.add(BigDecimal.ZERO);
		               
		            }
		            }	
		            
		            BigDecimal med = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getMedi_reimb();
		            	 if (basicPay != null) { 
		                    med = med.add(basicPay);
		                 }else {
		                    
		                    med = med.add(BigDecimal.ZERO);
		               
		            }
		            }
		            
		            BigDecimal convey = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getConv_allow();
		            	 if (basicPay != null) { 	
		                convey = convey.add(basicPay);
		                 
		            	 }else {
		                    convey = convey.add(BigDecimal.ZERO);
		            }
		            }
		            
		            BigDecimal lunch = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getLunch_allow();
		            	 if (basicPay != null) { 	
		               
		                	
		                    lunch = lunch.add(basicPay);
		                 
		            	 }else {
		                    
		                    lunch = lunch.add(BigDecimal.ZERO);
		               
		            }
		            }
		            
		            BigDecimal educ = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getEdu_allow();
		            	 if (basicPay != null) { 	
		               	
		                    educ = educ.add(basicPay);
		                 
		            	 }else {
		                    
		                    educ = educ.add(BigDecimal.ZERO);
		               
		            }
		            }
		            
		            BigDecimal attire = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getBuss_attire();
		            	 if (basicPay != null ) { 	
		               	
		                	attire = attire.add(basicPay);
		                 
		            	 }else {
		                    
		            		 attire = attire.add(BigDecimal.ZERO);
		               
		            }
		            }
		            
		            BigDecimal car = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getCar_maint();
		            	 if (basicPay != null) { 	
		               
		                	
		                	car = car.add(basicPay);
		                 
		            	 }else {
		                    
		            		 car = car.add(BigDecimal.ZERO);
		               
		            }
		            }
		            
		            BigDecimal leave = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getLeave_travel_allow();
		            	 if (basicPay != null) { 	
		               
		                	
		                	leave = leave.add(basicPay);
		                 
		            	 }else {
		                    
		            		 leave = leave.add(BigDecimal.ZERO);
		               
		            }
		            }
		            
		            BigDecimal outsta = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getOutstn_allow();
		            	 if (basicPay != null) { 	
		               
		                	
		                	outsta = outsta.add(basicPay);
		                 
		            	 }else {
		                    
		            		 outsta = outsta.add(BigDecimal.ZERO);
		               
		            }
		            }
		            
		            BigDecimal annual = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getAnnual_loyal_bonus();
		            	 if (basicPay != null) { 	
		               
		                	
		                	annual = annual.add(basicPay);
		                 
		            	 }else {
		                    
		            		 annual = annual.add(BigDecimal.ZERO);
		               
		            }
		            }
		            
		            BigDecimal other = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getOtr_allow();
		            	 if (basicPay != null) { 	
		               
		                	
		                	other = other.add(basicPay);
		                 
		            	 }else {
		                    
		            		 other = other.add(BigDecimal.ZERO);
		               
		            }
		            }
		            
		            BigDecimal gross = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getGross_salary();
		            	 if (basicPay != null ) { 	
		               
		                	gross = gross.add(basicPay);
		                 
		            	 }else {
		                    
		            		 gross = gross.add(BigDecimal.ZERO);
		               
		            }
		            }
		            
		            BigDecimal staff = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getSpf();
		            	 if (basicPay != null) { 	
		               
		                	staff = staff.add(basicPay);
		                 
		            	 }else {
		                    
		            		 staff = staff.add(BigDecimal.ZERO);
		               
		            }
		            }
		            
		            BigDecimal inc = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getTds();
		            	 if (basicPay != null) { 	
		               
		                	
		                	inc = inc.add(basicPay);
		                 
		            	 }else {
		                    
		            		 inc = inc.add(BigDecimal.ZERO);
		               
		            }
		            }
		            
		            BigDecimal proff = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getProf_tax();
		            	 if (basicPay != null ) { 	
		               
		                	
		                	proff = proff.add(basicPay);
		                 
		            	 }else {
		                    
		            		 proff = proff.add(BigDecimal.ZERO);
		               
		            }
		            }
		            
		            BigDecimal emplo = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getEsi();
		            	 if (basicPay != null) { 	
		               
		                	
		                	emplo = emplo.add(basicPay);
		                 
		            	 }else {
		                    
		            		 emplo = emplo.add(BigDecimal.ZERO);
		               
		            }
		            }
		            
		            BigDecimal recov = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getRecovery();
		            	 if (basicPay != null) { 	
		               
		                	
		                	recov = recov.add(basicPay);
		                 
		            	 }else {
		                    
		            		 recov = recov.add(BigDecimal.ZERO);
		               
		            }
		            }
		            
		            
		            BigDecimal otrded = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getOtr_ded();
		            	 if (basicPay != null) { 	
		               
		                	
		                	otrded = otrded.add(basicPay);
		                 
		            	 }else {
		                    
		            		 otrded = otrded.add(BigDecimal.ZERO);
		               
		            }
		            }
		            
		            BigDecimal totded = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getTotal_deductions();
		            	 if (basicPay != null ) { 	
		               
		                	
		                	totded = totded.add(basicPay);
		                 
		            	 }else {
		                    
		            		 totded = totded.add(BigDecimal.ZERO);
		               
		            }
		            }
		            
		            BigDecimal nets = BigDecimal.ZERO;
		            for (paystructureentity entity : basicPayEntities) {
		            	BigDecimal basicPay = entity.getNet_salary();
		            	 if (basicPay != null) { 	
		               
		                	
		                	nets = nets.add(basicPay);
		                 
		            	 }else {
		                    
		            		 nets = nets.add(BigDecimal.ZERO);
		               
		            }
		            }
		            
		            
		            //System.out.println(Basic);
		            md.addAttribute("stc", Basic);
		            md.addAttribute("hr", hra);
		            md.addAttribute("sp", spl);
		            md.addAttribute("me", med);
		            md.addAttribute("con", convey);
		            md.addAttribute("lun", lunch);
		            md.addAttribute("edu", educ);
		            md.addAttribute("att", attire);
		            md.addAttribute("ca", car);
		            md.addAttribute("lea", leave);
		            md.addAttribute("out", outsta);
		            md.addAttribute("ann", annual);
		            md.addAttribute("otr", other);
		            md.addAttribute("gro", gross);
		            md.addAttribute("sta", staff);
		            md.addAttribute("in", inc);
		            md.addAttribute("pro", proff);
		            md.addAttribute("emp", emplo);
		            md.addAttribute("rec", recov);
		            md.addAttribute("otrd", otrded);
		            md.addAttribute("totd", totded);
		            md.addAttribute("net", nets);
		            
		            md.addAttribute("formmode", "add");
		        }else if (formmode.equals("journal")) {
				md.addAttribute("formmode", "journal");
				
			}else if (formmode == null || formmode.equals("add1")) {
	            // Fetch data from the database
	            List<paystructureentity> basicPayEntities = Paystructurerep.getstc(sal);
	            
	        
	            BigDecimal Basic = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getBasic_pay();
	            	 if (basicPay != null ) {
	                    Basic = Basic.add(basicPay);
	                 
	            	 }else {
	                    
	                    Basic = Basic.add(BigDecimal.ZERO);
	               
	            }
	            }
	            
	            BigDecimal hra = BigDecimal.ZERO;

	            for (paystructureentity entity : basicPayEntities) {
	               BigDecimal basicPay = entity.getHra();
	                    if (basicPay != null ) {
	                        hra = hra.add(basicPay);
	                    } else {
	                    	
	                    	hra = hra.add(BigDecimal.ZERO);
	                    }
	              }
	            

	            
	            BigDecimal spl = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	
	                	BigDecimal basicPay = entity.getSpl_allow();
	                	if (basicPay != null ) {
	                    spl = spl.add(basicPay);
	                	}
	            	 else {
	                    
	                    spl = spl.add(BigDecimal.ZERO);
	               
	            }
	            }	
	            
	            BigDecimal med = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getMedi_reimb();
	            	 if (basicPay != null) { 
	                    med = med.add(basicPay);
	                 }else {
	                    
	                    med = med.add(BigDecimal.ZERO);
	               
	            }
	            }
	            
	            BigDecimal convey = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getConv_allow();
	            	 if (basicPay != null) { 	
	                convey = convey.add(basicPay);
	                 
	            	 }else {
	                    convey = convey.add(BigDecimal.ZERO);
	            }
	            }
	            
	            BigDecimal lunch = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getLunch_allow();
	            	 if (basicPay != null) { 	
	               
	                	
	                    lunch = lunch.add(basicPay);
	                 
	            	 }else {
	                    
	                    lunch = lunch.add(BigDecimal.ZERO);
	               
	            }
	            }
	            
	            BigDecimal educ = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getEdu_allow();
	            	 if (basicPay != null) { 	
	               	
	                    educ = educ.add(basicPay);
	                 
	            	 }else {
	                    
	                    educ = educ.add(BigDecimal.ZERO);
	               
	            }
	            }
	            
	            BigDecimal attire = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getBuss_attire();
	            	 if (basicPay != null ) { 	
	               	
	                	attire = attire.add(basicPay);
	                 
	            	 }else {
	                    
	            		 attire = attire.add(BigDecimal.ZERO);
	               
	            }
	            }
	            
	            BigDecimal car = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getCar_maint();
	            	 if (basicPay != null) { 	
	               
	                	
	                	car = car.add(basicPay);
	                 
	            	 }else {
	                    
	            		 car = car.add(BigDecimal.ZERO);
	               
	            }
	            }
	            
	            BigDecimal leave = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getLeave_travel_allow();
	            	 if (basicPay != null) { 	
	               
	                	
	                	leave = leave.add(basicPay);
	                 
	            	 }else {
	                    
	            		 leave = leave.add(BigDecimal.ZERO);
	               
	            }
	            }
	            
	            BigDecimal outsta = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getOutstn_allow();
	            	 if (basicPay != null) { 	
	               
	                	
	                	outsta = outsta.add(basicPay);
	                 
	            	 }else {
	                    
	            		 outsta = outsta.add(BigDecimal.ZERO);
	               
	            }
	            }
	            
	            BigDecimal annual = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getAnnual_loyal_bonus();
	            	 if (basicPay != null) { 	
	               
	                	
	                	annual = annual.add(basicPay);
	                 
	            	 }else {
	                    
	            		 annual = annual.add(BigDecimal.ZERO);
	               
	            }
	            }
	            
	            BigDecimal other = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getOtr_allow();
	            	 if (basicPay != null) { 	
	               
	                	
	                	other = other.add(basicPay);
	                 
	            	 }else {
	                    
	            		 other = other.add(BigDecimal.ZERO);
	               
	            }
	            }
	            
	            BigDecimal gross = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getGross_salary();
	            	 if (basicPay != null ) { 	
	               
	                	gross = gross.add(basicPay);
	                 
	            	 }else {
	                    
	            		 gross = gross.add(BigDecimal.ZERO);
	               
	            }
	            }
	            
	            BigDecimal staff = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getSpf();
	            	 if (basicPay != null) { 	
	               
	                	staff = staff.add(basicPay);
	                 
	            	 }else {
	                    
	            		 staff = staff.add(BigDecimal.ZERO);
	               
	            }
	            }
	            
	            BigDecimal inc = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getTds();
	            	 if (basicPay != null) { 	
	               
	                	
	                	inc = inc.add(basicPay);
	                 
	            	 }else {
	                    
	            		 inc = inc.add(BigDecimal.ZERO);
	               
	            }
	            }
	            
	            BigDecimal proff = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getProf_tax();
	            	 if (basicPay != null ) { 	
	               
	                	
	                	proff = proff.add(basicPay);
	                 
	            	 }else {
	                    
	            		 proff = proff.add(BigDecimal.ZERO);
	               
	            }
	            }
	            
	            BigDecimal emplo = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getEsi();
	            	 if (basicPay != null) { 	
	               
	                	
	                	emplo = emplo.add(basicPay);
	                 
	            	 }else {
	                    
	            		 emplo = emplo.add(BigDecimal.ZERO);
	               
	            }
	            }
	            
	            BigDecimal recov = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getRecovery();
	            	 if (basicPay != null) { 	
	               
	                	
	                	recov = recov.add(basicPay);
	                 
	            	 }else {
	                    
	            		 recov = recov.add(BigDecimal.ZERO);
	               
	            }
	            }
	            
	            
	            BigDecimal otrded = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getOtr_ded();
	            	 if (basicPay != null) { 	
	               
	                	
	                	otrded = otrded.add(basicPay);
	                 
	            	 }else {
	                    
	            		 otrded = otrded.add(BigDecimal.ZERO);
	               
	            }
	            }
	            
	            BigDecimal totded = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getTotal_deductions();
	            	 if (basicPay != null ) { 	
	               
	                	
	                	totded = totded.add(basicPay);
	                 
	            	 }else {
	                    
	            		 totded = totded.add(BigDecimal.ZERO);
	               
	            }
	            }
	            
	            BigDecimal nets = BigDecimal.ZERO;
	            for (paystructureentity entity : basicPayEntities) {
	            	BigDecimal basicPay = entity.getNet_salary();
	            	 if (basicPay != null) { 	
	               
	                	
	                	nets = nets.add(basicPay);
	                 
	            	 }else {
	                    
	            		 nets = nets.add(BigDecimal.ZERO);
	               
	            }
	            }
	            
	            
	            //System.out.println(Basic);
	            md.addAttribute("stc", Basic);
	            md.addAttribute("hr", hra);
	            md.addAttribute("sp", spl);
	            md.addAttribute("me", med);
	            md.addAttribute("con", convey);
	            md.addAttribute("lun", lunch);
	            md.addAttribute("edu", educ);
	            md.addAttribute("att", attire);
	            md.addAttribute("ca", car);
	            md.addAttribute("lea", leave);
	            md.addAttribute("out", outsta);
	            md.addAttribute("ann", annual);
	            md.addAttribute("otr", other);
	            md.addAttribute("gro", gross);
	            md.addAttribute("sta", staff);
	            md.addAttribute("in", inc);
	            md.addAttribute("pro", proff);
	            md.addAttribute("emp", emplo);
	            md.addAttribute("rec", recov);
	            md.addAttribute("otrd", otrded);
	            md.addAttribute("totd", totded);
	            md.addAttribute("net", nets);
	            
	            md.addAttribute("formmode", "add1");
	        }
			
			return "SalaryTransactionCreation";
		}

		@RequestMapping(value = "salaryPaymentTransaction", method = { RequestMethod.GET, RequestMethod.POST })
		public String salaryPaymentTransaction(@RequestParam(required = false) String formmode, Model md,
				HttpServletRequest rq) {

			String userId = (String) rq.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");
			
			return "SalaryPaymentTransaction";
		}

		@RequestMapping(value = "bankFileDownload", method = { RequestMethod.GET, RequestMethod.POST })
		public String bankFileDownload(@RequestParam(required = false) String formmode,@RequestParam(required = false) String a,Model md, HttpServletRequest rq) {
			String userId = (String) rq.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");
			
			System.out.println("bankFileDownload"+a);
			if (formmode == null || formmode.equals("list")) {
				// System.out.println(Paystructurerep.getpays(record));
				// md.addAttribute("salarypay",
				// Paystructurerep.getpays(record));assosiate_Profile_Repo
				// md.addAttribute("salarypay", Baj_Work_Repo.getpays(record));
				// md.addAttribute("salarypay", Paystructurerep.getpay());

				md.addAttribute("formmode", "list");
				;
			} else if (formmode.equals("add")) {
			
				
				 Date currentDate = new Date();

			        // Create a date format
			        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

			        // Format the current date
			        String formattedDate = dateFormat.format(currentDate);

			        // Print the formatted date
			        System.out.println("Current Date: " + formattedDate);
			        md.addAttribute("formattedDate", formattedDate);
				md.addAttribute("salarypay", Paystructurerep.bankjobicici(a));
				//md.addAttribute("ifsccode", Paystructurerep.bjicicinotpresent(a));
				
				System.out.println(Paystructurerep.bankjobicici(a));
				//System.out.println(Baj_Work_Repo.getpays(record));
				// md.addAttribute("salarypay", Paystructurerep.getpay());

				md.addAttribute("formmode", "add");
			}
			return "BankFileDownload";
		}

		@RequestMapping(value = "paySlipGeneration", method = { RequestMethod.GET, RequestMethod.POST })
		public String paySlipGeneration(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq) {

			String userId = (String) rq.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");
			md.addAttribute("cvfverify", Paystructurerep.getpaystruce());
			
			return "PaySlipGeneration";
		}

@RequestMapping(value = "perdiemGeneration", method = { RequestMethod.GET, RequestMethod.POST })
		public String perdiemGeneration(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq) {

			String userId = (String) rq.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");
			md.addAttribute("cvfverify",perdiemMasterRep.getperdium());
			return "PerdiemGeneration";
		}
		@RequestMapping(value = "viewtotds1", method = RequestMethod.POST)
		@ResponseBody
		public String viewtotds1(@RequestParam(required = false) String formmode,

				Model md, HttpServletRequest rq, @RequestParam(required = false) String b,
				@RequestParam(required = false) String a, @RequestParam(required = false) String t) throws ParseException {

			String userId = (String) rq.getSession().getAttribute("USERID");
			//md.addAttribute("RoleMenu", Baj_Work_Repo.getpays(userId));
			md.addAttribute("menu", "BTMHeaderMenu");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = dateFormat.parse(t);
			System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a);
			System.out.println("]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]" + t);

			// String h = a.substring(0, a.length() - 1);
			// System.out.println(h); // Output: 202

			int lastDigit = Character.getNumericValue(a.charAt(a.length() - 1));
			System.out.println(lastDigit);// Get the last digit as an integer
			lastDigit--; // Increment the last digit
			String h = a.substring(0, a.length() - 1) + lastDigit;

			// Remove the last character and append the incremented digit
			System.out.println("sdfghjkl" + h); // Output: 2024

			String inputYearString = a.substring(0, 4);
			String inputMonthString = a.substring(4);
			System.out.println("sdfghjhgfdsdfghj8888888888" + inputMonthString);
			String YearL = null;

			YearMonth currentYearMonth = YearMonth.now();
			// LocalDate endOfMonth = currentYearMonth.atEndOfMonth();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy");
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd");

			/*
			 * int n = Integer.parseInt(a); System.out.println("dfdfdfdfdf"+n);
			 * 
			 * 
			 * int month = n % 100;
			 * 
			 * 
			 * YearMonth yearMonth = YearMonth.of(n / 100, month); int lastDayOfMonth =
			 * yearMonth.lengthOfMonth();
			 * 
			 * 
			 * int lastDay = lastDayOfMonth;
			 * 
			 * 
			 * System.out.println("Last day of the month: " + lastDay);
			 */

			int n = Integer.parseInt(a);
			System.out.println("dfdfdfdfdf" + n);

			int month = n % 100;

			// Determine the last day of the month
			YearMonth yearMonth = YearMonth.of(n / 100, month);
			int lastDayOfMonth = yearMonth.lengthOfMonth();

			// Convert lastDayOfMonth to BigDecimal
			BigDecimal lastDay = BigDecimal.valueOf(lastDayOfMonth);
			System.out.println("Last day of the month: " + lastDay);

			// System.out.println(formatter2);
			String formattedMonth = currentYearMonth.format(formatter);
			String formattedYear = currentYearMonth.format(formatter1);
			// String formattedDate = endOfMonth.format(dateFormatter);
			System.out.println(formattedMonth);
			System.out.println(formattedYear);
			String p = formattedYear + formattedMonth;
			// System.out.println("End of the Month Date: " + formattedDate);

			if (a.equals(p)) {

				try {
					List<paystructureentity> up2 = Paystructurerep.getpays(h);
					// List<paystructureentity> salary=Paystructurerep.getpaysnewss(h);
					List<Baj_Sal_Work_Entity> up3 = new ArrayList<>();

					for (paystructureentity salpay : up2) {
						// SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Baj_Sal_Work_Entity salwork = new Baj_Sal_Work_Entity();
						salwork.setEmp_no(salpay.getEmp_no());
						salwork.setEmp_name(salpay.getEmp_name());
						salwork.setEmp_desig(salpay.getEmp_desig());
						salwork.setEmp_group(salpay.getEmp_group());
						salwork.setEmp_division(salpay.getEmp_division());

						salwork.setDate_of_birth(salpay.getDate_of_birth());
						salwork.setDate_of_joining(salpay.getDate_of_joining());

						salwork.setSpf_acct_no(salpay.getSpf_acct_no());
						salwork.setUrn_no(salpay.getUrn_no());
						salwork.setRecord_date(salpay.getRecord_date());
						salwork.setSalary_month(a); // First setting
						// salwork.setSalary_month(salpay.getSalary_month()); // Second setting

						// salwork.setNo_of_days(salpay.getNo_of_days());
						salwork.setNo_of_days(lastDay);
						// System.out.println("dfghjklpppppppppppppppppp"+lastDay);
						// salwork.setDays_paid(salpay.getDays_paid());
						salwork.setDays_paid(lastDay);
						// System.out.println("dfghjklpppppppppppppppppp"+lastDay);
						salwork.setLoss_of_pay(salpay.getLoss_of_pay());
						salwork.setPayment_mode(salpay.getPayment_mode());
						salwork.setBank_name(salpay.getBank_name());
						salwork.setBank_acct_no(salpay.getBank_acct_no());
						salwork.setBasic_pay(salpay.getBasic_pay());
						salwork.setHra(salpay.getHra());
						salwork.setSpl_allow(salpay.getSpl_allow());
						salwork.setMedi_reimb(salpay.getMedi_reimb());

						salwork.setConv_allow(salpay.getConv_allow());
						salwork.setLunch_allow(salpay.getLunch_allow());
						salwork.setEdu_allow(salpay.getEdu_allow());
						salwork.setBuss_attire(salpay.getBuss_attire());
						salwork.setCar_maint(salpay.getCar_maint());
						salwork.setLeave_travel_allow(salpay.getLeave_travel_allow());
						salwork.setOutstn_allow(salpay.getOutstn_allow());
						salwork.setAnnual_loyal_bonus(salpay.getAnnual_loyal_bonus());

						salwork.setOtr_allow(salpay.getOtr_allow());
						salwork.setGross_salary(salpay.getGross_salary());
						salwork.setSpf(salpay.getSpf());
						salwork.setTds(salpay.getTds());
						salwork.setProf_tax(salpay.getProf_tax());
						salwork.setEsi(salpay.getEsi());
						salwork.setRecovery(salpay.getRecovery());
						salwork.setOtr_ded(salpay.getOtr_ded());
						salwork.setTotal_deductions(salpay.getTotal_deductions());
						salwork.setNet_salary(salpay.getNet_salary());
						salwork.setDate_of_pay(salpay.getDate_of_pay());
						salwork.setCum_tds_fy(salpay.getCum_tds_fy());
						salwork.setProv_it(salpay.getProv_it());
						salwork.setTax_due(salpay.getTax_due());
						salwork.setTax_per_month(salpay.getTax_per_month());
						salwork.setCtc_amt(salpay.getCtc_amt());
						salwork.setDecl_status(salpay.getDecl_status());
						salwork.setCtc_eff_date(salpay.getCtc_eff_date());
						salwork.setCtc_end_date(salpay.getCtc_end_date());
						salwork.setMobile_no(salpay.getMobile_no());
						salwork.setEmail_id(salpay.getEmail_id());
						salwork.setRecord_type(salpay.getRecord_type());
						salwork.setRecord_srl_no(salpay.getRecord_srl_no());
						salwork.setRecord_date(salpay.getRecord_date());
						salwork.setRecord_date(date);
						// String d[]=(salpay.getRecord_date().toString()).split(" ");
						// salwork.setRecord_date(d[0]);
						salwork.setPay_status(salpay.getPay_status());
						salwork.setEntity_flg(salpay.getEntity_flg());
						salwork.setDel_flg(salpay.getDel_flg());
						salwork.setEntry_time(salpay.getEntry_time());
						salwork.setEntry_user(salpay.getEntry_user());
						salwork.setModify_flg(salpay.getModify_flg());
						salwork.setModify_time(salpay.getModify_time());
						salwork.setModify_user(salpay.getModify_user());
						salwork.setVerify_time(salpay.getVerify_time());
						salwork.setVerify_user(salpay.getVerify_user());
						salwork.setRemarks(salpay.getRemarks());
						salwork.setAadhar_no(salpay.getAadhar_no());
						salwork.setUniqueid(salwork.getEmp_no() + (salwork.getSalary_month()));
						// System.out.println(salwork.getEmp_no() + (salwork.getSalary_month()));

						up3.add(salwork);

						// up3.add(gstoverseas);
					}

					System.out.println(up3);
					//
					Baj_Work_Repo.saveAll(up3);
					// System.out.println("sdfghjkl"+salwork.getUniqueid());

					// gstBtmRep.getInsert(b,a);

					// System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a + b);
					// int uniqueIdCounter = Integer.parseInt(b);
					// int uniqueIdCounter1 = Integer.parseInt(a)salpay

					// System.out.println(gstRep.getInsert(a,b));
					String msg = "Data Saved Successfully";
					return msg;

				} catch (Exception e) {
					e.printStackTrace();
					return "Error: " + e.getMessage();
				}

			}

			else {

				try {
					List<paystructureentity> up2 = Paystructurerep.getpays(a);
					// List<paystructureentity> salary=Paystructurerep.getpaysnewss(h);
					List<Baj_Sal_Work_Entity> up3 = new ArrayList<>();

					for (paystructureentity salpay : up2) {
						// SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Baj_Sal_Work_Entity salwork = new Baj_Sal_Work_Entity();
						salwork.setEmp_no(salpay.getEmp_no());
						salwork.setEmp_name(salpay.getEmp_name());
						salwork.setEmp_desig(salpay.getEmp_desig());
						salwork.setEmp_group(salpay.getEmp_group());
						salwork.setEmp_division(salpay.getEmp_division());

						salwork.setDate_of_birth(salpay.getDate_of_birth());
						salwork.setDate_of_joining(salpay.getDate_of_joining());

						salwork.setSpf_acct_no(salpay.getSpf_acct_no());
						salwork.setUrn_no(salpay.getUrn_no());
						salwork.setRecord_date(salpay.getRecord_date());
						salwork.setSalary_month(a); // First setting
						// salwork.setSalary_month(salpay.getSalary_month()); // Second setting

						// salwork.setNo_of_days(salpay.getNo_of_days());
						salwork.setNo_of_days(lastDay);
						// System.out.println("dfghjklpppppppppppppppppp"+lastDay);
						// salwork.setDays_paid(salpay.getDays_paid());
						salwork.setDays_paid(lastDay);
						// System.out.println("dfghjklpppppppppppppppppp"+lastDay);
						salwork.setLoss_of_pay(salpay.getLoss_of_pay());
						salwork.setPayment_mode(salpay.getPayment_mode());
						salwork.setBank_name(salpay.getBank_name());
						salwork.setBank_acct_no(salpay.getBank_acct_no());
						salwork.setBasic_pay(salpay.getBasic_pay());
						salwork.setHra(salpay.getHra());
						salwork.setSpl_allow(salpay.getSpl_allow());
						salwork.setMedi_reimb(salpay.getMedi_reimb());

						salwork.setConv_allow(salpay.getConv_allow());
						salwork.setLunch_allow(salpay.getLunch_allow());
						salwork.setEdu_allow(salpay.getEdu_allow());
						salwork.setBuss_attire(salpay.getBuss_attire());
						salwork.setCar_maint(salpay.getCar_maint());
						salwork.setLeave_travel_allow(salpay.getLeave_travel_allow());
						salwork.setOutstn_allow(salpay.getOutstn_allow());
						salwork.setAnnual_loyal_bonus(salpay.getAnnual_loyal_bonus());

						salwork.setOtr_allow(salpay.getOtr_allow());
						salwork.setGross_salary(salpay.getGross_salary());
						salwork.setSpf(salpay.getSpf());
						salwork.setTds(salpay.getTds());
						salwork.setProf_tax(salpay.getProf_tax());
						salwork.setEsi(salpay.getEsi());
						salwork.setRecovery(salpay.getRecovery());
						salwork.setOtr_ded(salpay.getOtr_ded());
						salwork.setTotal_deductions(salpay.getTotal_deductions());
						salwork.setNet_salary(salpay.getNet_salary());
						salwork.setDate_of_pay(salpay.getDate_of_pay());
						salwork.setCum_tds_fy(salpay.getCum_tds_fy());
						salwork.setProv_it(salpay.getProv_it());
						salwork.setTax_due(salpay.getTax_due());
						salwork.setTax_per_month(salpay.getTax_per_month());
						salwork.setCtc_amt(salpay.getCtc_amt());
						salwork.setDecl_status(salpay.getDecl_status());
						salwork.setCtc_eff_date(salpay.getCtc_eff_date());
						salwork.setCtc_end_date(salpay.getCtc_end_date());
						salwork.setMobile_no(salpay.getMobile_no());
						salwork.setEmail_id(salpay.getEmail_id());
						salwork.setRecord_type(salpay.getRecord_type());
						salwork.setRecord_srl_no(salpay.getRecord_srl_no());
						salwork.setRecord_date(date);
						salwork.setPay_status(salpay.getPay_status());
						salwork.setEntity_flg(salpay.getEntity_flg());
						salwork.setDel_flg(salpay.getDel_flg());
						salwork.setEntry_time(salpay.getEntry_time());
						salwork.setEntry_user(salpay.getEntry_user());
						salwork.setModify_flg(salpay.getModify_flg());
						salwork.setModify_time(salpay.getModify_time());
						salwork.setModify_user(salpay.getModify_user());
						salwork.setVerify_time(salpay.getVerify_time());
						salwork.setVerify_user(salpay.getVerify_user());
						salwork.setRemarks(salpay.getRemarks());
						salwork.setAadhar_no(salpay.getAadhar_no());
						salwork.setUniqueid(salwork.getEmp_no() + (salwork.getSalary_month()));
						// System.out.println(salwork.getEmp_no() + (salwork.getSalary_month()));

						up3.add(salwork);

						// up3.add(gstoverseas);
					}

					System.out.println(up3);
					//
					Baj_Work_Repo.saveAll(up3);
					// System.out.println("sdfghjkl"+salwork.getUniqueid());

					// gstBtmRep.getInsert(b,a);

					// System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a + b);
					// int uniqueIdCounter = Integer.parseInt(b);
					// int uniqueIdCounter1 = Integer.parseInt(a)salpay

					// System.out.println(gstRep.getInsert(a,b));
					String msg = "Data Saved Successfully";
					return msg;

				} catch (Exception e) {
					e.printStackTrace();
					return "Error: " + e.getMessage();
				}

			}
		}

		/*
		 * private BigDecimal parseDate(String t) { // TODO Auto-generated method stub
		 * return null; }
		 */

		@RequestMapping(value = "submitaddbaj", method = RequestMethod.POST)
		@ResponseBody
		public String submitaddbaj(Model md, HttpServletRequest rq, @ModelAttribute Baj_Sal_Work_Entity Baj_Sal_Work_Entity,
				String emp_name) {

			System.out.println("the rating AGENCY>>>> ");
			Baj_Sal_Work_Entity up = Baj_Sal_Work_Entity;
			up.setUniqueid(Baj_Sal_Work_Entity.getEmp_no() + Baj_Sal_Work_Entity.getSalary_month());
			System.out.println(Baj_Sal_Work_Entity.getEmp_no() + Baj_Sal_Work_Entity.getSalary_month());

			System.out.println("hi it is gst here your adding the record for TDS");

			Baj_Work_Repo.save(up);

			return "added successfully BAJ SALARY";

		}

		@RequestMapping(value = "tdstab1edit1", method = RequestMethod.POST)
		@ResponseBody
		public String tdstab1edit1(@ModelAttribute Baj_Sal_Work_Entity Baj_Sal_Work_Entity, String tran_id,
				@RequestParam(required = false) String uniqueid) throws ParseException {

			System.out.println("hihihi");
			System.out.println(uniqueid);
			// Baj_Sal_Work_Entity up = Baj_Work_Repo.getlisttab1(uniqueid);
			Baj_Sal_Work_Entity up = Baj_Sal_Work_Entity;
			System.out.println("hi this is btm");
			Baj_Work_Repo.save(up);
			// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
			// Locale.ENGLISH);

			/*
			 * try {
			 * 
			 * up.setEmp_name(tdsentity.getEmp_name()); up.setPan_no(tdsentity.getPan_no());
			 * up.setDate_of_pay(tdsentity.getDate_of_pay());
			 * up.setNet_salary(tdsentity.getNet_salary());
			 * up.setRate_of_tds(tdsentity.getTds_tran_ref());
			 * up.setBank_bsr_code(tdsentity.getBank_bsr_code());
			 * up.setTds(tdsentity.getTds());
			 * up.setBank_chalan_no(tdsentity.getBank_chalan_no());
			 * up.setBank_name(tdsentity.getBank_name());
			 * up.setChalan_amt(tdsentity.getChalan_amt());
			 * up.setTds_remit_date(tdsentity.getTds_remit_date());
			 * up.setTds_tran_ref(tdsentity.getTds_tran_ref());
			 * 
			 * //up.setUniqueid(Gstoverseas.getUniqueid());
			 * 
			 * 
			 * 
			 * tdsRepos.save(up);
			 * System.out.println("hi this is gst from tds"+tdsentity.getEmp_name());
			 * System.out.println("hi this is btm"+tdsentity.getDate_of_pay());
			 * 
			 * // Save the 'up' object with the updated entry_time
			 * 
			 * } catch (Exception e) { e.printStackTrace(); // Handle potential errors here,
			 * such as ParseException }
			 */

			return "edited Successfully tdstable1";

		}

		@RequestMapping(value = "deletetds1", method = RequestMethod.POST)
		@ResponseBody
		public String deletetds1(Model md, HttpServletRequest rq, @ModelAttribute Baj_Sal_Work_Entity Baj_Sal_Work_Entity,
				String tran_id, @RequestParam(required = false) String uniqueid) {

			System.out.println(uniqueid);
			Baj_Sal_Work_Entity up = Baj_Work_Repo.getlisttab1(uniqueid);

			System.out.println("hi it is gst here your adding the record for india");
			System.out.println("hi it is gst here your adding the record " + Baj_Sal_Work_Entity.getUniqueid());

			Baj_Work_Repo.delete(up);

			return "deleted successfully";

		}
		@RequestMapping(value = "swappingtosalery", method = RequestMethod.POST)
		@ResponseBody
		public String swappingtosalery(
		        @RequestParam(required = false) String formmode,
		        Model md, HttpServletRequest rq,
		        @RequestParam(required = false) String b, @RequestParam(required = false) String a) {

		    String userId = (String) rq.getSession().getAttribute("USERID");
		   
		    
		    System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a );

		    try {
		        List<Baj_Sal_Work_Entity> up2 = Baj_Work_Repo.getswap(a);
		       // System.out.println(up2);
		        List<paystructureentity> up3 = new ArrayList<>();
		        
		        
		        for (Baj_Sal_Work_Entity salsecond : up2) {
		        	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		        	paystructureentity salarymain = new paystructureentity();
		        	salarymain.setEmp_no(salsecond.getEmp_no());  
		        	salarymain.setEmp_name(salsecond.getEmp_name());  
		        	salarymain.setEmp_desig(salsecond.getEmp_desig());  
		        	salarymain.setEmp_group(salsecond.getEmp_group()); 
		        	salarymain.setEmp_division(salsecond.getEmp_division()); 
		        	
		        	salarymain.setDate_of_birth(salsecond.getDate_of_birth()); 
		        	salarymain.setDate_of_joining(salsecond.getDate_of_joining()); 
		        	
		        	salarymain.setSpf_acct_no(salsecond.getSpf_acct_no()); 
		        	salarymain.setUrn_no(salsecond.getUrn_no()); 
		        	salarymain.setRecord_date(salsecond.getRecord_date());  
		        	salarymain.setSalary_month(salsecond.getSalary_month()); // First setting
		        	//salarymain.setSalary_month(salsecond.getSalary_month()); // Second setting
		        	
		        	

		        	//salarymain.setNo_of_days(salsecond.getNo_of_days()); 
		        	salarymain.setNo_of_days(salsecond.getNo_of_days()); 
		        	//System.out.println("dfghjklpppppppppppppppppp"+lastDay);
		        	//salarymain.setDays_paid(salsecond.getDays_paid());  
		        	salarymain.setDays_paid(salsecond.getDays_paid()); 
		        	//System.out.println("dfghjklpppppppppppppppppp"+lastDay);
		        	salarymain.setLoss_of_pay(salsecond.getLoss_of_pay());
		        	salarymain.setPayment_mode(salsecond.getPayment_mode());  
		        	salarymain.setBank_name(salsecond.getBank_name());  
		        	salarymain.setBank_acct_no(salsecond.getBank_acct_no());  
		        	salarymain.setBasic_pay(salsecond.getBasic_pay());
		        	salarymain.setHra(salsecond.getHra()); 
		        	salarymain.setSpl_allow(salsecond.getSpl_allow());
		        	salarymain.setMedi_reimb(salsecond.getMedi_reimb());
	 
		        	salarymain.setConv_allow(salsecond.getConv_allow());
		        	salarymain.setLunch_allow(salsecond.getLunch_allow());
		        	salarymain.setEdu_allow(salsecond.getEdu_allow());  
		        	salarymain.setBuss_attire(salsecond.getBuss_attire());  
		        	salarymain.setCar_maint(salsecond.getCar_maint());  
		        	salarymain.setLeave_travel_allow(salsecond.getLeave_travel_allow());
		        	salarymain.setOutstn_allow(salsecond.getOutstn_allow());
		        	salarymain.setAnnual_loyal_bonus(salsecond.getAnnual_loyal_bonus());
		        	
		        	salarymain.setOtr_allow(salsecond.getOtr_allow());
		        	salarymain.setGross_salary(salsecond.getGross_salary());
		        	salarymain.setSpf(salsecond.getSpf());
		        	salarymain.setTds(salsecond.getTds());
		        	salarymain.setProf_tax(salsecond.getProf_tax());
		        	salarymain.setEsi(salsecond.getEsi());
		        	salarymain.setRecovery(salsecond.getRecovery());
		        	salarymain.setOtr_ded(salsecond.getOtr_ded());
		        	salarymain.setTotal_deductions(salsecond.getTotal_deductions());
		        	salarymain.setNet_salary(salsecond.getNet_salary());
		        	salarymain.setDate_of_pay(salsecond.getDate_of_pay());
		        	salarymain.setCum_tds_fy(salsecond.getCum_tds_fy());
		        	salarymain.setProv_it(salsecond.getProv_it());
		        	salarymain.setTax_due(salsecond.getTax_due());
		        	salarymain.setTax_per_month(salsecond.getTax_per_month());
		        	salarymain.setCtc_amt(salsecond.getCtc_amt());
		        	salarymain.setDecl_status(salsecond.getDecl_status());
		        	salarymain.setCtc_eff_date(salsecond.getCtc_eff_date());
		        	salarymain.setCtc_end_date(salsecond.getCtc_end_date());
		        	salarymain.setMobile_no(salsecond.getMobile_no());
		        	salarymain.setEmail_id(salsecond.getEmail_id());
		        	salarymain.setRecord_type(salsecond.getRecord_type());
		        	salarymain.setRecord_srl_no(salsecond.getRecord_srl_no());
		        	salarymain.setRecord_date(salsecond.getRecord_date());
		        	//salarymain.setRecord_date(salsecond.getRecord_date());
		        	//String d[]=(salsecond.getRecord_date().toString()).split(" ");
		        	//salarymain.setRecord_date(d[0]);
		        	salarymain.setPay_status(salsecond.getPay_status());
		        	salarymain.setEntity_flg(salsecond.getEntity_flg());  
		        	salarymain.setDel_flg(salsecond.getDel_flg());  
		        	salarymain.setEntry_time(salsecond.getEntry_time());  
		        	salarymain.setEntry_user(salsecond.getEntry_user());  
		        	salarymain.setModify_flg(salsecond.getModify_flg());  
		        	salarymain.setModify_time(salsecond.getModify_time());  
		        	salarymain.setModify_user(salsecond.getModify_user());  
		        	salarymain.setVerify_time(salsecond.getVerify_time());  
		        	salarymain.setVerify_user(salsecond.getVerify_user());  
		        	salarymain.setRemarks(salsecond.getRemarks());
		        	salarymain.setAadhar_no(salsecond.getAadhar_no());
		        	
		        	
		        	

		       
		        	
		           up3.add(salarymain);

		           // up3.add(gstoverseas);
		        }

		       // System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIII"+up3);
		       //
		        Paystructurerep.saveAll(up3);

		        // gstBtmRep.getInsert(b,a);

		        System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" +up3 );
		        // int uniqueIdCounter = Integer.parseInt(b);
		        // int uniqueIdCounter1 = Integer.parseInt(a);

		       

		            // System.out.println(gstRep.getInsert(a,b));
		            String msg = "Data swapped Successfully";
		            return msg;

		      

		    } catch (Exception e) {
		        e.printStackTrace();
		        return "Error: " + e.getMessage();
		    }
		}
		



		
		@RequestMapping(value = "accountLedgerPost", method = { RequestMethod.GET, RequestMethod.POST })
		public String accountLedgerPost(@RequestParam(required = false) String formmode,
				@RequestParam(required = false) String resource_id, Model md, HttpServletRequest rq) {

			String userId = (String) rq.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("menu", "BTMHeaderMenu");
			
			if (formmode == null || formmode.equals("list")) {

				md.addAttribute("formmode", "list");

			} else if (formmode.equals("add")) {
				md.addAttribute("formmode", "add");
			} else if (formmode.equals("verify")) {

				md.addAttribute("formmode", "verify");

			} else if (formmode.equals("view")) {

				md.addAttribute("formmode", "view");

			} else if (formmode.equals("modify")) {
				md.addAttribute("formmode", "modify");

			}

			return "AccountLedgerPost";
		}

		byte[] setvalue;
		byte[] setImgValue;

		@RequestMapping(value = "fileupload", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public String handleFileUpload(@RequestParam("file") MultipartFile file,
				Document_Master_Entity document_Master_Entity) throws IOException {
			// Call service layer to handle file uploads and form data
			// Print the value of 'fileData' to the console

			// System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());

			
			byte[] byteArray = file.getBytes();
			this.setvalue = byteArray;

			return "success"; // Redirect to upload page after upload
		}
		
		
		@RequestMapping(value = "fileupload2", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public String handleFileUpload2(@RequestParam("file") MultipartFile file,
				Document_Master_Entity document_Master_Entity) throws IOException {
			// Call service layer to handle file uploads and form data
			// Print the value of 'fileData' to the console

			// System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());

			
			byte[] byteArray = file.getBytes();
			this.setImgValue = byteArray;

			return "success"; // Redirect to upload page after upload
		}

		@RequestMapping(value = "fileupload1", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public String handleFileUpload1(Document_Master_Entity document_Master_Entity) {
			// Call service layer to handle file uploads and form data
			// Print the value of 'fileData' to the console

			System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());
			document_Master_Entity.setDocument(setvalue);
			document_Master_Entity.setDoc_image(setImgValue);
			
			Document_Master_Repo.save(document_Master_Entity);
			return "success"; // Redirect to upload page after upload
		}
		
		
		@RequestMapping(value = "fileuploadoffervalue", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public String fileuploadoffervalue(@RequestParam(required = false) String ref,CandEvalFormEntity candEvalFormEntity) {
			// Call service layer to handle file uploads and form data
			// Print the value of 'fileData' to the console
			CandEvalFormEntity up=candEvalFormRep.getoffer(ref);
			System.out.println("getRef_no: " + up.getRef_no());
			up.setOffer(setImgoffer);
			//document_Master_Entity.setDoc_image(setImgValue);
			
			candEvalFormRep.save(up);
			return "success"; // Redirect to upload page after upload
		}
		byte[] setImgoffer;
		@RequestMapping(value = "fileuploadoffer", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public String fileuploadoffer(@RequestParam("file") MultipartFile file,@RequestParam(required = false) String ref,
				CandEvalFormEntity candEvalFormEntity) throws IOException {
			// Call service layer to handle file uploads and form data
			// Print the value of 'fileData' to the console
	System.out.println("jjjjjjjjjjjjkkkkkkkkkkkkkkkkkkkkkkk"+ref);
			// System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());
			System.out.println("ghghghggggggggggggggggggg"+file);

			
			byte[] byteArray = file.getBytes();
			this.setImgoffer = byteArray;

			return "success"; // Redirect to upload page after upload
		}
		
		
	
		
		
		@RequestMapping(value = "fileuploadappointmentvalue", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public String fileuploadappointmentvalue(@RequestParam(required = false) String ref,@RequestParam(required = false) String date,
				@RequestParam(required = false) String name,
				@RequestParam(required = false) String position,
				
	CandEvalFormEntity candEvalFormEntity) throws ParseException {
			// Call service layer to handle file uploads and form data
			// Print the value of 'fileData' to the console
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date dates=dateFormat.parse(date);
			System.out.println(date);
			
			CandEvalFormEntity up=candEvalFormRep.getappointment(ref);
			System.out.println("getRef_no: " + up.getRef_no());
			up.setRef_no(ref);
			up.setCandi_name(name);
			up.setDate_of_appointment_letter(dates);
			System.out.println(date);
			up.setPosition(position);
			System.out.println("getRef_no"+up.getEmail_id());
			up.setAddress(up.getAddress());
			up.setCtc(up.getCtc());
			up.setEmail_id(up.getEmail_id());
			up.setAppointment(setImgappoint1);
			up.setSalarystru(setImgappoint2);
			//document_Master_Entity.setDoc_image(setImgValue);
			
			candEvalFormRep.save(up);
			return "success"; // Redirect to upload page after upload
		}
		byte[] setImgappoint1;
		byte[] setImgappoint2;
		@RequestMapping(value = "fileuploadappointment", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public String fileuploadappointment(@RequestParam("file") MultipartFile file,@RequestParam("file1") MultipartFile file1,@RequestParam(required = false) String ref,
				CandEvalFormEntity candEvalFormEntity) throws IOException {
			// Call service layer to handle file uploads and form data
			// Print the value of 'fileData' to the console
	System.out.println("appoint"+ref);
			// System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());
			System.out.println("ment"+file);
			System.out.println("ment"+file1);

			
			byte[] byteArray = file.getBytes();
			this.setImgappoint1 = byteArray;
			byte[] byteArrays = file1.getBytes();
			this.setImgappoint2 = byteArrays;

			return "success"; // Redirect to upload page after upload
		}
		
		
		
		
		
		@PostMapping(value = "fileUploadPOMaster1")
		@ResponseBody
		public String uploadFilePO(@RequestParam("file") MultipartFile file, String screenId,
				@ModelAttribute Assosiate_Profile_Entity Assosiate_Profile_Entity , Model md, HttpServletRequest rq)
				throws FileNotFoundException, SQLException, IOException,NullPointerException {
			
			System.out.println("the testing   rest controller");

			System.out.println("fileSize" + file.getSize());

			if (file.getSize() < 50000000) {
				String userid = (String) rq.getSession().getAttribute("USERID");
				String msg = projectMasterServices.UploadPO(userid, file, userid, Assosiate_Profile_Entity);
				return msg;
			} else {
				return "File has not been successfully uploaded. Requires less than 128 KB size.";
			}

		}
		
		
		
		 @RequestMapping(
			      value = {"/bankexceldownload"},
			      method = {RequestMethod.GET},
			      produces = {"application/octet-stream"}
			   )
			   @ResponseBody
			   public ResponseEntity<InputStreamResource> bankexceldownload(HttpServletResponse response, @RequestParam(required = false) String filetype, @RequestParam(required = false) String a, @RequestParam(required = false) String years) throws IOException, SQLException, JRException {
			      response.setContentType("application/octet-stream");
			     

			      String fileName = "bankFileDownload" + a+ ".xlsx";
			      File eclFile = this.projectMasterServices.gettdsexcelbatchjob(fileName, filetype, a);
			      InputStreamResource resource = new InputStreamResource(new FileInputStream(eclFile));
			      HttpHeaders headers = new HttpHeaders();
			      headers.add("Content-Disposition", "attachment; filename=" + fileName);
			      return ((BodyBuilder)ResponseEntity.ok().headers(headers)).contentLength(eclFile.length()).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
			   }
		 @RequestMapping(value = "batchjobemail", method = { RequestMethod.GET, RequestMethod.POST })
			public ResponseEntity<String> batchjobemail(
					 @RequestParam(required = false) List<String> t, Model md)
					throws SQLException, ParseException, IOException {
				System.out.println("Hi");
				
				String to = "barath.p@bornfire.in";
				String from = "barath.p@bornfire.in";
				String username = "barath.p@bornfire.in"; // change accordingly
				String password = "Bornfire@123"; // change accordingly
				String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
				
				List<String> y = t;
				for (String bb : y) {
					System.out.println(bb);

				}

				System.out.println("sdfghjkl;");

				// Call sendMail method with correct parameters
				sendingmail_batchjob.batchjobsendingmail(from, host, to, username, password, y); // pass from, host,
																										// password, and to

				// Return success response
				return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
			}
		 
		 @RequestMapping(value = "payslipgeneration", method = RequestMethod.GET)
			@ResponseBody
			public InputStreamResource payslipgeneration(HttpServletResponse response,
					@RequestParam(value = "a", required = false) String a,@RequestParam(value = "t", required = false) String t,
					@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

				response.setContentType("application/octet-stream");

				InputStreamResource resource = null;
				System.out.println(a);
				try {
					logger.info("Getting download File :" + a + ", FileType :" + filetype + "");

					File repfile = projectMasterServices.payslip(a,t, filetype);

					response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
					resource = new InputStreamResource(new FileInputStream(repfile));
				} catch (JRException e) {
					// Log the error using the logger
					logger.error("Error generating TDS file", e);
					// Optionally, rethrow the exception or handle it as needed
					// throw new YourCustomException("Error generating TDS file", e);
				}

				return resource;
			}
		 
		 @Autowired
		 com.bornfire.services.Payslipgenerationemail Payslipgenerationemail;
		 
		 @RequestMapping(value = "payslipgenerationemail", method = { RequestMethod.GET, RequestMethod.POST })
			public ResponseEntity<String> payslipgenerationemails(@RequestParam(required = false) String a,
					@RequestParam(required = false) String d, @RequestParam(required = false) List<String> t, Model md)
					throws SQLException, ParseException, IOException {
				System.out.println("Hi");
				
				String to = null;
				String from = "barath.p@bornfire.in";
				String username = "barath.p@bornfire.in"; // change accordingly
				String password = "Bornfire@123"; // change accordingly
				String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
				String ref_no = d;
				System.out.println("refno"+ ref_no);
				List<String> y = t;
				for (String bb : y) {
					System.out.println(bb);

				}

				System.out.println("sdfghjkl;");

				// Call sendMail method with correct parameters
				Payslipgenerationemail.Payslipgenerationemails(from, host, to, username, password, ref_no,y); // pass from, host,
																										// password, and to

				// Return success response
				return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
			}
		 
		 @RequestMapping(value = "paypredium", method = RequestMethod.GET)
			@ResponseBody
			public InputStreamResource paypredium(HttpServletResponse response,
					@RequestParam(value = "a", required = false) String a,
					@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

				response.setContentType("application/octet-stream");

				InputStreamResource resource = null;
				System.out.println(a);
				try {
					logger.info("Getting download File :" + a + ", FileType :" + filetype + "");

					File repfile = projectMasterServices.payslippredim(a, filetype);

					response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
					resource = new InputStreamResource(new FileInputStream(repfile));
				} catch (JRException e) {
					// Log the error using the logger
					logger.error("Error generating TDS file", e);
					// Optionally, rethrow the exception or handle it as needed
					// throw new YourCustomException("Error generating TDS file", e);
				}

				return resource;
			}
		  @PostMapping("/sendapprovalstages")
		  @ResponseBody
		    public String receiveData(@RequestBody List<Map<String, String>> tableData) {

				System.out.println("sdfghjkl;");
		        String msg=placementServices.sendapprovlstages(tableData);
		        return msg;
		    }
		 

			@RequestMapping(value = "deletestages", method = { RequestMethod.GET, RequestMethod.POST })
			public String deletestages(@RequestParam(required = false) String formmode) {
		String  msg=placementServices.deletestages();
		  return msg;
		  
			}
			
			@RequestMapping(value = "salary_parameters", method = { RequestMethod.GET, RequestMethod.POST })
			public String salary_parameters(@RequestParam(required = false) String formmode,
					@RequestParam(required = false) BigDecimal srl_no, Model md, HttpServletRequest req) throws ParseException {
				String userId = (String) req.getSession().getAttribute("USERID");
				
				if (formmode == null || formmode.equals("list")) {
					md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
					md.addAttribute("formmode", "list");
			        List<salary_parameter> parameters = salary_parameter_Rep.getdata();
			        md.addAttribute("getdatas", parameters);
			        
				} else if (formmode.equals("add")) {
					md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
					md.addAttribute("formmode", formmode);
					List<String> group = Taxation_parameter_rep.getDistinctCountries();
						md.addAttribute("countryList", group);
					
				} else if (formmode.equals("edit")) {
					md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				       salary_parameter paramview = salary_parameter_Rep.getview(srl_no);
				       md.addAttribute("paramview", paramview);
					md.addAttribute("formmode", "edit");
					List<String> group = Taxation_parameter_rep.getDistinctCountries();
					md.addAttribute("countryList", group);

				}else if (formmode.equals("view")) {
					md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			       salary_parameter paramview = salary_parameter_Rep.getview(srl_no);
					md.addAttribute("paramview", paramview);
					md.addAttribute("formmode", "view");
				}else if (formmode.equals("verify")) {
					md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
					 salary_parameter paramview = salary_parameter_Rep.getview(srl_no);
						md.addAttribute("paramview", paramview);
						md.addAttribute("formmode", "verify");
				}else if (formmode.equals("delete")) {
					md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
					 salary_parameter paramview = salary_parameter_Rep.getview(srl_no);
						md.addAttribute("paramview", paramview);
						md.addAttribute("formmode", "delete");
				}

				return "SalaryParameters";
			}
			
			@RequestMapping(value = "add_salary_parameters", method = RequestMethod.POST)
			@ResponseBody
			public String add_salary_parameters(@RequestParam(required = false) String formmode,
			@ModelAttribute salary_parameter salary_parameter) throws NoSuchAlgorithmException, InvalidKeySpecException {

				String msg="";
			if (formmode.equals("add")) {
				System.out.println("enter the add");
				 msg=placementServices.addparam(salary_parameter,formmode);
			} else if (formmode.equals("edit")) {
				System.out.println("enter the edit");
				 msg=placementServices.addparam(salary_parameter,formmode);
			}else if (formmode.equals("verify")) {
				System.out.println("enter the verify");
				 msg=placementServices.addparam(salary_parameter,formmode);
			}else if (formmode.equals("delete")) {
				System.out.println("enter the delete");
				 msg=placementServices.addparam(salary_parameter,formmode);
			}
				return msg;
			}
			@RequestMapping(value = "getcoutriesdata", method = { RequestMethod.GET, RequestMethod.POST })
			@ResponseBody
			public List<Taxation_parameters> getcoutriesdata(@RequestParam(required = false) String selectedgroup,Model md) {
				
				System.out.println("enter the controll"+selectedgroup);
				 List<Taxation_parameters> countryList = Taxation_parameter_rep.getcountryrows(selectedgroup);
				
					return countryList;
			}
			
			
			@RequestMapping(value = "Taxation_parameters", method = { RequestMethod.GET, RequestMethod.POST })
			public String Taxation_parameters(@RequestParam(required = false) String formmode, @RequestParam(required = false) String emp_no,
					Model md, HttpServletRequest rq,@RequestParam(required = false) String group_name) {
				String userId = (String) rq.getSession().getAttribute("USERID");
				if (formmode== null || formmode.equals("add")) {
					 md.addAttribute("formmode", "add");
			        List<Taxation_parameters> parameters = Taxation_parameter_rep.getdata();
			        md.addAttribute("getdatas", parameters);
					System.out.println("enter the add");
					md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				}else if (formmode.equals("edit")) {
					md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
					List<Taxation_parameters> paramview = Taxation_parameter_rep.getview(group_name);
				       md.addAttribute("paramview", paramview);
					md.addAttribute("formmode", "edit");
				}else if (formmode.equals("verify")) {
					md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
					List<Taxation_parameters> paramview = Taxation_parameter_rep.getview(group_name);
					md.addAttribute("paramview", paramview);
					md.addAttribute("formmode", "verify");
				}else if (formmode.equals("delete")) {
					md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
					List<Taxation_parameters> paramview = Taxation_parameter_rep.getview(group_name);
					md.addAttribute("paramview", paramview);
					md.addAttribute("formmode", "delete");
				}else if (formmode.equals("view")) {
					md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
					List<Taxation_parameters> paramview = Taxation_parameter_rep.getview(group_name);
					md.addAttribute("paramview", paramview);
					md.addAttribute("formmode", "view");
				}
				return "TaxationParameters";
			}
			
			@PostMapping("/add_Tax_param")
		    @Transactional
		    public ResponseEntity<String> addTaxParam(@RequestParam(required = false) String formmode,
		    @RequestParam(required = false) BigDecimal srl_no, @RequestBody List<Taxation_parameters> taxationParametersList) {
				ResponseEntity<String>  msg=null;
		        if ("add".equals(formmode)) {
		        	msg=placementServices.add_Tax_param( taxationParametersList, formmode);
		        	
		        }  else  if ("edit".equals(formmode)) {
		        	msg=placementServices.add_Tax_param( taxationParametersList, formmode);
		        	System.out.println("the msg :"+msg);
		        }
		        return msg;
		    }
			

			@RequestMapping(value = "del_row_param", method = { RequestMethod.GET, RequestMethod.POST })
			@ResponseBody
			public String del_row_param(@RequestParam(required = false) BigDecimal srl_no,
			@RequestParam(required = false) String formmode,@RequestParam(required = false) String group_name){
				
				String msg="";
				if (formmode== null) {
				  msg=placementServices.deleteRow( srl_no);
				}else if (formmode.equals("verify")) {
					String h=group_name.substring(1);
					System.out.println("enter the verify:"+h);
					 msg=placementServices.ver_rows(h,formmode);
				}else if (formmode.equals("delete")) {
					String h=group_name.substring(1);
					System.out.println("enter the verify:"+group_name);
					 msg=placementServices.del_rows(h,formmode);
					 }
				 return msg;
			}
			
			@RequestMapping(value = "Performance_Evaluation", method = { RequestMethod.GET, RequestMethod.POST })
			public String Performance_Evaluation(@RequestParam(required = false) String formmode, @RequestParam(required = false) String ref_no,
			Model md, HttpServletRequest rq) {

				String userId = (String) rq.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				
				if (formmode == null || formmode.equals("list")) {
					md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
					md.addAttribute("del", perfomance_evaluation_rep.getCVFList());
					md.addAttribute("formmode", "list");

					md.addAttribute("dell", perfomance_evaluation_rep.getCVFList1(userId));
				} else if (formmode.equals("add")) {
					md.addAttribute("formmode", "add");
					List<String> list=resourceMasterRepo.getalist();
					md.addAttribute("userlist", list);
				} else if  (formmode.equals("view")) {
					md.addAttribute("cvfview", perfomance_evaluation_rep.getCVFform(ref_no));
					md.addAttribute("formmode", "view");
					
					LocalDate currentDate = LocalDate.now();
					System.out.println("The size:"+currentDate);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		            String formattedDate = currentDate.format(formatter);
					md.addAttribute("datee", formattedDate);
					
					md.addAttribute("submit",userId);

				} 
				return "Performance_Evaluation";
			}
			@RequestMapping(value = "Per_Eva_get_Name", method = { RequestMethod.GET, RequestMethod.POST })
			@ResponseBody
			public ResourceMaster Per_Eva_get_Name(@RequestParam(required = false) String empId,Model md) {
				
				System.out.println("enter the controll"+empId);
				ResourceMaster empname=resourceMasterRepo.getname(empId);
					return empname;
			}
			
			@RequestMapping(value = "Per_Eva_submit", method = { RequestMethod.GET, RequestMethod.POST })
			@ResponseBody
			public String Per_Eva_submit(@ModelAttribute perfomance_evaluation perfomance_evaluation,@RequestParam(required = false) String formmode, @RequestParam(required = false) String ref_no,
					Model md, HttpServletRequest rq) {
				String msg="";
				String userId = (String) rq.getSession().getAttribute("USERID");
				
				if (formmode.equals("add")) {
					msg=placementServices.evaluation(perfomance_evaluation,userId,"add");
					
				}else if(formmode.equals("modify")) {
					System.out.println("enter the controll");
					msg=placementServices.evaluation(perfomance_evaluation,ref_no,"modify");
					
				}
				return msg;
			}
			
			@RequestMapping(value = "ser_email", method = { RequestMethod.GET, RequestMethod.POST })
			public String ser_email(@RequestParam(required = false) String formmode,
					@RequestParam(required = false) BigDecimal resId, String empid, Model md, HttpServletRequest req) throws ParseException {

				String userId = (String) req.getSession().getAttribute("USERID");
				System.out.println("used id is :"+userId);
           md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
           if (formmode== null || formmode.equals("add")) {
				 md.addAttribute("formmode", "add");
				 
           }
				
				return "BAM_Services.html";
			}
			
			
			@RequestMapping(value = "ser_sms", method = { RequestMethod.GET, RequestMethod.POST })
			public String ser_sms(@RequestParam(required = false) String formmode,
					@RequestParam(required = false) BigDecimal resId, String empid, Model md, HttpServletRequest req) throws ParseException {

				String userId = (String) req.getSession().getAttribute("USERID");
				String username = (String) req.getSession().getAttribute("USERNAME");

				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				return "BAM_SMS.html";
			}

			@RequestMapping(value = "ser_alert", method = { RequestMethod.GET, RequestMethod.POST })
			public String ser_alert(@RequestParam(required = false) String formmode,
					@RequestParam(required = false) BigDecimal resId, String empid, Model md, HttpServletRequest req) throws ParseException {

				String userId = (String) req.getSession().getAttribute("USERID");
				String username = (String) req.getSession().getAttribute("USERNAME");

md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				return "BAM_ALERT.html";
			}
			@RequestMapping(value = "ser_chat", method = { RequestMethod.GET, RequestMethod.POST })
			public String ser_chat(@RequestParam(required = false) String formmode,
					@RequestParam(required = false) BigDecimal resId, String empid, Model md, HttpServletRequest req) throws ParseException {

				String userId = (String) req.getSession().getAttribute("USERID");
				String username = (String) req.getSession().getAttribute("USERNAME");

md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				return "BAM_CHAT.html";
			}
			@RequestMapping(value = "ser_events", method = { RequestMethod.GET, RequestMethod.POST })
			public String ser_events(@RequestParam(required = false) String formmode,
					@RequestParam(required = false) BigDecimal resId, String empid, Model md, HttpServletRequest req) throws ParseException {

				String userId = (String) req.getSession().getAttribute("USERID");
				String username = (String) req.getSession().getAttribute("USERNAME");

md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				return "BAM_EVENTS.html";
			}

			
			
			
			/*<----------------------- BAM MAINTAINANCE OPTION OPERATION  -------------------->*/
			
			@RequestMapping(value = "Catcodemaintain", method = { RequestMethod.GET, RequestMethod.POST })
			public String Catcodemaintain(@RequestParam(required = false) String formmode,String headcode,Model md, HttpServletRequest req)
			
					throws ParseException {
//				String EmpId = "U72900TN2017PTC115892";
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				
				Bamcategorycodemain newInventory = new Bamcategorycodemain();
		        newInventory.setEntry_user(userId);  // Set ENTRY_USER as the logged-in user
		        newInventory.setModify_user(userId); // Set MODIFY_USER as the logged-in user
		        newInventory.setVerify_user(userId); // Set VERIFY_USER as the logged-in user (optional for 
		        md.addAttribute("entryuser", newInventory);
		        
				if (formmode == null || formmode.equals("view")) {
					
					List<Bamcategorycodemain> Bamcategorycodemain = Bamcatcodemain.findAllOrderedBySlNo(); // Fetch your data
			        md.addAttribute("Bamcategorycodemain", Bamcategorycodemain);
					md.addAttribute("formmode", "list");
				

				} else if (formmode.equals("edit")) {

					md.addAttribute("formmode", "edit");
					md.addAttribute("Bamcategorycodemain", Bamcatcodemain.getbyId(headcode));

				}else if (formmode.equals("add")) {

					md.addAttribute("formmode", "add");
					md.addAttribute("Bamcategorycodemain", new Bamcategorycodemain());
				} else {

					md.addAttribute("formmode", formmode);
				}
				
				
				return "BAMCatCodeMain";
			}
			
			@RequestMapping(value = "deleteserialno", method = RequestMethod.POST)
			@ResponseBody
			public String deleteserialno(@RequestParam("ASN") String ASN) {

				System.out.println("The Asset is :"+ASN);
				String msg = adminOperServices.deletesrn(ASN);
				return msg;
			}
			@RequestMapping(value = "Catcodemaintainadd", method = RequestMethod.POST)
			@ResponseBody
			public String organizationMasterAdd(@RequestParam("formmode") String formmode,
					@ModelAttribute Bamcategorycodemain Bamcategorycodemain, Model md, HttpServletRequest rq,
					@RequestParam(required = false) String headcode,@RequestParam(required = false) String categorycode,
					@RequestParam(required = false) String subcategorycode) {

				System.out.println("The headcode is :"+headcode);
				System.out.println("The categorycode is :"+categorycode);
				System.out.println("The subcategorycode is :"+subcategorycode);
				String msg = adminOperServices.Catecodemaintain(Bamcategorycodemain, formmode,headcode,categorycode,subcategorycode);
				return msg;
			}
			
			/////DOCUMENT MANAGER
			@RequestMapping(value = "Documentmanager", method = { RequestMethod.GET, RequestMethod.POST })
			public String Bamdocumentmanager(@RequestParam(required = false) String formmode,String headcode, Model md, HttpServletRequest req)
					throws ParseException {
	//	String EmpId = "U72900TN2017PTC115892";
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				
				Bamdocumentmanager newInventory = new Bamdocumentmanager();
		        newInventory.setEntry_user(userId);  // Set ENTRY_USER as the logged-in user
		        newInventory.setModify_user(userId); // Set MODIFY_USER as the logged-in user
		        md.addAttribute("entryuser", newInventory);
		        
				if (formmode == null || formmode.equals("view")) {
					
					List<Bamdocumentmanager> Bamdocumentmanager = BAMDocmastrep.findAll(); // Fetch your data
			        md.addAttribute("Bamdocumentmanager", Bamdocumentmanager);
					md.addAttribute("formmode", "list");
				

				} else if (formmode.equals("edit")) {

					md.addAttribute("formmode", "edit");
					
					md.addAttribute("Bamdocumentmanager", BAMDocmastrep.findById(headcode).get());

				} else if (formmode.equals("verify")) {

					md.addAttribute("formmode", "verify");
					
					md.addAttribute("Bamdocumentmanager", BAMDocmastrep.findById(headcode).get());

				}else if (formmode.equals("add")) {

					md.addAttribute("formmode", "add");
					md.addAttribute("Bamdocumentmanager", new Bamdocumentmanager());

				} else {

					md.addAttribute("formmode", formmode);
				}

				return "BAMDocumentManager";
			}
			
			@RequestMapping(value = "DocManageradd", method = RequestMethod.POST)
			@ResponseBody
			public String organizationMasterAdd(@RequestParam("formmode") String formmode,
			                                    @ModelAttribute Bamdocumentmanager Bamdocumentmanager,
			                                    @RequestParam(required = false) MultipartFile documentFile,
			                                    Model md, HttpServletRequest rq) throws ParseException, IOException {
			    String msg = adminOperServices.DocManaaddedit(Bamdocumentmanager, formmode, documentFile);

			    return msg;
			}

			
			@RequestMapping(value = "InventoryMaster1", method = { RequestMethod.GET, RequestMethod.POST })
			public String InventoryMaster1(@RequestParam(required = false) String formmode, String headcode, Model md, HttpServletRequest req)
			        throws ParseException {

			    String userId = (String) req.getSession().getAttribute("USERID");
			    String username = (String) req.getSession().getAttribute("USERNAME");
			    md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			    //md.addAttribute("RoleMenu", resourceMasterRepo.getrole(username));
			    md.addAttribute("menu", "BTMHeaderMenu");

			    if (formmode == null || formmode.equals("view")) {

			        List<BAMInventorymaster> BAMInventorymaster = BAMInvmastrep.getall(); // Fetch your data
			        md.addAttribute("BAMInventorymaster", BAMInventorymaster);
			        md.addAttribute("formmode", "list");

			    } else if (formmode.equals("add")) {
			        System.out.println("Formmode :" + formmode);

			        BAMInventorymaster newInventory = new BAMInventorymaster();
			        newInventory.setEntry_user(userId);  // Set ENTRY_USER as the logged-in user
			        newInventory.setModify_user(userId); // Set MODIFY_USER as the logged-in user
			        newInventory.setVerify_user(userId); // Set VERIFY_USER as the logged-in user (optional for add)

			        md.addAttribute("formmode", "add");
			        md.addAttribute("BAMInventorymaster", newInventory);

			        List<Bamcategorycodemain> assetsrlno = Bamcatcodemain.getall();
			        List<String> srl = BAMInvmastrep.getdatas();
			        md.addAttribute("lists", srl);
			        md.addAttribute("assetsrlno", assetsrlno);
			        String assetsrlno1 = BAMInvmastrep.findLatestAssetSerialNumber();
			        md.addAttribute("assetsrlno1", assetsrlno1);

			    } else if (formmode.equals("edit")) {
			        BAMInventorymaster inventory = BAMInvmastrep.findById(headcode).get();
			        inventory.setModify_user(userId); // Set MODIFY_USER as the logged-in user

			        md.addAttribute("formmode", "edit");
			        md.addAttribute("BAMInventorymaster", inventory);

			    } else if (formmode.equals("verify")) {
			        BAMInventorymaster inventory = BAMInvmastrep.findById(headcode).get();
			        inventory.setVerify_user(userId); // Set VERIFY_USER as the logged-in user

			        md.addAttribute("formmode", "verify");
			        md.addAttribute("BAMInventorymaster", inventory);

			    } else {
			        md.addAttribute("formmode", formmode);
			    }

			    return "BAMInventoryMana";
			}

			@RequestMapping(value = "InventoryMaster_2", method = { RequestMethod.GET, RequestMethod.POST })
			public String InventoryMaster_2(@RequestParam(required = false) String formmode,String headcode, Model md, HttpServletRequest req)
					throws ParseException {

				
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				if (formmode == null || formmode.equals("view")) {
					
					List<BAMInventorymaster> BAMInventorymaster = BAMInvmastrep.findAll(); // Fetch your data
			        md.addAttribute("BAMInventorymaster", BAMInventorymaster);
					md.addAttribute("formmode", "list");
				

				} else if (formmode.equals("edit")) {

					md.addAttribute("formmode", "edit");
					md.addAttribute("BAMInventorymaster", BAMInvmastrep.findById(headcode).get());

				}else if (formmode.equals("verify")) {

					md.addAttribute("formmode", "verify");
					md.addAttribute("BAMInventorymaster", BAMInvmastrep.findById(headcode).get());

				}  else {

					md.addAttribute("formmode", formmode);
				}

				return "BAMInventoryMana_1";
				
			}
			
			@RequestMapping(value = "InvMastadd", method = RequestMethod.POST)
			@ResponseBody
			public String organizationMasterAdd(@RequestParam("formmode") String formmode,
					@RequestParam(required = false) String de_m,
					@RequestParam("depr_percent") String depr_percent,
					@RequestParam("Invtype") String Invtype,
					@ModelAttribute BAMInventorymaster BAMInventorymaster, Model md, HttpServletRequest rq,
					@RequestParam(required = false) String headcode,@RequestParam(required = false) String category_code,
					@RequestParam(required = false) String sub_category_code) throws KeyManagementException, NoSuchAlgorithmException {

				String userId = (String) rq.getSession().getAttribute("USERID");
				String username = (String) rq.getSession().getAttribute("USERNAME");
				System.out.println("The headcode control : "+headcode);
				System.out.println("The categorycode  : "+category_code);
				System.out.println("The subcategorycode  : "+sub_category_code);
				
				System.out.println(de_m);
				String msg = adminOperServices.Invmastadd(BAMInventorymaster, formmode,Invtype,userId,username, de_m,
						                                  depr_percent,headcode,category_code,sub_category_code);
				return msg;
			}
			

			
		////Inventory Transfer
			@RequestMapping(value = "InventoryTransfer1", method = { RequestMethod.GET, RequestMethod.POST })
			public String InventoryTransfer1(@RequestParam(required = false) String formmode,String headcode, Model md, HttpServletRequest req)
					throws ParseException {

				
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				if (formmode == null || formmode.equals("view")) {
					
					List<Baminventorytransfer> BAMInventorymaster = BamInvtrnrep.getall(); // Fetch your data
			        md.addAttribute("BAMInventorymaster", BAMInventorymaster);
					md.addAttribute("formmode", "list");
					md.addAttribute("operation", "operation");
				

				} else if (formmode.equals("add")) {

						md.addAttribute("formmode", "add");
						md.addAttribute("BAMInventorymaster", new Baminventorytransfer());
						List<String>  srl=BAMInvmastrep.getdatas();
						md.addAttribute("lists", srl);
						 //List<Bamcategorycodemain> lists=Bamcatcodemain.getall();
						//md.addAttribute("assetsrlno", lists);
						String assetsrlno=BamInvtrnrep.findLatestAssetSerialNumber();
						md.addAttribute("assetsrlno2", assetsrlno);

				}else if (formmode.equals("edit")) {

					md.addAttribute("operation", "operation");
					md.addAttribute("formmode", "edit");
					md.addAttribute("BAMInventorymaster", BamInvtrnrep.findById(headcode).get());

				}else if (formmode.equals("verify")) {

					md.addAttribute("formmode", "verify");
					md.addAttribute("BAMInventorymaster", BamInvtrnrep.findById(headcode).get());

				}  else {

					md.addAttribute("formmode", formmode);
				}

				return "Baminventtran";
			}
			
			
			
			@RequestMapping(value = "InventoryTransferlist", method = { RequestMethod.GET, RequestMethod.POST })
			public String InventoryTransferlist(@RequestParam(required = false) String formmode,String headcode, Model md, HttpServletRequest req)
					throws ParseException {

				
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				if (formmode == null || formmode.equals("view")) {
					
					List<Baminventorytransfer> BAMInventorymaster = BamInvtrnrep.getlist(); // Fetch your data
			        md.addAttribute("BAMInventorymaster", BAMInventorymaster);
					md.addAttribute("formmode", "list");
					md.addAttribute("inquries", "inquries");
					
				}else if (formmode.equals("edit")) {

					md.addAttribute("inquries", "inquries");
					md.addAttribute("formmode", "edit");
					md.addAttribute("BAMInventorymaster", BamInvtrnrep.findById(headcode).get());

				} 

				return "Baminventtrans";
			}
			
			@RequestMapping(value = "InvTranadd", method = RequestMethod.POST)
			@ResponseBody
			public String organizationTranAdd(@RequestParam("formmode") String formmode,
					@RequestParam("Invtype") String Invtype,
					@ModelAttribute Baminventorytransfer Bamdocumentmanager, Model md, HttpServletRequest rq) {

				String userId = (String) rq.getSession().getAttribute("USERID");
				String username = (String) rq.getSession().getAttribute("USERINAME");
				String msg = adminOperServices.Invtranadd(Bamdocumentmanager, formmode,Invtype,userId,username);
				return msg;
			}	
	
			//--------sale and write-----//
					@RequestMapping(value = "Salesandwriteoff", method = { RequestMethod.GET, RequestMethod.POST })
					public String Salesandwriteoff(@RequestParam(required = false) String formmode,String headcode, Model md, HttpServletRequest req)
							throws ParseException {

						
						String userId = (String) req.getSession().getAttribute("USERID");
						md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
						md.addAttribute("menu", "BTMHeaderMenu");
						System.out.println("outside user id is" + userId);
						if (formmode == null || formmode.equals("view")) {
							
							List<Bamsaleandwrite> Bamsaleandwrite = bamsalerep.findAll(); // Fetch your data
					        md.addAttribute("Bamsaleandwrite", Bamsaleandwrite);
							md.addAttribute("formmode", "list");
						

						} else if (formmode.equals("add")) {
							
							Bamsaleandwrite newInventory = new Bamsaleandwrite();

					        newInventory.setEntry_user(userId);  // Set ENTRY_USER as the logged-in user
					        newInventory.setModify_user(userId); // Set MODIFY_USER as the logged-in user
					        newInventory.setVerify_user(userId); // Set VERIFY_USER as the logged-in user (optional for add)

							System.out.println("inside entry user id is" + newInventory.getEntry_user());

								md.addAttribute("formmode", "add");

								md.addAttribute("Bamsaleandwrite", newInventory);
								 
								  
								List<String>  srl=BAMInvmastrep.getdatas();
								md.addAttribute("lists", srl);
								String user = (String) req.getSession().getAttribute("USERID");
								md.addAttribute("user", user);
								
						}else if (formmode.equals("edit")) {

							md.addAttribute("formmode", "edit");
							md.addAttribute("Bamsaleandwrite", bamsalerep.findById(headcode).get());

						}else if (formmode.equals("verify")) {

							md.addAttribute("formmode", "verify");
							md.addAttribute("Bamsaleandwrite", bamsalerep.findById(headcode).get());

						}  else {
							md.addAttribute("formmode", formmode);
						}

						return "Bamsaleandwrite";
					}
					
					@RequestMapping(value = "SaleandWriteadd", method = RequestMethod.POST)
					@ResponseBody
					public String organizationSaleWriteAdd(@RequestParam("formmode") String formmode,
							@RequestParam("Invtype") String Invtype,
							@ModelAttribute Bamsaleandwrite Bamdocumentmanager, Model md, HttpServletRequest rq) {

						String userId = (String) rq.getSession().getAttribute("USERID");
						String username = (String) rq.getSession().getAttribute("USERNAME");
						String msg = adminOperServices.SaleWriteadd(Bamdocumentmanager, formmode,Invtype,userId,username);
						return msg;
					}
			
			@RequestMapping(value = "Transactionmaster", method = { RequestMethod.GET, RequestMethod.POST })
			public String Transactionmaster(@RequestParam(required = false) String formmode,
					@RequestParam(required = false) String Id,String headcode, Model md, HttpServletRequest req)
					throws ParseException {

				
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				if (formmode == null ||formmode.equals("add")) {
					
					BamTransactionmaster newInventory = new BamTransactionmaster();

			        newInventory.setEntry_user(userId);  // Set ENTRY_USER as the logged-in user
			        newInventory.setModify_user(userId); // Set MODIFY_USER as the logged-in user
			       

						md.addAttribute("formmode", "add");

						md.addAttribute("BamTransactionmaster", newInventory);

						List<String>  srl=BAMInvmastrep.getdatas();
						md.addAttribute("lists", srl);

				}else if (formmode.equals("list")) {

					md.addAttribute("formmode", "list");

					System.out.println("size  : "+BAM_TRAN_MAS_rep.getdata().size());
					md.addAttribute("assets", BAM_TRAN_MAS_rep.getdata());
				} else if (formmode.equals("view")) {

					md.addAttribute("formmode", "view");
					System.out.println("size  : "+BAM_TRAN_MAS_rep.getdata().size());
					md.addAttribute("paramview", BAM_TRAN_MAS_rep.getview(Id));
					
				} else {

					md.addAttribute("formmode", formmode);
				}

				return "BamTranmast";
			}
			
			@RequestMapping(value = "Batchjobs", method = { RequestMethod.GET, RequestMethod.POST })
			public String Batchjobs(@RequestParam(required = false) String formmode,String headcode, Model md, HttpServletRequest req)
					throws ParseException {

				LocalDate currentDate = LocalDate.now();
				Date dd =new Date();

				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				
				BAM_AssetFlows_Entity newInventory = new BAM_AssetFlows_Entity();
		        newInventory.setEntry_user(userId);  // Set ENTRY_USER as the logged-in user
		        newInventory.setModify_user(userId); // Set MODIFY_USER as the logged-in user
		        newInventory.setVerify_user(userId); // Set VERIFY_USER as the logged-in user (optional for add)
		        
		        md.addAttribute("entryuser", newInventory);
		        
				if (formmode == null || formmode.equals("view")) {
					List<BAM_AssetFlows_Entity> list= new ArrayList<BAM_AssetFlows_Entity>();
					List<BAM_AssetFlows_Entity> BAMBatchjobs=BAM_AssetFlows_rep.getDep(currentDate);
					if(BAMBatchjobs.size()>0) {
					System.out.println("date is :"+BAMBatchjobs.get(0).getAsst_exp_date());
					
					
					for(BAM_AssetFlows_Entity up:BAMBatchjobs) {
						 if (up.getAsst_exp_date() != null && up.getAsst_exp_date().before(dd)) {
					            // Set remarks based on date comparison
					            up.setGen_remarks("In-Active");
					        } else {
					            // Handle the case where `asst_exp_date` is null
					            up.setGen_remarks("Active");
					        }
					up.setAsset_serial_no(up.getAsset_serial_no());
					up.setGen_frequncy(up.getGen_frequncy());
					up.setGen_flow_strt_date(up.getGen_flow_strt_date());
					list.add(up);
					}
					
								//List<BAMBatchjobs> BAMBatchjobs = Bambatjobrep.findAll(); // Fetch your data
			        md.addAttribute("BAMBatchjobs", list);
					md.addAttribute("formmode", "list");
					}
					else {
						md.addAttribute("formmode", "list");
						md.addAttribute("batch", "no");
					}

				} else if (formmode.equals("schedule")) {

						md.addAttribute("formmode", "schedule");

						BAM_AssetFlows_Entity BAMBatchjobs=BAM_AssetFlows_rep.srl_noget(headcode);
						md.addAttribute("BAMBatchjobs", BAMBatchjobs);
						
						int number = Integer.valueOf(headcode);
						String srlno=String.valueOf(number-1);
						BAM_AssetFlows_Entity lastrundate=BAM_AssetFlows_rep.srl_noget(srlno);
						if (lastrundate == null || lastrundate.getGen_flow_strt_date() == null || lastrundate.getGen_flow_strt_date().equals("")) {
						    md.addAttribute("lastrundate", null);
						} else {
						    md.addAttribute("lastrundate", lastrundate.getGen_flow_strt_date());
						}
						
						List<BAM_AssetFlows_Entity>up=BAM_AssetFlows_rep.getview(BAMBatchjobs.getAsset_serial_no());
						md.addAttribute("startdate", up.get(0).getGen_flow_strt_date());
							
										}
				else if(formmode.equals("prev")){
					
					md.addAttribute("formmode", "prev");
					md.addAttribute("BAMBatchjobs",BAM_AssetFlows_rep.getprevbatchjobs(currentDate));
				}
										
				else {

					md.addAttribute("formmode", formmode);
				}

				return "Bambatchjob";
			}
			
			@RequestMapping(value = "Depriciation", method = { RequestMethod.GET, RequestMethod.POST })
			public String Depriciation(@RequestParam(required = false) String formmode,String headcode, Model md, HttpServletRequest req)
					throws ParseException {

				
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				if (formmode == null || formmode.equals("view")) {
					
					List<BAMDepriciation> BAMDepriciation = Bamdeprep.findAll(); // Fetch your data
			        md.addAttribute("BAMDepriciation", BAMDepriciation);
					md.addAttribute("formmode", "list");
				

				} else if (formmode.equals("singleview")) {

						md.addAttribute("formmode", "schedule");
						md.addAttribute("BAMDepriciation", Bamdeprep.findById(headcode).get());

				}else {

					md.addAttribute("formmode", formmode);
				}

				return "BamDepriciation";
			}
			
			///////////////CLEARENCE
			
			//----------------1st-------------//
			@RequestMapping(value = "inventoryReport", method = { RequestMethod.GET, RequestMethod.POST })
			public String inventoryReport(@RequestParam(required = false) String formmode,
					@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
					@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
					throws ParseException {
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				List<BAMInventorymaster> BAMInventorymaster = BAMInvmastrep.getall();
				md.addAttribute("BAM",BAMInventorymaster);
				md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
				return "InventoryReport";
			}
			
			//FOR BOTH SOL ID AND REPORT DATE
			@RequestMapping(value = "inventoryReportDownload", method = RequestMethod.GET)
			@ResponseBody
			public InputStreamResource inventoryReportDownload(HttpServletResponse response,
			        @RequestParam(value = "sol_id", required = true) String sol_id,
			        @RequestParam(value = "report_date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date report_date,
			        @RequestParam(value = "report_type", required = true) String report_type) throws IOException, SQLException {

			    response.setContentType("application/octet-stream");

			    InputStreamResource resource = null;
			    try {
			        logger.info("Generating report for sol_id: " + sol_id + ", report_date: " + report_date + ", report_type: " + report_type);

			        // Assuming reportServices.getFileProfileMaster accepts these parameters
			        File repfile = reportServices.getFileInventoryRegister(sol_id,report_date,report_type);

			        response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			        resource = new InputStreamResource(new FileInputStream(repfile));

			    } catch (Exception e) {
			        e.printStackTrace();
			    }

			    return resource;
			}
			//FOR SOL ID
			@RequestMapping(value = "inventoryReportDownload1", method = RequestMethod.GET)
			@ResponseBody
			public InputStreamResource inventoryReportDownload1(HttpServletResponse response,
			        @RequestParam(value = "sol_id", required = true) String sol_id,
			        @RequestParam(value = "report_type", required = true) String report_type) throws IOException, SQLException {

			    response.setContentType("application/octet-stream");

			    InputStreamResource resource = null;
			    try {
			        logger.info("Generating report for sol_id: " + sol_id + ", report_type: " + report_type);

			        // Assuming reportServices.getFileProfileMaster accepts these parameters
			        File repfile = reportServices.getFileInventoryRegister1(sol_id,report_type);

			        response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			        resource = new InputStreamResource(new FileInputStream(repfile));
			        
			    } catch (Exception e) {
			        e.printStackTrace();
			        
			    }

			    return resource;
			    
			}
			
			//for DATE
			@RequestMapping(value = "inventoryReportDownload2", method = RequestMethod.GET)
			@ResponseBody
			public InputStreamResource inventoryReportDownload2(HttpServletResponse response,
			        @RequestParam(value = "report_date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date report_date,
			        @RequestParam(value = "report_type", required = true) String report_type) throws IOException, SQLException {

			    response.setContentType("application/octet-stream");

			    InputStreamResource resource = null;
			    try {
			        logger.info("Generating report for  report_date: " + report_date + ", report_type: " + report_type);

			        // Assuming reportServices.getFileProfileMaster accepts these parameters
			        File repfile = reportServices.getFileInventoryRegister2(report_date,report_type);

			        response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			        resource = new InputStreamResource(new FileInputStream(repfile));
			        System.out.println("try block completed for date of inventory report");

			    } catch (Exception e) {
			        e.printStackTrace();
			    }

			    return resource;
			}


			
			

			//-------------2.transferReport----------//
			
			@RequestMapping(value = "transferReport", method = { RequestMethod.GET, RequestMethod.POST })
			public String transferReport(@RequestParam(required = false) String formmode,
					@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
					@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
					throws ParseException {
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
				return "TransferReport";
			}
			
			
			@RequestMapping(value = "transferReportDownload", method = RequestMethod.GET)
			@ResponseBody
			public ResponseEntity<InputStreamResource> transferReportDownload(HttpServletResponse response,
			        @RequestParam(value = "from_sol_id", required = true) String from_sol_id,
			        
			        @RequestParam(value = "report_date", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date report_date,
			        @RequestParam(value = "report_type", required = true) String report_type) throws IOException, SQLException {
			    try {
			        logger.info("Generating report for from_sol_id: " + from_sol_id + ", report_date: " + report_date + ", report_type: " + report_type);

			        File repfile = reportServices.getFileTransferRegister(from_sol_id, report_date, report_type);

			        HttpHeaders headers = new HttpHeaders();
			        headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
			        headers.setContentDispositionFormData(repfile.getName(), repfile.getName());

			        InputStreamResource resource = new InputStreamResource(new FileInputStream(repfile));
			        return new ResponseEntity<>(resource, headers, HttpStatus.OK);

			    } catch (Exception e) {
			        e.printStackTrace();
			        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			    }
			}
			
			//for only date of transfer

			@RequestMapping(value = "transferReportDownload1", method = RequestMethod.GET)
			@ResponseBody
			public ResponseEntity<InputStreamResource> transferReportDownload1(HttpServletResponse response,
			        
			        @RequestParam(value = "report_date", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date report_date,
			        @RequestParam(value = "report_type", required = true) String report_type) throws IOException, SQLException {
			    try {
			        logger.info("Generating report for from_report_date: " + report_date + ", report_type: " + report_type);

			        File repfile = reportServices.getFileTransferRegister1(report_date, report_type);

			        HttpHeaders headers = new HttpHeaders();
			        headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
			        headers.setContentDispositionFormData(repfile.getName(), repfile.getName());

			        InputStreamResource resource = new InputStreamResource(new FileInputStream(repfile));
			        return new ResponseEntity<>(resource, headers, HttpStatus.OK);

			    } catch (Exception e) {
			        e.printStackTrace();
			        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			    }
			}
			//for from sol id
			
			@RequestMapping(value = "transferReportDownload2", method = RequestMethod.GET)
			@ResponseBody
			public ResponseEntity<InputStreamResource> transferReportDownload2(HttpServletResponse response,
			        @RequestParam(value = "from_sol_id", required = true) String from_sol_id,
			        @RequestParam(value = "report_type", required = true) String report_type) throws IOException, SQLException {
			    try {
			        logger.info("Generating report for from_sol_id: " + from_sol_id + ", report_type: " + report_type);

			        File repfile = reportServices.getFileTransferRegister2(from_sol_id, report_type);

			        HttpHeaders headers = new HttpHeaders();
			        headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
			        headers.setContentDispositionFormData(repfile.getName(), repfile.getName());

			        InputStreamResource resource = new InputStreamResource(new FileInputStream(repfile));
			        return new ResponseEntity<>(resource, headers, HttpStatus.OK);

			    } catch (Exception e) {
			        e.printStackTrace();
			        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			    }
			}

			

			//----------saleReport---------//
			
			@RequestMapping(value = "saleReport", method = { RequestMethod.GET, RequestMethod.POST })
			public String saleReport(@RequestParam(required = false) String formmode,
					@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
					@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
					throws ParseException {
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
				return "SaleReport";
			}
			
			
			@RequestMapping(value = "saleReportDownload", method = RequestMethod.GET)
			@ResponseBody
			public InputStreamResource saleReportDownload(HttpServletResponse response,
			        @RequestParam(value = "asset_serial_no", required = true) String asset_serial_no,
			        @RequestParam(value = "report_date", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date report_date,
			        @RequestParam(value = "report_type", required = true) String report_type) throws IOException, SQLException {

			    response.setContentType("application/octet-stream");

			    InputStreamResource resource = null;
			    try {
			        logger.info("Generating report for asset_serial_no: " + asset_serial_no + ", report_date: " + report_date + ", report_type: " + report_type);

			        // Assuming reportServices.getFileProfileMaster accepts these parameters
			        File repfile = reportServices.getFileSaleRegister(asset_serial_no, report_date, report_type);

			        response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			        resource = new InputStreamResource(new FileInputStream(repfile));

			    } catch (Exception e) {
			        e.printStackTrace();
			    }

			    return resource;
			}
			//for asset srl no
			@RequestMapping(value = "saleReportDownload1", method = RequestMethod.GET)
			@ResponseBody
			public InputStreamResource saleReportDownload1(HttpServletResponse response,
			        @RequestParam(value = "asset_serial_no", required = true) String asset_serial_no,
			        @RequestParam(value = "report_type", required = true) String report_type) throws IOException, SQLException {

			    response.setContentType("application/octet-stream");

			    InputStreamResource resource = null;
			    try {
			        logger.info("Generating report for asset_serial_no: " + asset_serial_no +", report_type: " + report_type);

			        // Assuming reportServices.getFileProfileMaster accepts these parameters
			        File repfile = reportServices.getFileSaleRegister1(asset_serial_no, report_type);

			        response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			        resource = new InputStreamResource(new FileInputStream(repfile));

			    } catch (Exception e) {
			        e.printStackTrace();
			    }

			    return resource;
			}
			
			
			//for date
			@RequestMapping(value = "saleReportDownload2", method = RequestMethod.GET)
			@ResponseBody
			public InputStreamResource saleReportDownload2(HttpServletResponse response,
			        @RequestParam(value = "report_date", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date report_date,
			        @RequestParam(value = "report_type", required = true) String report_type) throws IOException, SQLException {

			    response.setContentType("application/octet-stream");

			    InputStreamResource resource = null;
			    try {
			        logger.info("Generating report for  report_date: " + report_date + ", report_type: " + report_type);

			        // Assuming reportServices.getFileProfileMaster accepts these parameters
			        File repfile = reportServices.getFileSaleRegister2(report_date, report_type);

			        response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			        resource = new InputStreamResource(new FileInputStream(repfile));

			    } catch (Exception e) {
			        e.printStackTrace();
			    }

			    return resource;
			}

        //---------writeoffReport---------//
			@RequestMapping(value = "writeoffReport", method = { RequestMethod.GET, RequestMethod.POST })
			public String writeoffReport(@RequestParam(required = false) String formmode,
					@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
					@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
					throws ParseException {
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
				return "WriteoffReport";
			}
			
			
			@RequestMapping(value = "writeoffReportDownload", method = RequestMethod.GET)
			@ResponseBody
			public InputStreamResource writeoffReportDownload(HttpServletResponse response,
			        @RequestParam(value = "asset_serial_no", required = true) String asset_serial_no,
			        @RequestParam(value = "report_date", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date report_date,
			        @RequestParam(value = "report_type", required = true) String report_type) throws IOException, SQLException {

			    response.setContentType("application/octet-stream");

			    InputStreamResource resource = null;
			    try {
			        logger.info("Generating report for asset_serial_no: " + asset_serial_no + ", report_date: " + report_date + ", report_type: " + report_type);

			        // Assuming reportServices.getFileProfileMaster accepts these parameters
			        File repfile = reportServices.getFileWriteoffRegister(asset_serial_no, report_date, report_type);

			        response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			        resource = new InputStreamResource(new FileInputStream(repfile));

			    } catch (Exception e) {
			        e.printStackTrace();
			    }

			    return resource;
			}
			
			//for asset srl no(write off)
			@RequestMapping(value = "writeoffReportDownload1", method = RequestMethod.GET)
			@ResponseBody
			public InputStreamResource writeoffReportDownload1(HttpServletResponse response,
			        @RequestParam(value = "asset_serial_no", required = true) String asset_serial_no,
			        @RequestParam(value = "report_type", required = true) String report_type) throws IOException, SQLException {

			    response.setContentType("application/octet-stream");

			    InputStreamResource resource = null;
			    try {
			        logger.info("Generating report for asset_serial_no: " + asset_serial_no + ", report_type: " + report_type);

			        // Assuming reportServices.getFileProfileMaster accepts these parameters
			        File repfile = reportServices.getFileWriteoffRegister1(asset_serial_no,report_type);

			        response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			        resource = new InputStreamResource(new FileInputStream(repfile));

			    } catch (Exception e) {
			        e.printStackTrace();
			    }

			    return resource;
			}

			// for date(write off)
			
			@RequestMapping(value = "writeoffReportDownload2", method = RequestMethod.GET)
			@ResponseBody
			public InputStreamResource writeoffReportDownload2(HttpServletResponse response,
			        @RequestParam(value = "report_date", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date report_date,
			        @RequestParam(value = "report_type", required = true) String report_type) throws IOException, SQLException {

			    response.setContentType("application/octet-stream");

			    InputStreamResource resource = null;
			    try {
			        logger.info("Generating report for report_date: " + report_date + ", report_type: " + report_type);

			        // Assuming reportServices.getFileProfileMaster accepts these parameters
			        File repfile = reportServices.getFileWriteoffRegister2(report_date, report_type);

			        response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			        resource = new InputStreamResource(new FileInputStream(repfile));

			    } catch (Exception e) {
			        e.printStackTrace();
			    }

			    return resource;
			}
			
			
			//deprisation	
			@RequestMapping(value = "depreciationReport", method = { RequestMethod.GET, RequestMethod.POST })
			public String depreciationReport(@RequestParam(required = false) String formmode,
					@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
					@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
					throws ParseException {
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
				return "DeprisationReport";
			}
			
			//FOR BOTH asset srl no and date
			
			@RequestMapping(value = "DepreciationReportDownload", method = RequestMethod.GET)
			@ResponseBody
			public InputStreamResource DepreciationReportDownload(HttpServletResponse response,
			        @RequestParam(value = "asset_serial_no", required = true) String asset_serial_no,
			        @RequestParam(value = "report_date", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date report_date,
			        @RequestParam(value = "report_type", required = true) String report_type) throws IOException, SQLException {

			    response.setContentType("application/octet-stream");

			    InputStreamResource resource = null;
			    try {
			        logger.info("Generating report for asset_serial_no: " + asset_serial_no + ", report_date: " + report_date + ", report_type: " + report_type);

			        // Assuming reportServices.getFileProfileMaster accepts these parameters
			        File repfile = reportServices.getFileDepreciationReport(asset_serial_no, report_date, report_type);

			        response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			        resource = new InputStreamResource(new FileInputStream(repfile));

			    } catch (Exception e) {
			        e.printStackTrace();
			    }

			    return resource;
			}
			
			//for asset srl no (depreciation)
			@RequestMapping(value = "DepreciationReportDownload1", method = RequestMethod.GET)
			@ResponseBody
			public InputStreamResource DepreciationReportDownload1(HttpServletResponse response,
			        @RequestParam(value = "asset_serial_no", required = true) String asset_serial_no,
			        @RequestParam(value = "report_type", required = true) String report_type) throws IOException, SQLException {

			    response.setContentType("application/octet-stream");

			    InputStreamResource resource = null;
			    try {
			        logger.info("Generating report for asset_serial_no: " + asset_serial_no + ", report_type: " + report_type);

			        // Assuming reportServices.getFileProfileMaster accepts these parameters
			        File repfile = reportServices.getFileDepreciationReport1(asset_serial_no, report_type);

			        response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			        resource = new InputStreamResource(new FileInputStream(repfile));

			    } catch (Exception e) {
			        e.printStackTrace();
			    }

			    return resource;
			}
			
			// for only date
			
			@RequestMapping(value = "DepreciationReportDownload2", method = RequestMethod.GET)
			@ResponseBody
			public InputStreamResource DepreciationReportDownload2(HttpServletResponse response,
			        @RequestParam(value = "report_date", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date report_date,
			        @RequestParam(value = "report_type", required = true) String report_type) throws IOException, SQLException {

			    response.setContentType("application/octet-stream");

			    InputStreamResource resource = null;
			    try {
			        logger.info("Generating report for report_date: " + report_date + ", report_type: " + report_type);

			        // Assuming reportServices.getFileProfileMaster accepts these parameters
			        File repfile = reportServices.getFileDepreciationReport2(report_date, report_type);

			        response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			        resource = new InputStreamResource(new FileInputStream(repfile));

			    } catch (Exception e) {
			        e.printStackTrace();
			    }

			    return resource;
			}


		//deprisation transfer
			@RequestMapping(value = "Depreciationtran", method = { RequestMethod.GET, RequestMethod.POST })
			public String Depreciationtran(@RequestParam(required = false) String formmode,
					@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
					@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
					throws ParseException {
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
				return "Depreciationtran";
			}
			//print Report
			@RequestMapping(value = "printReport", method = { RequestMethod.GET, RequestMethod.POST })
			public String printReport(@RequestParam(required = false) String formmode,
					@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
					@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
					throws ParseException {
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				md.addAttribute("allusers",  resourceMasterRepo.gettotaluser());
				return "printReport";
			}
					
			//Dash
			@RequestMapping(value = "dashboardMaster", method = { RequestMethod.GET, RequestMethod.POST })
			public String dashboardMaster(@RequestParam(required = false) String formmode ,
						@RequestParam(required = false) String screen_id, String resId, @RequestParam(required = false) Optional<Integer> page,
					@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
					throws ParseException {
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				if (formmode == null || formmode.equals("view")) {
					md.addAttribute("formmode", "list"); md.addAttribute("WorkAssignmentList",
							  dashboardRepository.getWorkAssignlist());
							 
					
					md.addAttribute("formmode", "list");

				}  /*else if (formmode.equals("list")) {
					  
					 md.addAttribute("formmode", "list"); md.addAttribute("WorkAssignmentList",
					  dashboardRepository.getWorkAssignlist());
					  
					  }*/
					 else if (formmode.equals("add")) {

					md.addAttribute("formmode", "add");
					md.addAttribute("profileManagers", dashboardRepository.getWorkAssignlist());
					
				} else if (formmode.equals("list1")) {

					md.addAttribute("formmode", "list1");
					md.addAttribute("WorkAssignmentList", dashboardRepository.getWorkAssignlist());

				} else if (formmode.equals("edit")) {

					md.addAttribute("formmode", "edit");
					md.addAttribute("WorkAssignmentList", dashboardRepository.getWorkAssignlist());
					md.addAttribute("WorkAssignment", dashboardRepository.getWorkAssign(screen_id));

				} else if (formmode.equals("view1")) {

					md.addAttribute("formmode", "view1");
					md.addAttribute("WorkAssignment", dashboardRepository.getWorkAssign(screen_id));

				}else if (formmode.equals("approveList")) {

					md.addAttribute("formmode", "approveList");
					md.addAttribute("WorkAssignmentList", dashboardRepository.getWorkAssignlist());
					md.addAttribute("WorkAssignment", dashboardRepository.getWorkAssign(screen_id));

				}else if (formmode.equals("view2")) {

					md.addAttribute("formmode", "view2");
					md.addAttribute("WorkAssignment", dashboardRepository.getWorkAssign(screen_id));

				}

				return "DashboardMaster";
			}
			
			@RequestMapping(value = "Dashadd", method = RequestMethod.POST)
			@ResponseBody
			public String organizationDashAdd(@RequestParam("formmode") String formmode,
					@RequestParam("Dashtype") String Dashtype,
					@ModelAttribute DashBoardEntity dashBoardEntity, Model md, HttpServletRequest rq) {

				String userId = (String) rq.getSession().getAttribute("USERID");
				String username = (String) rq.getSession().getAttribute("USERNAME");
				String msg = adminOperServices.Dashofadd(dashBoardEntity, formmode,Dashtype,userId,username);
				return msg;
			}	
			
			@RequestMapping(value = "/Graphs1", method = { RequestMethod.GET, RequestMethod.POST })
			public String Graphs1(Model md, HttpServletRequest req)
					throws ParseException {

				String USERID = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(USERID));

				
				return "BAM_Graphs1.html";
			}
			@RequestMapping(value = "/Categ_Graphs1", method = { RequestMethod.GET, RequestMethod.POST })
			public String Categ_Graphs1(Model md, HttpServletRequest req)
					throws ParseException {

				String USERID = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(USERID));

				
				return "BAM_Categ_Graphs1.html";
			}

@RequestMapping(value = "/Location_Graphs1", method = { RequestMethod.GET, RequestMethod.POST })
			public String Location_Graphs1(Model md, HttpServletRequest req)
					throws ParseException {

				String USERID = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(USERID));

				return "BAM_Location_Graphs1.html";
			}


@RequestMapping(value = "/Dep_Graphs1", method = { RequestMethod.GET, RequestMethod.POST })
public String Dep_Graphs1(Model md, HttpServletRequest req)
		throws ParseException {

	String USERID = (String) req.getSession().getAttribute("USERID");
	md.addAttribute("RoleMenu", resourceMasterRepo.getrole(USERID));

	
	return "BAM_Dep_Graphs1.html";
}



			
			//COA			
			@RequestMapping(value = "chartofaccount", method = { RequestMethod.GET, RequestMethod.POST })
			public String chartofaccount(@RequestParam(required = false) String formmode,
					@RequestParam(required = false) String asset_serial_no, String resId, @RequestParam(required = false) Optional<Integer> page,
					@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
					throws ParseException {
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				if (formmode == null || formmode.equals("view")) {

					md.addAttribute("formmode", "view");

				} else if (formmode.equals("list")) {

					md.addAttribute("formmode", "list");
					md.addAttribute("WorkAssignmentList", charofaccountrep.getWorkAssignlist());

				} else if (formmode.equals("add")) {

					md.addAttribute("formmode", "add");
					md.addAttribute("profileManagers", charofaccountrep.getWorkAssignlist());
					
				} else if (formmode.equals("list1")) {

					md.addAttribute("formmode", "list1");
					md.addAttribute("WorkAssignmentList", charofaccountrep.getWorkAssignlist());

				} else if (formmode.equals("edit")) {

					md.addAttribute("formmode", "edit");
					md.addAttribute("WorkAssignmentList", charofaccountrep.getWorkAssignlist());
					md.addAttribute("WorkAssignment", charofaccountrep.getWorkAssign(asset_serial_no));

				} else if (formmode.equals("view1")) {

					md.addAttribute("formmode", "view1");
					md.addAttribute("WorkAssignment", charofaccountrep.getWorkAssign(asset_serial_no));

				}else if (formmode.equals("approveList")) {

					md.addAttribute("formmode", "approveList");
					md.addAttribute("WorkAssignmentList", charofaccountrep.getWorkAssignlist());
					md.addAttribute("WorkAssignment", charofaccountrep.getWorkAssign(asset_serial_no));

				}else if (formmode.equals("view2")) {

					md.addAttribute("formmode", "view2");
					md.addAttribute("WorkAssignment", charofaccountrep.getWorkAssign(asset_serial_no));

				}

				return "chart of account";
			}
			
			@RequestMapping(value = "Chartadd", method = RequestMethod.POST)
			@ResponseBody
			public String organizationChartAdd(@RequestParam("formmode") String formmode,
					@RequestParam("Charttype") String Charttype,
					@ModelAttribute ChartOfAccounts chartOfAccounts, Model md, HttpServletRequest rq) {

				String userId = (String) rq.getSession().getAttribute("USERID");
				String username = (String) rq.getSession().getAttribute("USERNAME");
				String msg = adminOperServices.Chartofadd(chartOfAccounts, formmode,Charttype,userId,username);
				return msg;
			}
			
			@RequestMapping(value = "GeneralLedger", method = { RequestMethod.GET, RequestMethod.POST })
			public String GeneralLedger(@RequestParam(required = false) String formmode,
					@RequestParam(required = false) String glcode, Model md, HttpServletRequest req) throws ParseException {
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				if (formmode == null || formmode.equals("view")) {

					md.addAttribute("formmode", "view");

				} else if (formmode.equals("list")) {

					md.addAttribute("formmode", "list");
					md.addAttribute("BamGeneralLedger", BamGeneralLedgerRep.getRefCodelist());

				} else if (formmode.equals("view1")) {

					md.addAttribute("formmode", "view1");
					md.addAttribute("GeneralLedger", adminOperServices.getGeneralLedger(glcode));

				} else if (formmode.equals("list1")) {

					md.addAttribute("formmode", "list1");
					md.addAttribute("BamGeneralLedger", BamGeneralLedgerRep.getRefCodelist());

				} else if (formmode.equals("edit")) {

					md.addAttribute("formmode", "edit");
					md.addAttribute("GeneralLedger", adminOperServices.getGeneralLedger(glcode));

				} else if (formmode.equals("add")) {

					md.addAttribute("formmode", formmode);

				} else if (formmode.equals("deleteList")) {

					md.addAttribute("formmode", "deleteList");
					md.addAttribute("BamGeneralLedger", BamGeneralLedgerRep.getRefCodelist());

				} else if (formmode.equals("delete")) {

					md.addAttribute("formmode", "delete");
					md.addAttribute("GeneralLedger", adminOperServices.getGeneralLedger(glcode));
				}

				return "Generalledger";
			}
			
			@RequestMapping(value = "GeneralLedgerAdd", method = RequestMethod.POST)
			@ResponseBody
			public String GeneralLedgerAdd(@RequestParam("formmode") String formmode,@RequestParam(required = false) String glcode,
					@ModelAttribute BamGeneralLedger BamGeneralLedger, Model md, HttpServletRequest rq) {
				String msg = adminOperServices.addGeneralLedger(BamGeneralLedger, formmode, glcode);
				return msg;
			}
			
			 @RequestMapping(value = "asset_flows", method = { RequestMethod.GET, RequestMethod.POST })
			    public String asset_flows(@RequestParam(required = false) String formmode, Model md, HttpServletRequest req) throws ParseException {

			        String userId = (String) req.getSession().getAttribute("USERID");
			        md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			        
			        BAM_AssetFlows_Entity newInventory = new BAM_AssetFlows_Entity();
			        newInventory.setEntry_user(userId);  // Set ENTRY_USER as the logged-in user
			        newInventory.setModify_user(userId); // Set MODIFY_USER as the logged-in user
			        newInventory.setVerify_user(userId); // Set VERIFY_USER as the logged-in user (optional for add)
			        
			        md.addAttribute("entryuser", newInventory);

			        // Fetch the list of serial numbers from BAMInvmastrep
			        List<String> srl = BAMInvmastrep.getdatas();
			        md.addAttribute("lists", srl);
			        String assetsrlno1=BAMInvmastrep.findLatestAssetSerialNumber();
					md.addAttribute("assetsrlno", assetsrlno1);

			        Date lastTransferDate = BamInvtrnrep.getdatas();
				    md.addAttribute("lastTransferDate", lastTransferDate);
				    System.out.println("Last Transfer Date: " + lastTransferDate);

			        return "BAM_Asset_Flows.html";
			    }
			
			@PostMapping("/addAssets")
		    @Transactional
		    public ResponseEntity<String> addAssets(@RequestParam(required = false) String formmode,
		    @RequestParam(required = false) BigDecimal srl_no, @RequestBody List<BAM_AssetFlows_Entity> BAM_AssetFlows_Entity) {
				ResponseEntity<String>  msg=null;
		        if ("add".equals(formmode)) {
		        	msg=placementServices.add_Assets( BAM_AssetFlows_Entity, formmode);
		        	
		        }  else  if ("edit".equals(formmode)) {
		        	msg=placementServices.add_Assets( BAM_AssetFlows_Entity, formmode);
		        	System.out.println("the msg :"+msg);
		        }
		        return msg;
		    }
			
			@PostMapping("/addTransMast")
		    @Transactional
		    public ResponseEntity<String> addTransMast(@RequestParam(required = false) String formmode,
		    @RequestParam(required = false) BigDecimal srl_no, @RequestBody List<BAM_TRAN_MAS_ENTITY> BAM_TRAN_MAS_ENTITY) {
				ResponseEntity<String>  msg=null;
		        if ("add".equals(formmode)) {
		        	System.out.println("enter...");
		        	msg=placementServices.add_trans( BAM_TRAN_MAS_ENTITY, formmode);
		        	
		        }  else  if ("edit".equals(formmode)) {
		        	msg=placementServices.add_trans( BAM_TRAN_MAS_ENTITY, formmode);
		        	System.out.println("the msg :"+msg);
		        }
		        return msg;
		    }
			
			
			
			@RequestMapping(value = "assets_flows", method = { RequestMethod.GET, RequestMethod.POST })
			public String markOnDuty(@RequestParam(required = false) String formmode,
					@RequestParam(required = false) String Id,Model md, HttpServletRequest req)
					throws ParseException {
				String userId = (String) req.getSession().getAttribute("USERID");
				
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				

				 System.out.println("Asset is :"+formmode);
				
				BAM_AssetFlows_Entity newInventory = new BAM_AssetFlows_Entity();
		        newInventory.setEntry_user(userId);  // Set ENTRY_USER as the logged-in user
		        newInventory.setModify_user(userId); // Set MODIFY_USER as the logged-in user
		        newInventory.setVerify_user(userId); // Set VERIFY_USER as the logged-in user (optional for add)


				 if (formmode == null) {
						md.addAttribute("formmode", "list"); //List
						
				md.addAttribute("assets", BAM_AssetFlows_rep.getdata());
				 }
				 else if ("view".equals(formmode)) {
					 
					 System.out.println("Asset is :"+Id);
						md.addAttribute("formmode", "view");

						 System.out.println("Asset is :"+BAM_AssetFlows_rep.getview(Id).size());
				md.addAttribute("paramview", BAM_AssetFlows_rep.getview(Id));
				md.addAttribute("paramview1", newInventory);
				 }
				return "BTMMarkOnDuty";
			}
			
			@RequestMapping(value = "acquisition1", method = { RequestMethod.GET, RequestMethod.POST })
			public String acquisition1(@RequestParam(required = false) String formmode,
					@RequestParam(required = false) String Id,Model md, HttpServletRequest req)
					throws ParseException {
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");

				 System.out.println("Asset is :"+formmode);
				 if (formmode == null) {
						md.addAttribute("formmode", "list"); //List
						//md.addAttribute("assets",acquisitionsrep.getWorkAssignlist());
						
				 }else if ("view".equals(formmode)) {
					 System.out.println("Asset is :"+Id);
						md.addAttribute("formmode", "view");
						
				//md.addAttribute("paramview",acquisitionsrep.getWorkAssign(Id));
				 }
				 else if (formmode.equals("add")) {

						md.addAttribute("formmode", "add");
						md.addAttribute("Bamdocumentmanager", new Bamdocumentmanager());

					}
				return "BAM_Acquisition.html";
			}

			
			
			@RequestMapping(value = "acquisition", method = { RequestMethod.GET, RequestMethod.POST })
			public String acquisition(@RequestParam(required = false) String formmode,String headcode, Model md, HttpServletRequest req)
					throws ParseException {

			//	String EmpId = "U72900TN2017PTC115892";
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				if (formmode == null || formmode.equals("view")) {
					
					 // Fetch your data
			        md.addAttribute("Bamdocumentmanager", bamAcquisitionrep.getBamAcquisitionlist());
					md.addAttribute("formmode", "list");
				

				} else if (formmode.equals("edit")) {

					md.addAttribute("formmode", "edit");
					md.addAttribute("BamAcquisition", bamAcquisitionrep.getall(headcode));
					
					//md.addAttribute("Bamdocumentmanager", BAMDocmastrep.findById(headcode).get());

				} else if (formmode.equals("verify")) {

					md.addAttribute("formmode", "verify");
					
					md.addAttribute("Bamdocumentmanager", bamAcquisitionrep.getall(headcode));

				}else if (formmode.equals("add")) {

					md.addAttribute("formmode", "add");
					md.addAttribute("BamAcquisition", new BamAcquisition());

				} else {

					md.addAttribute("formmode", formmode);
				}

				return "BAM_Acquisition.html";
			}

			
			@RequestMapping(value = "acquisitionadd", method = RequestMethod.POST)
			@ResponseBody
			public String acquisitionAdd(@RequestParam("formmode") String formmode,
					@ModelAttribute BamAcquisition BamAcquisition, Model md, HttpServletRequest rq) {

				String msg = adminOperServices.BamAcquisitionadd(BamAcquisition, formmode);
				return msg;
			}
			
			
			
			@RequestMapping(value = "/Graphs", method = { RequestMethod.GET, RequestMethod.POST })
			public String Graphs(Model md, HttpServletRequest req)
					throws ParseException {

				String USERID = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(USERID));

				
				return "BAM_Graphs.html";
			}
			

			@RequestMapping(value = "Graphsdata", method = RequestMethod.GET)
			@ResponseBody
			public Map<String, Double> Graphsdata(@RequestParam(required = false) String month, String year) {
				
			    String stryear="01"+"-04"+"-"+year;
			    int endyears=Integer.parseInt(year)+1;
			    String endyear="31"+"-03-"+String.valueOf(endyears);
			    System.out.println(endyears);
			    System.out.println(stryear);

				double finalAPR=0;
				double finalMAY=0;
				double finalJUN=0;
				double finalJULY=0;
				double finalAUG=0;
				double finalSEP=0;double finalOCT=0;double finalNOV=0;double finalDEC=0;
				double finalJAN=0;double finalFEB=0;double finalMAR=0;
				
			    int totalinventory=BAMInvmastrep.gettotal(stryear,endyear);
				int APR=BAMInvmastrep.getAPR(stryear,endyear);
				
				  int MAY=BAMInvmastrep.getMAY(stryear,endyear);
				  int JUN=BAMInvmastrep.getJUN(stryear,endyear); 
				  int JULY=BAMInvmastrep.getJULY(stryear,endyear); 
				  int AUG=BAMInvmastrep.getAUG(stryear,endyear);
				  int SEP=BAMInvmastrep.getSEP(stryear,endyear);
int OCT=BAMInvmastrep.getOCT(stryear,endyear);
int NOV=BAMInvmastrep.getNOV(stryear,endyear);
int DEC=BAMInvmastrep.getDEC(stryear,endyear);
int JAN=BAMInvmastrep.getJAN(stryear,endyear);
int FEB=BAMInvmastrep.getFEB(stryear,endyear);
int MAR=BAMInvmastrep.getMAR(stryear,endyear);

				 System.out.println("APR: " + APR);
				 System.out.println("MAY: " + MAY);
				 System.out.println("JUN: " + JUN);
				 System.out.println("JULY: " + JULY);
				 System.out.println("AUG: " + AUG);
				 System.out.println("SEP: " + SEP);
				 System.out.println("OCT: " + OCT);
				 System.out.println("NOV: " + NOV);
				 System.out.println("DEC: " + DEC);
				 System.out.println("JAN: " + JAN);
				 System.out.println("FEB: " + FEB);
				 System.out.println("MAR: " + MAR);


				if(totalinventory!=0) {
						if (APR != 0) {
    finalAPR = (double) APR / totalinventory * 100;
}
if (MAY != 0) {
    finalMAY = (double) MAY / totalinventory * 100;
}
if (JUN != 0) {
    finalJUN = (double) JUN / totalinventory * 100;
}
if (JULY != 0) {
    finalJULY = (double) JULY / totalinventory * 100;
}
if (AUG != 0) {
    finalAUG = (double) AUG / totalinventory * 100;
}
if (SEP != 0) {
    finalSEP = (double) SEP / totalinventory * 100;
}
if (OCT != 0) {
    finalOCT = (double) OCT / totalinventory * 100;
}
if (NOV != 0) {
    finalNOV = (double) NOV / totalinventory * 100;
}
if (DEC != 0) {
    finalDEC = (double) DEC / totalinventory * 100;
}
if (JAN != 0) {
    finalJAN = (double) JAN / totalinventory * 100;
}
if (FEB != 0) {
    finalFEB = (double) FEB / totalinventory * 100;
}
if (MAR != 0) {
    finalMAR = (double) MAR / totalinventory * 100;
}

					}
				System.out.println("APR: " + finalAPR + "%, SEP: " + finalSEP + "%" );
				
				Map<String,Double> data= new HashMap<>();
				data.put("totalinventory", (double) totalinventory);
				data.put("APR", finalAPR);
				
				 data.put("MAY", finalMAY); 
				 data.put("JUN", finalJUN);
				 data.put("JULY",finalJULY); 
				 data.put("AUG", finalAUG);
				 data.put("SEP", finalSEP);
				 data.put("OCT", finalOCT); 
				 data.put("NOV", finalNOV);
				 data.put("DEC",finalDEC);
				 data.put("JAN", finalJAN);
				 data.put("FEB", finalFEB);
				 data.put("MAR", finalMAR);
				 
				 return data;
			}
			//below is for inventories sale and transfer pie chart
			@RequestMapping(value = "Graphsdatadashboard", method = RequestMethod.GET)
			@ResponseBody
			public Map<String, Double> Graphsdatadashboard(@RequestParam(required = false) String month, String year) {
				
				String stryear="01"+"-04"+"-"+year;
			    int endyears=Integer.parseInt(year)+1;
			    String endyear="31"+"-03-"+String.valueOf(endyears);
			    
				
			    String monthyear=month+"-"+year; 
			    System.out.println(monthyear);
				int totalinventory=BAMInvmastrep.gettotal(stryear,endyear);
				int newinventory=BAMInvmastrep.getdata(monthyear);
				int transfer=BamInvtrnrep.gettrndata(monthyear);
				int sale=bamsalerep.getsaledata(monthyear);
				double amountsale=0;
				double amounttranfer=0;
				double amountnewinventory=0;
				System.out.println("sale="+sale);
				System.out.println("transfer="+transfer);
				if(totalinventory!=0) {
						if(sale!=0) {
							amountsale = (double)sale/totalinventory*100;
						}
						 if(transfer!=0) {
							amounttranfer = (double)transfer/totalinventory*100;
						}
						 if(newinventory!=0) {
							amountnewinventory= (double)newinventory/totalinventory*100;
						}
					}
				System.out.println("prtotal="+totalinventory);
				System.out.println("prsale="+amountsale);
				System.out.println("prtran="+amounttranfer);
				System.out.println("prnew="+amountnewinventory);
				Map<String,Double> data= new HashMap<>();
				data.put("totalinventory", (double) totalinventory);
				data.put("sale",amountsale);
				data.put("transfer",amounttranfer);
				data.put("newinventory",amountnewinventory);
			    return data;
			}	
				
			@RequestMapping(value = "/Dep_Graphs", method = { RequestMethod.GET, RequestMethod.POST })
			public String Dep_Graphs(Model md, HttpServletRequest req)
					throws ParseException {

				String USERID = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(USERID));

				
				return "BAM_Dep_Graphs.html";
			}
			
			
			@RequestMapping(value = "Dep_Graphdata", method = RequestMethod.GET)
			@ResponseBody
			public Map<String, Double> Dep_Graphdata(@RequestParam(required = false) String month, String year) {
				
			    //String monthyear=year;
			    String jan="01-"+year;
			    String feb="02-"+year;
			    String mar="03-"+year;
			    String apr="04-"+year;
			    String may="05-"+year;
			    String jun="06-"+year;
			    String jul="07-"+year;
			    String Aug="08-"+year;
			    String sep="09-"+year;
			    String oct="10-"+year;
			    String nov="11-"+year;
			    String dec="12-"+year;
			  
			    String stryear="01"+"-04"+"-"+year;
			    int endyears=Integer.parseInt(year)+1;
			    String endyear="31"+"-03-"+String.valueOf(endyears);
			    
			    //System.out.println(monthyear);
				int totalinventory=BAM_AssetFlows_rep.gettotal(stryear,endyear);
				
				int depjan=BAM_AssetFlows_rep.getdep(jan);
				int depfeb=BAM_AssetFlows_rep.getdep(feb);
				int depmar=BAM_AssetFlows_rep.getdep(mar);
				int depapr=BAM_AssetFlows_rep.getdep(apr);
				int depmay=BAM_AssetFlows_rep.getdep(may);
				int depjun=BAM_AssetFlows_rep.getdep(jun);
				int depjul=BAM_AssetFlows_rep.getdep(jul);
				int depaug=BAM_AssetFlows_rep.getdep(Aug);
				int depsep=BAM_AssetFlows_rep.getdep(sep);
				int depoct=BAM_AssetFlows_rep.getdep(oct);
				int depnov=BAM_AssetFlows_rep.getdep(nov);
				int depdec=BAM_AssetFlows_rep.getdep(dec);	
				
				System.out.println(depjan);
				System.out.println("depfeb"+depfeb);
				System.out.println("depmar"+depmar);
				System.out.println("depapr"+depapr);
				System.out.println("depmay"+depmay);
				System.out.println("depjun"+depjun);
				System.out.println("depjul"+depjul);
				System.out.println("depaug"+depaug);
				System.out.println("depsep"+depsep);
				System.out.println("depoct"+depoct);
				System.out.println("depnov"+depnov);
				System.out.println("depdec"+depdec);
				
				
				
				System.out.println("TOTAL :"+totalinventory);
				
				
				int intialcostjan =BAM_AssetFlows_rep.getintialcost(jan);
				int intialcostfeb =BAM_AssetFlows_rep.getintialcost(feb);
				int intialcostmar =BAM_AssetFlows_rep.getintialcost(mar);
				System.out.println("intialcostmar :"+intialcostmar);
				int intialcostapr =BAM_AssetFlows_rep.getintialcost(apr);
				int intialcostmay =BAM_AssetFlows_rep.getintialcost(may);
				int intialcostjun =BAM_AssetFlows_rep.getintialcost(jun);
				int intialcostjul =BAM_AssetFlows_rep.getintialcost(jul);
				int intialcostAug =BAM_AssetFlows_rep.getintialcost(Aug);
				int intialcostsep =BAM_AssetFlows_rep.getintialcost(sep);
				int intialcostoct =BAM_AssetFlows_rep.getintialcost(oct);
				int intialcostnov =BAM_AssetFlows_rep.getintialcost(nov);
				int intialcostdec =BAM_AssetFlows_rep.getintialcost(dec);
				
				
				
				int bookvaluejan= BAM_AssetFlows_rep.getbookvalue(jan);
				int bookvaluefeb= BAM_AssetFlows_rep.getbookvalue(feb);
				int bookvaluemar= BAM_AssetFlows_rep.getbookvalue(mar);
				System.out.println("bookvaluemar :"+bookvaluemar);
				int bookvalueapr= BAM_AssetFlows_rep.getbookvalue(apr);
				int bookvaluemay= BAM_AssetFlows_rep.getbookvalue(may);
				int bookvaluejun= BAM_AssetFlows_rep.getbookvalue(jun);
				int bookvaluejul= BAM_AssetFlows_rep.getbookvalue(jul);
				int bookvalueAug= BAM_AssetFlows_rep.getbookvalue(Aug);
				int bookvaluesep= BAM_AssetFlows_rep.getbookvalue(sep);
				int bookvalueoct= BAM_AssetFlows_rep.getbookvalue(oct);
				int bookvaluenov= BAM_AssetFlows_rep.getbookvalue(nov);
				int bookvaluedec= BAM_AssetFlows_rep.getbookvalue(dec);
				
				double finaldepjan=0;
				
				if(bookvaluejan!=0 && intialcostjan!=0){
					finaldepjan=(double)bookvaluejan/intialcostjan*100;
				}
				 double finaldepfeb=(double)bookvaluefeb/intialcostfeb*100;
				 double finaldepmar=(double)bookvaluemar/intialcostmar*100;
				 System.out.println("finaldepmar :"+finaldepmar);
				 double finaldepapr=(double)bookvalueapr/intialcostapr*100;
				 double finaldepmay=(double)bookvaluemay/intialcostmay*100;
				 double finaldepjun=(double)bookvaluejun/intialcostjun*100;
				 double finaldepjul=(double)bookvaluejul/intialcostjul*100;
				 double finaldepAug=(double)bookvalueAug/intialcostAug*100;
				 double finaldepsep=(double)bookvaluesep/intialcostsep*100;
				 double finaldepoct=(double)bookvalueoct/intialcostnov*100;
				 double finaldepnov=(double)bookvaluenov/intialcostnov*100;
				 double finaldepdec=(double)bookvaluedec/intialcostdec*100;
				 
				 System.out.println("finaldepjan="+finaldepjan);
				 double finalassetjan=0;
				 double finalassetfeb=0;
				 double finalassetmar=0;
				 double finalassetapr=0;
				 double finalassetmay=0;
				 double finalassetjun=0;
				 double finalassetjul=0;
				 double finalassetaug=0;
				 double finalassetsep=0;
				 double finalassetoct=0;
				 double finalassetnov=0;
				 double finalassetdec=0;
				 
				 if(totalinventory!=0) {
				  finalassetjan=(double)depjan/totalinventory*100;
				  finalassetfeb=(double)depfeb/totalinventory*100;
				  finalassetmar=(double)depmar/totalinventory*100;
				 System.out.println("finalassetmar="+finalassetmar);
				  finalassetapr=(double)depapr/totalinventory*100;
				  finalassetmay=(double)depmay/totalinventory*100;
				  finalassetjun=(double)depjun/totalinventory*100;
				  finalassetjul=(double)depjul/totalinventory*100;
				  finalassetaug=(double)depaug/totalinventory*100;
				  finalassetsep=(double)depsep/totalinventory*100;
				  finalassetoct=(double)depoct/totalinventory*100;
				  finalassetnov=(double)depnov/totalinventory*100;
				  finalassetdec=(double)depdec/totalinventory*100;
				 
				 System.out.println("finalassetjan="+finalassetjan);
				 System.out.println("finalassetmar"+finalassetmar);
				 }
				Map<String,Double> data= new HashMap<>();
				data.put("totalinventory", (double) totalinventory);
				data.put("ASSETDepreciationjan",finaldepjan);
				data.put("ASSETDepreciationfeb",finaldepfeb);
				data.put("ASSETDepreciationmar",finaldepmar);
				data.put("ASSETDepreciationapr",finaldepapr);
				data.put("ASSETDepreciationmay",finaldepmay);
				data.put("ASSETDepreciationjun",finaldepjun);
				data.put("ASSETDepreciationjul",finaldepjul);
				data.put("ASSETDepreciationaug",finaldepAug);
				data.put("ASSETDepreciationsep",finaldepsep);
				data.put("ASSETDepreciationoct",finaldepoct);
				data.put("ASSETDepreciationnov",finaldepnov);
				data.put("ASSETDepreciationdec",finaldepdec);
				
				
				
				data.put("finalassetjan",finalassetjan);
				data.put("finalassetjfeb",finalassetfeb);
				data.put("finalassetjmar",finalassetmar);
				data.put("finalassetjapr",finalassetapr);
				data.put("finalassetjmay",finalassetmay);
				data.put("finalassetjjun",finalassetjun);
				data.put("finalassetjjul",finalassetjul);
				data.put("finalassetjaug",finalassetaug);
				data.put("finalassetjsep",finalassetsep);
				data.put("finalassetjoct",finalassetoct);
				data.put("finalassetjnov",finalassetnov);
				data.put("finalassetjdec",finalassetdec);
				
				
				
				
				
				
			    return data;
			}

			
			
			@RequestMapping(value = "/Categ_Graphs", method = { RequestMethod.GET, RequestMethod.POST })
			public String Categ_Graphs(Model md, HttpServletRequest req)
					throws ParseException {

				String USERID = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(USERID));

				
				return "BAM_Categ_Graphs.html";
			}
			
			
			@RequestMapping(value = "Categ_Graphdata", method = RequestMethod.GET)
			@ResponseBody
			public Map<String, Double> Categ_Graphdata(@RequestParam(required = false) String month, String year) {
				
			    String stryear="01"+"-04"+"-"+year;
			    int endyears=Integer.parseInt(year)+1;
			    String endyear="31"+"-03-"+String.valueOf(endyears);
			    System.out.println(endyears);
			    System.out.println(stryear);
			    double finalFurnitureFittings=0;
			    double finalMachineryPlant =0;
			    double finalPremises =0;
			    
				int totalinventory=BAMInvmastrep.gettotalvalue(stryear,endyear);
				int FurnitureFittings = BAMInvmastrep.getFurnitureFittings(stryear,endyear);
				int	MachineryPlant= BAMInvmastrep.getMachineryPlant(stryear,endyear);
				int	Premises= BAMInvmastrep.getPremises(stryear,endyear);
				System.out.println("FurnitureFittings= "+FurnitureFittings);
				System.out.println("MachineryPlant= "+MachineryPlant);
				System.out.println("Premises= "+ Premises);
				System.out.println("totalinventory= "+totalinventory);
				if(totalinventory!=0) {
					if(FurnitureFittings!=0) {
					finalFurnitureFittings=(double)FurnitureFittings/totalinventory*100;	
					}
					if(MachineryPlant!=0) {
						finalMachineryPlant=(double)MachineryPlant/totalinventory*100;
					}
					if(Premises!=0) {
						finalPremises=(double)Premises/totalinventory*100;
					}
					
				}
				System.out.println("finalFurnitureFittings= "+finalFurnitureFittings);
				System.out.println("finalMachineryPlant= "+finalMachineryPlant);
				System.out.println("finalPremises= "+finalPremises);
				Map<String,Double> data= new HashMap<>();
				data.put("totalinventory", (double) totalinventory);
				data.put("FurnitureFittings",finalFurnitureFittings);
				data.put("MachineryPlant",finalMachineryPlant);
				data.put("Premises",finalPremises);
				
				
			    return data;
			}
			
			@RequestMapping(value = "/Location_Graphs", method = { RequestMethod.GET, RequestMethod.POST })
			public String Location_Graphs(Model md, HttpServletRequest req)
					throws ParseException {

				String USERID = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(USERID));

				return "BAM_Location_Graphs.html";
			}
			
			
			@RequestMapping(value = "Location_Graphdata", method = RequestMethod.GET)
			@ResponseBody
			public Map<String, Double> Location_Graphdata(@RequestParam(required = false) String month, String year) {
				
				String stryear="01"+"-04"+"-"+year;
			    int endyears=Integer.parseInt(year)+1;
			    String endyear="31"+"-03-"+String.valueOf(endyears);
			    System.out.println(endyears);
			    System.out.println(stryear);
			    
			    double finaloffice =0;
			    double finalbranch =0;
			    double finalquarters =0;
			    double finalgeneral =0;
			    
				int totalinventory=BAMInvmastrep.gettotal(stryear,endyear);
				int  office= BAMInvmastrep.getoffice(stryear,endyear);
				int quarters= BAMInvmastrep.getquarters(stryear,endyear);
				int general= BAMInvmastrep.getgeneral(stryear,endyear);
				int branch= BAMInvmastrep.getbranch(stryear,endyear);
				
				System.out.println("office= "+office);
				System.out.println("branch= "+branch);
				System.out.println("quarters= "+quarters );
				System.out.println("general= "+general );
				System.out.println("totalinventory= "+totalinventory);
				if(totalinventory!=0) {
					if(office!=0) {
						finaloffice=(double)office/totalinventory*100;	
					}
					if(branch!=0) {
						finalbranch=(double)branch/totalinventory*100;
					}
					if(quarters!=0) {
						finalquarters=(double)quarters/totalinventory*100;
					}
					if(general!=0) {
						finalgeneral=(double)general/totalinventory*100;
					}
					
				}
				System.out.println("finaloffice="+finaloffice);
				System.out.println("finalbranch="+finalbranch);
				System.out.println("finalquarters="+finalquarters);
				System.out.println("finalgeneral="+finalgeneral);
				
				Map<String,Double> data= new HashMap<>();
				data.put("totalinventory", (double) totalinventory);
				data.put("office", finaloffice);
				data.put("branch", finalbranch);
				data.put("quarters",finalquarters);
				data.put("general",finalgeneral);
			   // Map<String,Double> data= new HashMap<>();
			    return data;
			}

			 
			@RequestMapping(value = "get_depreciation", method = RequestMethod.GET)
			@ResponseBody
			public BAM_AssetFlows_Entity get_depreciation(@RequestParam(required = false) String AN) {
			    BAM_AssetFlows_Entity en = new BAM_AssetFlows_Entity();
			    System.out.println("Asset Serial Number is :" + AN);

			    Date currentDate = new Date();
			    SimpleDateFormat monthYearFormat = new SimpleDateFormat("MM-yyyy", Locale.getDefault());
			    String currentMonthYear = monthYearFormat.format(currentDate);

			    System.out.println("currentMonthYear is :" + currentMonthYear);

			    List<BAM_AssetFlows_Entity> getlist = BAM_AssetFlows_rep.getview(AN);

			    boolean foundMatchingData = false;
			    for (BAM_AssetFlows_Entity up : getlist) {
			        String entityMonthYear = monthYearFormat.format(up.getGen_flow_strt_date());
			        System.out.println("Checking entity: " + up.getAsset_name() + " for month-year: " + entityMonthYear);
			        if (entityMonthYear.equals(currentMonthYear)) {
			            System.out.println("Matched entityMonthYear: " + entityMonthYear);
			            en.setSrl_no(up.getSrl_no());
			            en.setAsset_currency(up.getAsset_currency());
			            en.setAsset_name(up.getAsset_name());
			            en.setCurr_book_value(up.getCurr_book_value());
			            en.setDepreciation_percentage(up.getDepreciation_percentage());
			            en.setAsset_type(up.getAsset_type());
			            en.setDate_of_purchase(up.getDate_of_purchase());
			            en.setOriginal_cost(up.getOriginal_cost());
			            en.setAsst_exp_date(up.getAsst_exp_date());
			            en.setDepreciation_flg(up.getDepreciation_flg());
			            en.setDepreciation_method(up.getDepreciation_method());
			            en.setAccum_depres(up.getAccum_depres());
			            en.setAcquis_date(up.getAcquis_date());
			            en.setYear_of_purchase(up.getYear_of_purchase());
			            en.setLife_span_months(up.getLife_span_months());
			            en.setGen_remarks(up.getGen_remarks());
			            en.setDepreciation_frequency(up.getDepreciation_frequency());
			            en.setDate_of_last_depreciation(up.getDate_of_last_depreciation());
			            
			            foundMatchingData = true;
			            break;
			        }
			    }

			    if (!foundMatchingData) {
			        System.out.println("No matching data found for the current month and year.");
			        // Optionally, set fields to null if no matching data
			        en.setCurr_book_value(null);
			        en.setDepreciation_percentage(null);
			    }

			    return en;
			}
			@RequestMapping(value = "get_serial_Inv", method = RequestMethod.GET)
			@ResponseBody
			public BAMInventorymaster get_serial_Inv(@RequestParam(required = false) String AN,Model md) {
				BAMInventorymaster en = new BAMInventorymaster();
			    System.out.println("Asset Serial Number is :" + AN);
			    BAMInventorymaster getlist = BAMInvmastrep.getview(AN);

			    return getlist;
			}
			
			@RequestMapping(value = "get_serial_Invtran", method = RequestMethod.GET)
			@ResponseBody
			public Baminventorytransfer get_serial_Invtran(@RequestParam(required = false) String AN,Model md) {
				Baminventorytransfer en = new Baminventorytransfer();
			    System.out.println("Asset Serial Number is :" + AN);
			    Baminventorytransfer getlist1 = BamInvtrnrep.getview(AN);

			    return getlist1;
			}
			
			@RequestMapping(value = "get_serial_main", method = RequestMethod.GET)
			@ResponseBody
			public Bamcategorycodemain get_serial_main(@RequestParam(required = false) String asset_category,Model md) {
				
			    System.out.println("Asset asset_category is :" + asset_category);
				Bamcategorycodemain getlist=Bamcatcodemain.getbyId(asset_category);
			    return getlist;
			}
			
			
			
			@PostMapping("/getAssetDetails")
			@Transactional
			public ResponseEntity<List<BAM_AssetFlows_Entity>> getAssetDetails(@RequestParam(required = false) String asset_serial_no) {
			    System.out.println("Received asset_serial_no: " + asset_serial_no); // Log the received asset_serial_no
			    
			    // Fetch asset flows based on the asset serial number
			    List<BAM_AssetFlows_Entity> assetFlows = placementServices.getAssetsBySerialNumber(asset_serial_no);
			    
			    if (assetFlows.isEmpty()) {
			        return ResponseEntity.notFound().build(); // Handle the case where no assets are found
			    }
			    
			    // Log gen_depreciation for each asset
			    for (BAM_AssetFlows_Entity asset : assetFlows) {
			        System.out.println("Asset Serial No: " + asset.getAsset_serial_no() + ", GEN_DEPRECIATION: " + asset.getGen_depreciation());
			    }

			    return ResponseEntity.ok(assetFlows); // Return the list of asset flows
			}
			@RequestMapping(value = "Audit", method = { RequestMethod.GET, RequestMethod.POST })
			public String Audit(@RequestParam(required = false) String formmode,
			                    @RequestParam(required = false) String delete_cust_id,
			                    @RequestParam(value = "page", required = false) Optional<Integer> page,
			                    @RequestParam(value = "size", required = false) Optional<Integer> size, 
			                    Model md, HttpServletRequest req) {

				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				
			    // Fetch the logged-in user
			    String loginuserid = (String) req.getSession().getAttribute("USERID");
			    List<UserProfile> list = loginServices.getUsersListone(loginuserid);
			    md.addAttribute("domainid", list);
			    
			    // Fetch the list of AUDIT_ENTITY from the repository
			    List<Audit_Master> auditList = auditRepository.getInquirelist();  // Use your repository to get the list
			    md.addAttribute("auditList", auditList);  // Add the list to the model
			    
			    return "Audit";  // Return the view name
			}
			
			
			@RequestMapping(value = "AccountLedger", method = { RequestMethod.GET, RequestMethod.POST })
			public String AccountLedger(@RequestParam(required = false) String formmode,
					 Model md, HttpServletRequest req) throws ParseException {
				String userId = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
				md.addAttribute("menu", "BTMHeaderMenu");
				

				return "AccountLedger";
			}

			@RequestMapping(value = "organizationDetails", method = { RequestMethod.GET, RequestMethod.POST })
			public String organizationDetails(@RequestParam(required = false) String formmode,
					@RequestParam(required = false) String branch_name, Model md, HttpServletRequest req,
					@RequestParam(required = false) BigDecimal record_srl) {

				String userid = (String) req.getSession().getAttribute("USERID");
				md.addAttribute("menu", "BAJHeaderMenu");

				if (formmode == null || formmode.equals("add")) {
					md.addAttribute("formmode", "add");
					List<Organization_Entity> organization = organization_Repo.getAllList();
					 md.addAttribute("organization", organization.get(0));

					md.addAttribute("OrgBranch", organization_Branch_Rep.getbranchlist());

				} else if (formmode.equals("ModifyHead")) {
					md.addAttribute("formmode", "ModifyHead");
					List<Organization_Entity> organization = organization_Repo.getAllList();
					md.addAttribute("organization", organization.get(0));
				} else if (formmode.equals("DeleteBranch")) {
					md.addAttribute("formmode", "DeleteBranch");
					md.addAttribute("OrgBranch", organization_Branch_Rep.getOrgBranch(branch_name));
				} else if (formmode.equals("AddBranch")) {
					md.addAttribute("formmode", "AddBranch");
				} else if (formmode.equals("modify")) {
					md.addAttribute("formmode", "modify");
					md.addAttribute("OrgBranch", organization_Branch_Rep.getOrgBranch(branch_name));

				} else if (formmode.equals("view")) {
					md.addAttribute("formmode", "view");
					md.addAttribute("OrgBranch", organization_Branch_Rep.getOrgBranch(branch_name));

				} else if (formmode.equals("addholiday")) {
					md.addAttribute("formmode", "addholiday");

				} else if (formmode.equals("UploadHoliday")) {
					md.addAttribute("formmode", "UploadHoliday");

				} else if (formmode.equals("ModifyHoliday")) {
					md.addAttribute("formmode", "ModifyHoliday");

				} else if (formmode.equals("listholiday")) {
					md.addAttribute("formmode", "listholiday");
					md.addAttribute("Listofvalues", holidayMaster_Rep.getlistofHoliday());

				} else if (formmode.equals("viewrecord")) {

					md.addAttribute("formmode", "viewrecord");
					md.addAttribute("singlerecord", holidayMaster_Rep.getsinglevalue(record_srl));
				}
				return "OrganizationDetails";
			}
		
}