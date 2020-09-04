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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for _ToolRef complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="_ToolRef">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="refid" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="group" type="{http://www.w3.org/2001/XMLSchema}string" default="default" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("56b784bb-9bdb-4a57-8b62-174853e307e1")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_ToolRef")
@XmlSeeAlso({
    org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Palette.Jxbv2ToolRef.class
})
public class Jxbv2ToolRef {
    @objid ("6c6308af-38d2-4998-83e5-642bdfd73abb")
    @XmlAttribute(name = "refid", required = true)
    protected String refid;

    @objid ("ec752970-a6e9-4b1f-a0f3-87e999b0a75b")
    @XmlAttribute(name = "group")
    protected String group;

    /**
     * Gets the value of the refid property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("108138ad-8194-446e-a742-fcfd63f7e80f")
    public String getRefid() {
        return this.refid;
    }

    /**
     * Sets the value of the refid property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("7a923c40-4f35-4200-81e3-2e0968fd9f35")
    public void setRefid(String value) {
        this.refid = value;
    }

    /**
     * Gets the value of the group property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cf643841-2c34-4530-8d43-b0bed163850a")
    public String getGroup() {
        if (this.group == null) {
            return "default";
        } else {
            return this.group;
        }
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("09c62034-8575-4557-bf24-ff74e6f45c71")
    public void setGroup(String value) {
        this.group = value;
    }

}
