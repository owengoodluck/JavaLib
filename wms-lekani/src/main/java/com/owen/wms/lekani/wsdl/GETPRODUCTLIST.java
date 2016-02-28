
package com.owen.wms.lekani.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡkey���Ե�ֵ��
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
     * ����key���Ե�ֵ��
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
     * ��ȡpageIndex���Ե�ֵ��
     * 
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * ����pageIndex���Ե�ֵ��
     * 
     */
    public void setPageIndex(int value) {
        this.pageIndex = value;
    }

    /**
     * ��ȡcatID���Ե�ֵ��
     * 
     */
    public int getCatID() {
        return catID;
    }

    /**
     * ����catID���Ե�ֵ��
     * 
     */
    public void setCatID(int value) {
        this.catID = value;
    }

    /**
     * ��ȡbrandID���Ե�ֵ��
     * 
     */
    public int getBrandID() {
        return brandID;
    }

    /**
     * ����brandID���Ե�ֵ��
     * 
     */
    public void setBrandID(int value) {
        this.brandID = value;
    }

    /**
     * ��ȡshopBeginTime���Ե�ֵ��
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
     * ����shopBeginTime���Ե�ֵ��
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
     * ��ȡshopEndTime���Ե�ֵ��
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
     * ����shopEndTime���Ե�ֵ��
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
     * ��ȡskus���Ե�ֵ��
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
     * ����skus���Ե�ֵ��
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
