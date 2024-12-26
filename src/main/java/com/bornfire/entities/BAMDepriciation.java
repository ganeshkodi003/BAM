package com.bornfire.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BAM_DEPRICIATION")
public class BAMDepriciation {

	@Id
	private String	solid;
	private String	dep_category;
	private String	assetsrlnofrom;
	private String	assettype;
	private String	depreciationmethod;
	private String	depreciationperiodfrom;
	private String	createtransaction;
	private String	soldescription;
	private String	descritipn;
	private String	assetsrlnoto;
	private String	dep_description;
	private String	frequency;
	private String	depreciationperiod;
	private String	remarks;
	public String getSolid() {
		return solid;
	}
	public void setSolid(String solid) {
		this.solid = solid;
	}
	public String getDep_category() {
		return dep_category;
	}
	public void setDep_category(String dep_category) {
		this.dep_category = dep_category;
	}
	public String getAssetsrlnofrom() {
		return assetsrlnofrom;
	}
	public void setAssetsrlnofrom(String assetsrlnofrom) {
		this.assetsrlnofrom = assetsrlnofrom;
	}
	public String getAssettype() {
		return assettype;
	}
	public void setAssettype(String assettype) {
		this.assettype = assettype;
	}
	public String getDepreciationmethod() {
		return depreciationmethod;
	}
	public void setDepreciationmethod(String depreciationmethod) {
		this.depreciationmethod = depreciationmethod;
	}
	public String getDepreciationperiodfrom() {
		return depreciationperiodfrom;
	}
	public void setDepreciationperiodfrom(String depreciationperiodfrom) {
		this.depreciationperiodfrom = depreciationperiodfrom;
	}
	public String getCreatetransaction() {
		return createtransaction;
	}
	public void setCreatetransaction(String createtransaction) {
		this.createtransaction = createtransaction;
	}
	public String getSoldescription() {
		return soldescription;
	}
	public void setSoldescription(String soldescription) {
		this.soldescription = soldescription;
	}
	public String getDescritipn() {
		return descritipn;
	}
	public void setDescritipn(String descritipn) {
		this.descritipn = descritipn;
	}
	public String getAssetsrlnoto() {
		return assetsrlnoto;
	}
	public void setAssetsrlnoto(String assetsrlnoto) {
		this.assetsrlnoto = assetsrlnoto;
	}
	public String getDep_description() {
		return dep_description;
	}
	public void setDep_description(String dep_description) {
		this.dep_description = dep_description;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getDepreciationperiod() {
		return depreciationperiod;
	}
	public void setDepreciationperiod(String depreciationperiod) {
		this.depreciationperiod = depreciationperiod;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "BAMDepriciation [solid=" + solid + ", dep_category=" + dep_category + ", assetsrlnofrom="
				+ assetsrlnofrom + ", assettype=" + assettype + ", depreciationmethod=" + depreciationmethod
				+ ", depreciationperiodfrom=" + depreciationperiodfrom + ", createtransaction=" + createtransaction
				+ ", soldescription=" + soldescription + ", descritipn=" + descritipn + ", assetsrlnoto=" + assetsrlnoto
				+ ", dep_description=" + dep_description + ", frequency=" + frequency + ", depreciationperiod="
				+ depreciationperiod + ", remarks=" + remarks + "]";
	}
	public BAMDepriciation(String solid, String dep_category, String assetsrlnofrom, String assettype,
			String depreciationmethod, String depreciationperiodfrom, String createtransaction, String soldescription,
			String descritipn, String assetsrlnoto, String dep_description, String frequency, String depreciationperiod,
			String remarks) {
		super();
		this.solid = solid;
		this.dep_category = dep_category;
		this.assetsrlnofrom = assetsrlnofrom;
		this.assettype = assettype;
		this.depreciationmethod = depreciationmethod;
		this.depreciationperiodfrom = depreciationperiodfrom;
		this.createtransaction = createtransaction;
		this.soldescription = soldescription;
		this.descritipn = descritipn;
		this.assetsrlnoto = assetsrlnoto;
		this.dep_description = dep_description;
		this.frequency = frequency;
		this.depreciationperiod = depreciationperiod;
		this.remarks = remarks;
	}
	public BAMDepriciation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	
}
