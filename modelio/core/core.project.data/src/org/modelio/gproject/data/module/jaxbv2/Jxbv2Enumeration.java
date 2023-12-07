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
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>Java class for _Enumeration complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="_Enumeration">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Literal" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;extension base="{}_Literal">
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("6a4e6489-9ab4-4233-9e36-20ce72ebf9ac")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Enumeration", propOrder = {
    "literal"
})
public class Jxbv2Enumeration {
    @objid ("2334795c-d97a-420c-b582-14f955d2fb6e")
    @XmlElement(name = "Literal")
    protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Enumeration.Jxbv2Literal> literal;

    /**
     * Gets the value of the literal property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the literal property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     * getLiteral().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Jxbv2Enumeration.Jxbv2Literal }
     */
    @objid ("65280ed7-8713-4a69-9a8d-917eb2e33859")
    public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Enumeration.Jxbv2Literal> getLiteral() {
        if (this.literal == null) {
            this.literal = new ArrayList<>();
        }
        return this.literal;
    }

    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;extension base="{}_Literal">
     * &lt;/extension>
     * &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @objid ("6f474653-1b90-461d-a946-80b05aa528bc")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Jxbv2Literal extends org.modelio.gproject.data.module.jaxbv2.Jxbv2Literal {
    }

}
