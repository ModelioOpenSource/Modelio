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
 * Proxy class to handle a {@link Event} with << UML2CreationEvent >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("16a2c5a1-3ebe-4d4a-92c9-14e29d2a36a8")
public class UML2CreationEvent {
    @objid ("4c8327d7-e22a-40a7-93d0-be6ed1606336")
    public static final String STEREOTYPE_NAME = "UML2CreationEvent";

    /**
     * The underlying {@link Event} represented by this proxy, never null.
     */
    @objid ("da8876ab-4387-4d0b-80ba-26c0cc86ff0d")
    protected final Event elt;

    /**
     * Tells whether a {@link UML2CreationEvent proxy} can be instantiated from a {@link MObject} checking it is a {@link Event} stereotyped << UML2CreationEvent >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("786b87fe-96fa-498f-a8da-28f24eec84f6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Event) && ((Event) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2CreationEvent.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Event} stereotyped << UML2CreationEvent >> then instantiate a {@link UML2CreationEvent} proxy.
     * 
     * @return a {@link UML2CreationEvent} proxy on the created {@link Event}.
     */
    @objid ("190f8986-fe97-4696-a63f-b42c3adca4e5")
    public static UML2CreationEvent create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Event");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2CreationEvent.STEREOTYPE_NAME);
        return UML2CreationEvent.instantiate((Event)e);
    }

    /**
     * Tries to instantiate a {@link UML2CreationEvent} proxy from a {@link Event} stereotyped << UML2CreationEvent >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Event
     * @return a {@link UML2CreationEvent} proxy or <i>null</i>.
     */
    @objid ("026552fd-cc47-402c-9294-e4e2cb33dced")
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
    @objid ("4b39000a-6fab-410b-9cf6-577dbe620385")
    public static UML2CreationEvent safeInstantiate(Event obj) throws IllegalArgumentException {
        if (UML2CreationEvent.canInstantiate(obj))
        	return new UML2CreationEvent(obj);
        else
        	throw new IllegalArgumentException("UML2CreationEvent: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("65038e99-bda7-484d-94a1-7e8571203949")
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
    @objid ("ff8ef16b-35a4-4634-a131-d9801fbe1ede")
    public Event getElement() {
        return this.elt;
    }

    @objid ("35c15a6c-4cbe-4d52-93a1-1f3a75d9be80")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("88594364-1e5c-43b9-89aa-4a6064960dab")
    protected UML2CreationEvent(Event elt) {
        this.elt = elt;
    }

    @objid ("ccd8e28f-0f71-4eec-bd00-ff6832d03c10")
    public static final class MdaTypes {
        @objid ("4c6a0dbb-037f-45f1-a720-e076f3b84fe9")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("086787ee-77f0-41ba-ba03-eebb5a95a50e")
        private static Stereotype MDAASSOCDEP;

        @objid ("8c7b7df3-a109-4cdf-a63b-d56da272884f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5ea87108-00d6-4734-93d8-4ff9656005b5")
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
