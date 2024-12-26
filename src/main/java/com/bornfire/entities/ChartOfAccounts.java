package com.bornfire.entities;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "CHART_OF_ACCOUNTS")
public class ChartOfAccounts {

	@Id
	private String asset_serial_no; 
	private String sol_id;
	private String account_number;
	private String sol_desc;
	private String crncy_code;
	private String home_crncy_ac;
	private String glsh_code;
	private String glsh_desc;
	private String schm_code;
	private String schm_desc;
	private String prod_group;
	private String acc_type;
	private String system_tran_flg;
	private String tran_restrict_flg;
	private String tran_type;
	private String addl_det_req_flg;
	private String addl_det_type;
	private BigDecimal acc_bal;
	private String bal_type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date last_tran_date;
	private String acc_status;
	private String entry_user;
	private String modify_user;
	private String auth_user;
	private Date entry_time;
	private Date modify_time;
	private Date auth_time;
	private String del_flg;
	private String entity_flg;
	private String modify_flg;
	private String transaction_detail;
	public String getAsset_serial_no() {
		return asset_serial_no;
	}
	public void setAsset_serial_no(String asset_serial_no) {
		this.asset_serial_no = asset_serial_no;
	}
	public String getSol_id() {
		return sol_id;
	}
	public void setSol_id(String sol_id) {
		this.sol_id = sol_id;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getSol_desc() {
		return sol_desc;
	}
	public void setSol_desc(String sol_desc) {
		this.sol_desc = sol_desc;
	}
	public String getCrncy_code() {
		return crncy_code;
	}
	public void setCrncy_code(String crncy_code) {
		this.crncy_code = crncy_code;
	}
	public String getHome_crncy_ac() {
		return home_crncy_ac;
	}
	public void setHome_crncy_ac(String home_crncy_ac) {
		this.home_crncy_ac = home_crncy_ac;
	}
	public String getGlsh_code() {
		return glsh_code;
	}
	public void setGlsh_code(String glsh_code) {
		this.glsh_code = glsh_code;
	}
	public String getGlsh_desc() {
		return glsh_desc;
	}
	public void setGlsh_desc(String glsh_desc) {
		this.glsh_desc = glsh_desc;
	}
	public String getSchm_code() {
		return schm_code;
	}
	public void setSchm_code(String schm_code) {
		this.schm_code = schm_code;
	}
	public String getSchm_desc() {
		return schm_desc;
	}
	public void setSchm_desc(String schm_desc) {
		this.schm_desc = schm_desc;
	}
	public String getProd_group() {
		return prod_group;
	}
	public void setProd_group(String prod_group) {
		this.prod_group = prod_group;
	}
	public String getAcc_type() {
		return acc_type;
	}
	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}
	public String getSystem_tran_flg() {
		return system_tran_flg;
	}
	public void setSystem_tran_flg(String system_tran_flg) {
		this.system_tran_flg = system_tran_flg;
	}
	public String getTran_restrict_flg() {
		return tran_restrict_flg;
	}
	public void setTran_restrict_flg(String tran_restrict_flg) {
		this.tran_restrict_flg = tran_restrict_flg;
	}
	public String getTran_type() {
		return tran_type;
	}
	public void setTran_type(String tran_type) {
		this.tran_type = tran_type;
	}
	public String getAddl_det_req_flg() {
		return addl_det_req_flg;
	}
	public void setAddl_det_req_flg(String addl_det_req_flg) {
		this.addl_det_req_flg = addl_det_req_flg;
	}
	public String getAddl_det_type() {
		return addl_det_type;
	}
	public void setAddl_det_type(String addl_det_type) {
		this.addl_det_type = addl_det_type;
	}
	public BigDecimal getAcc_bal() {
		return acc_bal;
	}
	public void setAcc_bal(BigDecimal acc_bal) {
		this.acc_bal = acc_bal;
	}
	public String getBal_type() {
		return bal_type;
	}
	public void setBal_type(String bal_type) {
		this.bal_type = bal_type;
	}
	public Date getLast_tran_date() {
		return last_tran_date;
	}
	public void setLast_tran_date(Date last_tran_date) {
		this.last_tran_date = last_tran_date;
	}
	public String getAcc_status() {
		return acc_status;
	}
	public void setAcc_status(String acc_status) {
		this.acc_status = acc_status;
	}
	public String getEntry_user() {
		return entry_user;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public String getModify_user() {
		return modify_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public String getAuth_user() {
		return auth_user;
	}
	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}
	public Date getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public Date getAuth_time() {
		return auth_time;
	}
	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	public String getTransaction_detail() {
		return transaction_detail;
	}
	public void setTransaction_detail(String transaction_detail) {
		this.transaction_detail = transaction_detail;
	}
	public ChartOfAccounts(String asset_serial_no, String sol_id, String account_number, String sol_desc,
			String crncy_code, String home_crncy_ac, String glsh_code, String glsh_desc, String schm_code,
			String schm_desc, String prod_group, String acc_type, String system_tran_flg, String tran_restrict_flg,
			String tran_type, String addl_det_req_flg, String addl_det_type, BigDecimal acc_bal, String bal_type,
			Date last_tran_date, String acc_status, String entry_user, String modify_user, String auth_user,
			Date entry_time, Date modify_time, Date auth_time, String del_flg, String entity_flg, String modify_flg,
			String transaction_detail) {
		super();
		this.asset_serial_no = asset_serial_no;
		this.sol_id = sol_id;
		this.account_number = account_number;
		this.sol_desc = sol_desc;
		this.crncy_code = crncy_code;
		this.home_crncy_ac = home_crncy_ac;
		this.glsh_code = glsh_code;
		this.glsh_desc = glsh_desc;
		this.schm_code = schm_code;
		this.schm_desc = schm_desc;
		this.prod_group = prod_group;
		this.acc_type = acc_type;
		this.system_tran_flg = system_tran_flg;
		this.tran_restrict_flg = tran_restrict_flg;
		this.tran_type = tran_type;
		this.addl_det_req_flg = addl_det_req_flg;
		this.addl_det_type = addl_det_type;
		this.acc_bal = acc_bal;
		this.bal_type = bal_type;
		this.last_tran_date = last_tran_date;
		this.acc_status = acc_status;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.transaction_detail = transaction_detail;
	}
	public ChartOfAccounts() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
