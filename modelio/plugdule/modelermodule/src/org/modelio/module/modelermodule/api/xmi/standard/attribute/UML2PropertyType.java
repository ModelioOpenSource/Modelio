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
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
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
    @objid ("9d1e88ba-ebbc-44ed-ad40-46a0cb5bec6d")
    public static final String STEREOTYPE_NAME = "UML2PropertyType";

    @objid ("c6f82880-a5f5-435c-b346-907cd9c48b4d")
    public static final String PROPERTYTYPE_TAGTYPE = "PropertyType";

    /**
     * The underlying {@link Attribute} represented by this proxy, never null.
     */
    @objid ("32c43bd7-e909-4671-81ce-8c85235f6185")
    protected final Attribute elt;

    /**
     * Tells whether a {@link UML2PropertyType proxy} can be instantiated from a {@link MObject} checking it is a {@link Attribute} stereotyped << UML2PropertyType >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("dba66895-807e-4dc5-afd1-81407d78512d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Attribute) && ((Attribute) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2PropertyType.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Attribute} stereotyped << UML2PropertyType >> then instantiate a {@link UML2PropertyType} proxy.
     * 
     * @return a {@link UML2PropertyType} proxy on the created {@link Attribute}.
     */
    @objid ("30799bf4-92ba-418f-93bb-63d3fc55518d")
    public static UML2PropertyType create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Attribute");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2PropertyType.STEREOTYPE_NAME);
        return UML2PropertyType.instantiate((Attribute)e);
    }

    /**
     * Tries to instantiate a {@link UML2PropertyType} proxy from a {@link Attribute} stereotyped << UML2PropertyType >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Attribute
     * @return a {@link UML2PropertyType} proxy or <i>null</i>.
     */
    @objid ("9f9e0023-aaa1-4ca9-9e4b-966193417e2c")
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
    @objid ("ea894c0b-f3ae-4e78-96fa-70918155b57d")
    public static UML2PropertyType safeInstantiate(Attribute obj) throws IllegalArgumentException {
        if (UML2PropertyType.canInstantiate(obj))
        	return new UML2PropertyType(obj);
        else
        	throw new IllegalArgumentException("UML2PropertyType: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("86b4d38a-69ce-4a1e-951c-22a92c121785")
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
    @objid ("c8843c65-2fb7-4acb-b3bc-9186e4db9b3d")
    public Attribute getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'PropertyType'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("93c733ac-6aac-4bb0-a47a-8799a498c6c5")
    public String getPropertyType() {
        return this.elt.getTagValue(UML2PropertyType.MdaTypes.PROPERTYTYPE_TAGTYPE_ELT);
    }

    @objid ("22cadd86-003f-4d7f-91ba-21a6193fd0f1")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for string property 'PropertyType'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("4fe103d4-f912-466d-b6c8-0f95944ccde7")
    public void setPropertyType(String value) {
        this.elt.putTagValue(UML2PropertyType.MdaTypes.PROPERTYTYPE_TAGTYPE_ELT, value);
    }

    @objid ("a328b37a-f039-429e-829f-c740ccae0d26")
    protected  UML2PropertyType(Attribute elt) {
        this.elt = elt;
    }

    @objid ("52f01be1-c820-4bae-a974-82d910c67b9a")
    public static final class MdaTypes {
        @objid ("bd7ebd04-0679-4363-b40d-021473c4d032")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ae424798-3864-4be8-bd62-d8f6e334a102")
        public static TagType PROPERTYTYPE_TAGTYPE_ELT;

        @objid ("75ed8928-226f-4f02-9f60-b20a40de27b6")
        private static Stereotype MDAASSOCDEP;

        @objid ("3d45c1fe-8df7-4d51-b477-d0c47eaea73c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b085a63e-6a9b-4740-ba9d-2a695d5e38ab")
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
