package com.bornfire.transaction_creation;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "executeFinacleScriptRequest", propOrder = {
    "executeFinacleScriptInputVO",
    "executeFinacleScriptCustomData"
    
})
public class ExecuteFinacleScriptRequest_entity {

    @XmlElement(name = "ExecuteFinacleScriptInputVO", required = true)
    protected List<ExecuteFinacleScriptInputVO_entity> executeFinacleScriptInputVO;

    @XmlElement(name = "executeFinacleScript_CustomData", required = true)
    protected List<ExecuteFinacleScript_CustomData_entity> executeFinacleScriptCustomData;

	public List<ExecuteFinacleScriptInputVO_entity> getExecuteFinacleScriptInputVO() {
		return executeFinacleScriptInputVO;
	}

	public void setExecuteFinacleScriptInputVO(List<ExecuteFinacleScriptInputVO_entity> executeFinacleScriptInputVO) {
		this.executeFinacleScriptInputVO = executeFinacleScriptInputVO;
	}

	public List<ExecuteFinacleScript_CustomData_entity> getExecuteFinacleScriptCustomData() {
		return executeFinacleScriptCustomData;
	}

	public void setExecuteFinacleScriptCustomData(
			List<ExecuteFinacleScript_CustomData_entity> executeFinacleScriptCustomData) {
		this.executeFinacleScriptCustomData = executeFinacleScriptCustomData;
	}

	@Override
	public String toString() {
		return "executeFinacleScriptRequest_entity [executeFinacleScriptInputVO=" + executeFinacleScriptInputVO
				+ ", executeFinacleScriptCustomData=" + executeFinacleScriptCustomData + "]";
	}

	
    
}
