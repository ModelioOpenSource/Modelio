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
 * Proxy class to handle a {@link Operation} with << UML2TemplateSignature >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c64395f8-85c2-42fe-9a80-f23505af725e")
public class UML2TemplateSignature {
    @objid ("5d60bcd1-85e8-47e2-8830-1809036774ee")
    public static final String STEREOTYPE_NAME = "UML2TemplateSignature";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("ab0beb78-f297-473f-9d20-358757762477")
    protected final Operation elt;

    /**
     * Tells whether a {@link UML2TemplateSignature proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation} stereotyped << UML2TemplateSignature >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("905a8b38-9969-4d6f-af01-5e44c30fe9ef")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Operation) && ((Operation) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2TemplateSignature.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Operation} stereotyped << UML2TemplateSignature >> then instantiate a {@link UML2TemplateSignature} proxy.
     * 
     * @return a {@link UML2TemplateSignature} proxy on the created {@link Operation}.
     */
    @objid ("1d6fe458-2a93-443e-a81f-425e27080252")
    public static UML2TemplateSignature create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Operation");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2TemplateSignature.STEREOTYPE_NAME);
        return UML2TemplateSignature.instantiate((Operation)e);
    }

    /**
     * Tries to instantiate a {@link UML2TemplateSignature} proxy from a {@link Operation} stereotyped << UML2TemplateSignature >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Operation
     * @return a {@link UML2TemplateSignature} proxy or <i>null</i>.
     */
    @objid ("70e37375-beb6-45ae-a872-9c482ade2415")
    public static UML2TemplateSignature instantiate(Operation obj) {
        return UML2TemplateSignature.canInstantiate(obj) ? new UML2TemplateSignature(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2TemplateSignature} proxy from a {@link Operation} stereotyped << UML2TemplateSignature >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Operation}
     * @return a {@link UML2TemplateSignature} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("10438812-5bbc-430c-afad-bb961fbf163d")
    public static UML2TemplateSignature safeInstantiate(Operation obj) throws IllegalArgumentException {
        if (UML2TemplateSignature.canInstantiate(obj))
        	return new UML2TemplateSignature(obj);
        else
        	throw new IllegalArgumentException("UML2TemplateSignature: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("94dab24b-8d68-4718-a240-b2d4c3314fcc")
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
        UML2TemplateSignature other = (UML2TemplateSignature) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Operation}. 
     * @return the Operation represented by this proxy, never null.
     */
    @objid ("eca4ae65-a3a5-4df2-835e-e9ba6cecfbf1")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("ab7acd64-9c25-4704-84a0-4044ed0f6d6d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("9af3c7e8-1fa8-4b2b-9645-1a7ec011446b")
    protected UML2TemplateSignature(Operation elt) {
        this.elt = elt;
    }

    @objid ("bd5513f3-a00c-4eb5-a954-b968c48149ed")
    public static final class MdaTypes {
        @objid ("830c9c38-65dc-46e6-a2cc-5740edd7b577")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("1840a8bc-09a2-4c85-991e-5fdd233ef671")
        private static Stereotype MDAASSOCDEP;

        @objid ("e595aa4c-bd3a-40f0-8da6-494f2891da95")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("42ad137a-ad0c-4754-b8ec-5d15ec07378d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "554cb8bb-5d0e-11df-a996-001302895b2b");
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
