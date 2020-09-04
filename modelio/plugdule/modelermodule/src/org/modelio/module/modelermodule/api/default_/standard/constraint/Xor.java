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
    @objid ("5fc29307-f4b3-4971-9e32-8480688c5a29")
    public static final String STEREOTYPE_NAME = "xor";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("3e082bb8-4c09-49f3-a138-e82ccc0bebfe")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Xor proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << xor >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e809e8c9-86f7-48ab-9f23-9c3b8a29f062")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Xor.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << xor >> then instantiate a {@link Xor} proxy.
     * 
     * @return a {@link Xor} proxy on the created {@link Constraint}.
     */
    @objid ("9afd55af-ad67-45ba-a3b4-899cf8558f9d")
    public static Xor create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Xor.STEREOTYPE_NAME);
        return Xor.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Xor} proxy from a {@link Constraint} stereotyped << xor >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Xor} proxy or <i>null</i>.
     */
    @objid ("de5f1391-07c6-4936-b0f9-17e3d87caf39")
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
    @objid ("30e0e0b9-e057-42b0-ae32-d8152b4fec30")
    public static Xor safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Xor.canInstantiate(obj))
        	return new Xor(obj);
        else
        	throw new IllegalArgumentException("Xor: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("890dec35-446e-4b56-9f94-a103926b8de4")
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
    @objid ("79f171d1-effb-4604-ab62-f254f3b66298")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("851fc00a-eb94-4dfd-a24f-059a859967d2")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b1150880-0578-4ecd-8fd7-94942cf78744")
    protected Xor(Constraint elt) {
        this.elt = elt;
    }

    @objid ("883eb518-ea9f-4c9d-a048-324ca600d764")
    public static final class MdaTypes {
        @objid ("ab603cd2-7352-437d-b0f2-71e0c89a8e69")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("47231c04-c8bc-41fc-984e-462985f1cc96")
        private static Stereotype MDAASSOCDEP;

        @objid ("06ff772d-0d68-4f31-b500-71b2f9d8591d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("68e2b540-3829-497d-a06f-ef5befe08867")
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
