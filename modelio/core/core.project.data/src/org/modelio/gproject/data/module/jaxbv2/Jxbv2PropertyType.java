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
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>Java class for _PropertyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="_PropertyType">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence minOccurs="0">
 * &lt;element name="Enumeration" type="{}_Enumeration" minOccurs="0"/>
 * &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 * &lt;attribute name="type" use="required">
 * &lt;simpleType>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 * &lt;enumeration value="Boolean"/>
 * &lt;enumeration value="Date"/>
 * &lt;enumeration value="Element"/>
 * &lt;enumeration value="Float"/>
 * &lt;enumeration value="Integer"/>
 * &lt;enumeration value="MultiText"/>
 * &lt;enumeration value="RichText"/>
 * &lt;enumeration value="Text"/>
 * &lt;enumeration value="Time"/>
 * &lt;enumeration value="Unsigned"/>
 * &lt;/restriction>
 * &lt;/simpleType>
 * &lt;/attribute>
 * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="ishidden" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("25f3a4eb-a813-4f07-b2ca-ea5adef9b145")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_PropertyType", propOrder = {
    "enumeration",
    "description",
    "extensions"
})
@XmlSeeAlso({
    org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2PropertyTypes.Jxbv2PropertyType.class
})
public class Jxbv2PropertyType {
    @objid ("e87762cb-1385-409a-b4ef-496ef596d6cf")
    @XmlElement(name = "Description")
    protected String description;

    @objid ("ff9b42a0-584f-463e-8415-ac222b21f31b")
    @XmlAttribute(name = "id", required = true)
    protected String id;

    @objid ("59310f6d-e71b-4ae9-bf0a-d284942a091d")
    @XmlAttribute(name = "type", required = true)
    protected String type;

    @objid ("e53e99ba-0367-499b-83b1-b89501770356")
    @XmlAttribute(name = "uid")
    protected String uid;

    @objid ("9039e27b-3954-418e-82bb-839497b15591")
    @XmlAttribute(name = "ishidden", required = true)
    protected String ishidden;

    @objid ("202f8711-0af1-4991-b40a-4a356f7b31b5")
    @XmlElement(name = "Enumeration")
    protected Jxbv2Enumeration enumeration;

    @objid ("2bc514f3-f0e1-47d5-9efe-6a367fe43e24")
    @XmlElement(name = "Extensions")
    protected org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyType.Jxbv2Extensions extensions;

    /**
     * Gets the value of the enumeration property.
     * @return
     * possible object is
     * {@link Enumeration }
     */
    @objid ("0756ae75-3396-4206-b37c-3964afbadefd")
    public Jxbv2Enumeration getEnumeration() {
        return this.enumeration;
    }

    /**
     * Sets the value of the enumeration property.
     * @param value allowed object is
     * {@link Enumeration }
     */
    @objid ("11c531c5-2c6f-4d75-981b-acdc8ff8f25c")
    public void setEnumeration(Jxbv2Enumeration value) {
        this.enumeration = value;
    }

    /**
     * Gets the value of the description property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("23b15038-255e-4024-a028-3935743ad918")
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the value of the description property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("69928021-37dd-42e9-9296-14b4ad0f3f93")
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the id property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("0dbdf70a-94f6-48b2-aabe-0e1422acd418")
    public String getId() {
        return this.id;
    }

    /**
     * Sets the value of the id property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("d13eefff-12a4-4143-9cb4-8b3ed2208f1d")
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the type property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("b68707a5-6fda-4cbd-8045-cc6521351380")
    public String getType() {
        return this.type;
    }

    /**
     * Sets the value of the type property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("4e2ed608-fd74-4cef-a8e6-df1739168d4c")
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the uid property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("1bd4a777-6ee2-4cfd-8d03-2810a15ef13d")
    public String getUid() {
        return this.uid;
    }

    /**
     * Sets the value of the uid property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("f8caceee-9cce-4874-b01a-7648fe291427")
    public void setUid(String value) {
        this.uid = value;
    }

    /**
     * Gets the value of the ishidden property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("09216e16-bec1-4078-8c47-6ae0cb8fec0f")
    public String getIshidden() {
        return this.ishidden;
    }

    /**
     * Sets the value of the ishidden property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("5bfcb8d1-2f5a-4986-b8fa-5969b44d218e")
    public void setIshidden(String value) {
        this.ishidden = value;
    }

    /**
     * Gets the value of the extensions property.
     * @return
     * possible object is
     * {@link PropertyType.Extensions }
     */
    @objid ("e3175619-9f35-4de0-a8a5-961c70666f61")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyType.Jxbv2Extensions getExtensions() {
        return this.extensions;
    }

    /**
     * Sets the value of the extensions property.
     * @param value allowed object is
     * {@link PropertyType.Extensions }
     */
    @objid ("70e35a27-5e86-4dad-8c16-a1916504f0fe")
    public void setExtensions(org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyType.Jxbv2Extensions value) {
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
    @objid ("225422bc-33c6-4cc5-82cb-2f2766971b0e")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
                "stereotypeRef"
            })
    public static class Jxbv2Extensions {
        @objid ("0d08e250-06da-4ef8-a10f-d6a0ebe54ed1")
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
        @objid ("89f15458-7afb-4d4a-8afd-e19e93b49225")
        public List<Jxbv2StereotypeRef> getStereotypeRef() {
            if (this.stereotypeRef == null) {
                this.stereotypeRef = new ArrayList<>();
            }
            return this.stereotypeRef;
        }

    }

}
