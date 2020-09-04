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
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.operation;

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
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Operation} with << UML2Reception >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("33ec2a3b-ce08-4728-96a1-13288d8a2e8d")
public class UML2Reception {
    @objid ("bf0a4e51-c6cd-4725-be9e-b691ad256045")
    public static final String STEREOTYPE_NAME = "UML2Reception";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("0beae0ce-b1af-41c2-a7ff-73d005ef0b22")
    protected final Operation elt;

    /**
     * Tells whether a {@link UML2Reception proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation} stereotyped << UML2Reception >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("a7154623-564a-434d-b873-5d3156a78a46")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Operation) && ((Operation) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Reception.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Operation} stereotyped << UML2Reception >> then instantiate a {@link UML2Reception} proxy.
     * 
     * @return a {@link UML2Reception} proxy on the created {@link Operation}.
     */
    @objid ("2cb3fcb5-7487-4fff-bc6e-5137dfd8b535")
    public static UML2Reception create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Operation");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Reception.STEREOTYPE_NAME);
        return UML2Reception.instantiate((Operation)e);
    }

    /**
     * Tries to instantiate a {@link UML2Reception} proxy from a {@link Operation} stereotyped << UML2Reception >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Operation
     * @return a {@link UML2Reception} proxy or <i>null</i>.
     */
    @objid ("ca801717-930d-41f1-bb8c-8e9f6808d0fe")
    public static UML2Reception instantiate(Operation obj) {
        return UML2Reception.canInstantiate(obj) ? new UML2Reception(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Reception} proxy from a {@link Operation} stereotyped << UML2Reception >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Operation}
     * @return a {@link UML2Reception} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("39f002d0-4c1e-4429-89ea-7aaa9ee71538")
    public static UML2Reception safeInstantiate(Operation obj) throws IllegalArgumentException {
        if (UML2Reception.canInstantiate(obj))
        	return new UML2Reception(obj);
        else
        	throw new IllegalArgumentException("UML2Reception: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ef0a5bc9-d78a-481e-80c2-43115dd2903c")
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
        UML2Reception other = (UML2Reception) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Operation}. 
     * @return the Operation represented by this proxy, never null.
     */
    @objid ("1c328ee2-e84c-478b-9151-35963f34d9c0")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("deadd28d-8cec-4f08-8588-6d7d68be74f5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f369c753-41e4-4066-bf2e-9d5a4bc53e7d")
    protected UML2Reception(Operation elt) {
        this.elt = elt;
    }

    @objid ("0ae1c0c7-22eb-45ec-a199-bcb1a4c8b8ef")
    public static final class MdaTypes {
        @objid ("e870bf79-da57-4332-84cd-d8b69057fbf3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b0631c8a-3dd5-4186-a704-a26b84e27d76")
        private static Stereotype MDAASSOCDEP;

        @objid ("221d3804-1e09-48d5-aab8-22e592bad8cb")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a6ad06bd-07e0-4c0a-bf02-8f470f9542b7")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "a46b20b8-26ab-11df-ac88-001302895b2b");
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
