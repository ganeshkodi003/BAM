package com.bornfire.account_creation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Body_entity", propOrder = {
    "executeFinacleScriptRequest"
    
})
public class Body_entity {

    @XmlElement(name = "executeFinacleScriptRequest", required = true)
    protected executeFinacleScriptRequest_entity executeFinacleScriptRequest;

	public executeFinacleScriptRequest_entity getExecuteFinacleScriptRequest() {
		return executeFinacleScriptRequest;
	}

	public void setExecuteFinacleScriptRequest(executeFinacleScriptRequest_entity executeFinacleScriptRequest) {
		this.executeFinacleScriptRequest = executeFinacleScriptRequest;
	}

	@Override
	public String toString() {
		return "Body_entity [executeFinacleScriptRequest=" + executeFinacleScriptRequest + "]";
	}

	

}
