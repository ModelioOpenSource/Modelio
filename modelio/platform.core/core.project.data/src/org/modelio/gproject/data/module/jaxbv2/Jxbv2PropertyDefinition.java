/* 
 * Copyright 2013-2019 Modeliosoft
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
 * <p>Java class for _PropertyDefinition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="_PropertyDefinition">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="TypeRef">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="Parameter" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="default-value" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="is-editable" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("5128ff30-d735-439b-a2ed-4c1d213dbd62")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_PropertyDefinition", propOrder = {
    "typeRef",
    "description",
    "parameter"
})
public class Jxbv2PropertyDefinition {
    @objid ("c6037036-888e-41b2-b465-80447b809698")
    @XmlAttribute(name = "id", required = true)
    protected String id;

    @objid ("dca49c40-43b6-4c78-adda-b082430d351b")
    @XmlAttribute(name = "label")
    protected String label;

    @objid ("b7a957ca-6b86-42a5-9e58-1cd50e45fc91")
    @XmlAttribute(name = "default-value")
    protected String defaultValue;

    @objid ("02ceca7b-5de0-4551-9dbe-1e89b16b0ae0")
    @XmlAttribute(name = "is-editable")
    protected Boolean isEditable;

    @objid ("4280001e-6bfa-4a6b-88b8-d3beb81f89bf")
    @XmlAttribute(name = "uid")
    protected String uid;

    @objid ("a2179734-8078-40b6-b889-4c74dfce40a4")
    @XmlElement(name = "Description")
    protected String description;

    @objid ("c9b8b2d7-8d31-416a-8084-f095fd3c5922")
    @XmlElement(name = "TypeRef", required = true)
    protected org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyDefinition.Jxbv2TypeRef typeRef;

    @objid ("377cbec1-0a86-43bf-8ac7-5eb0338fbbf7")
    @XmlElement(name = "Parameter")
    protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyDefinition.Jxbv2Parameter> parameter;

    /**
     * Gets the value of the typeRef property.
     * @return
     * possible object is
     * {@link PropertyDefinition.TypeRef }
     */
    @objid ("3058a6ac-99d3-485c-af7f-36d5cb13750c")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyDefinition.Jxbv2TypeRef getTypeRef() {
        return this.typeRef;
    }

    /**
     * Sets the value of the typeRef property.
     * 
     * @param value allowed object is
     * {@link PropertyDefinition.TypeRef }
     */
    @objid ("338399c4-e5a1-4b99-8bae-9979e6c02b60")
    public void setTypeRef(org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyDefinition.Jxbv2TypeRef value) {
        this.typeRef = value;
    }

    /**
     * Gets the value of the id property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cd43f207-0872-490c-9e33-7ccf663bc783")
    public String getId() {
        return this.id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("5de2b6b5-8974-45c9-83f3-72c36d834391")
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the label property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("256f59fc-e0e1-4208-9bcc-82af288b7cb4")
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("df21ffa2-4caf-407e-a4a9-64a8dd68b151")
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the defaultValue property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("1499535d-4151-4e27-bf7a-b35c7264a116")
    public String getDefaultValue() {
        return this.defaultValue;
    }

    /**
     * Sets the value of the defaultValue property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("78337ffe-9f0f-4530-9cca-584b42e42e6d")
    public void setDefaultValue(String value) {
        this.defaultValue = value;
    }

    /**
     * Gets the value of the isEditable property.
     * @return
     * possible object is
     * {@link Boolean }
     */
    @objid ("39707635-6bd8-4735-b071-4a48169ce1c1")
    public boolean isIsEditable() {
        if (this.isEditable == null) {
            return true;
        } else {
            return this.isEditable;
        }
    }

    /**
     * Sets the value of the isEditable property.
     * 
     * @param value allowed object is
     * {@link Boolean }
     */
    @objid ("00cc4ed3-96e1-4b24-971a-637727d65e67")
    public void setIsEditable(Boolean value) {
        this.isEditable = value;
    }

    /**
     * Gets the value of the uid property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("bb2e53e9-832b-4228-93b3-afe840faf3ec")
    public String getUid() {
        return this.uid;
    }

    /**
     * Sets the value of the uid property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("1dfa99c4-3891-4b1f-ad46-77d8d70d8fbb")
    public void setUid(String value) {
        this.uid = value;
    }

    /**
     * Gets the value of the description property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("99755e39-7c23-42d1-a5f8-b670826aa0db")
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("a36dc02d-8b19-48cb-beed-b3f3fb86da9e")
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the parameter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     * getParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PropertyDefinition.Parameter }
     */
    @objid ("ac56f07d-f41c-4d80-a231-0d03b52b092b")
    public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyDefinition.Jxbv2Parameter> getParameter() {
        if (this.parameter == null) {
            this.parameter = new ArrayList<>();
        }
        return this.parameter;
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
     * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @objid ("80a3c8d4-b710-4eec-9ced-d5d8b04d15af")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Jxbv2TypeRef {
        @objid ("14ca1a64-6841-4406-8dbd-bbf7389f4432")
        @XmlAttribute(name = "id", required = true)
        protected String id;

        /**
         * Gets the value of the id property.
         * @return
         * possible object is
         * {@link String }
         */
        @objid ("a5c76905-3069-460b-9894-56af25326969")
        public String getId() {
            return this.id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value allowed object is
         * {@link String }
         */
        @objid ("f7151852-cc56-4121-b7cc-33e83388ff0f")
        public void setId(String value) {
            this.id = value;
        }

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
     * &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @objid ("b9333abf-0a3c-44f3-9c5d-6e973de3b898")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Jxbv2Parameter {
        @objid ("d67fd133-5075-42f0-8ff9-cb94f917b49b")
        @XmlAttribute(name = "name", required = true)
        protected String name;

        @objid ("9f45c0bf-dcc6-4f2c-ae5c-a7f384bd1af3")
        @XmlAttribute(name = "value", required = true)
        protected String value;

        /**
         * Gets the value of the name property.
         * @return
         * possible object is
         * {@link String }
         */
        @objid ("bcf173e5-0911-4a95-85fd-3f07916b04b7")
        public String getName() {
            return this.name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value allowed object is
         * {@link String }
         */
        @objid ("e883a8ad-b170-4d58-8964-d592ccb66bdf")
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the value property.
         * @return
         * possible object is
         * {@link String }
         */
        @objid ("db7a3f36-4bce-4733-9335-566de1f664b3")
        public String getValue() {
            return this.value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value allowed object is
         * {@link String }
         */
        @objid ("8875b897-0bb8-457d-9908-b0ae7d75a7ef")
        public void setValue(String value) {
            this.value = value;
        }

    }

}
