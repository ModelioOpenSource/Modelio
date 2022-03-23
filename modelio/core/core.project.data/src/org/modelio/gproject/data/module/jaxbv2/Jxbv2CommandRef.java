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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for _CommandRef complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="_CommandRef">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="refid" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="group" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="group-image" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("40d36ca1-0402-443a-bd1d-b8971f8c8578")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_CommandRef")
@XmlSeeAlso({
    org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2ContextualMenu.Jxbv2CommandRef.class,
    org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Views.Jxbv2PropertyPage.Jxbv2CommandRef.class
})
public class Jxbv2CommandRef {
    @objid ("8c839e96-86e7-4a63-afd0-d27e1f321485")
    @XmlAttribute(name = "refid", required = true)
    protected String refid;

    @objid ("81dd4ac4-e244-4640-a085-3fe1c90c4f47")
    @XmlAttribute(name = "group")
    protected String group;

    @objid ("eb9ee308-333b-4f39-b05b-b86dc36c4324")
    @XmlAttribute(name = "group-image")
    protected String groupImage;

    /**
     * Gets the value of the refid property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("83b930fb-9d5d-4df4-97c5-f5adaa66a2b3")
    public String getRefid() {
        return this.refid;
    }

    /**
     * Sets the value of the refid property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("b3997949-082d-47a3-a79b-3644400c2b8e")
    public void setRefid(String value) {
        this.refid = value;
    }

    /**
     * Gets the value of the group property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("bbe8d25e-5bed-482c-a3a4-daf41c192684")
    public String getGroup() {
        return this.group;
    }

    /**
     * Sets the value of the group property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("a90b0611-9361-4cd5-8c0c-57ab33e57903")
    public void setGroup(String value) {
        this.group = value;
    }

    /**
     * Gets the value of the groupImage property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("36ef7b37-c37c-4a64-b795-8cf056d04c53")
    public String getGroupImage() {
        return this.groupImage;
    }

    /**
     * Sets the value of the groupImage property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("79fd6685-438b-4d46-9aa4-38cd64b2bfe2")
    public void setGroupImage(String value) {
        this.groupImage = value;
    }

}
