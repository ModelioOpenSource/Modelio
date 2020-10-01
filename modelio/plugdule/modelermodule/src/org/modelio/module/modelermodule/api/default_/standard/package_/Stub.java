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
package org.modelio.module.modelermodule.api.default_.standard.package_;

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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Package} with << stub >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1dc07035-e0be-493c-8a41-81d1f3e2bbfe")
public class Stub {
    @objid ("24a6cec8-6768-4cb5-9a7b-1fa157a84b08")
    public static final String STEREOTYPE_NAME = "stub";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("c9e947ef-6872-4568-a1b9-25eb0463de2a")
    protected final Package elt;

    /**
     * Tells whether a {@link Stub proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << stub >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("261ef0fc-f7aa-4d4f-a574-f60863ef9c5c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Stub.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << stub >> then instantiate a {@link Stub} proxy.
     * 
     * @return a {@link Stub} proxy on the created {@link Package}.
     */
    @objid ("de729b7b-9029-4f42-99e8-6099acfcbe72")
    public static Stub create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Stub.STEREOTYPE_NAME);
        return Stub.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link Stub} proxy from a {@link Package} stereotyped << stub >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link Stub} proxy or <i>null</i>.
     */
    @objid ("fcc4e35d-d6d5-4da3-996b-94cb415ba7c8")
    public static Stub instantiate(Package obj) {
        return Stub.canInstantiate(obj) ? new Stub(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Stub} proxy from a {@link Package} stereotyped << stub >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link Stub} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("3af999b9-4034-4cc1-8318-ebf10a796c19")
    public static Stub safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Stub.canInstantiate(obj))
        	return new Stub(obj);
        else
        	throw new IllegalArgumentException("Stub: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("013148f5-bb14-4567-89dc-22b10de22fa8")
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
        Stub other = (Stub) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("ebb731ad-9bc3-444c-87b0-9451560b463f")
    public Package getElement() {
        return this.elt;
    }

    @objid ("6812ebca-e06a-4aa3-9710-3ff4b8adac23")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("fb71769c-8b4b-4c98-b273-cb7a3f3aeb86")
    protected Stub(Package elt) {
        this.elt = elt;
    }

    @objid ("cbe40972-a0dc-4f70-a0d4-7a45c10fbcc0")
    public static final class MdaTypes {
        @objid ("bb453002-9b34-401b-b51c-87552130949e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ef63bedb-a622-4ff2-b002-275ee14e7867")
        private static Stereotype MDAASSOCDEP;

        @objid ("bc671f2e-329b-4d7f-af21-6f08623be03b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e41b4131-8e40-4a5f-bf8a-357a407b6e76")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01d7-0000-000000000000");
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
