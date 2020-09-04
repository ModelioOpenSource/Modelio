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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for _StereotypeRef complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="_StereotypeRef">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="uid" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("45568780-fbf8-4c8f-8dba-00feef12e0ba")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_StereotypeRef")
public class Jxbv2StereotypeRef {
    @objid ("c8e74381-4100-4ac6-bc54-ea2af58c09c5")
    @XmlAttribute(name = "uid", required = true)
    protected String uid;

    /**
     * Gets the value of the uid property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("fd5fcdf0-97b4-4811-a3fb-aa7824305bda")
    public String getUid() {
        return this.uid;
    }

    /**
     * Sets the value of the uid property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("a053dffd-2158-4e6f-9011-a861eea75244")
    public void setUid(String value) {
        this.uid = value;
    }

}
