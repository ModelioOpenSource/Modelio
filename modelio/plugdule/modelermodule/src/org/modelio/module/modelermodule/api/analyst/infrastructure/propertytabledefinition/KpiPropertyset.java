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
 * Proxy class to handle a {@link PropertyTableDefinition} with << kpi_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("94e0054a-b4c4-4633-b4ad-1f5362743248")
public class KpiPropertyset {
    @objid ("0762511b-3f98-4cfa-ae3b-4c6275b9525e")
    public static final String STEREOTYPE_NAME = "kpi_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("fee37812-a1c3-4b4e-8d1a-ccbfde01ca2e")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link KpiPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << kpi_propertyset >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("5f8c7db2-4e7e-4d6d-a782-79f553aba0fa")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, KpiPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << kpi_propertyset >> then instantiate a {@link KpiPropertyset} proxy.
     * 
     * @return a {@link KpiPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("800fa665-9944-4060-a0ed-db7b5ddc1153")
    public static KpiPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, KpiPropertyset.STEREOTYPE_NAME);
        return KpiPropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link KpiPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << kpi_propertyset >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link KpiPropertyset} proxy or <i>null</i>.
     */
    @objid ("a4c2cd0f-fd57-450c-bf2c-01db3a369497")
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
    @objid ("22cead42-bc51-4ea3-a0ca-c54220e0ceff")
    public static KpiPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (KpiPropertyset.canInstantiate(obj))
        	return new KpiPropertyset(obj);
        else
        	throw new IllegalArgumentException("KpiPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("09cf8416-2117-4e0d-8f84-3758441ecf45")
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
    @objid ("7cd2ae4c-bd36-4c73-871e-b3946e3dd3bc")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("e32693a8-972e-4abe-b209-96889276323a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("32ddd9c2-7b01-49b9-9274-75e9b72cba1b")
    protected KpiPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("5a1258eb-5565-4ee1-b232-31e1a693018d")
    public static final class MdaTypes {
        @objid ("3cae7482-d852-4553-aab2-ac8583aa69e7")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("73683a7d-1be8-4029-a3f9-cc39c1d6e2e6")
        private static Stereotype MDAASSOCDEP;

        @objid ("8a1e200f-6a3a-4eb6-9483-67db0a2b44cd")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a5e8cc00-045f-48ad-942d-fbc8e07a1f6f")
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
