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
    @objid ("fd0d30b3-0704-4e8d-8463-0acbeb24550f")
    public static final String STEREOTYPE_NAME = "UML2DestructionEvent";

    /**
     * The underlying {@link Event} represented by this proxy, never null.
     */
    @objid ("4510a6ee-b073-486b-b645-659cc2bee994")
    protected final Event elt;

    /**
     * Tells whether a {@link UML2DestructionEvent proxy} can be instantiated from a {@link MObject} checking it is a {@link Event} stereotyped << UML2DestructionEvent >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("3b705b42-e294-4beb-9dd4-c0b33cc1acab")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Event) && ((Event) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2DestructionEvent.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Event} stereotyped << UML2DestructionEvent >> then instantiate a {@link UML2DestructionEvent} proxy.
     * 
     * @return a {@link UML2DestructionEvent} proxy on the created {@link Event}.
     */
    @objid ("320f1e68-aa85-430d-919a-47df83d0881f")
    public static UML2DestructionEvent create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Event");
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
    @objid ("83640bdc-2282-4bfb-a036-52379c19a668")
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
    @objid ("e01aaffa-d530-4760-9bad-47c9cf7290ca")
    public static UML2DestructionEvent safeInstantiate(Event obj) throws IllegalArgumentException {
        if (UML2DestructionEvent.canInstantiate(obj))
        	return new UML2DestructionEvent(obj);
        else
        	throw new IllegalArgumentException("UML2DestructionEvent: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("4d39efdd-f49d-485b-bf7b-0425122f7499")
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
    @objid ("b477075a-2cfe-486d-ab8a-e8ad2441fa87")
    public Event getElement() {
        return this.elt;
    }

    @objid ("43ebe9a7-4c26-443a-967f-b48b0e61b5c2")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("7847e547-80f9-4f08-b594-57c045957acf")
    protected  UML2DestructionEvent(Event elt) {
        this.elt = elt;
    }

    @objid ("9f8a454e-892f-45b5-bfe5-6ffdf6e1cbbc")
    public static final class MdaTypes {
        @objid ("09d9508e-0d5b-45f8-90a0-41461baad94c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("922193cf-3820-49a3-a6fc-3200f9a58b5a")
        private static Stereotype MDAASSOCDEP;

        @objid ("f28be204-1fef-4016-9b67-90a6e02ac8aa")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9c01d471-4fcb-46fa-8522-37872373a64c")
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
