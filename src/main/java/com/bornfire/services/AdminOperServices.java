package com.bornfire.services;

import java.math.BigDecimal;


import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bornfire.config.AES;
import com.bornfire.entities.ApiRepo;
import com.bornfire.entities.ApiResponse_Entity;
import com.bornfire.entities.BAMInventorymaster;
import com.bornfire.entities.BAMInventryMastRep;
import com.bornfire.entities.BAM_AssetFlows_Entity;
import com.bornfire.entities.BAM_AssetFlows_Rep;
import com.bornfire.entities.BTMAdminAssociateMod;
import com.bornfire.entities.BTMAdminAssociateModRep;
import com.bornfire.entities.BTMAdminAssociateProfile;
import com.bornfire.entities.BTMAdminAssociateProfileRep;
import com.bornfire.entities.BTMAdminCalendarMaster;
import com.bornfire.entities.BTMAdminCalendarMasterRep;
import com.bornfire.entities.BTMAdminExpenseReportRep;
import com.bornfire.entities.BTMAdminHolidayMaster;
import com.bornfire.entities.BTMAdminHolidayMasterRep;
import com.bornfire.entities.BTMAdminOrganizationMaster;
import com.bornfire.entities.BTMAdminOrganizationMasterRep;
import com.bornfire.entities.BTMAdminProfileManager;
import com.bornfire.entities.BTMAdminProfileMangerRep;
import com.bornfire.entities.BTMDocumentMaster;
import com.bornfire.entities.BTMDocumentMasterRep;
import com.bornfire.entities.BTMProjectDetailsRep;
import com.bornfire.entities.BTMProjectMaster;
import com.bornfire.entities.BTMProjectMasterRep;
import com.bornfire.entities.BTMProjectTeamDetailsRep;
import com.bornfire.entities.BTMRefCodeMaster;
import com.bornfire.entities.BTMRefCodeMasterRep;
import com.bornfire.entities.BTMTravelMaster;
import com.bornfire.entities.BTMTravelMasterRep;
import com.bornfire.entities.BTMWorkAssignment;
import com.bornfire.entities.BTMWorkAssignmentRep;
import com.bornfire.entities.BamAcquisition;
import com.bornfire.entities.BamAcquisitionrep;
import com.bornfire.entities.BamDocumentMasRep;
import com.bornfire.entities.Bamcatcodemaintainrep;
import com.bornfire.entities.Bamcategorycodemain;
import com.bornfire.entities.Bamdocumentmanager;
import com.bornfire.entities.Baminventorytranrep;
import com.bornfire.entities.Baminventorytransfer;
import com.bornfire.entities.Bamsaleandwrite;
import com.bornfire.entities.Bamsaleandwriterep;
import com.bornfire.entities.ChartOfAccounts;
import com.bornfire.entities.ChartofAccountsrep;
import com.bornfire.entities.DashBoardEntity;
import com.bornfire.entities.DashBoardRepo;
import com.bornfire.entities.ExpenseMaster;
import com.bornfire.entities.LeaveMaster;
import com.bornfire.entities.LeaveMasterRep;
import com.bornfire.entities.LeaveTableRep;
import com.bornfire.entities.OnDuty;
import com.bornfire.entities.OnDutyRep;
import com.bornfire.entities.ProjectDetails;
import com.bornfire.entities.ProjectTeamDetails;
import com.bornfire.entities.ResourceMaster;
import com.bornfire.entities.ResourceMasterRepo;
import com.bornfire.messagebuilder.DocumentPacks1;
import com.bornfire.entities.BamGeneralLedgerRep;
import com.bornfire.entities.BamGeneralLedger;

import java.io.File;
import java.io.IOException;


@Service
@ConfigurationProperties("output")
@Transactional
public class AdminOperServices {
	@Autowired
	BamGeneralLedgerRep BamGeneralLedgerRep;
	@Autowired
	BAM_AssetFlows_Rep  BAM_AssetFlows_rep;
	
	  @Autowired
	  private LeaveTableRep leaveTableRep;

	@Autowired
	BTMAdminAssociateProfileRep btmAdminAssociateProfileRep;

	@Autowired
	BTMAdminOrganizationMasterRep btmAdminOrganizationMasterRep;

	@Autowired
	BTMAdminHolidayMasterRep btmAdminHolidayMasterRep;

	@Autowired
	BTMAdminProfileMangerRep btmAdminProfileMangerRep;

	@Autowired
	BTMAdminExpenseReportRep btmAdminExpenseReportRep;

	@Autowired
	BTMProjectMasterRep btmProjectMasterRep;

	@Autowired
	BTMTravelMasterRep BTMtravelMasterRep;

	@Autowired
	BTMWorkAssignmentRep btmWorkAssignmentRep;

	@Autowired
	BTMRefCodeMasterRep btmRefCodeMasterRep;

	@Autowired
	BTMDocumentMasterRep btmDocumentMasterRep;

	@Autowired
	BAMInventryMastRep BAMInvmastrep;
	
	@Autowired
	LeaveMasterRep leaveMasterRep;

	@Autowired
	OnDutyRep onDutyRep;

	@Autowired
	BTMProjectDetailsRep btmProjectDetailsRep;

	@Autowired
	BTMProjectTeamDetailsRep btmProjectTeamDetailsRep;

	@Autowired
	BTMAdminCalendarMasterRep btmAdminCalendarMasterRep;

	@Autowired
	BTMAdminAssociateModRep btmAdminAssociateModRep;

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	ResourceMasterRepo resourceMasterRepo;
	
	@Autowired
	Bamcatcodemaintainrep Bamcatcodemainrep;

	@Autowired
	BamDocumentMasRep BamDocmasRep;
	
	@Autowired
	BAMInventryMastRep Baminvmasrep;
	
	@Autowired
	Baminventorytranrep BamInvtrnrep;
	
	@Autowired
	Bamsaleandwriterep bamsalerep; 
	
	@Autowired
	ChartofAccountsrep chartofaccountsrep;
	
	@Autowired
	BamAcquisitionrep bamAcquisitionrep;
	
	@Autowired
	DashBoardRepo dashboardRepository;
	
	@Autowired
	ApiRepo apiResponseRepository;
	
	@Autowired
	DocumentPacks1 documentPacks1;
	
	@Autowired
	AuditService auditService;
	
// ==================================== Admin Organization Master ========================================

	public String addOrganizationModyfiy(BTMAdminOrganizationMaster btmAdminOrganizationMaster, String formmode) {

		String msg = "";

		if (formmode.equals("edit")) {

			BTMAdminOrganizationMaster up = btmAdminOrganizationMaster;

			up.setEntity_flg("Y");

			up.setDel_flg("Y");

			btmAdminOrganizationMasterRep.save(up);

			msg = "Modified Successfully";

		}
		return msg;
	}

	public BTMAdminOrganizationMaster getUser(String id) {

		if (btmAdminOrganizationMasterRep.existsById(id)) {
			BTMAdminOrganizationMaster up = btmAdminOrganizationMasterRep.findById(id).get();
			return up;
		} else {
			return new BTMAdminOrganizationMaster();
		}
	};
	/*
	 * public List<BTMAdminAssociateProfile> getAssociteUserslist() {
	 * 
	 * List<BTMAdminAssociateProfile> users =
	 * btmAdminAssociateProfileRep.getAssociatelist();
	 * 
	 * return users;
	 * 
	 * }
	 */

//	================================== Admin Associate master =========================================

	public String addAssociateUser(BTMAdminAssociateProfile user, String formmode ,String UserId) {

		String msg = "";

		if (formmode.equals("edit")) {
			BTMAdminAssociateProfile up1 = btmAdminAssociateProfileRep.getEmployeedetail(user.getResource_id());
			{
				
				user.setEntity_flg("N");
				user.setModify_flg("Y");
				user.setDel_flg("N");
				user.setModify_user(UserId);
				user.setModify_time(new Date());
				Session session = sessionFactory.getCurrentSession();
				session.saveOrUpdate(up1);
				up1.setEntity_flg("N");
				up1.setModify_flg("Y");
			//	BTMAdminAssociateMod NEW=new BTMAdminAssociateMod(up1);
				btmAdminAssociateProfileRep.save(user);
			//	btmAdminAssociateModRep.save(NEW);
				msg = "Modified Successfully";
			}

		} else if (formmode.equals("verify")) {
			Session session = sessionFactory.getCurrentSession();
			BTMAdminAssociateProfile ver = btmAdminAssociateProfileRep.getEmployeedetail(user.getResource_id());
			ver.setEntity_flg("Y");
			ver.setModify_flg("N");
			ver.setDel_flg("N");
			ver.setVerify_user(UserId);
			ver.setVerify_time(new Date());
			btmAdminAssociateProfileRep.save(ver);
			session.saveOrUpdate(ver);
			BTMAdminAssociateMod Main = new BTMAdminAssociateMod(ver);
			Main.setEntity_flg("N");
			//Main.setModify_flg("N");
			//Main.setDel_flg("N");
			btmAdminAssociateModRep.save(Main);
		//	btmAdminAssociatePrifleRep.deleteById(user.getResource_id());
			msg = "Verified Successfully";

		} else if (formmode.equals("delete")) {

			
				BTMAdminAssociateProfile Dmain = btmAdminAssociateProfileRep.getEmployeedetail(user.getResource_id());
				Dmain.setEntity_flg("N");
				Dmain.setDel_flg("Y");
				Session session = sessionFactory.getCurrentSession();
				session.saveOrUpdate(Dmain);
				msg = "Deleted Successfully";
		

		} else if (formmode.equals("cancel")) {

			BTMAdminAssociateMod ver = btmAdminAssociateModRep.getEmployeedetail(user.getResource_id());
			user.setEntity_flg("Y");
			user.setModify_flg("N");
			user.setDel_flg("N");
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(ver);
			BTMAdminAssociateProfile Main = new BTMAdminAssociateProfile(ver);
		//	Main.setEntity_flg("Y");
		//	Main.setModify_flg("N");
		//	Main.setDel_flg("N");
			btmAdminAssociateProfileRep.save(Main);
		//	btmAdminAssociateModRep.deleteById(user.getResource_id());
			msg = "Cancelled Successfully";

		}  else if (formmode.equals("add")) {

			Optional<BTMAdminAssociateProfile> addlist = btmAdminAssociateProfileRep.findById(user.getResource_id());
			if (addlist.isPresent()) {
				msg = "Account Already Exist";
			} else {
				BTMAdminAssociateProfile up = user;
				BTMAdminAssociateMod up1 = new BTMAdminAssociateMod();
				up.setResource_id(user.getResource_id());
				up.setEntity_flg("N");
				up.setModify_flg("N");
				up.setEntry_user(UserId);
				up.setEntry_time(new Date());
				up1.setResource_id(user.getResource_id());
				up.setDel_flg("N");
				up1.setEntity_flg("N");
				up1.setModify_flg("N");
				up1.setDel_flg("N");
				up1.setEntry_user(UserId);
				up1.setEntry_time(new Date());
				
				String encrypted_imei =null;
				try {
			 encrypted_imei =	AES.encrypt(user.getImei());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				up.setImei(encrypted_imei);
				up1.setImei(encrypted_imei);
				String encrypted_password =null;
				try {
			 encrypted_password =	AES.encrypt(user.getPassword());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				up.setPassword(encrypted_password);
				up1.setPassword(encrypted_password);
				btmAdminAssociateProfileRep.save(user);
				btmAdminAssociateModRep.save(up1);
				msg = "Account Added Successfully";
			}
		}
		return msg;
	}

	public BTMAdminAssociateProfile getAssociteUser(String resId) {

		if (btmAdminAssociateProfileRep.existsById(resId)) {
			BTMAdminAssociateProfile up = btmAdminAssociateProfileRep.findById(resId).get();
			return up;
		} else {
			return new BTMAdminAssociateProfile();
		}
	};

	public BTMAdminAssociateProfile getAssociteVerifyUser(String resId) {

		if (btmAdminAssociateProfileRep.existsById(resId)) {
			BTMAdminAssociateProfile up = btmAdminAssociateProfileRep.findById(resId).get();
			return up;
		} else {
			return new BTMAdminAssociateProfile();
		}
	}

	public BTMAdminAssociateProfile getAssociteCancelUser(String resId) {

		if (btmAdminAssociateProfileRep.existsById(resId)) {
			BTMAdminAssociateProfile up = btmAdminAssociateProfileRep.findById(resId).get();
			return up;
		} else {
			return new BTMAdminAssociateProfile();
		}
	}

	public BTMAdminAssociateProfile getAssociteListUser(String resId) {

		if (btmAdminAssociateProfileRep.existsById(resId)) {
			BTMAdminAssociateProfile up = btmAdminAssociateProfileRep.findById(resId).get();
			return up;
		} else {
			return new BTMAdminAssociateProfile();
		}
	}

// ========================================== Maintenance associate profile ==================================

	public String modifyAssociate(BTMAdminAssociateProfile btmAdminAssociateProfile, String formmode, String userid) {

		String msg = "";

		if (formmode.equals("edit")) {

			BTMAdminAssociateProfile up1 = btmAdminAssociateProfileRep.getEmployeedetail(userid);

			BTMAdminAssociateProfile up = btmAdminAssociateProfile;


			up1.setDis_start_date(up.getDis_start_date());

			up1.setDis_end_date(up.getDis_end_date());

			up1.setEntity_flg("N");

			up1.setDel_flg("Y");

			up1.setDisable_flg(up.getDisable_flg());

			btmAdminAssociateProfileRep.save(up1);

			msg = "Modified Successfully";

		}

		return msg;

	}

// =============================== Maintenance Leave Master=======================

	public String modifyLeave(LeaveMaster leaveMaster, String formmode, String userid ,String Id) {

		String msg = "";

		BigDecimal n = new BigDecimal(0);
		if (formmode.equals("approve")) {
			
			LeaveMaster up1 = leaveMasterRep.getleaveMaster(userid);

			LeaveMaster up = leaveMaster;
			ResourceMaster remail=resourceMasterRepo.getrole(Id);
			
			if(up1.getAppr_email_1() != null && remail.getEmail().equals(up1.getAppr_email_1()) || 
			   up1.getAppr_email_2() != null && remail.getEmail().equals(up1.getAppr_email_2())	||
			   up1.getAppr_email_3() != null && remail.getEmail().equals(up1.getAppr_email_3()) ||
			   up1.getAppr_email_4() != null && remail.getEmail().equals(up1.getAppr_email_4()) ||
			   up1.getAppr_email_5() != null && remail.getEmail().equals(up1.getAppr_email_5())) {
			
			
			if (up1.getAppr_email_1() != null && remail.getEmail().equals(up1.getAppr_email_1())) {
				up1.setAppr_email_status_1("Approved");
				up1.setAppr_no_days_1(n);
			}
			if (up1.getAppr_email_2() != null && remail.getEmail().equals(up1.getAppr_email_2())) {
				up1.setAppr_email_status_2("Approved");
				up1.setAppr_no_days_2(n);
			}
			if (up1.getAppr_email_3() != null && remail.getEmail().equals(up1.getAppr_email_3())) {
				up1.setAppr_email_status_3("Approved");
				up1.setAppr_no_days_3(n);
			}
			if (up1.getAppr_email_4() != null && remail.getEmail().equals(up1.getAppr_email_4())) {
				up1.setAppr_email_status_4("Approved");
				up1.setAppr_no_days_4(n);
			}
			if (up1.getAppr_email_5() != null && remail.getEmail().equals(up1.getAppr_email_5())) {
				up1.setAppr_email_status_5("Approved");
				up1.setAppr_no_days_5(n);
			}

			
			if(up1.getAppr_no_days_1().compareTo(BigDecimal.ZERO) == 0 &&
				    up1.getAppr_no_days_2().compareTo(BigDecimal.ZERO) == 0 &&
				    up1.getAppr_no_days_3().compareTo(BigDecimal.ZERO) == 0 &&
				    up1.getAppr_no_days_4().compareTo(BigDecimal.ZERO) == 0 &&
				    up1.getAppr_no_days_5().compareTo(BigDecimal.ZERO) == 0) {

				up1.setStatus("Approved");
				
				up1.setAuth_time(new Date());
				
				up1.setAuth_user(Id);

				up1.setEntity_flg("Y");

				leaveMasterRep.save(up1);
				
			}
			msg = "Approved Successfully";
			}else if(up1.getAppr_no_days_1().compareTo(BigDecimal.ZERO) == 0 &&
				    up1.getAppr_no_days_2().compareTo(BigDecimal.ZERO) == 0 &&
				    up1.getAppr_no_days_3().compareTo(BigDecimal.ZERO) == 0 &&
				    up1.getAppr_no_days_4().compareTo(BigDecimal.ZERO) == 0 &&
				    up1.getAppr_no_days_5().compareTo(BigDecimal.ZERO) == 0) {

				up1.setStatus("Approved");
				
				up1.setAuth_time(new Date());
				
				up1.setAuth_user(Id);

				up1.setEntity_flg("Y");

				leaveMasterRep.save(up1);
				msg = "Approved Successfully";
			}
			
			else {
				
				msg = "You Can't Approve";
			}
		}

		
		
		
		if (formmode.equals("reject")) {
			
			ResourceMaster remail=resourceMasterRepo.getrole(Id);
			LeaveMaster up1 = leaveMasterRep.getleaveMaster(userid);

			LeaveMaster up = leaveMaster;

			if(up1.getAppr_email_1() != null && remail.getEmail().equals(up1.getAppr_email_1()) || 
					   up1.getAppr_email_2() != null && remail.getEmail().equals(up1.getAppr_email_2())	||
					   up1.getAppr_email_3() != null && remail.getEmail().equals(up1.getAppr_email_3()) ||
					   up1.getAppr_email_4() != null && remail.getEmail().equals(up1.getAppr_email_4()) ||
					   up1.getAppr_email_5() != null && remail.getEmail().equals(up1.getAppr_email_5())) {
				
				

				if (up1.getAppr_email_1() != null && remail.getEmail().equals(up1.getAppr_email_1())) {
					up1.setAppr_email_status_1("Rejected");
					up1.setAppr_no_days_1(n);
				}
				if (up1.getAppr_email_2() != null && remail.getEmail().equals(up1.getAppr_email_2())) {
					up1.setAppr_email_status_2("Rejected");
					up1.setAppr_no_days_2(n);
				}
				if (up1.getAppr_email_3() != null && remail.getEmail().equals(up1.getAppr_email_3())) {
					up1.setAppr_email_status_3("Rejected");
					up1.setAppr_no_days_3(n);
				}
				if (up1.getAppr_email_4() != null && remail.getEmail().equals(up1.getAppr_email_4())) {
					up1.setAppr_email_status_4("Rejected");
					up1.setAppr_no_days_4(n);
				}
				if (up1.getAppr_email_5() != null && remail.getEmail().equals(up1.getAppr_email_5())) {
					up1.setAppr_email_status_5("Rejected");
					up1.setAppr_no_days_5(n);
				}

				
			
			
			if(up1.getAppr_no_days_1().compareTo(BigDecimal.ZERO) == 0 &&
				    up1.getAppr_no_days_2().compareTo(BigDecimal.ZERO) == 0 &&
				    up1.getAppr_no_days_3().compareTo(BigDecimal.ZERO) == 0 &&
				    up1.getAppr_no_days_4().compareTo(BigDecimal.ZERO) == 0 &&
				    up1.getAppr_no_days_5().compareTo(BigDecimal.ZERO) == 0) {

				up1.setStatus("Rejected");
				up1.setEntity_flg("Y");

				up1.setReject_remarks("Rejected");

				up1.setDel_flg("Y");
	            up1.setAuth_time(new Date());
				
				up1.setAuth_user(Id);

				leaveMasterRep.save(up1);
				
			}
			msg = "Rejected Successfully";
			
			}else if(up1.getAppr_no_days_1().compareTo(BigDecimal.ZERO) == 0 &&
				    up1.getAppr_no_days_2().compareTo(BigDecimal.ZERO) == 0 &&
				    up1.getAppr_no_days_3().compareTo(BigDecimal.ZERO) == 0 &&
				    up1.getAppr_no_days_4().compareTo(BigDecimal.ZERO) == 0 &&
				    up1.getAppr_no_days_5().compareTo(BigDecimal.ZERO) == 0) {
				
				up1.setStatus("Rejected");
				up1.setEntity_flg("Y");

				up1.setReject_remarks("Rejected");

				up1.setDel_flg("Y");
	         up1.setAuth_time(new Date());
				
				up1.setAuth_user(Id);

				leaveMasterRep.save(up1);

				msg = "Rejected Successfully";
				
				
		}else {
			
			msg = "You Can't Reject";
		}

	}
		return msg;
	}
// ============================= Maintenance OD Master ===================

	public String modifyOd(OnDuty onDuty, String formmode, String userid,String Id) {

		String msg = "";

		if (formmode.equals("approve")) {

			OnDuty up1 = onDutyRep.getOdMaster(userid);

			OnDuty up = onDuty;

			up1.setStatus("Approved");

			up1.setEntity_flg("Y");
			
up1.setAuth_time(new Date());
			
			up1.setAuth_user(Id);

			onDutyRep.save(up1);

			msg = "Approved Successfully";

		}

		if (formmode.equals("reject")) {

			OnDuty up1 = onDutyRep.getOdMaster(userid);

			OnDuty up = onDuty;

			up1.setStatus("Rejected");

			up1.setEntity_flg("Y");

			up1.setReject_remarks("Rejected");

			up1.setDel_flg("Y");
			
up1.setAuth_time(new Date());
			
			up1.setAuth_user(Id);

			onDutyRep.save(up1);

			msg = "Rejected Successfully";

		}

		return msg;

	}

// ============================== Maintenance Expense Master =========================

	public String modifyExpense(ExpenseMaster expenses, String formmode, String resId) {

		String msg = "";

		if (formmode.equals("approve")) {

			ExpenseMaster up1 = btmAdminExpenseReportRep.getExpenseMaster(resId);
			ExpenseMaster up = expenses;
			up1.setStatus("Approved");
			up1.setEntity_flg("Y");
			up1.setDel_flg("Y");

			btmAdminExpenseReportRep.save(up1);

			msg = "Approved Successfully";

		}

		if (formmode.equals("reject")) {

			ExpenseMaster up1 = btmAdminExpenseReportRep.getExpenseMaster(resId);
			ExpenseMaster up = expenses;
			up1.setStatus("Rejected");
			up1.setEntity_flg("Y");
			up1.setDel_flg("Y");

			btmAdminExpenseReportRep.save(up1);

			msg = "Rejected Successfully";

		}

		return msg;

	}

// ===================================== Maintenance Travel Master ================================

	public String modifyTravelMaster(BTMTravelMaster travelMaster, String formmode, String resId) {

		String msg = "";

		if (formmode.equals("approve")) {

			BTMTravelMaster up1 = BTMtravelMasterRep.getTravelMaster(resId);

			BTMTravelMaster up = travelMaster;

			up1.setTra_status("Approved");

			up1.setEntity_flg("Y");

			up1.setDel_flg("Y");

			BTMtravelMasterRep.save(up1);

			msg = "Approved Successfully";

		}

		if (formmode.equals("reject")) {

			BTMTravelMaster up1 = BTMtravelMasterRep.getTravelMaster(resId);
			BTMTravelMaster up = travelMaster;
			up1.setTra_status("Rejected");
			up1.setEntity_flg("Y");
			up1.setDel_flg("Y");

			BTMtravelMasterRep.save(up1);

			msg = "Rejected Successfully";

		}

		return msg;

	}

// ========================= Admin Holiday Master ===========================

	public String addHolidayDetails(BTMAdminHolidayMaster btmAdminHolidayMaster, String formmode, BigDecimal recordNo) {

		String msg = "";

		BTMAdminHolidayMaster up1 = btmAdminHolidayMaster;


		BigDecimal test = btmAdminHolidayMasterRep.getcount(up1.getRecord_srl());


		if (formmode.equals("add")) {

			BTMAdminHolidayMaster up = btmAdminHolidayMaster;

			up.setEntity_flg("Y");

			up.setDel_flg("N");

			btmAdminHolidayMasterRep.save(up);

			msg = "Added Successfully";

		} else if (formmode.equals("edit")) {

			BTMAdminHolidayMaster up = btmAdminHolidayMaster;

			up.setEntity_flg("Y");

			up.setDel_flg("N");

			btmAdminHolidayMasterRep.save(up);

			msg = "Edited Successfully";
		}

		return msg;
	}

	public List<BTMAdminHolidayMaster> getHolidaylist() {

		List<BTMAdminHolidayMaster> users = btmAdminHolidayMasterRep.getAssocitelist();

		return users;

	}

	public BTMAdminHolidayMaster getHolidayDetail(BigDecimal resId) {

		if (btmAdminHolidayMasterRep.existsById(resId)) {
			BTMAdminHolidayMaster up = btmAdminHolidayMasterRep.findById(resId).get();
			return up;
		} else {
			return new BTMAdminHolidayMaster();
		}
	};

//	================================= Admin Profile Manager ===============================

	public List<BTMAdminProfileManager> getProfileManagerlist() {

		List<BTMAdminProfileManager> users = btmAdminProfileMangerRep.getProfilelist();

		return users;

	}

	public BTMAdminProfileManager getProfileManager(String id) {

		if (btmAdminProfileMangerRep.existsById(id)) {
			BTMAdminProfileManager up = btmAdminProfileMangerRep.findById(id).get();
			return up;
		} else {
			return new BTMAdminProfileManager();
		}
	};

	public String addProfileDetails(BTMAdminProfileManager btmAdminProfileManager, String formmode) {

		String msg = "";

		if (formmode.equals("edit")) {

			BTMAdminProfileManager up = btmAdminProfileManager;

			up.setEntity_flg("Y");

			up.setDel_flg("N");

			btmAdminProfileMangerRep.save(up);

			msg = "Edited Successfully";

		} else if (formmode.equals("verify")) {

			BTMAdminProfileManager up = btmAdminProfileManager;

			up.setEntity_flg("Y");

			up.setDel_flg("N");

			up.setModify_flg("Y");

			btmAdminProfileMangerRep.save(up);

			msg = "Verified Successfully";

		} else if (formmode.equals("delete")) {

			BTMAdminProfileManager up = btmAdminProfileManager;

			up.setEntity_flg("N");

			up.setDel_flg("Y");

			btmAdminProfileMangerRep.save(up);

			msg = "Deleted Successfully";
		}

		return msg;
	}

	// LIST AND DETAILS EXPENSE REPORT
	public List<ExpenseMaster> getExpenseReportlist() {

		List<ExpenseMaster> users = btmAdminExpenseReportRep.getReportList();

		return users;

	}

	public ExpenseMaster getReportManager(String id) {

		if (btmAdminExpenseReportRep.existsById(id)) {
			ExpenseMaster up = btmAdminExpenseReportRep.findById(id).get();
			return up;
		} else {
			return new ExpenseMaster();
		}
	}

//	travel Master List

	public List<BTMTravelMaster> getTravelMasterList() {

		List<BTMTravelMaster> users = BTMtravelMasterRep.getTravellist();

		return users;
	}

	// LIST PROFILE MASTER
	public List<BTMProjectMaster> getProjectMasterlist() {

		List<BTMProjectMaster> users = btmProjectMasterRep.getProjectlist();

		return users;
	}

	// Add PROJECT MASTER

	public String addProjectMaster(BTMProjectMaster btmProjectMaster, ProjectDetails projectDetails,
			ProjectTeamDetails projectTeamDetails, String formmode, String userId) {

		String msg = "";
		int count = btmProjectMasterRep.getprojectCount(btmProjectMaster.getProj_id(), btmProjectMaster.getProj_name());

		if (count != 0) {
			msg = "already Exsist";
		} else {

			if (formmode.equals("add")) {

				BTMProjectMaster up = btmProjectMaster;

				up.setEntry_time(new Date());
				up.setEntry_user(userId);
				up.setEntity_flg("Y");
				up.setDel_flg("N");
				up.setRemarks("Pending");

				btmProjectMasterRep.save(up);
				ProjectDetails up1 = projectDetails;
				up1.setProj_id(up.getProj_id());
				up1.setClient_id(up.getClient_id());
				up1.setRemarks(up.getRemarks());
				up1.setEntity_flg("Y");
				up1.setDel_flg("N");

				btmProjectDetailsRep.save(up1);
				ProjectTeamDetails up2 = projectTeamDetails;

				up2.setClient_id(up.getClient_id());
				up2.setProj_id(up.getProj_id());
				up2.setRemarks(up.getRemarks());
				up2.setEntity_flg("Y");
				up2.setDel_flg("N");

				btmProjectTeamDetailsRep.save(up2);

				msg = "Added Successfully";

			}
		}

		if (formmode.equals("edit")) {

			BTMProjectMaster up = btmProjectMaster;
			up.setRemarks(up.getRemarks());
			up.setEntity_flg("Y");
			up.setModify_flg("Y");
			up.setModify_user(userId);
			up.setModify_time(new Date());
			up.setDel_flg("N");

			btmProjectMasterRep.save(up);

			ProjectDetails up1 = projectDetails;

			up1.setProj_id(up.getProj_id());
			up1.setClient_id(up.getClient_id());
			up1.setEntity_flg("Y");
			up1.setModify_flg("Y");
			up1.setDel_flg("N");

			btmProjectDetailsRep.save(up1);

			ProjectTeamDetails up2 = projectTeamDetails;

			up2.setClient_id(up.getClient_id());
			up2.setProj_id(up.getProj_id());
			up2.setEntity_flg("Y");
			up2.setModify_flg("Y");
			up2.setDel_flg("N");

			btmProjectTeamDetailsRep.save(up2);

			msg = "Edited Successfully";

		} else if (formmode.equals("delete")) {

			BTMProjectMaster up = btmProjectMaster;
			up.setEntity_flg("N");
			up.setDel_flg("Y");

			btmProjectMasterRep.save(up);

			ProjectDetails up1 = projectDetails;
			up1.setEntity_flg("N");
			up1.setDel_flg("Y");

			btmProjectDetailsRep.save(up1);

			ProjectTeamDetails up2 = projectTeamDetails;
			up2.setEntity_flg("N");
			up2.setDel_flg("Y");

			btmProjectTeamDetailsRep.save(up2);

			msg = "Deleted Successfully";
		}

		return msg;

	}

//GET PROJECT MASTER
	public BTMProjectMaster getProjectManager(String resId) {

		if (btmProjectMasterRep.existsById(resId)) {
			BTMProjectMaster up = btmProjectMasterRep.findById(resId).get();
			return up;
		} else {
			return new BTMProjectMaster();
		}
	}

	public BTMTravelMaster getTravelMaster(String resId) {

		if (BTMtravelMasterRep.existsById(resId)) {
			BTMTravelMaster up = BTMtravelMasterRep.findById(resId).get();
			return up;
		} else {
			return new BTMTravelMaster();
		}
	}

	// Work Assignment
	public BTMWorkAssignment getWorkAssignmentMaster(String resId) {

		if (btmProjectMasterRep.existsById(resId)) {
			BTMWorkAssignment up = btmWorkAssignmentRep.findById(resId).get();
			return up;
		} else {
			return new BTMWorkAssignment();
		}
	}
	//gl
	public String addGeneralLedger(BamGeneralLedger BamGeneralLedger, String formmode , String GL_CODE) {

		String msg = "";

		if (formmode.equals("add")) {

			BamGeneralLedger up = BamGeneralLedger;

			up.setDelFlg("N");

			up.setModifyFlg("N");

			BamGeneralLedgerRep.save(up);

			msg = "Added Successfully";
		}
		else if (formmode.equals("edit")) {

		BamGeneralLedger user =	BamGeneralLedgerRep.getRefMaster(GL_CODE);
			if(user.getGlCode().equals(BamGeneralLedger.getGlCode()) && user.getGlDescription().equals(BamGeneralLedger.getGlDescription()) && user.getGlType().equals(BamGeneralLedger.getGlType()) && user.getGlTypeDescription().equals(BamGeneralLedger.getGlTypeDescription())&& user.getModule().equals(BamGeneralLedger.getModule()) && user.getRemarks().equals(BamGeneralLedger.getRemarks())){
				msg="No Modification More";
		}
		else {
				BamGeneralLedger up = BamGeneralLedgerRep.getRefMaster(GL_CODE);
				up.setGlCode(BamGeneralLedger.getGlCode());
				up.setGlDescription(BamGeneralLedger.getGlDescription());
				up.setGlType(BamGeneralLedger.getGlType());
				up.setGlTypeDescription(BamGeneralLedger.getGlTypeDescription());
				up.setModule(BamGeneralLedger.getModule());
				up.setModifyFlg("Y");
				up.setDelFlg("N");
				BamGeneralLedgerRep.save(up);

			msg = "Edited Successfully";
			
		}
		
		}
		else if (formmode.equals("delete")) {

			BamGeneralLedger up =BamGeneralLedgerRep.getRefMaster(GL_CODE);
			up.setDelFlg("Y");
			BamGeneralLedgerRep.save(up);
			msg = "Deleted Successfully";
		}
		
		return msg;
	}
	
	
	
	
	public BamGeneralLedger getGeneralLedger(String id) {

		if (BamGeneralLedgerRep.existsById(id)) {
			BamGeneralLedger up = BamGeneralLedgerRep.findById(id).get();
			return up;
		} else {
			return new BamGeneralLedger();
		}
	}



	// =============== Admin RefCode master =========================

	public BTMRefCodeMaster getRefMaster(String id) {

		if (btmRefCodeMasterRep.existsById(id)) {
			BTMRefCodeMaster up = btmRefCodeMasterRep.findById(id).get();
			return up;
		} else {
			return new BTMRefCodeMaster();
		}
	}

	public String addRefCodeMaster(BTMRefCodeMaster btmRefCodeMaster, String formmode , String ref_id) {

		String msg = "";

		if (formmode.equals("add")) {

			BTMRefCodeMaster up = btmRefCodeMaster;

			up.setEntity_flg("Y");

			up.setDel_flg("N");

			btmRefCodeMasterRep.save(up);

			msg = "Added Successfully";

		} else if (formmode.equals("edit")) {

			BTMRefCodeMaster up = btmRefCodeMasterRep.getRefMaster(ref_id);
		up.setRef_type_desc(btmRefCodeMaster.getRef_type_desc());
		up.setRef_id_desc(btmRefCodeMaster.getRef_id_desc());
		up.setRemarks(btmRefCodeMaster.getRemarks());
		up.setModule_id(btmRefCodeMaster.getModule_id());
		up.setRef_type(btmRefCodeMaster.getRef_type());
		BTMRefCodeMaster user =	btmRefCodeMasterRep.findById(up.getRef_id()).get();

if(btmRefCodeMaster.getRef_type().equals(user.getRef_type()) && btmRefCodeMaster.getRef_type_desc().equals(user.getRef_type_desc()) &&  btmRefCodeMaster.getRef_id_desc().equals(user.getRef_id_desc()) && btmRefCodeMaster.getModule_id().equals(user.getModule_id()) && btmRefCodeMaster.getRemarks().equals(user.getRemarks()) 
	) {
msg="No Modification More";
}else {
		

			btmRefCodeMasterRep.save(up);

			msg = "Edited Successfully";}

		} else if (formmode.equals("delete")) {

			BTMRefCodeMaster up = btmRefCodeMasterRep.getRefMaster(ref_id);
			System.out.println(btmRefCodeMaster.getRef_id());
			up.setEntity_flg("Y");
			up.setDel_flg("Y");
			btmRefCodeMasterRep.save(up);
			msg = "Deleted Successfully";
		}

		return msg;
	}

// =========================== Admin Doc Master =======================================

	public String addDocumentUser(BTMDocumentMaster btmDocumentMaster, String formmode) {

		String msg = "";

		if (formmode.equals("add")) {

			BTMDocumentMaster up = btmDocumentMaster;

			up.setEntity_flg("Y");

			up.setDel_flg("N");

			btmDocumentMasterRep.save(up);

			msg = "Added Successfully";

		} else if (formmode.equals("edit")) {
			BTMDocumentMaster user =	btmDocumentMasterRep.findById(btmDocumentMaster.getDoc_ref_no()).get();

if(btmDocumentMaster.getDoc_id().equals(user.getDoc_id()) && btmDocumentMaster.getDoc_name().equals(user.getDoc_name()) && btmDocumentMaster.getDoc_desc().equals(user.getDoc_desc()) && btmDocumentMaster.getDoc_type().equals(user.getDoc_type()) && btmDocumentMaster.getDoc_group().equals(user.getDoc_group()) && btmDocumentMaster.getAccess_type().equals(user.getAccess_type()) && btmDocumentMaster.getAccess_group().equals(user.getAccess_group()) && btmDocumentMaster.getDoc_uploader().equals(user.getDoc_uploader()) && btmDocumentMaster.getDoc_approver().equals(user.getDoc_approver()) && btmDocumentMaster.getDoc_verifier().equals(user.getDoc_verifier()) && btmDocumentMaster.getDoc_owner().equals(user.getDoc_owner())
		) {
	msg="No Modification More";
}else {
			BTMDocumentMaster up = btmDocumentMaster;

			up.setEntity_flg("Y");

			up.setDel_flg("N");

			btmDocumentMasterRep.save(up);

			msg = "Edited Successfully";
}

		}

		return msg;
	}

	// get calendar list

	public List<BTMAdminCalendarMaster> getCalendarlist() {

		List<BTMAdminCalendarMaster> btm_cal = new ArrayList<>();
		List<Object[]> list_obj = btmAdminCalendarMasterRep.findBysrl();

		for (Object[] obj : list_obj) {

			BTMAdminCalendarMaster cal = new BTMAdminCalendarMaster();

			cal.setYear(String.valueOf(obj[0]));
			cal.setMonth(String.valueOf(obj[1]));


			btm_cal.add(cal);

		}
		return btm_cal;
	}

	// get monthly holiday list from HolidayMaster

	public List<BTMAdminHolidayMaster> getMonthlyHolidaylist(String year, String month) throws ParseException {


		List<BTMAdminHolidayMaster> hol_des = new ArrayList<>();
		List<Object[]> list_obj = btmAdminHolidayMasterRep.getMonthlyHolidaylists(year, month);


		for (Object[] obj : list_obj) {

			BTMAdminHolidayMaster holiday = new BTMAdminHolidayMaster();

			holiday.setCal_year(String.valueOf(obj[0]));
			holiday.setCal_month(String.valueOf(obj[1]));
			holiday.setRecord_date(new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(obj[2])));
			// holiday.setDay(String.valueOf(obj[3]));
			holiday.setHoliday_desc(String.valueOf(obj[4]));
			holiday.setHoliday_remarks(String.valueOf(obj[5]));

			hol_des.add(holiday);

		}
		return hol_des;
	}
	
	
	
	//////////CATEGORY CODE MAINTAINNANCE
	public String Catecodemaintain(Bamcategorycodemain Bamcategorycodemain, String formmode,String headcode,String categorycode,String subcategorycode) {

		String msg = "";

		if (formmode.equals("edit")) {
			System.out.println("The id is : "+Bamcategorycodemain.getSl_no());
			System.out.println("The id is : "+Bamcategorycodemain.getSl_no());
			Optional<Bamcategorycodemain> up = Bamcatcodemainrep.findById(Bamcategorycodemain.getSl_no());
			
			if(up.isPresent()) {
				Bamcategorycodemain bamcat = up.get();

				bamcat.setSolid(Bamcategorycodemain.getSolid());
				//bamcat.setLocation(Bamcategorycodemain.getLocation());
				bamcat.setDepreciation_fund_account(Bamcategorycodemain.getDepreciation_fund_account());
				//bamcat.setDepreciation_method(Bamcategorycodemain.getDepreciation_method());
				bamcat.setDepreciation_pandl_account(Bamcategorycodemain.getDepreciation_pandl_account());
				//bamcat.setDepreciation_percentage(Bamcategorycodemain.getDepreciation_percentage());
				bamcat.setAsset_account_number(Bamcategorycodemain.getAsset_account_number());
				Bamcatcodemainrep.save(bamcat);
			
				msg = "Modified Successfully";
			}

		}else if (formmode.equals("add")) {
			
			Session session = sessionFactory.getCurrentSession();

        	String asset_code = Bamcategorycodemain.getAsset_code();
 		    Long count = (Long) session.createQuery("SELECT COUNT(sp) FROM Bamcategorycodemain sp WHERE sp.asset_code = :asset_code")
 		                               .setParameter("asset_code", asset_code)
 		                               .uniqueResult();

 		    if (count > 0) {
 		        msg = " Asset Code already exists. Please Give different ...";
 		    } else {
			
			Session hs = sessionFactory.getCurrentSession();
			Bamcategorycodemain bamcat = new Bamcategorycodemain();
			DecimalFormat numformate = new DecimalFormat("00");
			BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT GENERATE_SRL_NO.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			String serialno = numformate.format(billNumber);

			bamcat.setSl_no(serialno);
			bamcat.setHead_code(headcode);
			bamcat.setHead_description(Bamcategorycodemain.getHead_description());
			bamcat.setCategory_code(categorycode);
			bamcat.setCategory_description(Bamcategorycodemain.getCategory_description());
			bamcat.setSub_category_code(subcategorycode);
			bamcat.setSub_category_description(Bamcategorycodemain.getSub_category_description());
			bamcat.setAsset_code(Bamcategorycodemain.getAsset_code());
			bamcat.setAsset_account_number(Bamcategorycodemain.getAsset_account_number());
			bamcat.setDepreciation_fund_account(Bamcategorycodemain.getDepreciation_fund_account());
			//bamcat.setDepreciation_method(Bamcategorycodemain.getDepreciation_method());
			bamcat.setDepreciation_pandl_account(Bamcategorycodemain.getDepreciation_pandl_account());
			//bamcat.setDepreciation_percentage(Bamcategorycodemain.getDepreciation_percentage());
			bamcat.setSolid(Bamcategorycodemain.getSolid());
			//bamcat.setLocation(Bamcategorycodemain.getLocation());
			msg = "Added Successfully";
			
			Bamcatcodemainrep.save(bamcat);
 		    }
		}
		return msg;
	}public String deletesrn(String asn) {
	    // Attempt to find the entity by its ID (ASN)
	    Optional<Bamcategorycodemain> optionalEntity = Bamcatcodemainrep.findById(asn);

	    if (optionalEntity.isPresent()) {
	        // If the entity exists, delete it
	    	Bamcatcodemainrep.delete(optionalEntity.get());
	        return "Deleted Successfully!";
	    } else {
	        // If the entity does not exist, return an appropriate message
	        return "Deletion Failed: Entity with ASN " + asn + " does not exist.";
	    }
	}

	// Document Manager
	@Value("${document.folder.path}")
	private String documentFolderPath;

	public String DocManaaddedit(Bamdocumentmanager Bamdocumentmanager, String formmode, MultipartFile file) {
	    String msg = "";

	    try {
	        if (formmode.equals("edit")) {
	            Optional<Bamdocumentmanager> up = BamDocmasRep.findById(Bamdocumentmanager.getDoc_id());

	            if (up.isPresent()) {
	                Bamdocumentmanager bamcat = up.get();
	                if(file!=null) {
	                String filePath = saveFile(file, bamcat.getDoc_id());
	                bamcat.setDoc_location(filePath);
	                }
	                bamcat.setModify_time(new Date());
	                bamcat.setDel_flg("N");
	                BamDocmasRep.save(bamcat);
	                msg = "Modified Successfully";
	            }
	        } else if (formmode.equals("add")) {
	        	if(file!=null) {
	            String filePath = saveFile(file, Bamdocumentmanager.getDoc_id());
	            System.out.println(filePath);
	            Bamdocumentmanager.setDoc_location(filePath);
	        	}
	        	Bamdocumentmanager.setDel_flg("N");
	            BamDocmasRep.save(Bamdocumentmanager);
	            msg = "Added Successfully";
	        } else if (formmode.equals("verify")) {
	            Optional<Bamdocumentmanager> up = BamDocmasRep.findById(Bamdocumentmanager.getDoc_id());

	            if (up.isPresent()) {
	                Bamdocumentmanager bamcat = up.get();
	                bamcat.setDel_flg("Y");
	                BamDocmasRep.save(bamcat);
	                msg = "Verified Successfully";
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        msg = "Document Upload Unsuccessful";
	    }

	    return msg;
	}


	private String saveFile(MultipartFile file, String docId) throws IOException {
	    String fileName = docId + "_" + file.getOriginalFilename();
	    String filePath = documentFolderPath + File.separator + fileName;
	    File destinationFile = new File(filePath);
	    file.transferTo(destinationFile);
	    return filePath;
	}
	public String BamAcquisitionadd(BamAcquisition BamAcquisition, String formmode) {

		String msg = "";

		if (formmode.equals("edit")) {

			 Optional<BamAcquisition> up = bamAcquisitionrep.findById(BamAcquisition.getAssetName());
			 
			
			if(up.isPresent()) {
				BamAcquisition bamcat = up.get();
				bamcat.setAssetName(BamAcquisition.getAssetName());
				bamcat.setAssetType(BamAcquisition.getAssetType());
				bamcat.setOriginalCost(BamAcquisition.getOriginalCost());
				bamcat.setDepreciationMethod(BamAcquisition.getDepreciationMethod());
				bamcat.setDepreciationPercentage(BamAcquisition.getDepreciationPercentage());
				bamcat.setDateOfAcquisition(BamAcquisition.getDateOfAcquisition());
				msg = "Modified Successfully";
			}

		} else if (formmode.equals("add")){
			
			BamAcquisition bamcat = BamAcquisition;
			bamAcquisitionrep.save(bamcat);
			msg = "Added Successfully";
			
			bamAcquisitionrep.save(bamcat);
			
		}
		return msg;
	}
	
	/////Inventory Master
	public String Invmastadd(BAMInventorymaster BAMInventorymaster, String formmode, String InvMastadd, String userId,String username, String depr_method, String depr_percent, String headcode, String categorycode, String subcategorycode) throws KeyManagementException, NoSuchAlgorithmException {

	    String msg = "";

	    if (InvMastadd.equals("InvMastadd")) {

	        if (formmode.equals("add")) {

	            Session hs = sessionFactory.getCurrentSession();
	            DecimalFormat numformate = new DecimalFormat("000000");
	            String cate_code = "0";

	            BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT INVENTORY_SRL_NO.NEXTVAL AS SRL_NO FROM DUAL")
	                    .getSingleResult();
	            String serialno = numformate.format(billNumber);

	            System.out.println("Sequence is : " + serialno);
	            if (categorycode.equals("I")) {
	                cate_code = "01";
	            } else if (categorycode.equals("II")) {
	                cate_code = "02";
	            } else if (categorycode.equals("III")) {
	                cate_code = "03";
	            } else if (categorycode.equals("IV")) {
	                cate_code = "04";
	            }

	            String AssetSrlNo = BAMInventorymaster.getSol_id() + BAMInventorymaster.getLoc_type() + headcode + cate_code + subcategorycode + serialno;

	            System.out.println("Asset serial no is : " + AssetSrlNo);

	            BAMInventorymaster bamcat = BAMInventorymaster;
	            bamcat.setAsst_srl_no(AssetSrlNo);
	            bamcat.setEntry_user(userId);
	            bamcat.setEntity_flg("Y");
	            bamcat.setEntry_time(new Date());
	            bamcat.setDel_flg("N");
	            bamcat.setVerify_flg("N");
	            System.out.println("The ins : " + depr_method);
	            System.out.println("depr_percent: " + BAMInventorymaster.getDepr_percent());
	            if (depr_percent != null && !depr_percent.trim().isEmpty()) {
	                bamcat.setDepr_percent(BAMInventorymaster.getDepr_percent());
	                bamcat.setDepr_method(depr_method);
	            } else {
	                System.out.println("The percent is either null or empty.");
	            }
	            Baminvmasrep.save(bamcat);
	            msg = "Asset " + AssetSrlNo + " Generated Successfully...";
	            auditService.logAudit("ADD","Added Successfully",userId,username);

	            try {
	                ApiResponse_Entity data = documentPacks1.getFixmlDoc_account();
	                System.out.println("ApiResponse_Entity data: " + data);
	                
	                if (data != null) {
	                    // Fetch the next sequence value for ApiResponse_Entity (srl_no)
	                    BigDecimal apiResponseSrlNo = (BigDecimal) hs.createNativeQuery("SELECT APIRESPONSE_SRL_NO.NEXTVAL FROM DUAL").getSingleResult();
	                    data.setSrl_no(apiResponseSrlNo); // Set the srl_no manually for ApiResponse_Entity
	                    
	                    // Assuming getFixmlDoc_account() returns an object that contains the account number
	                    String accountNumber = data.getDD_Account_id(); // Get the account number from ApiResponse_Entity
	                    
	                    // Debug: print the account number before setting it to BAMInventorymaster
	                    System.out.println("Account number retrieved from ApiResponse_Entity: " + accountNumber);
	                    
	                    // Set the account number from ApiResponse_Entity to BAMInventorymaster
	                    bamcat.setAcc_number(accountNumber);
	                    
	                    // Debug: print the BAMInventorymaster object before saving
	                    System.out.println("BAMInventorymaster object before saving: " + bamcat);
	                    
	                    // Save the ApiResponse_Entity to the database
	                    apiResponseRepository.save(data);
	                    System.out.println("ApiResponse_Entity data saved successfully: " + data);
	                    
	                    // Save BAMInventorymaster to the database
	                    Baminvmasrep.save(bamcat);
	                    
	                    // Debug: print the BAMInventorymaster object after saving
	                    System.out.println("BAMInventorymaster object saved to database: " + bamcat);
	                    
	                    // Create and save ChartOfAccounts
	                    ChartOfAccounts chartOfAccounts = new ChartOfAccounts();
	                    chartOfAccounts.setAsset_serial_no(AssetSrlNo);
	                    chartOfAccounts.setAccount_number(accountNumber); // Set account number in ChartOfAccounts
	                    chartOfAccounts.setSol_id("999");
	                    
	                    // Save the ChartOfAccounts entity to the database
	                    chartofaccountsrep.save(chartOfAccounts);
	                    System.out.println("ChartOfAccounts saved successfully: " + chartOfAccounts);
	                    
	                } else {
	                    msg += "No ApiResponse_Entity data to save.";
	                }
	            } catch (Exception e) {
	                // Handle any exceptions here (e.g., server down, database issues)
	                msg += "Error: Unable to fetch/save ApiResponse_Entity data. " + e.getMessage();
	                System.err.println("Error occurred while handling ApiResponse_Entity: " + e);
	                // Log the error and continue with the rest of the code
	            }
	            }else if(formmode.equals("edit")){
			
			Optional<BAMInventorymaster> BAMInvms = Baminvmasrep.findById(BAMInventorymaster.getAsst_srl_no());
			if(BAMInvms.isPresent()) {
				BAMInventorymaster bamcat = BAMInvms.get();
				bamcat.setAsst_name(BAMInventorymaster.getAsst_name());

				bamcat.setAsset_head(BAMInventorymaster.getAsset_head());
				bamcat.setAsset_category(BAMInventorymaster.getAsset_category());
				bamcat.setAsset_sub_category(BAMInventorymaster.getAsset_sub_category());
				bamcat.setCategory_desc(BAMInventorymaster.getCategory_desc());
				bamcat.setAsset_type(BAMInventorymaster.getAsset_type());
				bamcat.setAsst_crncy(BAMInventorymaster.getAsst_crncy());

				bamcat.setDate_of_purchase(BAMInventorymaster.getDate_of_purchase());

				bamcat.setYear_of_purchase(BAMInventorymaster.getYear_of_purchase());
				bamcat.setOrg_cost(BAMInventorymaster.getOrg_cost());
				bamcat.setAsst_exp_date(BAMInventorymaster.getAsst_exp_date());

				bamcat.setAsst_exp_date(BAMInventorymaster.getAsst_exp_date());
				bamcat.setAsst_rmks(BAMInventorymaster.getAsst_rmks());
				bamcat.setDepr_flag(BAMInventorymaster.getDepr_flag());
				bamcat.setDepr_freq(BAMInventorymaster.getDepr_freq());

				if (depr_percent != null && !depr_percent.trim().isEmpty()) {
				    bamcat.setDepr_percent(BAMInventorymaster.getDepr_percent());
				    System.out.println(depr_method);
				    bamcat.setDepr_method(depr_method);
				} else {
				    System.out.println("The percent is either null or empty.");
				}
				bamcat.setAcc_depr(BAMInventorymaster.getAcc_depr());
				bamcat.setLife_span_mth(BAMInventorymaster.getLife_span_mth());

				bamcat.setDate_of_last_depr(BAMInventorymaster.getDate_of_last_depr());
				bamcat.setDate_of_acqn(BAMInventorymaster.getDate_of_acqn());
				bamcat.setDate_of_last_tfr(BAMInventorymaster.getDate_of_last_tfr());
				bamcat.setCur_book_value(BAMInventorymaster.getCur_book_value());
				bamcat.setMkt_value(BAMInventorymaster.getMkt_value());
				bamcat.setLoc_type(BAMInventorymaster.getLoc_type());
				bamcat.setSol_id(BAMInventorymaster.getSol_id());
				bamcat.setEmp_id(BAMInventorymaster.getEmp_id());
				bamcat.setDept_div_name(BAMInventorymaster.getDept_div_name());
				bamcat.setLoca_addr(BAMInventorymaster.getLoca_addr());
				bamcat.setLoc_rmks(BAMInventorymaster.getLoc_rmks());
				bamcat.setGtee_amt(BAMInventorymaster.getGtee_amt());
				bamcat.setGtee_amt_percent(BAMInventorymaster.getGtee_amt_percent());
				bamcat.setNom_depr_amt(BAMInventorymaster.getNom_depr_amt());
				bamcat.setDepr_rmks(BAMInventorymaster.getDepr_rmks());
				
				bamcat.setModify_user(BAMInventorymaster.getAsset_head());
				bamcat.setModify_time(new Date());
				bamcat.setDel_flg("N");
				bamcat.setModify_flg("Y");
				bamcat.setVerify_flg("N");
				Baminvmasrep.save(bamcat);
				msg = "Modified Successfully";
				// After updating, log the audit entry
	            auditService.logAudit("EDIT","Modified Successfully", userId, username);

			}else {
				msg="Not a valid id";
			}
			
			
			
		}else if(formmode.equals("verify")){
			
			Optional<BAMInventorymaster> BAMInvms = Baminvmasrep.findById(BAMInventorymaster.getAsst_srl_no());
			if(BAMInvms.isPresent()) {
				BAMInventorymaster bamcat = BAMInvms.get();
				bamcat.setVerify_flg("Y");
				Baminvmasrep.save(bamcat);
				msg = "Verified Successfully";
	            auditService.logAudit("VERIFY","Verified Successfully",userId,username);
			}else {
				msg="Error Occured !!!";
			}
			
		}else {
			msg = "Invalid Option Please contact Administrator";
		}
		}else if(InvMastadd.equals("InvTransadd")) {
			
		}
		return msg;
	}

	
/////Inventory Transfer
	public String Invtranadd(Baminventorytransfer BAMInventorytransfer, String formmode, String InvTranadd, String userId, String username) {
	    String msg = "";

	    if (InvTranadd.equals("InvTranadd")) {
	        if (formmode.equals("add")) {
	            try {
	                if (BAMInventorytransfer.getAsst_srl_npo() == null) {
	                    return "Asset Serial Number is required.";
	                }
	                
	                Baminventorytransfer bamcat = BAMInventorytransfer;
	                bamcat.setEntry_user(userId);
	                bamcat.setEntity_flg("Y");
	                bamcat.setEntry_time(new Date());
	                bamcat.setDel_flg("N");
	                
	                BamInvtrnrep.save(bamcat);			
	                msg = "Added Successfully";
		            auditService.logAudit("ADD","Added Successfully",userId,username);
	            } catch (Exception e) {
	                msg = "Error occurred: " + e.getMessage();
	                e.printStackTrace(); // Log the error
	            }
	        }else if(formmode.equals("edit")){
			
			Optional<Baminventorytransfer> BAMInvms = BamInvtrnrep.findById(BAMInventorytransfer.getAsst_srl_npo());
			if(BAMInvms.isPresent()) {
				Baminventorytransfer bamcat = BAMInvms.get();
				bamcat.setAsst_name(BAMInventorytransfer.getAsst_name());
				
				bamcat.setAsst_xfr_ref_no(BAMInventorytransfer.getAsst_xfr_ref_no()); 		
				bamcat.setAsst_head(BAMInventorytransfer.getAsst_head());
				bamcat.setAsst_category(BAMInventorytransfer.getAsst_category());	
				bamcat.setAsst_sub_category(BAMInventorytransfer.getAsst_sub_category());
				bamcat.setAsst_type(BAMInventorytransfer.getAsst_type());
				bamcat.setAsst_crncy(BAMInventorytransfer.getAsst_crncy());

				bamcat.setDate_of_purchase(BAMInventorytransfer.getDate_of_purchase());

				bamcat.setYear_of_purchase(BAMInventorytransfer.getYear_of_purchase());
				bamcat.setOrg_cost(BAMInventorytransfer.getOrg_cost());
				bamcat.setLife_span_mth(BAMInventorytransfer.getLife_span_mth());

				bamcat.setAsst_exp_date(BAMInventorytransfer.getAsst_exp_date());
				bamcat.setAsst_rmks(BAMInventorytransfer.getAsst_rmks());
				bamcat.setVendor_name(BAMInventorytransfer.getVendor_name());
				
				bamcat.setPurchase_det(BAMInventorytransfer.getPurchase_det());
				bamcat.setDepr_flg(BAMInventorytransfer.getDepr_flg());
				bamcat.setDepr_freq(BAMInventorytransfer.getDepr_freq());
				bamcat.setDepr_method(BAMInventorytransfer.getDepr_method());
				bamcat.setDepr_percent(BAMInventorytransfer.getDepr_percent());
				bamcat.setAcc_depr(BAMInventorytransfer.getAcc_depr());

				bamcat.setDat_of_last_depr(BAMInventorytransfer.getDat_of_last_depr());
				bamcat.setDate_of_acqn(BAMInventorytransfer.getDate_of_acqn());
				bamcat.setDate_of_tfr(BAMInventorytransfer.getDate_of_tfr());
				bamcat.setCur_book_value_xfr_date(BAMInventorytransfer.getCur_book_value_xfr_date());
				bamcat.setMarket_value(BAMInventorytransfer.getMarket_value());
				bamcat.setFrom_category(BAMInventorytransfer.getFrom_category());
				bamcat.setFrom_loc_type(BAMInventorytransfer.getFrom_loc_type());
				bamcat.setFrom_sol_id(BAMInventorytransfer.getFrom_sol_id());
				bamcat.setFrom_emp_id(BAMInventorytransfer.getFrom_emp_id());
				bamcat.setFrom_dept_div_name(BAMInventorytransfer.getFrom_dept_div_name());
				bamcat.setFrom_loc_addr(BAMInventorytransfer.getFrom_loc_addr());
				bamcat.setFrom_loc_rmks(BAMInventorytransfer.getFrom_loc_rmks());
				bamcat.setTo_category(BAMInventorytransfer.getTo_category());
				bamcat.setTo_loc_type(BAMInventorytransfer.getTo_loc_type());
				bamcat.setTo_emp_id(BAMInventorytransfer.getTo_emp_id());
				bamcat.setTo_sol_id(BAMInventorytransfer.getTo_sol_id());
				bamcat.setTo_loc_addr(BAMInventorytransfer.getTo_loc_addr());
				bamcat.setTo_loc_rmks(BAMInventorytransfer.getTo_loc_rmks());
				bamcat.setTo_dept_div_name(BAMInventorytransfer.getTo_dept_div_name());
				
				bamcat.setModify_user(BAMInventorytransfer.getAsst_head());
				bamcat.setModify_time(new Date());
				bamcat.setDel_flg("N");
				bamcat.setModify_flg("Y");
				BamInvtrnrep.save(bamcat);
				msg = "Modified Successfully";
	            auditService.logAudit("EDIT","Modified Successfully", userId, username);

			}else {
				msg="Not a valid id";
			}
			
			
			
		}else if(formmode.equals("verify")){
			
			Optional<Baminventorytransfer> BAMInvms = BamInvtrnrep.findById(BAMInventorytransfer.getAsst_srl_npo());
			if(BAMInvms.isPresent()) {
				
				
				BAMInventorymaster up=Baminvmasrep.getview(BAMInventorytransfer.getAsst_srl_npo());
				up.setDel_flg("Y");
				Baminvmasrep.save(up);
				
				Baminventorytransfer bamcat = BAMInvms.get();
				bamcat.setDel_flg("Y");
				bamcat.setTransaction_detail("Transfered");
				BamInvtrnrep.save(bamcat);
				
				List<BAM_AssetFlows_Entity> BAMInvms1 = BAM_AssetFlows_rep.getview(BAMInventorytransfer.getAsst_srl_npo());
				for(BAM_AssetFlows_Entity up1:BAMInvms1) {
					up1.setTransfer_details("Transfered");
					BAM_AssetFlows_rep.save(up1);
				}
				msg = "Verified Successfully";
	            auditService.logAudit("VERIFY","Verified Successfully",userId,username);
			}
			
		}else {
			msg = "Invalid Option Please contact Administrator";
		}
		}else if(InvTranadd.equals("InvTransadd")) {
			
		}
		return msg;
	}	
/////Sale and write 
	public String SaleWriteadd(Bamsaleandwrite BAMsaleandwrite, String formmode,String SaleandWriteadd,String userId,String username) {

		String msg = "";

		if(SaleandWriteadd.equals("SaleandWriteadd")) {
		if (formmode.equals("add")){
			
			//For sale and write
			Bamsaleandwrite bamcat = BAMsaleandwrite;
			bamcat.setEntry_user(userId);
			bamcat.setEntity_flg("Y");
			bamcat.setEntry_time(new Date());
			bamcat.setDel_flg("N");
			bamsalerep.save(bamcat);
			
			//For Inventory master
			BAMInventorymaster ups=BAMInvmastrep.getview(BAMsaleandwrite.getAsst_srl_no());
			ups.setSale_flg("S");
			BAMInvmastrep.save(ups);
			
			//For Inventory Transfer
			Baminventorytransfer as=BamInvtrnrep.getall(BAMsaleandwrite.getAsst_srl_no());
			if (as!=null) {
			as.setSale_flg("S");
			as.setWriteoff_flg("S");
			BamInvtrnrep.save(as);
			}
			
			//For depreciation  
			

		    List<BAM_AssetFlows_Entity> getlist = BAM_AssetFlows_rep.getview(BAMsaleandwrite.getAsst_srl_no());
		    if (getlist!=null) {
			 for (BAM_AssetFlows_Entity up : getlist) {
				 
			            up.setSale_flg("S");
			            up.setWriteoff_flg("S");
						BAM_AssetFlows_rep.save(up);
			            
			    }
			 }
		    
		
			 msg = "Added Successfully";
	            auditService.logAudit("ADD","Added Successfully",userId,username);
		
		}else if(formmode.equals("edit")){
			
			Optional<Bamsaleandwrite> BAMInvms = bamsalerep.findById(BAMsaleandwrite.getAsst_srl_no());
			if(BAMInvms.isPresent()) {
				Bamsaleandwrite bamcat = BAMInvms.get();
				bamcat.setAsst_name(BAMsaleandwrite.getAsst_name());
				bamcat.setAsst_head(BAMsaleandwrite.getAsst_head());
				bamcat.setAsst_category(BAMsaleandwrite.getAsst_category());
				bamcat.setAsst_sub_cateogry(BAMsaleandwrite.getAsst_sub_cateogry());
				bamcat.setCategory_desc(BAMsaleandwrite.getCategory_desc());
				bamcat.setAsst_type(BAMsaleandwrite.getAsst_type());
				bamcat.setAsst_crncy(BAMsaleandwrite.getAsst_crncy());

				bamcat.setDate_of_purchase(BAMsaleandwrite.getDate_of_purchase());

				bamcat.setYear_of_purchase(BAMsaleandwrite.getYear_of_purchase());
				bamcat.setOrg_cost(BAMsaleandwrite.getOrg_cost());
				bamcat.setAsst_exp_date(BAMsaleandwrite.getAsst_exp_date());
				bamcat.setAsst_rmks(BAMsaleandwrite.getAsst_rmks());
				bamcat.setDepr_flag(BAMsaleandwrite.getDepr_flag());
				bamcat.setDepr_freq(BAMsaleandwrite.getDepr_freq());
				bamcat.setDepr_method(BAMsaleandwrite.getDepr_method());
				bamcat.setDepr_percent(BAMsaleandwrite.getDepr_percent());
				bamcat.setAcc_depr(BAMsaleandwrite.getAcc_depr());

				bamcat.setDate_of_last_depr(BAMsaleandwrite.getDate_of_last_depr());				
				bamcat.setDate_of_sale(BAMsaleandwrite.getDate_of_sale() );
				bamcat.setBook_value_sale_date(BAMsaleandwrite.getBook_value_sale_date());
				bamcat.setSale_value(BAMsaleandwrite.getSale_value());
				bamcat.setProfit_loss(BAMsaleandwrite.getProfit_loss());
				bamcat.setBuyer_name(BAMsaleandwrite.getBuyer_name());
				bamcat.setSale_det(BAMsaleandwrite.getSale_det());
				bamcat.setDate_of_scrap(BAMsaleandwrite.getDate_of_scrap());
				bamcat.setScrap_value(BAMsaleandwrite.getScrap_value());
				bamcat.setBook_value_scrap_date_amount(BAMsaleandwrite.getBook_value_scrap_date_amount());
				bamcat.setScrap_rmks(BAMsaleandwrite.getScrap_rmks());
				bamcat.setLoc_type(BAMsaleandwrite.getLoc_type());
				bamcat.setSol_id(BAMsaleandwrite.getSol_id());
				bamcat.setEmp_id(BAMsaleandwrite.getEmp_id());
				bamcat.setDept_div_name(BAMsaleandwrite.getDept_div_name());
				
				bamcat.setModify_user(BAMsaleandwrite.getAsst_head());
				bamcat.setModify_time(new Date());
				bamcat.setDel_flg("N");
				bamcat.setModify_flg("Y");
				bamsalerep.save(bamcat);
	            auditService.logAudit("EDIT","Modified Successfully", userId, username);
				msg = "Modified Successfully";
			}else {
				msg="Not a valid id";
			}
			
			
			
		}else if(formmode.equals("verify")){
			
			Optional<Bamsaleandwrite> BAMInvms = bamsalerep.findById(BAMsaleandwrite.getAsst_srl_no());
			if(BAMInvms.isPresent()) {
				Bamsaleandwrite bamcat = BAMInvms.get();
				bamcat.setDel_flg("Y");
				bamsalerep.save(bamcat);
	            auditService.logAudit("VERIFY","Verified Successfully",userId,username);
			}
			
		}else {
			msg = "Invalid Option Please contact Administrator";
		}
		}else if(SaleandWriteadd.equals("InvTransadd")) {
			
		}
		return msg;
	}
	
	
	
/////Chart of Accounts 
	public String Chartofadd(ChartOfAccounts chartOfAccounts, String formmode,String ChartofAccadd,String userId, String username) {

		String msg = "";

		if(ChartofAccadd.equals("ChartofAccadd")) {
		if (formmode.equals("add")){
			 List<BAM_AssetFlows_Entity> BAMInvms1 = BAM_AssetFlows_rep.getview(chartOfAccounts.getAsset_serial_no());
			for(BAM_AssetFlows_Entity up:BAMInvms1) {
				up.setTransfer_details("Transfered");
				BAM_AssetFlows_rep.save(up);
				

			}
			
			  ChartOfAccounts bamcat = chartOfAccounts; bamcat.setEntry_user(userId);
			  bamcat.setEntity_flg("Y"); bamcat.setEntry_time(new Date());
			  bamcat.setDel_flg("N"); bamcat.setTransaction_detail("Transfered");
			  chartofaccountsrep.save(bamcat);
			 
			chartofaccountsrep.save(bamcat);
			
			msg = "Added Successfully";
            auditService.logAudit("ADD","Added Successfully",userId,username);
			
		}else if(formmode.equals("edit")){
			
			Optional<ChartOfAccounts> BAMcharts = chartofaccountsrep.findById(chartOfAccounts.getAsset_serial_no());
			if(BAMcharts.isPresent()) {
				ChartOfAccounts bamcat = BAMcharts.get();
				bamcat.setSol_id(chartOfAccounts.getSol_id());
				bamcat.setSol_desc(chartOfAccounts.getSol_desc());
				bamcat.setCrncy_code(chartOfAccounts.getCrncy_code());
				bamcat.setHome_crncy_ac(chartOfAccounts.getHome_crncy_ac());
				bamcat.setGlsh_code(chartOfAccounts.getGlsh_code());
				bamcat.setGlsh_desc(chartOfAccounts.getGlsh_desc());
				bamcat.setSchm_code(chartOfAccounts.getSchm_code());
				bamcat.setSchm_desc(chartOfAccounts.getGlsh_desc());
				bamcat.setProd_group(chartOfAccounts.getProd_group());
				bamcat.setAcc_type(chartOfAccounts.getAcc_type());
				bamcat.setSystem_tran_flg(chartOfAccounts.getSystem_tran_flg());
				bamcat.setTran_restrict_flg(chartOfAccounts.getTran_restrict_flg());
				bamcat.setTran_type(chartOfAccounts.getTran_type());
				bamcat.setAddl_det_req_flg(chartOfAccounts.getAddl_det_req_flg());
				bamcat.setAddl_det_type(chartOfAccounts.getAddl_det_type());
				bamcat.setAcc_bal(chartOfAccounts.getAcc_bal());
				bamcat.setBal_type(chartOfAccounts.getBal_type());
				bamcat.setLast_tran_date(chartOfAccounts.getLast_tran_date());
				bamcat.setAcc_status(chartOfAccounts.getAcc_status());
				bamcat.setTransaction_detail(chartOfAccounts.getTransaction_detail());
				//bamcat.setModify_user(chartOfAccounts.getAsst_head());
				bamcat.setModify_time(new Date());
				bamcat.setDel_flg("N");
				bamcat.setModify_flg("Y");
				chartofaccountsrep.save(bamcat);
				msg = "Modified Successfully";
	            auditService.logAudit("EDIT","Modified Successfully", userId, username);
			}else {
				msg="Not a valid id";
			}
			
			
			
		}else if(formmode.equals("verify")){
			
			Optional<ChartOfAccounts> BAMInvms = chartofaccountsrep.findById(chartOfAccounts.getAsset_serial_no());
			if(BAMInvms.isPresent()) {
				ChartOfAccounts bamcat = BAMInvms.get();
				bamcat.setDel_flg("Y");
				chartofaccountsrep.save(bamcat);
	            auditService.logAudit("VERIFY","Verified Successfully",userId,username);
			}
			
		}else {
			msg = "Invalid Option Please contact Administrator";
		}
		}else if(ChartofAccadd.equals("ChartofAccadd")) {
			
		}
		return msg;
	}	

//dash

public String Dashofadd(DashBoardEntity dashBoardEntity, String formmode,String DashofAccadd,String userId,String username) {

	String msg = "";

	if(DashofAccadd.equals("DashofAccadd")) {
	if (formmode.equals("add")){
		 List<BAM_AssetFlows_Entity> BAMInvms1 = BAM_AssetFlows_rep.getview(dashBoardEntity.getScreen_id());
		for(BAM_AssetFlows_Entity up:BAMInvms1) {
			up.setTransfer_details("Transfered");
			BAM_AssetFlows_rep.save(up);
			

		}
		
		DashBoardEntity bamcat = dashBoardEntity; bamcat.setEntry_user(userId);
		  //bamcat.setEntity_flg("Y"); bamcat.setEntry_time(new Date());
		 // bamcat.setDel_flg("N"); bamcat.setTransaction_detail("Transfered");
		dashboardRepository.save(bamcat);
		 
		dashboardRepository.save(bamcat);
		
		msg = "Added Successfully";
        auditService.logAudit("ADD","Added Successfully",userId,username);
		
	}else if(formmode.equals("edit")){
		
		Optional<DashBoardEntity> BAMDash = dashboardRepository.findById(dashBoardEntity.getScreen_id());
		if(BAMDash.isPresent()) {
			DashBoardEntity bamcat = BAMDash.get();
			bamcat.setScreen_id(dashBoardEntity.getScreen_id());
			bamcat.setScreen_name(dashBoardEntity.getScreen_name());
			bamcat.setDash_board_1(dashBoardEntity.getDash_board_1());
			bamcat.setDash_board_2(dashBoardEntity.getDash_board_2());
			bamcat.setDash_board_3(dashBoardEntity.getDash_board_3());
			bamcat.setDash_board_4(dashBoardEntity.getDash_board_4());
			bamcat.setDash_board_5(dashBoardEntity.getDash_board_5());
			bamcat.setDescription_1(dashBoardEntity.getDescription_1());
			bamcat.setDescription_2(dashBoardEntity.getDescription_2());
			bamcat.setDescription_3(dashBoardEntity.getDescription_3());
			bamcat.setDescription_4(dashBoardEntity.getDescription_4());
			bamcat.setDescription_5(dashBoardEntity.getDescription_5());
			//bamcat.setModify_user(chartOfAccounts.getAsst_head());
			//bamcat.setModify_time(new Date());
			//bamcat.setDel_flg("N");
			//bamcat.setModify_flg("Y");
			dashboardRepository.save(bamcat);
			msg = "Modified Successfully";
            auditService.logAudit("EDIT","Modified Successfully", userId, username);
		}else {
			msg="Not a valid id";
		}
		
		
		
	}else if(formmode.equals("verify")){
		
		Optional<DashBoardEntity> BAMInvms = dashboardRepository.findById(dashBoardEntity.getScreen_id());
		if(BAMInvms.isPresent()) {
			DashBoardEntity bamcat = BAMInvms.get();
			//bamcat.setDel_flg("Y");
			dashboardRepository.save(bamcat);
            auditService.logAudit("VERIFY","Verified Successfully",userId,username);
		}
		
	}else {
		msg = "Invalid Option Please contact Administrator";
	}
	}else if(DashofAccadd.equals("DashofAccadd")) {
		
	}
	return msg;
}	
}
