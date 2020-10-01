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
    @objid ("cd4357b7-34b8-44f6-bdba-9c93e102cc58")
    public static final String STEREOTYPE_NAME = "Event";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("43f25b62-63ad-4872-9c10-0e45e7815f71")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Event proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Event >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("06549ef0-d6a0-4575-b856-81acdf7cb9c1")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Event.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Event >> then instantiate a {@link Event} proxy.
     * 
     * @return a {@link Event} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("a9d4fc81-013e-4cf5-bfd5-79a8ee8b2fb0")
    public static Event create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
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
    @objid ("191ef90e-4b4e-4401-b674-07943e6f104d")
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
    @objid ("a6f33d73-0956-4bf9-af13-bb48ee0c54fd")
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

    @objid ("3eccc397-5653-4cd3-8c79-a5140f312b5b")
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
    @objid ("8b255d5f-8e1c-4c2f-be2e-d2f9a97342d8")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("77a13fc2-1830-4191-9594-5ec868e86556")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("6fd21340-11d9-4d4e-949f-66d60c184457")
    protected Event(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("24c9a10e-2f9d-4fb5-9699-ecce4cc02405")
    public static final class MdaTypes {
        @objid ("a84824ca-b4f7-4c86-b6ed-c55324795f23")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("fc89cc0c-7665-4261-b1cc-d9ed21ef8a94")
        private static Stereotype MDAASSOCDEP;

        @objid ("5f2c1b88-555b-4ef8-94dd-c24c11a71457")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ab799538-09f2-4a04-83c4-9e9472ef9769")
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
