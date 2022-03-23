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
    @objid ("034380d2-bc21-407e-84b8-b27666585cac")
    public static final String STEREOTYPE_NAME = "implementation";

    /**
     * The underlying {@link Generalization} represented by this proxy, never null.
     */
    @objid ("748fbb9c-fc2e-48a3-87dd-5498d82e4e9d")
    protected final Generalization elt;

    /**
     * Tells whether a {@link Implementation proxy} can be instantiated from a {@link MObject} checking it is a {@link Generalization} stereotyped << implementation >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("335c73c0-5596-4a01-adc3-5dad3659fcd2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Generalization) && ((Generalization) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Implementation.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Generalization} stereotyped << implementation >> then instantiate a {@link Implementation} proxy.
     * 
     * @return a {@link Implementation} proxy on the created {@link Generalization}.
     */
    @objid ("6d2fa81f-3fcb-4fd5-a7f7-fd1e7f1fad24")
    public static Implementation create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Generalization");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Implementation.STEREOTYPE_NAME);
        return Implementation.instantiate((Generalization)e);
    }

    /**
     * Tries to instantiate a {@link Implementation} proxy from a {@link Generalization} stereotyped << implementation >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Generalization
     * @return a {@link Implementation} proxy or <i>null</i>.
     */
    @objid ("181e0a7f-9d72-4c48-8983-1e71164096fd")
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
    @objid ("07a00915-3fde-4fe8-b155-de225c5c3d45")
    public static Implementation safeInstantiate(Generalization obj) throws IllegalArgumentException {
        if (Implementation.canInstantiate(obj))
        	return new Implementation(obj);
        else
        	throw new IllegalArgumentException("Implementation: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1b17c0e7-97e9-4b6d-a991-a53c4558974c")
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
    @objid ("75f00a0f-32c3-4542-81f7-1a504fe4e8fe")
    public Generalization getElement() {
        return this.elt;
    }

    @objid ("c42b00df-dec0-4ba2-b0eb-e4e6c6801c06")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("c917d7fc-90da-4e9e-ad9f-fed033129b07")
    protected  Implementation(Generalization elt) {
        this.elt = elt;
    }

    @objid ("38988b48-d6c0-416a-a9ab-c1d8cde9859c")
    public static final class MdaTypes {
        @objid ("63070b2c-4bd4-45b0-a706-3e07678b407a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c265c9be-ac1a-4a49-8c38-7b5abab70dcb")
        private static Stereotype MDAASSOCDEP;

        @objid ("04e13e41-c95b-4a31-a57f-2e955ff488fe")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7f66934e-955a-41ed-816a-68695aa7ca03")
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
