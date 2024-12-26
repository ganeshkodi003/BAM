package com.bornfire.transaction_creation;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Security_entity", propOrder = {
    "Token",
    
    "FICertToken",
    "RealUserLoginSessionId",
    "RealUser",
    "RealUserPwd",
    "SSOTransferToken"
})
public class Security_entity {

    @XmlElement(name = "Token", required = true)
    protected Token_entity Token;
    

	@XmlElement(name = "FICertToken")
    protected String FICertToken;
	@XmlElement(name = "RealUserLoginSessionId")
    protected String RealUserLoginSessionId;
	@XmlElement(name = "RealUser")
    protected String RealUser;
	@XmlElement(name = "RealUserPwd")
    protected String RealUserPwd;
	@XmlElement(name = "SSOTransferToken")
    protected String SSOTransferToken;
	public Token_entity getToken() {
		return Token;
	}
	public void setToken(Token_entity token) {
		Token = token;
	}
	public String getFICertToken() {
		return FICertToken;
	}
	public void setFICertToken(String fICertToken) {
		FICertToken = fICertToken;
	}
	public String getRealUserLoginSessionId() {
		return RealUserLoginSessionId;
	}
	public void setRealUserLoginSessionId(String realUserLoginSessionId) {
		RealUserLoginSessionId = realUserLoginSessionId;
	}
	public String getRealUser() {
		return RealUser;
	}
	public void setRealUser(String realUser) {
		RealUser = realUser;
	}
	public String getRealUserPwd() {
		return RealUserPwd;
	}
	public void setRealUserPwd(String realUserPwd) {
		RealUserPwd = realUserPwd;
	}
	public String getSSOTransferToken() {
		return SSOTransferToken;
	}
	public void setSSOTransferToken(String sSOTransferToken) {
		SSOTransferToken = sSOTransferToken;
	}
}
