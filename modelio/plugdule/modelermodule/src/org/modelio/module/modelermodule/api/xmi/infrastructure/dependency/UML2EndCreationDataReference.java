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
package org.modelio.module.modelermodule.api.xmi.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << UML2EndCreationDataReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("2bb9e3da-9089-4006-a6a4-4b50ae303353")
public class UML2EndCreationDataReference {
    @objid ("52821bbf-2852-4305-9ec5-2561e8af60cc")
    public static final String STEREOTYPE_NAME = "UML2EndCreationDataReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("66f2872d-b40e-41d8-a8f7-2d3dc60c840e")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2EndCreationDataReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2EndCreationDataReference >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ad53819c-93bc-4e1d-9931-f7253eccdfc7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2EndCreationDataReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2EndCreationDataReference >> then instantiate a {@link UML2EndCreationDataReference} proxy.
     * 
     * @return a {@link UML2EndCreationDataReference} proxy on the created {@link Dependency}.
     */
    @objid ("7fc61de0-5298-41bf-9c91-9e6a891fb7d3")
    public static UML2EndCreationDataReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2EndCreationDataReference.STEREOTYPE_NAME);
        return UML2EndCreationDataReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2EndCreationDataReference} proxy from a {@link Dependency} stereotyped << UML2EndCreationDataReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2EndCreationDataReference} proxy or <i>null</i>.
     */
    @objid ("492a5e9a-058a-4dc7-8ae3-bbd64ecde6ef")
    public static UML2EndCreationDataReference instantiate(Dependency obj) {
        return UML2EndCreationDataReference.canInstantiate(obj) ? new UML2EndCreationDataReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2EndCreationDataReference} proxy from a {@link Dependency} stereotyped << UML2EndCreationDataReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2EndCreationDataReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("42fd3d72-7a8f-4483-8313-76fb8380f962")
    public static UML2EndCreationDataReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2EndCreationDataReference.canInstantiate(obj))
        	return new UML2EndCreationDataReference(obj);
        else
        	throw new IllegalArgumentException("UML2EndCreationDataReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("edd13420-9474-498b-b5f8-ffad6844decf")
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
        UML2EndCreationDataReference other = (UML2EndCreationDataReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("30e6d139-9772-4d7d-8713-adf3c0aa540f")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("81465b41-c50b-401c-abef-802ffda6468b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("efb6e6a1-5abd-4689-aba9-96f42ec44b21")
    protected UML2EndCreationDataReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("ce8685fd-e624-48a1-a3ab-352efe352774")
    public static final class MdaTypes {
        @objid ("dc67b724-9be2-4601-9b5f-517382bc07fb")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("daef2125-c027-4593-bc7a-0c92336ac1f7")
        private static Stereotype MDAASSOCDEP;

        @objid ("330926b8-0032-47f3-a79e-f4d0ee9990e7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("fb0f9470-03a5-4ec4-8a7c-59a4766bf28d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ee06097f-de99-11de-905b-001302895b2b");
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
