package com.bornfire.transaction_creation;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Token_entity", propOrder = {
    "PasswordToken"
})
public class Token_entity {

    @XmlElement(name = "PasswordToken", required = true)
    protected List<PasswordToken_entity> PasswordToken;

	public List<PasswordToken_entity> getPasswordToken() {
		return PasswordToken;
	}

	public void setPasswordToken(List<PasswordToken_entity> passwordToken) {
		PasswordToken = passwordToken;
	}
    
}
