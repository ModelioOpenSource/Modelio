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
    @objid ("0a1a3ad8-6551-4f75-8600-01761c484c2e")
    public static final String STEREOTYPE_NAME = "xor";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("ce43a752-1e96-4cf1-8b2e-e18cd18a0228")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Xor proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << xor >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("24e7c369-fda1-42d5-8072-48bd666db9c9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Xor.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << xor >> then instantiate a {@link Xor} proxy.
     * 
     * @return a {@link Xor} proxy on the created {@link Constraint}.
     */
    @objid ("3395e502-8e90-41a4-95c1-9e353c538d5b")
    public static Xor create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
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
    @objid ("9da30656-ba40-4c18-8b86-edb1a7c80e6f")
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
    @objid ("55866a1d-4650-461e-b87b-3d46b464fec3")
    public static Xor safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Xor.canInstantiate(obj))
        	return new Xor(obj);
        else
        	throw new IllegalArgumentException("Xor: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f89a3820-5403-471a-a538-8497ea0f877c")
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
    @objid ("10c91b02-ed04-4595-95f3-6839eb087a85")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("72f452c3-9948-47c8-b92f-7e10e0b7bd96")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("db3edc9a-7f96-4ce4-b1f5-43d653264506")
    protected Xor(Constraint elt) {
        this.elt = elt;
    }

    @objid ("883eb518-ea9f-4c9d-a048-324ca600d764")
    public static final class MdaTypes {
        @objid ("840f0291-24cf-4e41-afc8-d44f68d66167")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7d0197c6-9a4d-40e5-ac8f-f24122f301fc")
        private static Stereotype MDAASSOCDEP;

        @objid ("5b3f1617-b83c-4fd7-87ce-b43cb5a85847")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("645e6191-f18f-4a4e-9722-f6f4edaca789")
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
