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
    @objid ("5b12bdc6-8cc9-4fa0-85a1-03ba359ac838")
    public static final String STEREOTYPE_NAME = "scenario";

    /**
     * The underlying {@link Interaction} represented by this proxy, never null.
     */
    @objid ("cb41e007-ff6b-4a22-8308-a2a73b04f0b7")
    protected final Interaction elt;

    /**
     * Tells whether a {@link Scenario proxy} can be instantiated from a {@link MObject} checking it is a {@link Interaction} stereotyped << scenario >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4c2c3607-db1d-474c-8321-23d010149c3d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Interaction) && ((Interaction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Scenario.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Interaction} stereotyped << scenario >> then instantiate a {@link Scenario} proxy.
     * 
     * @return a {@link Scenario} proxy on the created {@link Interaction}.
     */
    @objid ("d7ffe2f0-1bb2-49dc-a7fb-47505cc0c9d7")
    public static Scenario create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Interaction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Scenario.STEREOTYPE_NAME);
        return Scenario.instantiate((Interaction)e);
    }

    /**
     * Tries to instantiate a {@link Scenario} proxy from a {@link Interaction} stereotyped << scenario >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Interaction
     * @return a {@link Scenario} proxy or <i>null</i>.
     */
    @objid ("66de24d5-b8af-4db8-8dd1-087ab11e65fa")
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
    @objid ("61573f6a-0dd2-43da-abf9-bfc3240b4a9d")
    public static Scenario safeInstantiate(Interaction obj) throws IllegalArgumentException {
        if (Scenario.canInstantiate(obj))
        	return new Scenario(obj);
        else
        	throw new IllegalArgumentException("Scenario: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9b224f62-5fbe-4f5b-b594-f08c7980d238")
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
    @objid ("2380fcf1-275e-4bd0-9f96-3b2027646eff")
    public Interaction getElement() {
        return this.elt;
    }

    @objid ("b3a448c5-586e-4ba6-b17b-6163e68ed691")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2d64f8b4-f74b-4188-88b7-4c40e22b633c")
    protected Scenario(Interaction elt) {
        this.elt = elt;
    }

    @objid ("4f33c4c3-47eb-4567-b0d2-04f872afd3f4")
    public static final class MdaTypes {
        @objid ("b99e821e-7a54-40ce-a86f-5f8975e89303")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7a38aea6-b276-41d4-a0af-2421e33a6016")
        private static Stereotype MDAASSOCDEP;

        @objid ("e1f593db-2c78-4d34-b99a-0b12ca1183ec")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("baebe987-d18f-4bae-a4b2-7588653c7c45")
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
