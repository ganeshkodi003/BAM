package com.bornfire.transaction_creation;

import javax.persistence.Entity;
import javax.persistence.Table;

/* TransactionCreationResponse */
@Entity
@Table(name="BAM_TRANSACTION_CREATION_RESPONSE")
public class TransactionCreationResponse {

	String tranId;
	String status;

	public String getTranId() {
		return tranId;
	}

	public void setTranId(String tranId) {
		this.tranId = tranId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



}

