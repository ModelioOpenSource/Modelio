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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReduceAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f1c94473-28e1-4fe7-ac2d-cd800cd05029")
public class UML2ReduceAction {
    @objid ("915921fd-8582-42f3-9c92-26caa1207d92")
    public static final String STEREOTYPE_NAME = "UML2ReduceAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("004907f3-a782-4263-8f9b-87c4b76e9af5")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReduceAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReduceAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("f4aa5959-b83a-44e9-8c06-e9c8bf61dab5")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReduceAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReduceAction >> then instantiate a {@link UML2ReduceAction} proxy.
     * 
     * @return a {@link UML2ReduceAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("4a80a35d-955e-462a-9c85-889507fd5577")
    public static UML2ReduceAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReduceAction.STEREOTYPE_NAME);
        return UML2ReduceAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReduceAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReduceAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReduceAction} proxy or <i>null</i>.
     */
    @objid ("63371e0b-4737-45ec-93a0-808a59b4e912")
    public static UML2ReduceAction instantiate(OpaqueAction obj) {
        return UML2ReduceAction.canInstantiate(obj) ? new UML2ReduceAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReduceAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReduceAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReduceAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("e31a3a9d-b831-411a-a658-41bd65fe7f35")
    public static UML2ReduceAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReduceAction.canInstantiate(obj))
        	return new UML2ReduceAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReduceAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("07a17ce7-4563-455b-b0bd-3be518055d3e")
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
        UML2ReduceAction other = (UML2ReduceAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("a44eba18-3a41-4bbf-8675-a34aa1f25834")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("6275edf0-8fb7-4923-a516-69752543c591")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a21f4add-d179-472c-9ba7-93399cd4b1cb")
    protected UML2ReduceAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("bb02c893-e717-4243-aea0-fd3720614737")
    public static final class MdaTypes {
        @objid ("c5ba57a7-b7a6-4a69-8c7d-68800d16decf")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7426adcc-3c63-4c40-935d-dae0615b141a")
        private static Stereotype MDAASSOCDEP;

        @objid ("e46db692-35f6-4467-a981-205499c55dd5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7a349939-c836-4ba9-a288-2537acef1da7")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "2eb4ec1b-c2fd-11de-8ac8-001302895b2b");
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
