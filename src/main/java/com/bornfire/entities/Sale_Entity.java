package com.bornfire.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WRITE_OFF_REPORT")
public class Sale_Entity {

	@Id
    private String asset_serial_no;
    private Date report_date;
    private String report_type;
    private String entry_user;
    private Date entry_date;
    private String modify_user;
    private Date modify_date;
    private String verify_user;
    private Date verify_date;
    private String status;
	public String getAsset_serial_no() {
		return asset_serial_no;
	}
	public void setAsset_serial_no(String asset_serial_no) {
		this.asset_serial_no = asset_serial_no;
	}
	public Date getReport_date() {
		return report_date;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	public String getReport_type() {
		return report_type;
	}
	public void setReport_type(String report_type) {
		this.report_type = report_type;
	}
	public String getEntry_user() {
		return entry_user;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public Date getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}
	public String getModify_user() {
		return modify_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public String getVerify_user() {
		return verify_user;
	}
	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}
	public Date getVerify_date() {
		return verify_date;
	}
	public void setVerify_date(Date verify_date) {
		this.verify_date = verify_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Sale_Entity(String asset_serial_no, Date report_date, String report_type, String entry_user, Date entry_date,
			String modify_user, Date modify_date, String verify_user, Date verify_date, String status) {
		super();
		this.asset_serial_no = asset_serial_no;
		this.report_date = report_date;
		this.report_type = report_type;
		this.entry_user = entry_user;
		this.entry_date = entry_date;
		this.modify_user = modify_user;
		this.modify_date = modify_date;
		this.verify_user = verify_user;
		this.verify_date = verify_date;
		this.status = status;
	}
	public Sale_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	
    
}
