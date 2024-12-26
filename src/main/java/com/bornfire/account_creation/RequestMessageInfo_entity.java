package com.bornfire.account_creation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestMessageInfo_entity", propOrder = {
    "BankId",
    "TimeZone",
    "EntityId",
    "EntityType",
    "ArmCorrelationId",
    "MessageDateTime"
})
public class RequestMessageInfo_entity {

	

	@XmlElement(name = "BankId")
    protected String BankId;
	@XmlElement(name = "TimeZone")
    protected String TimeZone;
	@XmlElement(name = "EntityId")
    protected String EntityId;
	@XmlElement(name = "EntityType")
    protected String EntityType;
	@XmlElement(name = "ArmCorrelationId")
    protected String ArmCorrelationId;
	@XmlElement(name = "MessageDateTime")
    protected String MessageDateTime;
	public String getBankId() {
		return BankId;
	}
	public void setBankId(String bankId) {
		BankId = bankId;
	}
	public String getTimeZone() {
		return TimeZone;
	}
	public void setTimeZone(String timeZone) {
		TimeZone = timeZone;
	}
	public String getEntityId() {
		return EntityId;
	}
	public void setEntityId(String entityId) {
		EntityId = entityId;
	}
	public String getEntityType() {
		return EntityType;
	}
	public void setEntityType(String entityType) {
		EntityType = entityType;
	}
	public String getArmCorrelationId() {
		return ArmCorrelationId;
	}
	public void setArmCorrelationId(String armCorrelationId) {
		ArmCorrelationId = armCorrelationId;
	}
	public String getMessageDateTime() {
		return MessageDateTime;
	}
	public void setMessageDateTime(String messageDateTime) {
		MessageDateTime = messageDateTime;
	}
}
