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
    @objid ("a2856950-6169-4c38-95c4-637df4df3c1f")
    public static final String STEREOTYPE_NAME = "UML2RedefinableTemplateSignature";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("e548283a-61b9-4a9f-be49-74de5f5369dc")
    protected final Operation elt;

    /**
     * Tells whether a {@link UML2RedefinableTemplateSignature proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation} stereotyped << UML2RedefinableTemplateSignature >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("16631b59-a465-467a-804a-e1c2e4d47da7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Operation) && ((Operation) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2RedefinableTemplateSignature.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Operation} stereotyped << UML2RedefinableTemplateSignature >> then instantiate a {@link UML2RedefinableTemplateSignature} proxy.
     * 
     * @return a {@link UML2RedefinableTemplateSignature} proxy on the created {@link Operation}.
     */
    @objid ("19c307b6-90ac-4d20-a962-b9a5f8e644cd")
    public static UML2RedefinableTemplateSignature create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Operation");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2RedefinableTemplateSignature.STEREOTYPE_NAME);
        return UML2RedefinableTemplateSignature.instantiate((Operation)e);
    }

    /**
     * Tries to instantiate a {@link UML2RedefinableTemplateSignature} proxy from a {@link Operation} stereotyped << UML2RedefinableTemplateSignature >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Operation
     * @return a {@link UML2RedefinableTemplateSignature} proxy or <i>null</i>.
     */
    @objid ("58d1170d-779a-4e28-af0b-1fdc110e9a95")
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
    @objid ("21869e4f-49f5-4c49-8d34-f53fff9d0499")
    public static UML2RedefinableTemplateSignature safeInstantiate(Operation obj) throws IllegalArgumentException {
        if (UML2RedefinableTemplateSignature.canInstantiate(obj))
        	return new UML2RedefinableTemplateSignature(obj);
        else
        	throw new IllegalArgumentException("UML2RedefinableTemplateSignature: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2918527f-e173-4372-a07e-87a481d23694")
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
    @objid ("a6ac934e-a576-4c9a-8c10-a00790829556")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("b40095f3-58d4-4496-aa20-97a84722f345")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a7b92a6d-b17e-43a6-b26a-c073e9fbb172")
    protected UML2RedefinableTemplateSignature(Operation elt) {
        this.elt = elt;
    }

    @objid ("17d7053e-244f-455d-aa7a-63194a9e59bb")
    public static final class MdaTypes {
        @objid ("d84c9f8f-056f-48b5-bc59-38cfcd66a97c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("29d3ffbc-d91e-4344-8bfc-a9a8e11b4d8b")
        private static Stereotype MDAASSOCDEP;

        @objid ("ef7830d2-8038-4b62-a550-5e6a6171d7aa")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c37ec422-5d9d-4d58-b066-d339c73b0e48")
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
