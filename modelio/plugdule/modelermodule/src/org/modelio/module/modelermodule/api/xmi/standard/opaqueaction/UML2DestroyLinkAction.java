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
 * Proxy class to handle a {@link OpaqueAction} with << UML2DestroyLinkAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9a9af42c-042a-4e5c-b598-80ac68b8af16")
public class UML2DestroyLinkAction {
    @objid ("0d928e3d-4175-4784-8fef-81457706f504")
    public static final String STEREOTYPE_NAME = "UML2DestroyLinkAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("3f1b68d7-8168-4d56-8e6e-1cae6867fe75")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2DestroyLinkAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2DestroyLinkAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("ce5b5de8-c77f-48c3-86e4-6088c3e6b894")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2DestroyLinkAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2DestroyLinkAction >> then instantiate a {@link UML2DestroyLinkAction} proxy.
     * 
     * @return a {@link UML2DestroyLinkAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("b0e00f8e-1557-47a3-8616-c64317f9f885")
    public static UML2DestroyLinkAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2DestroyLinkAction.STEREOTYPE_NAME);
        return UML2DestroyLinkAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2DestroyLinkAction} proxy from a {@link OpaqueAction} stereotyped << UML2DestroyLinkAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2DestroyLinkAction} proxy or <i>null</i>.
     */
    @objid ("967a2276-a433-4aff-88be-663688e30a5f")
    public static UML2DestroyLinkAction instantiate(OpaqueAction obj) {
        return UML2DestroyLinkAction.canInstantiate(obj) ? new UML2DestroyLinkAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2DestroyLinkAction} proxy from a {@link OpaqueAction} stereotyped << UML2DestroyLinkAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2DestroyLinkAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("329f313d-78fa-45c4-acf8-3cc2301f77c7")
    public static UML2DestroyLinkAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2DestroyLinkAction.canInstantiate(obj))
        	return new UML2DestroyLinkAction(obj);
        else
        	throw new IllegalArgumentException("UML2DestroyLinkAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5f049cd3-148b-4535-9c3c-dbd1cd1cb206")
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
        UML2DestroyLinkAction other = (UML2DestroyLinkAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("7e5b7e9c-e4c0-465f-a324-cbfd01d0e5fe")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("00c343c7-9111-4b23-9c9d-93d1e14e5dad")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("14708b4c-19ba-4104-a400-d9bc5f9debdd")
    protected UML2DestroyLinkAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("32304d94-dbf4-4c93-af43-6a718691d283")
    public static final class MdaTypes {
        @objid ("b4f849a2-e26d-4b5e-b07a-ee6d27288dae")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("37829175-0867-46d8-a71f-16dbf399f821")
        private static Stereotype MDAASSOCDEP;

        @objid ("a51af690-3ca2-450b-9eb4-f72641389521")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("edc15942-d20d-4862-bc00-f2ee97da19aa")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "744f6321-c2f9-11de-8ac8-001302895b2b");
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
