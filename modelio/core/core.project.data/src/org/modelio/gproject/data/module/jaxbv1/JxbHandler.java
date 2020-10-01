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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for handler complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="handler">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="metaclass" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="stereotype" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="relation" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="class" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("caee75bc-d6ff-11e1-9f03-001ec947ccaf")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "handler")
public class JxbHandler {
    @objid ("caee75eb-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String metaclass;

    @objid ("caee75ec-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String stereotype;

    @objid ("cae9b1b5-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String relation;

    @objid ("cae9b1b6-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute(name = "class")
    protected String clazz;

    /**
     * Gets the value of the metaclass property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae74ea7-d6ff-11e1-9f03-001ec947ccaf")
    public String getMetaclass() {
        return this.metaclass;
    }

    /**
     * Sets the value of the metaclass property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee75a8-d6ff-11e1-9f03-001ec947ccaf")
    public void setMetaclass(final String value) {
        this.metaclass = value;
    }

    /**
     * Gets the value of the stereotype property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee75a9-d6ff-11e1-9f03-001ec947ccaf")
    public String getStereotype() {
        return this.stereotype;
    }

    /**
     * Sets the value of the stereotype property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee75aa-d6ff-11e1-9f03-001ec947ccaf")
    public void setStereotype(final String value) {
        this.stereotype = value;
    }

    /**
     * Gets the value of the relation property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee75ab-d6ff-11e1-9f03-001ec947ccaf")
    public String getRelation() {
        return this.relation;
    }

    /**
     * Sets the value of the relation property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae9b1b2-d6ff-11e1-9f03-001ec947ccaf")
    public void setRelation(final String value) {
        this.relation = value;
    }

    /**
     * Gets the value of the clazz property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae9b1b3-d6ff-11e1-9f03-001ec947ccaf")
    public String getClazz() {
        return this.clazz;
    }

    /**
     * Sets the value of the clazz property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae9b1b4-d6ff-11e1-9f03-001ec947ccaf")
    public void setClazz(final String value) {
        this.clazz = value;
    }

}
