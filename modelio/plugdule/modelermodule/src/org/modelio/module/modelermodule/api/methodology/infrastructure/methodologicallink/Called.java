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
package org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
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
 * Proxy class to handle a {@link MethodologicalLink} with << Called >> stereotype.
 * <p>Stereotype description:
 * <br/><i>Business process ou application Process doivent faire apparaitre un activity call.</i></p>
 */
@objid ("f89804bf-ce37-4ee3-9391-4817cb8a0de9")
public class Called {
    @objid ("49c8ea6c-39fb-4a01-9adf-1572aa93d157")
    public static final String STEREOTYPE_NAME = "Called";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("81464a87-7c6b-4b51-ba22-aecffb3aeac1")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Called proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Called >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("68ecc1be-8886-4928-963e-419f2f5df11a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Called.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Called >> then instantiate a {@link Called} proxy.
     * 
     * @return a {@link Called} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("2f46865a-a116-4756-9f96-8ac5d2e17fca")
    public static Called create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Called.STEREOTYPE_NAME);
        return Called.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link Called} proxy from a {@link MethodologicalLink} stereotyped << Called >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link Called} proxy or <i>null</i>.
     */
    @objid ("54ce6a2f-70b6-4a71-a17b-9538f9281300")
    public static Called instantiate(MethodologicalLink obj) {
        return Called.canInstantiate(obj) ? new Called(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Called} proxy from a {@link MethodologicalLink} stereotyped << Called >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link Called} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("6b6dc657-8911-4c57-beec-0e93e17ed36e")
    public static Called safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (Called.canInstantiate(obj))
        	return new Called(obj);
        else
        	throw new IllegalArgumentException("Called: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("bff86b6d-45f8-4f89-9bbe-ccce4ed5c363")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    @objid ("7de0a3b2-2dae-493d-b2e1-b4d05f76b608")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("533aca26-cef6-4c86-8b0c-d734789a9b04")
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
        Called other = (Called) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("0d5ff809-d0a5-49c8-9de2-4042fb924fc8")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("3b066f48-7bd4-443d-bbbc-f6f8cc0ff196")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("86d1ed00-2d58-4617-96fc-d4ec6eff2168")
    protected Called(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("3a055766-68e6-4266-a9ac-43448b367a1b")
    public static final class MdaTypes {
        @objid ("6b3fc893-e260-4729-9dae-6a2185acdbd5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6b04fe0e-cefa-4e44-b0f8-90f95af598ed")
        private static Stereotype MDAASSOCDEP;

        @objid ("28c8bf8e-eb06-4bc9-965e-4ce4d129103b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("76a13b03-6f81-456f-8fe5-3fc893e8b8f7")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "c3862c6c-5983-4d1a-b0e2-58dd2685eda0");
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
