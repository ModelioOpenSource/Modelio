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
    @objid ("78831973-4c2c-4f02-a373-a01e65254d93")
    public static final String STEREOTYPE_NAME = "UML2StructuralFeatureReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("ca183ae0-f98b-401c-bd3e-3894a007e690")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2StructuralFeatureReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2StructuralFeatureReference >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("50fece49-deb8-418d-80f6-f3f1dc5ebb3d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2StructuralFeatureReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2StructuralFeatureReference >> then instantiate a {@link UML2StructuralFeatureReference} proxy.
     * 
     * @return a {@link UML2StructuralFeatureReference} proxy on the created {@link Dependency}.
     */
    @objid ("8a36faff-78ab-4f0e-91f8-9142f33d9a8b")
    public static UML2StructuralFeatureReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2StructuralFeatureReference.STEREOTYPE_NAME);
        return UML2StructuralFeatureReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2StructuralFeatureReference} proxy from a {@link Dependency} stereotyped << UML2StructuralFeatureReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2StructuralFeatureReference} proxy or <i>null</i>.
     */
    @objid ("3a2e7b25-6a93-4020-b5dd-edfdd203b49f")
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
    @objid ("ea1d85e7-af99-4626-8ff4-d3f3fecd836f")
    public static UML2StructuralFeatureReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2StructuralFeatureReference.canInstantiate(obj))
        	return new UML2StructuralFeatureReference(obj);
        else
        	throw new IllegalArgumentException("UML2StructuralFeatureReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b87afd60-3915-4a20-bd45-3583f3532a15")
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
    @objid ("97870863-df6b-4e88-a400-251ba4ba0fc1")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("be4f2ce7-bee3-411a-8ffa-dde3be28258f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("ef801150-937b-4715-a600-85b61aa25f83")
    protected  UML2StructuralFeatureReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("fb91f583-43cf-41d9-961d-4c8f8c7ed445")
    public static final class MdaTypes {
        @objid ("f944e045-4ce0-4d4f-a80e-05de9ebc4280")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("842d24d0-1776-4adf-84b9-1cd1d69abe82")
        private static Stereotype MDAASSOCDEP;

        @objid ("62f5c870-f8a0-47a5-8222-410bc5ff142b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5da470a3-0fc3-4e85-86b0-a8942867fc6b")
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
