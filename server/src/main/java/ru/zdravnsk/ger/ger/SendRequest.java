
package ru.zdravnsk.ger.ger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SendRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SendRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://ger.zdravnsk.ru/ger/SendRequest}id_person" minOccurs="0"/&gt;
 *         &lt;element ref="{http://ger.zdravnsk.ru/ger/SendRequest}surname" minOccurs="0"/&gt;
 *         &lt;element ref="{http://ger.zdravnsk.ru/ger/SendRequest}name" minOccurs="0"/&gt;
 *         &lt;element ref="{http://ger.zdravnsk.ru/ger/SendRequest}middle_name" minOccurs="0"/&gt;
 *         &lt;element ref="{http://ger.zdravnsk.ru/ger/SendRequest}date_birth" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SendRequest", propOrder = {
    "idPerson",
    "surname",
    "name",
    "middleName",
    "dateBirth"
})
public class SendRequest {

    @XmlElement(name = "id_person", namespace = "http://ger.zdravnsk.ru/ger/SendRequest")
    protected String idPerson;
    @XmlElement(namespace = "http://ger.zdravnsk.ru/ger/SendRequest")
    protected String surname;
    @XmlElement(namespace = "http://ger.zdravnsk.ru/ger/SendRequest")
    protected String name;
    @XmlElement(name = "middle_name", namespace = "http://ger.zdravnsk.ru/ger/SendRequest")
    protected String middleName;
    @XmlElement(name = "date_birth", namespace = "http://ger.zdravnsk.ru/ger/SendRequest")
    protected String dateBirth;

    /**
     * Gets the value of the idPerson property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPerson() {
        return idPerson;
    }

    /**
     * Sets the value of the idPerson property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPerson(String value) {
        this.idPerson = value;
    }

    /**
     * Gets the value of the surname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the middleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiddleName(String value) {
        this.middleName = value;
    }

    /**
     * Gets the value of the dateBirth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateBirth() {
        return dateBirth;
    }

    /**
     * Sets the value of the dateBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateBirth(String value) {
        this.dateBirth = value;
    }

}
