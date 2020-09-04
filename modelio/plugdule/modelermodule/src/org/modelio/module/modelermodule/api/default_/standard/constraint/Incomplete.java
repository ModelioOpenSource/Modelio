/* 
 * Copyright 2013-2019 Modeliosoft
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
 * Proxy class to handle a {@link Constraint} with << incomplete >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("b8be14f7-5bfd-4614-81be-eb12e3be42a2")
public class Incomplete {
    @objid ("165bc515-7da5-47fd-bcd7-1091a9fe8c00")
    public static final String STEREOTYPE_NAME = "incomplete";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("64f16ae0-67ab-49a7-b734-96436b1abfaa")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Incomplete proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << incomplete >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("a37d3345-a055-42b2-b9bb-2e1b4d51f8e2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Incomplete.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << incomplete >> then instantiate a {@link Incomplete} proxy.
     * 
     * @return a {@link Incomplete} proxy on the created {@link Constraint}.
     */
    @objid ("dcd0e5a4-229b-430d-a0fb-1f4664ead14e")
    public static Incomplete create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Incomplete.STEREOTYPE_NAME);
        return Incomplete.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Incomplete} proxy from a {@link Constraint} stereotyped << incomplete >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Incomplete} proxy or <i>null</i>.
     */
    @objid ("8efc22cf-baf3-4cd6-86cc-4c424c76ca42")
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
    @objid ("9b34cc2b-0ba6-4b51-ba42-f0e487ec0798")
    public static Incomplete safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Incomplete.canInstantiate(obj))
        	return new Incomplete(obj);
        else
        	throw new IllegalArgumentException("Incomplete: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("dbc5423d-bf5f-4cb7-9a27-ee8ff74a7e4d")
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
    @objid ("ce6f6fb5-41e1-4af6-9170-a093e0e77849")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("eb0d2586-44bb-403c-80ba-b70c8eee4430")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("6d6662fc-7dea-42d1-a692-89819f354297")
    protected Incomplete(Constraint elt) {
        this.elt = elt;
    }

    @objid ("73e28435-6124-4305-92ec-992d4d7fdbe1")
    public static final class MdaTypes {
        @objid ("729ddd6a-74cc-4e1b-af3c-431db15b72c2")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e661a25f-5cb8-45b8-b194-c9a0fb1feecf")
        private static Stereotype MDAASSOCDEP;

        @objid ("5ae88851-f2c2-4126-9a8a-9a452e5afafc")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("09bb5c70-7eb3-430d-a613-9c75f465edc7")
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
