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
package org.modelio.module.modelermodule.api.xmi.standard.opaqueaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
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
 * Proxy class to handle a {@link OpaqueAction} with << UML2CreateLinkObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d8f1f797-b904-47b7-a13f-1c46cc7fc8b7")
public class UML2CreateLinkObjectAction {
    @objid ("73f4cd9e-e210-40ed-b81d-67a1df69f7ac")
    public static final String STEREOTYPE_NAME = "UML2CreateLinkObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("e06a0b9c-2e4c-4ec6-a1b6-567f2fae6480")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2CreateLinkObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2CreateLinkObjectAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4b762611-a77f-48c5-8e50-250aada50fa0")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2CreateLinkObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2CreateLinkObjectAction >> then instantiate a {@link UML2CreateLinkObjectAction} proxy.
     * 
     * @return a {@link UML2CreateLinkObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("f81e6574-e1d6-4f11-93de-d91dda251ac6")
    public static UML2CreateLinkObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2CreateLinkObjectAction.STEREOTYPE_NAME);
        return UML2CreateLinkObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2CreateLinkObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateLinkObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2CreateLinkObjectAction} proxy or <i>null</i>.
     */
    @objid ("7902e003-dff4-475b-838e-40a99353fa30")
    public static UML2CreateLinkObjectAction instantiate(OpaqueAction obj) {
        return UML2CreateLinkObjectAction.canInstantiate(obj) ? new UML2CreateLinkObjectAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2CreateLinkObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateLinkObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2CreateLinkObjectAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("8c371e63-c03a-4703-935b-f1bc1dad1245")
    public static UML2CreateLinkObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2CreateLinkObjectAction.canInstantiate(obj))
        	return new UML2CreateLinkObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2CreateLinkObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f247a8fb-1f5a-4970-aa8c-9ee573275d0a")
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
        UML2CreateLinkObjectAction other = (UML2CreateLinkObjectAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("d9f954ab-8c41-4e00-83c5-ec5f84662dda")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("9638442c-5939-4536-9d46-3ce27a9e9a48")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("88856f26-e1b8-4546-826f-e9007d176a71")
    protected UML2CreateLinkObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("310f7147-2f6b-4605-9068-06aabbeaaf88")
    public static final class MdaTypes {
        @objid ("c5c5df48-6a39-45e2-95bc-1440c8dae7b5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e7c94869-c73e-49a7-9a13-0ee22848bab9")
        private static Stereotype MDAASSOCDEP;

        @objid ("5b1c167e-c916-44b0-b218-da6355397a78")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ddf3f4d1-3e84-45d5-a657-4668765ed2ff")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "f8e58a85-c2fa-11de-8ac8-001302895b2b");
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
