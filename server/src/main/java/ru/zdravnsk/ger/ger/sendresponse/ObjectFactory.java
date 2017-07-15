
package ru.zdravnsk.ger.ger.sendresponse;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import ru.zdravnsk.ger.ger.Error;
import ru.zdravnsk.ger.ger.ResponseData;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.zdravnsk.ger.ger.sendresponse package. 
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

    private final static QName _Data_QNAME = new QName("http://ger.zdravnsk.ru/ger/SendResponse", "data");
    private final static QName _Error_QNAME = new QName("http://ger.zdravnsk.ru/ger/SendResponse", "error");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.zdravnsk.ger.ger.sendresponse
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/SendResponse", name = "data")
    public JAXBElement<ResponseData> createData(ResponseData value) {
        return new JAXBElement<ResponseData>(_Data_QNAME, ResponseData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Error }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ger.zdravnsk.ru/ger/SendResponse", name = "error")
    public JAXBElement<Error> createError(Error value) {
        return new JAXBElement<Error>(_Error_QNAME, Error.class, null, value);
    }

}
