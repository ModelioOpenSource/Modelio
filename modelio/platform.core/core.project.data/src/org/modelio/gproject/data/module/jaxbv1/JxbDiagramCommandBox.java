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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for diagram-command-box complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="diagram-command-box">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;choice maxOccurs="unbounded" minOccurs="0">
 * &lt;element name="scope" type="{}scope" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;element name="handler" type="{}handler" minOccurs="0"/>
 * &lt;/choice>
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
@objid ("caee75be-d6ff-11e1-9f03-001ec947ccaf")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "diagram-command-box", propOrder = {
    "scopeOrHandler"
})
public class JxbDiagramCommandBox {
    @objid ("cae9b314-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String name;

    @objid ("caee75ac-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String label;

    @objid ("caee75ad-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String tooltip;

    @objid ("caee75ae-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String image;

    @objid ("caee7629-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String group;

    @objid ("cae9b313-d6ff-11e1-9f03-001ec947ccaf")
    @XmlElements({
        @XmlElement(name = "handler", type = JxbHandler.class),
        @XmlElement(name = "scope", type = JxbScope.class)
    })
    protected List<Object> scopeOrHandler;

    /**
     * Gets the value of the name property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee7631-d6ff-11e1-9f03-001ec947ccaf")
    public String getName() {
        return this.name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee7632-d6ff-11e1-9f03-001ec947ccaf")
    public void setName(final String value) {
        this.name = value;
    }

    /**
     * Gets the value of the label property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee75cb-d6ff-11e1-9f03-001ec947ccaf")
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee75cc-d6ff-11e1-9f03-001ec947ccaf")
    public void setLabel(final String value) {
        this.label = value;
    }

    /**
     * Gets the value of the tooltip property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee75cd-d6ff-11e1-9f03-001ec947ccaf")
    public String getTooltip() {
        return this.tooltip;
    }

    /**
     * Sets the value of the tooltip property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee7612-d6ff-11e1-9f03-001ec947ccaf")
    public void setTooltip(final String value) {
        this.tooltip = value;
    }

    /**
     * Gets the value of the image property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee7613-d6ff-11e1-9f03-001ec947ccaf")
    public String getImage() {
        return this.image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee7614-d6ff-11e1-9f03-001ec947ccaf")
    public void setImage(final String value) {
        this.image = value;
    }

    /**
     * Gets the value of the group property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae9b311-d6ff-11e1-9f03-001ec947ccaf")
    public String getGroup() {
        return this.group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae9b312-d6ff-11e1-9f03-001ec947ccaf")
    public void setGroup(final String value) {
        this.group = value;
    }

    /**
     * Gets the value of the scopeOrHandler property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scopeOrHandler property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     * getScopeOrHandler().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JxbHandler }
     * {@link JxbScope }
     */
    @objid ("caee762a-d6ff-11e1-9f03-001ec947ccaf")
    public List<Object> getScopeOrHandler() {
        if (this.scopeOrHandler == null) {
            this.scopeOrHandler = new ArrayList<>();
        }
        return this.scopeOrHandler;
    }

}
