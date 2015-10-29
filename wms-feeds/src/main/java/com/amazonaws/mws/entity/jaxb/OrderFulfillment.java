//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.29 at 10:21:45 PM CST 
//


package com.amazonaws.mws.entity.jaxb;

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
 *         &lt;choice>
 *           &lt;element ref="{}AmazonOrderID"/>
 *           &lt;element ref="{}MerchantOrderID"/>
 *         &lt;/choice>
 *         &lt;element name="MerchantFulfillmentID" type="{}IDNumber" minOccurs="0"/>
 *         &lt;element name="FulfillmentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="FulfillmentData" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;choice>
 *                     &lt;element ref="{}CarrierCode"/>
 *                     &lt;element name="CarrierName" type="{}String"/>
 *                   &lt;/choice>
 *                   &lt;element name="ShippingMethod" type="{}String" minOccurs="0"/>
 *                   &lt;element name="ShipperTrackingNumber" type="{}String" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="CODCollectionMethod" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="DirectPayment"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Item" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;choice>
 *                     &lt;element ref="{}AmazonOrderItemCode"/>
 *                     &lt;element ref="{}MerchantOrderItemID"/>
 *                   &lt;/choice>
 *                   &lt;element name="MerchantFulfillmentItemID" type="{}IDNumber" minOccurs="0"/>
 *                   &lt;element name="Quantity" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
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
    "amazonOrderID",
    "merchantOrderID",
    "merchantFulfillmentID",
    "fulfillmentDate",
    "fulfillmentData",
    "codCollectionMethod",
    "item"
})
@XmlRootElement(name = "OrderFulfillment")
public class OrderFulfillment {

    @XmlElement(name = "AmazonOrderID")
    protected String amazonOrderID;
    @XmlElement(name = "MerchantOrderID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String merchantOrderID;
    @XmlElement(name = "MerchantFulfillmentID")
    protected BigInteger merchantFulfillmentID;
    @XmlElement(name = "FulfillmentDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fulfillmentDate;
    @XmlElement(name = "FulfillmentData")
    protected OrderFulfillment.FulfillmentData fulfillmentData;
    @XmlElement(name = "CODCollectionMethod")
    protected String codCollectionMethod;
    @XmlElement(name = "Item")
    protected List<OrderFulfillment.Item> item;

    /**
     * Gets the value of the amazonOrderID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmazonOrderID() {
        return amazonOrderID;
    }

    /**
     * Sets the value of the amazonOrderID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmazonOrderID(String value) {
        this.amazonOrderID = value;
    }

    /**
     * Gets the value of the merchantOrderID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerchantOrderID() {
        return merchantOrderID;
    }

    /**
     * Sets the value of the merchantOrderID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerchantOrderID(String value) {
        this.merchantOrderID = value;
    }

    /**
     * Gets the value of the merchantFulfillmentID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMerchantFulfillmentID() {
        return merchantFulfillmentID;
    }

    /**
     * Sets the value of the merchantFulfillmentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMerchantFulfillmentID(BigInteger value) {
        this.merchantFulfillmentID = value;
    }

    /**
     * Gets the value of the fulfillmentDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFulfillmentDate() {
        return fulfillmentDate;
    }

    /**
     * Sets the value of the fulfillmentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFulfillmentDate(XMLGregorianCalendar value) {
        this.fulfillmentDate = value;
    }

    /**
     * Gets the value of the fulfillmentData property.
     * 
     * @return
     *     possible object is
     *     {@link OrderFulfillment.FulfillmentData }
     *     
     */
    public OrderFulfillment.FulfillmentData getFulfillmentData() {
        return fulfillmentData;
    }

    /**
     * Sets the value of the fulfillmentData property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderFulfillment.FulfillmentData }
     *     
     */
    public void setFulfillmentData(OrderFulfillment.FulfillmentData value) {
        this.fulfillmentData = value;
    }

    /**
     * Gets the value of the codCollectionMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCODCollectionMethod() {
        return codCollectionMethod;
    }

    /**
     * Sets the value of the codCollectionMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCODCollectionMethod(String value) {
        this.codCollectionMethod = value;
    }

    /**
     * Gets the value of the item property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderFulfillment.Item }
     * 
     * 
     */
    public List<OrderFulfillment.Item> getItem() {
        if (item == null) {
            item = new ArrayList<OrderFulfillment.Item>();
        }
        return this.item;
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
     *         &lt;choice>
     *           &lt;element ref="{}CarrierCode"/>
     *           &lt;element name="CarrierName" type="{}String"/>
     *         &lt;/choice>
     *         &lt;element name="ShippingMethod" type="{}String" minOccurs="0"/>
     *         &lt;element name="ShipperTrackingNumber" type="{}String" minOccurs="0"/>
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
        "carrierCode",
        "carrierName",
        "shippingMethod",
        "shipperTrackingNumber"
    })
    public static class FulfillmentData {

        @XmlElement(name = "CarrierCode")
        protected String carrierCode;
        @XmlElement(name = "CarrierName")
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        protected String carrierName;
        @XmlElement(name = "ShippingMethod")
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        protected String shippingMethod;
        @XmlElement(name = "ShipperTrackingNumber")
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        protected String shipperTrackingNumber;

        /**
         * Gets the value of the carrierCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCarrierCode() {
            return carrierCode;
        }

        /**
         * Sets the value of the carrierCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCarrierCode(String value) {
            this.carrierCode = value;
        }

        /**
         * Gets the value of the carrierName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCarrierName() {
            return carrierName;
        }

        /**
         * Sets the value of the carrierName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCarrierName(String value) {
            this.carrierName = value;
        }

        /**
         * Gets the value of the shippingMethod property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShippingMethod() {
            return shippingMethod;
        }

        /**
         * Sets the value of the shippingMethod property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShippingMethod(String value) {
            this.shippingMethod = value;
        }

        /**
         * Gets the value of the shipperTrackingNumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShipperTrackingNumber() {
            return shipperTrackingNumber;
        }

        /**
         * Sets the value of the shipperTrackingNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShipperTrackingNumber(String value) {
            this.shipperTrackingNumber = value;
        }

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
     *         &lt;choice>
     *           &lt;element ref="{}AmazonOrderItemCode"/>
     *           &lt;element ref="{}MerchantOrderItemID"/>
     *         &lt;/choice>
     *         &lt;element name="MerchantFulfillmentItemID" type="{}IDNumber" minOccurs="0"/>
     *         &lt;element name="Quantity" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
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
        "amazonOrderItemCode",
        "merchantOrderItemID",
        "merchantFulfillmentItemID",
        "quantity"
    })
    public static class Item {

        @XmlElement(name = "AmazonOrderItemCode")
        protected String amazonOrderItemCode;
        @XmlElement(name = "MerchantOrderItemID")
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        protected String merchantOrderItemID;
        @XmlElement(name = "MerchantFulfillmentItemID")
        protected BigInteger merchantFulfillmentItemID;
        @XmlElement(name = "Quantity")
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger quantity;

        /**
         * Gets the value of the amazonOrderItemCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAmazonOrderItemCode() {
            return amazonOrderItemCode;
        }

        /**
         * Sets the value of the amazonOrderItemCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAmazonOrderItemCode(String value) {
            this.amazonOrderItemCode = value;
        }

        /**
         * Gets the value of the merchantOrderItemID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMerchantOrderItemID() {
            return merchantOrderItemID;
        }

        /**
         * Sets the value of the merchantOrderItemID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMerchantOrderItemID(String value) {
            this.merchantOrderItemID = value;
        }

        /**
         * Gets the value of the merchantFulfillmentItemID property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getMerchantFulfillmentItemID() {
            return merchantFulfillmentItemID;
        }

        /**
         * Sets the value of the merchantFulfillmentItemID property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setMerchantFulfillmentItemID(BigInteger value) {
            this.merchantFulfillmentItemID = value;
        }

        /**
         * Gets the value of the quantity property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getQuantity() {
            return quantity;
        }

        /**
         * Sets the value of the quantity property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setQuantity(BigInteger value) {
            this.quantity = value;
        }

    }

}
