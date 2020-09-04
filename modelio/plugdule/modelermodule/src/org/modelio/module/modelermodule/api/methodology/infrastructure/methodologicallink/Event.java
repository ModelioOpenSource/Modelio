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
    @objid ("ffce3f9a-07d0-4922-82e5-d6f4eea87b0f")
    public static final String STEREOTYPE_NAME = "Event";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("86e13147-acde-4249-b092-4e1f00d3d5ed")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Event proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Event >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("72f9a67d-9129-4dee-961d-2af132397074")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Event.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Event >> then instantiate a {@link Event} proxy.
     * 
     * @return a {@link Event} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("cc120515-41c2-42e5-a0bb-fe082a6e4b5a")
    public static Event create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Event.STEREOTYPE_NAME);
        return Event.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link Event} proxy from a {@link MethodologicalLink} stereotyped << Event >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link Event} proxy or <i>null</i>.
     */
    @objid ("c86648ce-8784-4c8a-9078-a14537704982")
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
    @objid ("5c349118-1a04-4583-b656-26bca2d9bd30")
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

    @objid ("56d3f1d0-d6d2-41f2-b75c-8545db76537b")
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
    @objid ("3604fe99-2e9f-4def-8f6a-f4ea0a5b4fdf")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("53227073-6b0d-46e8-aa37-0cc00518b9cd")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("cc8d4669-4df3-410e-8a32-d2f3d303afaf")
    protected Event(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("24c9a10e-2f9d-4fb5-9699-ecce4cc02405")
    public static final class MdaTypes {
        @objid ("e8142b3c-7fd9-4923-8681-2e0a3bd6c1ca")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8e1cbd68-904d-4de6-b067-5a342f446b94")
        private static Stereotype MDAASSOCDEP;

        @objid ("4115c778-5c83-42c5-88aa-e16f90928141")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("45a61377-a293-42e5-81e6-46790baf7f55")
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
