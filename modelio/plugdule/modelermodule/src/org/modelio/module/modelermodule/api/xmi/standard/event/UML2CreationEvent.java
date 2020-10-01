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
 * Proxy class to handle a {@link Event} with << UML2CreationEvent >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("16a2c5a1-3ebe-4d4a-92c9-14e29d2a36a8")
public class UML2CreationEvent {
    @objid ("f71c2c52-8310-477e-ac0a-4eee0e9e15e4")
    public static final String STEREOTYPE_NAME = "UML2CreationEvent";

    /**
     * The underlying {@link Event} represented by this proxy, never null.
     */
    @objid ("a75b57d8-be2b-4ef1-9623-8185a35a91c8")
    protected final Event elt;

    /**
     * Tells whether a {@link UML2CreationEvent proxy} can be instantiated from a {@link MObject} checking it is a {@link Event} stereotyped << UML2CreationEvent >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("7cc8d1a4-1aed-4bf0-9c6d-579fd122048c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Event) && ((Event) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2CreationEvent.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Event} stereotyped << UML2CreationEvent >> then instantiate a {@link UML2CreationEvent} proxy.
     * 
     * @return a {@link UML2CreationEvent} proxy on the created {@link Event}.
     */
    @objid ("565270f1-7eb9-4f9c-b68a-f085a8816549")
    public static UML2CreationEvent create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Event");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2CreationEvent.STEREOTYPE_NAME);
        return UML2CreationEvent.instantiate((Event)e);
    }

    /**
     * Tries to instantiate a {@link UML2CreationEvent} proxy from a {@link Event} stereotyped << UML2CreationEvent >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Event
     * @return a {@link UML2CreationEvent} proxy or <i>null</i>.
     */
    @objid ("5d8a8a84-d1cf-450e-a586-dd34fb2cc70a")
    public static UML2CreationEvent instantiate(Event obj) {
        return UML2CreationEvent.canInstantiate(obj) ? new UML2CreationEvent(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2CreationEvent} proxy from a {@link Event} stereotyped << UML2CreationEvent >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Event}
     * @return a {@link UML2CreationEvent} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("60fdbfa1-c128-417f-86e8-e6560859f8c2")
    public static UML2CreationEvent safeInstantiate(Event obj) throws IllegalArgumentException {
        if (UML2CreationEvent.canInstantiate(obj))
        	return new UML2CreationEvent(obj);
        else
        	throw new IllegalArgumentException("UML2CreationEvent: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("6cd075d5-a405-4cd7-95bb-655f2cfb060d")
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
        UML2CreationEvent other = (UML2CreationEvent) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Event}. 
     * @return the Event represented by this proxy, never null.
     */
    @objid ("612ca180-7509-451f-b51a-81574babe8d4")
    public Event getElement() {
        return this.elt;
    }

    @objid ("4d3ae294-6497-42a5-9283-63d4b615f0bb")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2c0b4bbc-91c6-44c0-9768-e8c525142b71")
    protected UML2CreationEvent(Event elt) {
        this.elt = elt;
    }

    @objid ("ccd8e28f-0f71-4eec-bd00-ff6832d03c10")
    public static final class MdaTypes {
        @objid ("00deb2dd-ce3a-49ec-829c-98cd5f09c02e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("670d298a-1fb7-4dc7-96f6-4704cae52f57")
        private static Stereotype MDAASSOCDEP;

        @objid ("47cfe859-e80b-4268-ad72-ff4054a78d7d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("14f18a38-9f07-4840-8b28-529c8877ce2b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "4edcbbef-5d0a-11df-a996-001302895b2b");
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
