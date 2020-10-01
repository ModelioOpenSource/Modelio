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
package org.modelio.module.modelermodule.api.xmi.standard.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
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
 * Proxy class to handle a {@link Event} with << UML2ExecutionEvent >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("b2ec965b-ef55-4971-a1f2-5637918175da")
public class UML2ExecutionEvent {
    @objid ("1c3e0dc2-b452-467b-99a4-37b877391f1f")
    public static final String STEREOTYPE_NAME = "UML2ExecutionEvent";

    /**
     * The underlying {@link Event} represented by this proxy, never null.
     */
    @objid ("973e82f3-38fb-4108-aee4-193ae15a0fc6")
    protected final Event elt;

    /**
     * Tells whether a {@link UML2ExecutionEvent proxy} can be instantiated from a {@link MObject} checking it is a {@link Event} stereotyped << UML2ExecutionEvent >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("7ff08eeb-5875-424b-ac04-4c624c5bd661")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Event) && ((Event) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExecutionEvent.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Event} stereotyped << UML2ExecutionEvent >> then instantiate a {@link UML2ExecutionEvent} proxy.
     * 
     * @return a {@link UML2ExecutionEvent} proxy on the created {@link Event}.
     */
    @objid ("26d2fdb4-3124-482c-9022-07797f4f5467")
    public static UML2ExecutionEvent create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Event");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExecutionEvent.STEREOTYPE_NAME);
        return UML2ExecutionEvent.instantiate((Event)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExecutionEvent} proxy from a {@link Event} stereotyped << UML2ExecutionEvent >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Event
     * @return a {@link UML2ExecutionEvent} proxy or <i>null</i>.
     */
    @objid ("712bda01-c9fe-4bfa-899a-f535a4682750")
    public static UML2ExecutionEvent instantiate(Event obj) {
        return UML2ExecutionEvent.canInstantiate(obj) ? new UML2ExecutionEvent(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ExecutionEvent} proxy from a {@link Event} stereotyped << UML2ExecutionEvent >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Event}
     * @return a {@link UML2ExecutionEvent} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ddf0ab83-c04b-4b4a-a0cc-5fb469df1443")
    public static UML2ExecutionEvent safeInstantiate(Event obj) throws IllegalArgumentException {
        if (UML2ExecutionEvent.canInstantiate(obj))
        	return new UML2ExecutionEvent(obj);
        else
        	throw new IllegalArgumentException("UML2ExecutionEvent: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0aa74c89-e0ce-4b49-8c89-d4c1c8221a5d")
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
        UML2ExecutionEvent other = (UML2ExecutionEvent) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Event}. 
     * @return the Event represented by this proxy, never null.
     */
    @objid ("a649608b-a0cf-484e-a1ec-aeb4a66f07ec")
    public Event getElement() {
        return this.elt;
    }

    @objid ("c4d14547-fab9-4e04-bb48-bfe300c47ab1")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("42688013-edf9-4082-a799-33ffa8da06cf")
    protected UML2ExecutionEvent(Event elt) {
        this.elt = elt;
    }

    @objid ("f3b61e58-3dcd-44f6-852c-419944d58ac1")
    public static final class MdaTypes {
        @objid ("2b26220d-81d9-4ef5-ab58-5b31e36107e5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("73daf068-01e2-4b80-bf07-6da4f98dd856")
        private static Stereotype MDAASSOCDEP;

        @objid ("19b80189-88e8-40c1-b226-2b8772220816")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5ead95cd-a4b6-4684-997b-93a97cb00cb0")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "15d2108f-5d0c-11df-a996-001302895b2b");
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
