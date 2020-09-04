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
 * Proxy class to handle a {@link PropertyTableDefinition} with << requirement_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("406ee77a-a498-461f-bd3f-0a9f8dab7754")
public class RequirementPropertyset {
    @objid ("9d6be85f-4656-4aa2-92c0-f45dd0cbd6df")
    public static final String STEREOTYPE_NAME = "requirement_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("4187d2b8-0e47-4c82-961b-8db6153d24af")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link RequirementPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << requirement_propertyset >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("11c059c6-ba7c-4c83-9a14-aca09b5c6cf7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RequirementPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << requirement_propertyset >> then instantiate a {@link RequirementPropertyset} proxy.
     * 
     * @return a {@link RequirementPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("06fdba82-7512-42d9-9e24-9c76f4119128")
    public static RequirementPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, RequirementPropertyset.STEREOTYPE_NAME);
        return RequirementPropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link RequirementPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << requirement_propertyset >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link RequirementPropertyset} proxy or <i>null</i>.
     */
    @objid ("d0ceff41-6442-4eaa-ad4a-e15e300d8fba")
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
    @objid ("0c4369f1-6a08-41d5-a80d-2d92d0233318")
    public static RequirementPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (RequirementPropertyset.canInstantiate(obj))
        	return new RequirementPropertyset(obj);
        else
        	throw new IllegalArgumentException("RequirementPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("acbe6330-f756-471c-a94c-3ad69330f6b6")
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
    @objid ("bc574b69-91e8-4445-9f10-ff8923f3c0bd")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("0c5ada09-8890-4d53-bdf0-bdfa18e9f89b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("fc983dfa-6418-4e4c-b73d-e33a1fc2bab5")
    protected RequirementPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("8fb90d31-ee90-41bf-8357-2e601bf691dc")
    public static final class MdaTypes {
        @objid ("380dbfc1-7a6d-498b-9987-59ba27813eee")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("18266a31-9bb3-448a-aaec-3c6835914562")
        private static Stereotype MDAASSOCDEP;

        @objid ("6488ff42-ed5a-4d05-a44f-758c73aa8b56")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8ef932a9-c2ff-4209-b2aa-1bda0e8e9f7c")
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
