
package ru.zdravnsk.ger.ger.sendrequest;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.zdravnsk.ger.ger.sendrequest package. 
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

    private final static QName _IdPerson_QNAME = new QName("http://ger.zdravnsk.ru/ger/SendRequest", "id_person");
    private final static QName _Surname_QNAME = new QName("http://ger.zdravnsk.ru/ger/SendRequest", "surname");
    private final static QName _Name_QNAME = new QName("http://ger.zdravnsk.ru/ger/SendRequest", "name");
    private final static QName _MiddleName_QNAME = new QName("http://ger.zdravnsk.ru/ger/SendRequest", "middle_name");
    private final static QName _DateBirth_QNAME = new QName("http://ger.zdravnsk.ru/ger/SendRequest", "date_birth");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.zdravnsk.ger.ger.sendrequest
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/SendRequest", name = "id_person")
    public JAXBElement<String> createIdPerson(String value) {
        return new JAXBElement<String>(_IdPerson_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/SendRequest", name = "surname")
    public JAXBElement<String> createSurname(String value) {
        return new JAXBElement<String>(_Surname_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/SendRequest", name = "name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<String>(_Name_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/SendRequest", name = "middle_name")
    public JAXBElement<String> createMiddleName(String value) {
        return new JAXBElement<String>(_MiddleName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/SendRequest", name = "date_birth")
    public JAXBElement<String> createDateBirth(String value) {
        return new JAXBElement<String>(_DateBirth_QNAME, String.class, null, value);
    }

}
