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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for _MultiPathes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="_MultiPathes">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="PathEntry" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("40134a9c-c555-4420-8744-d03d1e3a1368")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_MultiPathes", propOrder = {
    "pathEntry"
})
public class Jxbv2MultiPathes {
    @objid ("f8221334-0092-4f26-a923-4687e50f8aeb")
    @XmlElement(name = "PathEntry")
    protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2MultiPathes.Jxbv2PathEntry> pathEntry;

    /**
     * Gets the value of the pathEntry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pathEntry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     * getPathEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MultiPathes.PathEntry }
     */
    @objid ("47bdf2ed-621c-4624-96d9-d74ad0bee47b")
    public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2MultiPathes.Jxbv2PathEntry> getPathEntry() {
        if (this.pathEntry == null) {
            this.pathEntry = new ArrayList<>();
        }
        return this.pathEntry;
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
     * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @objid ("8cc4f3cd-f0ea-4d9b-837b-9805f66606d3")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Jxbv2PathEntry {
        @objid ("7a327945-785b-45e2-a764-513746d7dfc2")
        @XmlAttribute(name = "path", required = true)
        protected String path;

        /**
         * Gets the value of the path property.
         * @return
         * possible object is
         * {@link String }
         */
        @objid ("80fb26b9-cc2a-4236-99ce-6bea4b23aad2")
        public String getPath() {
            return this.path;
        }

        /**
         * Sets the value of the path property.
         * 
         * @param value allowed object is
         * {@link String }
         */
        @objid ("f0841c22-9210-4325-af35-8e793abd9643")
        public void setPath(String value) {
            this.path = value;
        }

    }

}
