package com.bornfire.entities;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BAM_APIRESPONSE")
public class ApiResponse_Entity {
	@Id
	BigDecimal srl_no;
	String DD_Account_id;

	String status;
	String errorMsg;
	public String getDD_Account_id() {
		return DD_Account_id;
	}
	public void setDD_Account_id(String dD_Account_id) {
		DD_Account_id = dD_Account_id;
	}
	public BigDecimal getSrl_no() {
		return srl_no;
	}
	public void setSrl_no(BigDecimal srl_no) {
		this.srl_no = srl_no;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public ApiResponse_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}

