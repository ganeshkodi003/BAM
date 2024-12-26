package com.bornfire.entities;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "BAM_TRAN_MAS")
public class BAM_TRAN_MAS_ENTITY {


	    @Id
	    private BigDecimal  srl_no;
	    private String asset_serial_no;
	    private String asset_name;
	    private String asset_currency;
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	    private Date date_of_purchase;
	    private BigDecimal year_of_purchase;
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	    private Date asst_exp_date;
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	    private Date acquis_date;
	    private String life_span_months;
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	    private Date depreciation_start_date;
	    private String  asset_type;
	    private BigDecimal original_cost;
	    private String accum_depres;
	    private BigDecimal curr_book_value;
	    private String depreciation_flg;
	    private String depreciation_method;
	    private String depreciation_percentage;
	    private String depreciation_frequency;
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	    private Date date_of_last_depreciation;
	    private String gen_roll_no;
	    private String gen_flow_id;
	    private String gen_modify_flg;
	    private String gen_delete_flg;
	    private String process;
	    
	    private String gen_acc_no;
	    private String  gen_acc_name;
	    private String  gen_tran_perticular;
	    private BigDecimal gen_debit;
	    private BigDecimal gen_credit;
		public BigDecimal getSrl_no() {
			return srl_no;
		}
		public void setSrl_no(BigDecimal srl_no) {
			this.srl_no = srl_no;
		}
		public String getAsset_serial_no() {
			return asset_serial_no;
		}
		public void setAsset_serial_no(String asset_serial_no) {
			this.asset_serial_no = asset_serial_no;
		}
		public String getAsset_name() {
			return asset_name;
		}
		public void setAsset_name(String asset_name) {
			this.asset_name = asset_name;
		}
		public String getAsset_currency() {
			return asset_currency;
		}
		public void setAsset_currency(String asset_currency) {
			this.asset_currency = asset_currency;
		}
		public Date getDate_of_purchase() {
			return date_of_purchase;
		}
		public void setDate_of_purchase(Date date_of_purchase) {
			this.date_of_purchase = date_of_purchase;
		}
		public BigDecimal getYear_of_purchase() {
			return year_of_purchase;
		}
		public void setYear_of_purchase(BigDecimal year_of_purchase) {
			this.year_of_purchase = year_of_purchase;
		}
		public Date getAsst_exp_date() {
			return asst_exp_date;
		}
		public void setAsst_exp_date(Date asst_exp_date) {
			this.asst_exp_date = asst_exp_date;
		}
		public Date getAcquis_date() {
			return acquis_date;
		}
		public void setAcquis_date(Date acquis_date) {
			this.acquis_date = acquis_date;
		}
		public String getLife_span_months() {
			return life_span_months;
		}
		public void setLife_span_months(String life_span_months) {
			this.life_span_months = life_span_months;
		}
		public Date getDepreciation_start_date() {
			return depreciation_start_date;
		}
		public void setDepreciation_start_date(Date depreciation_start_date) {
			this.depreciation_start_date = depreciation_start_date;
		}
		public String getAsset_type() {
			return asset_type;
		}
		public void setAsset_type(String asset_type) {
			this.asset_type = asset_type;
		}
		public BigDecimal getOriginal_cost() {
			return original_cost;
		}
		public void setOriginal_cost(BigDecimal original_cost) {
			this.original_cost = original_cost;
		}
		public String getAccum_depres() {
			return accum_depres;
		}
		public void setAccum_depres(String accum_depres) {
			this.accum_depres = accum_depres;
		}
		public BigDecimal getCurr_book_value() {
			return curr_book_value;
		}
		public void setCurr_book_value(BigDecimal curr_book_value) {
			this.curr_book_value = curr_book_value;
		}
		public String getDepreciation_flg() {
			return depreciation_flg;
		}
		public void setDepreciation_flg(String depreciation_flg) {
			this.depreciation_flg = depreciation_flg;
		}
		public String getDepreciation_method() {
			return depreciation_method;
		}
		public void setDepreciation_method(String depreciation_method) {
			this.depreciation_method = depreciation_method;
		}
		public String getDepreciation_percentage() {
			return depreciation_percentage;
		}
		public void setDepreciation_percentage(String depreciation_percentage) {
			this.depreciation_percentage = depreciation_percentage;
		}
		public String getDepreciation_frequency() {
			return depreciation_frequency;
		}
		public void setDepreciation_frequency(String depreciation_frequency) {
			this.depreciation_frequency = depreciation_frequency;
		}
		public Date getDate_of_last_depreciation() {
			return date_of_last_depreciation;
		}
		public void setDate_of_last_depreciation(Date date_of_last_depreciation) {
			this.date_of_last_depreciation = date_of_last_depreciation;
		}
		public String getGen_roll_no() {
			return gen_roll_no;
		}
		public void setGen_roll_no(String gen_roll_no) {
			this.gen_roll_no = gen_roll_no;
		}
		public String getGen_flow_id() {
			return gen_flow_id;
		}
		public void setGen_flow_id(String gen_flow_id) {
			this.gen_flow_id = gen_flow_id;
		}
		public String getGen_modify_flg() {
			return gen_modify_flg;
		}
		public void setGen_modify_flg(String gen_modify_flg) {
			this.gen_modify_flg = gen_modify_flg;
		}
		public String getGen_delete_flg() {
			return gen_delete_flg;
		}
		public void setGen_delete_flg(String gen_delete_flg) {
			this.gen_delete_flg = gen_delete_flg;
		}
		public String getProcess() {
			return process;
		}
		public void setProcess(String process) {
			this.process = process;
		}
		public String getGen_acc_no() {
			return gen_acc_no;
		}
		public void setGen_acc_no(String gen_acc_no) {
			this.gen_acc_no = gen_acc_no;
		}
		public String getGen_acc_name() {
			return gen_acc_name;
		}
		public void setGen_acc_name(String gen_acc_name) {
			this.gen_acc_name = gen_acc_name;
		}
		public String getGen_tran_perticular() {
			return gen_tran_perticular;
		}
		public void setGen_tran_perticular(String gen_tran_perticular) {
			this.gen_tran_perticular = gen_tran_perticular;
		}
		public BigDecimal getGen_debit() {
			return gen_debit;
		}
		public void setGen_debit(BigDecimal gen_debit) {
			this.gen_debit = gen_debit;
		}
		public BigDecimal getGen_credit() {
			return gen_credit;
		}
		public void setGen_credit(BigDecimal gen_credit) {
			this.gen_credit = gen_credit;
		}
		public BAM_TRAN_MAS_ENTITY(BigDecimal srl_no, String asset_serial_no, String asset_name, String asset_currency,
				Date date_of_purchase, BigDecimal year_of_purchase, Date asst_exp_date, Date acquis_date,
				String life_span_months, Date depreciation_start_date, String asset_type, BigDecimal original_cost,
				String accum_depres, BigDecimal curr_book_value, String depreciation_flg, String depreciation_method,
				String depreciation_percentage, String depreciation_frequency, Date date_of_last_depreciation,
				String gen_roll_no, String gen_flow_id, String gen_modify_flg, String gen_delete_flg,
				String process, String gen_acc_no, String gen_acc_name, String gen_tran_perticular,
				BigDecimal gen_debit, BigDecimal gen_credit) {
			super();
			this.srl_no = srl_no;
			this.asset_serial_no = asset_serial_no;
			this.asset_name = asset_name;
			this.asset_currency = asset_currency;
			this.date_of_purchase = date_of_purchase;
			this.year_of_purchase = year_of_purchase;
			this.asst_exp_date = asst_exp_date;
			this.acquis_date = acquis_date;
			this.life_span_months = life_span_months;
			this.depreciation_start_date = depreciation_start_date;
			this.asset_type = asset_type;
			this.original_cost = original_cost;
			this.accum_depres = accum_depres;
			this.curr_book_value = curr_book_value;
			this.depreciation_flg = depreciation_flg;
			this.depreciation_method = depreciation_method;
			this.depreciation_percentage = depreciation_percentage;
			this.depreciation_frequency = depreciation_frequency;
			this.date_of_last_depreciation = date_of_last_depreciation;
			this.gen_roll_no = gen_roll_no;
			this.gen_flow_id = gen_flow_id;
			this.gen_modify_flg = gen_modify_flg;
			this.gen_delete_flg = gen_delete_flg;
			this.process = process;
			this.gen_acc_no = gen_acc_no; 
			this.gen_acc_name = gen_acc_name;
			this.gen_tran_perticular = gen_tran_perticular;
			this.gen_debit = gen_debit;
			this.gen_credit = gen_credit;
		}
		public BAM_TRAN_MAS_ENTITY() {
			super();
			// TODO Auto-generated constructor stub
		}


}
