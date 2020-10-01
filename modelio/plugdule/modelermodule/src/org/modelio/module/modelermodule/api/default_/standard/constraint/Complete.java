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
package org.modelio.module.modelermodule.api.default_.standard.constraint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Constraint;
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
 * Proxy class to handle a {@link Constraint} with << complete >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("949d7ce8-5626-43fd-86c7-a67be11c28f1")
public class Complete {
    @objid ("96006367-ab3e-4b29-87ad-bde584711cba")
    public static final String STEREOTYPE_NAME = "complete";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("d7459577-4ec6-4cd9-a7e4-981ba95a152f")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Complete proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << complete >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("21b118b6-fc3f-4078-af88-9058e3cb519b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Complete.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << complete >> then instantiate a {@link Complete} proxy.
     * 
     * @return a {@link Complete} proxy on the created {@link Constraint}.
     */
    @objid ("2120d128-6868-4575-931e-f02d67849357")
    public static Complete create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Complete.STEREOTYPE_NAME);
        return Complete.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Complete} proxy from a {@link Constraint} stereotyped << complete >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Complete} proxy or <i>null</i>.
     */
    @objid ("650dfa83-bdcd-485d-8320-0d1207205e08")
    public static Complete instantiate(Constraint obj) {
        return Complete.canInstantiate(obj) ? new Complete(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Complete} proxy from a {@link Constraint} stereotyped << complete >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Complete} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("341e0134-812d-42da-921f-c696ef1e4bcc")
    public static Complete safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Complete.canInstantiate(obj))
        	return new Complete(obj);
        else
        	throw new IllegalArgumentException("Complete: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("76e6529a-ebc5-43fe-8c8f-d345a0c202b5")
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
        Complete other = (Complete) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("cf776655-b9ea-4703-a914-e72e30cfffde")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("dbc2f4ad-869e-449d-bb70-cf9cbcffbfa3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("af85e0b6-ae7d-44b3-aabc-76c8835fae5f")
    protected Complete(Constraint elt) {
        this.elt = elt;
    }

    @objid ("6ee7387e-71e0-4229-bfbd-e7a7e66d6948")
    public static final class MdaTypes {
        @objid ("a602202a-c9c4-4066-9134-b29a33f68457")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("967fe907-2951-4500-a9c0-5db006a4b0b1")
        private static Stereotype MDAASSOCDEP;

        @objid ("25d92c17-7a8a-4fcf-8680-09219e425f70")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c731fa10-c1b2-4ac7-bfc4-08410cf6e4ed")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01f1-0000-000000000000");
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
