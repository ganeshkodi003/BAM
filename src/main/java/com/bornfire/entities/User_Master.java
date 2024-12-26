package com.bornfire.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER_MASTER")
public class User_Master {

    @Id
    @Column(name = "USER_ID", length = 100)
    private String userId;

    @Column(name = "USER_NAME", length = 10)
    private String userName;

    @Column(name = "BANK_ID", length = 100)
    private String bankId;

    @Column(name = "BANK_NAME", length = 10)
    private String bankName;

    @Column(name = "HOME_BR_ID", length = 100)
    private String homeBrId;

    @Column(name = "HOME_BR_NAME", length = 100)
    private String homeBrName;

    @Column(name = "WORK_BR_ID", length = 10)
    private String workBrId;

    @Column(name = "WORK_BR_NAME", length = 100)
    private String workBrName;

    @Column(name = "EMP_REF", length = 10)
    private String empRef;

    @Column(name = "USER_DESIG", length = 10)
    private String userDesig;

    @Column(name = "USER_CLASS", length = 10)
    private String userClass;

    @Column(name = "USER_ROLE", length = 10)
    private String userRole;

    @Column(name = "MOBILE_NO", length = 15)
    private String mobileNo;

    @Column(name = "ALT_NO", length = 15)
    private String altNo;

    @Column(name = "ACCESS_CODE", length = 10)
    private String accessCode;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "USER_ADDR_1", length = 100)
    private String userAddr1;

    @Column(name = "USER_ADDR_2", length = 100)
    private String userAddr2;

    @Column(name = "USER_CITY", length = 50)
    private String userCity;

    @Column(name = "USER_STATE", length = 50)
    private String userState;

    @Column(name = "USER_CNTRY", length = 5)
    private String userCntry;

    @Column(name = "USER_ZIP", length = 15)
    private String userZip;

    @Column(name = "VIRTUAL_USER_FLG", length = 1)
    private String virtualUserFlg;

    @Column(name = "USER_BR_FLG", length = 1)
    private String userBrFlg;

    @Column(name = "USER_CONT_PERSON", length = 100)
    private String userContPerson;

    @Column(name = "CONT_PERSON_NO", length = 15)
    private String contPersonNo;

    @Column(name = "PASSWORD", length = 100)
    private String password;

    @Column(name = "LIFE_OF_PW")
    private Integer lifeOfPw;

    @Column(name = "ACCT_EXPY_DATE")
    @Temporal(TemporalType.DATE)
    private Date acctExpyDate;

    @Column(name = "PW_EXPY_DATE")
    @Temporal(TemporalType.DATE)
    private Date pwExpyDate;

    @Column(name = "USER_REMARKS", length = 100)
    private String userRemarks;

    @Column(name = "DISABLE_FLG", length = 1)
    private String disableFlg;

    @Column(name = "DISABLE_START_DATE")
    @Temporal(TemporalType.DATE)
    private Date disableStartDate;

    @Column(name = "DISABLE_END_DATE")
    @Temporal(TemporalType.DATE)
    private Date disableEndDate;

    @Column(name = "LOGIN_LOW")
    @Temporal(TemporalType.DATE)
    private Date loginLow;

    @Column(name = "LOGIN_HIGH")
    @Temporal(TemporalType.DATE)
    private Date loginHigh;

    @Column(name = "ENTRY_USER", length = 10)
    private String entryUser;

    @Column(name = "MODIFY_USER", length = 10)
    private String modifyUser;

    @Column(name = "AUTH_USER", length = 10)
    private String authUser;

    @Column(name = "ENTRY_TIME")
    @Temporal(TemporalType.DATE)
    private Date entryTime;

    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.DATE)
    private Date modifyTime;

    @Column(name = "AUTH_TIME")
    @Temporal(TemporalType.DATE)
    private Date authTime;

    @Column(name = "DEL_FLG", length = 1)
    private String delFlg;

    @Column(name = "ENTITY_FLG", length = 1)
    private String entityFlg;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getHomeBrId() {
		return homeBrId;
	}

	public void setHomeBrId(String homeBrId) {
		this.homeBrId = homeBrId;
	}

	public String getHomeBrName() {
		return homeBrName;
	}

	public void setHomeBrName(String homeBrName) {
		this.homeBrName = homeBrName;
	}

	public String getWorkBrId() {
		return workBrId;
	}

	public void setWorkBrId(String workBrId) {
		this.workBrId = workBrId;
	}

	public String getWorkBrName() {
		return workBrName;
	}

	public void setWorkBrName(String workBrName) {
		this.workBrName = workBrName;
	}

	public String getEmpRef() {
		return empRef;
	}

	public void setEmpRef(String empRef) {
		this.empRef = empRef;
	}

	public String getUserDesig() {
		return userDesig;
	}

	public void setUserDesig(String userDesig) {
		this.userDesig = userDesig;
	}

	public String getUserClass() {
		return userClass;
	}

	public void setUserClass(String userClass) {
		this.userClass = userClass;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAltNo() {
		return altNo;
	}

	public void setAltNo(String altNo) {
		this.altNo = altNo;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserAddr1() {
		return userAddr1;
	}

	public void setUserAddr1(String userAddr1) {
		this.userAddr1 = userAddr1;
	}

	public String getUserAddr2() {
		return userAddr2;
	}

	public void setUserAddr2(String userAddr2) {
		this.userAddr2 = userAddr2;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getUserCntry() {
		return userCntry;
	}

	public void setUserCntry(String userCntry) {
		this.userCntry = userCntry;
	}

	public String getUserZip() {
		return userZip;
	}

	public void setUserZip(String userZip) {
		this.userZip = userZip;
	}

	public String getVirtualUserFlg() {
		return virtualUserFlg;
	}

	public void setVirtualUserFlg(String virtualUserFlg) {
		this.virtualUserFlg = virtualUserFlg;
	}

	public String getUserBrFlg() {
		return userBrFlg;
	}

	public void setUserBrFlg(String userBrFlg) {
		this.userBrFlg = userBrFlg;
	}

	public String getUserContPerson() {
		return userContPerson;
	}

	public void setUserContPerson(String userContPerson) {
		this.userContPerson = userContPerson;
	}

	public String getContPersonNo() {
		return contPersonNo;
	}

	public void setContPersonNo(String contPersonNo) {
		this.contPersonNo = contPersonNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLifeOfPw() {
		return lifeOfPw;
	}

	public void setLifeOfPw(Integer lifeOfPw) {
		this.lifeOfPw = lifeOfPw;
	}

	public Date getAcctExpyDate() {
		return acctExpyDate;
	}

	public void setAcctExpyDate(Date acctExpyDate) {
		this.acctExpyDate = acctExpyDate;
	}

	public Date getPwExpyDate() {
		return pwExpyDate;
	}

	public void setPwExpyDate(Date pwExpyDate) {
		this.pwExpyDate = pwExpyDate;
	}

	public String getUserRemarks() {
		return userRemarks;
	}

	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}

	public String getDisableFlg() {
		return disableFlg;
	}

	public void setDisableFlg(String disableFlg) {
		this.disableFlg = disableFlg;
	}

	public Date getDisableStartDate() {
		return disableStartDate;
	}

	public void setDisableStartDate(Date disableStartDate) {
		this.disableStartDate = disableStartDate;
	}

	public Date getDisableEndDate() {
		return disableEndDate;
	}

	public void setDisableEndDate(Date disableEndDate) {
		this.disableEndDate = disableEndDate;
	}

	public Date getLoginLow() {
		return loginLow;
	}

	public void setLoginLow(Date loginLow) {
		this.loginLow = loginLow;
	}

	public Date getLoginHigh() {
		return loginHigh;
	}

	public void setLoginHigh(Date loginHigh) {
		this.loginHigh = loginHigh;
	}

	public String getEntryUser() {
		return entryUser;
	}

	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getAuthUser() {
		return authUser;
	}

	public void setAuthUser(String authUser) {
		this.authUser = authUser;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getAuthTime() {
		return authTime;
	}

	public void setAuthTime(Date authTime) {
		this.authTime = authTime;
	}

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

	public String getEntityFlg() {
		return entityFlg;
	}

	public void setEntityFlg(String entityFlg) {
		this.entityFlg = entityFlg;
	}

	public User_Master(String userId, String userName, String bankId, String bankName, String homeBrId,
			String homeBrName, String workBrId, String workBrName, String empRef, String userDesig, String userClass,
			String userRole, String mobileNo, String altNo, String accessCode, String email, String userAddr1,
			String userAddr2, String userCity, String userState, String userCntry, String userZip,
			String virtualUserFlg, String userBrFlg, String userContPerson, String contPersonNo, String password,
			Integer lifeOfPw, Date acctExpyDate, Date pwExpyDate, String userRemarks, String disableFlg,
			Date disableStartDate, Date disableEndDate, Date loginLow, Date loginHigh, String entryUser,
			String modifyUser, String authUser, Date entryTime, Date modifyTime, Date authTime, String delFlg,
			String entityFlg) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.bankId = bankId;
		this.bankName = bankName;
		this.homeBrId = homeBrId;
		this.homeBrName = homeBrName;
		this.workBrId = workBrId;
		this.workBrName = workBrName;
		this.empRef = empRef;
		this.userDesig = userDesig;
		this.userClass = userClass;
		this.userRole = userRole;
		this.mobileNo = mobileNo;
		this.altNo = altNo;
		this.accessCode = accessCode;
		this.email = email;
		this.userAddr1 = userAddr1;
		this.userAddr2 = userAddr2;
		this.userCity = userCity;
		this.userState = userState;
		this.userCntry = userCntry;
		this.userZip = userZip;
		this.virtualUserFlg = virtualUserFlg;
		this.userBrFlg = userBrFlg;
		this.userContPerson = userContPerson;
		this.contPersonNo = contPersonNo;
		this.password = password;
		this.lifeOfPw = lifeOfPw;
		this.acctExpyDate = acctExpyDate;
		this.pwExpyDate = pwExpyDate;
		this.userRemarks = userRemarks;
		this.disableFlg = disableFlg;
		this.disableStartDate = disableStartDate;
		this.disableEndDate = disableEndDate;
		this.loginLow = loginLow;
		this.loginHigh = loginHigh;
		this.entryUser = entryUser;
		this.modifyUser = modifyUser;
		this.authUser = authUser;
		this.entryTime = entryTime;
		this.modifyTime = modifyTime;
		this.authTime = authTime;
		this.delFlg = delFlg;
		this.entityFlg = entityFlg;
	}

	public User_Master() {
		super();
		// TODO Auto-generated constructor stub
	}

   
}

