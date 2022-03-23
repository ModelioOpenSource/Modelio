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
package org.modelio.module.modelermodule.api.default_.standard.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
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
 * Proxy class to handle a {@link Actor} with << system >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9fad6019-0f3e-455a-a1b5-1a961fdfdf59")
public class System {
    @objid ("b272f091-5084-48e3-bd67-0057d5c06f7c")
    public static final String STEREOTYPE_NAME = "system";

    /**
     * The underlying {@link Actor} represented by this proxy, never null.
     */
    @objid ("3be3422a-b728-4c96-af26-5e517827da91")
    protected final Actor elt;

    /**
     * Tells whether a {@link System proxy} can be instantiated from a {@link MObject} checking it is a {@link Actor} stereotyped << system >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b1c19adf-3e32-40c5-9256-1e2ef5d2075e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Actor) && ((Actor) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, System.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Actor} stereotyped << system >> then instantiate a {@link System} proxy.
     * 
     * @return a {@link System} proxy on the created {@link Actor}.
     */
    @objid ("b8be2fcd-c2ad-458e-a00c-0b9a6af62f47")
    public static System create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Actor");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, System.STEREOTYPE_NAME);
        return System.instantiate((Actor)e);
    }

    /**
     * Tries to instantiate a {@link System} proxy from a {@link Actor} stereotyped << system >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Actor
     * @return a {@link System} proxy or <i>null</i>.
     */
    @objid ("bb6e5ab9-c279-4f2e-8b5e-8fba10633978")
    public static System instantiate(Actor obj) {
        return System.canInstantiate(obj) ? new System(obj) : null;
    }

    /**
     * Tries to instantiate a {@link System} proxy from a {@link Actor} stereotyped << system >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Actor}
     * @return a {@link System} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a8ec0dfb-5ab4-4162-a929-7f99cecd003f")
    public static System safeInstantiate(Actor obj) throws IllegalArgumentException {
        if (System.canInstantiate(obj))
        	return new System(obj);
        else
        	throw new IllegalArgumentException("System: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2b9caa7b-de10-4bba-b6ed-2e9671af77fc")
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
        System other = (System) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Actor}. 
     * @return the Actor represented by this proxy, never null.
     */
    @objid ("30aedb24-a563-466c-91e7-70e8d479f220")
    public Actor getElement() {
        return this.elt;
    }

    @objid ("bfe017f5-1c91-47d6-be16-0a8c635d28b8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("18231827-1254-45ef-bf77-bb8e43b0c675")
    protected  System(Actor elt) {
        this.elt = elt;
    }

    @objid ("2c237c05-ed2c-460e-a2b5-b7d3a6a403e1")
    public static final class MdaTypes {
        @objid ("881b65b3-263a-4952-ace4-2bc3cdf65632")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2c42c2c5-bb9c-4b80-aca5-25a9310453f6")
        private static Stereotype MDAASSOCDEP;

        @objid ("c74043ba-97ec-4bfe-a6ba-b024da83edd4")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7f92a3b7-5f60-4c04-bcaf-1e6b5cc04c4f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec1ac4-0000-2f09-0000-000000000000");
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
