
package ru.zdravnsk.ger.ger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://ger.zdravnsk.ru/ger/ResponseData}start_date_etap1" minOccurs="0"/&gt;
 *         &lt;element ref="{http://ger.zdravnsk.ru/ger/ResponseData}end_date_etap1" minOccurs="0"/&gt;
 *         &lt;element ref="{http://ger.zdravnsk.ru/ger/ResponseData}start_date_etap2" minOccurs="0"/&gt;
 *         &lt;element ref="{http://ger.zdravnsk.ru/ger/ResponseData}end_date_etap2" minOccurs="0"/&gt;
 *         &lt;element ref="{http://ger.zdravnsk.ru/ger/ResponseData}ref_id_person" minOccurs="0"/&gt;
 *         &lt;element ref="{http://ger.zdravnsk.ru/ger/ResponseData}pm_god" minOccurs="0"/&gt;
 *         &lt;element ref="{http://ger.zdravnsk.ru/ger/ResponseData}pm_kvartal" minOccurs="0"/&gt;
 *         &lt;element ref="{http://ger.zdravnsk.ru/ger/ResponseData}PM_HOSPITAL_RESULT" minOccurs="0"/&gt;
 *         &lt;element ref="{http://ger.zdravnsk.ru/ger/ResponseData}adress" minOccurs="0"/&gt;
 *         &lt;element ref="{http://ger.zdravnsk.ru/ger/ResponseData}tel" minOccurs="0"/&gt;
 *         &lt;element ref="{http://ger.zdravnsk.ru/ger/ResponseData}pm_result" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseData", propOrder = {
    "startDateEtap1",
    "endDateEtap1",
    "startDateEtap2",
    "endDateEtap2",
    "refIdPerson",
    "pmGod",
    "pmKvartal",
    "pmhospitalresult",
    "adress",
    "tel",
    "pmResult"
})
public class ResponseData {

    @XmlElement(name = "start_date_etap1", namespace = "http://ger.zdravnsk.ru/ger/ResponseData")
    protected String startDateEtap1;
    @XmlElement(name = "end_date_etap1", namespace = "http://ger.zdravnsk.ru/ger/ResponseData")
    protected String endDateEtap1;
    @XmlElement(name = "start_date_etap2", namespace = "http://ger.zdravnsk.ru/ger/ResponseData")
    protected String startDateEtap2;
    @XmlElement(name = "end_date_etap2", namespace = "http://ger.zdravnsk.ru/ger/ResponseData")
    protected String endDateEtap2;
    @XmlElement(name = "ref_id_person", namespace = "http://ger.zdravnsk.ru/ger/ResponseData")
    protected String refIdPerson;
    @XmlElement(name = "pm_god", namespace = "http://ger.zdravnsk.ru/ger/ResponseData")
    protected String pmGod;
    @XmlElement(name = "pm_kvartal", namespace = "http://ger.zdravnsk.ru/ger/ResponseData")
    protected String pmKvartal;
    @XmlElement(name = "PM_HOSPITAL_RESULT", namespace = "http://ger.zdravnsk.ru/ger/ResponseData")
    protected String pmhospitalresult;
    @XmlElement(namespace = "http://ger.zdravnsk.ru/ger/ResponseData")
    protected String adress;
    @XmlElement(namespace = "http://ger.zdravnsk.ru/ger/ResponseData")
    protected String tel;
    @XmlElement(name = "pm_result", namespace = "http://ger.zdravnsk.ru/ger/ResponseData")
    protected String pmResult;

    /**
     * Gets the value of the startDateEtap1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartDateEtap1() {
        return startDateEtap1;
    }

    /**
     * Sets the value of the startDateEtap1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDateEtap1(String value) {
        this.startDateEtap1 = value;
    }

    /**
     * Gets the value of the endDateEtap1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDateEtap1() {
        return endDateEtap1;
    }

    /**
     * Sets the value of the endDateEtap1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDateEtap1(String value) {
        this.endDateEtap1 = value;
    }

    /**
     * Gets the value of the startDateEtap2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartDateEtap2() {
        return startDateEtap2;
    }

    /**
     * Sets the value of the startDateEtap2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDateEtap2(String value) {
        this.startDateEtap2 = value;
    }

    /**
     * Gets the value of the endDateEtap2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDateEtap2() {
        return endDateEtap2;
    }

    /**
     * Sets the value of the endDateEtap2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDateEtap2(String value) {
        this.endDateEtap2 = value;
    }

    /**
     * Gets the value of the refIdPerson property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefIdPerson() {
        return refIdPerson;
    }

    /**
     * Sets the value of the refIdPerson property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefIdPerson(String value) {
        this.refIdPerson = value;
    }

    /**
     * Gets the value of the pmGod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPmGod() {
        return pmGod;
    }

    /**
     * Sets the value of the pmGod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPmGod(String value) {
        this.pmGod = value;
    }

    /**
     * Gets the value of the pmKvartal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPmKvartal() {
        return pmKvartal;
    }

    /**
     * Sets the value of the pmKvartal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPmKvartal(String value) {
        this.pmKvartal = value;
    }

    /**
     * Gets the value of the pmhospitalresult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPMHOSPITALRESULT() {
        return pmhospitalresult;
    }

    /**
     * Sets the value of the pmhospitalresult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPMHOSPITALRESULT(String value) {
        this.pmhospitalresult = value;
    }

    /**
     * Gets the value of the adress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdress() {
        return adress;
    }

    /**
     * Sets the value of the adress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdress(String value) {
        this.adress = value;
    }

    /**
     * Gets the value of the tel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTel() {
        return tel;
    }

    /**
     * Sets the value of the tel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTel(String value) {
        this.tel = value;
    }

    /**
     * Gets the value of the pmResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPmResult() {
        return pmResult;
    }

    /**
     * Sets the value of the pmResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPmResult(String value) {
        this.pmResult = value;
    }

	@Override
	public String toString() {
		return "ResponseData [startDateEtap1=" + startDateEtap1 + ", endDateEtap1=" + endDateEtap1 + ", startDateEtap2="
				+ startDateEtap2 + ", endDateEtap2=" + endDateEtap2 + ", refIdPerson=" + refIdPerson + ", pmGod="
				+ pmGod + ", pmKvartal=" + pmKvartal + ", pmhospitalresult=" + pmhospitalresult + ", adress=" + adress
				+ ", tel=" + tel + ", pmResult=" + pmResult + "]";
	}

}
