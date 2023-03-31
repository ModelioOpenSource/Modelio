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
 * Proxy class to handle a {@link PropertyTableDefinition} with << goal_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d01ebb18-da75-4ebb-96d8-94d22c850452")
public class GoalPropertyset {
    @objid ("5eeaa652-e0cf-4dfc-99bb-736359424f44")
    public static final String STEREOTYPE_NAME = "goal_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("102c45c6-b05a-4abc-a73d-cd1ba1c79083")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link GoalPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << goal_propertyset >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("8886b1f1-29e7-44c1-a111-46967c3c04f8")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, GoalPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << goal_propertyset >> then instantiate a {@link GoalPropertyset} proxy.
     * 
     * @return a {@link GoalPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("f22028e0-e98a-49d6-aa91-c9005462e77f")
    public static GoalPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, GoalPropertyset.STEREOTYPE_NAME);
        return GoalPropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link GoalPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << goal_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link GoalPropertyset} proxy or <i>null</i>.
     */
    @objid ("2774bb37-1c5d-4e47-b3cb-ee0bb762c07c")
    public static GoalPropertyset instantiate(PropertyTableDefinition obj) {
        return GoalPropertyset.canInstantiate(obj) ? new GoalPropertyset(obj) : null;
    }

    /**
     * Tries to instantiate a {@link GoalPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << goal_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link PropertyTableDefinition}
     * @return a {@link GoalPropertyset} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("73e0c3f9-3054-4741-85fc-79f5d7d10307")
    public static GoalPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (GoalPropertyset.canInstantiate(obj))
        	return new GoalPropertyset(obj);
        else
        	throw new IllegalArgumentException("GoalPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e8e9fa69-88f5-46eb-82db-ebf947d8a89a")
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
        GoalPropertyset other = (GoalPropertyset) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link PropertyTableDefinition}. 
     * @return the PropertyTableDefinition represented by this proxy, never null.
     */
    @objid ("44280dd3-3e6b-4f77-a081-28af3b0c12e4")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("ac7515f1-65f1-4de5-8ef8-a9b8cab61396")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("6591371b-70ec-429d-9a75-0fe94dd770d9")
    protected  GoalPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("af94a5ac-43a2-4dea-868e-bb40136af1d6")
    public static final class MdaTypes {
        @objid ("ee6eee25-4110-42f8-b74a-692cc2cfde40")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6c050394-f547-4e92-b5a5-0bf7e02e338f")
        private static Stereotype MDAASSOCDEP;

        @objid ("7b56bda7-e0d9-4bd3-a3c2-4a80383f1e2f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("02d3bfa4-59ea-44a9-ac39-40aca7a64ed1")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec141c-0000-12f2-0000-000000000000");
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
