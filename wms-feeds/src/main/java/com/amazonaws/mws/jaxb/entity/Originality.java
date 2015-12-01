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
 * <p>Java class for Originality.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Originality">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Original"/>
 *     &lt;enumeration value="Original Limited Edition"/>
 *     &lt;enumeration value="Reproduced"/>
 *     &lt;enumeration value="Reproduced Limited Edition"/>
 *     &lt;enumeration value="Replica"/>
 *     &lt;enumeration value="Replica Limited Edition"/>
 *     &lt;enumeration value="Limited Edition"/>
 *     &lt;enumeration value="Manufactured"/>
 *     &lt;enumeration value="Licensed"/>
 *     &lt;enumeration value="Vintage"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Originality")
@XmlEnum
public enum Originality {

    @XmlEnumValue("Original")
    ORIGINAL("Original"),
    @XmlEnumValue("Original Limited Edition")
    ORIGINAL_LIMITED_EDITION("Original Limited Edition"),
    @XmlEnumValue("Reproduced")
    REPRODUCED("Reproduced"),
    @XmlEnumValue("Reproduced Limited Edition")
    REPRODUCED_LIMITED_EDITION("Reproduced Limited Edition"),
    @XmlEnumValue("Replica")
    REPLICA("Replica"),
    @XmlEnumValue("Replica Limited Edition")
    REPLICA_LIMITED_EDITION("Replica Limited Edition"),
    @XmlEnumValue("Limited Edition")
    LIMITED_EDITION("Limited Edition"),
    @XmlEnumValue("Manufactured")
    MANUFACTURED("Manufactured"),
    @XmlEnumValue("Licensed")
    LICENSED("Licensed"),
    @XmlEnumValue("Vintage")
    VINTAGE("Vintage");
    private final String value;

    Originality(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Originality fromValue(String v) {
        for (Originality c: Originality.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}