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
 * <p>Java class for TorqueUnitOfMeasure.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TorqueUnitOfMeasure">
 *   &lt;restriction base="{}StringNotNull">
 *     &lt;enumeration value="foot-lbs"/>
 *     &lt;enumeration value="inch-lbs"/>
 *     &lt;enumeration value="centimeter_kilograms"/>
 *     &lt;enumeration value="foot_pounds"/>
 *     &lt;enumeration value="inch_ounces"/>
 *     &lt;enumeration value="inch_pounds"/>
 *     &lt;enumeration value="kilonewtons"/>
 *     &lt;enumeration value="kilograms_per_millimeter"/>
 *     &lt;enumeration value="newton_meters"/>
 *     &lt;enumeration value="newton_millimeters"/>
 *     &lt;enumeration value="newtons"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TorqueUnitOfMeasure")
@XmlEnum
public enum TorqueUnitOfMeasure {

    @XmlEnumValue("foot-lbs")
    FOOT_LBS("foot-lbs"),
    @XmlEnumValue("inch-lbs")
    INCH_LBS("inch-lbs"),
    @XmlEnumValue("centimeter_kilograms")
    CENTIMETER_KILOGRAMS("centimeter_kilograms"),
    @XmlEnumValue("foot_pounds")
    FOOT_POUNDS("foot_pounds"),
    @XmlEnumValue("inch_ounces")
    INCH_OUNCES("inch_ounces"),
    @XmlEnumValue("inch_pounds")
    INCH_POUNDS("inch_pounds"),
    @XmlEnumValue("kilonewtons")
    KILONEWTONS("kilonewtons"),
    @XmlEnumValue("kilograms_per_millimeter")
    KILOGRAMS_PER_MILLIMETER("kilograms_per_millimeter"),
    @XmlEnumValue("newton_meters")
    NEWTON_METERS("newton_meters"),
    @XmlEnumValue("newton_millimeters")
    NEWTON_MILLIMETERS("newton_millimeters"),
    @XmlEnumValue("newtons")
    NEWTONS("newtons");
    private final String value;

    TorqueUnitOfMeasure(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TorqueUnitOfMeasure fromValue(String v) {
        for (TorqueUnitOfMeasure c: TorqueUnitOfMeasure.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
