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
 * Proxy class to handle a {@link OpaqueAction} with << UML2AddVariableValueAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("05c0cf23-e64d-48c8-b386-93a8c01ad74b")
public class UML2AddVariableValueAction {
    @objid ("f02d9ec5-95b9-4cc0-ae5c-68754c2fb7ba")
    public static final String STEREOTYPE_NAME = "UML2AddVariableValueAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("d8b5e796-75a3-4ead-a3f9-261cce2626a5")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2AddVariableValueAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2AddVariableValueAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("1be49cea-1c7d-415b-95ab-0afb33edba59")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2AddVariableValueAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2AddVariableValueAction >> then instantiate a {@link UML2AddVariableValueAction} proxy.
     * 
     * @return a {@link UML2AddVariableValueAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("2c434003-04ce-4fb8-a143-8dc581569b71")
    public static UML2AddVariableValueAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2AddVariableValueAction.STEREOTYPE_NAME);
        return UML2AddVariableValueAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2AddVariableValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2AddVariableValueAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2AddVariableValueAction} proxy or <i>null</i>.
     */
    @objid ("bc3144e6-e856-406e-9f8a-62f9217a4806")
    public static UML2AddVariableValueAction instantiate(OpaqueAction obj) {
        return UML2AddVariableValueAction.canInstantiate(obj) ? new UML2AddVariableValueAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2AddVariableValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2AddVariableValueAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2AddVariableValueAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9ffcfabb-dba9-4331-bace-6c946fa7833f")
    public static UML2AddVariableValueAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2AddVariableValueAction.canInstantiate(obj))
        	return new UML2AddVariableValueAction(obj);
        else
        	throw new IllegalArgumentException("UML2AddVariableValueAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("01b3cdea-d80b-48fa-819b-68c35056b987")
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
        UML2AddVariableValueAction other = (UML2AddVariableValueAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("5086f521-58a5-4b25-a719-d0f1b52e2c46")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("c47c6977-32e7-45b2-9572-3c407f0b70b3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b0d2c581-74a1-4596-a9cf-246aa8001efe")
    protected UML2AddVariableValueAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("6292f25c-9e97-48f0-9eee-74b1c07894a1")
    public static final class MdaTypes {
        @objid ("2d73fe4b-1aa7-47a4-8a55-c69550af6f36")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2a0dc832-0dc1-47ed-b866-373e848b806c")
        private static Stereotype MDAASSOCDEP;

        @objid ("cfa8d647-a6e0-4afc-8bfa-f52ed8bbb8a1")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("bf6282ac-bef7-4f17-bde5-07f92af52d1b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "89326f2e-c2fc-11de-8ac8-001302895b2b");
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
