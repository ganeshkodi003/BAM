package com.bornfire.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "BAM_ACQUISITION")
public class BamAcquisition {

    @Id
    @Column(name = "ASSET_NAME", length = 100, nullable = false)
    private String assetName;

    @Column(name = "ASSET_TYPE", length = 50, nullable = false)
    private String assetType;

    @Column(name = "ORIGINAL_COST", nullable = false)
    private Double originalCost;

    @Column(name = "DEPRECIATION_METHOD", length = 50)
    private String depreciationMethod;

    @Column(name = "DEPRECIATION_PERCENTAGE", precision = 5, scale = 2)
    private Double depreciationPercentage;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATE_OF_ACQUISITION")
    private Date dateOfAcquisition;
    

	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	private Date	entry_time;
	private Date	modify_time;
	private Date	verify_time;
	private String	del_flg;
	private String	entity_flg;
	private String	modify_flg;
	
	


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

	// Default constructor
    public BamAcquisition() {
    }

    // Parameterized constructor
   
    // Getters and Setters
    public String getAssetName() {
        return assetName;
    }

    public BamAcquisition(String assetName, String assetType, Double originalCost, String depreciationMethod,
			Double depreciationPercentage, Date dateOfAcquisition, String entry_user, String modify_user,
			String verify_user, Date entry_time, Date modify_time, Date verify_time, String del_flg, String entity_flg,
			String modify_flg) {
		super();
		this.assetName = assetName;
		this.assetType = assetType;
		this.originalCost = originalCost;
		this.depreciationMethod = depreciationMethod;
		this.depreciationPercentage = depreciationPercentage;
		this.dateOfAcquisition = dateOfAcquisition;
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

	public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public Double getOriginalCost() {
        return originalCost;
    }

    public void setOriginalCost(Double originalCost) {
        this.originalCost = originalCost;
    }

    public String getDepreciationMethod() {
        return depreciationMethod;
    }

    public void setDepreciationMethod(String depreciationMethod) {
        this.depreciationMethod = depreciationMethod;
    }

    public Double getDepreciationPercentage() {
        return depreciationPercentage;
    }

    public void setDepreciationPercentage(Double depreciationPercentage) {
        this.depreciationPercentage = depreciationPercentage;
    }

    public Date getDateOfAcquisition() {
        return dateOfAcquisition;
    }

    public void setDateOfAcquisition(Date dateOfAcquisition) {
        this.dateOfAcquisition = dateOfAcquisition;
    }
}
