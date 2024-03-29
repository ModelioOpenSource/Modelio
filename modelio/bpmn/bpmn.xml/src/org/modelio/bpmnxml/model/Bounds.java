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
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2015.12.02 at 02:29:48 PM CET
//
package org.modelio.bpmnxml.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;

/**
 * <p>
 * Java class for Bounds complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bounds">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="x" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 * &lt;attribute name="y" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 * &lt;attribute name="width" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 * &lt;attribute name="height" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("ed32c23d-008c-40cf-a164-7ec7243ead27")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType (name = "Bounds", namespace = "http://www.omg.org/spec/DD/20100524/DC")
public class Bounds {
    @objid ("5d27ec07-4f81-481b-abf3-b25938b6d5ca")
    @XmlAttribute (name = "x", required = true)
    protected double x;

    @objid ("90cbf1c9-f617-48df-9242-d91b3e751636")
    @XmlAttribute (name = "y", required = true)
    protected double y;

    @objid ("34610c1d-4723-478f-b2ef-94a18c59fc94")
    @XmlAttribute (name = "width", required = true)
    protected double width;

    @objid ("01fdd393-a9e9-41b0-894c-ad2a6da9b8e0")
    @XmlAttribute (name = "height", required = true)
    protected double height;

    /**
     * Gets the value of the x property.
     */
    @objid ("215f3595-57b6-4d18-b244-c32586241561")
    public double getX() {
        return this.x;
    }

    /**
     * Sets the value of the x property.
     */
    @objid ("076886fe-dda8-4d67-9ad1-175560b1dd4d")
    public void setX(double value) {
        this.x = value;
    }

    /**
     * Gets the value of the y property.
     */
    @objid ("0e81d7e0-c883-4c73-a63b-e9636d07da08")
    public double getY() {
        return this.y;
    }

    /**
     * Sets the value of the y property.
     */
    @objid ("79ecadf1-71fb-4ce0-bfb4-89f515acfcf5")
    public void setY(double value) {
        this.y = value;
    }

    /**
     * Gets the value of the width property.
     */
    @objid ("74edeef5-fb89-46b1-9012-26869d8ad653")
    public double getWidth() {
        return this.width;
    }

    /**
     * Sets the value of the width property.
     */
    @objid ("00da8738-7d2d-4dff-8f12-fc208a248a1e")
    public void setWidth(double value) {
        this.width = value;
    }

    /**
     * Gets the value of the height property.
     */
    @objid ("a58535c1-2e12-4600-b9a1-fd576dad1201")
    public double getHeight() {
        return this.height;
    }

    /**
     * Sets the value of the height property.
     */
    @objid ("bbfcea7c-cb2e-4213-99fb-bd969c591705")
    public void setHeight(double value) {
        this.height = value;
    }

    /**
     * Returns a new rectangle which represents the union of
     * the receiver and the given rectangle.
     * <p>
     * The union of two rectangles is the smallest single rectangle
     * that completely covers both of the areas covered by the two
     * given rectangles.
     * </p>
     * @see #add(Rectangle)
     * @param rect the rectangle to perform union with
     * @return the union of the receiver and the argument
     * 
     * @exception IllegalArgumentException
     * <ul>
     * <li>ERROR_NULL_ARGUMENT - if the argument is null</li>
     * </ul>
     */
    @objid ("ff0c75f0-c8e8-4ac7-8c24-8f32c10b1a0f")
    public Bounds union(Bounds rect) {
        if (rect == null) {
            SWT.error(SWT.ERROR_NULL_ARGUMENT);
            // Never happens, an exception is thrown
            return null;
        }
        double left = this.x < rect.x ? this.x : rect.x;
        double top = this.y < rect.y ? this.y : rect.y;
        double lhs = this.x + this.width;
        double rhs = rect.x + rect.width;
        double right = lhs > rhs ? lhs : rhs;
        lhs = this.y + this.height;
        rhs = rect.y + rect.height;
        double bottom = lhs > rhs ? lhs : rhs;
        
        Bounds ret = new Bounds();
        ret.setX(left);
        ret.setY(top);
        ret.setWidth(right - left);
        ret.setHeight(bottom - top);
        return ret;
    }

}
