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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for scope complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="scope">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="metaclass" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="stereotype" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("caee75b7-d6ff-11e1-9f03-001ec947ccaf")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "scope")
public class JxbScope {
    @objid ("cae74f55-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String metaclass;

    @objid ("cae74f56-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String stereotype;

    /**
     * Gets the value of the metaclass property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae74f51-d6ff-11e1-9f03-001ec947ccaf")
    public String getMetaclass() {
        return this.metaclass;
    }

    /**
     * Sets the value of the metaclass property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae74f52-d6ff-11e1-9f03-001ec947ccaf")
    public void setMetaclass(final String value) {
        this.metaclass = value;
    }

    /**
     * Gets the value of the stereotype property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae74f53-d6ff-11e1-9f03-001ec947ccaf")
    public String getStereotype() {
        return this.stereotype;
    }

    /**
     * Sets the value of the stereotype property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae74f54-d6ff-11e1-9f03-001ec947ccaf")
    public void setStereotype(final String value) {
        this.stereotype = value;
    }

}
