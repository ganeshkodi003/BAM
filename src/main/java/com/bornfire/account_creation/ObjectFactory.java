package com.bornfire.account_creation;
//

//This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
//See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
//Any modifications to this file will be lost upon recompilation of the source schema. 
//Generated on: 2020.01.27 at 05:26:07 PM IST 
//

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

	private final static QName _Document_QNAME = new QName("",
			"FIXML");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema
	 * derived classes for package: com.bornfire.jaxb.pacs_008_001_08
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link Documents }
	 * 
	 */
	public Documents createDocument() {
		return new Documents();
	}
	/*
	 *//**
		 * Create an instance of {@link AccountIdentification4Choice1 }
		 * 
		 */
	/*
	 * public AccountIdentification4Choice1 createAccountIdentification4Choice1() {
	 * return new AccountIdentification4Choice1(); }
	 * 
	 *//**
		 * Create an instance of {@link ActiveCurrencyAndAmount }
		 * 
		 */
	/*
	 * public ActiveCurrencyAndAmount createActiveCurrencyAndAmount() { return new
	 * ActiveCurrencyAndAmount(); }
	 * 
	 * 
	 *//**
		 * Create an instance of {@link BranchAndFinancialInstitutionIdentification61 }
		 * 
		 */
	/*
	 * public BranchAndFinancialInstitutionIdentification61
	 * createBranchAndFinancialInstitutionIdentification61() { return new
	 * BranchAndFinancialInstitutionIdentification61(); }
	 * 
	 *//**
		 * Create an instance of {@link CashAccount381 }
		 * 
		 */
	/*
	 * public CashAccount381 createCashAccount381() { return new CashAccount381(); }
	 * 
	 * 
	 *//**
		 * Create an instance of {@link CategoryPurpose1Choice1 }
		 * 
		 */
	/*
	 * public CategoryPurpose1Choice1 createCategoryPurpose1Choice1() { return new
	 * CategoryPurpose1Choice1(); }
	 * 
	 * 
	 *//**
		 * Create an instance of {@link CreditTransferTransaction391 }
		 * 
		 */
	/*
	 * public PaymentTransaction1051 createPaymentTransaction1051() { return new
	 * PaymentTransaction1051(); }
	 * 
	 *//**
		 * Create an instance of {@link FIToFICustomerCreditTransferV08 }
		 * 
		 */
	/*
	 * public PaymentReturnV07 createPaymentReturnV07() { return new
	 * PaymentReturnV07(); }
	 * 
	 * 
	 *//**
		 * Create an instance of {@link FinancialInstitutionIdentification181 }
		 * 
		 */
	/*
	 * public FinancialInstitutionIdentification181
	 * createFinancialInstitutionIdentification181() { return new
	 * FinancialInstitutionIdentification181(); }
	 * 
	 *//**
		 * Create an instance of {@link GenericAccountIdentification11 }
		 * 
		 */
	/*
	 * public GenericAccountIdentification11 createGenericAccountIdentification11()
	 * { return new GenericAccountIdentification11(); }
	 * 
	 * 
	 * 
	 *//**
		 * Create an instance of {@link GroupHeader931 }
		 * 
		 */
	/*
	 * public GroupHeader931 createGroupHeader931() { return new GroupHeader931(); }
	 * 
	 * 
	 * 
	 * 
	 *//**
		 * Create an instance of {@link LocalInstrument2Choice1 }
		 * 
		 */
	/*
	 * public LocalInstrument2Choice1 createLocalInstrument2Choice1() { return new
	 * LocalInstrument2Choice1(); }
	 * 
	 * 
	 * 
	 *//**
		 * Create an instance of {@link PartyIdentification1351 }
		 * 
		 */
	/*
	 * public PartyIdentification1351 createPartyIdentification1351() { return new
	 * PartyIdentification1351(); }
	 * 
	 *//**
		 * Create an instance of {@link PaymentIdentification71 }
		 * 
		 */
	/*
	 * public OriginalGroupHeader171 createOriginalGroupHeader171() { return new
	 * OriginalGroupHeader171(); }
	 * 
	 *//**
		 * Create an instance of {@link PaymentTypeInformation271 }
		 * 
		 */
	/*
	 * public PaymentTypeInformation271 createPaymentTypeInformation271() { return
	 * new PaymentTypeInformation271(); }
	 * 
	 * 
	 *//**
		 * Create an instance of {@link ServiceLevel8Choice1 }
		 * 
		 */
	/*
	 * public ServiceLevel8Choice1 createServiceLevel8Choice1() { return new
	 * ServiceLevel8Choice1(); }
	 * 
	 *//**
		 * Create an instance of {@link SettlementInstruction71 }
		 * 
		 *//*
			 * public SettlementInstruction71 createSettlementInstruction71() { return new
			 * SettlementInstruction71(); }
			 * 
			 */

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Documents }{@code >}
	 * 
	 * @param value Java instance representing xml element's value.
	 * @return the new instance of {@link JAXBElement }{@code <}{@link Documents
	 *         }{@code >}
	 */
	@XmlElementDecl(namespace = "urn:iso:std:iso:20022:tech:xsd:Fixml", name = "FIXML")
	public JAXBElement<FIXML> createDocument(FIXML value) {
		return new JAXBElement<FIXML>(_Document_QNAME, FIXML.class, null, value);
	}

}