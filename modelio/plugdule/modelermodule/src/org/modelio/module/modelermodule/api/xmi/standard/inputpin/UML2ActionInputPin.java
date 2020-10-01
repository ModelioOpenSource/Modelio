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
package org.modelio.module.modelermodule.api.xmi.standard.inputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
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
 * Proxy class to handle a {@link InputPin} with << UML2ActionInputPin >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3bedd0c8-c338-4af8-95d9-6c58807f3ac3")
public class UML2ActionInputPin {
    @objid ("a804460e-ea89-4081-8640-81535487d37c")
    public static final String STEREOTYPE_NAME = "UML2ActionInputPin";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("165163d0-176d-4229-b956-3bb15613e985")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2ActionInputPin proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2ActionInputPin >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e95634de-001b-4ce8-84d2-e14e2b9bd391")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ActionInputPin.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2ActionInputPin >> then instantiate a {@link UML2ActionInputPin} proxy.
     * 
     * @return a {@link UML2ActionInputPin} proxy on the created {@link InputPin}.
     */
    @objid ("27ae7377-3a7d-426d-9d6e-aed58d310975")
    public static UML2ActionInputPin create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ActionInputPin.STEREOTYPE_NAME);
        return UML2ActionInputPin.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2ActionInputPin} proxy from a {@link InputPin} stereotyped << UML2ActionInputPin >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2ActionInputPin} proxy or <i>null</i>.
     */
    @objid ("fb250b78-267e-45cd-ba74-d44dfe4ead73")
    public static UML2ActionInputPin instantiate(InputPin obj) {
        return UML2ActionInputPin.canInstantiate(obj) ? new UML2ActionInputPin(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ActionInputPin} proxy from a {@link InputPin} stereotyped << UML2ActionInputPin >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2ActionInputPin} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9b9ea631-1945-4211-a2e9-dbfe269bb9c0")
    public static UML2ActionInputPin safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2ActionInputPin.canInstantiate(obj))
        	return new UML2ActionInputPin(obj);
        else
        	throw new IllegalArgumentException("UML2ActionInputPin: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5f9e7f9a-d439-4817-99ee-83189485fda3")
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
        UML2ActionInputPin other = (UML2ActionInputPin) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("4b632362-ed07-42dc-8127-cc7663feeaef")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("4f6df9d4-eb82-4331-8bb5-6f066a919f62")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("07ca09e7-216d-497a-81b5-7f89ae6b45ec")
    protected UML2ActionInputPin(InputPin elt) {
        this.elt = elt;
    }

    @objid ("dc5d92d9-0b36-408d-b559-7f7dd32d402b")
    public static final class MdaTypes {
        @objid ("3e382f4a-c5dc-474b-b50e-5db4ae31f9a9")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ea7f4d47-fa89-4184-b555-5f15aba51282")
        private static Stereotype MDAASSOCDEP;

        @objid ("a0500471-b9d4-4c60-84ad-9f9ab85697f8")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("80787627-725b-4c83-8b65-71c11bee49d5")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "0bd72298-5d08-11df-a996-001302895b2b");
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
