package com.bornfire.transaction_creation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FIXML", propOrder = {
    "heaDer",
    "boDy"
    
})
public class FIXML {


	 @XmlAttribute(name = "xsi:schemaLocation")
	    private String schemaLocation = "http://www.finacle.com/fixml executeFinacleScript.xsd";

	    @XmlAttribute(name = "xmlns")
	    private String xmlns = "http://www.finacle.com/fixml";

	    @XmlAttribute(name = "xmlns:xsi")
	    private String xmlnsXsi = "http://www.w3.org/2001/XMLSchema-instance";
    @XmlElement(name = "Header", required = true)
    protected Header_entity heaDer;

    @XmlElement(name = "Body", required = true)
    protected Body_entity boDy;

	public Header_entity getHeaDer() {
		return heaDer;
	}

	public void setHeaDer(Header_entity heaDer) {
		this.heaDer = heaDer;
	}

	public Body_entity getBoDy() {
		return boDy;
	}

	public void setBoDy(Body_entity boDy) {
		this.boDy = boDy;
	}

	@Override
	public String toString() {
		return "FIXML_entity [schemaLocation=" + schemaLocation + ", xmlns=" + xmlns + ", xmlnsXsi=" + xmlnsXsi
				+ ", heaDer=" + heaDer + ", boDy=" + boDy + "]";
	}

	    
}
