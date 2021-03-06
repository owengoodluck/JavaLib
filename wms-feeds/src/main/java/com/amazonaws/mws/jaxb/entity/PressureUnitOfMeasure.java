//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.29 at 10:21:45 PM CST 
//


package com.amazonaws.mws.jaxb.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PressureUnitOfMeasure.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PressureUnitOfMeasure">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="bars"/>
 *     &lt;enumeration value="psi"/>
 *     &lt;enumeration value="pascal"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PressureUnitOfMeasure")
@XmlEnum
public enum PressureUnitOfMeasure {

    @XmlEnumValue("bars")
    BARS("bars"),
    @XmlEnumValue("psi")
    PSI("psi"),
    @XmlEnumValue("pascal")
    PASCAL("pascal");
    private final String value;

    PressureUnitOfMeasure(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PressureUnitOfMeasure fromValue(String v) {
        for (PressureUnitOfMeasure c: PressureUnitOfMeasure.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
