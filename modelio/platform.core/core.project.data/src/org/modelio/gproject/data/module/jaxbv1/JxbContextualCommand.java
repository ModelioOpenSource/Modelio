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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for contextual-command complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="contextual-command">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;choice maxOccurs="unbounded" minOccurs="0">
 * &lt;element name="scope" type="{}scope" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;element name="handler" type="{}handler" minOccurs="0"/>
 * &lt;element name="contribution" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="location" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/choice>
 * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="tooltip" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="image" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="group" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="modify-model" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="group-image" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("caee75b3-d6ff-11e1-9f03-001ec947ccaf")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contextual-command", propOrder = {
    "scopeOrHandlerOrContribution"
})
@XmlSeeAlso({
    JxbModule.Gui.Command.class,
    JxbModule.Gui.ElementCreationCommand.class
})
public class JxbContextualCommand {
    @objid ("caee7610-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String name;

    @objid ("caee75ed-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String label;

    @objid ("caee75af-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String tooltip;

    @objid ("caee75c0-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String image;

    @objid ("caee75c1-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute
    protected String group;

    @objid ("caee75c2-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute(name = "modify-model")
    protected String modifyModel;

    @objid ("caee7642-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAttribute(name = "group-image")
    protected String groupImage;

    @objid ("cae74e65-d6ff-11e1-9f03-001ec947ccaf")
    @XmlElements({
        @XmlElement(name = "handler", type = JxbHandler.class),
        @XmlElement(name = "contribution", type = JxbContextualCommand.Contribution.class),
        @XmlElement(name = "scope", type = JxbScope.class)
    })
    protected List<Object> scopeOrHandlerOrContribution;

    /**
     * Gets the value of the name property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee75f2-d6ff-11e1-9f03-001ec947ccaf")
    public String getName() {
        return this.name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee75f3-d6ff-11e1-9f03-001ec947ccaf")
    public void setName(final String value) {
        this.name = value;
    }

    /**
     * Gets the value of the label property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee75f4-d6ff-11e1-9f03-001ec947ccaf")
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee75da-d6ff-11e1-9f03-001ec947ccaf")
    public void setLabel(final String value) {
        this.label = value;
    }

    /**
     * Gets the value of the tooltip property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee75db-d6ff-11e1-9f03-001ec947ccaf")
    public String getTooltip() {
        return this.tooltip;
    }

    /**
     * Sets the value of the tooltip property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee75dc-d6ff-11e1-9f03-001ec947ccaf")
    public void setTooltip(final String value) {
        this.tooltip = value;
    }

    /**
     * Gets the value of the image property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee7659-d6ff-11e1-9f03-001ec947ccaf")
    public String getImage() {
        return this.image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee765a-d6ff-11e1-9f03-001ec947ccaf")
    public void setImage(final String value) {
        this.image = value;
    }

    /**
     * Gets the value of the group property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee765b-d6ff-11e1-9f03-001ec947ccaf")
    public String getGroup() {
        return this.group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee762b-d6ff-11e1-9f03-001ec947ccaf")
    public void setGroup(final String value) {
        this.group = value;
    }

    /**
     * Gets the value of the modifyModel property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee762c-d6ff-11e1-9f03-001ec947ccaf")
    public String getModifyModel() {
        return this.modifyModel;
    }

    /**
     * Sets the value of the modifyModel property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee762d-d6ff-11e1-9f03-001ec947ccaf")
    public void setModifyModel(final String value) {
        this.modifyModel = value;
    }

    /**
     * Gets the value of the groupImage property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("caee7636-d6ff-11e1-9f03-001ec947ccaf")
    public String getGroupImage() {
        return this.groupImage;
    }

    /**
     * Sets the value of the groupImage property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("caee7637-d6ff-11e1-9f03-001ec947ccaf")
    public void setGroupImage(final String value) {
        this.groupImage = value;
    }

    /**
     * Gets the value of the scopeOrHandlerOrContribution property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scopeOrHandlerOrContribution property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     * getScopeOrHandlerOrContribution().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JxbHandler }
     * {@link JxbContextualCommand.Contribution }
     * {@link JxbScope }
     */
    @objid ("caee7643-d6ff-11e1-9f03-001ec947ccaf")
    public List<Object> getScopeOrHandlerOrContribution() {
        if (this.scopeOrHandlerOrContribution == null) {
            this.scopeOrHandlerOrContribution = new ArrayList<>();
        }
        return this.scopeOrHandlerOrContribution;
    }

    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;attribute name="location" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @objid ("caee7657-d6ff-11e1-9f03-001ec947ccaf")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Contribution {
        @objid ("cae74f17-d6ff-11e1-9f03-001ec947ccaf")
        @XmlAttribute
        protected String location;

        /**
         * Gets the value of the location property.
         * @return
         * possible object is
         * {@link String }
         */
        @objid ("cae74f15-d6ff-11e1-9f03-001ec947ccaf")
        public String getLocation() {
            return this.location;
        }

        /**
         * Sets the value of the location property.
         * 
         * @param value allowed object is
         * {@link String }
         */
        @objid ("cae74f16-d6ff-11e1-9f03-001ec947ccaf")
        public void setLocation(final String value) {
            this.location = value;
        }

    }

}
