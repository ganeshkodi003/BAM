package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSFER_REPORT")
public class TransferEntity {
	@Id
	private BigDecimal	transfer_id;
	private String	from_location_type;
	private String	from_sol_id;
	private BigDecimal	from_employee_id;
	private String	from_location_address;
	private Date	from_date;
	private String	to_location_type;
	private String	to_sol_id;
	private BigDecimal	to_employee_id;
	private String	to_location_address;
	private Date	to_date;
	private String	report_type;
	private Date	report_date;
	public BigDecimal getTransfer_id() {
		return transfer_id;
	}
	public void setTransfer_id(BigDecimal transfer_id) {
		this.transfer_id = transfer_id;
	}
	public String getFrom_location_type() {
		return from_location_type;
	}
	public void setFrom_location_type(String from_location_type) {
		this.from_location_type = from_location_type;
	}
	public String getFrom_sol_id() {
		return from_sol_id;
	}
	public void setFrom_sol_id(String from_sol_id) {
		this.from_sol_id = from_sol_id;
	}
	public BigDecimal getFrom_employee_id() {
		return from_employee_id;
	}
	public void setFrom_employee_id(BigDecimal from_employee_id) {
		this.from_employee_id = from_employee_id;
	}
	public String getFrom_location_address() {
		return from_location_address;
	}
	public void setFrom_location_address(String from_location_address) {
		this.from_location_address = from_location_address;
	}
	public Date getFrom_date() {
		return from_date;
	}
	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}
	public String getTo_location_type() {
		return to_location_type;
	}
	public void setTo_location_type(String to_location_type) {
		this.to_location_type = to_location_type;
	}
	public String getTo_sol_id() {
		return to_sol_id;
	}
	public void setTo_sol_id(String to_sol_id) {
		this.to_sol_id = to_sol_id;
	}
	public BigDecimal getTo_employee_id() {
		return to_employee_id;
	}
	public void setTo_employee_id(BigDecimal to_employee_id) {
		this.to_employee_id = to_employee_id;
	}
	public String getTo_location_address() {
		return to_location_address;
	}
	public void setTo_location_address(String to_location_address) {
		this.to_location_address = to_location_address;
	}
	public Date getTo_date() {
		return to_date;
	}
	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}
	public String getReport_type() {
		return report_type;
	}
	public void setReport_type(String report_type) {
		this.report_type = report_type;
	}
	public Date getReport_date() {
		return report_date;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	public TransferEntity(BigDecimal transfer_id, String from_location_type, String from_sol_id,
			BigDecimal from_employee_id, String from_location_address, Date from_date, String to_location_type,
			String to_sol_id, BigDecimal to_employee_id, String to_location_address, Date to_date, String report_type,
			Date report_date) {
		super();
		this.transfer_id = transfer_id;
		this.from_location_type = from_location_type;
		this.from_sol_id = from_sol_id;
		this.from_employee_id = from_employee_id;
		this.from_location_address = from_location_address;
		this.from_date = from_date;
		this.to_location_type = to_location_type;
		this.to_sol_id = to_sol_id;
		this.to_employee_id = to_employee_id;
		this.to_location_address = to_location_address;
		this.to_date = to_date;
		this.report_type = report_type;
		this.report_date = report_date;
	}
	public TransferEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
