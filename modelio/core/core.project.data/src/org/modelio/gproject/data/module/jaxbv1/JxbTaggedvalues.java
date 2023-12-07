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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>Java class for taggedvalues complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="taggedvalues">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@objid ("caee7654-d6ff-11e1-9f03-001ec947ccaf")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "taggedvalues")
public class JxbTaggedvalues {
    @objid ("cae74f6b-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String name;

    @objid ("cae74f6c-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String uid;

    @objid ("cae74f6d-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String label;

    @objid ("cae74f6e-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute(name = "parameter-card")
    protected String parameterCard;

    @objid ("cae74f6f-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute(name = "is-hidden")
    protected String isHidden;

    @objid ("cae74f70-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute(name = "is-signed")
    protected String isSigned;

    /**
     * Gets the value of the name property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae74f5f-d6ff-11e1-9f03-001ec947ccaf")
    public String getName() {
        return this.name;
    }

    /**
     * Sets the value of the name property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae74f60-d6ff-11e1-9f03-001ec947ccaf")
    public void setName(final String value) {
        this.name = value;
    }

    /**
     * Gets the value of the uid property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae74f61-d6ff-11e1-9f03-001ec947ccaf")
    public String getUid() {
        return this.uid;
    }

    /**
     * Sets the value of the uid property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae74f62-d6ff-11e1-9f03-001ec947ccaf")
    public void setUid(final String value) {
        this.uid = value;
    }

    /**
     * Gets the value of the label property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae74f63-d6ff-11e1-9f03-001ec947ccaf")
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the value of the label property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae74f64-d6ff-11e1-9f03-001ec947ccaf")
    public void setLabel(final String value) {
        this.label = value;
    }

    /**
     * Gets the value of the parameterCard property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae74f65-d6ff-11e1-9f03-001ec947ccaf")
    public String getParameterCard() {
        return this.parameterCard;
    }

    /**
     * Sets the value of the parameterCard property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae74f66-d6ff-11e1-9f03-001ec947ccaf")
    public void setParameterCard(final String value) {
        this.parameterCard = value;
    }

    /**
     * Gets the value of the isHidden property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae74f67-d6ff-11e1-9f03-001ec947ccaf")
    public String getIsHidden() {
        return this.isHidden;
    }

    /**
     * Sets the value of the isHidden property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae74f68-d6ff-11e1-9f03-001ec947ccaf")
    public void setIsHidden(final String value) {
        this.isHidden = value;
    }

    /**
     * Gets the value of the isSigned property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae74f69-d6ff-11e1-9f03-001ec947ccaf")
    public String getIsSigned() {
        return this.isSigned;
    }

    /**
     * Sets the value of the isSigned property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae74f6a-d6ff-11e1-9f03-001ec947ccaf")
    public void setIsSigned(final String value) {
        this.isSigned = value;
    }

}
