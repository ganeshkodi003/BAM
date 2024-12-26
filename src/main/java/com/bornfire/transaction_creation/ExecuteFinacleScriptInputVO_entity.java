package com.bornfire.transaction_creation;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExecuteFinacleScriptInputVO_entity", propOrder = {
    "requestId"
    
})
public class ExecuteFinacleScriptInputVO_entity {

	@XmlElement(name = "requestId")
    protected String requestId;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
}
