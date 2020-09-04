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
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << derive >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0a2b7f32-1cd2-44cd-b61d-f4464bbf91de")
public class Derive {
    @objid ("2c64365f-89dc-47b3-b6ff-eb23e87aa331")
    public static final String STEREOTYPE_NAME = "derive";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("90e9459e-f46d-40a3-950b-464458a53985")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Derive proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << derive >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("4942595d-66c1-44a8-b7f0-283a2205648d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Derive.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << derive >> then instantiate a {@link Derive} proxy.
     * 
     * @return a {@link Derive} proxy on the created {@link Dependency}.
     */
    @objid ("b9d65e9f-ffc2-4d8a-bbc9-862c11f1718c")
    public static Derive create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Derive.STEREOTYPE_NAME);
        return Derive.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Derive} proxy from a {@link Dependency} stereotyped << derive >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Derive} proxy or <i>null</i>.
     */
    @objid ("0c93cffa-646f-4fa3-8dcf-b388f2645876")
    public static Derive instantiate(Dependency obj) {
        return Derive.canInstantiate(obj) ? new Derive(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Derive} proxy from a {@link Dependency} stereotyped << derive >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Derive} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f9cfa02c-7286-4154-a0b1-7451062ab859")
    public static Derive safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Derive.canInstantiate(obj))
        	return new Derive(obj);
        else
        	throw new IllegalArgumentException("Derive: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("fae3f91a-f918-48b7-a7cc-9d7ed70540ab")
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
        Derive other = (Derive) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("e61e344e-27ef-4ce6-96a4-fc612bfc3e1e")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("46adf623-3502-423b-9a66-ae527487b81b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("00a90fcd-cd77-4dae-ae2a-2c0899358ac1")
    protected Derive(Dependency elt) {
        this.elt = elt;
    }

    @objid ("7dea825e-1f89-4805-9f37-7ff8a5c37024")
    public static final class MdaTypes {
        @objid ("2f8c00a0-ac01-4a97-b641-b73b8858c950")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5dc4a3e3-fe21-4849-9268-9e9bf5988b75")
        private static Stereotype MDAASSOCDEP;

        @objid ("536b9eea-5fd5-4f94-b93a-cd806680cb8b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("846ac25f-ad1b-4a14-98a7-5b59008298ad")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-021a-0000-000000000000");
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
