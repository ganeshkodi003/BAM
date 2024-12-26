package com.bornfire.messagebuilder;

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.bornfire.account_creation.Body_entity;
import com.bornfire.account_creation.Documents;
import com.bornfire.account_creation.ExecuteFinacleScriptInputVO_entity;

import com.bornfire.account_creation.FIXML;
import com.bornfire.account_creation.Header_entity;
import com.bornfire.account_creation.PasswordToken_entity;
import com.bornfire.account_creation.RequestHeader_entity;
import com.bornfire.account_creation.RequestMessageInfo_entity;
import com.bornfire.account_creation.Security_entity;
import com.bornfire.account_creation.Token_entity;
import com.bornfire.account_creation.executeFinacleScriptRequest_entity;
import com.bornfire.account_creation.executeFinacleScript_CustomData_entity;
import com.bornfire.account_creation.msgkey_entity;
import com.bornfire.config.Listener;
import com.bornfire.entities.ApiResponse_Entity;
import com.bornfire.entities.BAMInventorymaster;
import com.bornfire.entities.BAMInventryMastRep;
import com.bornfire.transaction_creation.TransactionCreationResponse;

@Component
public class DocumentPacks1 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7528939653987212494L;

	@Autowired
	Environment env;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	Listener listener;

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	BAMInventryMastRep bamInventoryMasterRepository;
	
	
	public void hostVerification() throws NoSuchAlgorithmException, KeyManagementException {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };
		// Install the all-trusting trust manager
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = (hostname, session) -> true;
		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	}
	

	/**** Create Document of getFixmlDoc 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException ****/
	public ApiResponse_Entity getFixmlDoc_account() throws KeyManagementException, NoSuchAlgorithmException {
		
		hostVerification();

		FIXML fixmlHdr = new FIXML();

		// Header Section
		Header_entity header = new Header_entity();
		RequestHeader_entity requestHeader = new RequestHeader_entity();
		// MessageKey List
		List<msgkey_entity> messageKeyList = new ArrayList<>();
		msgkey_entity msgkeyentity = new msgkey_entity();

		Session hs = sessionFactory.getCurrentSession();
		DecimalFormat numformate = new DecimalFormat("0000000");

		BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT API_REG_NO.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();
		String serialno = numformate.format(billNumber);
		System.out.println("Serial No "+serialno);
		msgkeyentity.setRequestUUID("BFBAM"+serialno);
		msgkeyentity.setServiceRequestId("executeFinacleScript");
		msgkeyentity.setServiceRequestVersion("10.2");
		msgkeyentity.setChannelId("COR");
		msgkeyentity.setLanguageId(""); // Example value
		messageKeyList.add(msgkeyentity);
		requestHeader.setMessageKey(messageKeyList);
        // Get the current date and time
        Date date = new Date();
        
        // Define the desired format as <MessageDateTime>
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        
        // Convert the Date object to a formatted string
        String dateString = formatter.format(date);
        
        // Output the formatted date string
        System.out.println(dateString); 
		// RequestMessageInfo List
		List<RequestMessageInfo_entity> requestMessageInfoList = new ArrayList<>();
		RequestMessageInfo_entity requestMessageInfoentity = new RequestMessageInfo_entity();
		requestMessageInfoentity.setBankId("01");
		requestMessageInfoentity.setTimeZone(""); // Example value
		requestMessageInfoentity.setEntityId(""); // Example value
		requestMessageInfoentity.setEntityType(""); // Example value
		requestMessageInfoentity.setArmCorrelationId(""); // Example value
		requestMessageInfoentity.setMessageDateTime(dateString);
		requestMessageInfoList.add(requestMessageInfoentity);
		requestHeader.setRequestMessageInfo(requestMessageInfoList);

		// Security List
		List<Security_entity> securityList = new ArrayList<>();
		Security_entity security = new Security_entity();
		Token_entity token = new Token_entity();
		List<PasswordToken_entity> passwordTokenList = new ArrayList<>();
		PasswordToken_entity passwordToken = new PasswordToken_entity();
		passwordToken.setUserId(""); // Example value
		passwordToken.setPassword(""); // Example value
		passwordTokenList.add(passwordToken);
		token.setPasswordToken(passwordTokenList);
		security.setToken(token);
		security.setFICertToken("");
		security.setRealUserLoginSessionId("");
		security.setRealUser("");
		security.setRealUserPwd("");
		security.setSSOTransferToken("");
		securityList.add(security);

		// Set RequestHeader fields
		requestHeader.setMessageKey(messageKeyList);
		requestHeader.setRequestMessageInfo(requestMessageInfoList);
		requestHeader.setSecurity(securityList);
		header.setRequestHeader(requestHeader);

		// Body Section
		Body_entity body = new Body_entity();
		executeFinacleScriptRequest_entity executeFinacleScriptRequest = new executeFinacleScriptRequest_entity();
		List<ExecuteFinacleScriptInputVO_entity> executeFinacleScriptInputVOList = new ArrayList<>();
		ExecuteFinacleScriptInputVO_entity executeFinacleScriptInputVO = new ExecuteFinacleScriptInputVO_entity();

		executeFinacleScriptInputVO.setRequestId("OAB_ACCT_OPEN.scr");
		 // Initialize a list to hold executeFinacleScriptCustomData entities
        List<executeFinacleScript_CustomData_entity> executeFinacleScriptCustomDataList = new ArrayList<>();
        
        // Fetch the latest record based on asset serial number
        BAMInventorymaster inventory = bamInventoryMasterRepository.findLatestRecord();
        
        if (inventory != null) {
            // Create a new instance of executeFinacleScript_CustomData_entity
            executeFinacleScript_CustomData_entity executeFinacleScriptCustomData = new executeFinacleScript_CustomData_entity();
            
            // Set values from the latest record
            executeFinacleScriptCustomData.setAcctName(inventory.getAsst_srl_no()); // Asset Serial Number
            executeFinacleScriptCustomData.setSolId(inventory.getSol_id()); // SOL ID
            executeFinacleScriptCustomData.setSchmCode("BFBAM");
            executeFinacleScriptCustomData.setCrncyCode("INR");
            executeFinacleScriptCustomData.setAcctShortName(inventory.getAsst_name()); // Asset Name

            // Add the configured object to the list
            executeFinacleScriptInputVOList.add(executeFinacleScriptInputVO);
            executeFinacleScriptCustomDataList.add(executeFinacleScriptCustomData);
            
            // Print asset serial number and asset name to the console
            System.out.println("Asset Serial Number: " + inventory.getAsst_srl_no());
            System.out.println("Asset Name: " + executeFinacleScriptCustomData.getAcctShortName());
        } else {
            System.out.println("No data found for the latest asset serial number.");
        }
		// Set Body fields
		executeFinacleScriptRequest.setExecuteFinacleScriptInputVO(executeFinacleScriptInputVOList);
		executeFinacleScriptRequest.setExecuteFinacleScriptCustomData(executeFinacleScriptCustomDataList);
		body.setExecuteFinacleScriptRequest(executeFinacleScriptRequest);

///Document
		FIXML document = new FIXML();
		document.setHeaDer(header);
		document.setBoDy(body);

///Convert Document XMl element to String
		JAXBContext jaxbContext;
		Marshaller jaxbMarshaller;
		StringWriter sw = null;
		try {
			jaxbContext = JAXBContext.newInstance(FIXML.class);
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			com.bornfire.account_creation.ObjectFactory obj = new com.bornfire.account_creation.ObjectFactory();
			JAXBElement<FIXML> jaxbElement = obj.createDocument(document);
			sw = new StringWriter();
			jaxbMarshaller.marshal(jaxbElement, sw);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(sw.toString());

///return document 
		// return sw != null ? sw.toString() : "";
		RestTemplate restTemplate = new RestTemplate();
		
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		
		List<HttpMessageConverter<?>> messageConverters1 = new ArrayList<>();
		messageConverters1.add(new StringHttpMessageConverter());
		restTemplate.setMessageConverters(messageConverters1);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> request = new HttpEntity<String>(sw.toString(), headers);

		// Use the headers in the HttpEntity, but without a body since it's a GET request
		HttpEntity<String> request1 = new HttpEntity<>(headers);

		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setProxy(null); // Ensure no proxy is set if not needed
		requestFactory.setConnectTimeout(15000);
		requestFactory.setReadTimeout(15000);
		System.out.println("url is :  " + env.getProperty("Fin.Acct.url"));
		final ResponseEntity<String> response = restTemplate.postForEntity(env.getProperty("Fin.Acct.url"), request,
				String.class);
		System.out.println("Response Body: " + response.getBody());

		// getResponse
		ApiResponse_Entity api = getAccountNumber(response.getBody());
		System.out.println("Response AcctNo: " + api.getDD_Account_id());
		System.out.println("Response Status: " + api.getStatus());
		System.out.println("Response Status: " + api.getErrorMsg());
		return api;
		}

	public ApiResponse_Entity getAccountNumber(String xmlResponse) {
		ApiResponse_Entity response = new ApiResponse_Entity();
	    try {
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document document = builder.parse(new InputSource(new StringReader(xmlResponse)));

	        // Extract HostTransaction status
	        NodeList hostTransactionList = document.getElementsByTagName("HostTransaction");
	        if (hostTransactionList.getLength() > 0) {
	            Element hostTransactionElement = (Element) hostTransactionList.item(0);
	            String hostTransactionStatus = getElementValue(hostTransactionElement, "Status");
	            response.setStatus(hostTransactionStatus);
	        }

	        // Extract AccountNumber from executeFinacleScript_CustomData
	        NodeList customDataList = document.getElementsByTagName("executeFinacleScript_CustomData");
	        if (customDataList.getLength() > 0) {
	            Element customDataElement = (Element) customDataList.item(0);
	            // Correct tag to "AccountNumber"
	            String accountNumber = getElementValue(customDataElement, "AccountNumber");
	            String errorMsg = getElementValue(customDataElement, "ErrorMsg");
	            if (accountNumber != null && !accountNumber.isEmpty()) {
	                response.setDD_Account_id(accountNumber);
	            } else {
	                response.setErrorMsg(errorMsg);
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return response;
	}

	private static String getElementValue(Element parentElement, String childTagName) {
	    NodeList nodeList = parentElement.getElementsByTagName(childTagName);
	    if (nodeList.getLength() > 0) {
	        return nodeList.item(0).getTextContent();
	    }
	    return null;
	}

	/*
	 * public String sendXmlToApi() { System.out.println("entered method"); String
	 * xmlContent = getFixmlDoc_account();
	 * 
	 * System.out.println( xmlContent); String url =
	 * "https://bfifdn2pweb01.bfi.com:11100/FISERVLET/fihttp";
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setContentType(MediaType.APPLICATION_XML); HttpEntity<String> request
	 * = new HttpEntity<>(xmlContent, headers);
	 * 
	 * String response = restTemplate.exchange(url, HttpMethod.POST, request,
	 * String.class).getBody();
	 * 
	 * return response; }
	 */

}

