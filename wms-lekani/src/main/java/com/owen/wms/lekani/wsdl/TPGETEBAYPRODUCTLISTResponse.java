
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
 *         &lt;element name="TPGET_EBAYPRODUCTLISTResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "tpgetebayproductlistResult"
})
@XmlRootElement(name = "TPGET_EBAYPRODUCTLISTResponse")
public class TPGETEBAYPRODUCTLISTResponse {

    @XmlElement(name = "TPGET_EBAYPRODUCTLISTResult")
    protected String tpgetebayproductlistResult;

    /**
     * ��ȡtpgetebayproductlistResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTPGETEBAYPRODUCTLISTResult() {
        return tpgetebayproductlistResult;
    }

    /**
     * ����tpgetebayproductlistResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTPGETEBAYPRODUCTLISTResult(String value) {
        this.tpgetebayproductlistResult = value;
    }

}
