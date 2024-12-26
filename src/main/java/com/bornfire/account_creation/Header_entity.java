package com.bornfire.account_creation;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Header_entity", propOrder = {
    "requestHeader",
    
})
public class Header_entity {


    @XmlElement(name = "RequestHeader", required = true)
    protected RequestHeader_entity requestHeader;

	public RequestHeader_entity getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequestHeader_entity requestHeader) {
		this.requestHeader = requestHeader;
	}

	@Override
	public String toString() {
		return "Header_entity [requestHeader=" + requestHeader + "]";
	}
    

    
    
    
	
}
