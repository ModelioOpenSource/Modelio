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
    @objid ("04e08e31-5ec0-4396-960f-de74697ca4e2")
    public static final String STEREOTYPE_NAME = "implementation";

    /**
     * The underlying {@link Generalization} represented by this proxy, never null.
     */
    @objid ("ff53e72c-bdd2-4709-ac26-58a22913ce22")
    protected final Generalization elt;

    /**
     * Tells whether a {@link Implementation proxy} can be instantiated from a {@link MObject} checking it is a {@link Generalization} stereotyped << implementation >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("0ae95a58-c2f1-4de2-bb35-4b6f6f41d979")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Generalization) && ((Generalization) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Implementation.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Generalization} stereotyped << implementation >> then instantiate a {@link Implementation} proxy.
     * 
     * @return a {@link Implementation} proxy on the created {@link Generalization}.
     */
    @objid ("6c6bf91a-a997-4295-ba77-335591e7a65b")
    public static Implementation create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Generalization");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Implementation.STEREOTYPE_NAME);
        return Implementation.instantiate((Generalization)e);
    }

    /**
     * Tries to instantiate a {@link Implementation} proxy from a {@link Generalization} stereotyped << implementation >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Generalization
     * @return a {@link Implementation} proxy or <i>null</i>.
     */
    @objid ("52206adc-3508-4294-8928-752f7d4ab06a")
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
    @objid ("771d5df8-38d2-4513-90a3-41ef0b14b332")
    public static Implementation safeInstantiate(Generalization obj) throws IllegalArgumentException {
        if (Implementation.canInstantiate(obj))
        	return new Implementation(obj);
        else
        	throw new IllegalArgumentException("Implementation: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("cd082d2d-6541-430d-8932-56aa3efe8c85")
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
    @objid ("be651748-7fd2-447b-96ad-e902f1c364ee")
    public Generalization getElement() {
        return this.elt;
    }

    @objid ("aa81c0b5-f81c-425e-ab17-8a465d69ffc5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("fd77b5b9-718e-4bb5-ace4-e59c589967f5")
    protected Implementation(Generalization elt) {
        this.elt = elt;
    }

    @objid ("38988b48-d6c0-416a-a9ab-c1d8cde9859c")
    public static final class MdaTypes {
        @objid ("d75ab0e6-1f6d-4543-8834-1abac69232e2")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5bf0ab82-a5d0-434c-9f93-477d33c99ecd")
        private static Stereotype MDAASSOCDEP;

        @objid ("81c9a07f-5c7b-4f26-9326-999e167c1be1")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c83b70fc-19f2-4020-b2c6-2d8ee2dac2d1")
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
