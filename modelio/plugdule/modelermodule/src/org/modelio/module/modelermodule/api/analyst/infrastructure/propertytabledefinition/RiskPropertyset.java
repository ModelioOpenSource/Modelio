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
 * Proxy class to handle a {@link PropertyTableDefinition} with << risk_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c3f8d3e7-1c4c-4ac8-b29b-91c5210bbdc0")
public class RiskPropertyset {
    @objid ("abb38401-8104-4719-af3f-1e8bff6c608f")
    public static final String STEREOTYPE_NAME = "risk_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("40bc95dd-bc83-41fa-a763-f9fe5c0ca900")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link RiskPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << risk_propertyset >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e30f8bab-cc65-4807-b047-9b4d4acc3518")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RiskPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << risk_propertyset >> then instantiate a {@link RiskPropertyset} proxy.
     * 
     * @return a {@link RiskPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("c40caacf-766c-4ce3-bfc5-b2092c5d87ff")
    public static RiskPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.PropertyTableDefinition");
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
    @objid ("b6b8951b-57ab-433e-b888-ea528eda1c8a")
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
    @objid ("cc660b38-1150-433e-b621-eb05dfd0155b")
    public static RiskPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (RiskPropertyset.canInstantiate(obj))
        	return new RiskPropertyset(obj);
        else
        	throw new IllegalArgumentException("RiskPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("174d5112-81c6-48c0-b4f4-d8b27e338c63")
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
    @objid ("21909c33-7ee4-42e3-8f17-328e632fef41")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("dc713322-4ff2-4152-ae47-506af43dd7a0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("be47ff53-cadf-4149-b52b-bb316c0b4521")
    protected  RiskPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("aca15579-4c69-4a63-8444-925d2d65d8fe")
    public static final class MdaTypes {
        @objid ("2202b966-1287-435f-a38a-08e7eb44cb2c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("400171e3-43b7-48d0-a740-6a24dbffaa89")
        private static Stereotype MDAASSOCDEP;

        @objid ("be18891f-805a-4ccd-8709-9264430481a3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d1ab9d42-3c25-4c51-95b6-737a12065fbf")
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
