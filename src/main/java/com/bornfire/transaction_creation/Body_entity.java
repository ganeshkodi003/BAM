package com.bornfire.transaction_creation;

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
    protected ExecuteFinacleScriptRequest_entity executeFinacleScriptRequest;

	public ExecuteFinacleScriptRequest_entity getExecuteFinacleScriptRequest() {
		return executeFinacleScriptRequest;
	}

	public void setExecuteFinacleScriptRequest(ExecuteFinacleScriptRequest_entity executeFinacleScriptRequest) {
		this.executeFinacleScriptRequest = executeFinacleScriptRequest;
	}

	@Override
	public String toString() {
		return "Body_entity [executeFinacleScriptRequest=" + executeFinacleScriptRequest + "]";
	}

	
	

}
