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
package org.modelio.module.modelermodule.api.xmi.standard.class_;

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
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Class} with << UML2StereotypeProperty >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("16847bf6-c61c-4c56-9ab6-52148bf70507")
public class UML2StereotypeProperty {
    @objid ("7c1c0326-4ccf-4112-9e0d-2f8e0162c0ad")
    public static final String STEREOTYPE_NAME = "UML2StereotypeProperty";

    @objid ("33e97d35-278a-4fd8-a31a-872d275a3e78")
    public static final String PROPERTY_TAGTYPE = "Property";

    /**
     * The underlying {@link Class} represented by this proxy, never null.
     */
    @objid ("b184cd1b-1191-4007-a1ca-c7b1664862f0")
    protected final Class elt;

    /**
     * Tells whether a {@link UML2StereotypeProperty proxy} can be instantiated from a {@link MObject} checking it is a {@link Class} stereotyped << UML2StereotypeProperty >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("42343774-51aa-48f1-b694-5ea3898e2d63")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Class) && ((Class) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2StereotypeProperty.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Class} stereotyped << UML2StereotypeProperty >> then instantiate a {@link UML2StereotypeProperty} proxy.
     * 
     * @return a {@link UML2StereotypeProperty} proxy on the created {@link Class}.
     */
    @objid ("d8b0d7e3-8172-4f4b-94ca-568787fb2fb2")
    public static UML2StereotypeProperty create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Class");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2StereotypeProperty.STEREOTYPE_NAME);
        return UML2StereotypeProperty.instantiate((Class)e);
    }

    /**
     * Tries to instantiate a {@link UML2StereotypeProperty} proxy from a {@link Class} stereotyped << UML2StereotypeProperty >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Class
     * @return a {@link UML2StereotypeProperty} proxy or <i>null</i>.
     */
    @objid ("cc0fa672-4665-4f69-94fa-0d1d2a305605")
    public static UML2StereotypeProperty instantiate(Class obj) {
        return UML2StereotypeProperty.canInstantiate(obj) ? new UML2StereotypeProperty(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2StereotypeProperty} proxy from a {@link Class} stereotyped << UML2StereotypeProperty >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Class}
     * @return a {@link UML2StereotypeProperty} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a2563f7e-b300-4d67-a72d-eb22a33f68a1")
    public static UML2StereotypeProperty safeInstantiate(Class obj) throws IllegalArgumentException {
        if (UML2StereotypeProperty.canInstantiate(obj))
        	return new UML2StereotypeProperty(obj);
        else
        	throw new IllegalArgumentException("UML2StereotypeProperty: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("da8ec820-253b-4e9f-aad8-1f1c9e57cf55")
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
        UML2StereotypeProperty other = (UML2StereotypeProperty) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Class}. 
     * @return the Class represented by this proxy, never null.
     */
    @objid ("e82c1785-2154-43c9-a1eb-c1a73951c25f")
    public Class getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Property'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("bf1c9d87-d6f3-487b-aa26-573f25f3b56f")
    public String getProperty() {
        return this.elt.getTagValue(UML2StereotypeProperty.MdaTypes.PROPERTY_TAGTYPE_ELT);
    }

    @objid ("7f3e8180-3356-40ea-a777-2b6e57757ffc")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'Property'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("f454b038-de39-4dcf-885c-cda194e44280")
    public void setProperty(String value) {
        this.elt.putTagValue(UML2StereotypeProperty.MdaTypes.PROPERTY_TAGTYPE_ELT, value);
    }

    @objid ("6b50e486-aa9a-4f2f-a601-900a68cf0c1d")
    protected UML2StereotypeProperty(Class elt) {
        this.elt = elt;
    }

    @objid ("3e2c97cc-5fc7-4642-8c2c-e5591f0c6d83")
    public static final class MdaTypes {
        @objid ("067cefc9-6418-40ec-ae52-07e3941abe80")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a8544054-4850-4bbd-9253-92a35774caa3")
        public static TagType PROPERTY_TAGTYPE_ELT;

        @objid ("1a0dae02-0644-45d8-9229-0b95b7ce345c")
        private static Stereotype MDAASSOCDEP;

        @objid ("59e47bc4-f034-4271-a033-a6ca64eb110f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8da767d2-c9c1-4b9c-8065-bbf519b37806")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "bae91a3b-7009-11e0-a462-0027103f347c");
            PROPERTY_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "c28e8b06-7009-11e0-a462-0027103f347c");
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
