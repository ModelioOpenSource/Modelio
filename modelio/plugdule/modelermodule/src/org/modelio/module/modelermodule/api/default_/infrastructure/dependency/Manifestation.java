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
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

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
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << manifestation >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("21713da7-5de8-4464-8371-298380f8461d")
public class Manifestation {
    @objid ("a088dbad-79ec-4516-ae6b-64d4c05a571a")
    public static final String STEREOTYPE_NAME = "manifestation";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("fa60e571-e87f-48ff-89c7-c830c6fb9ea6")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Manifestation proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << manifestation >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("09c87795-261a-4a9b-bdac-2e530400e287")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Manifestation.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << manifestation >> then instantiate a {@link Manifestation} proxy.
     * 
     * @return a {@link Manifestation} proxy on the created {@link Dependency}.
     */
    @objid ("337c6b59-b24c-4207-8bcd-8a35ec8c7464")
    public static Manifestation create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Manifestation.STEREOTYPE_NAME);
        return Manifestation.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Manifestation} proxy from a {@link Dependency} stereotyped << manifestation >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Manifestation} proxy or <i>null</i>.
     */
    @objid ("31dffc31-289e-451a-9c87-23524e3b317d")
    public static Manifestation instantiate(Dependency obj) {
        return Manifestation.canInstantiate(obj) ? new Manifestation(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Manifestation} proxy from a {@link Dependency} stereotyped << manifestation >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Manifestation} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("149b4179-46d4-4a8c-a311-108e228d0f1c")
    public static Manifestation safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Manifestation.canInstantiate(obj))
        	return new Manifestation(obj);
        else
        	throw new IllegalArgumentException("Manifestation: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("4b8fef40-0992-4a2d-b087-a2f0c6f52c42")
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
        Manifestation other = (Manifestation) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("ce3802d5-f62f-4218-b316-b0ddab36b847")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("75cac40b-3d08-4700-bb2c-70dd06929491")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("5995ef7b-b1c9-4b1a-8699-42960ab381ce")
    protected Manifestation(Dependency elt) {
        this.elt = elt;
    }

    @objid ("a2578fb0-0629-4fb7-8870-be21ae2eb03a")
    public static final class MdaTypes {
        @objid ("c5fc4d2e-a31e-4b4d-b126-b2f5073a2fe3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7b789c52-7826-4a0c-83d6-d0b5582e0744")
        private static Stereotype MDAASSOCDEP;

        @objid ("3ee80efc-2b0b-466f-becf-1cf7fdbbec01")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("def67e58-686c-4bc7-9f3f-cd83f79b5820")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "d5bccf8e-79b3-48df-8c79-09200aa52d19");
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
