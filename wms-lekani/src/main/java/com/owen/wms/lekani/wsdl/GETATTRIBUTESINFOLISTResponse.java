
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
 *         &lt;element name="GET_ATTRIBUTESINFOLISTResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "getattributesinfolistResult"
})
@XmlRootElement(name = "GET_ATTRIBUTESINFOLISTResponse")
public class GETATTRIBUTESINFOLISTResponse {

    @XmlElement(name = "GET_ATTRIBUTESINFOLISTResult")
    protected String getattributesinfolistResult;

    /**
     * ��ȡgetattributesinfolistResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGETATTRIBUTESINFOLISTResult() {
        return getattributesinfolistResult;
    }

    /**
     * ����getattributesinfolistResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGETATTRIBUTESINFOLISTResult(String value) {
        this.getattributesinfolistResult = value;
    }

}
