//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.29 at 10:21:45 PM CST 
//


package com.amazonaws.mws.entity.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MagnificationUnitOfMeasure.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MagnificationUnitOfMeasure">
 *   &lt;restriction base="{}String">
 *     &lt;enumeration value="multiplier_x"/>
 *     &lt;enumeration value="diopters"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MagnificationUnitOfMeasure")
@XmlEnum
public enum MagnificationUnitOfMeasure {

    @XmlEnumValue("multiplier_x")
    MULTIPLIER_X("multiplier_x"),
    @XmlEnumValue("diopters")
    DIOPTERS("diopters");
    private final String value;

    MagnificationUnitOfMeasure(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MagnificationUnitOfMeasure fromValue(String v) {
        for (MagnificationUnitOfMeasure c: MagnificationUnitOfMeasure.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
