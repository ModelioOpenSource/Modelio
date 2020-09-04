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
package org.modelio.module.modelermodule.api.default_.standard.interaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
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
 * Proxy class to handle a {@link Interaction} with << scenario >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f5f40ae1-5948-4d16-9830-c685c68ebd5d")
public class Scenario {
    @objid ("b92ba6ad-e0bf-4430-bd16-c9dd15ec57b7")
    public static final String STEREOTYPE_NAME = "scenario";

    /**
     * The underlying {@link Interaction} represented by this proxy, never null.
     */
    @objid ("5a8886c2-f8a8-4476-9f27-66f4bad8f498")
    protected final Interaction elt;

    /**
     * Tells whether a {@link Scenario proxy} can be instantiated from a {@link MObject} checking it is a {@link Interaction} stereotyped << scenario >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("07d35518-bb91-4ffc-b6fd-0f127b9881ab")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Interaction) && ((Interaction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Scenario.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Interaction} stereotyped << scenario >> then instantiate a {@link Scenario} proxy.
     * 
     * @return a {@link Scenario} proxy on the created {@link Interaction}.
     */
    @objid ("912f1570-4556-417f-a52b-b3f6ae24eb2f")
    public static Scenario create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Interaction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Scenario.STEREOTYPE_NAME);
        return Scenario.instantiate((Interaction)e);
    }

    /**
     * Tries to instantiate a {@link Scenario} proxy from a {@link Interaction} stereotyped << scenario >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Interaction
     * @return a {@link Scenario} proxy or <i>null</i>.
     */
    @objid ("3d0c1df8-f574-4c50-9eeb-95ba85a29ad1")
    public static Scenario instantiate(Interaction obj) {
        return Scenario.canInstantiate(obj) ? new Scenario(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Scenario} proxy from a {@link Interaction} stereotyped << scenario >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Interaction}
     * @return a {@link Scenario} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f556b527-446c-4525-98ec-19997a3b7e6d")
    public static Scenario safeInstantiate(Interaction obj) throws IllegalArgumentException {
        if (Scenario.canInstantiate(obj))
        	return new Scenario(obj);
        else
        	throw new IllegalArgumentException("Scenario: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b074fcd4-edbb-4c13-9ea0-eb9ad841ec4c")
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
        Scenario other = (Scenario) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Interaction}. 
     * @return the Interaction represented by this proxy, never null.
     */
    @objid ("c4635598-5586-45aa-89c5-94342cf26659")
    public Interaction getElement() {
        return this.elt;
    }

    @objid ("c4a3a3f4-d71a-4cf8-b66b-d4a61f53c294")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("77ed475c-615c-4795-9e60-d5bbe36823f0")
    protected Scenario(Interaction elt) {
        this.elt = elt;
    }

    @objid ("4f33c4c3-47eb-4567-b0d2-04f872afd3f4")
    public static final class MdaTypes {
        @objid ("f64b6a28-4d9c-4b47-91eb-e4ddbd86e1c1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a897a3fa-4116-4b80-9998-a9acccba98ce")
        private static Stereotype MDAASSOCDEP;

        @objid ("64e816f4-8c76-4498-b1c6-320059d19241")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("99991d10-3081-44a1-a8a9-db8844dd60bf")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec1ac4-0000-2eff-0000-000000000000");
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
