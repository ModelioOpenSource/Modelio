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
 * Proxy class to handle a {@link Constraint} with << incomplete >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("b8be14f7-5bfd-4614-81be-eb12e3be42a2")
public class Incomplete {
    @objid ("a7e10eb0-6401-4de2-bf16-5cff9c108974")
    public static final String STEREOTYPE_NAME = "incomplete";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("5561a5e2-b618-471e-a283-9798b09694a9")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Incomplete proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << incomplete >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("1c467a56-dfa0-448a-b288-29511438a254")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Incomplete.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << incomplete >> then instantiate a {@link Incomplete} proxy.
     * 
     * @return a {@link Incomplete} proxy on the created {@link Constraint}.
     */
    @objid ("c0887696-5cfc-46cd-9620-4226f656ebf0")
    public static Incomplete create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Constraint");
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
    @objid ("da3998db-f5bd-4a0d-a73e-c81eef2a4a5c")
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
    @objid ("81f709e1-ae4c-4ec7-bcef-6fb23b6f0b4e")
    public static Incomplete safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Incomplete.canInstantiate(obj))
        	return new Incomplete(obj);
        else
        	throw new IllegalArgumentException("Incomplete: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("86cf6b75-ae5e-4f32-820e-b3f637ce20c8")
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
    @objid ("4cd1f8c4-aab0-4b1e-a12a-0a774328b4ba")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("a72f2143-4d60-4009-b31a-60d92f9e0fde")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("0ebb60ab-1393-43f0-bee1-067416f64926")
    protected  Incomplete(Constraint elt) {
        this.elt = elt;
    }

    @objid ("73e28435-6124-4305-92ec-992d4d7fdbe1")
    public static final class MdaTypes {
        @objid ("b1d89b66-8b61-40f9-93db-71c1df511686")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3e2e5845-50a9-4885-b690-8b78622afd1b")
        private static Stereotype MDAASSOCDEP;

        @objid ("d88dc060-b623-49f2-9a55-98c9cadbce5b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2aa87a61-38a2-43a6-a520-25f03dd95531")
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
