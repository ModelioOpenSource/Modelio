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
 * Proxy class to handle a {@link PropertyTableDefinition} with << risk_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c3f8d3e7-1c4c-4ac8-b29b-91c5210bbdc0")
public class RiskPropertyset {
    @objid ("88027fb4-e8ea-4b37-a2bc-6ad1cbc448b5")
    public static final String STEREOTYPE_NAME = "risk_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("ba62d9c4-da6c-4966-865f-76c16448539f")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link RiskPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << risk_propertyset >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("dcc107da-24c4-40d1-bb3a-23fd04a0a517")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RiskPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << risk_propertyset >> then instantiate a {@link RiskPropertyset} proxy.
     * 
     * @return a {@link RiskPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("4c202ebc-76c5-4357-a740-31e9e715ec25")
    public static RiskPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, RiskPropertyset.STEREOTYPE_NAME);
        return RiskPropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link RiskPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << risk_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link RiskPropertyset} proxy or <i>null</i>.
     */
    @objid ("ef79addd-8621-4158-8aaf-9b418d005172")
    public static RiskPropertyset instantiate(PropertyTableDefinition obj) {
        return RiskPropertyset.canInstantiate(obj) ? new RiskPropertyset(obj) : null;
    }

    /**
     * Tries to instantiate a {@link RiskPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << risk_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link PropertyTableDefinition}
     * @return a {@link RiskPropertyset} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("5e535eeb-ed41-43a1-9d38-901066c674bc")
    public static RiskPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (RiskPropertyset.canInstantiate(obj))
        	return new RiskPropertyset(obj);
        else
        	throw new IllegalArgumentException("RiskPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b47bdfa7-8bd6-48e4-9d38-a13def377478")
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
        RiskPropertyset other = (RiskPropertyset) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link PropertyTableDefinition}. 
     * @return the PropertyTableDefinition represented by this proxy, never null.
     */
    @objid ("d05f0f82-c5e2-41c1-aa8a-9a23ac94a5e6")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("84f095f4-fbe2-4fdb-ba37-bf8631b4ed26")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e40d90fc-0ed5-4482-8bf3-c45f27aff7bb")
    protected RiskPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("aca15579-4c69-4a63-8444-925d2d65d8fe")
    public static final class MdaTypes {
        @objid ("424f2f4c-628f-4e29-ae49-bccbc25bd522")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d756d7e0-d53d-4e97-b9fa-e8d115f30cff")
        private static Stereotype MDAASSOCDEP;

        @objid ("b0bf2abe-9ba9-4b8a-bc1b-3c2d6303946e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("afda9d47-c9ac-43df-bdfe-a871ab4c3b62")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "679a9417-8f06-4255-a409-1e1f7136e418");
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
