package com.bornfire.entities;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "AUDIT_MASTER")
public class Audit_Master {

    @Id
    private BigDecimal audit_srl_num; 
    private Date audit_date; 
    private String audit_user_id; 
    private String audit_user_name;
    private String action; 
    private String remarks; 
    private String table_name;
    private Date db_date;
    private String channel_id;
    private String entry_user;
    private String modify_user;
    private String auth_user;
    private Date entry_time;
    private Date modify_time;
    private Date auth_time;
    private String del_flg;
    private String entity_flg;
    private String modify_flg;
	public BigDecimal getAudit_srl_num() {
		return audit_srl_num;
	}
	public void setAudit_srl_num(BigDecimal audit_srl_num) {
		this.audit_srl_num = audit_srl_num;
	}
	public Date getAudit_date() {
		return audit_date;
	}
	public void setAudit_date(Date audit_date) {
		this.audit_date = audit_date;
	}
	public String getAudit_user_id() {
		return audit_user_id;
	}
	public void setAudit_user_id(String audit_user_id) {
		this.audit_user_id = audit_user_id;
	}
	public String getAudit_user_name() {
		return audit_user_name;
	}
	public void setAudit_user_name(String audit_user_name) {
		this.audit_user_name = audit_user_name;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public Date getDb_date() {
		return db_date;
	}
	public void setDb_date(Date db_date) {
		this.db_date = db_date;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
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
	public Audit_Master(BigDecimal audit_srl_num, Date audit_date, String audit_user_id, String audit_user_name,
			String action, String remarks, String table_name, Date db_date, String channel_id, String entry_user,
			String modify_user, String auth_user, Date entry_time, Date modify_time, Date auth_time, String del_flg,
			String entity_flg, String modify_flg) {
		super();
		this.audit_srl_num = audit_srl_num;
		this.audit_date = audit_date;
		this.audit_user_id = audit_user_id;
		this.audit_user_name = audit_user_name;
		this.action = action;
		this.remarks = remarks;
		this.table_name = table_name;
		this.db_date = db_date;
		this.channel_id = channel_id;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
	}
	public Audit_Master() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
    
    
}
