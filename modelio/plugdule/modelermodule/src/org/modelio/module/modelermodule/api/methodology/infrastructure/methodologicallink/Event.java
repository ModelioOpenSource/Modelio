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
package org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
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
 * Proxy class to handle a {@link MethodologicalLink} with << Event >> stereotype.
 * <p>Stereotype description:
 * <br/><i>Drag & drop Business Event ou Application ou Technology Event dans BPMN fait apparaître un événement BPMN</i></p>
 */
@objid ("cf8c7e41-0c2a-41b4-9804-c91c0266763d")
public class Event {
    @objid ("54b4fe26-4aa4-488f-8f1d-d84b78e09f74")
    public static final String STEREOTYPE_NAME = "Event";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("babc9b65-4d7b-4abf-80dd-a41b1de206b8")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Event proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Event >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a007f982-6aaa-4bbb-99b5-c4e534afa2e8")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Event.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Event >> then instantiate a {@link Event} proxy.
     * 
     * @return a {@link Event} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("58922570-594a-4b97-be9d-0979743c8621")
    public static Event create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Event.STEREOTYPE_NAME);
        return Event.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link Event} proxy from a {@link MethodologicalLink} stereotyped << Event >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link Event} proxy or <i>null</i>.
     */
    @objid ("1d1373d4-2872-401b-b320-3dc37284d26d")
    public static Event instantiate(MethodologicalLink obj) {
        return Event.canInstantiate(obj) ? new Event(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Event} proxy from a {@link MethodologicalLink} stereotyped << Event >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link Event} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("e835211c-77da-4130-9e3a-cf6d622b1101")
    public static Event safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (Event.canInstantiate(obj))
        	return new Event(obj);
        else
        	throw new IllegalArgumentException("Event: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0f389cd8-7e96-4028-97c1-403e9e0020b6")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    @objid ("f7ab7bde-1a5a-48e4-831d-c0db79ec3fed")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("b841b00f-70dc-41aa-878d-83e114ba629b")
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
        Event other = (Event) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("bff26f63-c134-4323-a534-10194464599e")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("6919af31-7c07-4cca-8c6a-4e1df6292668")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("d0bf0a5e-1425-48d2-aea1-b907735997cc")
    protected  Event(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("24c9a10e-2f9d-4fb5-9699-ecce4cc02405")
    public static final class MdaTypes {
        @objid ("48a7c4a1-0f89-44b5-ae18-ad93514d92a1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("53b308c0-3619-4464-8b2f-fb6755e778fa")
        private static Stereotype MDAASSOCDEP;

        @objid ("bd4367aa-9524-4f86-9de9-37d660f8dcbd")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("89e5fef8-3671-4123-a867-c845df39b88b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "143b4e00-fe2e-44d0-9c64-5a95e385ec5a");
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
