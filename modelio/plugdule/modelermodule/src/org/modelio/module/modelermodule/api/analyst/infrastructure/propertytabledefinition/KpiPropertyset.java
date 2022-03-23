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
 * Proxy class to handle a {@link PropertyTableDefinition} with << kpi_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("94e0054a-b4c4-4633-b4ad-1f5362743248")
public class KpiPropertyset {
    @objid ("2e1680f2-1b33-48fe-811a-c15212ccabf9")
    public static final String STEREOTYPE_NAME = "kpi_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("5df79bc8-cc32-412b-b605-1729b90ee757")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link KpiPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << kpi_propertyset >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("f2f9c719-5dd6-47e6-845e-27546570d772")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, KpiPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << kpi_propertyset >> then instantiate a {@link KpiPropertyset} proxy.
     * 
     * @return a {@link KpiPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("71a9524f-4f77-4d06-89cc-f5f726ed3e72")
    public static KpiPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, KpiPropertyset.STEREOTYPE_NAME);
        return KpiPropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link KpiPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << kpi_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link KpiPropertyset} proxy or <i>null</i>.
     */
    @objid ("f73c9817-7d7b-4a2d-8a36-4675070665fb")
    public static KpiPropertyset instantiate(PropertyTableDefinition obj) {
        return KpiPropertyset.canInstantiate(obj) ? new KpiPropertyset(obj) : null;
    }

    /**
     * Tries to instantiate a {@link KpiPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << kpi_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link PropertyTableDefinition}
     * @return a {@link KpiPropertyset} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("cdb82b9c-fd2b-4c06-a465-fb4f494ace35")
    public static KpiPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (KpiPropertyset.canInstantiate(obj))
        	return new KpiPropertyset(obj);
        else
        	throw new IllegalArgumentException("KpiPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("29090c57-0c46-43d5-bb54-30f696be8dd6")
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
        KpiPropertyset other = (KpiPropertyset) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link PropertyTableDefinition}. 
     * @return the PropertyTableDefinition represented by this proxy, never null.
     */
    @objid ("7569c38f-776f-4ec9-858c-15a04efbb4cb")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("0ff3771a-f177-4bc3-8ecc-e99de06f6d06")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("fd274be4-15f1-42a0-9a2e-280e7c787505")
    protected  KpiPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("5a1258eb-5565-4ee1-b232-31e1a693018d")
    public static final class MdaTypes {
        @objid ("4bf25c1d-19d6-4c10-8062-732deb57def3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ac357567-c5ee-4ea6-bd96-bd4ab650661f")
        private static Stereotype MDAASSOCDEP;

        @objid ("32086760-b71a-43b2-b484-385d9ce81bca")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("756d33eb-b1ff-49ec-a988-219c708e5de9")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "a1a154ca-b239-4724-9953-8ca651e3ee64");
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
