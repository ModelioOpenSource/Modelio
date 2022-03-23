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
 * Proxy class to handle a {@link Event} with << UML2CreationEvent >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("16a2c5a1-3ebe-4d4a-92c9-14e29d2a36a8")
public class UML2CreationEvent {
    @objid ("7f9df946-6603-416c-b87a-e43345776bbe")
    public static final String STEREOTYPE_NAME = "UML2CreationEvent";

    /**
     * The underlying {@link Event} represented by this proxy, never null.
     */
    @objid ("81b818de-b917-419e-a84f-0f391a7f5a53")
    protected final Event elt;

    /**
     * Tells whether a {@link UML2CreationEvent proxy} can be instantiated from a {@link MObject} checking it is a {@link Event} stereotyped << UML2CreationEvent >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("1fc62c1f-4af2-47c1-893a-434f82759548")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Event) && ((Event) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2CreationEvent.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Event} stereotyped << UML2CreationEvent >> then instantiate a {@link UML2CreationEvent} proxy.
     * 
     * @return a {@link UML2CreationEvent} proxy on the created {@link Event}.
     */
    @objid ("0a5d1453-274d-4063-bd7a-d8ce1b276787")
    public static UML2CreationEvent create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Event");
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
    @objid ("4fd66b87-0174-49be-add2-282c6097e005")
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
    @objid ("3beac459-36df-4ce5-ab99-599cbdec2347")
    public static UML2CreationEvent safeInstantiate(Event obj) throws IllegalArgumentException {
        if (UML2CreationEvent.canInstantiate(obj))
        	return new UML2CreationEvent(obj);
        else
        	throw new IllegalArgumentException("UML2CreationEvent: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("74b907eb-c147-461b-b02e-3d596bb29fe7")
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
    @objid ("ec902e9a-12c1-459e-ad95-2bd88877f739")
    public Event getElement() {
        return this.elt;
    }

    @objid ("059fdafb-757a-4135-aba8-76eefff57fa2")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("b8413b6f-5c87-43eb-9b77-f25ed8f038cd")
    protected  UML2CreationEvent(Event elt) {
        this.elt = elt;
    }

    @objid ("ccd8e28f-0f71-4eec-bd00-ff6832d03c10")
    public static final class MdaTypes {
        @objid ("3d0a5533-01a9-4fd7-8485-648237e51a74")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e8505b95-00f7-4d24-868e-8535a455d7aa")
        private static Stereotype MDAASSOCDEP;

        @objid ("2e8daba8-d77f-4aea-80a2-fe45e6b8f3d3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("cf6f57ed-8af3-4dff-a5f8-71a9f63e6005")
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
