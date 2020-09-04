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
 * Proxy class to handle a {@link Constraint} with << overlapping >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("56431f9b-f67b-4509-9a09-2227ea5aef29")
public class Overlapping {
    @objid ("cfadae37-6735-4336-977d-852914699f80")
    public static final String STEREOTYPE_NAME = "overlapping";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("92584aab-31db-4957-8796-dbb3c28af5c3")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Overlapping proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << overlapping >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e35b83e6-773b-4ee6-8bf6-c12f9b19c3e8")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Overlapping.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << overlapping >> then instantiate a {@link Overlapping} proxy.
     * 
     * @return a {@link Overlapping} proxy on the created {@link Constraint}.
     */
    @objid ("c9705638-deda-4833-9fa8-869c5a092376")
    public static Overlapping create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Overlapping.STEREOTYPE_NAME);
        return Overlapping.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Overlapping} proxy from a {@link Constraint} stereotyped << overlapping >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Overlapping} proxy or <i>null</i>.
     */
    @objid ("c48c46c8-c459-41cf-9321-ef51d6a4014e")
    public static Overlapping instantiate(Constraint obj) {
        return Overlapping.canInstantiate(obj) ? new Overlapping(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Overlapping} proxy from a {@link Constraint} stereotyped << overlapping >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Overlapping} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a4647b64-37ef-4310-8c30-ee4ea2b18248")
    public static Overlapping safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Overlapping.canInstantiate(obj))
        	return new Overlapping(obj);
        else
        	throw new IllegalArgumentException("Overlapping: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2ed65aee-d6b0-4bd4-8d2d-55571f008a7d")
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
        Overlapping other = (Overlapping) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("92f0978c-fb65-4fd6-b180-bf7add34828b")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("b66a63b0-c2c3-477a-abaa-4fe0eca33872")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b981bdcc-b21f-4c26-9bcb-76580489b5f4")
    protected Overlapping(Constraint elt) {
        this.elt = elt;
    }

    @objid ("ae8f7c08-3dd0-4e2e-9abe-678cf39942a8")
    public static final class MdaTypes {
        @objid ("66868f3b-7a15-4770-9ce0-436a0a3cb5a7")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7393649e-f129-44f7-b21a-afb2e57c42d0")
        private static Stereotype MDAASSOCDEP;

        @objid ("ba2945b7-ee38-4058-a1bc-c0897492ba13")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3c6f0790-0cee-4f7e-9574-2eef3427ab51")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01fb-0000-000000000000");
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
