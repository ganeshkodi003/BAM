package com.bornfire.transaction_creation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "executeFinacleScript_CustomData_entity", propOrder = {
    "creditAcct",
    "debitAcct",
    "tranAmt",
    "rateCode"
    
})
public class ExecuteFinacleScript_CustomData_entity {

	@XmlElement(name = "CreditAcct")
    protected String creditAcct;
	@XmlElement(name = "DebitAcct")
    protected String debitAcct;
	@XmlElement(name = "tranAmt")
    protected String tranAmt;
	@XmlElement(name = "rateCode")
    protected String rateCode;
	public String getCreditAcct() {
		return creditAcct;
	}
	public void setCreditAcct(String creditAcct) {
		this.creditAcct = creditAcct;
	}
	public String getBebitAcct() {
		return debitAcct;
	}
	public void setBebitAcct(String bebitAcct) {
		this.debitAcct = bebitAcct;
	}
	public String getTranAmt() {
		return tranAmt;
	}
	public void setTranAmt(String tranAmt) {
		this.tranAmt = tranAmt;
	}
	public String getRateCode() {
		return rateCode;
	}
	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}
	@Override
	public String toString() {
		return "executeFinacleScript_CustomData_entity [creditAcct=" + creditAcct + ", debitAcct=" + debitAcct
				+ ", tranAmt=" + tranAmt + ", rateCode=" + rateCode + "]";
	}

	
	
	
}
