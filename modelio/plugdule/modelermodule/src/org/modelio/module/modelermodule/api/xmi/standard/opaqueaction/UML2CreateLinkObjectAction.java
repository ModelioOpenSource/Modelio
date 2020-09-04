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
    @objid ("8ce51420-8aa5-4de0-b6b0-4aaa0e0b790a")
    public static final String STEREOTYPE_NAME = "UML2CreateLinkObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("b2d8fd02-b96d-4af1-abc7-01e3b850c19b")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2CreateLinkObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2CreateLinkObjectAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("3e189700-3792-47d5-bfd6-71453ed33868")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2CreateLinkObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2CreateLinkObjectAction >> then instantiate a {@link UML2CreateLinkObjectAction} proxy.
     * 
     * @return a {@link UML2CreateLinkObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("5916de98-8df5-428f-9fa0-8b7b4435e9ad")
    public static UML2CreateLinkObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2CreateLinkObjectAction.STEREOTYPE_NAME);
        return UML2CreateLinkObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2CreateLinkObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateLinkObjectAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2CreateLinkObjectAction} proxy or <i>null</i>.
     */
    @objid ("9eb1e40f-608b-4e0a-9692-79dda8343179")
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
    @objid ("df2631ce-2b61-4c88-afbd-6488ed926b49")
    public static UML2CreateLinkObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2CreateLinkObjectAction.canInstantiate(obj))
        	return new UML2CreateLinkObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2CreateLinkObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("7e2af302-ea04-4e09-830e-f3e936dadf40")
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
    @objid ("bcdca077-eb47-4210-8d1d-f1168f916652")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("3317ef92-23a2-4be1-b716-da889b3329fd")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("8e60968d-71bd-42d8-8699-36157951b84e")
    protected UML2CreateLinkObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("310f7147-2f6b-4605-9068-06aabbeaaf88")
    public static final class MdaTypes {
        @objid ("4b178982-757c-4fd1-8c28-a331813b68c3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("84b4e4de-6a01-45f2-8ec2-f05a96ea65f6")
        private static Stereotype MDAASSOCDEP;

        @objid ("665034d5-0b78-4e1e-b906-c2d60f797256")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0b017032-f970-47e9-8b52-0cff1888cb67")
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
