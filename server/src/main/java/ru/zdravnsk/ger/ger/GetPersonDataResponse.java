
package ru.zdravnsk.ger.ger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getPersonDataResult" type="{http://ger.zdravnsk.ru/ger}SendResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getPersonDataResult"
})
@XmlRootElement(name = "getPersonDataResponse")
public class GetPersonDataResponse {

    protected SendResponse getPersonDataResult;

    /**
     * Gets the value of the getPersonDataResult property.
     * 
     * @return
     *     possible object is
     *     {@link SendResponse }
     *     
     */
    public SendResponse getGetPersonDataResult() {
        return getPersonDataResult;
    }

    /**
     * Sets the value of the getPersonDataResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SendResponse }
     *     
     */
    public void setGetPersonDataResult(SendResponse value) {
        this.getPersonDataResult = value;
    }

}
