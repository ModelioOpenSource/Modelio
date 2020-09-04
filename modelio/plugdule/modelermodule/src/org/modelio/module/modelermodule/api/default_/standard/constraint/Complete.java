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
    @objid ("30c56c9c-933d-4112-b2c5-f4c9ea123c25")
    public static final String STEREOTYPE_NAME = "complete";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("53b95f58-0cb3-45f9-96b3-3d5040b21573")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Complete proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << complete >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("7417f368-140c-4149-9b8c-d074318cb307")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Complete.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << complete >> then instantiate a {@link Complete} proxy.
     * 
     * @return a {@link Complete} proxy on the created {@link Constraint}.
     */
    @objid ("c4f298ef-e370-4de9-be85-45cf95fde54d")
    public static Complete create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Complete.STEREOTYPE_NAME);
        return Complete.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Complete} proxy from a {@link Constraint} stereotyped << complete >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Complete} proxy or <i>null</i>.
     */
    @objid ("68e968a6-e446-4c97-bdbb-62b003cc9fe1")
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
    @objid ("6e29a804-bc87-4aef-a979-7977b1780a44")
    public static Complete safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Complete.canInstantiate(obj))
        	return new Complete(obj);
        else
        	throw new IllegalArgumentException("Complete: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("741f44a2-4c6d-4292-b6f3-fd07d844ff60")
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
    @objid ("249ff318-b6ce-4e35-89c0-2e6b21514d72")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("473f403d-00ed-41bb-ba00-ca95545bd88d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("70a0b917-b358-4e60-87fc-e77cd6ac1e44")
    protected Complete(Constraint elt) {
        this.elt = elt;
    }

    @objid ("6ee7387e-71e0-4229-bfbd-e7a7e66d6948")
    public static final class MdaTypes {
        @objid ("09cc95b1-5eb1-45dc-9f65-99e6091a2359")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9b4118c1-f2e4-499c-b4de-876579cbb5e2")
        private static Stereotype MDAASSOCDEP;

        @objid ("c3bd2c3e-d39c-4c0b-beef-dd406a7ec59b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2d952923-19c1-4169-b795-d4328c416485")
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
