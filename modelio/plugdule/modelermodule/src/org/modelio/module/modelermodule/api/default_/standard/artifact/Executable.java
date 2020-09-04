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
    @objid ("85792730-abd9-405d-801f-465b8785874c")
    public static final String STEREOTYPE_NAME = "executable";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("2b0e449a-bbea-41c0-bb47-a6acc3669b73")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Executable proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << executable >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("d0bb5876-0a28-4214-a444-dd036cfc19cc")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Executable.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << executable >> then instantiate a {@link Executable} proxy.
     * 
     * @return a {@link Executable} proxy on the created {@link Artifact}.
     */
    @objid ("6ae51709-7902-4437-9038-8979591d2fd0")
    public static Executable create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Executable.STEREOTYPE_NAME);
        return Executable.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Executable} proxy from a {@link Artifact} stereotyped << executable >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Executable} proxy or <i>null</i>.
     */
    @objid ("cac9ba1e-677a-4291-abbd-7d79bf7b751d")
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
    @objid ("751e3d2a-ad98-4953-8112-6cc04e91c940")
    public static Executable safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Executable.canInstantiate(obj))
        	return new Executable(obj);
        else
        	throw new IllegalArgumentException("Executable: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("153caca4-091e-4ff7-80fd-795517121bd6")
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
    @objid ("24b3a672-2d4c-4b34-9fd0-5fdcce1e4d41")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("5f8b2eeb-03f0-4f67-be14-b0e0e862caa1")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b429edb8-6201-43c6-bbc4-65dbd1c8ce34")
    protected Executable(Artifact elt) {
        this.elt = elt;
    }

    @objid ("51e459a7-b6fd-484f-af65-d1a7e5948cea")
    public static final class MdaTypes {
        @objid ("78908865-6d68-4088-8d8c-6efa2f7ccddb")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("53df4461-2c31-49d4-9ed4-8df2e5f196fd")
        private static Stereotype MDAASSOCDEP;

        @objid ("fa480dc8-8754-4ccd-adc6-ad4a74a09891")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("719af54d-1f0f-45f4-bb12-22c85c360eca")
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
