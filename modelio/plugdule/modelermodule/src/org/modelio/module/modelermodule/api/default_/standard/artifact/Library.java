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
 * Proxy class to handle a {@link Artifact} with << library >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("6adfb96d-3843-4138-b8fa-f7724e9f9ec8")
public class Library {
    @objid ("79bfb9e1-cc5f-4102-9b09-ec0e726ace40")
    public static final String STEREOTYPE_NAME = "library";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("8eaa5e22-53f9-4425-8cc5-7d9237a9842d")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Library proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << library >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("14a3c6ed-d3c0-4d0a-9d12-fce838aac2c4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Library.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << library >> then instantiate a {@link Library} proxy.
     * 
     * @return a {@link Library} proxy on the created {@link Artifact}.
     */
    @objid ("b354ecb1-3f09-4ad7-adca-6e0447c6f6ac")
    public static Library create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Library.STEREOTYPE_NAME);
        return Library.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Library} proxy from a {@link Artifact} stereotyped << library >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Library} proxy or <i>null</i>.
     */
    @objid ("d10a1018-9e64-4b22-8ef3-715978b04e1f")
    public static Library instantiate(Artifact obj) {
        return Library.canInstantiate(obj) ? new Library(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Library} proxy from a {@link Artifact} stereotyped << library >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link Library} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c94a23a3-9bcb-4d1c-930d-390bea8b8459")
    public static Library safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Library.canInstantiate(obj))
        	return new Library(obj);
        else
        	throw new IllegalArgumentException("Library: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2309e75e-f8d7-4664-979c-93d93a744833")
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
        Library other = (Library) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("bf5a6401-6b66-4bee-bb1c-06b35bb6241c")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("b8aa556a-08e9-47ed-9ae7-515245faa956")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ff00d672-166a-4fcc-94a2-682c4c813e1c")
    protected Library(Artifact elt) {
        this.elt = elt;
    }

    @objid ("7c651d53-3107-491d-9c5d-92dc5fa1b829")
    public static final class MdaTypes {
        @objid ("eebd771c-ea4a-47d4-9490-dee5727c6e82")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("86c9b105-5aae-40e4-b77f-3d207015da29")
        private static Stereotype MDAASSOCDEP;

        @objid ("fcae10ca-bf3d-42f5-8d09-4ad68ed960a4")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("cefc7179-d205-4cb5-a64a-674e2340fd2c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01c5-0000-000000000000");
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
