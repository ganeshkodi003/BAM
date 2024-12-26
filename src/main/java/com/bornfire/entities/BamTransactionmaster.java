package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BAM_TRANSACTION_MASTER")
public class BamTransactionmaster {
	
	private String	asst_trans;
	private BigDecimal	ref_no;
	@Id
	private String	tran_id;
	private String	part_tran_srl_no;
	private String	asst_srl_no;
	private String	asst_desc;
	private String asst_ccy;
	private String	sol_id;
	private String	asst_acct_no;
	private String	depr_acct_no;
	private String	depr_pnl_acct_no;
	private String	sun_dr_acct_no;
	private String	sun_cr_acct_no;
	private BigDecimal	org_cost;
	private BigDecimal	acc_depr_amt;
	private BigDecimal	depr_amt;
	private BigDecimal	sale_value;
	private Date	fin_tran_date;
	private String	fin_tran_id;
	private String	fin_part_tran_srl_no;
	private String	fin_part_tran_type;
	private BigDecimal	fin_tran_amt;
	private String	fin_tran_particular;
	private String	tran_channel;
	private Date Date_of_purchase;
	private BigDecimal Current_book_value;
	private String Year_of_purchase;
	private String dep_flg;
	private Date Acct_exp_date;
	private String Depreciation_method;
	private Date Date_of_acquisition;
	private String depriciation_per;
	private String life_span_months;
	private String depriciation_freq;
	private Date depriciation_start_date;
	private Date date_of_last_dep;
	private String	entry_user;
	private String	modify_user;
	private String	auth_user;
	private Date	entry_time;
	private Date	modify_time;
	private Date	auth_time;
	private String	del_flg;
	private String	pstd_flg;
	public String getAsst_trans() {
		return asst_trans;
	}
	public void setAsst_trans(String asst_trans) {
		this.asst_trans = asst_trans;
	}
	public BigDecimal getRef_no() {
		return ref_no;
	}
	public void setRef_no(BigDecimal ref_no) {
		this.ref_no = ref_no;
	}
	public String getTran_id() {
		return tran_id;
	}
	public void setTran_id(String tran_id) {
		this.tran_id = tran_id;
	}
	public String getPart_tran_srl_no() {
		return part_tran_srl_no;
	}
	public void setPart_tran_srl_no(String part_tran_srl_no) {
		this.part_tran_srl_no = part_tran_srl_no;
	}
	public String getAsst_srl_no() {
		return asst_srl_no;
	}
	public void setAsst_srl_no(String asst_srl_no) {
		this.asst_srl_no = asst_srl_no;
	}
	public String getAsst_desc() {
		return asst_desc;
	}
	public void setAsst_desc(String asst_desc) {
		this.asst_desc = asst_desc;
	}
	public String getSol_id() {
		return sol_id;
	}
	public void setSol_id(String sol_id) {
		this.sol_id = sol_id;
	}
	public String getAsst_acct_no() {
		return asst_acct_no;
	}
	public void setAsst_acct_no(String asst_acct_no) {
		this.asst_acct_no = asst_acct_no;
	}
	public String getDepr_acct_no() {
		return depr_acct_no;
	}
	public void setDepr_acct_no(String depr_acct_no) {
		this.depr_acct_no = depr_acct_no;
	}
	public String getDepr_pnl_acct_no() {
		return depr_pnl_acct_no;
	}
	public void setDepr_pnl_acct_no(String depr_pnl_acct_no) {
		this.depr_pnl_acct_no = depr_pnl_acct_no;
	}
	public String getSun_dr_acct_no() {
		return sun_dr_acct_no;
	}
	public void setSun_dr_acct_no(String sun_dr_acct_no) {
		this.sun_dr_acct_no = sun_dr_acct_no;
	}
	public String getSun_cr_acct_no() {
		return sun_cr_acct_no;
	}
	public void setSun_cr_acct_no(String sun_cr_acct_no) {
		this.sun_cr_acct_no = sun_cr_acct_no;
	}
	public BigDecimal getOrg_cost() {
		return org_cost;
	}
	public void setOrg_cost(BigDecimal org_cost) {
		this.org_cost = org_cost;
	}
	public BigDecimal getAcc_depr_amt() {
		return acc_depr_amt;
	}
	public void setAcc_depr_amt(BigDecimal acc_depr_amt) {
		this.acc_depr_amt = acc_depr_amt;
	}
	public BigDecimal getDepr_amt() {
		return depr_amt;
	}
	public void setDepr_amt(BigDecimal depr_amt) {
		this.depr_amt = depr_amt;
	}
	public BigDecimal getSale_value() {
		return sale_value;
	}
	public void setSale_value(BigDecimal sale_value) {
		this.sale_value = sale_value;
	}
	public Date getFin_tran_date() {
		return fin_tran_date;
	}
	public void setFin_tran_date(Date fin_tran_date) {
		this.fin_tran_date = fin_tran_date;
	}
	public String getFin_tran_id() {
		return fin_tran_id;
	}
	public void setFin_tran_id(String fin_tran_id) {
		this.fin_tran_id = fin_tran_id;
	}
	public String getFin_part_tran_srl_no() {
		return fin_part_tran_srl_no;
	}
	public void setFin_part_tran_srl_no(String fin_part_tran_srl_no) {
		this.fin_part_tran_srl_no = fin_part_tran_srl_no;
	}
	public String getFin_part_tran_type() {
		return fin_part_tran_type;
	}
	public void setFin_part_tran_type(String fin_part_tran_type) {
		this.fin_part_tran_type = fin_part_tran_type;
	}
	public BigDecimal getFin_tran_amt() {
		return fin_tran_amt;
	}
	public void setFin_tran_amt(BigDecimal fin_tran_amt) {
		this.fin_tran_amt = fin_tran_amt;
	}
	public String getFin_tran_particular() {
		return fin_tran_particular;
	}
	public void setFin_tran_particular(String fin_tran_particular) {
		this.fin_tran_particular = fin_tran_particular;
	}
	public String getTran_channel() {
		return tran_channel;
	}
	public void setTran_channel(String tran_channel) {
		this.tran_channel = tran_channel;
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
	public String getPstd_flg() {
		return pstd_flg;
	}
	public void setPstd_flg(String pstd_flg) {
		this.pstd_flg = pstd_flg;
	}
	public String getAsst_ccy() {
		return asst_ccy;
	}
	public void setAsst_ccy(String asst_ccy) {
		this.asst_ccy = asst_ccy;
	}
	public Date getDate_of_purchase() {
		return Date_of_purchase;
	}
	public void setDate_of_purchase(Date date_of_purchase) {
		Date_of_purchase = date_of_purchase;
	}
	public BigDecimal getCurrent_book_value() {
		return Current_book_value;
	}
	public void setCurrent_book_value(BigDecimal current_book_value) {
		Current_book_value = current_book_value;
	}
	public String getYear_of_purchase() {
		return Year_of_purchase;
	}
	public void setYear_of_purchase(String year_of_purchase) {
		Year_of_purchase = year_of_purchase;
	}
	public String getDep_flg() {
		return dep_flg;
	}
	public void setDep_flg(String dep_flg) {
		this.dep_flg = dep_flg;
	}
	public Date getAcct_exp_date() {
		return Acct_exp_date;
	}
	public void setAcct_exp_date(Date acct_exp_date) {
		Acct_exp_date = acct_exp_date;
	}
	public String getDepreciation_method() {
		return Depreciation_method;
	}
	public void setDepreciation_method(String depreciation_method) {
		Depreciation_method = depreciation_method;
	}
	public Date getDate_of_acquisition() {
		return Date_of_acquisition;
	}
	public void setDate_of_acquisition(Date date_of_acquisition) {
		Date_of_acquisition = date_of_acquisition;
	}
	public String getDepriciation_per() {
		return depriciation_per;
	}
	public void setDepriciation_per(String depriciation_per) {
		this.depriciation_per = depriciation_per;
	}
	public String getLife_span_months() {
		return life_span_months;
	}
	public void setLife_span_months(String life_span_months) {
		this.life_span_months = life_span_months;
	}
	public String getDepriciation_freq() {
		return depriciation_freq;
	}
	public void setDepriciation_freq(String depriciation_freq) {
		this.depriciation_freq = depriciation_freq;
	}
	public Date getDepriciation_start_date() {
		return depriciation_start_date;
	}
	public void setDepriciation_start_date(Date depriciation_start_date) {
		this.depriciation_start_date = depriciation_start_date;
	}
	public Date getDate_of_last_dep() {
		return date_of_last_dep;
	}
	public void setDate_of_last_dep(Date date_of_last_dep) {
		this.date_of_last_dep = date_of_last_dep;
	}
	@Override
	public String toString() {
		return "BamTransactionmaster [asst_trans=" + asst_trans + ", ref_no=" + ref_no + ", tran_id=" + tran_id
				+ ", part_tran_srl_no=" + part_tran_srl_no + ", asst_srl_no=" + asst_srl_no + ", asst_desc=" + asst_desc
				+ ", asst_ccy=" + asst_ccy + ", sol_id=" + sol_id + ", asst_acct_no=" + asst_acct_no + ", depr_acct_no="
				+ depr_acct_no + ", depr_pnl_acct_no=" + depr_pnl_acct_no + ", sun_dr_acct_no=" + sun_dr_acct_no
				+ ", sun_cr_acct_no=" + sun_cr_acct_no + ", org_cost=" + org_cost + ", acc_depr_amt=" + acc_depr_amt
				+ ", depr_amt=" + depr_amt + ", sale_value=" + sale_value + ", fin_tran_date=" + fin_tran_date
				+ ", fin_tran_id=" + fin_tran_id + ", fin_part_tran_srl_no=" + fin_part_tran_srl_no
				+ ", fin_part_tran_type=" + fin_part_tran_type + ", fin_tran_amt=" + fin_tran_amt
				+ ", fin_tran_particular=" + fin_tran_particular + ", tran_channel=" + tran_channel
				+ ", Date_of_purchase=" + Date_of_purchase + ", Current_book_value=" + Current_book_value
				+ ", Year_of_purchase=" + Year_of_purchase + ", dep_flg=" + dep_flg + ", Acct_exp_date=" + Acct_exp_date
				+ ", Depreciation_method=" + Depreciation_method + ", Date_of_acquisition=" + Date_of_acquisition
				+ ", depriciation_per=" + depriciation_per + ", life_span_months=" + life_span_months
				+ ", depriciation_freq=" + depriciation_freq + ", depriciation_start_date=" + depriciation_start_date
				+ ", date_of_last_dep=" + date_of_last_dep + ", entry_user=" + entry_user + ", modify_user="
				+ modify_user + ", auth_user=" + auth_user + ", entry_time=" + entry_time + ", modify_time="
				+ modify_time + ", auth_time=" + auth_time + ", del_flg=" + del_flg + ", pstd_flg=" + pstd_flg + "]";
	}
	public BamTransactionmaster(String asst_trans, BigDecimal ref_no, String tran_id, String part_tran_srl_no,
			String asst_srl_no, String asst_desc, String asst_ccy, String sol_id, String asst_acct_no,
			String depr_acct_no, String depr_pnl_acct_no, String sun_dr_acct_no, String sun_cr_acct_no,
			BigDecimal org_cost, BigDecimal acc_depr_amt, BigDecimal depr_amt, BigDecimal sale_value,
			Date fin_tran_date, String fin_tran_id, String fin_part_tran_srl_no, String fin_part_tran_type,
			BigDecimal fin_tran_amt, String fin_tran_particular, String tran_channel, Date date_of_purchase,
			BigDecimal current_book_value, String year_of_purchase, String dep_flg, Date acct_exp_date,
			String depreciation_method, Date date_of_acquisition, String depriciation_per, String life_span_months,
			String depriciation_freq, Date depriciation_start_date, Date date_of_last_dep, String entry_user,
			String modify_user, String auth_user, Date entry_time, Date modify_time, Date auth_time, String del_flg,
			String pstd_flg) {
		super();
		this.asst_trans = asst_trans;
		this.ref_no = ref_no;
		this.tran_id = tran_id;
		this.part_tran_srl_no = part_tran_srl_no;
		this.asst_srl_no = asst_srl_no;
		this.asst_desc = asst_desc;
		this.asst_ccy = asst_ccy;
		this.sol_id = sol_id;
		this.asst_acct_no = asst_acct_no;
		this.depr_acct_no = depr_acct_no;
		this.depr_pnl_acct_no = depr_pnl_acct_no;
		this.sun_dr_acct_no = sun_dr_acct_no;
		this.sun_cr_acct_no = sun_cr_acct_no;
		this.org_cost = org_cost;
		this.acc_depr_amt = acc_depr_amt;
		this.depr_amt = depr_amt;
		this.sale_value = sale_value;
		this.fin_tran_date = fin_tran_date;
		this.fin_tran_id = fin_tran_id;
		this.fin_part_tran_srl_no = fin_part_tran_srl_no;
		this.fin_part_tran_type = fin_part_tran_type;
		this.fin_tran_amt = fin_tran_amt;
		this.fin_tran_particular = fin_tran_particular;
		this.tran_channel = tran_channel;
		Date_of_purchase = date_of_purchase;
		Current_book_value = current_book_value;
		Year_of_purchase = year_of_purchase;
		this.dep_flg = dep_flg;
		Acct_exp_date = acct_exp_date;
		Depreciation_method = depreciation_method;
		Date_of_acquisition = date_of_acquisition;
		this.depriciation_per = depriciation_per;
		this.life_span_months = life_span_months;
		this.depriciation_freq = depriciation_freq;
		this.depriciation_start_date = depriciation_start_date;
		this.date_of_last_dep = date_of_last_dep;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.del_flg = del_flg;
		this.pstd_flg = pstd_flg;
	}
	public BamTransactionmaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	


}
