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

package org.modelio.gproject.data.module.jaxbv1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for externdocumenttype complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="externdocumenttype">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="is-hidden" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("caee75bb-d6ff-11e1-9f03-001ec947ccaf")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "externdocumenttype")
public class JxbExterndocumenttype {
    @objid ("caee75e7-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute(name = "is-hidden")
    protected String isHidden;

    @objid ("caee75e8-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String label;

    @objid ("caee75e9-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String name;

    @objid ("caee75ea-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String uid;

    /**
     * Gets the value of the isHidden property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee7628-d6ff-11e1-9f03-001ec947ccaf")
    public String getIsHidden() {
        return this.isHidden;
    }

    /**
     * Sets the value of the isHidden property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee763f-d6ff-11e1-9f03-001ec947ccaf")
    public void setIsHidden(final String value) {
        this.isHidden = value;
    }

    /**
     * Gets the value of the label property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee7640-d6ff-11e1-9f03-001ec947ccaf")
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the value of the label property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee7641-d6ff-11e1-9f03-001ec947ccaf")
    public void setLabel(final String value) {
        this.label = value;
    }

    /**
     * Gets the value of the name property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee75de-d6ff-11e1-9f03-001ec947ccaf")
    public String getName() {
        return this.name;
    }

    /**
     * Sets the value of the name property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee75df-d6ff-11e1-9f03-001ec947ccaf")
    public void setName(final String value) {
        this.name = value;
    }

    /**
     * Gets the value of the uid property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee75e0-d6ff-11e1-9f03-001ec947ccaf")
    public String getUid() {
        return this.uid;
    }

    /**
     * Sets the value of the uid property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee75e1-d6ff-11e1-9f03-001ec947ccaf")
    public void setUid(final String value) {
        this.uid = value;
    }

}
