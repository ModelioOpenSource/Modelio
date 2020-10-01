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
 * Proxy class to handle a {@link Event} with << UML2DestructionEvent >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("de52f393-67b1-475c-bd25-f72709e6e3a4")
public class UML2DestructionEvent {
    @objid ("174be668-074b-44b1-9dee-478e25895f7e")
    public static final String STEREOTYPE_NAME = "UML2DestructionEvent";

    /**
     * The underlying {@link Event} represented by this proxy, never null.
     */
    @objid ("09475427-9ac3-4e16-b1b1-5054d0da8a87")
    protected final Event elt;

    /**
     * Tells whether a {@link UML2DestructionEvent proxy} can be instantiated from a {@link MObject} checking it is a {@link Event} stereotyped << UML2DestructionEvent >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("429f22e2-e99c-40ff-b6d5-e84fc007182b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Event) && ((Event) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2DestructionEvent.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Event} stereotyped << UML2DestructionEvent >> then instantiate a {@link UML2DestructionEvent} proxy.
     * 
     * @return a {@link UML2DestructionEvent} proxy on the created {@link Event}.
     */
    @objid ("86a78bbd-3d0a-42d2-a61d-016cbc4507c4")
    public static UML2DestructionEvent create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Event");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2DestructionEvent.STEREOTYPE_NAME);
        return UML2DestructionEvent.instantiate((Event)e);
    }

    /**
     * Tries to instantiate a {@link UML2DestructionEvent} proxy from a {@link Event} stereotyped << UML2DestructionEvent >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Event
     * @return a {@link UML2DestructionEvent} proxy or <i>null</i>.
     */
    @objid ("ad381852-a589-465a-8838-8b000ef7b5ed")
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
    @objid ("54e09b91-22d7-4eb3-a440-48520d1a6004")
    public static UML2DestructionEvent safeInstantiate(Event obj) throws IllegalArgumentException {
        if (UML2DestructionEvent.canInstantiate(obj))
        	return new UML2DestructionEvent(obj);
        else
        	throw new IllegalArgumentException("UML2DestructionEvent: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5131d99c-346a-4d8f-a818-226a72a9ce07")
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
    @objid ("ef110bc7-6efa-4ec7-9e66-d8e98fcbc7e3")
    public Event getElement() {
        return this.elt;
    }

    @objid ("216310bc-3742-42e2-a4f6-c0f55d396f56")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("46fd307d-952e-40b9-b0f9-2a2ce632fd51")
    protected UML2DestructionEvent(Event elt) {
        this.elt = elt;
    }

    @objid ("9f8a454e-892f-45b5-bfe5-6ffdf6e1cbbc")
    public static final class MdaTypes {
        @objid ("d0ba5b48-69c6-42ab-b478-c8bb51845992")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5e8c8984-f6cb-47f0-8d44-6d0e2f53b08b")
        private static Stereotype MDAASSOCDEP;

        @objid ("d543b7bc-a2f4-4e32-95e6-314073540877")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3c7085e8-158f-4b67-b16f-f04d841e486e")
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
