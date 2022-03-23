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
    @objid ("73ec81f6-ba1e-443e-835c-897876efe9b9")
    public static final String STEREOTYPE_NAME = "UML2RedefinableTemplateSignature";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("bf1bf544-6008-4005-b2e4-0389b3e490ce")
    protected final Operation elt;

    /**
     * Tells whether a {@link UML2RedefinableTemplateSignature proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation} stereotyped << UML2RedefinableTemplateSignature >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("32ab21c3-aa72-4845-8a05-11bbc4b6345d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Operation) && ((Operation) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2RedefinableTemplateSignature.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Operation} stereotyped << UML2RedefinableTemplateSignature >> then instantiate a {@link UML2RedefinableTemplateSignature} proxy.
     * 
     * @return a {@link UML2RedefinableTemplateSignature} proxy on the created {@link Operation}.
     */
    @objid ("91b28ab4-0a5e-409c-872d-66534460a444")
    public static UML2RedefinableTemplateSignature create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Operation");
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
    @objid ("434722e8-2a1c-4fcc-9905-282541b7bfb2")
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
    @objid ("dfdd0a6a-9b60-44b1-94cd-9be4120e2538")
    public static UML2RedefinableTemplateSignature safeInstantiate(Operation obj) throws IllegalArgumentException {
        if (UML2RedefinableTemplateSignature.canInstantiate(obj))
        	return new UML2RedefinableTemplateSignature(obj);
        else
        	throw new IllegalArgumentException("UML2RedefinableTemplateSignature: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("7b38e86a-3217-4af7-9215-7a524e7f093a")
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
    @objid ("cf4d10a1-ee2e-4fbb-b57c-8fa32693263b")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("b0649382-7ab6-4f5b-bc8c-76756182addf")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("3fcd747e-e0f7-41c1-9bab-d0d6e30a9d6d")
    protected  UML2RedefinableTemplateSignature(Operation elt) {
        this.elt = elt;
    }

    @objid ("17d7053e-244f-455d-aa7a-63194a9e59bb")
    public static final class MdaTypes {
        @objid ("0654339d-ebd8-410c-9a19-568203ce010c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("dec85ce2-dc53-4061-adfd-0e141220cec0")
        private static Stereotype MDAASSOCDEP;

        @objid ("3ac9c459-d2b3-4261-89c1-03c1dc6738a6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f2509b69-f9b1-44fc-a970-a4a3700ab2bb")
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
