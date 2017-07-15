
package ru.zdravnsk.ger.ger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ru.zdravnsk.ger.ger.sendresponse.error.ErrorMessageItem;


/**
 * <p>Java class for Error complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Error"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://ger.zdravnsk.ru/ger/SendResponse/Error}errorMessage" minOccurs="0"/&gt;
 *         &lt;element ref="{http://ger.zdravnsk.ru/ger/SendResponse/Error}errorCode" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Error", propOrder = {
    "errorMessage",
    "errorCode"
})
public class Error {

    @XmlElement(namespace = "http://ger.zdravnsk.ru/ger/SendResponse/Error")
    protected ErrorMessageItem errorMessage;
    @XmlElement(namespace = "http://ger.zdravnsk.ru/ger/SendResponse/Error")
    protected String errorCode;

    /**
     * Gets the value of the errorMessage property.
     * 
     * @return
     *     possible object is
     *     {@link ErrorMessageItem }
     *     
     */
    public ErrorMessageItem getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the value of the errorMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorMessageItem }
     *     
     */
    public void setErrorMessage(ErrorMessageItem value) {
        this.errorMessage = value;
    }

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

}
