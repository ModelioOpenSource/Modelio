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
 * Proxy class to handle a {@link Dependency} with << guarantee >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("59095dca-f0dc-4be2-a58b-5035f5929642")
public class Guarantee {
    @objid ("1ea74479-33fd-4575-972a-b4eeb69406b5")
    public static final String STEREOTYPE_NAME = "guarantee";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("eaa55f75-da98-45cd-bfa4-58584cb6a768")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Guarantee proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << guarantee >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("def3ebc0-943b-440f-bd47-c620d0e2c553")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Guarantee.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << guarantee >> then instantiate a {@link Guarantee} proxy.
     * 
     * @return a {@link Guarantee} proxy on the created {@link Dependency}.
     */
    @objid ("52070f6b-74f2-433c-aa99-12e3f4c26d2e")
    public static Guarantee create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Guarantee.STEREOTYPE_NAME);
        return Guarantee.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Guarantee} proxy from a {@link Dependency} stereotyped << guarantee >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Guarantee} proxy or <i>null</i>.
     */
    @objid ("9bc9f924-cbda-45bd-86d6-996b7c288fc5")
    public static Guarantee instantiate(Dependency obj) {
        return Guarantee.canInstantiate(obj) ? new Guarantee(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Guarantee} proxy from a {@link Dependency} stereotyped << guarantee >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Guarantee} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9bd26c05-4354-4fd2-a484-8007268ad21b")
    public static Guarantee safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Guarantee.canInstantiate(obj))
        	return new Guarantee(obj);
        else
        	throw new IllegalArgumentException("Guarantee: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("7cec8941-51e8-487e-8c82-3c339f7bb5ec")
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
        Guarantee other = (Guarantee) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("f48590db-ef27-466b-ac15-39ddd78a68dc")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("e5c4b345-d02d-4b60-9b1e-20e24cdfb53f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("7ac618ae-70e1-4099-91f1-ce493baca277")
    protected Guarantee(Dependency elt) {
        this.elt = elt;
    }

    @objid ("d5bd302d-6ad5-4ad1-bfa4-bca43f79b810")
    public static final class MdaTypes {
        @objid ("e61f0f3b-a867-4d55-8f47-d91932210020")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0170b5d7-4a9a-4718-ae6f-73245291eef1")
        private static Stereotype MDAASSOCDEP;

        @objid ("8da52133-c990-49bc-b8f6-b643e2fa49ce")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3d2a72c4-9db7-4374-acb4-9b5ef25bf1d0")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0251-0000-000000000000");
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
