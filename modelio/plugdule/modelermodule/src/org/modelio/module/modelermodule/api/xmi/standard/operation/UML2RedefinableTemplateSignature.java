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
package org.modelio.module.modelermodule.api.xmi.standard.operation;

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
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Operation} with << UML2RedefinableTemplateSignature >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("63263b0d-3e0b-4d58-b78f-6a6ecff63f13")
public class UML2RedefinableTemplateSignature {
    @objid ("d81bff33-e63e-4d44-876e-1f71aeb945df")
    public static final String STEREOTYPE_NAME = "UML2RedefinableTemplateSignature";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("edacd656-3c28-4c91-8d50-0c113fd886cc")
    protected final Operation elt;

    /**
     * Tells whether a {@link UML2RedefinableTemplateSignature proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation} stereotyped << UML2RedefinableTemplateSignature >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("bc14bf79-b50a-4b26-b138-41fc36c9fb69")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Operation) && ((Operation) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2RedefinableTemplateSignature.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Operation} stereotyped << UML2RedefinableTemplateSignature >> then instantiate a {@link UML2RedefinableTemplateSignature} proxy.
     * 
     * @return a {@link UML2RedefinableTemplateSignature} proxy on the created {@link Operation}.
     */
    @objid ("5140662e-2904-4d73-b06e-c25ce893dc06")
    public static UML2RedefinableTemplateSignature create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Operation");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2RedefinableTemplateSignature.STEREOTYPE_NAME);
        return UML2RedefinableTemplateSignature.instantiate((Operation)e);
    }

    /**
     * Tries to instantiate a {@link UML2RedefinableTemplateSignature} proxy from a {@link Operation} stereotyped << UML2RedefinableTemplateSignature >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Operation
     * @return a {@link UML2RedefinableTemplateSignature} proxy or <i>null</i>.
     */
    @objid ("5662b3db-2c9f-4b27-b2af-3b044a9cdd83")
    public static UML2RedefinableTemplateSignature instantiate(Operation obj) {
        return UML2RedefinableTemplateSignature.canInstantiate(obj) ? new UML2RedefinableTemplateSignature(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2RedefinableTemplateSignature} proxy from a {@link Operation} stereotyped << UML2RedefinableTemplateSignature >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Operation}
     * @return a {@link UML2RedefinableTemplateSignature} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("e65e11ff-5e79-4a61-85af-ba909b4bc7b7")
    public static UML2RedefinableTemplateSignature safeInstantiate(Operation obj) throws IllegalArgumentException {
        if (UML2RedefinableTemplateSignature.canInstantiate(obj))
        	return new UML2RedefinableTemplateSignature(obj);
        else
        	throw new IllegalArgumentException("UML2RedefinableTemplateSignature: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("bbfc0dc9-90d8-4e3e-83c1-4e97c1996a5e")
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
        UML2RedefinableTemplateSignature other = (UML2RedefinableTemplateSignature) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Operation}. 
     * @return the Operation represented by this proxy, never null.
     */
    @objid ("a7a72080-2f74-40e8-848b-a6982a26f00f")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("dd4b38d3-706f-4b5b-bf22-be150bb20bbe")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b29417f0-5ff6-4866-a0c7-9662554a2b5f")
    protected UML2RedefinableTemplateSignature(Operation elt) {
        this.elt = elt;
    }

    @objid ("17d7053e-244f-455d-aa7a-63194a9e59bb")
    public static final class MdaTypes {
        @objid ("cb6d9e62-e653-4f0c-b58a-77821460f0db")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ec3423f0-db0d-4d21-8a45-122e31998f0d")
        private static Stereotype MDAASSOCDEP;

        @objid ("f231eee8-6105-4125-910e-4fa4cd9cccf7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5763b680-f8b9-455a-bce4-6fd34768fbaa")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "90a0e341-5d0d-11df-a996-001302895b2b");
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
