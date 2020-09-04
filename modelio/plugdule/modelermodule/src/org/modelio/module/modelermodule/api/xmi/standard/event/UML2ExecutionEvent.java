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
    @objid ("d510a1b8-dceb-4fab-a72d-cc04b6306099")
    public static final String STEREOTYPE_NAME = "UML2ExecutionEvent";

    /**
     * The underlying {@link Event} represented by this proxy, never null.
     */
    @objid ("90236fe4-06a8-4efb-96b7-8ff33cdd5394")
    protected final Event elt;

    /**
     * Tells whether a {@link UML2ExecutionEvent proxy} can be instantiated from a {@link MObject} checking it is a {@link Event} stereotyped << UML2ExecutionEvent >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c6600e89-1cd0-4590-8c0d-5981a6cd401e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Event) && ((Event) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExecutionEvent.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Event} stereotyped << UML2ExecutionEvent >> then instantiate a {@link UML2ExecutionEvent} proxy.
     * 
     * @return a {@link UML2ExecutionEvent} proxy on the created {@link Event}.
     */
    @objid ("1d5b538d-6e92-47c9-b5c1-d0123a7bf68a")
    public static UML2ExecutionEvent create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Event");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExecutionEvent.STEREOTYPE_NAME);
        return UML2ExecutionEvent.instantiate((Event)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExecutionEvent} proxy from a {@link Event} stereotyped << UML2ExecutionEvent >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Event
     * @return a {@link UML2ExecutionEvent} proxy or <i>null</i>.
     */
    @objid ("eb4676a3-6a73-4370-bb44-0d64875744c3")
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
    @objid ("dac7ccac-c13e-4444-ab5e-d617b0fe2a7c")
    public static UML2ExecutionEvent safeInstantiate(Event obj) throws IllegalArgumentException {
        if (UML2ExecutionEvent.canInstantiate(obj))
        	return new UML2ExecutionEvent(obj);
        else
        	throw new IllegalArgumentException("UML2ExecutionEvent: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("04ba009b-9b26-40b0-93f9-a697e1010a11")
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
    @objid ("44fb4af8-d1f7-4a15-9e5b-cfa6af99a03f")
    public Event getElement() {
        return this.elt;
    }

    @objid ("59a2e8ce-40f2-46ab-a411-6090fb54c707")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a1b02a98-ea06-4ce8-be35-a3f853386ee0")
    protected UML2ExecutionEvent(Event elt) {
        this.elt = elt;
    }

    @objid ("f3b61e58-3dcd-44f6-852c-419944d58ac1")
    public static final class MdaTypes {
        @objid ("00a86cbc-4254-48f2-b6b0-855942463a31")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("176fd82e-2ad3-4917-ab56-4509f44b3f96")
        private static Stereotype MDAASSOCDEP;

        @objid ("4e74722a-0079-46b2-89a1-a72c6ed7e360")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9fedf855-7b7b-4e39-abfb-1c36e8df87d7")
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
