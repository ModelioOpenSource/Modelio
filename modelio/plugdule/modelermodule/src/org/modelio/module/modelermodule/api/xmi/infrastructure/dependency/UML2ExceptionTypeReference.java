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
package org.modelio.module.modelermodule.api.xmi.infrastructure.dependency;

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
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << UML2ExceptionTypeReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("87372d24-b077-4b6a-a76e-518b2d785b07")
public class UML2ExceptionTypeReference {
    @objid ("5fef17fa-8d68-403c-a214-10963bd6e0e6")
    public static final String STEREOTYPE_NAME = "UML2ExceptionTypeReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("58bd8981-1065-4fb4-a0d1-15465d426900")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2ExceptionTypeReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2ExceptionTypeReference >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0b440ee9-3c37-4be3-8394-f5022a00ec8c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExceptionTypeReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2ExceptionTypeReference >> then instantiate a {@link UML2ExceptionTypeReference} proxy.
     * 
     * @return a {@link UML2ExceptionTypeReference} proxy on the created {@link Dependency}.
     */
    @objid ("52d690a0-d8ce-4966-ad71-b93b7dd515e4")
    public static UML2ExceptionTypeReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExceptionTypeReference.STEREOTYPE_NAME);
        return UML2ExceptionTypeReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExceptionTypeReference} proxy from a {@link Dependency} stereotyped << UML2ExceptionTypeReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2ExceptionTypeReference} proxy or <i>null</i>.
     */
    @objid ("328d3e5c-6ea9-49bf-bee8-1a1eb3a9a326")
    public static UML2ExceptionTypeReference instantiate(Dependency obj) {
        return UML2ExceptionTypeReference.canInstantiate(obj) ? new UML2ExceptionTypeReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ExceptionTypeReference} proxy from a {@link Dependency} stereotyped << UML2ExceptionTypeReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2ExceptionTypeReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b0b27247-bdde-4ae1-88fd-af069e29babc")
    public static UML2ExceptionTypeReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2ExceptionTypeReference.canInstantiate(obj))
        	return new UML2ExceptionTypeReference(obj);
        else
        	throw new IllegalArgumentException("UML2ExceptionTypeReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("13b9cc37-b6a4-4653-9f8e-cc346269ce76")
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
        UML2ExceptionTypeReference other = (UML2ExceptionTypeReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("7b689a71-f4ae-4a9f-aa77-484c1df48d77")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("4fc345ae-ac36-4aea-ad25-750735979acd")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d795bd53-e8c0-4c03-a805-eeb512517be6")
    protected UML2ExceptionTypeReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("209cf20e-a9e7-4dbf-a4ff-221a53f12e81")
    public static final class MdaTypes {
        @objid ("4392e5c3-18e3-4f90-a083-8b8c7986b457")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a67283ba-7f7b-4820-babe-6cffb10413ba")
        private static Stereotype MDAASSOCDEP;

        @objid ("c0cb3ce5-f5cc-47b5-846f-39e73ef24d96")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("34e79a6a-6310-4eae-91c4-75f0326d3464")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "4e477e48-35b4-11df-9280-001302895b2b");
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
