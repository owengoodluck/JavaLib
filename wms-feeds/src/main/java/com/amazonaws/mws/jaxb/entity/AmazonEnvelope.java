//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.29 at 10:21:45 PM CST 
//


package com.amazonaws.mws.jaxb.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Header"/>
 *         &lt;element name="MessageType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="FulfillmentCenter"/>
 *               &lt;enumeration value="Inventory"/>
 *               &lt;enumeration value="Listings"/>
 *               &lt;enumeration value="OrderAcknowledgement"/>
 *               &lt;enumeration value="OrderAdjustment"/>
 *               &lt;enumeration value="OrderFulfillment"/>
 *               &lt;enumeration value="Override"/>
 *               &lt;enumeration value="Price"/>
 *               &lt;enumeration value="ProcessingReport"/>
 *               &lt;enumeration value="Product"/>
 *               &lt;enumeration value="ProductImage"/>
 *               &lt;enumeration value="Relationship"/>
 *               &lt;enumeration value="SettlementReport"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{}MarketplaceName" minOccurs="0"/>
 *         &lt;element name="PurgeAndReplace" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="EffectiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Message" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="MessageID" type="{}IDNumber"/>
 *                   &lt;element name="OperationType" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;enumeration value="Update"/>
 *                         &lt;enumeration value="Delete"/>
 *                         &lt;enumeration value="PartialUpdate"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;choice>
 *                     &lt;element ref="{}FulfillmentCenter"/>
 *                     &lt;element ref="{}Inventory"/>
 *                     &lt;element ref="{}Listings"/>
 *                     &lt;element ref="{}OrderAcknowledgement"/>
 *                     &lt;element ref="{}OrderAdjustment"/>
 *                     &lt;element ref="{}OrderFulfillment"/>
 *                     &lt;element ref="{}Override"/>
 *                     &lt;element ref="{}Price"/>
 *                     &lt;element ref="{}ProcessingReport"/>
 *                     &lt;element ref="{}Product"/>
 *                     &lt;element ref="{}ProductImage"/>
 *                     &lt;element ref="{}Relationship"/>
 *                     &lt;element ref="{}SettlementReport"/>
 *                   &lt;/choice>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "header",
    "messageType",
    "marketplaceName",
    "purgeAndReplace",
    "effectiveDate",
    "message"
})
@XmlRootElement(name = "AmazonEnvelope")
public class AmazonEnvelope {

    @XmlElement(name = "Header", required = true)
    protected Header header;
    @XmlElement(name = "MessageType", required = true)
    protected String messageType;
    @XmlElement(name = "MarketplaceName")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String marketplaceName;
    @XmlElement(name = "PurgeAndReplace")
    protected Boolean purgeAndReplace;
    @XmlElement(name = "EffectiveDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar effectiveDate;
    @XmlElement(name = "Message", required = true)
    protected List<AmazonEnvelope.Message> message;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link Header }
     *     
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link Header }
     *     
     */
    public void setHeader(Header value) {
        this.header = value;
    }

    /**
     * Gets the value of the messageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * Sets the value of the messageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageType(String value) {
        this.messageType = value;
    }

    /**
     * 
     *                                                 The MarketplaceName is only supported for
     *                                                 Override feeds.
     *                                                 If included here, the MarketplaceName will
     *                                                 apply to all messages in the feed.
     *                                                 
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarketplaceName() {
        return marketplaceName;
    }

    /**
     * Sets the value of the marketplaceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketplaceName(String value) {
        this.marketplaceName = value;
    }

    /**
     * Gets the value of the purgeAndReplace property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPurgeAndReplace() {
        return purgeAndReplace;
    }

    /**
     * Sets the value of the purgeAndReplace property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPurgeAndReplace(Boolean value) {
        this.purgeAndReplace = value;
    }

    /**
     * Gets the value of the effectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the value of the effectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEffectiveDate(XMLGregorianCalendar value) {
        this.effectiveDate = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the message property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AmazonEnvelope.Message }
     * 
     * 
     */
    public List<AmazonEnvelope.Message> getMessage() {
        if (message == null) {
            message = new ArrayList<AmazonEnvelope.Message>();
        }
        return this.message;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="MessageID" type="{}IDNumber"/>
     *         &lt;element name="OperationType" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;enumeration value="Update"/>
     *               &lt;enumeration value="Delete"/>
     *               &lt;enumeration value="PartialUpdate"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;choice>
     *           &lt;element ref="{}FulfillmentCenter"/>
     *           &lt;element ref="{}Inventory"/>
     *           &lt;element ref="{}Listings"/>
     *           &lt;element ref="{}OrderAcknowledgement"/>
     *           &lt;element ref="{}OrderAdjustment"/>
     *           &lt;element ref="{}OrderFulfillment"/>
     *           &lt;element ref="{}Override"/>
     *           &lt;element ref="{}Price"/>
     *           &lt;element ref="{}ProcessingReport"/>
     *           &lt;element ref="{}Product"/>
     *           &lt;element ref="{}ProductImage"/>
     *           &lt;element ref="{}Relationship"/>
     *           &lt;element ref="{}SettlementReport"/>
     *         &lt;/choice>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "messageID",
        "operationType",
        "fulfillmentCenter",
        "inventory",
        "listings",
        "orderAcknowledgement",
        "orderAdjustment",
        "orderFulfillment",
        "override",
        "price",
        "processingReport",
        "product",
        "productImage",
        "relationship",
        "settlementReport"
    })
    public static class Message {

        @XmlElement(name = "MessageID", required = true)
        protected BigInteger messageID;
        @XmlElement(name = "OperationType")
        protected String operationType;
        @XmlElement(name = "FulfillmentCenter")
        protected FulfillmentCenter fulfillmentCenter;
        @XmlElement(name = "Inventory")
        protected Inventory inventory;
        @XmlElement(name = "Listings")
        protected Listings listings;
        @XmlElement(name = "OrderAcknowledgement")
        protected OrderAcknowledgement orderAcknowledgement;
        @XmlElement(name = "OrderAdjustment")
        protected OrderAdjustment orderAdjustment;
        @XmlElement(name = "OrderFulfillment")
        protected OrderFulfillment orderFulfillment;
        @XmlElement(name = "Override")
        protected Override override;
        @XmlElement(name = "Price")
        protected Price price;
        @XmlElement(name = "ProcessingReport")
        protected ProcessingReport processingReport;
        @XmlElement(name = "Product")
        protected Product product;
        @XmlElement(name = "ProductImage")
        protected ProductImage productImage;
        @XmlElement(name = "Relationship")
        protected Relationship relationship;
        @XmlElement(name = "SettlementReport")
        protected SettlementReport settlementReport;

        /**
         * Gets the value of the messageID property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getMessageID() {
            return messageID;
        }

        /**
         * Sets the value of the messageID property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setMessageID(BigInteger value) {
            this.messageID = value;
        }

        /**
         * Gets the value of the operationType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOperationType() {
            return operationType;
        }

        /**
         * Sets the value of the operationType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOperationType(String value) {
            this.operationType = value;
        }

        /**
         * Gets the value of the fulfillmentCenter property.
         * 
         * @return
         *     possible object is
         *     {@link FulfillmentCenter }
         *     
         */
        public FulfillmentCenter getFulfillmentCenter() {
            return fulfillmentCenter;
        }

        /**
         * Sets the value of the fulfillmentCenter property.
         * 
         * @param value
         *     allowed object is
         *     {@link FulfillmentCenter }
         *     
         */
        public void setFulfillmentCenter(FulfillmentCenter value) {
            this.fulfillmentCenter = value;
        }

        /**
         * Gets the value of the inventory property.
         * 
         * @return
         *     possible object is
         *     {@link Inventory }
         *     
         */
        public Inventory getInventory() {
            return inventory;
        }

        /**
         * Sets the value of the inventory property.
         * 
         * @param value
         *     allowed object is
         *     {@link Inventory }
         *     
         */
        public void setInventory(Inventory value) {
            this.inventory = value;
        }

        /**
         * Gets the value of the listings property.
         * 
         * @return
         *     possible object is
         *     {@link Listings }
         *     
         */
        public Listings getListings() {
            return listings;
        }

        /**
         * Sets the value of the listings property.
         * 
         * @param value
         *     allowed object is
         *     {@link Listings }
         *     
         */
        public void setListings(Listings value) {
            this.listings = value;
        }

        /**
         * Gets the value of the orderAcknowledgement property.
         * 
         * @return
         *     possible object is
         *     {@link OrderAcknowledgement }
         *     
         */
        public OrderAcknowledgement getOrderAcknowledgement() {
            return orderAcknowledgement;
        }

        /**
         * Sets the value of the orderAcknowledgement property.
         * 
         * @param value
         *     allowed object is
         *     {@link OrderAcknowledgement }
         *     
         */
        public void setOrderAcknowledgement(OrderAcknowledgement value) {
            this.orderAcknowledgement = value;
        }

        /**
         * Gets the value of the orderAdjustment property.
         * 
         * @return
         *     possible object is
         *     {@link OrderAdjustment }
         *     
         */
        public OrderAdjustment getOrderAdjustment() {
            return orderAdjustment;
        }

        /**
         * Sets the value of the orderAdjustment property.
         * 
         * @param value
         *     allowed object is
         *     {@link OrderAdjustment }
         *     
         */
        public void setOrderAdjustment(OrderAdjustment value) {
            this.orderAdjustment = value;
        }

        /**
         * Gets the value of the orderFulfillment property.
         * 
         * @return
         *     possible object is
         *     {@link OrderFulfillment }
         *     
         */
        public OrderFulfillment getOrderFulfillment() {
            return orderFulfillment;
        }

        /**
         * Sets the value of the orderFulfillment property.
         * 
         * @param value
         *     allowed object is
         *     {@link OrderFulfillment }
         *     
         */
        public void setOrderFulfillment(OrderFulfillment value) {
            this.orderFulfillment = value;
        }

        /**
         * Gets the value of the override property.
         * 
         * @return
         *     possible object is
         *     {@link Override }
         *     
         */
        public Override getOverride() {
            return override;
        }

        /**
         * Sets the value of the override property.
         * 
         * @param value
         *     allowed object is
         *     {@link Override }
         *     
         */
        public void setOverride(Override value) {
            this.override = value;
        }

        /**
         * Gets the value of the price property.
         * 
         * @return
         *     possible object is
         *     {@link Price }
         *     
         */
        public Price getPrice() {
            return price;
        }

        /**
         * Sets the value of the price property.
         * 
         * @param value
         *     allowed object is
         *     {@link Price }
         *     
         */
        public void setPrice(Price value) {
            this.price = value;
        }

        /**
         * Gets the value of the processingReport property.
         * 
         * @return
         *     possible object is
         *     {@link ProcessingReport }
         *     
         */
        public ProcessingReport getProcessingReport() {
            return processingReport;
        }

        /**
         * Sets the value of the processingReport property.
         * 
         * @param value
         *     allowed object is
         *     {@link ProcessingReport }
         *     
         */
        public void setProcessingReport(ProcessingReport value) {
            this.processingReport = value;
        }

        /**
         * Gets the value of the product property.
         * 
         * @return
         *     possible object is
         *     {@link Product }
         *     
         */
        public Product getProduct() {
            return product;
        }

        /**
         * Sets the value of the product property.
         * 
         * @param value
         *     allowed object is
         *     {@link Product }
         *     
         */
        public void setProduct(Product value) {
            this.product = value;
        }

        /**
         * Gets the value of the productImage property.
         * 
         * @return
         *     possible object is
         *     {@link ProductImage }
         *     
         */
        public ProductImage getProductImage() {
            return productImage;
        }

        /**
         * Sets the value of the productImage property.
         * 
         * @param value
         *     allowed object is
         *     {@link ProductImage }
         *     
         */
        public void setProductImage(ProductImage value) {
            this.productImage = value;
        }

        /**
         * Gets the value of the relationship property.
         * 
         * @return
         *     possible object is
         *     {@link Relationship }
         *     
         */
        public Relationship getRelationship() {
            return relationship;
        }

        /**
         * Sets the value of the relationship property.
         * 
         * @param value
         *     allowed object is
         *     {@link Relationship }
         *     
         */
        public void setRelationship(Relationship value) {
            this.relationship = value;
        }

        /**
         * Gets the value of the settlementReport property.
         * 
         * @return
         *     possible object is
         *     {@link SettlementReport }
         *     
         */
        public SettlementReport getSettlementReport() {
            return settlementReport;
        }

        /**
         * Sets the value of the settlementReport property.
         * 
         * @param value
         *     allowed object is
         *     {@link SettlementReport }
         *     
         */
        public void setSettlementReport(SettlementReport value) {
            this.settlementReport = value;
        }

    }

}
