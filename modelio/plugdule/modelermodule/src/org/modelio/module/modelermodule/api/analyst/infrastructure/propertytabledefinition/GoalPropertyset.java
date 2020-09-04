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
 * Proxy class to handle a {@link PropertyTableDefinition} with << goal_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d01ebb18-da75-4ebb-96d8-94d22c850452")
public class GoalPropertyset {
    @objid ("6ebbaa61-743d-4de0-a094-08676e7da3f3")
    public static final String STEREOTYPE_NAME = "goal_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("47e1277b-65dc-4362-9577-2accbf92efa6")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link GoalPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << goal_propertyset >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e2e839f6-cd2f-472c-bf74-7db4c704c12c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, GoalPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << goal_propertyset >> then instantiate a {@link GoalPropertyset} proxy.
     * 
     * @return a {@link GoalPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("e772d44d-ed4d-41e0-87e7-7baf22498da8")
    public static GoalPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, GoalPropertyset.STEREOTYPE_NAME);
        return GoalPropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link GoalPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << goal_propertyset >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link GoalPropertyset} proxy or <i>null</i>.
     */
    @objid ("698cb2b8-f576-44cd-bcd0-794c214f55cf")
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
    @objid ("a28f6e13-023f-416a-922f-7fa56cd53e10")
    public static GoalPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (GoalPropertyset.canInstantiate(obj))
        	return new GoalPropertyset(obj);
        else
        	throw new IllegalArgumentException("GoalPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("735362ab-74e0-492e-a62f-362c1419a087")
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
    @objid ("5b5c46ac-56f7-48f5-b250-26100938a00e")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("b46137ce-48d1-42fe-85c3-d82f53dafb3f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f08ef9f1-f820-4b7a-bab9-9296bc00c0a4")
    protected GoalPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("af94a5ac-43a2-4dea-868e-bb40136af1d6")
    public static final class MdaTypes {
        @objid ("53a16544-4ad5-4a90-9cc9-6956bf996b7f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("191e97b7-8815-44d5-bfba-f8807e1afb14")
        private static Stereotype MDAASSOCDEP;

        @objid ("17f6a91f-03dc-47ef-b896-35f589e76b41")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e4912ec3-4b66-4e08-ac44-06a15a75e1c0")
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
