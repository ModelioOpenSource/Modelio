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
    @objid ("4b3d5dbf-3a12-4cc7-991f-753e497408f4")
    public static final String STEREOTYPE_NAME = "scenario";

    /**
     * The underlying {@link Interaction} represented by this proxy, never null.
     */
    @objid ("510259ba-36ec-4456-86b2-9c3074b3e0b0")
    protected final Interaction elt;

    /**
     * Tells whether a {@link Scenario proxy} can be instantiated from a {@link MObject} checking it is a {@link Interaction} stereotyped << scenario >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a6cfd5fa-4876-4053-a78b-df92f624ad23")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Interaction) && ((Interaction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Scenario.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Interaction} stereotyped << scenario >> then instantiate a {@link Scenario} proxy.
     * 
     * @return a {@link Scenario} proxy on the created {@link Interaction}.
     */
    @objid ("52428967-c61a-4898-9c98-f6d1a1ad7bc0")
    public static Scenario create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Interaction");
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
    @objid ("7a9eb325-9d26-49a7-8b3a-02151ac3d79f")
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
    @objid ("e140dc63-5f28-4495-a52c-adf130297a6a")
    public static Scenario safeInstantiate(Interaction obj) throws IllegalArgumentException {
        if (Scenario.canInstantiate(obj))
        	return new Scenario(obj);
        else
        	throw new IllegalArgumentException("Scenario: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2898d105-bde7-48bb-888e-ea29a95406cc")
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
    @objid ("26a93c11-39dd-451a-bee0-079d65983f9d")
    public Interaction getElement() {
        return this.elt;
    }

    @objid ("06393fdb-a0a9-4533-a9d8-8c9be38c5d49")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("c82c8200-9f6d-4426-8f86-b1f20effa4e1")
    protected  Scenario(Interaction elt) {
        this.elt = elt;
    }

    @objid ("4f33c4c3-47eb-4567-b0d2-04f872afd3f4")
    public static final class MdaTypes {
        @objid ("7a521b6a-d109-4096-8846-a88c7abd3b55")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a8daca42-0c9d-4627-b0e4-7b0869614cf9")
        private static Stereotype MDAASSOCDEP;

        @objid ("25ea3af3-c502-44bc-9319-b873af9b1b77")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("697bb737-9711-4a3d-a5de-6120423207a8")
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
