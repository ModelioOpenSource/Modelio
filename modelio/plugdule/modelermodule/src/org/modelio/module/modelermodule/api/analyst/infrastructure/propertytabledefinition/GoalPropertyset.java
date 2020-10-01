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
 * Proxy class to handle a {@link PropertyTableDefinition} with << goal_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d01ebb18-da75-4ebb-96d8-94d22c850452")
public class GoalPropertyset {
    @objid ("5cae09b7-4c95-40aa-9490-fc91bb806ba0")
    public static final String STEREOTYPE_NAME = "goal_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("9b844239-c112-413d-9453-25579eb13e2b")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link GoalPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << goal_propertyset >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("9a48ea58-8550-48fb-931c-8971e276a52e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, GoalPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << goal_propertyset >> then instantiate a {@link GoalPropertyset} proxy.
     * 
     * @return a {@link GoalPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("2f646299-fe4e-4b8e-958b-30d429367dd8")
    public static GoalPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
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
    @objid ("d912ea94-43b2-4f85-8d4d-b41c1f78939d")
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
    @objid ("af94d723-12af-477d-8cdc-ddda64a8d2ef")
    public static GoalPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (GoalPropertyset.canInstantiate(obj))
        	return new GoalPropertyset(obj);
        else
        	throw new IllegalArgumentException("GoalPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b9876c01-ea79-4f92-bc25-a157e90d2995")
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
    @objid ("c5ba8cb2-f919-49f7-9bc4-b3db5c7929f7")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("c18f1b73-dc65-4426-84ec-0b783205b21c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b92fe63b-2d50-4611-8ee8-4b80e46b152c")
    protected GoalPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("af94a5ac-43a2-4dea-868e-bb40136af1d6")
    public static final class MdaTypes {
        @objid ("5bd8ad07-28fb-4101-9ae9-0ce4ff948f2c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("85e9a103-32ac-4042-9c8d-e5c7a0230b0d")
        private static Stereotype MDAASSOCDEP;

        @objid ("09ce88ea-1cbb-4384-a9fb-3b3c8832d732")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c1a3cc6e-5af0-4d52-ab40-c5bed03ca66f")
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
