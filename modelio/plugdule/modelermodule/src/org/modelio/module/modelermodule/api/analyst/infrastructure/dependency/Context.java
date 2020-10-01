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
 * Proxy class to handle a {@link Dependency} with << context >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9adbf168-2b19-4e82-afe0-a6337867d437")
public class Context {
    @objid ("a813fb18-aa1d-489a-ad75-6e1a4d147023")
    public static final String STEREOTYPE_NAME = "context";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("e9a4fe1f-a4ca-4422-8bf5-18e60e757e70")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Context proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << context >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("dd163272-932d-454f-953a-72d1f757f849")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Context.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << context >> then instantiate a {@link Context} proxy.
     * 
     * @return a {@link Context} proxy on the created {@link Dependency}.
     */
    @objid ("39d4f9a3-5f0c-4575-b894-5a1d7b26ab76")
    public static Context create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Context.STEREOTYPE_NAME);
        return Context.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Context} proxy from a {@link Dependency} stereotyped << context >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Context} proxy or <i>null</i>.
     */
    @objid ("19f1b09b-3cd9-472c-b2c0-197b272a4550")
    public static Context instantiate(Dependency obj) {
        return Context.canInstantiate(obj) ? new Context(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Context} proxy from a {@link Dependency} stereotyped << context >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Context} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("23e4124a-8414-415f-9e53-366e4cdd032e")
    public static Context safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Context.canInstantiate(obj))
        	return new Context(obj);
        else
        	throw new IllegalArgumentException("Context: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3ee8c745-17de-4671-9294-cb619efb13a3")
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
        Context other = (Context) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("4bda4df3-18ce-44c1-846a-e0a6938f8572")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("c97fb9ca-1092-44be-8b39-0f3b0ad522d8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("fa38fcf6-5c68-4ccd-b45e-e2b6da96b3eb")
    protected Context(Dependency elt) {
        this.elt = elt;
    }

    @objid ("b5192799-2b87-4173-932c-3e67df3991ab")
    public static final class MdaTypes {
        @objid ("21f827e3-088b-4ed5-94be-82de42db863a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4b0259c4-81e3-4267-b149-d28f8ef2aed3")
        private static Stereotype MDAASSOCDEP;

        @objid ("ff735e13-6160-4bf0-8ef2-8fff7101a7f8")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1d8a9b62-771d-40ea-bcad-1a3bb1fb0fe3")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0242-0000-000000000000");
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
