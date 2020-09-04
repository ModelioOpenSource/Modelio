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
 * Proxy class to handle a {@link Dependency} with << UML2StructuralFeatureReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("8dcc2244-21d0-4ae1-8601-ed7e4b3c648e")
public class UML2StructuralFeatureReference {
    @objid ("092dbec5-1cab-46c7-94d5-1c3404422b77")
    public static final String STEREOTYPE_NAME = "UML2StructuralFeatureReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("83cefdbb-250f-49ad-ab9f-69c177e5e228")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2StructuralFeatureReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2StructuralFeatureReference >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("ac885140-8ba1-4013-884c-6ba19ec9a0ba")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2StructuralFeatureReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2StructuralFeatureReference >> then instantiate a {@link UML2StructuralFeatureReference} proxy.
     * 
     * @return a {@link UML2StructuralFeatureReference} proxy on the created {@link Dependency}.
     */
    @objid ("c7ded1be-c0e0-40b3-8b93-6e470a37850f")
    public static UML2StructuralFeatureReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2StructuralFeatureReference.STEREOTYPE_NAME);
        return UML2StructuralFeatureReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2StructuralFeatureReference} proxy from a {@link Dependency} stereotyped << UML2StructuralFeatureReference >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2StructuralFeatureReference} proxy or <i>null</i>.
     */
    @objid ("f9a7a194-d265-4b53-8422-3382167041c6")
    public static UML2StructuralFeatureReference instantiate(Dependency obj) {
        return UML2StructuralFeatureReference.canInstantiate(obj) ? new UML2StructuralFeatureReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2StructuralFeatureReference} proxy from a {@link Dependency} stereotyped << UML2StructuralFeatureReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2StructuralFeatureReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c0303cb0-863e-43dd-bc09-dd0b726e9237")
    public static UML2StructuralFeatureReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2StructuralFeatureReference.canInstantiate(obj))
        	return new UML2StructuralFeatureReference(obj);
        else
        	throw new IllegalArgumentException("UML2StructuralFeatureReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c8b3c7dd-855a-4c04-a2c3-b9cbc42bbd27")
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
        UML2StructuralFeatureReference other = (UML2StructuralFeatureReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("92de7cd4-af63-43fa-be22-f2d7fa8dd45a")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("b5e3f10d-c2f3-4f8d-bbe3-4ae4f70f64e3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("87b48dc2-e0f0-4aef-b290-cee2718fba30")
    protected UML2StructuralFeatureReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("fb91f583-43cf-41d9-961d-4c8f8c7ed445")
    public static final class MdaTypes {
        @objid ("93c46a51-588d-4251-a406-586f8b9f7336")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d36f64a6-333f-4f67-acf1-8e78f9d21cd0")
        private static Stereotype MDAASSOCDEP;

        @objid ("3e2783e8-9d47-408d-8237-c8dc45bda342")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("bc719035-47d8-46b3-8a3c-7b8d7c929845")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ed0bb1c3-de99-11de-905b-001302895b2b");
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
