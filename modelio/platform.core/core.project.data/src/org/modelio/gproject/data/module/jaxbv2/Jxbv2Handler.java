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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>
 * Java class for _Handler complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="_Handler">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="HParameter" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;attribute name="class" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("e0f5d2ac-c5ba-4ab7-8490-5588e693711d")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType (name = "_Handler", propOrder = {
        "hParameter"
})
@XmlSeeAlso ({
        org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Matrices.Jxbv2MatrixType.Jxbv2X.class
})
public class Jxbv2Handler {
    @objid ("62458b37-e1f7-45b1-bf07-09cdb4ae2106")
    @XmlAttribute (name = "class", required = true)
    protected String clazz;

    @objid ("15053b6b-431b-44e9-943b-3a7ea053f6b4")
    @XmlElement (name = "HParameter")
    protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Handler.Jxbv2HParameter> hParameter;

    /**
     * Gets the value of the hParameter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the hParameter
     * property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getHParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Jxbv2Handler.Jxbv2HParameter }
     */
    @objid ("28ad1344-7b1b-4118-b373-b1e0c5d3654b")
    public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Handler.Jxbv2HParameter> getHParameter() {
        if (this.hParameter == null) {
            this.hParameter = new ArrayList<>();
        }
        return this.hParameter;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * @return possible object is {@link String }
     */
    @objid ("b33b8577-9b2e-4fe5-86e6-d552ce97a345")
    public String getClazz() {
        return this.clazz;
    }

    /**
     * Sets the value of the clazz property.
     * 
     * @param value allowed object is {@link String }
     */
    @objid ("33f315ad-8aaa-4e58-9656-521e8796f16b")
    public void setClazz(String value) {
        this.clazz = value;
    }

    /**
     * <p>
     * Java class for anonymous complex type.
     * 
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @objid ("0f3f4edd-6832-4769-a90d-5afbd1532013")
    @XmlAccessorType (XmlAccessType.FIELD)
    @XmlType (name = "")
    public static class Jxbv2HParameter {
        @objid ("ec1f692c-402e-470d-a02c-2c535ce7d76c")
        @XmlAttribute (name = "name", required = true)
        protected String name;

        @objid ("029bc69b-0c11-4518-876d-6f22cd38c5b4")
        @XmlAttribute (name = "value", required = true)
        protected String value;

        /**
         * Gets the value of the name property.
         * 
         * @return possible object is {@link String }
         */
        @objid ("21062c30-0b0b-4c0f-9e1d-4bd075bdc688")
        public String getName() {
            return this.name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value allowed object is {@link String }
         */
        @objid ("434b07b3-d830-4538-90b7-347587bfbc25")
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the value property.
         * 
         * @return possible object is {@link String }
         */
        @objid ("a8fbc511-e414-4739-87ba-7fb600183ff1")
        public String getValue() {
            return this.value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value allowed object is {@link String }
         */
        @objid ("24e2e402-8cba-4e4e-8cae-8a0c327e03dd")
        public void setValue(String value) {
            this.value = value;
        }

    }

}
