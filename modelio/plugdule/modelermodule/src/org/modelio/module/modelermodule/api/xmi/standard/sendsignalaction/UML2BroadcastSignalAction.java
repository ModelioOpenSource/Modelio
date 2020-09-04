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
package org.modelio.module.modelermodule.api.xmi.standard.sendsignalaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
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
 * Proxy class to handle a {@link SendSignalAction} with << UML2BroadcastSignalAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("912daf5b-02b1-4804-a635-9b0de80c403f")
public class UML2BroadcastSignalAction {
    @objid ("f503849d-cc42-41c6-80f1-ccd3a83a0e95")
    public static final String STEREOTYPE_NAME = "UML2BroadcastSignalAction";

    /**
     * The underlying {@link SendSignalAction} represented by this proxy, never null.
     */
    @objid ("9ccb3533-c5ce-4cc5-85ad-24978c924e7b")
    protected final SendSignalAction elt;

    /**
     * Tells whether a {@link UML2BroadcastSignalAction proxy} can be instantiated from a {@link MObject} checking it is a {@link SendSignalAction} stereotyped << UML2BroadcastSignalAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("b2d59261-d3d4-47e3-9a25-b6a530b5a9fb")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof SendSignalAction) && ((SendSignalAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2BroadcastSignalAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link SendSignalAction} stereotyped << UML2BroadcastSignalAction >> then instantiate a {@link UML2BroadcastSignalAction} proxy.
     * 
     * @return a {@link UML2BroadcastSignalAction} proxy on the created {@link SendSignalAction}.
     */
    @objid ("1ad7d79a-894e-4582-adf8-65ad57c2411c")
    public static UML2BroadcastSignalAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("SendSignalAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2BroadcastSignalAction.STEREOTYPE_NAME);
        return UML2BroadcastSignalAction.instantiate((SendSignalAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2BroadcastSignalAction} proxy from a {@link SendSignalAction} stereotyped << UML2BroadcastSignalAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a SendSignalAction
     * @return a {@link UML2BroadcastSignalAction} proxy or <i>null</i>.
     */
    @objid ("00904511-b0e9-4f41-91ae-89843c3afda5")
    public static UML2BroadcastSignalAction instantiate(SendSignalAction obj) {
        return UML2BroadcastSignalAction.canInstantiate(obj) ? new UML2BroadcastSignalAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2BroadcastSignalAction} proxy from a {@link SendSignalAction} stereotyped << UML2BroadcastSignalAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link SendSignalAction}
     * @return a {@link UML2BroadcastSignalAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f969c84e-85dd-4b46-872e-5961814129b1")
    public static UML2BroadcastSignalAction safeInstantiate(SendSignalAction obj) throws IllegalArgumentException {
        if (UML2BroadcastSignalAction.canInstantiate(obj))
        	return new UML2BroadcastSignalAction(obj);
        else
        	throw new IllegalArgumentException("UML2BroadcastSignalAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5bc7e551-d589-4752-9911-39423f5b1e38")
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
        UML2BroadcastSignalAction other = (UML2BroadcastSignalAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link SendSignalAction}. 
     * @return the SendSignalAction represented by this proxy, never null.
     */
    @objid ("2c00754a-3d60-48a1-ab3a-964920eec6a0")
    public SendSignalAction getElement() {
        return this.elt;
    }

    @objid ("9df36a3c-033a-40d8-97f1-1bc6fb601dac")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e02a57fd-70b0-41fb-8ed9-129ee6b3b013")
    protected UML2BroadcastSignalAction(SendSignalAction elt) {
        this.elt = elt;
    }

    @objid ("e18ef05a-6696-4bc4-a862-c1d144cb1230")
    public static final class MdaTypes {
        @objid ("0ca5cfb9-01b7-4077-bbce-895facc408a8")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("031942ff-f049-43df-a150-e5accf5894cd")
        private static Stereotype MDAASSOCDEP;

        @objid ("89b87e7d-85db-4512-b53b-fd18577233a6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("478cf667-0f99-439a-85bf-078e1f275b14")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "edc05471-5d08-11df-a996-001302895b2b");
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
