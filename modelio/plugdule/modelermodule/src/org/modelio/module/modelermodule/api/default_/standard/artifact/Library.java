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
 * Proxy class to handle a {@link Artifact} with << library >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("6adfb96d-3843-4138-b8fa-f7724e9f9ec8")
public class Library {
    @objid ("d9be0267-2446-4312-9bb0-849a7b3047b3")
    public static final String STEREOTYPE_NAME = "library";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("26f11c6e-a76a-4bda-a259-4a7e7e9f8378")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Library proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << library >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e18356ad-5419-4544-8627-a60e2038486b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Library.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << library >> then instantiate a {@link Library} proxy.
     * 
     * @return a {@link Library} proxy on the created {@link Artifact}.
     */
    @objid ("227d0641-97b9-4163-81b5-12827cb3ed2e")
    public static Library create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Library.STEREOTYPE_NAME);
        return Library.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Library} proxy from a {@link Artifact} stereotyped << library >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Library} proxy or <i>null</i>.
     */
    @objid ("6161eee0-ddde-4b88-8217-726fdaca91e0")
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
    @objid ("4d3e4c44-9f15-4083-945d-45fe8533651c")
    public static Library safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Library.canInstantiate(obj))
        	return new Library(obj);
        else
        	throw new IllegalArgumentException("Library: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("cc0e0c47-6765-4388-895a-cc9bb15233d1")
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
    @objid ("63bb15e3-05ef-40d8-8505-7992399fe5f7")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("b4e50273-4aa8-4225-a65e-a6d4443b8f17")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("fd106c17-78f8-43d1-95f6-4e148bbf4671")
    protected Library(Artifact elt) {
        this.elt = elt;
    }

    @objid ("7c651d53-3107-491d-9c5d-92dc5fa1b829")
    public static final class MdaTypes {
        @objid ("cb192ba3-d2b1-411d-a6a7-7c9d38abe61e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("807217f9-0db8-4609-acfb-45ba1b6b094e")
        private static Stereotype MDAASSOCDEP;

        @objid ("3559cbe7-f3ad-4b84-bedc-d832c32218b4")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5355b17b-fff4-4dc9-b1c3-5094af10e858")
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
