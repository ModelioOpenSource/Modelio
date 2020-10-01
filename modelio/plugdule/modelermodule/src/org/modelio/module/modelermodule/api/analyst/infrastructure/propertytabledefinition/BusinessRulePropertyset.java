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
package org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition;

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
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link PropertyTableDefinition} with << business_rule_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c2b1093f-2962-4523-99fe-38f70a53ddad")
public class BusinessRulePropertyset {
    @objid ("cca911f0-2cbd-4157-a15e-8bbfd53bf191")
    public static final String STEREOTYPE_NAME = "business_rule_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("51c2c164-8f4b-4946-bf5e-c689dddcae5c")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link BusinessRulePropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << business_rule_propertyset >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("778f4d72-b760-47e0-9d95-65658dd1fecf")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, BusinessRulePropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << business_rule_propertyset >> then instantiate a {@link BusinessRulePropertyset} proxy.
     * 
     * @return a {@link BusinessRulePropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("93b6c59a-02cc-413e-a113-3591738f27ff")
    public static BusinessRulePropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, BusinessRulePropertyset.STEREOTYPE_NAME);
        return BusinessRulePropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link BusinessRulePropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << business_rule_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link BusinessRulePropertyset} proxy or <i>null</i>.
     */
    @objid ("37e0ac77-3217-4e9e-be1b-f3ec0796c4b9")
    public static BusinessRulePropertyset instantiate(PropertyTableDefinition obj) {
        return BusinessRulePropertyset.canInstantiate(obj) ? new BusinessRulePropertyset(obj) : null;
    }

    /**
     * Tries to instantiate a {@link BusinessRulePropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << business_rule_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link PropertyTableDefinition}
     * @return a {@link BusinessRulePropertyset} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f04e07cd-b4f5-4fea-b8b7-0f20195c6554")
    public static BusinessRulePropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (BusinessRulePropertyset.canInstantiate(obj))
        	return new BusinessRulePropertyset(obj);
        else
        	throw new IllegalArgumentException("BusinessRulePropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("4b52be04-2613-4690-9388-0807cafaf46d")
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
        BusinessRulePropertyset other = (BusinessRulePropertyset) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link PropertyTableDefinition}. 
     * @return the PropertyTableDefinition represented by this proxy, never null.
     */
    @objid ("387e4834-fb96-41da-b195-c4bca3831ae3")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("9fe127a9-5693-46fc-bad7-3134af933b16")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b274b988-d919-4f04-80c0-0e7e9685c930")
    protected BusinessRulePropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("dd832859-edff-49b3-945e-65d2c349b0b0")
    public static final class MdaTypes {
        @objid ("7ffc547f-ed04-4fbf-bf41-2af033d78ee4")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4842dd1f-3e39-4333-abf0-8c78f3ad1697")
        private static Stereotype MDAASSOCDEP;

        @objid ("29977eaf-4748-4845-9774-0e26dc2ba2cc")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("bfecabf7-e262-4b69-99e2-7d526b883ec1")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec141c-0000-1301-0000-000000000000");
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
