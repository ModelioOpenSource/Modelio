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
 * <p>
 * Java class for _Literal complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="_Literal">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
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
 * &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("5cc0e493-5acc-439e-9e5e-e66f95364c12")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Literal", propOrder = {
    "description",
    "extensions"
})
@XmlSeeAlso({
    org.modelio.gproject.data.module.jaxbv2.Jxbv2Enumeration.Jxbv2Literal.class
})
public class Jxbv2Literal {
    @objid ("6c6b1328-c7f4-428f-91f2-b1a43b78d589")
    @XmlAttribute(name = "value", required = true)
    protected String value;

    @objid ("05ec6953-23e3-4507-9a7d-0bc0f740b93e")
    @XmlAttribute(name = "label")
    protected String label;

    @objid ("3eec1813-fb16-4f75-843d-bb76bb1317e0")
    @XmlElement(name = "Description")
    protected String description;

    @objid ("b4070606-4948-4f1f-8a9e-3cc1ec7ecc45")
    @XmlElement (name = "Extensions")
    protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Literal.Jxbv2Extensions extensions;

    /**
     * Gets the value of the value property.
     * @return possible object is {@link String }
     */
    @objid ("780b1425-d12d-4e27-93b2-4a8f116dc764")
    public String getValue() {
        return this.value;
    }

    /**
     * Sets the value of the value property.
     * @param value allowed object is {@link String }
     */
    @objid ("7ebcccd3-8324-4eaf-b9a7-2e25ef17d32f")
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the label property.
     * @return possible object is {@link String }
     */
    @objid ("f753b2e2-2388-46f5-9532-4ea86726ad33")
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the value of the label property.
     * @param value allowed object is {@link String }
     */
    @objid ("e4419d10-16c1-4eb6-960f-259006463265")
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the description property.
     * @return possible object is {@link String }
     */
    @objid ("cc43df48-3303-44fd-aaa8-ea32c15e423f")
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the value of the description property.
     * @param value allowed object is {@link String }
     */
    @objid ("212132f6-87ba-4efb-a1c9-7a23912d1806")
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the extensions property.
     * @return possible object is {@link Jxbv2Literal.Jxbv2Extensions }
     */
    @objid ("6aabd36b-b204-4735-8bbe-bb0fc3d93600")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Literal.Jxbv2Extensions getExtensions() {
        return this.extensions;
    }

    /**
     * Sets the value of the extensions property.
     * @param value allowed object is {@link Jxbv2Literal.Jxbv2Extensions }
     */
    @objid ("0065d35d-a7cb-44fc-b4ec-e8cdcc6c72d2")
    public void setExtensions(org.modelio.gproject.data.module.jaxbv2.Jxbv2Literal.Jxbv2Extensions value) {
        this.extensions = value;
    }

    /**
     * <p>
     * Java class for anonymous complex type.
     * 
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
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
    @objid ("0a27d1b6-085f-4fee-848f-bb9c1ef50004")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
                "stereotypeRef"
            })
    public static class Jxbv2Extensions {
        @objid ("f521a820-dd6a-434d-95d7-f915101bb9b6")
        @XmlElement (name = "StereotypeRef")
        protected List<Jxbv2StereotypeRef> stereotypeRef;

        /**
         * Gets the value of the stereotypeRef property.
         * 
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the stereotypeRef
         * property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * 
         * <pre>
         * getStereotypeRef().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list {@link Jxbv2StereotypeRef }
         */
        @objid ("8b2f4a08-c77c-4542-b355-2b124a3a9990")
        public List<Jxbv2StereotypeRef> getStereotypeRef() {
            if (this.stereotypeRef == null) {
                this.stereotypeRef = new ArrayList<>();
            }
            return this.stereotypeRef;
        }

    }

}
