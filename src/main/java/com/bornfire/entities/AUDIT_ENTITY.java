package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "AUD_TABLE")
public class AUDIT_ENTITY {
	
	@Id
	@Column(name = "AUDIT_ID", nullable = false)
    private BigDecimal audit_Id;  // Using BigDecimal for NUMBER type

    @Column(name = "ASSET_SERIAL_NO", length = 100)
    private String Asst_sl_no;  // Assuming it's a VARCHAR2(100)

    @Column(name = "VERIFY_USER", length = 100)
    private String verify_user;  // Assuming it's a VARCHAR2(100)

    @Column(name = "VERIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date verify_time;  // Assuming it's a TIMESTAMP(6)

    @Column(name = "ACTION", length = 50)
    private String action;  // Assuming it's a VARCHAR2(50)
    
    @Column(name = "REMARK", length = 50)
    private String remark;  // Assuming it's a VARCHAR2(50)

    
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	// Getters and Setters
    public BigDecimal getAudit_Id() {
        return audit_Id;
    }

    public void setAudit_Id(BigDecimal audit_Id) {
        this.audit_Id = audit_Id;
    }

    public String getAsst_sl_no() {
        return Asst_sl_no;
    }

    public void setAsst_sl_no(String Asst_sl_no) {
        this.Asst_sl_no = Asst_sl_no;
    }

    public String getVerify_user() {
        return verify_user;
    }

    public void setVerify_user(String verify_user) {
        this.verify_user = verify_user;
    }

    public Date getVerify_time() {
        return verify_time;
    }

    public void setVerify_time(Date verify_time) {
        this.verify_time = verify_time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }	    
	}