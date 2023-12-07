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
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>Java class for docpath complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="docpath">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;choice maxOccurs="unbounded" minOccurs="0">
 * &lt;element name="entry">
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
@objid ("caee75ba-d6ff-11e1-9f03-001ec947ccaf")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "docpath", propOrder = {
    "entry"
})
public class JxbDocpath {
    @objid ("caee760d-d6ff-11e1-9f03-001ec947ccaf")
    protected List<org.modelio.gproject.data.module.jaxbv1.JxbDocpath.Entry> entry;

    /**
     * Gets the value of the entry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     * getEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JxbDocpath.Entry }
     */
    @objid ("caee760e-d6ff-11e1-9f03-001ec947ccaf")
    public List<org.modelio.gproject.data.module.jaxbv1.JxbDocpath.Entry> getEntry() {
        if (this.entry == null) {
            this.entry = new ArrayList<>();
        }
        return this.entry;
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
    @objid ("caec13bd-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Entry {
        @objid ("cae74f93-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAttribute
        protected String path;

        /**
         * Gets the value of the path property.
         * @return
         * possible object is
         * {@link String }
         */
        @objid ("cae9b0c1-d6ff-11e1-9f03-001ec947ccaf")
        public String getPath() {
            return this.path;
        }

        /**
         * Sets the value of the path property.
         * @param value allowed object is
         * {@link String }
         */
        @objid ("cae9b0c2-d6ff-11e1-9f03-001ec947ccaf")
        public void setPath(final String value) {
            this.path = value;
        }

    }

}
