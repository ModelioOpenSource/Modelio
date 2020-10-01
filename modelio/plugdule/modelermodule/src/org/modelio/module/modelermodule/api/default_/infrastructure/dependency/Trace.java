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
 * Proxy class to handle a {@link Dependency} with << trace >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("deb1d430-370d-47a6-83f9-89eae36b4475")
public class Trace {
    @objid ("e106f20f-b424-43bc-a28c-8c5fb5bba61d")
    public static final String STEREOTYPE_NAME = "trace";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("d85123f7-990f-4e30-89c4-e2dfbcf07f60")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Trace proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << trace >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("6420abcb-f8ef-4c85-89b6-425c47e6c851")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Trace.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << trace >> then instantiate a {@link Trace} proxy.
     * 
     * @return a {@link Trace} proxy on the created {@link Dependency}.
     */
    @objid ("11d4c924-f3ed-471c-a7f1-4f3353084481")
    public static Trace create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Trace.STEREOTYPE_NAME);
        return Trace.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Trace} proxy from a {@link Dependency} stereotyped << trace >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Trace} proxy or <i>null</i>.
     */
    @objid ("a9b83a71-8139-4759-9e12-4e511773cbe3")
    public static Trace instantiate(Dependency obj) {
        return Trace.canInstantiate(obj) ? new Trace(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Trace} proxy from a {@link Dependency} stereotyped << trace >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Trace} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a2663d87-1e58-4d6e-808e-23d6849eca58")
    public static Trace safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Trace.canInstantiate(obj))
        	return new Trace(obj);
        else
        	throw new IllegalArgumentException("Trace: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a9827933-91e3-42ba-aed9-50e763b5189c")
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
        Trace other = (Trace) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("76615f72-6620-44a8-a1f1-f8c57e5f4989")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("41518c5f-d68a-4c0d-a8ea-34c002f88104")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("5c73f0c8-ceb7-425e-9a4f-4e31c6a583a4")
    protected Trace(Dependency elt) {
        this.elt = elt;
    }

    @objid ("1335f40a-5185-493c-ac4b-6745cea1855f")
    public static final class MdaTypes {
        @objid ("1a7d5adc-8339-4050-a032-8e885c99aa3e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a75c4cdb-4583-498d-8d55-d9d9dbec1770")
        private static Stereotype MDAASSOCDEP;

        @objid ("09aa1193-454a-410f-a753-09e0a703b87a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0c212ccf-386d-489f-9f5b-bbf90d09f99f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01280500-0000-0b37-0000-000000000000");
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
