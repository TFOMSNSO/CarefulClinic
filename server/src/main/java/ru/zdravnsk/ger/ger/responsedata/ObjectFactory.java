
package ru.zdravnsk.ger.ger.responsedata;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.zdravnsk.ger.ger.responsedata package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StartDateEtap1_QNAME = new QName("http://ger.zdravnsk.ru/ger/ResponseData", "start_date_etap1");
    private final static QName _EndDateEtap1_QNAME = new QName("http://ger.zdravnsk.ru/ger/ResponseData", "end_date_etap1");
    private final static QName _StartDateEtap2_QNAME = new QName("http://ger.zdravnsk.ru/ger/ResponseData", "start_date_etap2");
    private final static QName _EndDateEtap2_QNAME = new QName("http://ger.zdravnsk.ru/ger/ResponseData", "end_date_etap2");
    private final static QName _RefIdPerson_QNAME = new QName("http://ger.zdravnsk.ru/ger/ResponseData", "ref_id_person");
    private final static QName _PmGod_QNAME = new QName("http://ger.zdravnsk.ru/ger/ResponseData", "pm_god");
    private final static QName _PmKvartal_QNAME = new QName("http://ger.zdravnsk.ru/ger/ResponseData", "pm_kvartal");
    private final static QName _PMHOSPITALRESULT_QNAME = new QName("http://ger.zdravnsk.ru/ger/ResponseData", "PM_HOSPITAL_RESULT");
    private final static QName _Adress_QNAME = new QName("http://ger.zdravnsk.ru/ger/ResponseData", "adress");
    private final static QName _Tel_QNAME = new QName("http://ger.zdravnsk.ru/ger/ResponseData", "tel");
    private final static QName _PmResult_QNAME = new QName("http://ger.zdravnsk.ru/ger/ResponseData", "pm_result");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.zdravnsk.ger.ger.responsedata
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/ResponseData", name = "start_date_etap1")
    public JAXBElement<String> createStartDateEtap1(String value) {
        return new JAXBElement<String>(_StartDateEtap1_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/ResponseData", name = "end_date_etap1")
    public JAXBElement<String> createEndDateEtap1(String value) {
        return new JAXBElement<String>(_EndDateEtap1_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/ResponseData", name = "start_date_etap2")
    public JAXBElement<String> createStartDateEtap2(String value) {
        return new JAXBElement<String>(_StartDateEtap2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/ResponseData", name = "end_date_etap2")
    public JAXBElement<String> createEndDateEtap2(String value) {
        return new JAXBElement<String>(_EndDateEtap2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/ResponseData", name = "ref_id_person")
    public JAXBElement<String> createRefIdPerson(String value) {
        return new JAXBElement<String>(_RefIdPerson_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/ResponseData", name = "pm_god")
    public JAXBElement<String> createPmGod(String value) {
        return new JAXBElement<String>(_PmGod_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/ResponseData", name = "pm_kvartal")
    public JAXBElement<String> createPmKvartal(String value) {
        return new JAXBElement<String>(_PmKvartal_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/ResponseData", name = "PM_HOSPITAL_RESULT")
    public JAXBElement<String> createPMHOSPITALRESULT(String value) {
        return new JAXBElement<String>(_PMHOSPITALRESULT_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/ResponseData", name = "adress")
    public JAXBElement<String> createAdress(String value) {
        return new JAXBElement<String>(_Adress_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/ResponseData", name = "tel")
    public JAXBElement<String> createTel(String value) {
        return new JAXBElement<String>(_Tel_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/ResponseData", name = "pm_result")
    public JAXBElement<String> createPmResult(String value) {
        return new JAXBElement<String>(_PmResult_QNAME, String.class, null, value);
    }

}
