package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="BAM_SALE_AND_WRITE_OFF")
public class Bamsaleandwrite {

	@Id
	private String	asst_srl_no;
	private String	asst_name;
	private String	asst_head;
	private String	asst_category;
	private String	asst_sub_cateogry;
	private String	category_desc;
	private String	asst_type;
	private String	asst_crncy;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	date_of_purchase;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	year_of_purchase;
	private BigDecimal	org_cost;
	private BigDecimal	life_span_mth;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	asst_exp_date;
	private String	asst_rmks;
	private String	depr_flag;
	private String	depr_freq;
	private String	depr_method;
	private BigDecimal	depr_percent;
	private BigDecimal	acc_depr;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	date_of_last_depr;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	date_of_sale;
	private BigDecimal	book_value_sale_date;
	private BigDecimal	sale_value;
	private String	profit_loss;
	private String	buyer_name;
	private String	sale_det;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	date_of_scrap;
	private BigDecimal	book_value_scrap_date_amount;
	private BigDecimal	scrap_value;
	private String	scrap_rmks;
	private String	loc_type;
	private String	sol_id;
	private String	emp_id;
	private String	dept_div_name;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	@DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss a")
	private Date	entry_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss a")
	private Date	modify_time;
	private Date	verify_time;
	private String	del_flg;
	private String	entity_flg;
	private String	modify_flg;
	public String getAsst_srl_no() {
		return asst_srl_no;
	}
	public void setAsst_srl_no(String asst_srl_no) {
		this.asst_srl_no = asst_srl_no;
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
	public String getAsst_sub_cateogry() {
		return asst_sub_cateogry;
	}
	public void setAsst_sub_cateogry(String asst_sub_cateogry) {
		this.asst_sub_cateogry = asst_sub_cateogry;
	}
	public String getCategory_desc() {
		return category_desc;
	}
	public void setCategory_desc(String category_desc) {
		this.category_desc = category_desc;
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
	public String getDepr_flag() {
		return depr_flag;
	}
	public void setDepr_flag(String depr_flag) {
		this.depr_flag = depr_flag;
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
	public Date getDate_of_last_depr() {
		return date_of_last_depr;
	}
	public void setDate_of_last_depr(Date date_of_last_depr) {
		this.date_of_last_depr = date_of_last_depr;
	}
	public Date getDate_of_sale() {
		return date_of_sale;
	}
	public void setDate_of_sale(Date date_of_sale) {
		this.date_of_sale = date_of_sale;
	}
	public BigDecimal getBook_value_sale_date() {
		return book_value_sale_date;
	}
	public void setBook_value_sale_date(BigDecimal book_value_sale_date) {
		this.book_value_sale_date = book_value_sale_date;
	}
	public BigDecimal getSale_value() {
		return sale_value;
	}
	public void setSale_value(BigDecimal sale_value) {
		this.sale_value = sale_value;
	}
	public String getProfit_loss() {
		return profit_loss;
	}
	public void setProfit_loss(String profit_loss) {
		this.profit_loss = profit_loss;
	}
	public String getBuyer_name() {
		return buyer_name;
	}
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}
	public String getSale_det() {
		return sale_det;
	}
	public void setSale_det(String sale_det) {
		this.sale_det = sale_det;
	}
	public Date getDate_of_scrap() {
		return date_of_scrap;
	}
	public void setDate_of_scrap(Date date_of_scrap) {
		this.date_of_scrap = date_of_scrap;
	}
	public BigDecimal getBook_value_scrap_date_amount() {
		return book_value_scrap_date_amount;
	}
	public void setBook_value_scrap_date_amount(BigDecimal book_value_scrap_date_amount) {
		this.book_value_scrap_date_amount = book_value_scrap_date_amount;
	}
	public BigDecimal getScrap_value() {
		return scrap_value;
	}
	public void setScrap_value(BigDecimal scrap_value) {
		this.scrap_value = scrap_value;
	}
	public String getScrap_rmks() {
		return scrap_rmks;
	}
	public void setScrap_rmks(String scrap_rmks) {
		this.scrap_rmks = scrap_rmks;
	}
	public String getLoc_type() {
		return loc_type;
	}
	public void setLoc_type(String loc_type) {
		this.loc_type = loc_type;
	}
	public String getSol_id() {
		return sol_id;
	}
	public void setSol_id(String sol_id) {
		this.sol_id = sol_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getDept_div_name() {
		return dept_div_name;
	}
	public void setDept_div_name(String dept_div_name) {
		this.dept_div_name = dept_div_name;
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
	@Override
	public String toString() {
		return "Bamsaleandwrite [asst_srl_no=" + asst_srl_no + ", asst_name=" + asst_name + ", asst_head=" + asst_head
				+ ", asst_category=" + asst_category + ", asst_sub_cateogry=" + asst_sub_cateogry + ", category_desc="
				+ category_desc + ", asst_type=" + asst_type + ", asst_crncy=" + asst_crncy + ", date_of_purchase="
				+ date_of_purchase + ", year_of_purchase=" + year_of_purchase + ", org_cost=" + org_cost
				+ ", life_span_mth=" + life_span_mth + ", asst_exp_date=" + asst_exp_date + ", asst_rmks=" + asst_rmks
				+ ", depr_flag=" + depr_flag + ", depr_freq=" + depr_freq + ", depr_method=" + depr_method
				+ ", depr_percent=" + depr_percent + ", acc_depr=" + acc_depr + ", date_of_last_depr="
				+ date_of_last_depr + ", date_of_sale=" + date_of_sale + ", book_value_sale_date="
				+ book_value_sale_date + ", sale_value=" + sale_value + ", profit_loss=" + profit_loss + ", buyer_name="
				+ buyer_name + ", sale_det=" + sale_det + ", date_of_scrap=" + date_of_scrap
				+ ", book_value_scrap_date_amount=" + book_value_scrap_date_amount + ", scrap_value=" + scrap_value
				+ ", scrap_rmks=" + scrap_rmks + ", loc_type=" + loc_type + ", sol_id=" + sol_id + ", emp_id=" + emp_id
				+ ", dept_div_name=" + dept_div_name + ", entry_user=" + entry_user + ", modify_user=" + modify_user
				+ ", verify_user=" + verify_user + ", entry_time=" + entry_time + ", modify_time=" + modify_time
				+ ", verify_time=" + verify_time + ", del_flg=" + del_flg + ", entity_flg=" + entity_flg
				+ ", modify_flg=" + modify_flg + "]";
	}
	public Bamsaleandwrite(String asst_srl_no, String asst_name, String asst_head, String asst_category,
			String asst_sub_cateogry, String category_desc, String asst_type, String asst_crncy, Date date_of_purchase,
			Date year_of_purchase, BigDecimal org_cost, BigDecimal life_span_mth, Date asst_exp_date, String asst_rmks,
			String depr_flag, String depr_freq, String depr_method, BigDecimal depr_percent, BigDecimal acc_depr,
			Date date_of_last_depr, Date date_of_sale, BigDecimal book_value_sale_date, BigDecimal sale_value,
			String profit_loss, String buyer_name, String sale_det, Date date_of_scrap,
			BigDecimal book_value_scrap_date_amount, BigDecimal scrap_value, String scrap_rmks, String loc_type,
			String sol_id, String emp_id, String dept_div_name, String entry_user, String modify_user,
			String verify_user, Date entry_time, Date modify_time, Date verify_time, String del_flg, String entity_flg,
			String modify_flg) {
		super();
		this.asst_srl_no = asst_srl_no;
		this.asst_name = asst_name;
		this.asst_head = asst_head;
		this.asst_category = asst_category;
		this.asst_sub_cateogry = asst_sub_cateogry;
		this.category_desc = category_desc;
		this.asst_type = asst_type;
		this.asst_crncy = asst_crncy;
		this.date_of_purchase = date_of_purchase;
		this.year_of_purchase = year_of_purchase;
		this.org_cost = org_cost;
		this.life_span_mth = life_span_mth;
		this.asst_exp_date = asst_exp_date;
		this.asst_rmks = asst_rmks;
		this.depr_flag = depr_flag;
		this.depr_freq = depr_freq;
		this.depr_method = depr_method;
		this.depr_percent = depr_percent;
		this.acc_depr = acc_depr;
		this.date_of_last_depr = date_of_last_depr;
		this.date_of_sale = date_of_sale;
		this.book_value_sale_date = book_value_sale_date;
		this.sale_value = sale_value;
		this.profit_loss = profit_loss;
		this.buyer_name = buyer_name;
		this.sale_det = sale_det;
		this.date_of_scrap = date_of_scrap;
		this.book_value_scrap_date_amount = book_value_scrap_date_amount;
		this.scrap_value = scrap_value;
		this.scrap_rmks = scrap_rmks;
		this.loc_type = loc_type;
		this.sol_id = sol_id;
		this.emp_id = emp_id;
		this.dept_div_name = dept_div_name;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
	}
	public Bamsaleandwrite() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
}
