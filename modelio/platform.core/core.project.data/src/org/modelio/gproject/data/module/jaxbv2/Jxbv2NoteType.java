/* 
 * Copyright 2013-2018 Modeliosoft
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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for _NoteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="_NoteType">
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
 * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="is-hidden" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="mime-type" type="{http://www.w3.org/2001/XMLSchema}string" default="text/plain" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("cbb78a95-4281-4421-9134-ba04c01bfb3b")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_NoteType", propOrder = {
    "description",
    "extensions"
})
public class Jxbv2NoteType {
    @objid ("ee291809-8461-4c25-b5d8-baf07a2d7bc6")
    @XmlAttribute(name = "name", required = true)
    protected String name;

    @objid ("ce49fde0-16c1-4350-bf7e-77bd044c2c2e")
    @XmlAttribute(name = "label")
    protected String label;

    @objid ("c99eec46-a288-4746-a403-9539a3af5884")
    @XmlAttribute(name = "is-hidden")
    protected String isHidden;

    @objid ("b2f4de30-6315-438d-8500-f26a5464d966")
    @XmlAttribute(name = "uid")
    protected String uid;

    @objid ("7bf983ef-dbfd-431f-b67e-2605c2858141")
    @XmlAttribute(name = "mime-type")
    protected String mimeType;

    @objid ("58284760-21c7-45ba-9782-d62df74860dc")
    @XmlElement(name = "Description")
    protected String description;

    @objid ("4afe5c2b-8f98-4078-9391-c85d3f6324ff")
    @XmlElement(name = "Extensions")
    protected org.modelio.gproject.data.module.jaxbv2.Jxbv2NoteType.Jxbv2Extensions extensions;

    /**
     * Gets the value of the name property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("6e440e26-d96b-4ff6-9031-9509be1522d8")
    public String getName() {
        return this.name;
    }

    /**
     * Sets the value of the name property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("48de5ef7-f3cf-4657-8cb7-1bbb43880209")
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the label property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("0120eee6-1a3a-4178-a682-20a63c3c4e57")
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the value of the label property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("e846c7ea-4450-4653-9d1d-1c6acafdcf36")
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the isHidden property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("f12ec28f-1b73-4336-9187-5d0fc04a5ef0")
    public String getIsHidden() {
        return this.isHidden;
    }

    /**
     * Sets the value of the isHidden property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("6a62e1d3-9005-4370-8921-a8453ce19af1")
    public void setIsHidden(String value) {
        this.isHidden = value;
    }

    /**
     * Gets the value of the uid property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("e94507b7-81b4-4316-afed-5c755ab85d7c")
    public String getUid() {
        return this.uid;
    }

    /**
     * Sets the value of the uid property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("6bad6339-d84e-4690-aafd-75a08dccdd3b")
    public void setUid(String value) {
        this.uid = value;
    }

    /**
     * Gets the value of the mimeType property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("1486052f-f515-4915-a64c-793fb0300105")
    public String getMimeType() {
        if (this.mimeType == null) {
            return "text/plain";
        } else {
            return this.mimeType;
        }
    }

    /**
     * Sets the value of the mimeType property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("e163cd3c-7bf8-43f6-bf7b-9d0c0c1dd523")
    public void setMimeType(String value) {
        this.mimeType = value;
    }

    /**
     * Gets the value of the description property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("733a8eb5-f131-487f-bca5-fae9f36a78c2")
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the value of the description property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("29372e22-d95a-4155-b069-4a851284e0ce")
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the extensions property.
     * @return
     * possible object is
     * {@link NoteType.Extensions }
     */
    @objid ("1d133935-92b2-4650-a6ce-2eb19e732842")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2NoteType.Jxbv2Extensions getExtensions() {
        return this.extensions;
    }

    /**
     * Sets the value of the extensions property.
     * @param value allowed object is
     * {@link NoteType.Extensions }
     */
    @objid ("a7c80a07-c179-433e-b82a-a644cf7fa881")
    public void setExtensions(org.modelio.gproject.data.module.jaxbv2.Jxbv2NoteType.Jxbv2Extensions value) {
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
    @objid ("9bdefb37-f11f-4970-a00f-e51b6148b746")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "stereotypeRef"
    })
    public static class Jxbv2Extensions {
        @objid ("03cdffe9-0b30-4c6a-99c4-43364f58ef98")
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
        @objid ("5f4ebdbf-1878-4542-aedd-89509e743279")
        public List<Jxbv2StereotypeRef> getStereotypeRef() {
            if (this.stereotypeRef == null) {
                this.stereotypeRef = new ArrayList<>();
            }
            return this.stereotypeRef;
        }

    }

}
