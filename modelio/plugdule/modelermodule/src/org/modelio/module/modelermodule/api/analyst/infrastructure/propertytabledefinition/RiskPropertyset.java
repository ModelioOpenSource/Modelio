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
 * Proxy class to handle a {@link PropertyTableDefinition} with << risk_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c3f8d3e7-1c4c-4ac8-b29b-91c5210bbdc0")
public class RiskPropertyset {
    @objid ("807511bb-dc7e-4eca-a7a7-7e84af90a8a4")
    public static final String STEREOTYPE_NAME = "risk_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("23da8c1a-66db-42cf-8c49-40edf394fa10")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link RiskPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << risk_propertyset >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("ee8fa1f7-4a6e-4511-8644-39a17ca44755")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RiskPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << risk_propertyset >> then instantiate a {@link RiskPropertyset} proxy.
     * 
     * @return a {@link RiskPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("7f8bf489-0856-495a-9310-b42dd263ba93")
    public static RiskPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, RiskPropertyset.STEREOTYPE_NAME);
        return RiskPropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link RiskPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << risk_propertyset >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link RiskPropertyset} proxy or <i>null</i>.
     */
    @objid ("03361555-15fa-4a6a-9f1c-03547ccb37fa")
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
    @objid ("b2583990-8932-4c94-bc50-137f8b80d10e")
    public static RiskPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (RiskPropertyset.canInstantiate(obj))
        	return new RiskPropertyset(obj);
        else
        	throw new IllegalArgumentException("RiskPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1d3db9fc-76fe-4a33-b55a-b8220028c4ef")
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
    @objid ("a4f9ae03-e00a-4171-9fca-6a4674cf8de0")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("1218eff8-5854-41e5-831e-25b1fad2739b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b9d7db52-cc83-437a-aa03-ea457020cf6d")
    protected RiskPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("aca15579-4c69-4a63-8444-925d2d65d8fe")
    public static final class MdaTypes {
        @objid ("c37f0529-2b21-4dfa-b542-8cf92b7fea84")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("46602794-daf6-4036-bf0f-5f7a9f5d5821")
        private static Stereotype MDAASSOCDEP;

        @objid ("dba30ec5-8932-4b14-8e86-9808bbc3975d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3170e83f-4bee-477b-8a7c-2831db17b44c")
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
