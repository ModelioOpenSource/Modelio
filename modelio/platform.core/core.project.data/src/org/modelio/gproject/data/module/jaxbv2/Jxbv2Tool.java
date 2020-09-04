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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for _Tool complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="_Tool">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Scope-source" type="{}_Scope" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;element name="Scope-target" type="{}_Scope" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;element name="Handler" type="{}_Handler"/>
 * &lt;/sequence>
 * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="tooltip" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="image" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("a43bd602-f22e-41c4-b776-3a85f1b34120")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "_Tool", propOrder = {
    "scopeSource",
    "scopeTarget",
    "handler"
})
public class Jxbv2Tool {
    @objid ("534560c2-8e01-427e-a328-06078c8eb4eb")
    @XmlAttribute(name = "id", required = true)
    protected String id;

    @objid ("715e5f8a-f916-471c-9f7c-ac6fa8d3ab00")
    @XmlAttribute(name = "label")
    protected String label;

    @objid ("f04c8bd1-3c27-440a-addf-fd96655a51d5")
    @XmlAttribute(name = "tooltip")
    protected String tooltip;

    @objid ("5e2a9452-a791-4a2b-a807-b407b588cfba")
    @XmlAttribute(name = "image")
    protected String image;

    @objid ("0e2d782e-713f-4ff4-8213-f41ca6fcc811")
    @XmlElement(name = "Scope-source")
    protected List<Jxbv2Scope> scopeSource;

    @objid ("03e46797-9635-4470-93c2-28b2d41a160c")
    @XmlElement(name = "Scope-target")
    protected List<Jxbv2Scope> scopeTarget;

    @objid ("93e0cd13-d39a-40f8-b712-b386335cbe1a")
    @XmlElement(name = "Handler", required = true)
    protected Jxbv2Handler handler;

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
     * {@link Jxbv2Scope }
     */
    @objid ("b4b43f9e-dd2f-4ad4-ac40-786f629d0f6b")
    public List<Jxbv2Scope> getScopeSource() {
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
     * {@link Jxbv2Scope }
     */
    @objid ("cf3351e7-e440-4f67-9444-b24235776a3f")
    public List<Jxbv2Scope> getScopeTarget() {
        if (this.scopeTarget == null) {
            this.scopeTarget = new ArrayList<>();
        }
        return this.scopeTarget;
    }

    /**
     * Gets the value of the handler property.
     * @return
     * possible object is
     * {@link Jxbv2Handler }
     */
    @objid ("898cb446-746c-40e8-8a54-5234f544df2f")
    public Jxbv2Handler getHandler() {
        return this.handler;
    }

    /**
     * Sets the value of the handler property.
     * 
     * @param value allowed object is
     * {@link Jxbv2Handler }
     */
    @objid ("51748bb6-cc0c-4cad-9e5f-1e6a06e109a3")
    public void setHandler(Jxbv2Handler value) {
        this.handler = value;
    }

    /**
     * Gets the value of the id property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("9b090c13-f31b-4784-8309-99aa95bf6444")
    public String getId() {
        return this.id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("c3822b41-3231-4e1d-a5f4-da1c1f7a5fc7")
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the label property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("35eb48c0-1cfa-47d5-8ea8-0047841d4acd")
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("b98128fe-a680-4b0a-9529-862b28a30eb0")
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the tooltip property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("a31ed6bc-5d4d-443f-bf84-a48d4ec82ead")
    public String getTooltip() {
        return this.tooltip;
    }

    /**
     * Sets the value of the tooltip property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("2a1f5f62-b233-440c-a4f8-5cca619ac94c")
    public void setTooltip(String value) {
        this.tooltip = value;
    }

    /**
     * Gets the value of the image property.
     * @return
     * possible object is
     * {@link String }
     */
    @objid ("63f4ab6d-c7d4-4c2d-8efc-b1a7e719cd36")
    public String getImage() {
        return this.image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    @objid ("e681975b-efa7-4519-8485-25d1a8a1468c")
    public void setImage(String value) {
        this.image = value;
    }

}
