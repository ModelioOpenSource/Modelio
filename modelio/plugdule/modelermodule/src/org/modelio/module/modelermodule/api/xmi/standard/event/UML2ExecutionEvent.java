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
 * Proxy class to handle a {@link Event} with << UML2ExecutionEvent >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("b2ec965b-ef55-4971-a1f2-5637918175da")
public class UML2ExecutionEvent {
    @objid ("6d85613d-1d85-44ba-a975-f5aac0a93ef9")
    public static final String STEREOTYPE_NAME = "UML2ExecutionEvent";

    /**
     * The underlying {@link Event} represented by this proxy, never null.
     */
    @objid ("9d432f3d-b189-4fef-94e6-3064d4230c26")
    protected final Event elt;

    /**
     * Tells whether a {@link UML2ExecutionEvent proxy} can be instantiated from a {@link MObject} checking it is a {@link Event} stereotyped << UML2ExecutionEvent >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("92b96d5e-9910-4c50-ab0c-058138da8963")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Event) && ((Event) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExecutionEvent.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Event} stereotyped << UML2ExecutionEvent >> then instantiate a {@link UML2ExecutionEvent} proxy.
     * 
     * @return a {@link UML2ExecutionEvent} proxy on the created {@link Event}.
     */
    @objid ("d4a29ea4-821a-4647-99c6-3f7da63fae62")
    public static UML2ExecutionEvent create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Event");
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
    @objid ("02c1c627-e92d-4a97-b685-32a9444ceeee")
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
    @objid ("e6bb4de0-c6a2-4cf7-b268-9af002cdfaec")
    public static UML2ExecutionEvent safeInstantiate(Event obj) throws IllegalArgumentException {
        if (UML2ExecutionEvent.canInstantiate(obj))
        	return new UML2ExecutionEvent(obj);
        else
        	throw new IllegalArgumentException("UML2ExecutionEvent: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8faf6419-9e05-4e55-a5da-b466f21bb6d4")
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
    @objid ("d3f080e4-36df-41ad-af62-682456417475")
    public Event getElement() {
        return this.elt;
    }

    @objid ("330b1922-e661-4c5a-aa1f-a2eaa80183db")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("daa9417b-33a0-47d9-b41c-74d7cc48f2bd")
    protected  UML2ExecutionEvent(Event elt) {
        this.elt = elt;
    }

    @objid ("f3b61e58-3dcd-44f6-852c-419944d58ac1")
    public static final class MdaTypes {
        @objid ("6ec86cf9-ce73-4c7b-a2fa-13c8fe1bdbd5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5c71234c-7876-442e-b316-5586277ad036")
        private static Stereotype MDAASSOCDEP;

        @objid ("595d9da7-38e8-45fc-9b54-e0bdf086a7c7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6f2581a8-fba5-495b-a839-04e1a2c3dff8")
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
