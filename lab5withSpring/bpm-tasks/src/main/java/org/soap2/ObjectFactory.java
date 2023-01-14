
package org.soap2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.soap2 package. 
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

    private final static QName _ReserveFurnitureResponse_QNAME = new QName("http://asu.dgtu.donetsk.ua/ex/students", "ReserveFurnitureResponse");
    private final static QName _ReserveFurniture_QNAME = new QName("http://asu.dgtu.donetsk.ua/ex/students", "ReserveFurniture");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.soap2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReserveFurniture }
     * 
     */
    public ReserveFurniture createReserveFurniture() {
        return new ReserveFurniture();
    }

    /**
     * Create an instance of {@link ReserveFurnitureResponse }
     * 
     */
    public ReserveFurnitureResponse createReserveFurnitureResponse() {
        return new ReserveFurnitureResponse();
    }

    /**
     * Create an instance of {@link UserReserve }
     * 
     */
    public UserReserve createUserReserve() {
        return new UserReserve();
    }

    /**
     * Create an instance of {@link Furniture }
     * 
     */
    public Furniture createFurniture() {
        return new Furniture();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReserveFurnitureResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://asu.dgtu.donetsk.ua/ex/students", name = "ReserveFurnitureResponse")
    public JAXBElement<ReserveFurnitureResponse> createReserveFurnitureResponse(ReserveFurnitureResponse value) {
        return new JAXBElement<ReserveFurnitureResponse>(_ReserveFurnitureResponse_QNAME, ReserveFurnitureResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReserveFurniture }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://asu.dgtu.donetsk.ua/ex/students", name = "ReserveFurniture")
    public JAXBElement<ReserveFurniture> createReserveFurniture(ReserveFurniture value) {
        return new JAXBElement<ReserveFurniture>(_ReserveFurniture_QNAME, ReserveFurniture.class, null, value);
    }

}
