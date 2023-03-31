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
 * Proxy class to handle a {@link Dependency} with << UML2ComponentRealization >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("20892636-39d2-4f82-9ef5-673a7149b405")
public class UML2ComponentRealization {
    @objid ("f970b00b-c9b7-4006-9362-a0083b7c9ef5")
    public static final String STEREOTYPE_NAME = "UML2ComponentRealization";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("91fd3ada-9acc-4dc2-9572-ca5640ab5132")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2ComponentRealization proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2ComponentRealization >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("777fe3a9-6d5a-440b-b7d0-3e59e8533612")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ComponentRealization.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2ComponentRealization >> then instantiate a {@link UML2ComponentRealization} proxy.
     * 
     * @return a {@link UML2ComponentRealization} proxy on the created {@link Dependency}.
     */
    @objid ("4b7b903a-46b5-47e7-b8e5-debc95ab2ed7")
    public static UML2ComponentRealization create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ComponentRealization.STEREOTYPE_NAME);
        return UML2ComponentRealization.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2ComponentRealization} proxy from a {@link Dependency} stereotyped << UML2ComponentRealization >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2ComponentRealization} proxy or <i>null</i>.
     */
    @objid ("48590fd3-d6f8-487b-856d-607753ae2862")
    public static UML2ComponentRealization instantiate(Dependency obj) {
        return UML2ComponentRealization.canInstantiate(obj) ? new UML2ComponentRealization(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ComponentRealization} proxy from a {@link Dependency} stereotyped << UML2ComponentRealization >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2ComponentRealization} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d72ebac0-f420-45d5-bc4c-aa6a108a50db")
    public static UML2ComponentRealization safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2ComponentRealization.canInstantiate(obj))
        	return new UML2ComponentRealization(obj);
        else
        	throw new IllegalArgumentException("UML2ComponentRealization: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1b6310a3-9637-46d3-a470-347a607a4419")
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
        UML2ComponentRealization other = (UML2ComponentRealization) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("d1b1ac26-4dfa-4467-8a52-6e44c9a38a84")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("c4afff0f-3a7a-495a-a03f-b6a02eb2739c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("aa16d44e-c361-4ed8-af06-55b5d9bc4e80")
    protected  UML2ComponentRealization(Dependency elt) {
        this.elt = elt;
    }

    @objid ("c07294a9-7636-4649-a593-5c852a1a29bd")
    public static final class MdaTypes {
        @objid ("b7c07634-2ac5-4849-bde7-758403e15273")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8162131f-7f2d-424a-b0af-8c1716f9aed2")
        private static Stereotype MDAASSOCDEP;

        @objid ("e468f90b-2492-4b0a-acf9-f9ce10879d4e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e4ae9b64-6109-42cc-97f5-e9554b73ef4a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "99bf7919-5d09-11df-a996-001302895b2b");
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
