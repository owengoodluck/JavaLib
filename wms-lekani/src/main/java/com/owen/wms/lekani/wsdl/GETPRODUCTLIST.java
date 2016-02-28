
package com.owen.wms.lekani.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PageIndex" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="CatID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="BrandID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ShopBeginTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ShopEndTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SKUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "key",
    "pageIndex",
    "catID",
    "brandID",
    "shopBeginTime",
    "shopEndTime",
    "skus"
})
@XmlRootElement(name = "GET_PRODUCTLIST")
public class GETPRODUCTLIST {

    @XmlElement(name = "Key")
    protected String key;
    @XmlElement(name = "PageIndex")
    protected int pageIndex;
    @XmlElement(name = "CatID")
    protected int catID;
    @XmlElement(name = "BrandID")
    protected int brandID;
    @XmlElement(name = "ShopBeginTime")
    protected String shopBeginTime;
    @XmlElement(name = "ShopEndTime")
    protected String shopEndTime;
    @XmlElement(name = "SKUS")
    protected String skus;

    /**
     * 获取key属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置key属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * 获取pageIndex属性的值。
     * 
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * 设置pageIndex属性的值。
     * 
     */
    public void setPageIndex(int value) {
        this.pageIndex = value;
    }

    /**
     * 获取catID属性的值。
     * 
     */
    public int getCatID() {
        return catID;
    }

    /**
     * 设置catID属性的值。
     * 
     */
    public void setCatID(int value) {
        this.catID = value;
    }

    /**
     * 获取brandID属性的值。
     * 
     */
    public int getBrandID() {
        return brandID;
    }

    /**
     * 设置brandID属性的值。
     * 
     */
    public void setBrandID(int value) {
        this.brandID = value;
    }

    /**
     * 获取shopBeginTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShopBeginTime() {
        return shopBeginTime;
    }

    /**
     * 设置shopBeginTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShopBeginTime(String value) {
        this.shopBeginTime = value;
    }

    /**
     * 获取shopEndTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShopEndTime() {
        return shopEndTime;
    }

    /**
     * 设置shopEndTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShopEndTime(String value) {
        this.shopEndTime = value;
    }

    /**
     * 获取skus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSKUS() {
        return skus;
    }

    /**
     * 设置skus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSKUS(String value) {
        this.skus = value;
    }

}
