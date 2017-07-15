
package ru.zdravnsk.ger.ger.sendresponse.error;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ErrorMessageItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ErrorMessageItem"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="errorMessageItem" type="{http://ger.zdravnsk.ru/ger/SendResponse/Error}ArrayOfString" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErrorMessageItem", propOrder = {
    "errorMessageItem"
})
public class ErrorMessageItem {

    protected ArrayOfString errorMessageItem;

    /**
     * Gets the value of the errorMessageItem property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getErrorMessageItem() {
        return errorMessageItem;
    }

    /**
     * Sets the value of the errorMessageItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setErrorMessageItem(ArrayOfString value) {
        this.errorMessageItem = value;
    }

}
