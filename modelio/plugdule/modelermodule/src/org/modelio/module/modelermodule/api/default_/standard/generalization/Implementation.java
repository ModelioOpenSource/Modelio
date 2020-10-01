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
package org.modelio.module.modelermodule.api.default_.standard.generalization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Generalization} with << implementation >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("537f865d-60d7-49df-a0f5-0faa2f98dc42")
public class Implementation {
    @objid ("dfbd6694-71b4-4a29-ae4d-0915bd7a46ed")
    public static final String STEREOTYPE_NAME = "implementation";

    /**
     * The underlying {@link Generalization} represented by this proxy, never null.
     */
    @objid ("2d4b32a2-88e1-41e6-946b-15f93eeca12d")
    protected final Generalization elt;

    /**
     * Tells whether a {@link Implementation proxy} can be instantiated from a {@link MObject} checking it is a {@link Generalization} stereotyped << implementation >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ba0e8a8d-f511-45a7-a941-c497ed36f1b9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Generalization) && ((Generalization) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Implementation.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Generalization} stereotyped << implementation >> then instantiate a {@link Implementation} proxy.
     * 
     * @return a {@link Implementation} proxy on the created {@link Generalization}.
     */
    @objid ("5cef2585-7c4b-42b5-985e-f5b0327a9679")
    public static Implementation create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Generalization");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Implementation.STEREOTYPE_NAME);
        return Implementation.instantiate((Generalization)e);
    }

    /**
     * Tries to instantiate a {@link Implementation} proxy from a {@link Generalization} stereotyped << implementation >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Generalization
     * @return a {@link Implementation} proxy or <i>null</i>.
     */
    @objid ("54e490ae-b4aa-4ec8-9ca7-bdc02d0f65c7")
    public static Implementation instantiate(Generalization obj) {
        return Implementation.canInstantiate(obj) ? new Implementation(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Implementation} proxy from a {@link Generalization} stereotyped << implementation >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Generalization}
     * @return a {@link Implementation} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("0b1d2b26-5bad-486d-a7ca-6cfa411297cd")
    public static Implementation safeInstantiate(Generalization obj) throws IllegalArgumentException {
        if (Implementation.canInstantiate(obj))
        	return new Implementation(obj);
        else
        	throw new IllegalArgumentException("Implementation: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d3f5476a-2afa-4674-98db-c9cc0797c0f8")
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
        Implementation other = (Implementation) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Generalization}. 
     * @return the Generalization represented by this proxy, never null.
     */
    @objid ("5f81d9bf-c5db-4e81-85e6-31c3a66c0de5")
    public Generalization getElement() {
        return this.elt;
    }

    @objid ("39a155c0-d3b1-4949-bdfe-7f49e606f4f1")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("8194cb04-d8cb-4e49-9f50-910396b20226")
    protected Implementation(Generalization elt) {
        this.elt = elt;
    }

    @objid ("38988b48-d6c0-416a-a9ab-c1d8cde9859c")
    public static final class MdaTypes {
        @objid ("8d423656-116c-453a-b0bc-df6f41681054")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a6c7b655-caf9-4103-bdb7-97fecb786c32")
        private static Stereotype MDAASSOCDEP;

        @objid ("f226b812-c64b-41d6-8c89-33fa6543da7e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9710cb73-cd7a-458c-8b54-c74b17dfbce3")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01c7-0000-000000000000");
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
