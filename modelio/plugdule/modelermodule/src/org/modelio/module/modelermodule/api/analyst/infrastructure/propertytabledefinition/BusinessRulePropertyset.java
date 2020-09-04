/* 
 * Copyright 2013-2019 Modeliosoft
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
    @objid ("f706c3de-263f-4b5b-9e18-befacce1a303")
    public static final String STEREOTYPE_NAME = "business_rule_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("1f8f2882-794f-4e4b-9f9d-d115ffb359c7")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link BusinessRulePropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << business_rule_propertyset >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("f3b0c623-d835-4a8b-b709-4453b2fd7422")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, BusinessRulePropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << business_rule_propertyset >> then instantiate a {@link BusinessRulePropertyset} proxy.
     * 
     * @return a {@link BusinessRulePropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("3bbe95bb-860c-4c16-ad22-2df7cdbc4257")
    public static BusinessRulePropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, BusinessRulePropertyset.STEREOTYPE_NAME);
        return BusinessRulePropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link BusinessRulePropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << business_rule_propertyset >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link BusinessRulePropertyset} proxy or <i>null</i>.
     */
    @objid ("a92da276-1bd6-4085-be46-cc6a06585843")
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
    @objid ("9f1573a4-eafb-4d35-a45c-fa89f8d44391")
    public static BusinessRulePropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (BusinessRulePropertyset.canInstantiate(obj))
        	return new BusinessRulePropertyset(obj);
        else
        	throw new IllegalArgumentException("BusinessRulePropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8ed2103c-f848-4c0e-9dff-d7e7df9b3f5e")
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
    @objid ("05b5a068-6256-41f2-91dc-b2842ee0cbb4")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("bacdfffc-f011-4eb6-952d-58f4227e6696")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("6dc12274-9cb8-4570-b56d-5c26a05da2b9")
    protected BusinessRulePropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("dd832859-edff-49b3-945e-65d2c349b0b0")
    public static final class MdaTypes {
        @objid ("8693ecf2-1919-4bcc-aefc-f60587e17f3c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f4237a15-4bb9-4d67-ba27-e6dfcc40a2e4")
        private static Stereotype MDAASSOCDEP;

        @objid ("0a67bd3a-dfa8-4a2f-ad10-590ae41e35fc")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("91fc1fd7-d39c-4700-914f-84ca9448a0f4")
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
