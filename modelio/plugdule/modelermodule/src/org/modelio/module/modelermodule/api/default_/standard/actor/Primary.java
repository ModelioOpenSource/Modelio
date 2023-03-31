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
 * Proxy class to handle a {@link Actor} with << primary >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("29339174-3c68-4de4-ad0a-02e0b97a6a0c")
public class Primary {
    @objid ("fac67729-563e-4f1b-aded-22fdbc8f09d0")
    public static final String STEREOTYPE_NAME = "primary";

    /**
     * The underlying {@link Actor} represented by this proxy, never null.
     */
    @objid ("bb15dd0e-93f4-4e71-aee8-4eaa210ce2f1")
    protected final Actor elt;

    /**
     * Tells whether a {@link Primary proxy} can be instantiated from a {@link MObject} checking it is a {@link Actor} stereotyped << primary >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("5b0ce14e-6646-4430-a2d6-f8af8cc84d63")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Actor) && ((Actor) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Primary.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Actor} stereotyped << primary >> then instantiate a {@link Primary} proxy.
     * 
     * @return a {@link Primary} proxy on the created {@link Actor}.
     */
    @objid ("b4ccdcb9-ab99-4e30-81d5-571a9824aef6")
    public static Primary create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Actor");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Primary.STEREOTYPE_NAME);
        return Primary.instantiate((Actor)e);
    }

    /**
     * Tries to instantiate a {@link Primary} proxy from a {@link Actor} stereotyped << primary >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Actor
     * @return a {@link Primary} proxy or <i>null</i>.
     */
    @objid ("e43eb16c-402e-4dbb-9267-e3ff171dcd54")
    public static Primary instantiate(Actor obj) {
        return Primary.canInstantiate(obj) ? new Primary(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Primary} proxy from a {@link Actor} stereotyped << primary >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Actor}
     * @return a {@link Primary} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("5ad71bbf-4fa3-4487-ae06-456d6ad4ccd8")
    public static Primary safeInstantiate(Actor obj) throws IllegalArgumentException {
        if (Primary.canInstantiate(obj))
        	return new Primary(obj);
        else
        	throw new IllegalArgumentException("Primary: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("656df4a3-8170-42cb-b079-ae4462d9425c")
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
        Primary other = (Primary) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Actor}. 
     * @return the Actor represented by this proxy, never null.
     */
    @objid ("655de11f-2169-4256-987b-c867d6149b81")
    public Actor getElement() {
        return this.elt;
    }

    @objid ("008580ef-8143-445c-9167-8495c48a1dc8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("d1c6c823-04d6-4d5d-8948-80e99e87e7b9")
    protected  Primary(Actor elt) {
        this.elt = elt;
    }

    @objid ("1ae3dee4-02d9-4030-b240-c2d466e4b84b")
    public static final class MdaTypes {
        @objid ("72c2c581-89a0-4969-964f-698cceb54d2d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("60fd289b-7d20-422b-a1f3-4476c16c9541")
        private static Stereotype MDAASSOCDEP;

        @objid ("8d106d9e-419b-4beb-b7a2-33b645022b35")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("90ab4ed7-9a56-450c-994d-2c45b8a037e8")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec1ac4-0000-2ef9-0000-000000000000");
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
