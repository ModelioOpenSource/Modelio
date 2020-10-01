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
 * Proxy class to handle a {@link PropertyTableDefinition} with << requirement_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("406ee77a-a498-461f-bd3f-0a9f8dab7754")
public class RequirementPropertyset {
    @objid ("9aa3cab5-35cd-424f-b03e-dd1083aee9b3")
    public static final String STEREOTYPE_NAME = "requirement_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("29350daf-fa55-45e9-9c4d-fc06559092c7")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link RequirementPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << requirement_propertyset >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ac8cc888-4b99-4e4f-8f9c-a64fdbe1fcce")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RequirementPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << requirement_propertyset >> then instantiate a {@link RequirementPropertyset} proxy.
     * 
     * @return a {@link RequirementPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("13212a39-2dc0-4ad1-9571-67ba93473809")
    public static RequirementPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
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
    @objid ("6eb9d397-1adb-47de-bd24-93c84f91ff62")
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
    @objid ("6e68a5fd-b578-48ea-a6f9-d01e89f36b10")
    public static RequirementPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (RequirementPropertyset.canInstantiate(obj))
        	return new RequirementPropertyset(obj);
        else
        	throw new IllegalArgumentException("RequirementPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b2bb735b-7128-4c37-9fbd-567d700875d0")
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
    @objid ("309fca1a-38aa-455c-8bec-17d82bf03c4c")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("7a9c0f8b-8e23-4a36-b37e-9f414acf8155")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b387d510-5c67-45bc-9a0d-2357fff9caf5")
    protected RequirementPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("8fb90d31-ee90-41bf-8357-2e601bf691dc")
    public static final class MdaTypes {
        @objid ("affa8ea1-4c5b-4c0c-9e71-acd7aa565d90")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b1871c11-1152-494d-9a07-d4829db94eab")
        private static Stereotype MDAASSOCDEP;

        @objid ("fa16b866-4281-4727-a41e-d458f00a7be8")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3d429184-796a-4e63-9c2b-868382c3f0a7")
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
