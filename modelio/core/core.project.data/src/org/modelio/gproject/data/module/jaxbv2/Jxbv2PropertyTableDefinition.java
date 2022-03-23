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
package org.modelio.gproject.data.module.jaxbv2;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for _PropertyTableDefinition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="_PropertyTableDefinition">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="PropertyDefinition" type="{}_PropertyDefinition" maxOccurs="unbounded"/>
 * &lt;element name="Extensions" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="StereotypeRef" type="{}_StereotypeRef" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("82736df9-b101-48c1-803d-13d63092739d")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_PropertyTableDefinition", propOrder = {
    "description",
    "propertyDefinition",
    "extensions"
})
public class Jxbv2PropertyTableDefinition {
    @objid ("3dc81f9e-584f-4e5a-bf15-63dee2c30f8e")
    @XmlAttribute(name = "id", required = true)
    protected String id;

    @objid ("3b57954e-121b-4495-a120-d0ebe243e203")
    @XmlAttribute(name = "label")
    protected String label;

    @objid ("e19bb06a-d01c-45fa-9958-1734639f4e94")
    @XmlAttribute(name = "uid")
    protected String uid;

    @objid ("7c938cd0-167d-4665-b805-6a6988bfeb86")
    @XmlElement(name = "Description")
    protected String description;

    @objid ("8aac24bd-c3dc-4fa1-aa58-cbf6e2a66064")
    @XmlElement(name = "PropertyDefinition", required = true)
    protected List<Jxbv2PropertyDefinition> propertyDefinition;

    @objid ("ccd8cd50-5e1f-418f-9a35-963d24dd1a2f")
    @XmlElement(name = "Extensions")
    protected org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyTableDefinition.Jxbv2Extensions extensions;

    /**
     * Gets the value of the propertyDefinition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the propertyDefinition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     * getPropertyDefinition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PropertyDefinition }
     */
    @objid ("81bcd34f-07d3-4eeb-86e6-ba58d2567e1b")
    public List<Jxbv2PropertyDefinition> getPropertyDefinition() {
        if (this.propertyDefinition == null) {
            this.propertyDefinition = new ArrayList<>();
        }
        return this.propertyDefinition;
    }

    /**
     * Gets the value of the id property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("28b28ab6-6761-4aed-83a7-89d6846c8cf5")
    public String getId() {
        return this.id;
    }

    /**
     * Sets the value of the id property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("a6080dad-e4ef-4d17-8818-d698c6137823")
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the label property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("738d5552-aef8-4bba-ad1e-f2f9a94e27be")
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the value of the label property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("03cf0bad-65c2-42c5-bb6f-9e0b960454d8")
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the uid property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("7cdd8d2d-1fe1-4c9b-8583-c8e1bee3f57a")
    public String getUid() {
        return this.uid;
    }

    /**
     * Sets the value of the uid property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("0a136c65-5df8-4a4e-b1ea-4fa17dc412a4")
    public void setUid(String value) {
        this.uid = value;
    }

    /**
     * Gets the value of the description property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("7a1a6bcf-aae8-40e7-b986-01c65d401014")
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the value of the description property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("69f1f6fd-04e5-459c-bac6-572a5764000d")
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the extensions property.
     * @return
     * possible object is
     * {@link PropertyTableDefinition.Extensions }
     */
    @objid ("85978f9c-79bd-4fe4-a8ff-2b4ef0c697fd")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyTableDefinition.Jxbv2Extensions getExtensions() {
        return this.extensions;
    }

    /**
     * Sets the value of the extensions property.
     * @param value allowed object is
     * {@link PropertyTableDefinition.Extensions }
     */
    @objid ("4340a6a7-12bf-4a8c-bbfb-83c497a6f1cf")
    public void setExtensions(org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyTableDefinition.Jxbv2Extensions value) {
        this.extensions = value;
    }

    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="StereotypeRef" type="{}_StereotypeRef" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @objid ("229cd5fc-7508-41ba-b99d-43028c7caa89")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "stereotypeRef"
        })
    public static class Jxbv2Extensions {
        @objid ("c8705e6a-fb2b-4717-ae4b-e4253dfe9607")
        @XmlElement(name = "StereotypeRef")
        protected List<Jxbv2StereotypeRef> stereotypeRef;

        /**
         * Gets the value of the stereotypeRef property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the stereotypeRef property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         * getStereotypeRef().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link StereotypeRef }
         */
        @objid ("6225cd0f-f82f-457a-8e92-21933cfc76ad")
        public List<Jxbv2StereotypeRef> getStereotypeRef() {
            if (this.stereotypeRef == null) {
                this.stereotypeRef = new ArrayList<>();
            }
            return this.stereotypeRef;
        }

    }

}
