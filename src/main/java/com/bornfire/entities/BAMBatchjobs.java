package com.bornfire.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BAM_BATCH_JOBS")
public class BAMBatchjobs {

	@Id
	private String	job_id;
	private String	frequency;
	private Date	start_date;
	private String	execution_type;
	private Date	last_run_date;
	private Date	next_due_date;
	private String	job_status;
	private String	job_description;
	private String	periodicity;
	private Date	end_date;
	private String	batch_program;
	private String	status;
	private String	remarks;
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public String getExecution_type() {
		return execution_type;
	}
	public void setExecution_type(String execution_type) {
		this.execution_type = execution_type;
	}
	public Date getLast_run_date() {
		return last_run_date;
	}
	public void setLast_run_date(Date last_run_date) {
		this.last_run_date = last_run_date;
	}
	public Date getNext_due_date() {
		return next_due_date;
	}
	public void setNext_due_date(Date next_due_date) {
		this.next_due_date = next_due_date;
	}
	public String getJob_status() {
		return job_status;
	}
	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}
	public String getJob_description() {
		return job_description;
	}
	public void setJob_description(String job_description) {
		this.job_description = job_description;
	}
	public String getPeriodicity() {
		return periodicity;
	}
	public void setPeriodicity(String periodicity) {
		this.periodicity = periodicity;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getBatch_program() {
		return batch_program;
	}
	public void setBatch_program(String batch_program) {
		this.batch_program = batch_program;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "BAMBatchjobs [job_id=" + job_id + ", frequency=" + frequency + ", execution_type=" + execution_type
				+ ", job_status=" + job_status + ", job_description=" + job_description + ", periodicity=" + periodicity
				+ ", batch_program=" + batch_program + ", status=" + status + ", remarks=" + remarks + "]";
	}
	public BAMBatchjobs(String job_id, String frequency, Date start_date, String execution_type, Date last_run_date,
			Date next_due_date, String job_status, String job_description, String periodicity, Date end_date,
			String batch_program, String status, String remarks) {
		super();
		this.job_id = job_id;
		this.frequency = frequency;
		this.start_date = start_date;
		this.execution_type = execution_type;
		this.last_run_date = last_run_date;
		this.next_due_date = next_due_date;
		this.job_status = job_status;
		this.job_description = job_description;
		this.periodicity = periodicity;
		this.end_date = end_date;
		this.batch_program = batch_program;
		this.status = status;
		this.remarks = remarks;
	}
	public BAMBatchjobs() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
