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
    @objid ("7eefc12b-f228-46e7-ae5e-259aa08ce0ff")
    public static final String STEREOTYPE_NAME = "business_rule_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("a9d03b65-9029-4b32-a4ca-5e9492664940")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link BusinessRulePropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << business_rule_propertyset >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("181d6623-2dbe-461f-b3f0-f4330d2a1018")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, BusinessRulePropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << business_rule_propertyset >> then instantiate a {@link BusinessRulePropertyset} proxy.
     * 
     * @return a {@link BusinessRulePropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("d6434582-8931-4a57-98ac-8af36a4df68e")
    public static BusinessRulePropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.PropertyTableDefinition");
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
    @objid ("dab5db8b-29a6-491e-b160-1f6e5cc80ccb")
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
    @objid ("9cef436d-7f83-4353-855e-f337a326c6b7")
    public static BusinessRulePropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (BusinessRulePropertyset.canInstantiate(obj))
        	return new BusinessRulePropertyset(obj);
        else
        	throw new IllegalArgumentException("BusinessRulePropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5db11930-207e-4dd0-9355-1bf4035a6cdf")
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
    @objid ("a5d478a9-73e6-41c1-bf88-258dd1c964a8")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("c1518069-ed42-4b38-be44-83edc6d36050")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("53461f1d-92f0-48ff-af5d-50db4092d981")
    protected  BusinessRulePropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("dd832859-edff-49b3-945e-65d2c349b0b0")
    public static final class MdaTypes {
        @objid ("7f4fc4e6-a0a1-4de2-9fa8-3b02d7902ffa")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2fc2e122-2a59-4961-b005-4960ff16e0bb")
        private static Stereotype MDAASSOCDEP;

        @objid ("7f295e4e-b9a8-4da8-8eb1-bef8d4e034db")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("56a31627-6748-440b-9143-9ce048265a19")
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
