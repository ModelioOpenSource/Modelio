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
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>Java class for _Dependency complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="_Dependency">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("a2432153-ba7a-4604-bb93-c1d7743f6f0e")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Dependency")
@XmlSeeAlso({
    org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Dependencies.Jxbv2Required.class,
    org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Dependencies.Jxbv2Optional.class
})
public class Jxbv2Dependency {
    @objid ("70a49709-592c-4eba-b9db-0edd3ccc32ab")
    @XmlAttribute(name = "name", required = true)
    protected String name;

    @objid ("15e6271f-564e-44ba-b011-d8d0da3d7cb9")
    @XmlAttribute(name = "version", required = true)
    protected String version;

    /**
     * Gets the value of the name property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cd2c5a72-3b8e-4e37-a696-28935499df45")
    public String getName() {
        return this.name;
    }

    /**
     * Sets the value of the name property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("91c59e1d-d70c-4154-b0df-6070c8ba25e7")
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the version property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("b5dec2c8-b3fc-4a74-95ba-dd6c219d03a1")
    public String getVersion() {
        return this.version;
    }

    /**
     * Sets the value of the version property.
     * @param value allowed object is
     * {@link String }
     */
    @objid ("fca50077-ec93-40b9-8a4a-21c092b08f62")
    public void setVersion(String value) {
        this.version = value;
    }

}
