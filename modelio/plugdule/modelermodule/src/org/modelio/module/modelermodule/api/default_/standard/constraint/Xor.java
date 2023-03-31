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
package org.modelio.module.modelermodule.api.default_.standard.constraint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Constraint;
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
 * Proxy class to handle a {@link Constraint} with << xor >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("068acec6-4e04-4c0c-b2a6-986644ad0778")
public class Xor {
    @objid ("4ca16151-2f49-48d2-9d83-625bf534db05")
    public static final String STEREOTYPE_NAME = "xor";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("38036341-aa0f-480e-b0c4-9631f43d6dc4")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Xor proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << xor >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("15593366-14a6-4d65-a3b2-1136fa75d402")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Xor.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << xor >> then instantiate a {@link Xor} proxy.
     * 
     * @return a {@link Xor} proxy on the created {@link Constraint}.
     */
    @objid ("b4fe5b5d-b4a3-422b-93e7-4519fc79398a")
    public static Xor create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Xor.STEREOTYPE_NAME);
        return Xor.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Xor} proxy from a {@link Constraint} stereotyped << xor >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Xor} proxy or <i>null</i>.
     */
    @objid ("b2803fb1-a8f6-423c-a864-c3a07f38a80d")
    public static Xor instantiate(Constraint obj) {
        return Xor.canInstantiate(obj) ? new Xor(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Xor} proxy from a {@link Constraint} stereotyped << xor >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Xor} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("82e30e37-7ae6-4c08-97c0-9ab50de8bd6b")
    public static Xor safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Xor.canInstantiate(obj))
        	return new Xor(obj);
        else
        	throw new IllegalArgumentException("Xor: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("37dd89a9-1e9c-4002-b766-ca4e6d0db747")
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
        Xor other = (Xor) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("b7e81a59-027d-48d2-9304-ed8347d889cc")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("405c2b8f-04f6-4a5f-9af8-6a78e7dd828e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("5bfaf10d-7f5b-41e7-b642-637e0bacd7e3")
    protected  Xor(Constraint elt) {
        this.elt = elt;
    }

    @objid ("883eb518-ea9f-4c9d-a048-324ca600d764")
    public static final class MdaTypes {
        @objid ("629f8f7b-2743-4da6-9a0e-45c2e9dd8eab")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6229b9bb-e1f6-4d48-9122-38f1918c9f89")
        private static Stereotype MDAASSOCDEP;

        @objid ("8da0bd84-2d37-42e3-b955-3042874d75c6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c26d70ed-4148-4bf2-b648-1fedb609b2eb")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-0200-0000-000000000000");
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
