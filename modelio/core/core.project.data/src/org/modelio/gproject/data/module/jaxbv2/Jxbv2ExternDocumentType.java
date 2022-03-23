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
 * <p>
 * Java class for _ExternDocumentType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="_ExternDocumentType">
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
 * &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="is-hidden" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("105d3538-9518-493d-961c-f254276f44da")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType (name = "_ExternDocumentType", propOrder = {
        "description",
        "extensions"
})
public class Jxbv2ExternDocumentType {
    @objid ("a602e92a-6646-4a74-be5c-fe4d7501093a")
    @XmlAttribute (name = "name", required = true)
    protected String name;

    @objid ("ab4aa660-eafe-4611-a03c-d38454883fdd")
    @XmlAttribute (name = "uid")
    protected String uid;

    @objid ("b76ff7d3-13e3-4b65-8406-68972d4190b6")
    @XmlAttribute (name = "label")
    protected String label;

    @objid ("505e6649-5665-4d43-936f-b7496b98009a")
    @XmlAttribute (name = "is-hidden")
    protected String isHidden;

    @objid ("819ec70a-3ac4-4640-87e5-9815a3b230e0")
    @XmlElement (name = "Description")
    protected String description;

    @objid ("39f71e51-5fff-47e6-92b4-090a2320576a")
    @XmlElement (name = "Extensions")
    protected org.modelio.gproject.data.module.jaxbv2.Jxbv2ExternDocumentType.Jxbv2Extensions extensions;

    /**
     * Gets the value of the name property.
     * @return possible object is {@link String }
     */
    @objid ("6e81968f-2a41-4b7d-86d2-360f0e67a923")
    public String getName() {
        return this.name;
    }

    /**
     * Sets the value of the name property.
     * @param value allowed object is {@link String }
     */
    @objid ("b00c5c1c-f150-49dd-85f6-cebd2c8adc63")
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the uid property.
     * @return possible object is {@link String }
     */
    @objid ("ef281c53-2efb-40cc-9854-8634dd7d0721")
    public String getUid() {
        return this.uid;
    }

    /**
     * Sets the value of the uid property.
     * @param value allowed object is {@link String }
     */
    @objid ("a7dae9af-9e02-4174-81e2-8d95cc5fde58")
    public void setUid(String value) {
        this.uid = value;
    }

    /**
     * Gets the value of the label property.
     * @return possible object is {@link String }
     */
    @objid ("395785dc-1be1-41b5-9818-7ef4f194dae6")
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the value of the label property.
     * @param value allowed object is {@link String }
     */
    @objid ("3e9c446d-bd12-4fc7-9150-fc3089a21961")
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the isHidden property.
     * @return possible object is {@link String }
     */
    @objid ("e14b3b4c-7174-4fe1-839b-1401195a8e4e")
    public String getIsHidden() {
        return this.isHidden;
    }

    /**
     * Sets the value of the isHidden property.
     * @param value allowed object is {@link String }
     */
    @objid ("f31b908d-4f7f-4148-9db3-c68d57608c51")
    public void setIsHidden(String value) {
        this.isHidden = value;
    }

    /**
     * Gets the value of the description property.
     * @return possible object is {@link String }
     */
    @objid ("cf876b95-b57e-436f-9f2a-eb151ee49a1d")
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the value of the description property.
     * @param value allowed object is {@link String }
     */
    @objid ("8f233076-15ae-460d-81dc-7b76cae551e9")
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the extensions property.
     * @return possible object is {@link Jxbv2ExternDocumentType.Jxbv2Extensions }
     */
    @objid ("d3a8558b-3f4b-4ba0-9d80-2df46ec63bf3")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2ExternDocumentType.Jxbv2Extensions getExtensions() {
        return this.extensions;
    }

    /**
     * Sets the value of the extensions property.
     * @param value allowed object is {@link Jxbv2ExternDocumentType.Jxbv2Extensions }
     */
    @objid ("3499618d-ac86-4c1f-9a77-c620f95ce44f")
    public void setExtensions(org.modelio.gproject.data.module.jaxbv2.Jxbv2ExternDocumentType.Jxbv2Extensions value) {
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
    @objid ("71d8469e-bbd3-4385-9af7-c85f241137ce")
    @XmlAccessorType (XmlAccessType.FIELD)
    @XmlType (name = "", propOrder = {
                "stereotypeRef"
        })
    public static class Jxbv2Extensions {
        @objid ("c5372a0e-1da8-4ccf-9daf-f1f56925747d")
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
        @objid ("589635e1-708f-435d-8bce-a89f4e540f2b")
        public List<Jxbv2StereotypeRef> getStereotypeRef() {
            if (this.stereotypeRef == null) {
                this.stereotypeRef = new ArrayList<>();
            }
            return this.stereotypeRef;
        }

    }

}
