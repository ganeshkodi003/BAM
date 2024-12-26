package com.bornfire.account_creation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "msgkey_entity", propOrder = {
    "RequestUUID",
    "ServiceRequestId",
    "ServiceRequestVersion",
    "ChannelId",
    "LanguageId"
})
public class msgkey_entity {
	
	@XmlElement(name = "RequestUUID")
    protected String RequestUUID;
	@XmlElement(name = "ServiceRequestId")
    protected String ServiceRequestId;
	@XmlElement(name = "ServiceRequestVersion")
    protected String ServiceRequestVersion;
	@XmlElement(name = "ChannelId")
    protected String ChannelId;
	@XmlElement(name = "LanguageId")
    protected String LanguageId;
	public String getRequestUUID() {
		return RequestUUID;
	}
	public void setRequestUUID(String requestUUID) {
		RequestUUID = requestUUID;
	}
	public String getServiceRequestId() {
		return ServiceRequestId;
	}
	public void setServiceRequestId(String serviceRequestId) {
		ServiceRequestId = serviceRequestId;
	}
	public String getServiceRequestVersion() {
		return ServiceRequestVersion;
	}
	public void setServiceRequestVersion(String serviceRequestVersion) {
		ServiceRequestVersion = serviceRequestVersion;
	}
	public String getChannelId() {
		return ChannelId;
	}
	public void setChannelId(String channelId) {
		ChannelId = channelId;
	}
	public String getLanguageId() {
		return LanguageId;
	}
	public void setLanguageId(String languageId) {
		LanguageId = languageId;
	}
	
	
	
	
}
