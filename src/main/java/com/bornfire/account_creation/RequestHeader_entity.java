package com.bornfire.account_creation;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestHeader", propOrder = {
    "MessageKey",
    "RequestMessageInfo",
    "Security"
    
})
public class RequestHeader_entity {

	

    @XmlElement(name = "MessageKey", required = true)
    protected List<msgkey_entity> MessageKey;
    @XmlElement(name = "RequestMessageInfo", required = true)
    protected List<RequestMessageInfo_entity> RequestMessageInfo;
    @XmlElement(name = "Security", required = true)
    protected List<Security_entity> Security;
	public List<msgkey_entity> getMessageKey() {
		return MessageKey;
	}
	public void setMessageKey(List<msgkey_entity> messageKey) {
		MessageKey = messageKey;
	}
	public List<RequestMessageInfo_entity> getRequestMessageInfo() {
		return RequestMessageInfo;
	}
	public void setRequestMessageInfo(List<RequestMessageInfo_entity> requestMessageInfo) {
		RequestMessageInfo = requestMessageInfo;
	}
	public List<Security_entity> getSecurity() {
		return Security;
	}
	public void setSecurity(List<Security_entity> security) {
		Security = security;
	}
        
}
