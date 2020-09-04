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

package org.modelio.gproject.data.module.jaxbv1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for diagram-command complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="diagram-command">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="handler" type="{}handler"/>
 * &lt;/sequence>
 * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="tooltip" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="image" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="group" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("caee75b4-d6ff-11e1-9f03-001ec947ccaf")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "diagram-command", propOrder = {
    "handler"
})
public class JxbDiagramCommand {
    @objid ("caee7623-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String name;

    @objid ("caee75d5-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String label;

    @objid ("caee75d6-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String tooltip;

    @objid ("caee75d7-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String image;

    @objid ("caee75d8-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String group;

    @objid ("caee761f-d6ff-11e1-9f03-001ec947ccaf")
    @XmlElement(required = true)
    protected JxbHandler handler;

    /**
     * Gets the value of the handler property.
     * @return
     * possible object is
     * {@link JxbHandler }
     */
    @objid ("caee7620-d6ff-11e1-9f03-001ec947ccaf")
    public JxbHandler getHandler() {
        return this.handler;
    }

    /**
     * Sets the value of the handler property.
     * 
     * @param value allowed object is
     * {@link JxbHandler }
     */
    @objid ("caee75f8-d6ff-11e1-9f03-001ec947ccaf")
    public void setHandler(final JxbHandler value) {
        this.handler = value;
    }

    /**
     * Gets the value of the name property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee75f9-d6ff-11e1-9f03-001ec947ccaf")
    public String getName() {
        return this.name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee75fa-d6ff-11e1-9f03-001ec947ccaf")
    public void setName(final String value) {
        this.name = value;
    }

    /**
     * Gets the value of the label property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee762e-d6ff-11e1-9f03-001ec947ccaf")
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee762f-d6ff-11e1-9f03-001ec947ccaf")
    public void setLabel(final String value) {
        this.label = value;
    }

    /**
     * Gets the value of the tooltip property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee7630-d6ff-11e1-9f03-001ec947ccaf")
    public String getTooltip() {
        return this.tooltip;
    }

    /**
     * Sets the value of the tooltip property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee7633-d6ff-11e1-9f03-001ec947ccaf")
    public void setTooltip(final String value) {
        this.tooltip = value;
    }

    /**
     * Gets the value of the image property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee7634-d6ff-11e1-9f03-001ec947ccaf")
    public String getImage() {
        return this.image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee7635-d6ff-11e1-9f03-001ec947ccaf")
    public void setImage(final String value) {
        this.image = value;
    }

    /**
     * Gets the value of the group property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee7621-d6ff-11e1-9f03-001ec947ccaf")
    public String getGroup() {
        return this.group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee7622-d6ff-11e1-9f03-001ec947ccaf")
    public void setGroup(final String value) {
        this.group = value;
    }

}
