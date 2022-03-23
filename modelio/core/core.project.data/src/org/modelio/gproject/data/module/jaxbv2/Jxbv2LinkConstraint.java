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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for _LinkConstraint complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="_LinkConstraint">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence maxOccurs="unbounded">
 * &lt;element name="SourceScope" type="{}_Scope"/>
 * &lt;element name="TargetScope" type="{}_Scope"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("e186af3d-6673-4d22-8ab8-e9ad5c15650e")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_LinkConstraint", propOrder = {
    "sourceScopeAndTargetScope"
})
public class Jxbv2LinkConstraint {
    @objid ("9f54212b-c4fc-42ba-8e46-2581da5ab4c5")
    @XmlElementRefs({
            @XmlElementRef(name = "SourceScope", type = JAXBElement.class),
            @XmlElementRef(name = "TargetScope", type = JAXBElement.class)
        })
    protected List<JAXBElement<Jxbv2Scope>> sourceScopeAndTargetScope;

    /**
     * Gets the value of the sourceScopeAndTargetScope property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sourceScopeAndTargetScope property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     * getSourceScopeAndTargetScope().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Jxbv2Scope }{@code >}
     * {@link JAXBElement }{@code <}{@link Jxbv2Scope }{@code >}
     */
    @objid ("3e9290cb-22e3-4a8f-8b42-9f56e44f177f")
    public List<JAXBElement<Jxbv2Scope>> getSourceScopeAndTargetScope() {
        if (this.sourceScopeAndTargetScope == null) {
            this.sourceScopeAndTargetScope = new ArrayList<>();
        }
        return this.sourceScopeAndTargetScope;
    }

}
