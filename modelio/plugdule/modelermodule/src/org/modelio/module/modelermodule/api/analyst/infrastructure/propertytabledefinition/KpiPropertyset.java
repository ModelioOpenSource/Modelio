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
 * Proxy class to handle a {@link PropertyTableDefinition} with << kpi_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("94e0054a-b4c4-4633-b4ad-1f5362743248")
public class KpiPropertyset {
    @objid ("623653ac-4175-4390-ad47-7fe174dca87c")
    public static final String STEREOTYPE_NAME = "kpi_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("3b22fc1c-294c-4e56-985f-ed59edc67a5c")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link KpiPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << kpi_propertyset >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("fbe14b45-4d6e-439d-8902-c5f0edd9fd94")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, KpiPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << kpi_propertyset >> then instantiate a {@link KpiPropertyset} proxy.
     * 
     * @return a {@link KpiPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("d01bd967-79c2-4268-bac1-880275c06f9a")
    public static KpiPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
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
    @objid ("82f478ca-12e2-4028-a7c1-d486c23d8d32")
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
    @objid ("57862262-bcf0-4528-823e-7a0315b21902")
    public static KpiPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (KpiPropertyset.canInstantiate(obj))
        	return new KpiPropertyset(obj);
        else
        	throw new IllegalArgumentException("KpiPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e98ba130-cde9-493a-8ac1-dadde95f6aec")
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
    @objid ("92ccbf14-c73b-4780-9311-acd5fa0ca886")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("4a196d74-81d7-4252-b753-f726f1e6d6a0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f4c32115-9ca7-484f-bfe5-c295c568494c")
    protected KpiPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("5a1258eb-5565-4ee1-b232-31e1a693018d")
    public static final class MdaTypes {
        @objid ("c2b4f4b1-ea8c-47b7-8a29-a7faef87025c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("608d2584-6eae-4675-977c-bf310ae33871")
        private static Stereotype MDAASSOCDEP;

        @objid ("a48084fb-aa7f-460e-aea5-ba8214910122")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5cb42d75-d197-4683-b0fd-23918764c7e6")
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
