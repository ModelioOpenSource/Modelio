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
 * Module: ModelerModule v9.1.00

 * This file was generated on 3/2/20 11:26 AM by Modelio Studio.
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
    @objid ("2ba95800-b29d-44d4-9e8f-0b9011b48dfc")
    public static final String STEREOTYPE_NAME = "UML2StereotypeProperty";

    @objid ("2b7e72fb-d4fa-48d5-9876-c37d868452fb")
    public static final String PROPERTY_TAGTYPE = "Property";

    /**
     * The underlying {@link Class} represented by this proxy, never null.
     */
    @objid ("7b77b11d-165e-45f1-8a30-fede8655adde")
    protected final Class elt;

    /**
     * Tells whether a {@link UML2StereotypeProperty proxy} can be instantiated from a {@link MObject} checking it is a {@link Class} stereotyped << UML2StereotypeProperty >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("7e203f7b-3ba5-48f5-ae1f-e1eeb0931b1c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Class) && ((Class) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2StereotypeProperty.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Class} stereotyped << UML2StereotypeProperty >> then instantiate a {@link UML2StereotypeProperty} proxy.
     * 
     * @return a {@link UML2StereotypeProperty} proxy on the created {@link Class}.
     */
    @objid ("6be8d6fe-20af-45cb-b04f-26839b476cb7")
    public static UML2StereotypeProperty create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Class");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2StereotypeProperty.STEREOTYPE_NAME);
        return UML2StereotypeProperty.instantiate((Class)e);
    }

    /**
     * Tries to instantiate a {@link UML2StereotypeProperty} proxy from a {@link Class} stereotyped << UML2StereotypeProperty >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Class
     * @return a {@link UML2StereotypeProperty} proxy or <i>null</i>.
     */
    @objid ("2f59e57c-a06e-4746-82d4-2148b8845562")
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
    @objid ("d6edd3bf-bd80-46d0-95da-0b001e6dd120")
    public static UML2StereotypeProperty safeInstantiate(Class obj) throws IllegalArgumentException {
        if (UML2StereotypeProperty.canInstantiate(obj))
        	return new UML2StereotypeProperty(obj);
        else
        	throw new IllegalArgumentException("UML2StereotypeProperty: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("65f44075-5ab6-452f-8dcc-a2e98b4ab4cf")
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
    @objid ("f041f464-e319-4f33-9cf0-9b10ef7d5028")
    public Class getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Property'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("c715fc33-4858-439b-8f9e-619d266a4ded")
    public String getProperty() {
        return this.elt.getTagValue(UML2StereotypeProperty.MdaTypes.PROPERTY_TAGTYPE_ELT);
    }

    @objid ("00cd04d3-e2ec-4d56-93b3-c44bc5742bed")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'Property'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("499180fe-8c7f-4624-8bea-e6e73cf1abd0")
    public void setProperty(String value) {
        this.elt.putTagValue(UML2StereotypeProperty.MdaTypes.PROPERTY_TAGTYPE_ELT, value);
    }

    @objid ("551a08fb-1578-4edc-85f6-0c71904f3f4c")
    protected UML2StereotypeProperty(Class elt) {
        this.elt = elt;
    }

    @objid ("3e2c97cc-5fc7-4642-8c2c-e5591f0c6d83")
    public static final class MdaTypes {
        @objid ("30b7416c-3ab3-4342-9be8-9c85fd701f7d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("77d6ab71-c692-4321-8b7a-6e33f256d4d1")
        public static TagType PROPERTY_TAGTYPE_ELT;

        @objid ("ff5b465a-8834-4b5f-9cc6-ad3496f40bdf")
        private static Stereotype MDAASSOCDEP;

        @objid ("cf0811c1-b386-4029-a882-2bb591493177")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f5904e80-8f08-4709-9638-c11e84fe42e4")
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
