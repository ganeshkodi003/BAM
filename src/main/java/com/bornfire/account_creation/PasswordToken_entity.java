package com.bornfire.account_creation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PasswordToken_entity", propOrder = {
    "UserId",
    "Password"
})
public class PasswordToken_entity {

	@XmlElement(name = "UserId")
    protected String UserId;

	@XmlElement(name = "Password")
    protected String Password;

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
}
