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
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for _Command complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="_Command">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Scope" type="{}_Scope" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;element name="Handler" type="{}_Handler"/>
 * &lt;/sequence>
 * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@objid ("be398bb7-affb-48b5-ac5e-1b5e9be53359")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType (name = "_Command", propOrder = {
        "scope",
        "handler"
})
public class Jxbv2Command {
    @objid ("11c0ebff-4cbd-4586-a508-7dabc0d4a2c8")
    @XmlAttribute (name = "id", required = true)
    protected String id;

    @objid ("280f7c98-c72a-4507-a7da-af5d7718f54e")
    @XmlAttribute (name = "label")
    protected String label;

    @objid ("83728c77-840e-49a5-bd65-aca48dcde21d")
    @XmlAttribute (name = "tooltip")
    protected String tooltip;

    @objid ("153591c1-d11f-4b95-a4d6-ea41e3001967")
    @XmlAttribute (name = "image")
    protected String image;

    @objid ("10504a2c-1691-4474-8f19-d7ed6480ca39")
    @XmlAttribute (name = "group")
    protected String group;

    @objid ("14f00a4d-a45b-410a-8de8-d546028bb300")
    @XmlAttribute (name = "modify-model")
    protected String modifyModel;

    @objid ("064f0e81-058a-4650-bcab-5fbb5ba2ba3d")
    @XmlAttribute (name = "group-image")
    protected String groupImage;

    @objid ("b0760da6-3331-4c87-82bb-af629737d3b9")
    @XmlElement (name = "Scope")
    protected List<Jxbv2Scope> scope;

    @objid ("4502fd63-6a4f-43bb-bae5-8d98d1b05967")
    @XmlElement (name = "Handler", required = true)
    protected Jxbv2Handler handler;

    /**
     * Gets the value of the scope property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the scope property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getScope().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Jxbv2Scope }
     */
    @objid ("c6e4d74a-e03c-41e2-8013-b9421d2ae813")
    public List<Jxbv2Scope> getScope() {
        if (this.scope == null) {
            this.scope = new ArrayList<>();
        }
        return this.scope;
    }

    /**
     * Gets the value of the handler property.
     * @return possible object is {@link Jxbv2Handler }
     */
    @objid ("297d5aca-870e-43d1-b11a-500522343c94")
    public Jxbv2Handler getHandler() {
        return this.handler;
    }

    /**
     * Sets the value of the handler property.
     * @param value allowed object is {@link Jxbv2Handler }
     */
    @objid ("a1c566e6-a57f-4711-bc0f-2db55bde8d1c")
    public void setHandler(Jxbv2Handler value) {
        this.handler = value;
    }

    /**
     * Gets the value of the id property.
     * @return possible object is {@link String }
     */
    @objid ("63d94877-ae83-4993-ad63-4cc852669714")
    public String getId() {
        return this.id;
    }

    /**
     * Sets the value of the id property.
     * @param value allowed object is {@link String }
     */
    @objid ("3c9f62ff-af3c-4b07-a9da-b834e38f880a")
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the label property.
     * @return possible object is {@link String }
     */
    @objid ("31ea23e8-18dc-416e-9b4f-63ec003c824c")
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the value of the label property.
     * @param value allowed object is {@link String }
     */
    @objid ("3fb7b422-fceb-4c29-928b-c43c1729cb29")
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the tooltip property.
     * @return possible object is {@link String }
     */
    @objid ("9fc5d379-86b6-413f-af99-b0e8922e0d9e")
    public String getTooltip() {
        return this.tooltip;
    }

    /**
     * Sets the value of the tooltip property.
     * @param value allowed object is {@link String }
     */
    @objid ("ae8c5f4a-49c2-4f70-ac23-0c56cce3f099")
    public void setTooltip(String value) {
        this.tooltip = value;
    }

    /**
     * Gets the value of the image property.
     * @return possible object is {@link String }
     */
    @objid ("0ee9ca94-6f56-4bfe-a508-5806a4e63005")
    public String getImage() {
        return this.image;
    }

    /**
     * Sets the value of the image property.
     * @param value allowed object is {@link String }
     */
    @objid ("b84ae993-5fc0-4305-9f9e-9667021b5e03")
    public void setImage(String value) {
        this.image = value;
    }

    /**
     * Gets the value of the group property.
     * @return possible object is {@link String }
     */
    @objid ("2c67ec7f-e9db-4fff-ad73-65b0fbfcf63f")
    public String getGroup() {
        return this.group;
    }

    /**
     * Sets the value of the group property.
     * @param value allowed object is {@link String }
     */
    @objid ("4f146385-fb8e-453b-acdd-0ecb2166f1a4")
    public void setGroup(String value) {
        this.group = value;
    }

    /**
     * Gets the value of the modifyModel property.
     * @return possible object is {@link String }
     */
    @objid ("c05d2787-1014-4661-ac79-b00e8e1d719c")
    public String getModifyModel() {
        return this.modifyModel;
    }

    /**
     * Sets the value of the modifyModel property.
     * @param value allowed object is {@link String }
     */
    @objid ("d5695918-101f-4a8a-8e98-b0a6f9af7084")
    public void setModifyModel(String value) {
        this.modifyModel = value;
    }

    /**
     * Gets the value of the groupImage property.
     * @return possible object is {@link String }
     */
    @objid ("8eced4ba-0481-4e60-b611-4150baf76c36")
    public String getGroupImage() {
        return this.groupImage;
    }

    /**
     * Sets the value of the groupImage property.
     * @param value allowed object is {@link String }
     */
    @objid ("0c2031f5-84ad-46c4-b4a5-b8c03d91e024")
    public void setGroupImage(String value) {
        this.groupImage = value;
    }

}
