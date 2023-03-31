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
    @objid ("2aae4524-c6c7-4ae3-87bb-f3f6e9a1ecd1")
    public static final String STEREOTYPE_NAME = "UML2EndCreationDataReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("fca25ef0-537a-426c-873d-a5aac2abe777")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2EndCreationDataReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2EndCreationDataReference >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("787fb67d-8b63-4b45-b093-88358e5c9c43")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2EndCreationDataReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2EndCreationDataReference >> then instantiate a {@link UML2EndCreationDataReference} proxy.
     * 
     * @return a {@link UML2EndCreationDataReference} proxy on the created {@link Dependency}.
     */
    @objid ("545340ec-0701-45c6-9f65-b0e6b01d0154")
    public static UML2EndCreationDataReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
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
    @objid ("018ebf25-a7fe-4e97-a94f-46b134201dc3")
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
    @objid ("4b786d7f-bf9f-421c-9e08-1a6002036066")
    public static UML2EndCreationDataReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2EndCreationDataReference.canInstantiate(obj))
        	return new UML2EndCreationDataReference(obj);
        else
        	throw new IllegalArgumentException("UML2EndCreationDataReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("37391e3a-af99-44a3-a75f-420ec5596466")
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
    @objid ("c02c7c8d-6af8-4351-a498-6f7cd8509618")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("bfe75690-a6f7-4f31-984f-c858868aef09")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("07b09105-f482-49a3-ba08-b62ab5306e20")
    protected  UML2EndCreationDataReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("ce8685fd-e624-48a1-a3ab-352efe352774")
    public static final class MdaTypes {
        @objid ("d66b1267-85c8-484d-b045-74fc20220dfc")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("bb98beb0-1dde-43b5-9789-7bb0502eb877")
        private static Stereotype MDAASSOCDEP;

        @objid ("165815bc-02c2-4581-9c6a-dff48d4d6da6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("feb51100-912e-454c-84b4-a85884c0dcb9")
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
