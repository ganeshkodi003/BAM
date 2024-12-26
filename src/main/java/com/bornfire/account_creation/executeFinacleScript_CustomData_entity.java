package com.bornfire.account_creation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "executeFinacleScript_CustomData_entity", propOrder = {
    "solId",
    "SchmCode",
    "crncyCode",
    "AcctName",
    "AcctShortName"
    
})
public class executeFinacleScript_CustomData_entity {

	@XmlElement(name = "solId")
    protected String solId;
	@XmlElement(name = "SchmCode")
    protected String SchmCode;
	@XmlElement(name = "crncyCode")
    protected String crncyCode;
	@XmlElement(name = "AcctName")
    protected String AcctName;
	@XmlElement(name = "AcctShortName")
    protected String AcctShortName;
	public String getSolId() {
		return solId;
	}
	public void setSolId(String solId) {
		this.solId = solId;
	}
	public String getSchmCode() {
		return SchmCode;
	}
	public void setSchmCode(String schmCode) {
		SchmCode = schmCode;
	}
	public String getCrncyCode() {
		return crncyCode;
	}
	public void setCrncyCode(String crncyCode) {
		this.crncyCode = crncyCode;
	}
	public String getAcctName() {
		return AcctName;
	}
	public void setAcctName(String acctName) {
		AcctName = acctName;
	}
	public String getAcctShortName() {
		return AcctShortName;
	}
	public void setAcctShortName(String acctShortName) {
		AcctShortName = acctShortName;
	}
}
