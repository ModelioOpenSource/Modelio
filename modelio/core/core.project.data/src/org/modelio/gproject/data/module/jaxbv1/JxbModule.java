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
package org.modelio.gproject.data.module.jaxbv1;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;choice maxOccurs="unbounded" minOccurs="0">
 * &lt;element name="parameter" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;choice maxOccurs="unbounded" minOccurs="0">
 * &lt;element name="enumeration" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;choice maxOccurs="unbounded" minOccurs="0">
 * &lt;element name="literal" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/choice>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;/choice>
 * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="group" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="default-value" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="profile" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;choice maxOccurs="unbounded" minOccurs="0">
 * &lt;element name="stereotype" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;choice maxOccurs="unbounded" minOccurs="0">
 * &lt;element name="icons" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;choice maxOccurs="unbounded" minOccurs="0">
 * &lt;element name="small" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="explorer" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="diagram" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/choice>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="taggedvalues" type="{}taggedvalues" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;element name="notetype" type="{}notetype" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;element name="externdocumenttype" type="{}externdocumenttype" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/choice>
 * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="metaclass" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="owner-stereotype" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="is-hidden" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="anonymous-stereotype" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;choice maxOccurs="unbounded" minOccurs="0">
 * &lt;element name="taggedvalues" type="{}taggedvalues" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;element name="notetype" type="{}notetype" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;element name="externdocumenttype" type="{}externdocumenttype" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/choice>
 * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="metaclass" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/choice>
 * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="gui" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;choice maxOccurs="unbounded" minOccurs="0">
 * &lt;element name="property-page" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;simpleContent>
 * &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="class" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="image" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/extension>
 * &lt;/simpleContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="command" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;extension base="{}contextual-command">
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="element-creation-command" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;extension base="{}contextual-command">
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="customized-diagram" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;choice maxOccurs="unbounded" minOccurs="0">
 * &lt;element name="palette" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;choice maxOccurs="unbounded" minOccurs="0">
 * &lt;element name="diagram-command" type="{}diagram-command" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;element name="diagram-command-box" type="{}diagram-command-box" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;element name="diagram-command-link" type="{}diagram-command-link" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;element name="diagram-command-attachedbox" type="{}diagram-command-box" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/choice>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="style" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;choice maxOccurs="unbounded" minOccurs="0">
 * &lt;element name="style-property" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="metaclass" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="stereotype" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="key" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/choice>
 * &lt;attribute name="base-style" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/choice>
 * &lt;attribute name="base-diagram" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="stereotype" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="keepBasePalette" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/choice>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="dependencies">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;choice maxOccurs="unbounded" minOccurs="0">
 * &lt;element name="required" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="optional" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="ramc" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/choice>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="classpath" type="{}classpath"/>
 * &lt;element name="docpath" type="{}docpath" minOccurs="0"/>
 * &lt;/choice>
 * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="image" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="author" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="class" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="licenseRequired" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 * &lt;attribute name="binaryversion" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("caee75e5-d6ff-11e1-9f03-001ec947ccaf")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "parameterOrProfileOrGui"
})
@XmlRootElement(name = "module")
public class JxbModule {
    @objid ("cae74ec8-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected Boolean licenseRequired;

    @objid ("cae74eca-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String uid;

    @objid ("cae74ecb-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String name;

    @objid ("cae74ecc-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String image;

    @objid ("cae74ecd-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String author;

    @objid ("cae74ece-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String description;

    @objid ("cae74e55-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String version;

    @objid ("cae74e56-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute(name = "class")
    protected String clazz;

    @objid ("cae74e57-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String binaryversion;

    @objid ("cae74ec9-d6ff-11e1-9f03-001ec947ccaf")
    @XmlElements({
            @XmlElement(name = "docpath", type = JxbDocpath.class),
            @XmlElement(name = "classpath", type = JxbClasspath.class),
            @XmlElement(name = "gui", type = JxbModule.Gui.class),
            @XmlElement(name = "parameter", type = JxbModule.JxbParameter.class),
            @XmlElement(name = "profile", type = JxbModule.JxbProfile.class),
            @XmlElement(name = "dependencies", type = JxbModule.Dependencies.class)
        })
    protected List<Object> parameterOrProfileOrGui;

    /**
     * Gets the value of the uid property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae9b0d4-d6ff-11e1-9f03-001ec947ccaf")
    public String getUid() {
        return this.uid;
    }

    /**
     * Sets the value of the uid property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae9b0d5-d6ff-11e1-9f03-001ec947ccaf")
    public void setUid(final String value) {
        this.uid = value;
    }

    /**
     * Gets the value of the name property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae9b0d6-d6ff-11e1-9f03-001ec947ccaf")
    public String getName() {
        return this.name;
    }

    /**
     * Sets the value of the name property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae9b0d7-d6ff-11e1-9f03-001ec947ccaf")
    public void setName(final String value) {
        this.name = value;
    }

    /**
     * Gets the value of the image property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae9b0d8-d6ff-11e1-9f03-001ec947ccaf")
    public String getImage() {
        return this.image;
    }

    /**
     * Sets the value of the image property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae9b1ab-d6ff-11e1-9f03-001ec947ccaf")
    public void setImage(final String value) {
        this.image = value;
    }

    /**
     * Gets the value of the author property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae9b1ac-d6ff-11e1-9f03-001ec947ccaf")
    public String getAuthor() {
        return this.author;
    }

    /**
     * Sets the value of the author property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae9b1ad-d6ff-11e1-9f03-001ec947ccaf")
    public void setAuthor(final String value) {
        this.author = value;
    }

    /**
     * Gets the value of the description property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae9b1ae-d6ff-11e1-9f03-001ec947ccaf")
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the value of the description property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae9b1af-d6ff-11e1-9f03-001ec947ccaf")
    public void setDescription(final String value) {
        this.description = value;
    }

    /**
     * Gets the value of the version property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae9b1b0-d6ff-11e1-9f03-001ec947ccaf")
    public String getVersion() {
        return this.version;
    }

    /**
     * Sets the value of the version property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae9b1b1-d6ff-11e1-9f03-001ec947ccaf")
    public void setVersion(final String value) {
        this.version = value;
    }

    /**
     * Gets the value of the clazz property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae74ec2-d6ff-11e1-9f03-001ec947ccaf")
    public String getClazz() {
        return this.clazz;
    }

    /**
     * Sets the value of the clazz property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae74ec3-d6ff-11e1-9f03-001ec947ccaf")
    public void setClazz(final String value) {
        this.clazz = value;
    }

    /**
     * Gets the value of the licenseRequired property.
     * @return
     * possible object is
     * {@link Boolean }
     */
    @objid ("cae74ec4-d6ff-11e1-9f03-001ec947ccaf")
    public Boolean isLicenseRequired() {
        return this.licenseRequired;
    }

    /**
     * Sets the value of the licenseRequired property.
     * @param value allowed object is
     * {@link Boolean }
     */
    @objid ("cae74ec5-d6ff-11e1-9f03-001ec947ccaf")
    public void setLicenseRequired(final Boolean value) {
        this.licenseRequired = value;
    }

    /**
     * Gets the value of the binaryversion property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae74ec6-d6ff-11e1-9f03-001ec947ccaf")
    public String getBinaryversion() {
        return this.binaryversion;
    }

    /**
     * Sets the value of the binaryversion property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae74ec7-d6ff-11e1-9f03-001ec947ccaf")
    public void setBinaryversion(final String value) {
        this.binaryversion = value;
    }

    /**
     * Gets the value of the parameterOrProfileOrGui property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameterOrProfileOrGui property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     * getParameterOrProfileOrGui().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JxbDocpath }
     * {@link JxbClasspath }
     * {@link JxbModule.Gui }
     * {@link JxbModule.JxbParameter }
     * {@link JxbModule.JxbProfile }
     * {@link JxbModule.Dependencies }
     */
    @objid ("cae74e58-d6ff-11e1-9f03-001ec947ccaf")
    public List<Object> getParameterOrProfileOrGui() {
        if (this.parameterOrProfileOrGui == null) {
            this.parameterOrProfileOrGui = new ArrayList<>();
        }
        return this.parameterOrProfileOrGui;
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
     * &lt;choice maxOccurs="unbounded" minOccurs="0">
     * &lt;element name="required" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="optional" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="ramc" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/choice>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @objid ("caec13be-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "requiredOrOptionalOrRamc"
        })
    public static class Dependencies {
        @objid ("cae9b0c7-d6ff-11e1-9f03-001ec947ccaf")
        @XmlElements({
                    @XmlElement(name = "optional", type = JxbModule.Dependencies.Optional.class),
                    @XmlElement(name = "ramc", type = JxbModule.Dependencies.Ramc.class),
                    @XmlElement(name = "required", type = JxbModule.Dependencies.Required.class)
                })
        protected List<Object> requiredOrOptionalOrRamc;

        /**
         * Gets the value of the requiredOrOptionalOrRamc property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the requiredOrOptionalOrRamc property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         * getRequiredOrOptionalOrRamc().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link JxbModule.Dependencies.Optional }
         * {@link JxbModule.Dependencies.Ramc }
         * {@link JxbModule.Dependencies.Required }
         */
        @objid ("cae74f7e-d6ff-11e1-9f03-001ec947ccaf")
        public List<Object> getRequiredOrOptionalOrRamc() {
            if (this.requiredOrOptionalOrRamc == null) {
                this.requiredOrOptionalOrRamc = new ArrayList<>();
            }
            return this.requiredOrOptionalOrRamc;
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
         * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("cae74f7b-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Optional {
            @objid ("cae9b2e9-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute
            protected String name;

            @objid ("cae9b2ea-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute
            protected String version;

            /**
             * Gets the value of the name property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("cae9b2d6-d6ff-11e1-9f03-001ec947ccaf")
            public String getName() {
                return this.name;
            }

            /**
             * Sets the value of the name property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("cae9b2da-d6ff-11e1-9f03-001ec947ccaf")
            public void setName(final String value) {
                this.name = value;
            }

            /**
             * Gets the value of the version property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("cae9b2db-d6ff-11e1-9f03-001ec947ccaf")
            public String getVersion() {
                return this.version;
            }

            /**
             * Sets the value of the version property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("cae9b2e8-d6ff-11e1-9f03-001ec947ccaf")
            public void setVersion(final String value) {
                this.version = value;
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
         * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("cae74f7c-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Required {
            @objid ("cae74f3b-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute
            protected String name;

            @objid ("cae74f3c-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute
            protected String version;

            /**
             * Gets the value of the name property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("cae74f37-d6ff-11e1-9f03-001ec947ccaf")
            public String getName() {
                return this.name;
            }

            /**
             * Sets the value of the name property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("cae74f38-d6ff-11e1-9f03-001ec947ccaf")
            public void setName(final String value) {
                this.name = value;
            }

            /**
             * Gets the value of the version property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("cae74f39-d6ff-11e1-9f03-001ec947ccaf")
            public String getVersion() {
                return this.version;
            }

            /**
             * Sets the value of the version property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("cae74f3a-d6ff-11e1-9f03-001ec947ccaf")
            public void setVersion(final String value) {
                this.version = value;
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
         * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("cae74f7d-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Ramc {
            @objid ("cae9b2ec-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute
            protected String name;

            @objid ("cae9b2ed-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute
            protected String version;

            /**
             * Gets the value of the name property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("caee7601-d6ff-11e1-9f03-001ec947ccaf")
            public String getName() {
                return this.name;
            }

            /**
             * Sets the value of the name property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("caee7602-d6ff-11e1-9f03-001ec947ccaf")
            public void setName(final String value) {
                this.name = value;
            }

            /**
             * Gets the value of the version property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("caee7603-d6ff-11e1-9f03-001ec947ccaf")
            public String getVersion() {
                return this.version;
            }

            /**
             * Sets the value of the version property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("cae9b2eb-d6ff-11e1-9f03-001ec947ccaf")
            public void setVersion(final String value) {
                this.version = value;
            }

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
     * &lt;choice maxOccurs="unbounded" minOccurs="0">
     * &lt;element name="property-page" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;simpleContent>
     * &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="class" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="image" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/extension>
     * &lt;/simpleContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="command" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;extension base="{}contextual-command">
     * &lt;/extension>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="element-creation-command" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;extension base="{}contextual-command">
     * &lt;/extension>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="customized-diagram" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;choice maxOccurs="unbounded" minOccurs="0">
     * &lt;element name="palette" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;choice maxOccurs="unbounded" minOccurs="0">
     * &lt;element name="diagram-command" type="{}diagram-command" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;element name="diagram-command-box" type="{}diagram-command-box" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;element name="diagram-command-link" type="{}diagram-command-link" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;element name="diagram-command-attachedbox" type="{}diagram-command-box" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;/choice>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="style" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;choice maxOccurs="unbounded" minOccurs="0">
     * &lt;element name="style-property" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;attribute name="metaclass" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="stereotype" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="key" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/choice>
     * &lt;attribute name="base-style" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/choice>
     * &lt;attribute name="base-diagram" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="stereotype" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="keepBasePalette" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/choice>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @objid ("caee75b8-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "propertyPageOrCommandOrElementCreationCommand"
        })
    public static class Gui {
        @objid ("cae9b0de-d6ff-11e1-9f03-001ec947ccaf")
        @XmlElements({
                    @XmlElement(name = "property-page", type = JxbModule.Gui.PropertyPage.class),
                    @XmlElement(name = "element-creation-command", type = JxbModule.Gui.ElementCreationCommand.class),
                    @XmlElement(name = "customized-diagram", type = JxbModule.Gui.CustomizedDiagram.class),
                    @XmlElement(name = "command", type = JxbModule.Gui.Command.class)
                })
        protected List<Object> propertyPageOrCommandOrElementCreationCommand;

        /**
         * Gets the value of the propertyPageOrCommandOrElementCreationCommand property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the propertyPageOrCommandOrElementCreationCommand property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         * getPropertyPageOrCommandOrElementCreationCommand().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link JxbModule.Gui.PropertyPage }
         * {@link JxbModule.Gui.ElementCreationCommand }
         * {@link JxbModule.Gui.CustomizedDiagram }
         * {@link JxbModule.Gui.Command }
         */
        @objid ("cae9b0df-d6ff-11e1-9f03-001ec947ccaf")
        public List<Object> getPropertyPageOrCommandOrElementCreationCommand() {
            if (this.propertyPageOrCommandOrElementCreationCommand == null) {
                this.propertyPageOrCommandOrElementCreationCommand = new ArrayList<>();
            }
            return this.propertyPageOrCommandOrElementCreationCommand;
        }

        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;extension base="{}contextual-command">
         * &lt;/extension>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("cae74f7f-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Command extends JxbContextualCommand {
// empty
            
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
         * &lt;choice maxOccurs="unbounded" minOccurs="0">
         * &lt;element name="palette" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;choice maxOccurs="unbounded" minOccurs="0">
         * &lt;element name="diagram-command" type="{}diagram-command" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;element name="diagram-command-box" type="{}diagram-command-box" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;element name="diagram-command-link" type="{}diagram-command-link" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;element name="diagram-command-attachedbox" type="{}diagram-command-box" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;/choice>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;element name="style" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;choice maxOccurs="unbounded" minOccurs="0">
         * &lt;element name="style-property" maxOccurs="unbounded" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;attribute name="metaclass" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="stereotype" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="key" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/choice>
         * &lt;attribute name="base-style" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/choice>
         * &lt;attribute name="base-diagram" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="stereotype" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="keepBasePalette" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("cae74f80-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                    "paletteOrStyle"
                })
        public static class CustomizedDiagram {
            @objid ("cae9b305-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute
            protected Boolean keepBasePalette;

            @objid ("cae9b306-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute(name = "base-diagram")
            protected String baseDiagram;

            @objid ("cae9b307-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute
            protected String stereotype;

            @objid ("cae74f3d-d6ff-11e1-9f03-001ec947ccaf")
            @XmlElements({
                            @XmlElement(name = "palette", type = JxbModule.Gui.CustomizedDiagram.Palette.class),
                            @XmlElement(name = "style", type = JxbModule.Gui.CustomizedDiagram.Style.class)
                        })
            protected List<Object> paletteOrStyle;

            /**
             * Gets the value of the baseDiagram property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("cae9b2fd-d6ff-11e1-9f03-001ec947ccaf")
            public String getBaseDiagram() {
                return this.baseDiagram;
            }

            /**
             * Sets the value of the baseDiagram property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("cae9b2fe-d6ff-11e1-9f03-001ec947ccaf")
            public void setBaseDiagram(final String value) {
                this.baseDiagram = value;
            }

            /**
             * Gets the value of the stereotype property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("cae9b301-d6ff-11e1-9f03-001ec947ccaf")
            public String getStereotype() {
                return this.stereotype;
            }

            /**
             * Sets the value of the stereotype property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("cae9b302-d6ff-11e1-9f03-001ec947ccaf")
            public void setStereotype(final String value) {
                this.stereotype = value;
            }

            /**
             * Gets the value of the keepBasePalette property.
             * @return
             * possible object is
             * {@link Boolean }
             */
            @objid ("cae9b303-d6ff-11e1-9f03-001ec947ccaf")
            public Boolean isKeepBasePalette() {
                return this.keepBasePalette;
            }

            /**
             * Sets the value of the keepBasePalette property.
             * @param value allowed object is
             * {@link Boolean }
             */
            @objid ("cae9b304-d6ff-11e1-9f03-001ec947ccaf")
            public void setKeepBasePalette(final Boolean value) {
                this.keepBasePalette = value;
            }

            /**
             * Gets the value of the paletteOrStyle property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the paletteOrStyle property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             * getPaletteOrStyle().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link JxbModule.Gui.CustomizedDiagram.Palette }
             * {@link JxbModule.Gui.CustomizedDiagram.Style }
             */
            @objid ("cae9b308-d6ff-11e1-9f03-001ec947ccaf")
            public List<Object> getPaletteOrStyle() {
                if (this.paletteOrStyle == null) {
                    this.paletteOrStyle = new ArrayList<>();
                }
                return this.paletteOrStyle;
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
             * &lt;choice maxOccurs="unbounded" minOccurs="0">
             * &lt;element name="diagram-command" type="{}diagram-command" maxOccurs="unbounded" minOccurs="0"/>
             * &lt;element name="diagram-command-box" type="{}diagram-command-box" maxOccurs="unbounded" minOccurs="0"/>
             * &lt;element name="diagram-command-link" type="{}diagram-command-link" maxOccurs="unbounded" minOccurs="0"/>
             * &lt;element name="diagram-command-attachedbox" type="{}diagram-command-box" maxOccurs="unbounded" minOccurs="0"/>
             * &lt;/choice>
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             */
            @objid ("cae9b2fa-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                            "diagramCommandOrDiagramCommandBoxOrDiagramCommandLink"
                        })
            public static class Palette {
                @objid ("caec1450-d6ff-11e1-9f03-001ec947ccaf")
                @XmlElementRefs({
                                    @XmlElementRef(name = "diagram-command-link", type = JAXBElement.class),
                                    @XmlElementRef(name = "diagram-command-box", type = JAXBElement.class),
                                    @XmlElementRef(name = "diagram-command", type = JAXBElement.class),
                                    @XmlElementRef(name = "diagram-command-attachedbox", type = JAXBElement.class)
                                })
                protected List<JAXBElement<?>> diagramCommandOrDiagramCommandBoxOrDiagramCommandLink;

                /**
                 * Gets the value of the diagramCommandOrDiagramCommandBoxOrDiagramCommandLink property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the diagramCommandOrDiagramCommandBoxOrDiagramCommandLink property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 * getDiagramCommandOrDiagramCommandBoxOrDiagramCommandLink().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link JAXBElement }{@code <}{@link JxbDiagramCommand }{@code >}
                 * {@link JAXBElement }{@code <}{@link JxbDiagramCommandLink }{@code >}
                 * {@link JAXBElement }{@code <}{@link JxbDiagramCommandBox }{@code >}
                 * {@link JAXBElement }{@code <}{@link JxbDiagramCommandBox }{@code >}
                 */
                @objid ("caec1451-d6ff-11e1-9f03-001ec947ccaf")
                public List<JAXBElement<?>> getDiagramCommandOrDiagramCommandBoxOrDiagramCommandLink() {
                    if (this.diagramCommandOrDiagramCommandBoxOrDiagramCommandLink == null) {
                        this.diagramCommandOrDiagramCommandBoxOrDiagramCommandLink = new ArrayList<>();
                    }
                    return this.diagramCommandOrDiagramCommandBoxOrDiagramCommandLink;
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
             * &lt;choice maxOccurs="unbounded" minOccurs="0">
             * &lt;element name="style-property" maxOccurs="unbounded" minOccurs="0">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;attribute name="metaclass" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="stereotype" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="key" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;/choice>
             * &lt;attribute name="base-style" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             */
            @objid ("cae9b2fc-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                            "styleProperty"
                        })
            public static class Style {
                @objid ("caec145a-d6ff-11e1-9f03-001ec947ccaf")
                @XmlAttribute(name = "base-style")
                protected String baseStyle;

                @objid ("caec1456-d6ff-11e1-9f03-001ec947ccaf")
                @XmlElement(name = "style-property")
                protected List<org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.CustomizedDiagram.Style.StyleProperty> styleProperty;

                /**
                 * Gets the value of the styleProperty property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the styleProperty property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 * getStyleProperty().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link JxbModule.Gui.CustomizedDiagram.Style.StyleProperty }
                 */
                @objid ("caec1457-d6ff-11e1-9f03-001ec947ccaf")
                public List<org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.CustomizedDiagram.Style.StyleProperty> getStyleProperty() {
                    if (this.styleProperty == null) {
                        this.styleProperty = new ArrayList<>();
                    }
                    return this.styleProperty;
                }

                /**
                 * Gets the value of the baseStyle property.
                 * @return
                 * possible object is
                 * {@link String }
                 */
                @objid ("caec1458-d6ff-11e1-9f03-001ec947ccaf")
                public String getBaseStyle() {
                    return this.baseStyle;
                }

                /**
                 * Sets the value of the baseStyle property.
                 * @param value allowed object is
                 * {@link String }
                 */
                @objid ("caec1459-d6ff-11e1-9f03-001ec947ccaf")
                public void setBaseStyle(final String value) {
                    this.baseStyle = value;
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
                 * &lt;attribute name="metaclass" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;attribute name="stereotype" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;attribute name="key" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;/restriction>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 */
                @objid ("caec1455-d6ff-11e1-9f03-001ec947ccaf")
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class StyleProperty {
                    @objid ("caec1536-d6ff-11e1-9f03-001ec947ccaf")
                    @XmlAttribute
                    protected String metaclass;

                    @objid ("caec1537-d6ff-11e1-9f03-001ec947ccaf")
                    @XmlAttribute
                    protected String stereotype;

                    @objid ("caec1538-d6ff-11e1-9f03-001ec947ccaf")
                    @XmlAttribute
                    protected String key;

                    @objid ("caec1539-d6ff-11e1-9f03-001ec947ccaf")
                    @XmlAttribute
                    protected String value;

                    /**
                     * Gets the value of the metaclass property.
                     * @return
                     * possible object is
                     * {@link String }
                     */
                    @objid ("caec152e-d6ff-11e1-9f03-001ec947ccaf")
                    public String getMetaclass() {
                        return this.metaclass;
                    }

                    /**
                     * Sets the value of the metaclass property.
                     * @param value allowed object is
                     * {@link String }
                     */
                    @objid ("caec152f-d6ff-11e1-9f03-001ec947ccaf")
                    public void setMetaclass(final String value) {
                        this.metaclass = value;
                    }

                    /**
                     * Gets the value of the stereotype property.
                     * @return
                     * possible object is
                     * {@link String }
                     */
                    @objid ("caec1530-d6ff-11e1-9f03-001ec947ccaf")
                    public String getStereotype() {
                        return this.stereotype;
                    }

                    /**
                     * Sets the value of the stereotype property.
                     * @param value allowed object is
                     * {@link String }
                     */
                    @objid ("caec1531-d6ff-11e1-9f03-001ec947ccaf")
                    public void setStereotype(final String value) {
                        this.stereotype = value;
                    }

                    /**
                     * Gets the value of the key property.
                     * @return
                     * possible object is
                     * {@link String }
                     */
                    @objid ("caec1532-d6ff-11e1-9f03-001ec947ccaf")
                    public String getKey() {
                        return this.key;
                    }

                    /**
                     * Sets the value of the key property.
                     * @param value allowed object is
                     * {@link String }
                     */
                    @objid ("caec1533-d6ff-11e1-9f03-001ec947ccaf")
                    public void setKey(final String value) {
                        this.key = value;
                    }

                    /**
                     * Gets the value of the value property.
                     * @return
                     * possible object is
                     * {@link String }
                     */
                    @objid ("caec1534-d6ff-11e1-9f03-001ec947ccaf")
                    public String getValue() {
                        return this.value;
                    }

                    /**
                     * Sets the value of the value property.
                     * @param value allowed object is
                     * {@link String }
                     */
                    @objid ("caec1535-d6ff-11e1-9f03-001ec947ccaf")
                    public void setValue(final String value) {
                        this.value = value;
                    }

                }

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
         * &lt;extension base="{}contextual-command">
         * &lt;/extension>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("cae74f81-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class ElementCreationCommand extends JxbContextualCommand {
//          empty
            
        }

        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         * &lt;simpleContent>
         * &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="class" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="image" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/extension>
         * &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("cae9b0dd-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                    "value"
                })
        public static class PropertyPage {
            @objid ("cae9b321-d6ff-11e1-9f03-001ec947ccaf")
            @XmlValue
            protected String value;

            @objid ("cae9b322-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute
            protected String name;

            @objid ("cae9b323-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute(name = "class")
            protected String clazz;

            @objid ("cae9b324-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute
            protected String label;

            @objid ("cae9b325-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute
            protected String image;

            /**
             * Gets the value of the value property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("cae9b310-d6ff-11e1-9f03-001ec947ccaf")
            public String getValue() {
                return this.value;
            }

            /**
             * Sets the value of the value property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("cae9b318-d6ff-11e1-9f03-001ec947ccaf")
            public void setValue(final String value) {
                this.value = value;
            }

            /**
             * Gets the value of the name property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("cae9b319-d6ff-11e1-9f03-001ec947ccaf")
            public String getName() {
                return this.name;
            }

            /**
             * Sets the value of the name property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("cae9b31a-d6ff-11e1-9f03-001ec947ccaf")
            public void setName(final String value) {
                this.name = value;
            }

            /**
             * Gets the value of the clazz property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("cae9b31b-d6ff-11e1-9f03-001ec947ccaf")
            public String getClazz() {
                return this.clazz;
            }

            /**
             * Sets the value of the clazz property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("cae9b31c-d6ff-11e1-9f03-001ec947ccaf")
            public void setClazz(final String value) {
                this.clazz = value;
            }

            /**
             * Gets the value of the label property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("cae9b31d-d6ff-11e1-9f03-001ec947ccaf")
            public String getLabel() {
                return this.label;
            }

            /**
             * Sets the value of the label property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("cae9b31e-d6ff-11e1-9f03-001ec947ccaf")
            public void setLabel(final String value) {
                this.label = value;
            }

            /**
             * Gets the value of the image property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("cae9b31f-d6ff-11e1-9f03-001ec947ccaf")
            public String getImage() {
                return this.image;
            }

            /**
             * Sets the value of the image property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("cae9b320-d6ff-11e1-9f03-001ec947ccaf")
            public void setImage(final String value) {
                this.image = value;
            }

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
     * &lt;choice maxOccurs="unbounded" minOccurs="0">
     * &lt;element name="enumeration" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;choice maxOccurs="unbounded" minOccurs="0">
     * &lt;element name="literal" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/choice>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     * &lt;/choice>
     * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="group" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="default-value" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @objid ("caee75b9-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "enumerationOrDescription"
        })
    public static class JxbParameter {
        @objid ("cae9b0f3-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAttribute
        protected String name;

        @objid ("cae9b0f4-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAttribute
        protected String label;

        @objid ("cae9b0f5-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAttribute
        protected String group;

        @objid ("cae9b0f6-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAttribute(name = "default-value")
        protected String defaultValue;

        @objid ("cae9b0f7-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAttribute
        protected String type;

        @objid ("cae9b0f8-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAttribute
        protected String uid;

        @objid ("cae9b0f2-d6ff-11e1-9f03-001ec947ccaf")
        @XmlElements({
                    @XmlElement(name = "enumeration", type = JxbModule.JxbParameter.JxbEnumeration.class),
                    @XmlElement(name = "description", type = String.class)
                })
        protected List<Object> enumerationOrDescription;

        /**
         * Gets the value of the name property.
         * @return
         * possible object is
         * {@link String }
         */
        @objid ("cae9b0e6-d6ff-11e1-9f03-001ec947ccaf")
        public String getName() {
            return this.name;
        }

        /**
         * Sets the value of the name property.
         * @param value allowed object is
         * {@link String }
         */
        @objid ("cae9b0e7-d6ff-11e1-9f03-001ec947ccaf")
        public void setName(final String value) {
            this.name = value;
        }

        /**
         * Gets the value of the label property.
         * @return
         * possible object is
         * {@link String }
         */
        @objid ("cae9b0e8-d6ff-11e1-9f03-001ec947ccaf")
        public String getLabel() {
            return this.label;
        }

        /**
         * Sets the value of the label property.
         * @param value allowed object is
         * {@link String }
         */
        @objid ("cae9b0e9-d6ff-11e1-9f03-001ec947ccaf")
        public void setLabel(final String value) {
            this.label = value;
        }

        /**
         * Gets the value of the group property.
         * @return
         * possible object is
         * {@link String }
         */
        @objid ("cae9b0ea-d6ff-11e1-9f03-001ec947ccaf")
        public String getGroup() {
            return this.group;
        }

        /**
         * Sets the value of the group property.
         * @param value allowed object is
         * {@link String }
         */
        @objid ("cae9b0eb-d6ff-11e1-9f03-001ec947ccaf")
        public void setGroup(final String value) {
            this.group = value;
        }

        /**
         * Gets the value of the defaultValue property.
         * @return
         * possible object is
         * {@link String }
         */
        @objid ("cae9b0ec-d6ff-11e1-9f03-001ec947ccaf")
        public String getDefaultValue() {
            return this.defaultValue;
        }

        /**
         * Sets the value of the defaultValue property.
         * @param value allowed object is
         * {@link String }
         */
        @objid ("cae9b0ed-d6ff-11e1-9f03-001ec947ccaf")
        public void setDefaultValue(final String value) {
            this.defaultValue = value;
        }

        /**
         * Gets the value of the type property.
         * @return
         * possible object is
         * {@link String }
         */
        @objid ("cae9b0ee-d6ff-11e1-9f03-001ec947ccaf")
        public String getType() {
            return this.type;
        }

        /**
         * Sets the value of the type property.
         * @param value allowed object is
         * {@link String }
         */
        @objid ("cae9b0ef-d6ff-11e1-9f03-001ec947ccaf")
        public void setType(final String value) {
            this.type = value;
        }

        /**
         * Gets the value of the uid property.
         * @return
         * possible object is
         * {@link String }
         */
        @objid ("cae9b0f0-d6ff-11e1-9f03-001ec947ccaf")
        public String getUid() {
            return this.uid;
        }

        /**
         * Sets the value of the uid property.
         * @param value allowed object is
         * {@link String }
         */
        @objid ("cae9b0f1-d6ff-11e1-9f03-001ec947ccaf")
        public void setUid(final String value) {
            this.uid = value;
        }

        /**
         * Gets the value of the enumerationOrDescription property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the enumerationOrDescription property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         * getEnumerationOrDescription().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link JxbModule.JxbParameter.JxbEnumeration }
         * {@link String }
         */
        @objid ("cae9b0f9-d6ff-11e1-9f03-001ec947ccaf")
        public List<Object> getEnumerationOrDescription() {
            if (this.enumerationOrDescription == null) {
                this.enumerationOrDescription = new ArrayList<>();
            }
            return this.enumerationOrDescription;
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
         * &lt;choice maxOccurs="unbounded" minOccurs="0">
         * &lt;element name="literal" maxOccurs="unbounded" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/choice>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("cae9b0e5-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                    "literal"
                })
        public static class JxbEnumeration {
            @objid ("caec1308-d6ff-11e1-9f03-001ec947ccaf")
            protected List<org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbParameter.JxbEnumeration.Literal> literal;

            /**
             * Gets the value of the literal property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the literal property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             * getLiteral().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link JxbModule.JxbParameter.JxbEnumeration.Literal }
             */
            @objid ("caec1309-d6ff-11e1-9f03-001ec947ccaf")
            public List<org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbParameter.JxbEnumeration.Literal> getLiteral() {
                if (this.literal == null) {
                    this.literal = new ArrayList<>();
                }
                return this.literal;
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
             * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             */
            @objid ("caec1307-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Literal {
                @objid ("caec14ae-d6ff-11e1-9f03-001ec947ccaf")
                @XmlAttribute
                protected String name;

                @objid ("caec14af-d6ff-11e1-9f03-001ec947ccaf")
                @XmlAttribute
                protected String label;

                /**
                 * Gets the value of the name property.
                 * @return
                 * possible object is
                 * {@link String }
                 */
                @objid ("caec14aa-d6ff-11e1-9f03-001ec947ccaf")
                public String getName() {
                    return this.name;
                }

                /**
                 * Sets the value of the name property.
                 * @param value allowed object is
                 * {@link String }
                 */
                @objid ("caec14ab-d6ff-11e1-9f03-001ec947ccaf")
                public void setName(final String value) {
                    this.name = value;
                }

                /**
                 * Gets the value of the label property.
                 * @return
                 * possible object is
                 * {@link String }
                 */
                @objid ("caec14ac-d6ff-11e1-9f03-001ec947ccaf")
                public String getLabel() {
                    return this.label;
                }

                /**
                 * Sets the value of the label property.
                 * @param value allowed object is
                 * {@link String }
                 */
                @objid ("caec14ad-d6ff-11e1-9f03-001ec947ccaf")
                public void setLabel(final String value) {
                    this.label = value;
                }

            }

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
     * &lt;choice maxOccurs="unbounded" minOccurs="0">
     * &lt;element name="stereotype" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;choice maxOccurs="unbounded" minOccurs="0">
     * &lt;element name="icons" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;choice maxOccurs="unbounded" minOccurs="0">
     * &lt;element name="small" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="explorer" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="diagram" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/choice>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="taggedvalues" type="{}taggedvalues" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;element name="notetype" type="{}notetype" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;element name="externdocumenttype" type="{}externdocumenttype" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;/choice>
     * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="metaclass" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="owner-stereotype" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="is-hidden" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="anonymous-stereotype" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;choice maxOccurs="unbounded" minOccurs="0">
     * &lt;element name="taggedvalues" type="{}taggedvalues" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;element name="notetype" type="{}notetype" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;element name="externdocumenttype" type="{}externdocumenttype" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;/choice>
     * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="metaclass" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/choice>
     * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @objid ("caee75c4-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "stereotypeOrAnonymousStereotype"
        })
    public static class JxbProfile {
        @objid ("cae9b102-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAttribute
        protected String uid;

        @objid ("cae9b105-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAttribute
        protected String name;

        @objid ("cae9b101-d6ff-11e1-9f03-001ec947ccaf")
        @XmlElements({
                    @XmlElement(name = "stereotype", type = JxbModule.JxbProfile.JxbStereotype.class),
                    @XmlElement(name = "anonymous-stereotype", type = JxbModule.JxbProfile.JxbAnonymousStereotype.class)
                })
        protected List<Object> stereotypeOrAnonymousStereotype;

        /**
         * Gets the value of the uid property.
         * @return
         * possible object is
         * {@link String }
         */
        @objid ("cae9b0fc-d6ff-11e1-9f03-001ec947ccaf")
        public String getUid() {
            return this.uid;
        }

        /**
         * Sets the value of the uid property.
         * @param value allowed object is
         * {@link String }
         */
        @objid ("cae9b0fe-d6ff-11e1-9f03-001ec947ccaf")
        public void setUid(final String value) {
            this.uid = value;
        }

        /**
         * Gets the value of the name property.
         * @return
         * possible object is
         * {@link String }
         */
        @objid ("cae9b0ff-d6ff-11e1-9f03-001ec947ccaf")
        public String getName() {
            return this.name;
        }

        /**
         * Sets the value of the name property.
         * @param value allowed object is
         * {@link String }
         */
        @objid ("cae9b100-d6ff-11e1-9f03-001ec947ccaf")
        public void setName(final String value) {
            this.name = value;
        }

        /**
         * Gets the value of the stereotypeOrAnonymousStereotype property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the stereotypeOrAnonymousStereotype property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         * getStereotypeOrAnonymousStereotype().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link JxbModule.JxbProfile.JxbStereotype }
         * {@link JxbModule.JxbProfile.JxbAnonymousStereotype }
         */
        @objid ("cae9b106-d6ff-11e1-9f03-001ec947ccaf")
        public List<Object> getStereotypeOrAnonymousStereotype() {
            if (this.stereotypeOrAnonymousStereotype == null) {
                this.stereotypeOrAnonymousStereotype = new ArrayList<>();
            }
            return this.stereotypeOrAnonymousStereotype;
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
         * &lt;choice maxOccurs="unbounded" minOccurs="0">
         * &lt;element name="taggedvalues" type="{}taggedvalues" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;element name="notetype" type="{}notetype" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;element name="externdocumenttype" type="{}externdocumenttype" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;/choice>
         * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="metaclass" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("cae9b0fa-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                    "taggedvaluesOrNotetypeOrExterndocumenttype"
                })
        public static class JxbAnonymousStereotype {
            @objid ("caec134a-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute
            protected String uid;

            @objid ("caec134b-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute
            protected String metaclass;

            @objid ("caec1349-d6ff-11e1-9f03-001ec947ccaf")
            @XmlElements({
                            @XmlElement(name = "taggedvalues", type = JxbTaggedvalues.class),
                            @XmlElement(name = "externdocumenttype", type = JxbExterndocumenttype.class),
                            @XmlElement(name = "notetype", type = JxbNotetype.class)
                        })
            protected List<Object> taggedvaluesOrNotetypeOrExterndocumenttype;

            /**
             * Gets the value of the uid property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("caec1345-d6ff-11e1-9f03-001ec947ccaf")
            public String getUid() {
                return this.uid;
            }

            /**
             * Sets the value of the uid property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("caec1346-d6ff-11e1-9f03-001ec947ccaf")
            public void setUid(final String value) {
                this.uid = value;
            }

            /**
             * Gets the value of the metaclass property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("caec1347-d6ff-11e1-9f03-001ec947ccaf")
            public String getMetaclass() {
                return this.metaclass;
            }

            /**
             * Sets the value of the metaclass property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("caec1348-d6ff-11e1-9f03-001ec947ccaf")
            public void setMetaclass(final String value) {
                this.metaclass = value;
            }

            /**
             * Gets the value of the taggedvaluesOrNotetypeOrExterndocumenttype property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the taggedvaluesOrNotetypeOrExterndocumenttype property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             * getTaggedvaluesOrNotetypeOrExterndocumenttype().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link JxbTaggedvalues }
             * {@link JxbExterndocumenttype }
             * {@link JxbNotetype }
             */
            @objid ("caec134c-d6ff-11e1-9f03-001ec947ccaf")
            public List<Object> getTaggedvaluesOrNotetypeOrExterndocumenttype() {
                if (this.taggedvaluesOrNotetypeOrExterndocumenttype == null) {
                    this.taggedvaluesOrNotetypeOrExterndocumenttype = new ArrayList<>();
                }
                return this.taggedvaluesOrNotetypeOrExterndocumenttype;
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
         * &lt;choice maxOccurs="unbounded" minOccurs="0">
         * &lt;element name="icons" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;choice maxOccurs="unbounded" minOccurs="0">
         * &lt;element name="small" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;element name="explorer" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;element name="diagram" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/choice>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;element name="taggedvalues" type="{}taggedvalues" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;element name="notetype" type="{}notetype" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;element name="externdocumenttype" type="{}externdocumenttype" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;/choice>
         * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="metaclass" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="owner-stereotype" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="is-hidden" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("cae9b0fb-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                    "iconsOrTaggedvaluesOrNotetype"
                })
        public static class JxbStereotype {
            @objid ("caec135e-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute
            protected String uid;

            @objid ("caec135f-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute
            protected String name;

            @objid ("caec1360-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute
            protected String label;

            @objid ("caec1361-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute
            protected String metaclass;

            @objid ("caec1362-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute(name = "owner-stereotype")
            protected String ownerStereotype;

            @objid ("caec1363-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAttribute(name = "is-hidden")
            protected String isHidden;

            @objid ("caec135d-d6ff-11e1-9f03-001ec947ccaf")
            @XmlElements({
                            @XmlElement(name = "icons", type = JxbModule.JxbProfile.JxbStereotype.Icons.class),
                            @XmlElement(name = "taggedvalues", type = JxbTaggedvalues.class),
                            @XmlElement(name = "externdocumenttype", type = JxbExterndocumenttype.class),
                            @XmlElement(name = "notetype", type = JxbNotetype.class)
                        })
            protected List<Object> iconsOrTaggedvaluesOrNotetype;

            /**
             * Gets the value of the uid property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("caec134f-d6ff-11e1-9f03-001ec947ccaf")
            public String getUid() {
                return this.uid;
            }

            /**
             * Sets the value of the uid property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("caec1350-d6ff-11e1-9f03-001ec947ccaf")
            public void setUid(final String value) {
                this.uid = value;
            }

            /**
             * Gets the value of the name property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("caec1351-d6ff-11e1-9f03-001ec947ccaf")
            public String getName() {
                return this.name;
            }

            /**
             * Sets the value of the name property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("caec1352-d6ff-11e1-9f03-001ec947ccaf")
            public void setName(final String value) {
                this.name = value;
            }

            /**
             * Gets the value of the label property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("caec1353-d6ff-11e1-9f03-001ec947ccaf")
            public String getLabel() {
                return this.label;
            }

            /**
             * Sets the value of the label property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("caec1356-d6ff-11e1-9f03-001ec947ccaf")
            public void setLabel(final String value) {
                this.label = value;
            }

            /**
             * Gets the value of the metaclass property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("caec1357-d6ff-11e1-9f03-001ec947ccaf")
            public String getMetaclass() {
                return this.metaclass;
            }

            /**
             * Sets the value of the metaclass property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("caec1358-d6ff-11e1-9f03-001ec947ccaf")
            public void setMetaclass(final String value) {
                this.metaclass = value;
            }

            /**
             * Gets the value of the ownerStereotype property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("caec1359-d6ff-11e1-9f03-001ec947ccaf")
            public String getOwnerStereotype() {
                return this.ownerStereotype;
            }

            /**
             * Sets the value of the ownerStereotype property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("caec135a-d6ff-11e1-9f03-001ec947ccaf")
            public void setOwnerStereotype(final String value) {
                this.ownerStereotype = value;
            }

            /**
             * Gets the value of the isHidden property.
             * @return
             * possible object is
             * {@link String }
             */
            @objid ("caec135b-d6ff-11e1-9f03-001ec947ccaf")
            public String getIsHidden() {
                return this.isHidden;
            }

            /**
             * Sets the value of the isHidden property.
             * @param value allowed object is
             * {@link String }
             */
            @objid ("caec135c-d6ff-11e1-9f03-001ec947ccaf")
            public void setIsHidden(final String value) {
                this.isHidden = value;
            }

            /**
             * Gets the value of the iconsOrTaggedvaluesOrNotetype property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the iconsOrTaggedvaluesOrNotetype property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             * getIconsOrTaggedvaluesOrNotetype().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link JxbModule.JxbProfile.JxbStereotype.Icons }
             * {@link JxbTaggedvalues }
             * {@link JxbExterndocumenttype }
             * {@link JxbNotetype }
             */
            @objid ("caec1364-d6ff-11e1-9f03-001ec947ccaf")
            public List<Object> getIconsOrTaggedvaluesOrNotetype() {
                if (this.iconsOrTaggedvaluesOrNotetype == null) {
                    this.iconsOrTaggedvaluesOrNotetype = new ArrayList<>();
                }
                return this.iconsOrTaggedvaluesOrNotetype;
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
             * &lt;choice maxOccurs="unbounded" minOccurs="0">
             * &lt;element name="small" minOccurs="0">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;element name="explorer" minOccurs="0">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;element name="diagram" minOccurs="0">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;/choice>
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             */
            @objid ("caec134e-d6ff-11e1-9f03-001ec947ccaf")
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                            "smallOrExplorerOrDiagram"
                        })
            public static class Icons {
                @objid ("caec14de-d6ff-11e1-9f03-001ec947ccaf")
                @XmlElements({
                                    @XmlElement(name = "small", type = JxbModule.JxbProfile.JxbStereotype.Icons.Small.class),
                                    @XmlElement(name = "explorer", type = JxbModule.JxbProfile.JxbStereotype.Icons.Explorer.class),
                                    @XmlElement(name = "diagram", type = JxbModule.JxbProfile.JxbStereotype.Icons.Diagram.class)
                                })
                protected List<Object> smallOrExplorerOrDiagram;

                /**
                 * Gets the value of the smallOrExplorerOrDiagram property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the smallOrExplorerOrDiagram property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 * getSmallOrExplorerOrDiagram().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link JxbModule.JxbProfile.JxbStereotype.Icons.Small }
                 * {@link JxbModule.JxbProfile.JxbStereotype.Icons.Explorer }
                 * {@link JxbModule.JxbProfile.JxbStereotype.Icons.Diagram }
                 */
                @objid ("caec14df-d6ff-11e1-9f03-001ec947ccaf")
                public List<Object> getSmallOrExplorerOrDiagram() {
                    if (this.smallOrExplorerOrDiagram == null) {
                        this.smallOrExplorerOrDiagram = new ArrayList<>();
                    }
                    return this.smallOrExplorerOrDiagram;
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
                 * &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;/restriction>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 */
                @objid ("caec14da-d6ff-11e1-9f03-001ec947ccaf")
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Diagram {
                    @objid ("caec1572-d6ff-11e1-9f03-001ec947ccaf")
                    @XmlAttribute
                    protected String path;

                    /**
                     * Gets the value of the path property.
                     * @return
                     * possible object is
                     * {@link String }
                     */
                    @objid ("caec1570-d6ff-11e1-9f03-001ec947ccaf")
                    public String getPath() {
                        return this.path;
                    }

                    /**
                     * Sets the value of the path property.
                     * @param value allowed object is
                     * {@link String }
                     */
                    @objid ("caec1571-d6ff-11e1-9f03-001ec947ccaf")
                    public void setPath(final String value) {
                        this.path = value;
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
                 * &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;/restriction>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 */
                @objid ("caec14db-d6ff-11e1-9f03-001ec947ccaf")
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Explorer {
                    @objid ("caec1578-d6ff-11e1-9f03-001ec947ccaf")
                    @XmlAttribute
                    protected String path;

                    /**
                     * Gets the value of the path property.
                     * @return
                     * possible object is
                     * {@link String }
                     */
                    @objid ("caec1576-d6ff-11e1-9f03-001ec947ccaf")
                    public String getPath() {
                        return this.path;
                    }

                    /**
                     * Sets the value of the path property.
                     * @param value allowed object is
                     * {@link String }
                     */
                    @objid ("caec1577-d6ff-11e1-9f03-001ec947ccaf")
                    public void setPath(final String value) {
                        this.path = value;
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
                 * &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;/restriction>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 */
                @objid ("caec14dd-d6ff-11e1-9f03-001ec947ccaf")
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Small {
                    @objid ("caec157e-d6ff-11e1-9f03-001ec947ccaf")
                    @XmlAttribute
                    protected String path;

                    /**
                     * Gets the value of the path property.
                     * @return
                     * possible object is
                     * {@link String }
                     */
                    @objid ("caec157c-d6ff-11e1-9f03-001ec947ccaf")
                    public String getPath() {
                        return this.path;
                    }

                    /**
                     * Sets the value of the path property.
                     * @param value allowed object is
                     * {@link String }
                     */
                    @objid ("caec157d-d6ff-11e1-9f03-001ec947ccaf")
                    public void setPath(final String value) {
                        this.path = value;
                    }

                }

            }

        }

    }

}
