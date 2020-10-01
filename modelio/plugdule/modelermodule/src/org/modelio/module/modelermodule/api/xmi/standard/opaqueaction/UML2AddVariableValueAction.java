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
 * Proxy class to handle a {@link OpaqueAction} with << UML2AddVariableValueAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("05c0cf23-e64d-48c8-b386-93a8c01ad74b")
public class UML2AddVariableValueAction {
    @objid ("bb319cce-22da-4622-bd82-512de4978d39")
    public static final String STEREOTYPE_NAME = "UML2AddVariableValueAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("1903c118-9d58-4084-9a14-0ea9ca6469c3")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2AddVariableValueAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2AddVariableValueAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("00903d0e-17a3-40a3-a5ec-61daf0867d19")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2AddVariableValueAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2AddVariableValueAction >> then instantiate a {@link UML2AddVariableValueAction} proxy.
     * 
     * @return a {@link UML2AddVariableValueAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("f4e98499-9916-4b84-8f4e-bcf278d4916c")
    public static UML2AddVariableValueAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2AddVariableValueAction.STEREOTYPE_NAME);
        return UML2AddVariableValueAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2AddVariableValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2AddVariableValueAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2AddVariableValueAction} proxy or <i>null</i>.
     */
    @objid ("233f39ce-0d8c-436e-8c2f-e6f91b287ae7")
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
    @objid ("14c7429c-2199-49c4-b15f-cb03cc8bafa1")
    public static UML2AddVariableValueAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2AddVariableValueAction.canInstantiate(obj))
        	return new UML2AddVariableValueAction(obj);
        else
        	throw new IllegalArgumentException("UML2AddVariableValueAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("81e7f496-f3e0-4222-8da4-a56c4bb2183c")
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
    @objid ("cbab9a1c-06e4-4caa-a2a3-8e99b4fb37ba")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("5d9e9138-ee01-4c1e-8555-c077beadfcb0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2c0b8ba5-305e-4032-a2f1-def478817aec")
    protected UML2AddVariableValueAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("6292f25c-9e97-48f0-9eee-74b1c07894a1")
    public static final class MdaTypes {
        @objid ("1ed8d582-09a6-43d6-a29a-0e0ebb662b6b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4bd13bba-deef-46a1-905a-43faf5534283")
        private static Stereotype MDAASSOCDEP;

        @objid ("582ae275-b126-4213-abd7-4205f0913f4c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("98e202fb-e065-4a95-9eda-1a4e261321a3")
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
