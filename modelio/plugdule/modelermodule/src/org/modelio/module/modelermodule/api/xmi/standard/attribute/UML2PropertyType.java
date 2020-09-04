/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.attribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Attribute} with << UML2PropertyType >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("97813a21-b7b0-4858-b207-98c2879e16e9")
public class UML2PropertyType {
    @objid ("839cc92f-ceff-403b-b762-f3a00f7083a9")
    public static final String STEREOTYPE_NAME = "UML2PropertyType";

    @objid ("631749d1-1538-44da-9175-d72c05dc95dc")
    public static final String PROPERTYTYPE_TAGTYPE = "PropertyType";

    /**
     * The underlying {@link Attribute} represented by this proxy, never null.
     */
    @objid ("536d9742-a1cc-468f-8996-14790f548d95")
    protected final Attribute elt;

    /**
     * Tells whether a {@link UML2PropertyType proxy} can be instantiated from a {@link MObject} checking it is a {@link Attribute} stereotyped << UML2PropertyType >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("7a3efcaf-2453-44ee-b466-674122e5f20a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Attribute) && ((Attribute) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2PropertyType.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Attribute} stereotyped << UML2PropertyType >> then instantiate a {@link UML2PropertyType} proxy.
     * 
     * @return a {@link UML2PropertyType} proxy on the created {@link Attribute}.
     */
    @objid ("8266d959-2ddf-4172-b8ba-a042c345a39a")
    public static UML2PropertyType create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Attribute");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2PropertyType.STEREOTYPE_NAME);
        return UML2PropertyType.instantiate((Attribute)e);
    }

    /**
     * Tries to instantiate a {@link UML2PropertyType} proxy from a {@link Attribute} stereotyped << UML2PropertyType >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Attribute
     * @return a {@link UML2PropertyType} proxy or <i>null</i>.
     */
    @objid ("ede1ea70-2a38-4b57-8400-d7b089dbd086")
    public static UML2PropertyType instantiate(Attribute obj) {
        return UML2PropertyType.canInstantiate(obj) ? new UML2PropertyType(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2PropertyType} proxy from a {@link Attribute} stereotyped << UML2PropertyType >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Attribute}
     * @return a {@link UML2PropertyType} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("07214a6c-be44-49b7-901a-c3a8cbb79379")
    public static UML2PropertyType safeInstantiate(Attribute obj) throws IllegalArgumentException {
        if (UML2PropertyType.canInstantiate(obj))
        	return new UML2PropertyType(obj);
        else
        	throw new IllegalArgumentException("UML2PropertyType: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("4533b937-ef3c-4e31-bd1f-db9c1b64e2e9")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UML2PropertyType other = (UML2PropertyType) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Attribute}. 
     * @return the Attribute represented by this proxy, never null.
     */
    @objid ("81ba0e7c-8950-4156-8b0a-5c342f8f720e")
    public Attribute getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'PropertyType'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("cccfcea9-f70e-4ef3-86bf-d26080e6f23b")
    public String getPropertyType() {
        return this.elt.getTagValue(UML2PropertyType.MdaTypes.PROPERTYTYPE_TAGTYPE_ELT);
    }

    @objid ("d6e36719-6611-408b-acc0-e18590c6e973")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'PropertyType'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("562b74da-114f-4119-af1c-87b7c2937dec")
    public void setPropertyType(String value) {
        this.elt.putTagValue(UML2PropertyType.MdaTypes.PROPERTYTYPE_TAGTYPE_ELT, value);
    }

    @objid ("158b6aac-0508-45ee-a9f6-db25d5ccae52")
    protected UML2PropertyType(Attribute elt) {
        this.elt = elt;
    }

    @objid ("52f01be1-c820-4bae-a974-82d910c67b9a")
    public static final class MdaTypes {
        @objid ("dc4a33dd-7c45-471a-a1cc-d4440228dfca")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9637e6dc-252b-4cd4-bf09-96d9e9e1447c")
        public static TagType PROPERTYTYPE_TAGTYPE_ELT;

        @objid ("4219509d-4d13-492e-8a49-a59e7468bc0e")
        private static Stereotype MDAASSOCDEP;

        @objid ("1750e09d-0b61-42bb-8dca-4cefe25c08e3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8f32edec-6983-4304-92b5-52967681ed1e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "68c63e60-70d6-11e0-872f-0027103f347c");
            PROPERTYTYPE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "68c63e61-70d6-11e0-872f-0027103f347c");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
        }


	static {
		if(ModelerModuleModule.getInstance() != null) {
			init(ModelerModuleModule.getInstance().getModuleContext());
		}
	}
    }

}
