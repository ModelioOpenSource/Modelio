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
    @objid ("51e809dc-73e0-4a06-96d8-44e675163b4d")
    public static final String STEREOTYPE_NAME = "system";

    /**
     * The underlying {@link Actor} represented by this proxy, never null.
     */
    @objid ("f30b266a-ef2a-4fec-8deb-08dd93d7fd5f")
    protected final Actor elt;

    /**
     * Tells whether a {@link System proxy} can be instantiated from a {@link MObject} checking it is a {@link Actor} stereotyped << system >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("f7ec4eee-cc36-4dbd-9f4d-9746732c0db1")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Actor) && ((Actor) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, System.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Actor} stereotyped << system >> then instantiate a {@link System} proxy.
     * 
     * @return a {@link System} proxy on the created {@link Actor}.
     */
    @objid ("6214c598-297d-48cf-a7ce-770994be8de9")
    public static System create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Actor");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, System.STEREOTYPE_NAME);
        return System.instantiate((Actor)e);
    }

    /**
     * Tries to instantiate a {@link System} proxy from a {@link Actor} stereotyped << system >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Actor
     * @return a {@link System} proxy or <i>null</i>.
     */
    @objid ("c3293e59-d5b7-47da-a211-3bc762dd4491")
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
    @objid ("b76eafdd-343c-4844-a2fd-a9efc55a5f48")
    public static System safeInstantiate(Actor obj) throws IllegalArgumentException {
        if (System.canInstantiate(obj))
        	return new System(obj);
        else
        	throw new IllegalArgumentException("System: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("68cd3a12-4654-4a9a-a511-b381cf505ada")
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
    @objid ("10929cbe-3e85-4eab-927b-333fc9c22905")
    public Actor getElement() {
        return this.elt;
    }

    @objid ("d6ce1e77-5a29-4401-b9f0-e22a83a1854e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0754aad6-1209-4e33-aeb2-96fbb011a474")
    protected System(Actor elt) {
        this.elt = elt;
    }

    @objid ("2c237c05-ed2c-460e-a2b5-b7d3a6a403e1")
    public static final class MdaTypes {
        @objid ("daeb21d6-57b8-4fb7-8d2c-f9849384aac4")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6f366cee-2cb3-47e2-ae5e-a4030fb7ede7")
        private static Stereotype MDAASSOCDEP;

        @objid ("8d34e82c-95b3-4d16-9939-30b035268324")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("474280dd-000a-410f-96e6-407a299012a6")
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
