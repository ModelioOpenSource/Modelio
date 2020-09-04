/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2015.12.02 at 02:29:48 PM CET
//
package org.modelio.bpmnxml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for BPMNLabel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BPMNLabel">
 * &lt;complexContent>
 * &lt;extension base="{http://www.omg.org/spec/DD/20100524/DI}Label">
 * &lt;attribute name="labelStyle" type="{http://www.w3.org/2001/XMLSchema}QName" />
 * &lt;anyAttribute processContents='lax' namespace='##other'/>
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("df544373-1c98-42eb-aed3-0fbcb449dda3")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BPMNLabel", namespace = "http://www.omg.org/spec/BPMN/20100524/DI")
public class BPMNLabel extends Label {
    @objid ("84888b20-026b-4b00-9021-bfc8ccf6fd7d")
    @XmlAttribute(name = "labelStyle")
    protected QName labelStyle;

    /**
     * Gets the value of the labelStyle property.
     * @return
     * possible object is
     * {@link QName }
     */
    @objid ("5ae87ff9-ffb8-4bd3-a2e0-e22f077571aa")
    public QName getLabelStyle() {
        return this.labelStyle;
    }

    /**
     * Sets the value of the labelStyle property.
     * 
     * @param value allowed object is
     * {@link QName }
     */
    @objid ("ad0b13e1-1523-40ea-a644-b37da0161eec")
    public void setLabelStyle(QName value) {
        this.labelStyle = value;
    }

}