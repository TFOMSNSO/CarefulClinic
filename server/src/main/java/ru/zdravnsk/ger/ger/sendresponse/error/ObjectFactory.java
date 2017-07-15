
package ru.zdravnsk.ger.ger.sendresponse.error;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.zdravnsk.ger.ger.sendresponse.error package. 
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

    private final static QName _ErrorMessage_QNAME = new QName("http://ger.zdravnsk.ru/ger/SendResponse/Error", "errorMessage");
    private final static QName _ErrorCode_QNAME = new QName("http://ger.zdravnsk.ru/ger/SendResponse/Error", "errorCode");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.zdravnsk.ger.ger.sendresponse.error
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ErrorMessageItem }
     * 
     */
    public ErrorMessageItem createErrorMessageItem() {
        return new ErrorMessageItem();
    }

    /**
     * Create an instance of {@link ArrayOfString }
     * 
     */
    public ArrayOfString createArrayOfString() {
        return new ArrayOfString();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorMessageItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/SendResponse/Error", name = "errorMessage")
    public JAXBElement<ErrorMessageItem> createErrorMessage(ErrorMessageItem value) {
        return new JAXBElement<ErrorMessageItem>(_ErrorMessage_QNAME, ErrorMessageItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/SendResponse/Error", name = "errorCode")
    public JAXBElement<String> createErrorCode(String value) {
        return new JAXBElement<String>(_ErrorCode_QNAME, String.class, null, value);
    }

}
