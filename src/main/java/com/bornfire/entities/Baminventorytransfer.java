package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="BAM_INVENTORY_TRANSAFER")
public class Baminventorytransfer {
	@Id
	private String	asst_srl_npo;
	private BigDecimal	srl_no;
	private BigDecimal	asst_xfr_ref_no;
	private String	asst_name;
	private String	asst_head;
	private String	asst_category;
	private String	asst_sub_category;
	private String	asst_type;
	private String	asst_crncy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Date	date_of_purchase;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	year_of_purchase;
	private BigDecimal	org_cost;
	private BigDecimal	life_span_mth;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	asst_exp_date;
	private String	asst_rmks;
	private String	vendor_name;
	private String	purchase_det;
	private String	depr_flg;
	private String	depr_freq;
	private String	depr_method;
	private BigDecimal	depr_percent;
	private BigDecimal	acc_depr;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	dat_of_last_depr;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	date_of_acqn;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	date_of_tfr;
	private BigDecimal	cur_book_value_xfr_date;
	private BigDecimal	market_value;
	private String	from_category;
	private String	from_loc_type;
	private String	from_sol_id;
	private String	from_emp_id;
	private String	from_dept_div_name;
	private String	from_loc_addr;
	private String	from_loc_rmks;
	private String	to_category;
	private String	to_loc_type;
	private String	to_sol_id;
	private String	to_emp_id;
	private String	to_dept_div_name;
	private String	to_loc_addr;
	private String	to_loc_rmks; 
	private String	transaction_detail;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date	entry_time;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date	modify_time;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date	verify_time;
	private String	del_flg;
	private String	entity_flg;
	private String	modify_flg;
	private String	sale_flg;
	private String	writeoff_flg;
	public String getAsst_srl_npo() {
		return asst_srl_npo;
	}
	public void setAsst_srl_npo(String asst_srl_npo) {
		this.asst_srl_npo = asst_srl_npo;
	}
	public BigDecimal getSrl_no() {
		return srl_no;
	}
	public void setSrl_no(BigDecimal srl_no) {
		this.srl_no = srl_no;
	}
	public BigDecimal getAsst_xfr_ref_no() {
		return asst_xfr_ref_no;
	}
	public void setAsst_xfr_ref_no(BigDecimal asst_xfr_ref_no) {
		this.asst_xfr_ref_no = asst_xfr_ref_no;
	}
	public String getAsst_name() {
		return asst_name;
	}
	public void setAsst_name(String asst_name) {
		this.asst_name = asst_name;
	}
	public String getAsst_head() {
		return asst_head;
	}
	public void setAsst_head(String asst_head) {
		this.asst_head = asst_head;
	}
	public String getAsst_category() {
		return asst_category;
	}
	public void setAsst_category(String asst_category) {
		this.asst_category = asst_category;
	}
	public String getAsst_sub_category() {
		return asst_sub_category;
	}
	public void setAsst_sub_category(String asst_sub_category) {
		this.asst_sub_category = asst_sub_category;
	}
	public String getAsst_type() {
		return asst_type;
	}
	public void setAsst_type(String asst_type) {
		this.asst_type = asst_type;
	}
	public String getAsst_crncy() {
		return asst_crncy;
	}
	public void setAsst_crncy(String asst_crncy) {
		this.asst_crncy = asst_crncy;
	}
	public Date getDate_of_purchase() {
		return date_of_purchase;
	}
	public void setDate_of_purchase(Date date_of_purchase) {
		this.date_of_purchase = date_of_purchase;
	}
	public Date getYear_of_purchase() {
		return year_of_purchase;
	}
	public void setYear_of_purchase(Date year_of_purchase) {
		this.year_of_purchase = year_of_purchase;
	}
	public BigDecimal getOrg_cost() {
		return org_cost;
	}
	public void setOrg_cost(BigDecimal org_cost) {
		this.org_cost = org_cost;
	}
	public BigDecimal getLife_span_mth() {
		return life_span_mth;
	}
	public void setLife_span_mth(BigDecimal life_span_mth) {
		this.life_span_mth = life_span_mth;
	}
	public Date getAsst_exp_date() {
		return asst_exp_date;
	}
	public void setAsst_exp_date(Date asst_exp_date) {
		this.asst_exp_date = asst_exp_date;
	}
	public String getAsst_rmks() {
		return asst_rmks;
	}
	public void setAsst_rmks(String asst_rmks) {
		this.asst_rmks = asst_rmks;
	}
	public String getVendor_name() {
		return vendor_name;
	}
	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}
	public String getPurchase_det() {
		return purchase_det;
	}
	public void setPurchase_det(String purchase_det) {
		this.purchase_det = purchase_det;
	}
	public String getDepr_flg() {
		return depr_flg;
	}
	public void setDepr_flg(String depr_flg) {
		this.depr_flg = depr_flg;
	}
	public String getDepr_freq() {
		return depr_freq;
	}
	public void setDepr_freq(String depr_freq) {
		this.depr_freq = depr_freq;
	}
	public String getDepr_method() {
		return depr_method;
	}
	public void setDepr_method(String depr_method) {
		this.depr_method = depr_method;
	}
	public BigDecimal getDepr_percent() {
		return depr_percent;
	}
	public void setDepr_percent(BigDecimal depr_percent) {
		this.depr_percent = depr_percent;
	}
	public BigDecimal getAcc_depr() {
		return acc_depr;
	}
	public void setAcc_depr(BigDecimal acc_depr) {
		this.acc_depr = acc_depr;
	}
	public Date getDat_of_last_depr() {
		return dat_of_last_depr;
	}
	public void setDat_of_last_depr(Date dat_of_last_depr) {
		this.dat_of_last_depr = dat_of_last_depr;
	}
	public Date getDate_of_acqn() {
		return date_of_acqn;
	}
	public void setDate_of_acqn(Date date_of_acqn) {
		this.date_of_acqn = date_of_acqn;
	}
	public Date getDate_of_tfr() {
		return date_of_tfr;
	}
	public void setDate_of_tfr(Date date_of_tfr) {
		this.date_of_tfr = date_of_tfr;
	}
	public BigDecimal getCur_book_value_xfr_date() {
		return cur_book_value_xfr_date;
	}
	public void setCur_book_value_xfr_date(BigDecimal cur_book_value_xfr_date) {
		this.cur_book_value_xfr_date = cur_book_value_xfr_date;
	}
	public BigDecimal getMarket_value() {
		return market_value;
	}
	public void setMarket_value(BigDecimal market_value) {
		this.market_value = market_value;
	}
	public String getFrom_category() {
		return from_category;
	}
	public void setFrom_category(String from_category) {
		this.from_category = from_category;
	}
	public String getFrom_loc_type() {
		return from_loc_type;
	}
	public void setFrom_loc_type(String from_loc_type) {
		this.from_loc_type = from_loc_type;
	}
	public String getFrom_sol_id() {
		return from_sol_id;
	}
	public void setFrom_sol_id(String from_sol_id) {
		this.from_sol_id = from_sol_id;
	}
	public String getFrom_emp_id() {
		return from_emp_id;
	}
	public void setFrom_emp_id(String from_emp_id) {
		this.from_emp_id = from_emp_id;
	}
	public String getFrom_dept_div_name() {
		return from_dept_div_name;
	}
	public void setFrom_dept_div_name(String from_dept_div_name) {
		this.from_dept_div_name = from_dept_div_name;
	}
	public String getFrom_loc_addr() {
		return from_loc_addr;
	}
	public void setFrom_loc_addr(String from_loc_addr) {
		this.from_loc_addr = from_loc_addr;
	}
	public String getFrom_loc_rmks() {
		return from_loc_rmks;
	}
	public void setFrom_loc_rmks(String from_loc_rmks) {
		this.from_loc_rmks = from_loc_rmks;
	}
	public String getTo_category() {
		return to_category;
	}
	public void setTo_category(String to_category) {
		this.to_category = to_category;
	}
	public String getTo_loc_type() {
		return to_loc_type;
	}
	public void setTo_loc_type(String to_loc_type) {
		this.to_loc_type = to_loc_type;
	}
	public String getTo_sol_id() {
		return to_sol_id;
	}
	public void setTo_sol_id(String to_sol_id) {
		this.to_sol_id = to_sol_id;
	}
	public String getTo_emp_id() {
		return to_emp_id;
	}
	public void setTo_emp_id(String to_emp_id) {
		this.to_emp_id = to_emp_id;
	}
	public String getTo_dept_div_name() {
		return to_dept_div_name;
	}
	public void setTo_dept_div_name(String to_dept_div_name) {
		this.to_dept_div_name = to_dept_div_name;
	}
	public String getTo_loc_addr() {
		return to_loc_addr;
	}
	public void setTo_loc_addr(String to_loc_addr) {
		this.to_loc_addr = to_loc_addr;
	}
	public String getTo_loc_rmks() {
		return to_loc_rmks;
	}
	public void setTo_loc_rmks(String to_loc_rmks) {
		this.to_loc_rmks = to_loc_rmks;
	}
	public String getTransaction_detail() {
		return transaction_detail;
	}
	public void setTransaction_detail(String transaction_detail) {
		this.transaction_detail = transaction_detail;
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
	public String getVerify_user() {
		return verify_user;
	}
	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
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
	public Date getVerify_time() {
		return verify_time;
	}
	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
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
	public String getSale_flg() {
		return sale_flg;
	}
	public void setSale_flg(String sale_flg) {
		this.sale_flg = sale_flg;
	}
	public String getWriteoff_flg() {
		return writeoff_flg;
	}
	public void setWriteoff_flg(String writeoff_flg) {
		this.writeoff_flg = writeoff_flg;
	}
	public Baminventorytransfer(String asst_srl_npo, BigDecimal srl_no, BigDecimal asst_xfr_ref_no, String asst_name,
			String asst_head, String asst_category, String asst_sub_category, String asst_type, String asst_crncy,
			Date date_of_purchase, Date year_of_purchase, BigDecimal org_cost, BigDecimal life_span_mth,
			Date asst_exp_date, String asst_rmks, String vendor_name, String purchase_det, String depr_flg,
			String depr_freq, String depr_method, BigDecimal depr_percent, BigDecimal acc_depr, Date dat_of_last_depr,
			Date date_of_acqn, Date date_of_tfr, BigDecimal cur_book_value_xfr_date, BigDecimal market_value,
			String from_category, String from_loc_type, String from_sol_id, String from_emp_id,
			String from_dept_div_name, String from_loc_addr, String from_loc_rmks, String to_category,
			String to_loc_type, String to_sol_id, String to_emp_id, String to_dept_div_name, String to_loc_addr,
			String to_loc_rmks, String transaction_detail, String entry_user, String modify_user, String verify_user,
			Date entry_time, Date modify_time, Date verify_time, String del_flg, String entity_flg, String modify_flg,
			String sale_flg, String writeoff_flg) {
		super();
		this.asst_srl_npo = asst_srl_npo;
		this.srl_no = srl_no;
		this.asst_xfr_ref_no = asst_xfr_ref_no;
		this.asst_name = asst_name;
		this.asst_head = asst_head;
		this.asst_category = asst_category;
		this.asst_sub_category = asst_sub_category;
		this.asst_type = asst_type;
		this.asst_crncy = asst_crncy;
		this.date_of_purchase = date_of_purchase;
		this.year_of_purchase = year_of_purchase;
		this.org_cost = org_cost;
		this.life_span_mth = life_span_mth;
		this.asst_exp_date = asst_exp_date;
		this.asst_rmks = asst_rmks;
		this.vendor_name = vendor_name;
		this.purchase_det = purchase_det;
		this.depr_flg = depr_flg;
		this.depr_freq = depr_freq;
		this.depr_method = depr_method;
		this.depr_percent = depr_percent;
		this.acc_depr = acc_depr;
		this.dat_of_last_depr = dat_of_last_depr;
		this.date_of_acqn = date_of_acqn;
		this.date_of_tfr = date_of_tfr;
		this.cur_book_value_xfr_date = cur_book_value_xfr_date;
		this.market_value = market_value;
		this.from_category = from_category;
		this.from_loc_type = from_loc_type;
		this.from_sol_id = from_sol_id;
		this.from_emp_id = from_emp_id;
		this.from_dept_div_name = from_dept_div_name;
		this.from_loc_addr = from_loc_addr;
		this.from_loc_rmks = from_loc_rmks;
		this.to_category = to_category;
		this.to_loc_type = to_loc_type;
		this.to_sol_id = to_sol_id;
		this.to_emp_id = to_emp_id;
		this.to_dept_div_name = to_dept_div_name;
		this.to_loc_addr = to_loc_addr;
		this.to_loc_rmks = to_loc_rmks;
		this.transaction_detail = transaction_detail;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.sale_flg = sale_flg;
		this.writeoff_flg = writeoff_flg;
	}
	public Baminventorytransfer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
}
