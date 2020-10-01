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
    @objid ("13b79f6c-6c6e-44e6-a76a-b1208d4f3315")
    public static final String STEREOTYPE_NAME = "primary";

    /**
     * The underlying {@link Actor} represented by this proxy, never null.
     */
    @objid ("603a780f-0c46-4241-a10f-2a6f60b81745")
    protected final Actor elt;

    /**
     * Tells whether a {@link Primary proxy} can be instantiated from a {@link MObject} checking it is a {@link Actor} stereotyped << primary >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("f18be5f4-9a0c-4007-9483-04c4f70ff2a9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Actor) && ((Actor) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Primary.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Actor} stereotyped << primary >> then instantiate a {@link Primary} proxy.
     * 
     * @return a {@link Primary} proxy on the created {@link Actor}.
     */
    @objid ("7eb28209-6f1a-4850-8c8e-36306618351e")
    public static Primary create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Actor");
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
    @objid ("630c8e7f-7fd8-4f09-9391-13cc04a3d57a")
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
    @objid ("fea3b668-9462-4fcb-8ac8-620ca253b0e7")
    public static Primary safeInstantiate(Actor obj) throws IllegalArgumentException {
        if (Primary.canInstantiate(obj))
        	return new Primary(obj);
        else
        	throw new IllegalArgumentException("Primary: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8c1abb79-a3f4-427c-8ba1-c0df24f2c070")
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
    @objid ("95aa0f92-fa32-402c-a7c8-792e1a0d45fc")
    public Actor getElement() {
        return this.elt;
    }

    @objid ("f3834f13-ab95-4479-b5fe-9c29d6510584")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ae9ae009-bdfc-410f-9e7e-812dad423b61")
    protected Primary(Actor elt) {
        this.elt = elt;
    }

    @objid ("1ae3dee4-02d9-4030-b240-c2d466e4b84b")
    public static final class MdaTypes {
        @objid ("0dc7df0c-92f3-4243-ab65-6ac31137862b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9c38ba87-a146-492a-a17c-69c09efc2cab")
        private static Stereotype MDAASSOCDEP;

        @objid ("e231eec0-f308-44a3-ac81-0780a811a988")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5f41af42-d504-467d-8292-89fb10b45ebe")
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
