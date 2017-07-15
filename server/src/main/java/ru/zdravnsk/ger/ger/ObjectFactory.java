
package ru.zdravnsk.ger.ger;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.zdravnsk.ger.ger package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.zdravnsk.ger.ger
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPersonData }
     * 
     */
    public GetPersonData createGetPersonData() {
        return new GetPersonData();
    }

    /**
     * Create an instance of {@link SendRequest }
     * 
     */
    public SendRequest createSendRequest() {
        return new SendRequest();
    }

    /**
     * Create an instance of {@link GetPersonDataResponse }
     * 
     */
    public GetPersonDataResponse createGetPersonDataResponse() {
        return new GetPersonDataResponse();
    }

    /**
     * Create an instance of {@link SendResponse }
     * 
     */
    public SendResponse createSendResponse() {
        return new SendResponse();
    }

    /**
     * Create an instance of {@link ResponseData }
     * 
     */
    public ResponseData createResponseData() {
        return new ResponseData();
    }

    /**
     * Create an instance of {@link Error }
     * 
     */
    public Error createError() {
        return new Error();
    }

}
