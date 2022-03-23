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
 * <p>Java class for _TagType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="_TagType">
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
 * &lt;attribute name="parameter-card" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="is-hidden" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="is-signed" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("cce82806-7194-45a8-8b5e-11b0ce1be82a")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_TagType", propOrder = {
    "description",
    "extensions"
})
public class Jxbv2TagType {
    @objid ("062be60c-4d71-4978-8964-6e261e7f66a4")
    @XmlAttribute(name = "name", required = true)
    protected String name;

    @objid ("d26f0f60-c2d6-474e-994f-130ec756d8f4")
    @XmlAttribute(name = "uid")
    protected String uid;

    @objid ("986ad41f-b0a2-45c2-bb09-81668b90506d")
    @XmlAttribute(name = "label")
    protected String label;

    @objid ("4da3590c-a960-46a7-9a35-79574b22be9a")
    @XmlAttribute(name = "parameter-card")
    protected String parameterCard;

    @objid ("22d11008-5bb0-4b1e-9191-3d748e81acfe")
    @XmlAttribute(name = "is-hidden")
    protected String isHidden;

    @objid ("9305e903-8376-4991-8691-049f69f0539e")
    @XmlAttribute(name = "is-signed")
    protected String isSigned;

    @objid ("0d4c8699-25d5-44b4-9d1c-de374ed3a068")
    @XmlElement(name = "Description")
    protected String description;

    @objid ("04a1d23d-fed8-4ab1-bef3-e39ef106863f")
    @XmlElement(name = "Extensions")
    protected org.modelio.gproject.data.module.jaxbv2.Jxbv2TagType.Jxbv2Extensions extensions;

    /**
     * Gets the value of the name property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("82af38e2-2f16-4b80-bf40-f9399244abc6")
    public String getName() {
        return this.name;
    }

    /**
     * Sets the value of the name property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("90c86ab2-c279-4d00-8bbd-e3f1b8e3c5b3")
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the uid property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("85c0d28d-de40-4f11-8473-55d9683513be")
    public String getUid() {
        return this.uid;
    }

    /**
     * Sets the value of the uid property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("af627d28-4f1c-47cd-b82d-60efdeb06b32")
    public void setUid(String value) {
        this.uid = value;
    }

    /**
     * Gets the value of the label property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("8e46f058-1d7f-4f49-93cc-093f5f19f548")
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the value of the label property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("67892a34-f2e2-4814-8d19-e1f92841ca78")
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the parameterCard property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("68bfebb8-164a-4d33-9a5c-d5d6c752730e")
    public String getParameterCard() {
        return this.parameterCard;
    }

    /**
     * Sets the value of the parameterCard property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cbf05206-fe47-4daa-ba6f-87b024b346ad")
    public void setParameterCard(String value) {
        this.parameterCard = value;
    }

    /**
     * Gets the value of the isHidden property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("3efa2ecb-3486-4eea-9559-62a9d2157f57")
    public String getIsHidden() {
        return this.isHidden;
    }

    /**
     * Sets the value of the isHidden property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cb241851-8e54-42be-8f7d-fa830de3f646")
    public void setIsHidden(String value) {
        this.isHidden = value;
    }

    /**
     * Gets the value of the isSigned property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("b1599daa-9b7d-45ba-99d7-3fc998a5ccfc")
    public String getIsSigned() {
        return this.isSigned;
    }

    /**
     * Sets the value of the isSigned property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("c5ded916-a39a-4220-a8ae-972bbf30ea4c")
    public void setIsSigned(String value) {
        this.isSigned = value;
    }

    /**
     * Gets the value of the description property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("03b6fd9a-80c6-4c03-ae52-bf8af5b77823")
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the value of the description property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("0fc683f5-41ec-455d-b8d8-43b5d78a1c13")
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the extensions property.
     * @return
     * possible object is
     * {@link TagType.Extensions }
     */
    @objid ("c7615ccd-8661-41f8-806d-f1ce55fb46e6")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2TagType.Jxbv2Extensions getExtensions() {
        return this.extensions;
    }

    /**
     * Sets the value of the extensions property.
     * @param value allowed object is
     * {@link TagType.Extensions }
     */
    @objid ("ad62164d-dee8-4322-9d57-3c26b7c9d60b")
    public void setExtensions(org.modelio.gproject.data.module.jaxbv2.Jxbv2TagType.Jxbv2Extensions value) {
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
    @objid ("9f8aa878-f945-4d33-85ff-3aedc0f18a71")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "stereotypeRef"
        })
    public static class Jxbv2Extensions {
        @objid ("9fc71853-ceb3-42ac-9054-764eec9427ac")
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
        @objid ("58a4aa59-83ac-4cbf-b911-980df2bc438b")
        public List<Jxbv2StereotypeRef> getStereotypeRef() {
            if (this.stereotypeRef == null) {
                this.stereotypeRef = new ArrayList<>();
            }
            return this.stereotypeRef;
        }

    }

}
