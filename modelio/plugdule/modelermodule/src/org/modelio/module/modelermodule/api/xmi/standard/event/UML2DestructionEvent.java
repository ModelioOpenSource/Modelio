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
 * Proxy class to handle a {@link Event} with << UML2DestructionEvent >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("de52f393-67b1-475c-bd25-f72709e6e3a4")
public class UML2DestructionEvent {
    @objid ("d6b7061e-ba6a-468c-8393-b5480bad1239")
    public static final String STEREOTYPE_NAME = "UML2DestructionEvent";

    /**
     * The underlying {@link Event} represented by this proxy, never null.
     */
    @objid ("d8a1799e-2d1e-499f-a7cf-6468ae8c28d2")
    protected final Event elt;

    /**
     * Tells whether a {@link UML2DestructionEvent proxy} can be instantiated from a {@link MObject} checking it is a {@link Event} stereotyped << UML2DestructionEvent >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("44ae0eb4-2d0f-4be7-9d14-4fde13acba52")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Event) && ((Event) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2DestructionEvent.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Event} stereotyped << UML2DestructionEvent >> then instantiate a {@link UML2DestructionEvent} proxy.
     * 
     * @return a {@link UML2DestructionEvent} proxy on the created {@link Event}.
     */
    @objid ("22c0748d-a132-4ea6-961e-deacbdf022cf")
    public static UML2DestructionEvent create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Event");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2DestructionEvent.STEREOTYPE_NAME);
        return UML2DestructionEvent.instantiate((Event)e);
    }

    /**
     * Tries to instantiate a {@link UML2DestructionEvent} proxy from a {@link Event} stereotyped << UML2DestructionEvent >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Event
     * @return a {@link UML2DestructionEvent} proxy or <i>null</i>.
     */
    @objid ("246aea2a-d8b2-42a6-84c1-609d0293c6bf")
    public static UML2DestructionEvent instantiate(Event obj) {
        return UML2DestructionEvent.canInstantiate(obj) ? new UML2DestructionEvent(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2DestructionEvent} proxy from a {@link Event} stereotyped << UML2DestructionEvent >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Event}
     * @return a {@link UML2DestructionEvent} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f1068193-67f5-4bd8-8149-a1131d222553")
    public static UML2DestructionEvent safeInstantiate(Event obj) throws IllegalArgumentException {
        if (UML2DestructionEvent.canInstantiate(obj))
        	return new UML2DestructionEvent(obj);
        else
        	throw new IllegalArgumentException("UML2DestructionEvent: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("6b463b19-3748-40ce-ad34-00446a87fca0")
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
        UML2DestructionEvent other = (UML2DestructionEvent) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Event}. 
     * @return the Event represented by this proxy, never null.
     */
    @objid ("4794495e-19d9-4dc0-b082-3916039089f7")
    public Event getElement() {
        return this.elt;
    }

    @objid ("15efd912-75a7-4e42-8915-43fc37c0d70f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("eb67d9cf-da47-47e2-b792-acd2f09ec923")
    protected UML2DestructionEvent(Event elt) {
        this.elt = elt;
    }

    @objid ("9f8a454e-892f-45b5-bfe5-6ffdf6e1cbbc")
    public static final class MdaTypes {
        @objid ("2f3ad330-b196-451a-b2fc-f325835487f0")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2d530d12-cddb-45fb-ac45-464077b70885")
        private static Stereotype MDAASSOCDEP;

        @objid ("ada22b03-730a-461d-b2f8-ea488cc391e4")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("23a4a58a-5339-4aef-97be-c42266e45fb1")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "c0f03827-5d0b-11df-a996-001302895b2b");
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
