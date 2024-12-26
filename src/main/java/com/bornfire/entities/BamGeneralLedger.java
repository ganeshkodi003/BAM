package com.bornfire.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "GENERAL_LED")
public class BamGeneralLedger {

    @Id
    @Column(name="GL_CODE")
    private String glCode;
    @Column(name="GL_DESCRIPTION")
    private String glDescription;
    @Column(name="GL_TYPE")
    private String glType;
    @Column(name="GL_TYPE_DESCRIPTION")
    private String glTypeDescription;
    @Column(name="MODULE")
    private String module;
    @Column(name="REMARKS")
    private String remarks;
    @Column(name="DEL_FLG")
    private String delFlg;
    @Column(name="MODIFY_FLG")
    private String modifyFlg;
   

    // Getters and Setters

    public String getGlCode() {
        return glCode;
    }

    public void setGlCode(String glCode) {
        this.glCode = glCode;
    }

    public String getGlDescription() {
        return glDescription;
    }

    public void setGlDescription(String glDescription) {
        this.glDescription = glDescription;
    }

    public String getGlType() {
        return glType;
    }

    public void setGlType(String glType) {
        this.glType = glType;
    }

    public String getGlTypeDescription() {
        return glTypeDescription;
    }

    public void setGlTypeDescription(String glTypeDescription) {
        this.glTypeDescription = glTypeDescription;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getModifyFlg() {
        return modifyFlg;
    }

    public void setModifyFlg(String modifyFlg) {
        this.modifyFlg = modifyFlg;
    }

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

	public BamGeneralLedger() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BamGeneralLedger(String glCode, String glDescription, String glType, String glTypeDescription, String module,
			String remarks, String modifyFlg, String delFlg) {
		super();
		this.glCode = glCode;
		this.glDescription = glDescription;
		this.glType = glType;
		this.glTypeDescription = glTypeDescription;
		this.module = module;
		this.remarks = remarks;
		this.modifyFlg = modifyFlg;
		this.delFlg = delFlg;
	}
}
