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
    @objid ("5cf1e859-2f64-4151-a998-777c1fa929b2")
    public static final String STEREOTYPE_NAME = "context";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("7073fa7b-f5cf-43cf-a50f-5f1798d437a2")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Context proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << context >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("cfc65428-ede9-4044-b61d-dc0b96b95cb7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Context.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << context >> then instantiate a {@link Context} proxy.
     * 
     * @return a {@link Context} proxy on the created {@link Dependency}.
     */
    @objid ("9dc7eb43-8d57-4f76-9379-35091dcdc8eb")
    public static Context create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
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
    @objid ("5163327a-c9c1-4c63-b1bb-24be5f92d2a4")
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
    @objid ("85d65c16-fb83-4416-81a6-56e8be531c0f")
    public static Context safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Context.canInstantiate(obj))
        	return new Context(obj);
        else
        	throw new IllegalArgumentException("Context: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("43d4ab12-1bc5-4b24-ac52-5bac9ea2e501")
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
    @objid ("6a42592c-674e-4487-9b11-5111c87a941c")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("6d1b48b9-45cf-42aa-9e27-30c9824c61d6")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("9b0abd55-7a58-43ec-848b-40dd903a7561")
    protected  Context(Dependency elt) {
        this.elt = elt;
    }

    @objid ("b5192799-2b87-4173-932c-3e67df3991ab")
    public static final class MdaTypes {
        @objid ("66aad3ed-4182-4416-a4fd-bd4fb3ce6c2d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("08b11df8-3c28-4feb-8886-8355d4db76b7")
        private static Stereotype MDAASSOCDEP;

        @objid ("44326e25-6d61-434d-b19c-4924bcc2f2e2")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("05d2ce4e-615c-4bc2-b363-02273fa84c7d")
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
