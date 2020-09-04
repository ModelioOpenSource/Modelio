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
 * Proxy class to handle a {@link Dependency} with << impact_subroot >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("22b736f4-9a7f-47e0-addf-c7a7e736d6b7")
public class ImpactSubroot {
    @objid ("830abe62-5b07-48b3-9c39-44ea0274a847")
    public static final String STEREOTYPE_NAME = "impact_subroot";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("a65856d7-cc58-4ec2-a0c5-d455832a2825")
    protected final Dependency elt;

    /**
     * Tells whether a {@link ImpactSubroot proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << impact_subroot >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("903ffb5d-d3a0-4525-a2f8-5e0ed923b9ab")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ImpactSubroot.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << impact_subroot >> then instantiate a {@link ImpactSubroot} proxy.
     * 
     * @return a {@link ImpactSubroot} proxy on the created {@link Dependency}.
     */
    @objid ("38af2b6c-8345-473b-8fbd-d96f1dc56018")
    public static ImpactSubroot create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ImpactSubroot.STEREOTYPE_NAME);
        return ImpactSubroot.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link ImpactSubroot} proxy from a {@link Dependency} stereotyped << impact_subroot >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link ImpactSubroot} proxy or <i>null</i>.
     */
    @objid ("c3fbd7d2-71cd-4542-82b2-70aa45d2d524")
    public static ImpactSubroot instantiate(Dependency obj) {
        return ImpactSubroot.canInstantiate(obj) ? new ImpactSubroot(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ImpactSubroot} proxy from a {@link Dependency} stereotyped << impact_subroot >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link ImpactSubroot} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("12477803-132f-4319-8117-aca76f69ce57")
    public static ImpactSubroot safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (ImpactSubroot.canInstantiate(obj))
        	return new ImpactSubroot(obj);
        else
        	throw new IllegalArgumentException("ImpactSubroot: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b28324d8-52c3-4e29-9937-54fa795dc86b")
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
        ImpactSubroot other = (ImpactSubroot) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("aad64393-8069-49a7-b566-503bf3d46de4")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("39ead311-506e-4e01-9696-5d1054d4132c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("82244986-478b-41a1-af56-81f207fc9d44")
    protected ImpactSubroot(Dependency elt) {
        this.elt = elt;
    }

    @objid ("32cf63bf-4543-49bf-a3df-7d91136132db")
    public static final class MdaTypes {
        @objid ("5ca6a3da-630f-45fd-9149-9ec0e21124ab")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b128d427-7efe-4514-b0b6-bf1e231bfb6a")
        private static Stereotype MDAASSOCDEP;

        @objid ("cca442ed-1853-48f4-b11c-01fc7e722f62")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c2206584-100f-48d4-a743-cccb4c411f1a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec2468-0000-0ac6-0000-000000000000");
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
