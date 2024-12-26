package com.bornfire.messagebuilder;




import java.io.Serializable;


import java.io.StringReader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.hibernate.SessionFactory;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.bornfire.config.Listener;
import com.bornfire.transaction_creation.Body_entity;
import com.bornfire.transaction_creation.Documents;
import com.bornfire.transaction_creation.ExecuteFinacleScriptInputVO_entity;
import com.bornfire.transaction_creation.ExecuteFinacleScriptRequest_entity;
import com.bornfire.transaction_creation.ExecuteFinacleScript_CustomData_entity;
import com.bornfire.transaction_creation.FIXML;
import com.bornfire.transaction_creation.Header_entity;
import com.bornfire.transaction_creation.PasswordToken_entity;
import com.bornfire.transaction_creation.RequestHeader_entity;
import com.bornfire.transaction_creation.RequestMessageInfo_entity;
import com.bornfire.transaction_creation.Security_entity;
import com.bornfire.transaction_creation.Token_entity;
import com.bornfire.transaction_creation.TransactionCreationResponse;
import com.bornfire.transaction_creation.msgkey_entity;


@Component
public class DocumentPacks2 implements Serializable
{

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
	public TransactionCreationResponse getFixmlDoc_transaction() throws KeyManagementException, NoSuchAlgorithmException {

		hostVerification();

		FIXML fixmlHdr = new FIXML();

		     // Header Section
		        Header_entity header = new Header_entity();
		        RequestHeader_entity requestHeader = new RequestHeader_entity();
		     // MessageKey List
		        List<msgkey_entity> messageKeyList = new ArrayList<>();
		        msgkey_entity msgkeyentity = new msgkey_entity();

			     Session hs = sessionFactory.getCurrentSession();
					DecimalFormat numformate = new DecimalFormat("000000000000");
						
					BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT API_TRAN_NO.NEXTVAL AS SRL_NO FROM DUAL")
							.getSingleResult();
					String serialno = numformate.format(billNumber);
		        //msgkeyentity.setRequestUUID("Apple"+serialno);
		        msgkeyentity.setRequestUUID("Apple123333123455");
		        msgkeyentity.setServiceRequestId("executeFinacleScript");
		        msgkeyentity.setServiceRequestVersion("10.2");
		        msgkeyentity.setChannelId("COR");
		        msgkeyentity.setLanguageId(""); // Example value
		        messageKeyList.add(msgkeyentity);
		        requestHeader.setMessageKey(messageKeyList);
		        Date date = new Date();

		        // Define the desired date format
		        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		        // Convert the Date object to a String
		        String dateString = formatter.format(date);
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
		        security.setFICertToken("");// Example value
		        security.setRealUserLoginSessionId("");// Example value
		        security.setRealUser("");// Example value
		        security.setRealUserPwd("");// Example value
		        security.setSSOTransferToken("");// Example value
		        
		        securityList.add(security);
		        requestHeader.setSecurity(securityList);

		     // Set RequestHeader fields
		     requestHeader.setMessageKey(messageKeyList);
		     requestHeader.setRequestMessageInfo(requestMessageInfoList);
		     requestHeader.setSecurity(securityList);
		     header.setRequestHeader(requestHeader);
		     fixmlHdr.setHeaDer(header);

		     // Body Section
		     Body_entity body = new Body_entity();
		     ExecuteFinacleScriptRequest_entity executeFinacleScriptRequest = new ExecuteFinacleScriptRequest_entity();
		     List<ExecuteFinacleScriptInputVO_entity> executeFinacleScriptInputVOList = new ArrayList<>();
		     ExecuteFinacleScriptInputVO_entity executeFinacleScriptInputVO = new ExecuteFinacleScriptInputVO_entity();
		     
		     executeFinacleScriptInputVO.setRequestId("bornfireBumTran.scr");
		     List<ExecuteFinacleScript_CustomData_entity> executeFinacleScriptCustomDataList = new ArrayList<>();
		     ExecuteFinacleScript_CustomData_entity executeFinacleScriptCustomData = new ExecuteFinacleScript_CustomData_entity();
		     executeFinacleScriptCustomData.setCreditAcct("55500001165");
		     executeFinacleScriptCustomData.setBebitAcct("55500001040");
		     executeFinacleScriptCustomData.setTranAmt("5000");
		     executeFinacleScriptCustomData.setRateCode("NOR");
		     executeFinacleScriptInputVOList.add(executeFinacleScriptInputVO);
		     executeFinacleScriptCustomDataList.add(executeFinacleScriptCustomData);
		     
		     // Set Body fields
		     executeFinacleScriptRequest.setExecuteFinacleScriptInputVO(executeFinacleScriptInputVOList);
		     executeFinacleScriptRequest.setExecuteFinacleScriptCustomData(executeFinacleScriptCustomDataList);
		     body.setExecuteFinacleScriptRequest(executeFinacleScriptRequest);
		     fixmlHdr.setBoDy(body);

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
	    	  //jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
		      jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			com.bornfire.transaction_creation.ObjectFactory obj = new com.bornfire.transaction_creation.ObjectFactory();
			JAXBElement<FIXML> jaxbElement =obj.createDocument(document);
			sw = new StringWriter();
			jaxbMarshaller.marshal(jaxbElement, sw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		  System.out.println(sw.toString());
		 
///return document 
		//return sw.toString();

		    RestTemplate restTemplate = new RestTemplate();
		    List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		    messageConverters.add(new StringHttpMessageConverter());
		    restTemplate.setMessageConverters(messageConverters);

		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_XML);

		    // Ensure `sw` (StringWriter) is properly initialized and converted to string
		    String requestPayload = sw.toString(); 
		    HttpEntity<String> request = new HttpEntity<>(requestPayload, headers);

		    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		    requestFactory.setConnectTimeout(15000); // 15 seconds
		    requestFactory.setReadTimeout(15000); // 15 seconds
		    restTemplate.setRequestFactory(requestFactory);

		    TransactionCreationResponse apiResponse = new TransactionCreationResponse();
		    
		    try {
		        // Retrieve and check the URL using the correct property key
		        String urlString = env.getProperty("Fin.Tran.url");  // Changed from "Fin.Tran.Url" to "Fin.Tran.url"
		        
		        if (urlString == null || urlString.trim().isEmpty()) {
		            throw new IllegalArgumentException("The 'Fin.Tran.url' property is not set or is empty.");
		        }

		        // Ensure the URI is valid and absolute
		        URI url = new URI(urlString);

		        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		        System.out.println("Response Body: " + response.getBody());

		        // Extract response data
		        apiResponse = getTransactionResponse(response.getBody());

		    } catch (URISyntaxException e) {
		        System.err.println("The URL provided is not a valid absolute URI: " + e.getMessage());
		    } catch (HttpClientErrorException | HttpServerErrorException e) {
		        // Handle HTTP errors (4xx and 5xx)
		        System.err.println("HTTP Status Code: " + e.getStatusCode());
		        System.err.println("HTTP Response Body: " + e.getResponseBodyAsString());
		    } catch (ResourceAccessException e) {
		        // Handle timeout or connection errors
		        System.err.println("Resource access error: " + e.getMessage());
		    } catch (RestClientException e) {
		        // General catch for other RestTemplate exceptions
		        System.err.println("Error occurred during REST call: " + e.getMessage());
		    } catch (Exception unknown) {
		        // Handle any other exceptions
		        System.err.println("An unexpected error occurred: " + unknown.getMessage());
		        unknown.printStackTrace();
		    }

		    System.out.println("Response Tran No: " + apiResponse.getTranId());
		    System.out.println("Response Status: " + apiResponse.getStatus());

		    return apiResponse;
		}

		public TransactionCreationResponse getTransactionResponse(String xmlResponse) {
		    TransactionCreationResponse response = new TransactionCreationResponse();
		    try {
		        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder builder = factory.newDocumentBuilder();
		        Document document = builder.parse(new InputSource(new StringReader(xmlResponse)));

		        NodeList hostTransactionList = document.getElementsByTagName("HostTransaction");
		        if (hostTransactionList.getLength() > 0) {
		            Element hostTransactionElement = (Element) hostTransactionList.item(0);
		            String hostTransactionStatus = getElementValue(hostTransactionElement, "Status");
		            response.setStatus(hostTransactionStatus);
		        }

		        NodeList customDataList = document.getElementsByTagName("executeFinacleScript_CustomData");
		        if (customDataList.getLength() > 0) {
		            Element customDataElement = (Element) customDataList.item(0);
		            String tranId = getElementValue(customDataElement, "Tran_Id");
		            response.setTranId(tranId);
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
	 * public String sendXmlToApi() { String xmlContent = getFixmlDoc_transaction();
	 * 
	 * String url = "https://bfifdn2pweb01.bfi.com:11100/FISERVLET/fihttp";
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
