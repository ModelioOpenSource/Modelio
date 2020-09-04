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
import javax.xml.bind.annotation.XmlType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for diagram-command-link complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="diagram-command-link">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="scope-source" type="{}scope" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;element name="scope-target" type="{}scope" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;element name="handler" type="{}handler" minOccurs="0"/>
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
@objid ("caee75bf-d6ff-11e1-9f03-001ec947ccaf")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "diagram-command-link", propOrder = {
    "scopeSource",
    "scopeTarget",
    "handler"
})
public class JxbDiagramCommandLink {
    @objid ("caee7653-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String name;

    @objid ("caee75e2-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String label;

    @objid ("caee75e3-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String tooltip;

    @objid ("caee75e4-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String image;

    @objid ("caee764a-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String group;

    @objid ("caee7650-d6ff-11e1-9f03-001ec947ccaf")
    @XmlElement(name = "scope-source")
    protected List<JxbScope> scopeSource;

    @objid ("caee7651-d6ff-11e1-9f03-001ec947ccaf")
    @XmlElement(name = "scope-target")
    protected List<JxbScope> scopeTarget;

    @objid ("caee7652-d6ff-11e1-9f03-001ec947ccaf")
    protected JxbHandler handler;

    /**
     * Gets the value of the scopeSource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scopeSource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     * getScopeSource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JxbScope }
     */
    @objid ("caee764b-d6ff-11e1-9f03-001ec947ccaf")
    public List<JxbScope> getScopeSource() {
        if (this.scopeSource == null) {
            this.scopeSource = new ArrayList<>();
        }
        return this.scopeSource;
    }

    /**
     * Gets the value of the scopeTarget property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scopeTarget property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     * getScopeTarget().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JxbScope }
     */
    @objid ("caee764c-d6ff-11e1-9f03-001ec947ccaf")
    public List<JxbScope> getScopeTarget() {
        if (this.scopeTarget == null) {
            this.scopeTarget = new ArrayList<>();
        }
        return this.scopeTarget;
    }

    /**
     * Gets the value of the handler property.
     * @return
     * possible object is
     * {@link JxbHandler }
     */
    @objid ("caee764d-d6ff-11e1-9f03-001ec947ccaf")
    public JxbHandler getHandler() {
        return this.handler;
    }

    /**
     * Sets the value of the handler property.
     * 
     * @param value allowed object is
     * {@link JxbHandler }
     */
    @objid ("cae9b0d9-d6ff-11e1-9f03-001ec947ccaf")
    public void setHandler(final JxbHandler value) {
        this.handler = value;
    }

    /**
     * Gets the value of the name property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae9b0da-d6ff-11e1-9f03-001ec947ccaf")
    public String getName() {
        return this.name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("cae9b0db-d6ff-11e1-9f03-001ec947ccaf")
    public void setName(final String value) {
        this.name = value;
    }

    /**
     * Gets the value of the label property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("cae9b0dc-d6ff-11e1-9f03-001ec947ccaf")
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee75d1-d6ff-11e1-9f03-001ec947ccaf")
    public void setLabel(final String value) {
        this.label = value;
    }

    /**
     * Gets the value of the tooltip property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee75d2-d6ff-11e1-9f03-001ec947ccaf")
    public String getTooltip() {
        return this.tooltip;
    }

    /**
     * Sets the value of the tooltip property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee75d3-d6ff-11e1-9f03-001ec947ccaf")
    public void setTooltip(final String value) {
        this.tooltip = value;
    }

    /**
     * Gets the value of the image property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee75d4-d6ff-11e1-9f03-001ec947ccaf")
    public String getImage() {
        return this.image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee7615-d6ff-11e1-9f03-001ec947ccaf")
    public void setImage(final String value) {
        this.image = value;
    }

    /**
     * Gets the value of the group property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee7616-d6ff-11e1-9f03-001ec947ccaf")
    public String getGroup() {
        return this.group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee7617-d6ff-11e1-9f03-001ec947ccaf")
    public void setGroup(final String value) {
        this.group = value;
    }

}
