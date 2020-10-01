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
 * Proxy class to handle a {@link Constraint} with << incomplete >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("b8be14f7-5bfd-4614-81be-eb12e3be42a2")
public class Incomplete {
    @objid ("8ab51ea2-146e-4f3a-8df7-596e24e636c6")
    public static final String STEREOTYPE_NAME = "incomplete";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("d4d85644-2a0a-4d81-9cff-49224c2f01f5")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Incomplete proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << incomplete >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("49343a88-c8d5-4195-ab43-79328df8a466")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Incomplete.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << incomplete >> then instantiate a {@link Incomplete} proxy.
     * 
     * @return a {@link Incomplete} proxy on the created {@link Constraint}.
     */
    @objid ("84e7afd4-df19-468b-962b-81b4805c4c1a")
    public static Incomplete create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Incomplete.STEREOTYPE_NAME);
        return Incomplete.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Incomplete} proxy from a {@link Constraint} stereotyped << incomplete >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Incomplete} proxy or <i>null</i>.
     */
    @objid ("1e7fd8eb-f998-4094-8341-11d4d16f48f7")
    public static Incomplete instantiate(Constraint obj) {
        return Incomplete.canInstantiate(obj) ? new Incomplete(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Incomplete} proxy from a {@link Constraint} stereotyped << incomplete >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Incomplete} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("7253fd1f-1e55-4fef-9024-f1c35fe79191")
    public static Incomplete safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Incomplete.canInstantiate(obj))
        	return new Incomplete(obj);
        else
        	throw new IllegalArgumentException("Incomplete: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("cb2bb433-9c40-4118-aa64-a0233973875f")
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
        Incomplete other = (Incomplete) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("b8112057-5bb8-4cf2-957c-035d6752b191")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("0acf573f-0129-4510-9ce7-5fb0fa27a883")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0acadd5e-e3fb-4c95-a7ee-37de01b1515f")
    protected Incomplete(Constraint elt) {
        this.elt = elt;
    }

    @objid ("73e28435-6124-4305-92ec-992d4d7fdbe1")
    public static final class MdaTypes {
        @objid ("38bb5088-a135-4eb1-a433-a28323c71cac")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ac17a610-a613-4fb0-bdeb-a4e5979ca274")
        private static Stereotype MDAASSOCDEP;

        @objid ("2eb368e9-4655-4709-a7ae-1dbf618c2ba3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c8d254bc-89db-44b8-b2c0-7d1c3b17d896")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01f7-0000-000000000000");
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
