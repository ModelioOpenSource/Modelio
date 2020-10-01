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
package org.modelio.module.modelermodule.api.default_.standard.artifact;

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
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Artifact} with << executable >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d4573417-3d08-45a2-b10e-0d1b9d7f1d7d")
public class Executable {
    @objid ("d9419d81-cf8c-4c17-9622-0f0dae2a5da5")
    public static final String STEREOTYPE_NAME = "executable";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("0f8f067f-c77b-46a1-8d55-f48a082c97aa")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Executable proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << executable >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0cc6fa66-89c5-4310-8623-590a80b38776")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Executable.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << executable >> then instantiate a {@link Executable} proxy.
     * 
     * @return a {@link Executable} proxy on the created {@link Artifact}.
     */
    @objid ("f0311c18-e7b1-49c6-8f8b-dcec6a8f0ab7")
    public static Executable create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Executable.STEREOTYPE_NAME);
        return Executable.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Executable} proxy from a {@link Artifact} stereotyped << executable >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Executable} proxy or <i>null</i>.
     */
    @objid ("08ded866-765a-489f-bd7a-788fbd8cd15e")
    public static Executable instantiate(Artifact obj) {
        return Executable.canInstantiate(obj) ? new Executable(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Executable} proxy from a {@link Artifact} stereotyped << executable >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link Executable} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("2cc0ee5e-1963-4392-b80f-8b2e3f6b701e")
    public static Executable safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Executable.canInstantiate(obj))
        	return new Executable(obj);
        else
        	throw new IllegalArgumentException("Executable: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("204919b6-9147-4d4c-8933-ebb2a48837fc")
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
        Executable other = (Executable) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("f6ce3948-c292-4c49-94c8-e543a4962ffc")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("bd28a511-3586-46d8-9655-765828ab3944")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a82b4cf2-8a6e-44d5-905f-4775c17629b3")
    protected Executable(Artifact elt) {
        this.elt = elt;
    }

    @objid ("51e459a7-b6fd-484f-af65-d1a7e5948cea")
    public static final class MdaTypes {
        @objid ("d5934c95-5c6e-4105-8879-d6eb7440b1e5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c2bdf9b6-64fe-48ea-b7b5-8b1ee6b79cf8")
        private static Stereotype MDAASSOCDEP;

        @objid ("9c90bb6c-9b5e-43b5-b32d-129b41b90fa3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e2b8844c-0e42-433b-96e1-0cdd29d1f0c4")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01c3-0000-000000000000");
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
