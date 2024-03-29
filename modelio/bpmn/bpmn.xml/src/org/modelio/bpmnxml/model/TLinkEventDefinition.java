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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

/**
 * <p>Java class for tLinkEventDefinition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tLinkEventDefinition">
 * &lt;complexContent>
 * &lt;extension base="{http://www.omg.org/spec/BPMN/20100524/MODEL}tEventDefinition">
 * &lt;sequence>
 * &lt;element name="source" type="{http://www.w3.org/2001/XMLSchema}QName" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;element name="target" type="{http://www.w3.org/2001/XMLSchema}QName" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;anyAttribute processContents='lax' namespace='##other'/>
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("b489145d-3392-4edc-b164-79532eca75ae")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tLinkEventDefinition", propOrder = {
    "source",
    "target"
})
public class TLinkEventDefinition extends TEventDefinition {
    @objid ("92e8704e-ae9f-4bc5-834f-1b3c65a81a4f")
    @XmlAttribute(name = "name", required = true)
    protected String name;

    @objid ("e2f1390f-3bf2-4e9d-9844-56700daed382")
    protected List<QName> source;

    @objid ("a771072b-abd6-4be5-8a0f-ed79d4d12120")
    protected QName target;

    /**
     * Gets the value of the source property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the source property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     * getSource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QName }
     */
    @objid ("771f8377-eb1b-4189-a7c3-86dc21bf9397")
    public List<QName> getSource() {
        if (this.source == null) {
            this.source = new ArrayList<>();
        }
        return this.source;
    }

    /**
     * Gets the value of the target property.
     * @return
     * possible object is
     * {@link QName }
     */
    @objid ("7e264ecf-fedc-4f9d-8240-a34e12627930")
    public QName getTarget() {
        return this.target;
    }

    /**
     * Sets the value of the target property.
     * @param value allowed object is
     * {@link QName }
     */
    @objid ("030223b2-1e8a-4761-8d85-5d739a953545")
    public void setTarget(QName value) {
        this.target = value;
    }

    /**
     * Gets the value of the name property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("5f32164e-4574-4f30-abdc-df958108b8f2")
    public String getName() {
        return this.name;
    }

    /**
     * Sets the value of the name property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("89d78b56-09e4-4a84-982f-3ca8a58b9657")
    public void setName(String value) {
        this.name = value;
    }

}
