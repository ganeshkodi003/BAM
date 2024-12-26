package com.bornfire.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "inventory_master")
public class Inventory_Master {

    @Id
    @Column(name = "asset_serial_number")
    private String assetSerialNumber;

    @Column(name = "asset_name", nullable = false)
    private String assetName;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "asset_type", nullable = false)
    private String assetType;

    @Column(name = "asset_currency", nullable = false)
    private String assetCurrency;

    @Column(name = "date_of_purchase", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfPurchase;

    @Column(name = "year_of_purchase", nullable = false)
    private Integer yearOfPurchase;

    @Column(name = "original_cost", nullable = false, precision = 15, scale = 2)
    private BigDecimal originalCost;

    @Column(name = "life_span_months", nullable = false)
    private Integer lifeSpanMonths;

    @Column(name = "asset_expiry_date")
    @Temporal(TemporalType.DATE)
    private Date assetExpiryDate;

    @Column(name = "asset_remarks", length = 255)
    private String assetRemarks;

    @Column(name = "depreciation_flag", nullable = false)
    private String depreciationFlag;

    @Column(name = "depreciation_frequency", nullable = false)
    private String depreciationFrequency;

    @Column(name = "depreciation_method", nullable = false)
    private String depreciationMethod;

    @Column(name = "depreciation_percentage", precision = 5, scale = 2)
    private BigDecimal depreciationPercentage;

    @Column(name = "accumulated_depreciation", precision = 15, scale = 2)
    private BigDecimal accumulatedDepreciation;

    @Column(name = "date_of_last_depreciation", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfLastDepreciation;

    @Column(name = "date_of_acquisition", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfAcquisition;

    @Column(name = "date_of_last_transfer")
    @Temporal(TemporalType.DATE)
    private Date dateOfLastTransfer;

    @Column(name = "current_book_value", nullable = false, precision = 15, scale = 2)
    private BigDecimal currentBookValue;

    @Column(name = "market_value", nullable = false, precision = 15, scale = 2)
    private BigDecimal marketValue;

    @Column(name = "location_type", nullable = false)
    private String locationType;

    @Column(name = "sol_id", nullable = false)
    private String solId;

    @Column(name = "employee_id", nullable = false)
    private String employeeId;

    @Column(name = "department_division_name", length = 100)
    private String departmentDivisionName;

    @Column(name = "location_address", length = 255)
    private String locationAddress;

    @Column(name = "location_remarks", length = 255)
    private String locationRemarks;

    @Column(name = "guarantee_amount_flag", nullable = false)
    private String guaranteeAmountFlag;

    @Column(name = "guarantee_amount_percentage", precision = 5, scale = 2)
    private BigDecimal guaranteeAmountPercentage;

    @Column(name = "nominal_depreciation_amount", precision = 15, scale = 2)
    private BigDecimal nominalDepreciationAmount;

    @Column(name = "depreciation_remarks", length = 255)
    private String depreciationRemarks;
    
    @Column(name = "transfer_reference_number")
    private String transferReferenceNumber;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "purchase_details")
    private String purchaseDetails;

    @Column(name = "TO_Category")
    private String toCategory;

    @Column(name = "TO_Location_type")
    private String toLocationType;

    @Column(name = "TO_solid")
    private String toSolid;

    @Column(name = "TO_employee_id")
    private String toEmployeeId;

    @Column(name = "TO_Dep_Division_name")
    private String toDepDivisionName;

    @Column(name = "TO_location_address")
    private String toLocationAddress;

    @Column(name = "TO_location_Remarks")
    private String toLocationRemarks;

    @Column(name = "Book_value_on_date_sale")
    private BigDecimal bookValueOnDateSale;

    @Column(name = "sale_value")
    private BigDecimal saleValue;

    @Column(name = "profit_loss")
    private BigDecimal profitLoss;

    @Column(name = "buyers_name")
    private String buyersName;

    @Column(name = "sale_details")
    private String saleDetails;

    @Column(name = "date_of_scrap")
    @Temporal(TemporalType.DATE)
    private Date dateOfScrap;

    @Column(name = "Book_value_on_date_of_write_off")
    private BigDecimal bookValueOnDateOfWriteOff;

    @Column(name = "scrap_value")
    private BigDecimal scrapValue;

    @Column(name = "scrap_remarks")
    private String scrapRemarks;


    

    // Getters and Setters
    public String getAssetSerialNumber() {
        return assetSerialNumber;
    }

    public void setAssetSerialNumber(String assetSerialNumber) {
        this.assetSerialNumber = assetSerialNumber;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetCurrency() {
        return assetCurrency;
    }

    public void setAssetCurrency(String assetCurrency) {
        this.assetCurrency = assetCurrency;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Integer getYearOfPurchase() {
        return yearOfPurchase;
    }

    public void setYearOfPurchase(Integer yearOfPurchase) {
        this.yearOfPurchase = yearOfPurchase;
    }

    public BigDecimal getOriginalCost() {
        return originalCost;
    }

    public void setOriginalCost(BigDecimal originalCost) {
        this.originalCost = originalCost;
    }

    public Integer getLifeSpanMonths() {
        return lifeSpanMonths;
    }

    public void setLifeSpanMonths(Integer lifeSpanMonths) {
        this.lifeSpanMonths = lifeSpanMonths;
    }

    public Date getAssetExpiryDate() {
        return assetExpiryDate;
    }

    public void setAssetExpiryDate(Date assetExpiryDate) {
        this.assetExpiryDate = assetExpiryDate;
    }

    public String getAssetRemarks() {
        return assetRemarks;
    }

    public void setAssetRemarks(String assetRemarks) {
        this.assetRemarks = assetRemarks;
    }

    public String getDepreciationFlag() {
        return depreciationFlag;
    }

    public void setDepreciationFlag(String depreciationFlag) {
        this.depreciationFlag = depreciationFlag;
    }

    public String getDepreciationFrequency() {
        return depreciationFrequency;
    }

    public void setDepreciationFrequency(String depreciationFrequency) {
        this.depreciationFrequency = depreciationFrequency;
    }

    public String getDepreciationMethod() {
        return depreciationMethod;
    }

    public void setDepreciationMethod(String depreciationMethod) {
        this.depreciationMethod = depreciationMethod;
    }

    public BigDecimal getDepreciationPercentage() {
        return depreciationPercentage;
    }

    public void setDepreciationPercentage(BigDecimal depreciationPercentage) {
        this.depreciationPercentage = depreciationPercentage;
    }

    public BigDecimal getAccumulatedDepreciation() {
        return accumulatedDepreciation;
    }

    public void setAccumulatedDepreciation(BigDecimal accumulatedDepreciation) {
        this.accumulatedDepreciation = accumulatedDepreciation;
    }

    public Date getDateOfLastDepreciation() {
        return dateOfLastDepreciation;
    }

    public void setDateOfLastDepreciation(Date dateOfLastDepreciation) {
        this.dateOfLastDepreciation = dateOfLastDepreciation;
    }

    public Date getDateOfAcquisition() {
        return dateOfAcquisition;
    }

    public void setDateOfAcquisition(Date dateOfAcquisition) {
        this.dateOfAcquisition = dateOfAcquisition;
    }

    public Date getDateOfLastTransfer() {
        return dateOfLastTransfer;
    }

    public void setDateOfLastTransfer(Date dateOfLastTransfer) {
        this.dateOfLastTransfer = dateOfLastTransfer;
    }

    public BigDecimal getCurrentBookValue() {
        return currentBookValue;
    }

    public void setCurrentBookValue(BigDecimal currentBookValue) {
        this.currentBookValue = currentBookValue;
    }

    public BigDecimal getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getSolId() {
        return solId;
    }

    public void setSolId(String solId) {
        this.solId = solId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepartmentDivisionName() {
        return departmentDivisionName;
    }

    public void setDepartmentDivisionName(String departmentDivisionName) {
        this.departmentDivisionName = departmentDivisionName;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLocationRemarks() {
        return locationRemarks;
    }

    public void setLocationRemarks(String locationRemarks) {
        this.locationRemarks = locationRemarks;
    }

    public String getGuaranteeAmountFlag() {
        return guaranteeAmountFlag;
    }

    public void setGuaranteeAmountFlag(String guaranteeAmountFlag) {
        this.guaranteeAmountFlag = guaranteeAmountFlag;
    }

    public BigDecimal getGuaranteeAmountPercentage() {
        return guaranteeAmountPercentage;
    }

    public void setGuaranteeAmountPercentage(BigDecimal guaranteeAmountPercentage) {
        this.guaranteeAmountPercentage = guaranteeAmountPercentage;
    }

    public BigDecimal getNominalDepreciationAmount() {
        return nominalDepreciationAmount;
    }

    public void setNominalDepreciationAmount(BigDecimal nominalDepreciationAmount) {
        this.nominalDepreciationAmount = nominalDepreciationAmount;
    }

    public String getDepreciationRemarks() {
        return depreciationRemarks;
    }

    public void setDepreciationRemarks(String depreciationRemarks) {
        this.depreciationRemarks = depreciationRemarks;
    }

    
    
	public String getTransferReferenceNumber() {
		return transferReferenceNumber;
	}

	public void setTransferReferenceNumber(String transferReferenceNumber) {
		this.transferReferenceNumber = transferReferenceNumber;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getPurchaseDetails() {
		return purchaseDetails;
	}

	public void setPurchaseDetails(String purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}

	public String getToCategory() {
		return toCategory;
	}

	public void setToCategory(String toCategory) {
		this.toCategory = toCategory;
	}

	public String getToLocationType() {
		return toLocationType;
	}

	public void setToLocationType(String toLocationType) {
		this.toLocationType = toLocationType;
	}

	public String getToSolid() {
		return toSolid;
	}

	public void setToSolid(String toSolid) {
		this.toSolid = toSolid;
	}

	public String getToEmployeeId() {
		return toEmployeeId;
	}

	public void setToEmployeeId(String toEmployeeId) {
		this.toEmployeeId = toEmployeeId;
	}

	public String getToDepDivisionName() {
		return toDepDivisionName;
	}

	public void setToDepDivisionName(String toDepDivisionName) {
		this.toDepDivisionName = toDepDivisionName;
	}

	public String getToLocationAddress() {
		return toLocationAddress;
	}

	public void setToLocationAddress(String toLocationAddress) {
		this.toLocationAddress = toLocationAddress;
	}

	public String getToLocationRemarks() {
		return toLocationRemarks;
	}

	public void setToLocationRemarks(String toLocationRemarks) {
		this.toLocationRemarks = toLocationRemarks;
	}

	public BigDecimal getBookValueOnDateSale() {
		return bookValueOnDateSale;
	}

	public void setBookValueOnDateSale(BigDecimal bookValueOnDateSale) {
		this.bookValueOnDateSale = bookValueOnDateSale;
	}

	public BigDecimal getSaleValue() {
		return saleValue;
	}

	public void setSaleValue(BigDecimal saleValue) {
		this.saleValue = saleValue;
	}

	public BigDecimal getProfitLoss() {
		return profitLoss;
	}

	public void setProfitLoss(BigDecimal profitLoss) {
		this.profitLoss = profitLoss;
	}

	public String getBuyersName() {
		return buyersName;
	}

	public void setBuyersName(String buyersName) {
		this.buyersName = buyersName;
	}

	public String getSaleDetails() {
		return saleDetails;
	}

	public void setSaleDetails(String saleDetails) {
		this.saleDetails = saleDetails;
	}

	public Date getDateOfScrap() {
		return dateOfScrap;
	}

	public void setDateOfScrap(Date dateOfScrap) {
		this.dateOfScrap = dateOfScrap;
	}

	public BigDecimal getBookValueOnDateOfWriteOff() {
		return bookValueOnDateOfWriteOff;
	}

	public void setBookValueOnDateOfWriteOff(BigDecimal bookValueOnDateOfWriteOff) {
		this.bookValueOnDateOfWriteOff = bookValueOnDateOfWriteOff;
	}

	public BigDecimal getScrapValue() {
		return scrapValue;
	}

	public void setScrapValue(BigDecimal scrapValue) {
		this.scrapValue = scrapValue;
	}

	public String getScrapRemarks() {
		return scrapRemarks;
	}

	public void setScrapRemarks(String scrapRemarks) {
		this.scrapRemarks = scrapRemarks;
	}

	public Inventory_Master() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
