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
 * Proxy class to handle a {@link OpaqueAction} with << UML2UnmarshallAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("79f49b91-19a8-4376-b6f1-84c1d30a8534")
public class UML2UnmarshallAction {
    @objid ("78fa28f7-8141-454c-a7a6-5ccca3770838")
    public static final String STEREOTYPE_NAME = "UML2UnmarshallAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("9cf7e6ec-aafd-4620-a586-95c646e8ab7c")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2UnmarshallAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2UnmarshallAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("860033ad-9382-4d15-964a-0ba87533df7d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2UnmarshallAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2UnmarshallAction >> then instantiate a {@link UML2UnmarshallAction} proxy.
     * 
     * @return a {@link UML2UnmarshallAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("0875e13a-fd41-4bf1-a1b0-69ace66f46a5")
    public static UML2UnmarshallAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2UnmarshallAction.STEREOTYPE_NAME);
        return UML2UnmarshallAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2UnmarshallAction} proxy from a {@link OpaqueAction} stereotyped << UML2UnmarshallAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2UnmarshallAction} proxy or <i>null</i>.
     */
    @objid ("b6707466-1eef-434f-b6bf-f11e157d823b")
    public static UML2UnmarshallAction instantiate(OpaqueAction obj) {
        return UML2UnmarshallAction.canInstantiate(obj) ? new UML2UnmarshallAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2UnmarshallAction} proxy from a {@link OpaqueAction} stereotyped << UML2UnmarshallAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2UnmarshallAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("dea06976-7f1b-450e-a184-c5c037c5e1ed")
    public static UML2UnmarshallAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2UnmarshallAction.canInstantiate(obj))
        	return new UML2UnmarshallAction(obj);
        else
        	throw new IllegalArgumentException("UML2UnmarshallAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c7dbeb99-9275-46b9-9afd-45ee13701bc2")
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
        UML2UnmarshallAction other = (UML2UnmarshallAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("b6cfb96f-3866-4b71-a9e0-9184c185d149")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("a9564984-ff41-418b-b2e3-8ef0939be137")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("283bc3cc-9225-435a-87a7-59da7d42adc0")
    protected UML2UnmarshallAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("3dccb5eb-58b9-4a9d-ae64-d0336e7eb538")
    public static final class MdaTypes {
        @objid ("a311c710-0008-4103-98b0-af1e9bb9ca4b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("eadae447-0ea7-4c56-b466-260acb3c5b8d")
        private static Stereotype MDAASSOCDEP;

        @objid ("4d8cd97c-3c1f-4962-a165-84f2f20dcc25")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3471a637-f5c6-466a-a762-25cf6358e75e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "76c174ab-c2fd-11de-8ac8-001302895b2b");
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
