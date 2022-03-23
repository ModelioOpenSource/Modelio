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
 * Proxy class to handle a {@link PropertyTableDefinition} with << requirement_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("406ee77a-a498-461f-bd3f-0a9f8dab7754")
public class RequirementPropertyset {
    @objid ("70b5c29d-d3f7-4277-b7c8-43e67c37a4ce")
    public static final String STEREOTYPE_NAME = "requirement_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("3ea29358-117d-44da-a38e-63321418d70f")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link RequirementPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << requirement_propertyset >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e27a6a66-d805-4751-81f3-8125052c354c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RequirementPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << requirement_propertyset >> then instantiate a {@link RequirementPropertyset} proxy.
     * 
     * @return a {@link RequirementPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("f4e760a2-e435-4dd9-8c26-f2537d70ddd9")
    public static RequirementPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, RequirementPropertyset.STEREOTYPE_NAME);
        return RequirementPropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link RequirementPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << requirement_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link RequirementPropertyset} proxy or <i>null</i>.
     */
    @objid ("aed87e32-fe12-467b-a97d-1abeb502c9f5")
    public static RequirementPropertyset instantiate(PropertyTableDefinition obj) {
        return RequirementPropertyset.canInstantiate(obj) ? new RequirementPropertyset(obj) : null;
    }

    /**
     * Tries to instantiate a {@link RequirementPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << requirement_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link PropertyTableDefinition}
     * @return a {@link RequirementPropertyset} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("7d887039-e590-4512-a246-b23193810478")
    public static RequirementPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (RequirementPropertyset.canInstantiate(obj))
        	return new RequirementPropertyset(obj);
        else
        	throw new IllegalArgumentException("RequirementPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("bcab1c41-69fc-470d-b05b-be8fdcd8a1f7")
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
        RequirementPropertyset other = (RequirementPropertyset) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link PropertyTableDefinition}. 
     * @return the PropertyTableDefinition represented by this proxy, never null.
     */
    @objid ("0ffbedec-c304-428c-a21d-664c355ac98a")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("f17cc216-5f86-416d-b4ca-dfd28c41c64d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("b71e781e-b104-4190-a151-ee10ffebb1f6")
    protected  RequirementPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("8fb90d31-ee90-41bf-8357-2e601bf691dc")
    public static final class MdaTypes {
        @objid ("d00107ae-a6be-4448-b488-bcf7e7527216")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("bf1cd680-8f03-44c5-930e-c5b107051ca0")
        private static Stereotype MDAASSOCDEP;

        @objid ("b1b2969e-f342-4882-9216-be603527aeff")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9c30546a-b25f-4602-8141-f09382140cb4")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec141c-0000-12f7-0000-000000000000");
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
